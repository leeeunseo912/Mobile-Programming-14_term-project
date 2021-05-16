package gachon.example.honsaldulsal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Frag_home extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Product> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_frag_home,container,false);

//        Button button = (Button)v.findViewById(R.id.btn_enlarge);
//        button.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                MainActivity activity = (MainActivity) getActivity();
//                activity.onFragmentChanged(0);
//            }
//        });
//        Frag_item.java
//        public class Frag_item extends Fragment{
//            @Nullable
//            @Override
//            public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
//                ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_frag_item, container, false);
//
//                Button button = (Button)rootView.findViewById(R.id.btn_exit);
//                button.setOnClickListener(new View.OnClickListener(){
//                    @Override
//                    public void onClick(View v){
//                        MainActivity activity = (MainActivity)getActivity();
//                        activity.onFragmentChanged(1);
//                    }
//                });
//
//                return rootView;
//            }
//        }

        recyclerView = (RecyclerView)v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        databaseReference = database.getReference("Product"); // DB 테이블 연결
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Product product = snapshot.getValue(Product.class);
                    arrayList.add(product);
                }
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Frag_home", String.valueOf(databaseError.toException()));
            }
        });

        adapter = new ProductAdapter(arrayList, getContext());
        recyclerView.setAdapter(adapter);

        return v;

    }
}