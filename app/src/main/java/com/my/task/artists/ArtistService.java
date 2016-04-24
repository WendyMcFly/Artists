package com.my.task.artists;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ArtistService {
    @GET("download.cdn.yandex.net/mobilization-2016/artists.json")
    Call<List<Artist>> getArtists();
}
