package com.example.fuel_mule;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

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

    private class ImageHolder extends RecyclerView.ViewHolder {
        private ImageView im;

        public ImageHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.grid_item_image, parent, false));
        }

        public void bind(Bitmap bm) {
            im.setImageBitmap(bm);
        }
    }

    private class ImageAdapter extends RecyclerView.Adapter<ImageHolder> {
        private List<Bitmap> mImages;

        public ImageAdapter(List<Bitmap> bm) {
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

        }

        @Override
        public int getItemCount() {
            return mImages.size();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }

    // Check if there is a photo imported
    private boolean checkPhotoAdded() {
        return mAdapter.getItemCount() > 0;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(ResID, container, false);

        mImageGrid = v.findViewById(R.id.image_grid);
        mImageGrid.setLayoutManager(new GridLayoutManager(getContext(), 2));

        mAdapter = new ImageAdapter(Collections.<Bitmap>emptyList());

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
                // Open Camera
            }
        });

        mButtonImportPhoto = v.findViewById(R.id.import_photo_button);
        mButtonImportPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Files
            }
        });

        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }
}
