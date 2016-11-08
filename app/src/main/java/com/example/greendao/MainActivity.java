package com.example.greendao;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.greendao.adapter.MyAdapter;
import com.example.greendao.db.DBUtil;
import com.example.greendao.entity.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_age)
    EditText et_age;
    @BindView(R.id.lv)
    ListView lv;
    private DBUtil dbUtil;
    private List<User> list;
    private MyAdapter adapter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dbUtil = DBUtil.getInstance(this);
        list = new ArrayList<>();
        user = new User();
        adapter = new MyAdapter(MainActivity.this, list);
    }

    public void queryall(View view) {
        list.clear();
        list.addAll(dbUtil.queryUserList());
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void update(View view) {
        String name = et_name.getText().toString().toString().trim();
        String age = et_age.getText().toString().trim();
        if ("".equals(name)&&name.length()==0){
            Toast.makeText(MainActivity.this, "姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(age)&&age.length()==0){
            Toast.makeText(MainActivity.this, "年龄不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        User user1 = dbUtil.queryByUserName(name);
        if (user1 == null) {
            Toast.makeText(MainActivity.this, "这个人不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        int age1 = Integer.parseInt(age);
        Log.i("lxq", "修改:" + name + "+" + age1);
        user1.setAge(age1);
        dbUtil.updateUser(user1);
        Toast.makeText(MainActivity.this, "修改成功，修改为" + user1.toString(), Toast.LENGTH_SHORT).show();
    }

    public void delete(View view) {
        String name = et_name.getText().toString().toString().trim();
        if ("".equals(name)&&name.length()==0){
            Toast.makeText(MainActivity.this, "姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.i("lxq", "删除：" + name );
        User user1 = dbUtil.queryByUserName(name);
        if (user1 == null) {
            Toast.makeText(MainActivity.this, "这个人不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        dbUtil.deleteUser(user1);
        Toast.makeText(MainActivity.this, "删除成功"+user1.toString(), Toast.LENGTH_SHORT).show();

    }

    public void add(View view) {
        String name = et_name.getText().toString().toString().trim();
        String age = et_age.getText().toString().trim();
        if ("".equals(name)&&name.length()==0){
            Toast.makeText(MainActivity.this, "姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(age)&&age.length()==0){
            Toast.makeText(MainActivity.this, "年龄不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (dbUtil.queryByUserName(name)!=null){
            Toast.makeText(MainActivity.this, "这个人已经存在", Toast.LENGTH_SHORT).show();
            return;
        }
        int age1 = Integer.parseInt(age);
        Log.i("lxq", "添加" + name + "+" + age);
        user.setName(name);
        user.setAge(age1);
        user.setId(null);
        dbUtil.insertUser(user);
        Toast.makeText(MainActivity.this, "添加成功"+user.toString(), Toast.LENGTH_SHORT).show();
        user.setName("");
        user.setAge(-1);
    }


}
