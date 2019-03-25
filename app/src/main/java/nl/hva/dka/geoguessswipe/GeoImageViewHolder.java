package nl.hva.dka.geoguessswipe;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class GeoImageViewHolder extends RecyclerView.ViewHolder {
    public ImageView mImageView;
    public View mView;

    public GeoImageViewHolder(@NonNull View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.imageView);
        mView = itemView;
    }
}
