package com.ahk.post;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class newpostactivity extends AppCompatActivity {
EditText edtWriterName,edtPostCintent;
Button buPost;
    String connectionparameter;
    byte parameterbytes[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpostactivity);
        edtWriterName =(EditText)findViewById(R.id.edtWriterName);
        edtPostCintent=(EditText)findViewById(R.id.edtPostContent);
        buPost=(Button)findViewById(R.id.buPost);
        buPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Void,Void,String>(){
                    @Override
                    protected void onPreExecute() {
                        String writerName=edtWriterName.getText().toString();
                        String postContent=edtPostCintent.getText().toString();

                        String writerNameKey="writer=";
                        String postContentKey="&postcontent=";
                        try {
                            connectionparameter = writerNameKey + URLEncoder.encode(writerName, "UTF-8") +postContentKey+URLEncoder.encode(postContent,"UTF-8");
                            parameterbytes=connectionparameter.getBytes("UTF-8");
                        }
                        catch (IOException e){e.printStackTrace(); }

                    }

                    @Override
                    protected String doInBackground(Void... voids) {
                        String url="http://23.96.56.156/insertposts.php";
                        try {
                            URL insertPostURL = new URL(url);
                            HttpURLConnection connection = (HttpURLConnection) insertPostURL.openConnection();
                            connection.setRequestMethod("POST");
                            connection.getOutputStream().write(parameterbytes);
                            InputStreamReader inputStream=new InputStreamReader( connection.getInputStream());
                            BufferedReader reader=new BufferedReader(inputStream);
                            return reader.readLine();

                        }catch(IOException e){e.printStackTrace(); return "error";}

                    }
                    @Override
                    protected void onPostExecute(String s) {
                        Toast.makeText(newpostactivity.this, "Successe", Toast.LENGTH_SHORT).show();
                        edtPostCintent.setText("");
                    }


                }.execute();
            }
        });
    }
}
