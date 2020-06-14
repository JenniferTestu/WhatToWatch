package com.jennifertestu.whattowatch.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.jennifertestu.whattowatch.adapter.ToWatchAdapter;

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {
    private RecyclerItemTouchHelperListener listener;

    public RecyclerItemTouchHelper(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            final View foregroundView = ((ToWatchAdapter.FilmViewHolder) viewHolder).viewForeground;

            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((ToWatchAdapter.FilmViewHolder) viewHolder).viewForeground;
        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final View foregroundView = ((ToWatchAdapter.FilmViewHolder) viewHolder).viewForeground;
        getDefaultUIUtil().clearView(foregroundView);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((ToWatchAdapter.FilmViewHolder) viewHolder).viewForeground;
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            if (dX > 0) {
                ((ToWatchAdapter.FilmViewHolder) viewHolder).textAime.setVisibility(View.VISIBLE);
                ((ToWatchAdapter.FilmViewHolder) viewHolder).viewBackground.setBackgroundColor(Color.parseColor("#5CB85C"));
                ((ToWatchAdapter.FilmViewHolder) viewHolder).textPasAime.setVisibility(View.GONE);

            } else {
                ((ToWatchAdapter.FilmViewHolder) viewHolder).textAime.setVisibility(View.GONE);
                ((ToWatchAdapter.FilmViewHolder) viewHolder).viewBackground.setBackgroundColor(Color.parseColor("#D9534F"));
                ((ToWatchAdapter.FilmViewHolder) viewHolder).textPasAime.setVisibility(View.VISIBLE);

            }

            getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                    actionState, isCurrentlyActive);
        }
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    public interface RecyclerItemTouchHelperListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
    }
}