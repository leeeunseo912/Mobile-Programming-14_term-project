package gachon.example.honsaldulsal;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class My_information extends Fragment {

    private Button goback;
    private TextView name;
    private TextView email;
    private TextView birthday;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("UserInfo");
    private DatabaseReference Username = myRef.child("leeeunseo");
    private DatabaseReference Useremail = myRef.child("email");
    private DatabaseReference Userbirth = myRef.child("birth");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.my_information, container, false);

        name = (TextView)rootView.findViewById(R.id.username);
        email= (TextView)rootView.findViewById(R.id.useremail);
        birthday = (TextView)rootView.findViewById(R.id.userbirth);


        goback = (Button) rootView.findViewById(R.id.goback2);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) getActivity()).setFrag(3);
            }
        });

//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot){
//            if(dataSnapshot.getValue(String.class)!=null){
//                String key = dataSnapshot.getKey();
//                if(key.equals("email")){
//                    String first = dataSnapshot.getValue(String.class);
//                    email.setText(first);
//                }
//                if(key.equals("birth")){
//                    String second = dataSnapshot.getValue(String.class);
//                    birthday.setText(second);
//                }
//            }
//
//        }
//
//        public void onCancelled(DatabaseError databaseError){
//
//
//        }
//
//        @Override
//        protected void onStart(){
//
//            super.onStart();;
//            Useremail.addValueEventListener(this);
//            Userbirth.addValueEventListener(this);
//        }

        return rootView;

    }
}

