package com.krbmsoft.webappdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

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
    TextView name;

    EditText n1;
    EditText n2;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        resultText = (TextView) findViewById(R.id.main_result_text);
        name = (TextView) findViewById(R.id.name);

        btn=(Button)findViewById(R.id.getdata);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n1 = (EditText) findViewById(R.id.p1);
                n2 = (EditText) findViewById(R.id.p2);

                Map<String, String> params = new HashMap<String, String>() {{
                    put("num1", n1.getText().toString());
                    put("num2", n2.getText().toString());
                }};

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
        });


    }

    private void updateUI(FuelError error, String result) {
        if (error == null) {

            try {
                JSONObject jsonObj = new JSONObject(result);
                String name= jsonObj.getString("name");
                String tol= jsonObj.getString("tol");

                this.name.setText(name);
                resultText.setText(tol);

            } catch (JSONException e) {
                e.printStackTrace();
            }



        } else {
            Toast t= Toast.makeText(this.getApplicationContext(),"Error",Toast.LENGTH_SHORT);
            t.show();
        }
    }
}
