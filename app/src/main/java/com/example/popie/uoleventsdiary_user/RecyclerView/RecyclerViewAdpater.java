package com.example.popie.uoleventsdiary_user.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.popie.uoleventsdiary_user.Model.Event;
import com.example.popie.uoleventsdiary_user.R;
import com.example.popie.uoleventsdiary_user.ViewAllDetailActivity;
import com.google.gson.Gson;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by popie on 12/26/2017.
 */

public class RecyclerViewAdpater extends RecyclerView.Adapter<RecyclerViewAdpater.EventViewHolder> {

    Context context;
    List<Event> eventList;

    public RecyclerViewAdpater(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, null);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdpater.EventViewHolder holder, int position) {
        final Event event = eventList.get(position);

        byte[] encodeByte = Base64.decode(event.getImage(), Base64.DEFAULT);
        Bitmap bm = BitmapFactory.decodeByteArray(encodeByte, 0,
                encodeByte.length);

//        Picasso.with(context)
//                .load()
//                .into(holder.circleImageView);

        holder.circleImageView.setImageBitmap(bm);

        holder.tvName.setText(event.getName());


        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "Id: " + event.getId(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ViewAllDetailActivity.class);
                Gson gson = new Gson();
                String eventData = gson.toJson(event);
                intent.putExtra("eventData", eventData);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        CircleImageView circleImageView;
        View v;

        public EventViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.eventName);
            circleImageView = itemView.findViewById(R.id.circleImageView);
            v = itemView.findViewById(R.id.cardView);
        }
    }
}
