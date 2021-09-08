package com.firstapp.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firstapp.myapplication.api.ApiCallsMethod;
import com.firstapp.myapplication.api.request.MyApiRequest;
import com.firstapp.myapplication.api.response.Response1;
import com.firstapp.myapplication.api.response.ResponseData;
import com.firstapp.myapplication.utils.MyUrls;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    Button ClickOn;
    EditText mail;
    EditText pass;
    ApiCallsMethod a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mail = findViewById(R.id.mail);
        pass = findViewById(R.id.pass);


        Retrofit r = new Retrofit.Builder()
                .baseUrl(MyUrls.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        a = r.create(ApiCallsMethod.class);




    }

    public void ClickOn(View V){

       String Mailid = mail.getText().toString();
       String Password = pass.getText().toString();


        MyApiRequest r1 = new MyApiRequest();
        r1.setEmail(Mailid);
        r1.setPassword(Password);


        Call<Response1> res = a.login(r1);

        res.enqueue(new Callback<Response1>() {
            @Override
            public void onResponse(Call<Response1> call, Response<Response1> response) {

                if (response.code() == 200){
                    Toast.makeText(MainActivity.this, "Api call succ", Toast.LENGTH_SHORT).show();
                }else if (response.code() == 401){
                    Toast.makeText(MainActivity.this, "Invalid token", Toast.LENGTH_SHORT).show();
                }else if (response.code() == 500){
                    Toast.makeText(MainActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Login Api", Toast.LENGTH_SHORT).show();
                }

//                Log.d("TAG", "onResponse: " + response.body().getData().getEmail());
            }

            @Override
            public void onFailure(Call<Response1> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed Api", Toast.LENGTH_SHORT).show();
            }
        });


//        Toast.makeText(this, "massage", Toast.LENGTH_SHORT).show();
    }

}