package com.example.jordanlefevre.helloworld;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class MainActivity extends AppCompatActivity {
    public Category[] categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.goglasses.fr/")
                .addConverterFactory(
                        JacksonConverterFactory.create())
                .build();

        CategoriesAPI api = retrofit.create(CategoriesAPI.class);

        Call<ResponseCategories> call = api.getCategories();

        call.enqueue(new Callback<ResponseCategories>() {
            public void onResponse(Call<ResponseCategories> call, Response<ResponseCategories> response) {
                ResponseCategories responseCategories = response.body();

                categories = responseCategories.categories;

                ViewPager viewPager = (ViewPager) findViewById(R.id.listFragment);
                viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), categories));

                TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
                tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                tabLayout.setupWithViewPager(viewPager);
            }

            public void onFailure(Call<ResponseCategories> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.preferences:
                Intent intent = new Intent(this, Preferences.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
