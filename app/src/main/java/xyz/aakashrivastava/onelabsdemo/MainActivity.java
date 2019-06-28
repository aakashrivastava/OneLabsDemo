package xyz.aakashrivastava.onelabsdemo;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    String url = "https://pixabay.com/api/?key=12895042-e76001b68419f58758546f446&q=dog&image_type=photo&pretty=true&per_page=30";
    ImageButton searchButton;
    private ArrayList<ImageModel> glist;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    FloatingActionButton floatingActionButton;
    GridLayoutManager gridLayoutManager;
    ProgressBar progressBar;
    EditText searchBox;
    GridChangerFragment gridChangerFragment;

    int scrollPosition = 0;
    int page = 1;
    int gridspan = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        glist = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        searchBox = findViewById(R.id.search_box);
        searchButton = findViewById(R.id.search_button);
        //floatingActionButton = findViewById(R.id.fab);
        gridChangerFragment = new GridChangerFragment();

        recyclerViewAdapter= new RecyclerViewAdapter(getApplicationContext(), glist);

        gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        //gridLayoutManager.setSpanSizeLookup(onSpanSizeLookup);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    page++;
                    if(isNetworkConnected())
                        fetchImages(searchBox.getText().toString(),page);

                }
            }
        });

        if(isNetworkConnected())
            fetchImages("",1);
        else{

        }

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setClickListener(this);

        /*floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gridChangerFragment.show(getSupportFragmentManager(), "ONELABS");
                gridspan = gridChangerFragment.returnGridSpan();
                gridLayoutManager.setSpanCount(gridspan);
                recyclerView.setLayoutManager(gridLayoutManager);
            }
        });*/


        /*searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String url1 = null;
                try {
                    url1 = "https://pixabay.com/api/?key=12895042-e76001b68419f58758546f446&q=" + URLEncoder.encode(searchBox.getText().toString(), "utf-8") + "&image_type=photo&pretty=true";
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url1, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                JSONArray imgxyz = null;
                                try {
                                    imgxyz = response.getJSONArray("hits");
                                    for(int i=0; i<imgxyz.length(); i++) {
                                        JSONObject object = imgxyz.getJSONObject(i);
                                        ImageModel model = new ImageModel();
                                        model.setId(object.getInt("id"));
                                        model.setUrl(object.getString("previewURL"));
                                        model.setType(object.getString("type"));
                                        glist.add(model);
                                    }
                                    recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), glist);
                                    Log.d("asdf", "onResponse: ");
                                    progressBar.setVisibility(View.INVISIBLE);
                                   // Toast.makeText(MainActivity.this, imgxyz.length(), Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "No JSON GG!", Toast.LENGTH_LONG).show();
                                error.printStackTrace();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        });
                VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });*/
    }

    @Override
    public void onClick(View view, int position) {
        Pair[] pair = new Pair[1];
        pair[0] = new Pair<View, String>(view, "hello");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pair);
        final ImageModel model = glist.get(position);
        Intent intent = new Intent(this, FullScreenActivity.class);
        intent.putExtra("eeee",position);
        intent.putExtra("uuuu", model.getUrl());
        intent.putParcelableArrayListExtra("qqqq", glist);
        Log.d("GG", "onClick: "+ glist.size());
        startActivity(intent, options.toBundle());
    }


    public void fetchImages(final String query,int page) {
        progressBar.setVisibility(View.VISIBLE);
        String url1 = null;
        try {
            url1 = "https://pixabay.com/api/?key=12895042-e76001b68419f58758546f446&q=" + URLEncoder.encode(searchBox.getText().toString(), "utf-8") + "&image_type=photo&pretty=true";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url1, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray imgxyz = null;
                        try {
                            imgxyz = response.getJSONArray("hits");
                            for(int i=0; i<imgxyz.length(); i++) {
                                JSONObject object = imgxyz.getJSONObject(i);
                                ImageModel model = new ImageModel();
                                model.setId(object.getInt("id"));
                                model.setUrl(object.getString("previewURL"));
                                model.setType(object.getString("type"));
                                glist.add(model);
                            }
                            recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), glist);
                            Log.d("asdf", "onResponse: ");
                            progressBar.setVisibility(View.INVISIBLE);
                            // Toast.makeText(MainActivity.this, imgxyz.length(), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "No JSON GG!", Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
        recyclerViewAdapter.notifyDataSetChanged();
        page++;
    }

    public void two(View v){
        setview(2);
    }
    public void three(View v){
        setview(3);
    }
    public void four(View v){
        setview(4);
    }

    public void setview(int num){
        if (recyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,num));
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, glist);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.scrollToPosition(scrollPosition);
        recyclerViewAdapter.setClickListener(this);
        recyclerViewAdapter.notifyDataSetChanged();

    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
