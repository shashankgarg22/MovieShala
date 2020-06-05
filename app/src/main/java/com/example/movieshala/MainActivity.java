package com.example.movieshala;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.movieshala.Adapters.MovieAdapter;
import com.example.movieshala.Utility.MovieUtils;
import com.example.movieshala.objects.MovieDetail;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private int flag=1;
    MovieAdapter recyclerViewAdapter;
    ArrayList<MovieDetail> movieDetailsArrayList;
    RecyclerView recyclerView;
    IamAsyncTask asyncTask;
    IamAsyncTask asyncTask2;
    ProgressBar progressbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieDetailsArrayList= new ArrayList<>();
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        //Initializing adapter with empty arrayList
        recyclerViewAdapter=new MovieAdapter(this,movieDetailsArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);

        asyncTask=new IamAsyncTask();
        asyncTask.execute("https://api.themoviedb.org/3/movie/popular?api_key=daa28366515d4284fea776b7e1ea6050");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();

        if(itemThatWasClickedId==R.id.popular){
            if(flag!=1) {
                asyncTask = new IamAsyncTask();
                asyncTask.execute("https://api.themoviedb.org/3/movie/popular?api_key=daa28366515d4284fea776b7e1ea6050");
                flag=1;
            }
        }
        if(itemThatWasClickedId==R.id.top_rated){
            if(flag!=2) {
                asyncTask2 = new IamAsyncTask();
                asyncTask2.execute("https://api.themoviedb.org/3/movie/top_rated?api_key=daa28366515d4284fea776b7e1ea6050");
                flag=2;
            }
        }
        return true;
    }

    @SuppressLint("StaticFieldLeak")
    private class IamAsyncTask extends AsyncTask<String,Void,List<MovieDetail>>{

        @Override
        protected void onPreExecute() {
            progressbar=findViewById(R.id.progress_bar);
            if(progressbar.getVisibility()== View.GONE){
                progressbar.setVisibility(View.VISIBLE);
            }

        }

        @Override
        protected List<MovieDetail> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            return MovieUtils.fetchMovieDetails(urls[0]);
        }

        @Override
        protected void onPostExecute(List<MovieDetail> movieDetailsList) {
            super.onPostExecute(movieDetailsList);
            progressbar.setVisibility(View.GONE);


            recyclerViewAdapter=new MovieAdapter(MainActivity.this,movieDetailsList);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
            recyclerView.setAdapter(recyclerViewAdapter);



        }
    }
}
