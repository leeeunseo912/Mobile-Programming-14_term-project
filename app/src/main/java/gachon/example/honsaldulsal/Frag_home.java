package gachon.example.honsaldulsal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.widget.SearchView;

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
import java.util.Arrays;
import java.util.List;

import io.grpc.ProxyDetector;

public class Frag_home extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Product> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_frag_home,container,false);
 //       items = Arrays.asList();

//         Frag_home(List<String> items){
//             this.items = items;
//        }
//
//
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

        searchView = v.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final ArrayList<Product> filteredList = filter(arrayList,newText);
                adapter.setFilter(filteredList);
                return true; //입력하는 값을 실시간으로 받기 위해서 true로 고침침
            }

            private ArrayList<Product>filter(ArrayList<Product> products, String query){
                query = query.toLowerCase();

                ArrayList<Product> filteredList = new ArrayList<>();
                for(Product product : products){
                    final String text = product.getItem().toLowerCase();
                    if(text.contains(query)){
                        filteredList.add(product);
                    }
                }
                return filteredList;
            }
        });

        return v;
    }


//    private String search(String query){
//        StringBuilder stringBuilder = new StringBuilder();
//        for(int i=0;i<items.size();i++) {
//            String item = items.get(i);
//            if (item.toLowerCase().contains(query.toLowerCase())) {
//                stringBuilder.append(item);
//            }
//            else{
//                stringBuilder.append(item + "\n");
//            }
//        }
//        return stringBuilder.toString();
//    }
//
//
//
//    private String getResult(){
//        StringBuilder stringBuilder = new StringBuilder();
//        for(int i=0;i<items.size();i++) {
//            String item = items.get(i);
//            if (i == items.size() - 1) {
//                stringBuilder.append(item);
//            }
//            else{
//                stringBuilder.append(item + "\n");
//            }
//        }
//        return stringBuilder.toString();
//    }
}