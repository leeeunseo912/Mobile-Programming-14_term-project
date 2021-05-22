package gachon.example.honsaldulsal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


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
        people_tv.setText(people);
        etc_tv.setText(etc);

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chatIntent = new Intent(getApplicationContext(), ChatActivity.class);
                chatIntent.putExtra("productKey", productKey);
                startActivity(chatIntent);

            }
        });
    }
}