package nl.hva.dka.geoguessswipe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {
    private final Context context;
    private ArrayList<GeoImage> mImages;
    private GeoImageAdapter geoImageAdapter;
    private RecyclerView mRecyclerView;
    private GestureDetector mGestureDetector;

    public MainActivity(){
        this.context = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImages = new ArrayList<>();

        for (int i = 0; i < GeoImage.IMAGE_NAMES.length; i++) {
            mImages.add(new GeoImage(GeoImage.IMAGE_NAMES[i], GeoImage.IMAGE_IDS[i]));
        }

        mRecyclerView = findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener());

        mRecyclerView.addOnItemTouchListener(this);

        geoImageAdapter = new GeoImageAdapter(mImages);
        mRecyclerView.setAdapter(geoImageAdapter);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    //Called when a user swipes left or right on a ViewHolder
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        //Get the index corresponding to the selected position
                        int position = (viewHolder.getAdapterPosition());
                        GeoImage geoImage = mImages.get(position);
                        mImages.remove(geoImage);
                        boolean isInEurope = isImageInEurope(geoImage.getName());
                        if (swipeDir == ItemTouchHelper.LEFT && isInEurope || swipeDir == ItemTouchHelper.RIGHT && !isInEurope) {
                            Toast.makeText(context, "You are right next one!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context, "You are wrong try again!", Toast.LENGTH_SHORT).show();
                            mImages.add(geoImage);
                        }
                        updateUI();

                        if (mImages.size() == 0) {
                            reset();
                        }
                    }


                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    private boolean isImageInEurope(String name) {
        return (name.startsWith("Y"));
    }

    private void reset() {
        for (int i = 0; i < GeoImage.IMAGE_NAMES.length; i++) {
            mImages.add(new GeoImage(GeoImage.IMAGE_NAMES[i], GeoImage.IMAGE_IDS[i]));
        }
        Toast.makeText(this, "New Round!", Toast.LENGTH_SHORT).show();
    }

    private void updateUI() {
        if (geoImageAdapter == null) {
            geoImageAdapter = new GeoImageAdapter(mImages);
            mRecyclerView.setAdapter(geoImageAdapter);
        } else {
            geoImageAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        int childAdapterPosition = recyclerView.getChildAdapterPosition(child);
        GeoImage geoImage = mImages.get(childAdapterPosition);
        Toast.makeText(this, geoImage.getName().substring(2), Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
