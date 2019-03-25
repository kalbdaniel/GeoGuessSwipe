package nl.hva.dka.geoguessswipe;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class GeoImageAdapter extends RecyclerView.Adapter<GeoImageViewHolder> {
    public List<GeoImage> images;

    public GeoImageAdapter(List<GeoImage> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public GeoImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_cell, viewGroup, false);
        return new GeoImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GeoImageViewHolder geoImageViewHolder, int i) {
        final GeoImage geoImage = images.get(i);
        geoImageViewHolder.mImageView.setImageResource(geoImage.getId());
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}
