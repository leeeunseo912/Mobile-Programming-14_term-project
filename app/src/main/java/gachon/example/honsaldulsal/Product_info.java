package gachon.example.honsaldulsal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class Product_info extends AppCompatActivity {

    ImageView image_tv;
    TextView item_tv;
    TextView price_tv;
    TextView people_tv;
    TextView etc_tv;
    Button chat;
    String image;
    String item;
    String location;
    String people;
    String etc;
    String price;
    String quantity;
    String productKey;
    int cur;
    int tnum;
    String id;
    int in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);


        Intent intent = getIntent();
        image = intent.getStringExtra("image");
        item = intent.getStringExtra("item");
        location = intent.getStringExtra("item");
        people = intent.getStringExtra("people");
        etc = intent.getStringExtra("etc");
        price = intent.getStringExtra("price");
        quantity = intent.getStringExtra("quantity");
        productKey = intent.getStringExtra("productKey");

        image_tv = findViewById(R.id.itemImage);
        item_tv = findViewById(R.id.itemName);
        price_tv = findViewById(R.id.price);
        people_tv = findViewById(R.id.people);
        etc_tv = findViewById(R.id.etc);
        chat = findViewById(R.id.chat);
        Glide.with(this)
                .load(image)
                .into(this.image_tv); //이미지 삽입
        item_tv.setText(item);
        price_tv.setText(price);
        etc_tv.setText(etc);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Product").child(productKey);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user != null ? user.getEmail() : null;
        email = email.substring(0, email.indexOf("@"));
        id = email;

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Product pd = snapshot.getValue(Product.class);
                cur = pd.getCurrentNum();
                tnum = pd.getPeopleNum();

                people_tv.setText(cur + "명 / " + tnum + "명");
                if (cur == tnum) {
                    chat.setText("만원");
                    chat.setEnabled(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chatIntent = new Intent(getApplicationContext(), ChatActivity.class);
                chatIntent.putExtra("productKey", productKey);
                if(in != 1){
                myRef.child("currentNum").setValue(++cur);
                myRef.child("par").push().child("id").setValue(id);
                }

                startActivity(chatIntent);
            }
        });

        myRef.child("par").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Par p = snapshot.getValue(Par.class);
                    String gid = p.getId();
                    if (id.equals(gid)) {
                        in = 1;
                    } else {
                        in = 0;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });




    }
}