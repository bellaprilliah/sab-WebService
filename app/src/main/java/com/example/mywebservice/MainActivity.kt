package com.example.mywebservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listMahasiswa;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listMahasiswa = (RecyclerView) findViewById(R.id.ListMahasiswa);
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        listMahasiswa.setLayoutManager(linearLayoutManager);
        new GetMahasiswaAsyncTask().execute();
    }

    private class GetMahasiswaAsyncTask extends AsyncTask<String, String, String>{
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading ... ");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params){
        String response = "";
        try{
            String url =
            "https://satriabagusi.000webhostapp.com/getdatajson.php";
            response = CustomHttpClient.executeHttpGet(url);
        }catch (Exception e){
            response = e.toString();
        }
        return response;
    }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            progressDialog.dismiss();
            try {
                Log.e("masuk", "Respon result -> "+result);
                JSONObject object = new JSONObject(result);
                ArrayList<HashMap<String, String>> arr = new ArrayList<>();
                if(object.getString("success").equalsIgnoreCase("1")){
                    JSONArray array = object.getJSONArray("data");
                    HashMap<String, String> map;
                    for (int i = 0; i < array.length(); i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        map = new HashMap<String, String>();
                        map.put("nrp", jsonObject.getString("nrp"));
                        map.put("nama", jsonObject.getString("nama"));
                        map.put("prodi", jsonObject.getString("prodi"));
                        arr.add(map);
                    }
                }
                listMahasiswa.setAdapter(new MahasiswaAdapter(arr, MainActivity.this));
            }catch (Exception e){
                Log.e("masuk", "-> " + e.getMessage());
            }
        }

    }