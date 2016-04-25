package com.github.fesswood.yandextestapp.presentation.main.musicGroupList;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.github.fesswood.yandextestapp.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MusicGroupAdapter extends
        RecyclerView.Adapter<MusicGroupAdapter.MusicGroupViewHolder> {
    private final List<MusicGroupViewModel> contacts;
    private View.OnClickListener onItemClickListener;

    public MusicGroupAdapter(List<MusicGroupViewModel> contacts) {
        this.contacts = contacts;
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MusicGroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_group_view, parent, false);
        return new MusicGroupViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(MusicGroupViewHolder holder, int position) {
        holder.bind(contacts.get(position));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
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
            itemView.setOnClickListener(onItemClickListener);
        }

        public void bind(MusicGroupViewModel group) {
            mDvCover.setImageURI(Uri.parse(group.getCoverSmalUrl()));
            mTvTitle.setText(group.getName());
            mTvGenres.setText(Arrays.toString(group.getGenres().toArray()));
            mTvDescription.setText(group.getName());
        }
    }
}
