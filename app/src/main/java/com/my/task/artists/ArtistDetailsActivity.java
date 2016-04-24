package com.my.task.artists;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ArtistDetailsActivity extends AppCompatActivity {

    private static final String ARG_ARTIST = "arg.artist";

    private ImageView cover;
    private TextView genre;
    private TextView albums;
    private TextView songs;
    private TextView bio;

    public static void start(Context context, Artist artist) {
        Intent intent = new Intent(context, ArtistDetailsActivity.class);
        intent.putExtra(ARG_ARTIST, artist);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        findViews();
        setupToolbar(toolbar);

        Artist artist = (Artist) getIntent().getSerializableExtra(ARG_ARTIST);
        fillArtistInfo(artist);
    }

    private void setupToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    private void fillArtistInfo(Artist artist) {
        setTitle(artist.getName());

        bio.setText(artist.getDescription());
        albums.setText(getResources().getQuantityString(R.plurals.details_albums, artist.getAlbums(), artist.getAlbums()));
        songs.setText(getResources().getQuantityString(R.plurals.details_songs, artist.getTracks(), artist.getTracks()));

        String genres = TextUtils.join(", ", artist.getGenres());
        genre.setText(genres);
        Picasso.with(ArtistDetailsActivity.this).load(artist.getCover().getBig()).into(cover);
    }

    private void findViews() {
        cover = (ImageView) findViewById(R.id.artist_cover);
        genre = (TextView) findViewById(R.id.genre);
        albums = (TextView) findViewById(R.id.albums_count);
        songs = (TextView) findViewById(R.id.songs_count);
        bio = (TextView) findViewById(R.id.bio);
    }
}
