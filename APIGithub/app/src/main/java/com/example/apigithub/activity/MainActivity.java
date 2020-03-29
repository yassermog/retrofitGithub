package com.example.apigithub.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    GridView gridView;
    RepoAdapter repoAdapter;
    List<ItemModel> mItems;
    SearchView TopicsSearchView;
    Spinner dropdown;
    String langaugeFilter;
    String[] languages = new String[]{"Any","JavaScript", "Java", "Python","C#","Kotlin","C++","Ruby"};
    ProgressBar progressBar;
    TextView noResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=(ProgressBar)findViewById(R.id.progressBar_cyclic);
        dropdown = (Spinner) findViewById(R.id.languagesDropDown);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        langaugeFilter="Any";

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            int checks=0;
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                if(++checks > 1) { // to avoid this call during initializations
                    langaugeFilter = languages[pos];
                    gridView.setAdapter(null);
                    progressBar.setVisibility(View.INVISIBLE);
                    CallApi();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        gridView = (GridView) findViewById(R.id.gridview);
        noResults=(TextView) findViewById(R.id.noResults);
        repoAdapter=new RepoAdapter(getApplicationContext(),mItems);
        TopicsSearchView=(SearchView) findViewById(R.id.topicsSearchView);
        TopicsSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            int checks=0;
            @Override
            public boolean onQueryTextSubmit(String query) {
                mItems=new ArrayList<ItemModel>();
                gridView.setAdapter(null);
                List<ItemModel> mlist=new ArrayList<ItemModel>();
                CallApi();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(++checks > 1) { // to avoid this call during initializations
                    mItems = new ArrayList<ItemModel>();
                    gridView.setAdapter(null);
                    progressBar.setVisibility(View.INVISIBLE);
                    //CallApi(); to avoid calling to many times
                }
                return false;
            }
        });

    }

    public void CallApi(){
        APIInterface apiService = APIClient.getRetrofit().create(APIInterface.class);
        Map<String, String> params = new HashMap<String, String>();
        String query = TopicsSearchView.getQuery().toString();
        String q="topic:"+query;

        if(langaugeFilter !="Any")
            q=query+"+language:"+langaugeFilter;

        params.put("q", q);
        //params.put("language", langaugeFilter);
        if(query.length() < 3 ){
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        Call<ResponseModel> call = apiService.getRepos(params);
        call.enqueue(new Callback<ResponseModel>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                gridView.setAdapter(null);
                ResponseModel ResponseObj=response.body();
                if ( ResponseObj == null ) {
                    return;
                }
                List<RepositoryModel> list=ResponseObj.getItems();
                List<ItemModel> mlist=new ArrayList<ItemModel>();
                int totalcount=ResponseObj.getTotalCount();
                int count=30;
                progressBar.setVisibility(View.INVISIBLE);
                Context context = getApplicationContext();
                CharSequence text = totalcount+ " Items was Found !";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                if(totalcount==0)
                {
                    noResults.setVisibility(View.VISIBLE);
                }
                else {
                    if (totalcount < count) {
                        count = totalcount;
                    }
                    noResults.setVisibility(View.INVISIBLE);
                    for (int i = 0; i < count; i++) {
                        String topics = String.join(",", list.get(i).getTopics());
                        String title = list.get(i).getFullName();
                        String desc = list.get(i).getDescription();
                        String htmlUrl = list.get(i).getHtmlUrl();
                        mItems.add(new ItemModel(title, desc, topics, htmlUrl));
                    }
                    repoAdapter = new RepoAdapter(getApplicationContext(), mItems);
                    gridView.setAdapter(repoAdapter);
                    repoAdapter.notifyDataSetChanged();

                }
            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}

