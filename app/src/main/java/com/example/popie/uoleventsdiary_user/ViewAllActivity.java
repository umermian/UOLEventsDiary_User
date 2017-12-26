package com.example.popie.uoleventsdiary_user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.popie.uoleventsdiary_user.Model.Event;
import com.example.popie.uoleventsdiary_user.RecyclerView.RecyclerViewAdpater;
import com.example.popie.uoleventsdiary_user.client.UserClient;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewAllActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Retrofit retrofit;
    UserClient userClient;
    RecyclerViewAdpater recyclerViewAdpater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.159/uoleventsdiary/public/api/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        userClient = retrofit.create(UserClient.class);

        Call<List<Event>> call = userClient.getEvents();

        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Total events" + response.body().size(), Toast.LENGTH_SHORT).show();
                    recyclerViewAdpater = new RecyclerViewAdpater(ViewAllActivity.this, response.body());
                    recyclerView.setAdapter(recyclerViewAdpater);

                } else {
                    Toast.makeText(getApplicationContext(), "Response Failure", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Try again when network is strong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
