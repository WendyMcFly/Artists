package com.my.task.artists;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ArtistsActivity extends AppCompatActivity {

    private RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);

        list = (RecyclerView) findViewById(R.id.list);
        initViews();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setupToolbar(toolbar);

        obtainArtistInfo();
    }

    private void initViews() {
        final ArtistsAdapter adapter = new ArtistsAdapter();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(Artist artist) {
                ArtistDetailsActivity.start(ArtistsActivity.this, artist);
            }
        });

        list.addItemDecoration(new DividerItemDecoration(this));
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
    }

    private void setupToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
    }

    private void obtainArtistInfo() {
        final Retrofit retrofit = ((App) getApplication()).getRetrofit();
        final ArtistService artistService = retrofit.create(ArtistService.class);
        artistService.getArtists().enqueue(new GetArtistsCallback());
    }

    private void updateArtists(List<Artist> artists) {
        if (list.getAdapter() instanceof ArtistsAdapter) {
            ((ArtistsAdapter) list.getAdapter()).setArtists(artists);
        }
    }

    private void showError() {
        Toast.makeText(ArtistsActivity.this, R.string.error_message, Toast.LENGTH_SHORT).show();
    }

    public interface OnItemClickListener {
        void onClick(Artist artist);
    }

    private class GetArtistsCallback implements Callback<List<Artist>> {
        @Override
        public void onResponse(Call<List<Artist>> call, Response<List<Artist>> response) {
            updateArtists(response.body());
        }

        @Override
        public void onFailure(Call<List<Artist>> call, Throwable t) {
            showError();
        }
    }
}
