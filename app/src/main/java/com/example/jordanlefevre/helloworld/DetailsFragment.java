package com.example.jordanlefevre.helloworld;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by jordanlefevre on 15/03/2017.
 */

public class DetailsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.details_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();

        TextView title = (TextView) getActivity().findViewById(R.id.title);
        title.setText(Html.fromHtml(bundle.getString("title")));

        TextView description = (TextView) getActivity().findViewById(R.id.description);
        description.setText(Html.fromHtml(bundle.getString("description")));

        TextView author = (TextView) getActivity().findViewById(R.id.author);
        author.setText(bundle.getString("author") + ", ");

        TextView publicationDate = (TextView) getActivity().findViewById(R.id.publicationDate);
        publicationDate.setText(bundle.getString("publicationDate"));

        TextView nbComments = (TextView) getActivity().findViewById(R.id.nbComments);
        nbComments.setText("(" + String.valueOf(bundle.getInt("nbComments")) + " commentaires)");

        ImageView image = (ImageView) getActivity().findViewById(R.id.photo);
        Picasso.with(image.getContext())
                .load(bundle.getString("urlImage"))
                .error(R.drawable.no_image)
                .into(image);
    }
}
