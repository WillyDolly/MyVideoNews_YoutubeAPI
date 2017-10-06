package com.popland.pop.myvideonews_youtubeapi;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
String VideoList_APIkey = "AIzaSyCUtV5--xlQ3pTVooZRts7_ZNvmrOzTWtk";
    String VideoListId;
    JSONObject parent, child, snippet,thumbnails,anhDefault, resourceId;
    String title, publishedAt, url, videoId;
    ArrayList<Video> arrlVideo;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i = getIntent();
        VideoListId = i.getStringExtra("videoList");
        listView = (ListView)findViewById(R.id.listView);
        arrlVideo = new ArrayList<Video>();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
                intent.putExtra("videoId",arrlVideo.get(position).videoId);
                startActivity(intent);
                return false;
            }
        });
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new JsonParser().execute("https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=" + VideoListId + "&maxResults=50&key=" + VideoList_APIkey);
            }
        });

    }

    class JsonParser extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... params) {
            return docNoiDungTuURL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject parent = new JSONObject(s);
                JSONArray items = parent.getJSONArray("items");
                for(int i=0;i<items.length();i++){
                     child = items.getJSONObject(i);
                     snippet = child.getJSONObject("snippet");
                     title = snippet.getString("title");
                     publishedAt = snippet.getString("publishedAt");

                     thumbnails = snippet.getJSONObject("thumbnails");
                     anhDefault = thumbnails.getJSONObject("default");
                     url = anhDefault.getString("url");
                     resourceId = snippet.getJSONObject("resourceId");
                     videoId = resourceId.getString("videoId");
                    arrlVideo.add(new Video(title,publishedAt,url,videoId));
                }
                CustomBaseAdapter adapter = new CustomBaseAdapter(Main2Activity.this,R.layout.custom_videolist,arrlVideo);
                listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public String docNoiDungTuURL(String url){
        String urlTojson ="";
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            urlTojson = EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlTojson;
    }
}
