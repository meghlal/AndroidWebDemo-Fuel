package com.krbmsoft.webappdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import fuel.Fuel;
import fuel.core.FuelError;
import fuel.core.Handler;
import fuel.core.Request;
import fuel.core.Response;

public class Get extends AppCompatActivity {


    private static final String TAG = "Main";
    TextView resultText;
    private Map<String, String> params = new HashMap<String, String>() {{
        put("q", "Ami");
        put("platform", "Android");
    }};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        resultText = (TextView) findViewById(R.id.main_result_text);

        Fuel.get("http://192.168.0.3/android/get", params).responseString(new Handler<String>() {
            @Override
            public void failure(Request request, Response response, FuelError error) {
                //do something when it is failure
            }

            @Override
            public void success(Request request, Response response, String data) {
                updateUI(null, data);
            }
        });
    }

    private void updateUI(FuelError error, String result) {
        if (error == null) {
            resultText.setText(resultText.getText() + result);
        } else {
            Toast t= Toast.makeText(this.getApplicationContext(),"Error",Toast.LENGTH_SHORT);
            t.show();
        }
    }
}
