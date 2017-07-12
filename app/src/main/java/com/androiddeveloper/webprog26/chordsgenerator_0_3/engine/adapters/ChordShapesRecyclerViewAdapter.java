package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.R;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.constants.Constants;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.ChordShape;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by webprog on 11.07.17.
 */

public class ChordShapesRecyclerViewAdapter extends RecyclerView.Adapter<ChordShapesRecyclerViewAdapter.ChordShapesRecyclerViewViewHolder>
implements DataUpdater {

    private static final String TAG = "DataUpdater";

    private final Context mContext;
    private final View.OnClickListener onClickListener;
    private ArrayList<ChordShape> mChordShapes = new ArrayList<>();

    @Inject
    public ChordShapesRecyclerViewAdapter(Context context, View.OnClickListener onClickListener) {
        this.mContext = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public ChordShapesRecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChordShapesRecyclerViewViewHolder(LayoutInflater
                .from(getContext())
                .inflate(R.layout.chord_shapes_recyclerview_item,
                        parent, false));
    }

    @Override
    public void onBindViewHolder(ChordShapesRecyclerViewViewHolder holder, int position) {
        final ChordShape chordShape = getChordShapes().get(position);

        if(chordShape != null){

            holder.bind(chordShape);

        }
    }

    @Override
    public int getItemCount() {
        return getChordShapes().size();
    }

    private Context getContext() {
        return mContext;
    }

    private ArrayList<ChordShape> getChordShapes() {
        return mChordShapes;
    }

    private View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    @Override
    public void updateData(ArrayList<ChordShape> chordShapes) {
        this.mChordShapes = chordShapes;

        for(ChordShape chordShape: chordShapes){

            Log.i(TAG, chordShape.toString());

        }

        notifyDataSetChanged();
    }

    class ChordShapesRecyclerViewViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.cv_chord_shape)
        CardView mCvChordShape;

        @BindView(R.id.iv_chordshape)
        ImageView mIvChordShape;

        public ChordShapesRecyclerViewViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final ChordShape chordShape){

            Bitmap chordShapeBitmap = chordShape.getChordShapeBitmap();

            if(chordShapeBitmap != null){
                Log.i(TAG, chordShapeBitmap.toString());

                getIvChordShape().setImageBitmap(chordShapeBitmap);

            }

            getCvChordShape().setTag(getAdapterPosition());
            getCvChordShape().setOnClickListener(getOnClickListener());
        }

        private CardView getCvChordShape() {
            return mCvChordShape;
        }

        private ImageView getIvChordShape() {
            return mIvChordShape;
        }
    }
}
