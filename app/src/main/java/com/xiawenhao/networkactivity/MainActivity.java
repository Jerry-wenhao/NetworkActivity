package com.xiawenhao.networkactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Network;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.button)
    Button buttonGetMessage;
    @BindView(R.id.count_button)
    Button CountNumber;

    String url = "https://twc-android-bootcamp.github.io/fake-data/data/default.json";

    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        CountNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
                int countNumber = sharedPref.getInt("count", 0);
                Toast.makeText(getApplicationContext(), Integer.toString(countNumber), Toast.LENGTH_SHORT).show();
            }
        });


        buttonGetMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient okHttpClient = new OkHttpClient();
                final Request request = new Request.Builder().url(url).build();
                Call call = okHttpClient.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Throw Error", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        final String message = Objects.requireNonNull(response.body()).string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dataHandler(message);
                            }
                        });
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        int count = sharedPref.getInt("count", 0);
        sharedPref.edit().putInt("count", ++count).apply();
    }


    private void dataHandler(String message) {
        Wrapper wrapper = gson.fromJson(message, Wrapper.class);
        if (wrapper.getData().size() > 0) {
            Toast.makeText(MainActivity.this, wrapper.getData().get(0).getName(), Toast.LENGTH_SHORT).show();
        }
    }
}

