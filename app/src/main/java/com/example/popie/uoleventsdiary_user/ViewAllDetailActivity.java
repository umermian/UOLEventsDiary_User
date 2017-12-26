package com.example.popie.uoleventsdiary_user;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.EditText;

import com.example.popie.uoleventsdiary_user.Model.Event;
import com.google.gson.Gson;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewAllDetailActivity extends AppCompatActivity {

    EditText etName, etDateTime, etVenue, etOrganizer, etPhone;
    CircleImageView circleImageView;
    Gson gson;
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_detail);

        etName = findViewById(R.id.etName);
        etDateTime = findViewById(R.id.etDateTime);
        etVenue = findViewById(R.id.etVenue);
        etOrganizer = findViewById(R.id.etOrganizer);
        etPhone = findViewById(R.id.etPhone);
        circleImageView = findViewById(R.id.imageViewEvent);

        Intent intent = getIntent();
        String eventData = intent.getStringExtra("eventData");
        gson = new Gson();
        event = gson.fromJson(eventData, Event.class);

        etName.setText(event.getName());
        etDateTime.setText(event.getDateTime());
        etVenue.setText(event.getVenue());
        etOrganizer.setText(event.getOName());
        etPhone.setText(event.getPhone());

        byte[] encodeByte = Base64.decode(event.getImage(), Base64.DEFAULT);
        Bitmap bm = BitmapFactory.decodeByteArray(encodeByte, 0,
                encodeByte.length);

        circleImageView.setImageBitmap(bm);


    }
}
