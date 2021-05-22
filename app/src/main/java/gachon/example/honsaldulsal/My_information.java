package gachon.example.honsaldulsal;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class My_information extends Fragment {

    private Button goback;
    private TextView name_text;
    private TextView email_text;
    private TextView birthday_text;

    private FirebaseAuth mAuth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();//파이어베이스 realtime 가져오기
    private DatabaseReference myRef;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.my_information, container, false);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user!=null?user.getEmail():null;//로그인한 유저의 고유 uid 가져오기
        email = email.substring(0,email.indexOf("@"));
        mAuth = FirebaseAuth.getInstance(); //유저 계정 정보 가져오기

        myRef = database.getReference("UserInfo");
        DatabaseReference name = myRef.child(email).child("name");
        DatabaseReference Email = myRef.child(email).child("email");
        DatabaseReference birth = myRef.child(email).child("birth");

        name_text = (TextView)rootView.findViewById(R.id.username);
        email_text= (TextView)rootView.findViewById(R.id.useremail);
        birthday_text = (TextView)rootView.findViewById(R.id.userbirth);

        name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String text = snapshot.getValue(String.class);
                name_text.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Email.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String text = snapshot.getValue(String.class);
                email_text.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        birth.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String text = snapshot.getValue(String.class);
                birthday_text.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        goback = (Button) rootView.findViewById(R.id.goback2);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) getActivity()).setFrag(3);
            }
        });

        return rootView;

    }
}