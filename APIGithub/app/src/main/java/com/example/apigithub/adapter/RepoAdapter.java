package com.example.apigithub.adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.apigithub.R;
import com.example.apigithub.model.ItemModel;
import java.util.ArrayList;
import java.util.List;

public class RepoAdapter extends BaseAdapter {
    private List<ItemModel> items= new ArrayList<ItemModel>();
    private LayoutInflater layoutInflater;

    public RepoAdapter(Context context, List<ItemModel> items) {
        this.items = items;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view1=convertView;
        TextView repoBody;
        TextView repoTitle;

        if(view1==null){
            view1=layoutInflater.inflate(R.layout.grid_item,parent,false);
            view1.setTag(R.id.RepoTitleTxt,view1.findViewById(R.id.RepoTitleTxt));
            view1.setTag(R.id.RepoBodyTxt,view1.findViewById(R.id.RepoBodyTxt));
        }
        repoTitle=(TextView) view1.getTag(R.id.RepoTitleTxt);
        repoBody=(TextView) view1.getTag(R.id.RepoBodyTxt);

        ItemModel itemModel=items.get(position);
        repoTitle.setText(itemModel.title);
        repoBody.setText(itemModel.body);

        return view1;
    }
}
