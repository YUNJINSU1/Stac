package com.example.friend;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.friend.Friend;
import com.example.friend.FriendAdapter;
import com.example.myapplication.R;

import java.util.List;

public class AddFriendFragment extends Fragment {

    private EditText editTextFriendId;
    private Button buttonAddFriend;
    //public List<Friend> friendList;
    public
    FriendAdapter friendAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 여기서 필요한 초기화 작업을 수행할 수 있습니다.
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_friend, container, false);

        editTextFriendId = view.findViewById(R.id.editTextFriendId);
        buttonAddFriend = view.findViewById(R.id.buttonAddFriend);

        buttonAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFriend();
            }
        });

        return view;
    }

    private void addFriend() {
        String friendId = editTextFriendId.getText().toString().trim();
        if(friendId == null){
            Toast.makeText(getActivity(),"null값 입니다.",Toast.LENGTH_SHORT).show();
            return;
        }
        // 여기서 입력된 친구 아이디를 이용하여 친구를 추가하는 작업을 수행합니다.
        // 예를 들어, 입력된 아이디로 Friend 객체를 생성하여 리스트에 추가하고 어댑터에 알립니다.
        Friend newFriend = new Friend(friendId, R.drawable.basic_user);
        if(newFriend == null){
            Toast.makeText(getActivity(),"null값 입니다.",Toast.LENGTH_SHORT).show();
            return;
        }
        friendAdapter.friendList.add(newFriend);

        friendAdapter.notifyDataSetChanged(); // 데이터 변경을 어댑터에 알림

        // 친구 추가 완료 후 플래그먼트를 닫습니다.
        getParentFragmentManager().popBackStack();
    }
}
