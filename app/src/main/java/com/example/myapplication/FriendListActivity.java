package com.example.myapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.friend.AddFriendFragment;
import com.example.friend.Friend;
import com.example.friend.FriendAdapter;

import java.util.ArrayList;
import java.util.List;

public class FriendListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FriendAdapter friendAdapter;
    private List<Friend> friendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("친구");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        friendList = new ArrayList<>();
        friendList.add(new Friend("John Doe", R.drawable.ic_launcher_foreground));
        friendList.add(new Friend("Jane Smith", R.drawable.ic_launcher_background));
        // 친구 목록에 더 추가

        friendAdapter = new FriendAdapter(friendList);
        recyclerView.setAdapter(friendAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.friend_menu, menu);
        return true;
    }

    // 액션바 메뉴 클릭 처리
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Toast.makeText(this,"클릭 처리 완료", Toast.LENGTH_SHORT).show();

        if (id == R.id.action_add_friend) {

            // 친구 추가 버튼 클릭 시 플래그먼트 열기
            openAddFriendFragment();
            return true;
        }
        else if (id == android.R.id.home) {
            // 뒤로가기 버튼 클릭 시 처리
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // 친구 추가 기능 구현
    private void openAddFriendFragment() {
        Toast.makeText(this ,"open",Toast.LENGTH_SHORT);
        AddFriendFragment addFriendFragment = new AddFriendFragment();
        addFriendFragment.friendAdapter = friendAdapter;

        // 플래그먼트를 열 때 필요한 데이터를 전달할 수도 있습니다.
        //addFriendFragment.friendList = friendList;
        //addFriendFragment.friendAdapter = friendAdapter;

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, addFriendFragment);
        fragmentTransaction.addToBackStack(null); // 백스택에 추가하여 뒤로가기 동작이 가능하게 합니다.
        fragmentTransaction.commit();
    }
}