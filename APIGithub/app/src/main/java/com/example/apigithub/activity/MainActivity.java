package com.example.apigithub.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import com.example.apigithub.R;
import com.example.apigithub.adapter.RepoAdapter;
import com.example.apigithub.api.APIClient;
import com.example.apigithub.api.APIInterface;
import com.example.apigithub.model.ItemModel;
import com.example.apigithub.model.RepositoryModel;
import com.example.apigithub.model.ResponseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Response";
    int[] strings={Color.parseColor("#9c27b0"),Color.parseColor("#303f9f"),Color.parseColor("#388e3c"),Color.parseColor("#ff5722"),Color.parseColor("#009688"), Color.parseColor("#FF4081"),Color.parseColor("#f44336"),Color.parseColor("#2196f3"),Color.parseColor("#dd2c00"),Color.parseColor("#424242"),Color.parseColor("#37474f")};
    GridView gridView;
    RepoAdapter repoAdapter;
    List<ItemModel> mItems=new ArrayList<ItemModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView)findViewById(R.id.gridview);
        repoAdapter=new RepoAdapter(getApplicationContext(),mItems);
        CallApi();
    }
    public void CallApi(){
        APIInterface apiService = APIClient.getRetrofit().create(APIInterface.class);
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", "php");
        Call<ResponseModel> call = apiService.getRepos(params);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel ResponseObj=response.body();
                List<RepositoryModel> list=ResponseObj.getItems();
                List<ItemModel> mlist=new ArrayList<ItemModel>();
                for(int i=0;i<2;i++){
                    mItems.add(new ItemModel(list.get(i).getName(), list.get(i).getShort_description()));
                }
                repoAdapter=new RepoAdapter(getApplicationContext(),mItems);
                gridView.setAdapter(repoAdapter);
                repoAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}

