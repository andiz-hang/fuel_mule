package com.example.fuel_mule;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

@SuppressWarnings("FieldCanBeLocal")
public class CameraFragment extends DisplayFragment {
    private static final @LayoutRes int ResID = R.layout.fragment_camera;

    private RecyclerView mImageGrid;
    private ImageAdapter mAdapter;

    private Button mButtonAnalyze;
    private Button mButtonTakePhoto;
    private Button mButtonImportPhoto;

    private Callbacks mCallbacks;
    public interface Callbacks {
        void onAnalyzeButtonSelected();
    }

    private static class ImageHolder extends RecyclerView.ViewHolder {
        private ImageView im;

        public ImageHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.grid_item_image, parent, false));

            im = itemView.findViewById(R.id.image_camera);
        }

        public void bind(Bitmap bm) {
            im.setImageBitmap(bm);
        }

//        private ImageHolder(View itemView) {
//            super(itemView);
//            im = itemView.findViewById(R.id.image_camera);
//        }
    }

    private class ImageAdapter extends RecyclerView.Adapter<ImageHolder> {
        private ArrayList<Bitmap> mImages;

        public ImageAdapter(ArrayList<Bitmap> bm) {
            mImages = bm;
        }

        @NonNull
        @Override
        public ImageHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new ImageHolder(layoutInflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull ImageHolder ih, int i) {
            Bitmap b = mImages.get(i);
            ih.bind(b);
        }

        @Override
        public int getItemCount() {
            return mImages.size();
        }

        public ArrayList<Bitmap> getList() {
            return mImages;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(ResID, container, false);

        mImageGrid = v.findViewById(R.id.image_grid);
        mImageGrid.setLayoutManager(new GridLayoutManager(getContext(), 5));
        mAdapter = new ImageAdapter(new ArrayList<Bitmap>());
        mImageGrid.setAdapter(mAdapter);

        mButtonAnalyze = v.findViewById(R.id.analyze_button);
        mButtonAnalyze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPhotoAdded()) mCallbacks.onAnalyzeButtonSelected();
                else Toast.makeText(getActivity(), R.string.please_add_image, Toast.LENGTH_LONG).show();
            }
        });

        mButtonTakePhoto = v.findViewById(R.id.take_photo_button);
        mButtonTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Request Camera access
                PictureUtils.checkPermission(getActivity(), Manifest.permission.CAMERA, 0);

                Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);
            }
        });

        mButtonImportPhoto = v.findViewById(R.id.import_photo_button);
        mButtonImportPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Request FILE access
                PictureUtils.checkPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE, 0);

                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickPhoto.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(pickPhoto, 1);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_CANCELED) {
            switch (requestCode) {

                // Take Photo
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        addImageToGrid(selectedImage);
                    } break;

                // Import Photo
                case 1:
                    if (resultCode == RESULT_OK && data != null) {

                        ClipData clipdata = data.getClipData();
                        if (clipdata != null) {
                            ArrayList<Bitmap> bitmaps = new ArrayList<>();
                            for (int i=0; i<clipdata.getItemCount();i++) {
                                try {
                                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), clipdata.getItemAt(i).getUri());
                                    bitmaps.add(bitmap);
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                            addImageToGrid(bitmaps);

                        } else {

                            Uri selectedImage = data.getData();
                            String[] filePathColumn = {MediaStore.Images.Media.DATA};
                            if (selectedImage != null) {
                                Cursor cursor = getContext().getContentResolver().query(selectedImage,
                                        filePathColumn, null, null, null);
                                if (cursor != null) {
                                    cursor.moveToFirst();

                                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                    String picturePath = cursor.getString(columnIndex);

                                    //debug
                                    Log.d("debug", PictureUtils.getScaledBitmap(picturePath, getActivity()).getClass().getName());
                                    addImageToGrid(PictureUtils.getScaledBitmap(picturePath, getActivity()));
                                    cursor.close();
                                }
                            }
                        }
                    }

                    break;
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    // Check if there is a photo imported
    private boolean checkPhotoAdded() {
        return mAdapter.getItemCount() > 0;
    }

    private void addImageToGrid(Bitmap b) {

        ArrayList<Bitmap> bms = mAdapter.getList();
        bms.add(b);

        mAdapter = new ImageAdapter(bms);
        mImageGrid.setAdapter(mAdapter);
    }

    private void addImageToGrid(ArrayList<Bitmap> b) {

        ArrayList<Bitmap> bms = mAdapter.getList();
        bms.addAll(b);

        mAdapter = new ImageAdapter(bms);
        mImageGrid.setAdapter(mAdapter);
    }
}
