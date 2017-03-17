package com.example.jordanlefevre.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jordanlefevre on 14/03/2017.
 */

public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detailsFragment, new DetailsFragment())
                .commitAllowingStateLoss();
    }

    public void backToList(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
