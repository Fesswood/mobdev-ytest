package com.github.fesswood.yandextestapp.presentation.main.musicGroupList;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.github.fesswood.yandextestapp.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MusicGroupAdapter extends
        RecyclerView.Adapter<MusicGroupAdapter.MusicGroupViewHolder> {
    private final List<MusicGroupViewModel> mMusicGroupList;
    private OnMusicGroupClickListener onItemClickListener;
    private int lastPosition = -1;
    private Context mContext;

    public MusicGroupAdapter(List<MusicGroupViewModel> contacts, Context context) {
        this.mMusicGroupList = contacts;
        mContext = context;
    }

    public void setOnMusicGroupClickListener(OnMusicGroupClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MusicGroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.music_group_view, parent, false);
        return new MusicGroupViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(MusicGroupViewHolder holder, int position) {
        holder.bind(mMusicGroupList.get(position));
        holder.itemView.setOnClickListener(v -> {
            onItemClickListener.selectMusicGroup(mMusicGroupList.get(position));
        });
        setAnimation(holder.itemView, position);
    }

    @Override
    public void onViewDetachedFromWindow(MusicGroupViewHolder holder) {
        (holder).clearAnimation();
    }


    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return mMusicGroupList.size();
    }

    public class MusicGroupViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.dvCover)
        SimpleDraweeView mDvCover;
        @Bind(R.id.tvTitle)
        TextView mTvTitle;
        @Bind(R.id.tvGenres)
        TextView mTvGenres;
        @Bind(R.id.tvDescription)
        TextView mTvDescription;

        public MusicGroupViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(MusicGroupViewModel group) {
            mDvCover.setImageURI(Uri.parse(group.getCoverSmallUrl()));
            mTvTitle.setText(group.getName());
            mTvGenres.setText(TextUtils.join(",", group.getGenres()));
            mTvDescription.setText(formatMusicGroupInfo(group));
        }

        @NonNull
        private String formatMusicGroupInfo(MusicGroupViewModel group) {
            return itemView.getContext().getString(R.string.small_description,
                    group.getAlbums(),
                    group.getTracks());
        }
        public void clearAnimation() {
            itemView.clearAnimation();
        }
    }

    public interface OnMusicGroupClickListener {
        public void selectMusicGroup(MusicGroupViewModel viewModel);
    }
}
