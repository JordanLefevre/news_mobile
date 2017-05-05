package com.example.jordanlefevre.helloworld;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by jordanlefevre on 14/03/2017.
 */

public class MyAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final News[] news;
    private final OnListItemClickListener listener;

    public MyAdapter(News[] news, OnListItemClickListener listener) {
        this.news = news;
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).bindValue(news[position]);
    }

    @Override
    public int getItemCount() {
        return news.length;
    }

    public RecyclerView.ViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater =
                LayoutInflater.from(parent.getContext());
        return new MyViewHolder(inflater.inflate(
                R.layout.item, parent, false
        ), this.listener);
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private TextView description;
        private TextView author;
        private TextView publicationDate;
        private ImageView image;
        private TextView nbComments;
        private final OnListItemClickListener listener;

        MyViewHolder(View itemView, OnListItemClickListener l) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            author = (TextView) itemView.findViewById(R.id.author);
            publicationDate = (TextView) itemView.findViewById(R.id.publicationDate);
            image = (ImageView) itemView.findViewById(R.id.photo);
            nbComments = (TextView) itemView.findViewById(R.id.nbComments);

            listener = l;
        }

        void bindValue(News news) {
            title.setText(Html.fromHtml(news.titre));
            description.setText(Html.fromHtml(news.description));
            author.setText(news.auteur.name + ", ");
            publicationDate.setText(news.publicationDate);
            nbComments.setText(String.valueOf(news.nb_comments));

            if(news.attachments.length > 0) {
                Picasso.with(image.getContext())
                        .load(news.attachments[0].url)
                        .error(R.drawable.no_image)
                        .into(image);
            }
        }

        public void onClick(View v) {
            listener.onItemClicked(getAdapterPosition());
        }
    }
}