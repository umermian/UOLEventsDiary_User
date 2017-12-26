package com.example.popie.uoleventsdiary_user.client;

import com.example.popie.uoleventsdiary_user.Model.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by popie on 12/26/2017.
 */

public interface UserClient {

    @GET("events")
    Call<List<Event>> getEvents();
}
