package com.amst.g1.superheores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {

    public static final String SUPER_ID = "HeroId";
    private TextView tvHeroName, tvRealName;
    private int heroId;
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        heroId = getIntent().getIntExtra(SUPER_ID, 0);
        tvHeroName = findViewById(R.id.tvHeroName);
        tvRealName = findViewById(R.id.tvRealName);
        getHeroDetails();
    }

    private void getHeroDetails() {
        if (heroId != 0) {
            String url = String.format("%s%s/%d", Api.BASE_URL, Api.API_TOKEN, heroId);
           JsonObjectRequest objectRequest = new JsonObjectRequest(url, null,
                   new Response.Listener<JSONObject>() {
               @Override
               public void onResponse(JSONObject response) {
                   try {
                       Log.v("RESPONSE", response.toString());

                       tvHeroName.setText(response.getString("name"));
                       tvRealName.setText(response.getJSONObject("biography")
                               .getString("full-name"));
                   } catch (Exception e) {

                   }
               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                   Log.v("VolleyError", error.toString());
               }
           });
            Volley.newRequestQueue(mContext).add(objectRequest);
        }
    }
}
