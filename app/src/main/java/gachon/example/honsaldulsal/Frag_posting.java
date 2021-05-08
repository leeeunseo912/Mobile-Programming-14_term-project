package gachon.example.honsaldulsal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Frag_posting extends Fragment {

    private DatabaseReference mDatabase;

    public class User {
        String[] info = new String[6];

        public User() {
        }

        public User(String name, String quantity, String price, String location, String people, String etc) {
            this.info[0] = name;
            this.info[1] = quantity;
            this.info[2] = price;
            this.info[3] = location;
            this.info[4] = people;
            this.info[5] = etc;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_frag_posting,container,false);

//        mDatabase = FirebaseDatabase.getInstance().getReference("Product");
//
//
//
//        EditText Iname = v.findViewById(R.id.postItem);
//        EditText Iquantity = v.findViewById(R.id.postQuantity);
//        EditText Iprice = v.findViewById(R.id.postPrice);
//        EditText Ilocation = v.findViewById(R.id.postLocation);
//        EditText Ipeople = v.findViewById(R.id.postPeople);
//        EditText IEtc = v.findViewById(R.id.postEtc);
//
//        Button postbtn = v.findViewById(R.id.postBtn);
//
//        postbtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                User user = new User(Iname.getText().toString(), Iquantity.getText().toString(), Iprice.getText().toString(), Ilocation.getText().toString(), Ipeople.getText().toString(),
//                        IEtc.getText().toString());
//                mDatabase.child("info").child("product1").setValue(user);
//            }
//        });

        return v;

    }
}