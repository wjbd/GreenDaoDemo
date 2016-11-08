package com.example.greendao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.greendao.R;
import com.example.greendao.entity.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxx on 2016/11/7.
 */

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<User> list;

    public MyAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.item_id.setText(list.get(i).getId() + "");
        viewHolder.item_age.setText(list.get(i).getAge() + "");
        viewHolder.item_name.setText(list.get(i).getName());
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.item_name)
        TextView item_name;
        @BindView(R.id.item_age)
        TextView item_age;
        @BindView(R.id.item_id)
        TextView item_id;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}