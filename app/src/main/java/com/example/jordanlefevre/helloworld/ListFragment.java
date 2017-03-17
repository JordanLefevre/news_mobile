package com.example.jordanlefevre.helloworld;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by jordanlefevre on 15/03/2017.
 */

public class ListFragment extends Fragment {

    private News[] newsList;
    private RecyclerView rView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        rView = (RecyclerView) view.findViewById(R.id.rView);
        rView.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.nb_columns)));

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SQLiteDatabase db = DatabaseHelper.getInstance(getContext()).getWritableDatabase();





        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.goglasses.fr/")
                .addConverterFactory(
                        JacksonConverterFactory.create())
                .build();

        NewsAPI api = retrofit.create(NewsAPI.class);

        Call<ResponseNews> call;

        if(getArguments().getInt("category") != 0) {
            call = api.getNewsByCategory(getArguments().getInt("category"));
        }
        else {
            call = api.getRecentNews();
        }

        call.enqueue(new Callback<ResponseNews>() {
            public void onResponse(Call<ResponseNews> call, Response<ResponseNews> response) {
                ResponseNews responseNews = response.body();

                newsList = responseNews.posts;
                rView.setAdapter(new MyAdapter(newsList, new OnListItemClickListener() {
                    @Override
                    public void onItemClicked(int position) {
                        News news = newsList[position];

                        Bundle bundle = new Bundle();
                        bundle.putString("title", news.titre);
                        bundle.putString("description", news.description);
                        bundle.putString("author", news.auteur.name);
                        bundle.putString("publicationDate", news.publicationDate);
                        bundle.putInt("nbComments", news.nb_comments);
                        bundle.putBoolean("important", news.important);
                        bundle.putString("urlImage", news.attachments[0].url);

                        Intent intent = new Intent(getActivity(), DetailsActivity.class);
                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                }));
            }

            public void onFailure(Call<ResponseNews> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public static ListFragment newInstance(int category_id) {
        ListFragment fragment = new ListFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("category", category_id);

        fragment.setArguments(bundle);
        return fragment;
    }
}
