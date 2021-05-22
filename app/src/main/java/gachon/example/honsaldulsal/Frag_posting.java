package gachon.example.honsaldulsal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_frag_posting,container,false);


//        imageView = v.findViewById(R.id.postImage);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(intent, 0);
//            }
//
//        });

        
        EditText Iname = v.findViewById(R.id.postItem);
        EditText Iquantity = v.findViewById(R.id.postQuantity);
        EditText Iprice = v.findViewById(R.id.postPrice);
        EditText Ilocation = v.findViewById(R.id.postLocation);
        EditText Ipeople = v.findViewById(R.id.postPeople);
        EditText IEtc = v.findViewById(R.id.postEtc);

        Button postbtn = v.findViewById(R.id.postBtn);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user != null ? user.getEmail() : null;
        email = email.substring(0, email.indexOf("@"));
        String finalEmail1 = email;
        //      location



        postbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                double people = Integer.parseInt(Ipeople.getText().toString());
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                HashMap<String, Object> productValue = new HashMap<>();
                productValue.put("currentNum", 3);
                productValue.put("etc", IEtc.getText().toString());
                productValue.put("image", "aaa");
                productValue.put("item", Iname.getText().toString());
                productValue.put("location", Ilocation.getText().toString());
                productValue.put("peopleNum", people);
                productValue.put("price", getPrice(Iprice.getText().toString()));
                productValue.put("quantity", Iquantity.getText().toString());
                productValue.put("id", finalEmail1);
                myRef.child(product).child(finalEmail1 + "/" + Iname.getText().toString()).setValue(productValue);
                Toast.makeText(getActivity(), "포스팅 완료", Toast.LENGTH_LONG).show();
            }
        });

        return v;

    }

    public String getPrice(String name){
        String price = "";
        if(name.equalsIgnoreCase("water")){
            price = "440";
        }else if(name.equalsIgnoreCase("handcream")){
            price = "800";
        }else if(name.equalsIgnoreCase("curry")){
            price = "1790";
        }else if(name.equalsIgnoreCase("Sesame oil")){
            price = "3800";
        }else if(name.equalsIgnoreCase("Oyster Sauce")){
            price = "3000";
        }else{
            price = "No Info";
        }
        return price;
    }
}