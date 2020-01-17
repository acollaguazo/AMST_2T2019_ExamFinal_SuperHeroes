package com.amst.g1.superheores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class ResultsActivity extends AppCompatActivity {

    private String superHeroName;
    private Context mContext = this;
    public static String SUPER_NAME = "SUPER_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        superHeroName = getIntent().getStringExtra(SUPER_NAME);
        searchHeroe();
    }

    private void searchHeroe() {
        String url = String.format("%s%s/search/%s", Api.BASE_URL, Api.API_TOKEN, superHeroName);
        JsonArrayRequest heroesRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    Log.v("RESPONSE", response.toString());
                    for (int i=0; i<response.length(); i++) {
//                        JSONObject hero = JSONObject() response.get(i);
                        Log.v("RESPONSE", response.get(i).toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("VolleyError", error.toString());
            }
        });
        Volley.newRequestQueue(mContext).add(heroesRequest);
    }


}
