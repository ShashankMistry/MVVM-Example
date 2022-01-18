package com.shashank.mvvm.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shashank.mvvm.Modals.actors;
import com.shashank.mvvm.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ActorViewHolder> {

    private Context context;
    private List<actors> actorsList;

    public ActorAdapter(Context context, List<actors> actorsList) {
        this.context = context;
        this.actorsList = actorsList;
    }

    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ActorViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.each_tile, parent, false));
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder holder, int position) {
        actors actors= actorsList.get(position);
        holder.id.setText("ID: "+ actors.getId());
        holder.title.setText("TITLE: "+ actors.getTitle());
//        Glide.with(holder.url.getContext()).load(actors.getThumbnailUrl()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground).dontAnimate().into(holder.url);
        Picasso.get().load(actors.getUrl()).placeholder(R.drawable.ic_launcher_background).into(holder.url);

    }

    @Override
    public int getItemCount() {
        return actorsList.size();
    }

    public void getAllActors(List<actors> actorsList){
        this.actorsList = actorsList;

    }

    public static class ActorViewHolder extends RecyclerView.ViewHolder{
        public TextView id, title;
        public ImageView url;
        public ActorViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);
            url = itemView.findViewById(R.id.url);
        }
    }


}
