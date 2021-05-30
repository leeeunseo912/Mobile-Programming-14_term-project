package gachon.example.honsaldulsal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static java.lang.System.exit;

public class Frag_posting extends Fragment {

    private ImageView imageView;
    private static String product = "Product";
    FirebaseDatabase database;
    DatabaseReference myRef;
    int REQUEST_IMAGE_CODE=1001;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_frag_posting,container,false);
//      이미지 선택
        imageView = v.findViewById(R.id.postImage);
        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,REQUEST_IMAGE_CODE);
            }
        });



        EditText Iname = v.findViewById(R.id.postItem);
        EditText Iquantity = v.findViewById(R.id.postQuantity);
        EditText Iprice = v.findViewById(R.id.postPrice);
        EditText Ipeople = v.findViewById(R.id.postPeople);
        EditText IEtc = v.findViewById(R.id.postEtc);

        Button postbtn = v.findViewById(R.id.postBtn);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user != null ? user.getEmail() : null;
        email = email.substring(0, email.indexOf("@"));
        String finalEmail = email;

        //      location
        final String[] loc = {new String()};
        myRef = database.getInstance().getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                loc[0] = (String)snapshot.child("UserInfo").child(finalEmail).child("location").getValue();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        postbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                double people = Integer.parseInt(Ipeople.getText().toString());
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference();
                HashMap<String, Object> productValue = new HashMap<>();
                productValue.put("currentNum", 1);
                productValue.put("etc", IEtc.getText().toString());
                productValue.put("image", "aaa");
                productValue.put("item", Iname.getText().toString());
                productValue.put("location", loc[0]);
                productValue.put("peopleNum", people);
                productValue.put("price", getPrice(Iprice.getText().toString()));
                productValue.put("quantity", Iquantity.getText().toString());
                productValue.put("par", finalEmail);
                myRef.child(product).child(finalEmail + Iname.getText().toString()).setValue(productValue);
                myRef.child(product).child(finalEmail + Iname.getText().toString()).child("complete").setValue(0);
                myRef.child(product).child(finalEmail + Iname.getText().toString()).child("par").push().child("id").setValue(finalEmail);
                Toast.makeText(getActivity(), "포스팅 완료", Toast.LENGTH_LONG).show();
            }
        });

        return v;

    }
    public String getPrice(String name) {
        String price = "";
        if(name.equalsIgnoreCase("water")){
            price = "500";
        }else if(name.equalsIgnoreCase("handcream")){
            price = "800";
        }else if(name.equalsIgnoreCase("curry")){
            price = "1790";
        }else if(name.equalsIgnoreCase("Sesame oil")){
            price =  "6000";
        }else if(name.equalsIgnoreCase("Oyster Sauce")){
            price = "3000";
        }else
            price = name;
        return price;
    }
}