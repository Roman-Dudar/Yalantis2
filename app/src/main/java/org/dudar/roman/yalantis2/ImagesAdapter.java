package org.dudar.roman.yalantis2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * Created by Roman on 24.03.2016.
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImageViewHolder> {

    private int[] mImagesIds;

    public ImagesAdapter(int[] imagesIds) {
        this.mImagesIds = imagesIds;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ImageViewHolder(ImageView imageView) {
            super(imageView);
            this.imageView = imageView;
        }
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ImageView imageView = (ImageView) inflater.inflate(R.layout.image_view, parent, false);
        return new ImageViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        int currentImageId = mImagesIds[position];
        Context context = holder.imageView.getContext();
        Picasso.with(context)
                .load(currentImageId)
                .centerCrop()
                .fit()
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return mImagesIds.length;
    }

}
