package com.my.task.artists;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ArtistHolder> {
    private List<Artist> artists = new ArrayList<>();
    private ArtistsActivity.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(ArtistsActivity.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ArtistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArtistHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist, parent, false));
    }

    @Override
    public void onBindViewHolder(ArtistHolder holder, int position) {
        final Artist artist = artists.get(position);
        holder.setContent(artist);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(artist);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public void setArtists(List<Artist> artists) {
        this.artists.clear();
        this.artists.addAll(artists);
        notifyDataSetChanged();
    }

    public class ArtistHolder extends RecyclerView.ViewHolder {
        private ImageView cover;
        private TextView name;
        private TextView genre;
        private TextView description;

        public ArtistHolder(View itemView) {
            super(itemView);
            cover = (ImageView) itemView.findViewById(R.id.artist_cover);
            name = (TextView) itemView.findViewById(R.id.artist_name);
            genre = (TextView) itemView.findViewById(R.id.genre);
            description = (TextView) itemView.findViewById(R.id.description);
        }

        public void setContent(Artist artist) {
            Resources res = itemView.getContext().getResources();
            String genreText = TextUtils.join(", ", artist.getGenres());
            String descriptionText = res.getQuantityString(R.plurals.details_albums, artist.getAlbums(), artist.getAlbums())
                    + ", "
                    + res.getQuantityString(R.plurals.details_songs, artist.getTracks(), artist.getTracks());

            name.setText(artist.getName());
            genre.setText(genreText);
            description.setText(descriptionText);
            Picasso.with(itemView.getContext()).load(artist.getCover().getSmall()).into(cover);
        }
    }
}
