package gachon.example.honsaldulsal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
    TextView host_tv;
    TextView location_tv;
    Button chat;
    String image;
    String item;
    String location;
    String people;
    String etc;
    String price;
    String quantity;
    String productKey;
    String host;
    int cur=0;
    int tnum=0;
    String id;
    int in=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        Intent intent = getIntent();
        image = intent.getStringExtra("image");
        item = intent.getStringExtra("item");
        location = intent.getStringExtra("location");
        people = intent.getStringExtra("people");
        etc = intent.getStringExtra("etc");
        price = intent.getStringExtra("price");
        quantity = intent.getStringExtra("quantity");
        productKey = intent.getStringExtra("productKey");
        host = intent.getStringExtra("host");

        image_tv = findViewById(R.id.itemImage);
        item_tv = findViewById(R.id.itemName);
        price_tv = findViewById(R.id.price);
        people_tv = findViewById(R.id.people);
        etc_tv = findViewById(R.id.etc);
        host_tv = findViewById(R.id.host);
        chat = findViewById(R.id.chat);
        location_tv = findViewById(R.id.location);
        //Glide.with(this)
        //        .load(image)
        //.into(this.image_tv); //이미지 삽입
        location_tv.setText("위치 : " + location);
        item_tv.setText(item);
        price_tv.setText(price + "원");
        etc_tv.setText(etc);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Product").child(productKey);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user != null ? user.getEmail() : null;
        email = email.substring(0, email.indexOf("@"));
        id = email;

        host = productKey.replace(item, "");
        host_tv.setText("Host : " + host);

        // 이미지 강제설정
        if (item.contains("삼다수") == true)
            image_tv.setImageResource(R.drawable.water);
        else if (item.contains("삼분카레") == true)
            image_tv.setImageResource(R.drawable.curry);
        else if (item.contains("김") == true)
            image_tv.setImageResource(R.drawable.kim);
        else if (item.contains("휴지"))
            image_tv.setImageResource(R.drawable.tissue);
        else if (item.contains("다우니"))
            image_tv.setImageResource(R.drawable.downy);
        else if (item.contains("세제"))
            image_tv.setImageResource(R.drawable.beet);
        else if (item.contains("면도날") == true)
            image_tv.setImageResource(R.drawable.gillette);
        else if (item.contains("마스크") == true)
            image_tv.setImageResource(R.drawable.mask);
        else
            image_tv.setImageResource(R.drawable.set_image);

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chatIntent = new Intent(getApplicationContext(), ChatActivity.class);
                chatIntent.putExtra("productKey", productKey);
                if(in != 1){
                    myRef.child("currentNum").setValue(++cur);
                    myRef.child("par").push().child("id").setValue(id);
                }

                startActivityForResult(chatIntent,100);
            }
        });

        myRef.child("par").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Par p = snapshot.getValue(Par.class);
                    String gid = p.getId();
                    if (id.equals(gid)) {
                        in = 1; //있으면 1
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                Product pd = datasnapshot.getValue(Product.class);
                try {
                    cur = pd.getCurrentNum();
                    tnum = pd.getPeopleNum();
                    if (cur == tnum && in == 0) {
                        chat.setText("만원");
                        chat.setEnabled(false);
                    }
                    people_tv.setText("현재 - "+ cur + "명 / " + tnum + "명");
                }
                catch (NullPointerException e){
                    Log.e("null","null");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Product");

        Log.e("딜리트",Activity.RESULT_OK+" ");
        if(requestCode ==100){
            if (resultCode != Activity.RESULT_OK) {
                return;
            }
            String deleteProductKey = data.getExtras().getString("deleteProductKey");
            finish();
        }

    }
}