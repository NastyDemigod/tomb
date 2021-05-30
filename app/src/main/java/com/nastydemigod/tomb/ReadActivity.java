package com.nastydemigod.tomb;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ReadActivity extends AppCompatActivity {
//    private ListView listView;
//    private ArrayAdapter<String> adapter;
//    private List<String> listData;
//
//    private DatabaseReference mDataBase;
//    private String USER_KEY = "User";

    private TextView mTextViewResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_layout);
//        init();
//        getDataFromDB();
        mTextViewResult = findViewById(R.id.text_view_result);

        OkHttpClient client = new OkHttpClient();

        String url = "http://185.117.152.68:3999/defunct/6/";

        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    String myResponse = response.body().string();

                    ReadActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextViewResult.setText(myResponse);
                        }
                    });
                }
            }
        });
    }
//    private void init() {
//        listView = findViewById(R.id.listView);
//        listData = new ArrayList<>();
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
//        listView.setAdapter(adapter);
//        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
//    }
//    private void getDataFromDB() {
//        ValueEventListener vListener = new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (listData.size() > 0) listData.clear();
//                for(DataSnapshot ds : snapshot.getChildren() ){
//                    User user = ds.getValue(User.class);
//                    assert user != null;
//                    listData.add(user.name);
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        };
//        mDataBase.addValueEventListener(vListener);
//    }
}
