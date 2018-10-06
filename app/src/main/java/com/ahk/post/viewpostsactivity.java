package com.ahk.post;

import android.app.VoiceInteractor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class viewpostsactivity extends AppCompatActivity {
    String jsonObjectPosts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpostsactivity);
        new AsyncTask<Void, Void, List<Post>>() {

            @Override
            protected void onPreExecute() {

            }

            @Override
            protected List<Post> doInBackground(Void... voids) {
                String url = "http://23.96.56.156/viewposts.php";
                Response.Listener<String> resLis = new Response.Listener<String>() {
                    public List<Post> posts;

                    @Override
                    public void onResponse(String response) {
                        try {
                            posts = new ArrayList<>();
                            String encodedstring = URLEncoder.encode(response, "ISO-8859-1");
                            response = URLDecoder.decode(encodedstring, "UTF-8");
                            jsonObjectPosts = response;
                            JSONObject object = new JSONObject(response);
                            JSONArray postsArray = object.getJSONArray("posts");
                            for (int i = 0; i < postsArray.length(); i++) {
                                JSONObject currentObject = postsArray.getJSONObject(i);
                                String id = currentObject.getString("id");
                                String writer = currentObject.getString("writer");
                                String postContent = currentObject.getString("postContent");
                                Post post = new Post(id, writer, postContent);
                                posts.add(post);

                            }

                        } catch (JSONException je) {
                            je.printStackTrace();
                        } catch (UnsupportedEncodingException ue) {
                            ue.printStackTrace();
                        }
                    }
                };

                StringRequest postRequest = new StringRequest(Request.Method.GET, url, resLis, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(viewpostsactivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                Volley.newRequestQueue(viewpostsactivity.this).add(postRequest);


resLis.p
                    return ;

            }

                @Override
                protected void onPostExecute (List < Post > posts) {
                    super.onPostExecute(posts);
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
                    recyclerView.setLayoutManager(new LinearLayoutManager(viewpostsactivity.this));
                    PostAdapter postAdapter = new PostAdapter(posts);
                    recyclerView.setAdapter(postAdapter);
                }
            }.execute();
        }}

