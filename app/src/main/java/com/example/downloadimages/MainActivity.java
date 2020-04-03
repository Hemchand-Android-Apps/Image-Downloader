package com.example.downloadimages;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    public void downloadImage(View view) {
        ImageDownloader imageDownloader = new ImageDownloader();
        Bitmap myBitmap = null;
        try {
            myBitmap = imageDownloader.execute("https://lh3.googleusercontent.com/2Sz6YvP7YpFM6pcRvQFGbLcDiZFeIql6Uc-PAwrHay5eYoPETE-843Iwx_KecRfWfUsvoxTHk7aCRUeaYlIT3SxemAAOTkJzxHClnOxFPiPSjxXxVY5-FY3zirlzIKK4gWoconC7uP-o1HjmCalzrgV54kVomLx9XQ3J_CpuGwQvBLhAFGXzIbgcjxbwRY2gLrkbLCgFLEZZehY5WrhX1nAsqZgOhs0M9x4lCF-5FiZ6wYqaJ8SLnoOo8_gd7ma1sH_j04qtFFVmHgcWYtzVqBuLk5sFucHewpCXIhtL1ENHLaRc90cwtcUa2l0jD40_rE8WmL3-G0iw6KeQgqqU4yVLftSGkJ49097eA51JUzJxcH3LxHerjulIeN63bydpTPe1zzlNdzbEthmZ3aM6CLJ-CwoZOaUARziX3Vz9cvr_seL74kzwJwEjcvgxO1L02RYltwVoKNdRw_bdzW1ptG-qkNE05jnHnbrnfngPCa8hCTP2JmioHcfwXfY-5hQKOBPHRM6pS_KDqnkDoHLCV8SjpoIP7nzwpQJWikJe1PZGieESb10c6UVNvHpN3WeZZ7OHaM_6MpYHHAoL7XKtN4mqxTMXurbnYfKp1Jbkugteod_6ehYdDvHBy9e2POyclLC-auuW6e-qIDYy36EakJurVkJ8D5JTYiP_gJTHWsbcAmhEgYZMtYOeNeYlaq0=w1186-h1580-no").get();
            imageView.setImageBitmap(myBitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... url) {

            try {
                URL myUrl = new URL(url[0]);
                HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
    }
}
