package com.example.app8_callwebservice;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(t -> {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    Proxy proxyTest = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("proxy.fu.edu.vn", 8080));
//                    Request request = new Request.Builder()
//                            .url("https://5d8e18ec7162f10014a48ead.mockapi.io/api/fake/1")
//                            .build();
//
//                    OkHttpClient.Builder builder = new OkHttpClient.Builder().proxy(proxyTest);
//                    OkHttpClient client = builder.build();
//
//                    try (Response response = client.newCall(request).execute()) {
//                        String res = response.body().string();
//                        System.out.println(res);
//                        textView.setText(res);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

                    try {
                        URL url = new URL("http://tuyendung.misa.com.vn/wp-json/wp/v2/posts");

                        InputStream is = url.openConnection(proxyTest).getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        String line = null;
                        StringBuilder sb = new StringBuilder();
                        while ((line = br.readLine()) != null) {
                            sb.append(line);
                        }
                        textView.setText(sb.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };
            thread.start();
        });

    }

    private class Task extends AsyncTask<String, String, String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }
}
