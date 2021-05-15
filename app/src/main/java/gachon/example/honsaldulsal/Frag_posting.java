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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.InputStream;
import java.util.HashMap;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static java.lang.System.exit;

public class Frag_posting extends Fragment {

    private DatabaseReference mDatabase;
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
        mDatabase = FirebaseDatabase.getInstance().getReference("Product");



        EditText Iname = v.findViewById(R.id.postItem);
        EditText Iquantity = v.findViewById(R.id.postQuantity);
        EditText Iprice = v.findViewById(R.id.postPrice);
        EditText Ilocation = v.findViewById(R.id.postLocation);
        EditText Ipeople = v.findViewById(R.id.postPeople);
        EditText IEtc = v.findViewById(R.id.postEtc);

        Button postbtn = v.findViewById(R.id.postBtn);

        postbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference prod = database.getReference(product);
                HashMap<String, Object> productValue = new HashMap<>();
                productValue.put("etc", IEtc);
                productValue.put("image", "");
                productValue.put("item", Iname);
                productValue.put("location", Ilocation);
                productValue.put("peopleNum", Ipeople);
                productValue.put("price", Iprice);
                productValue.put("quantity", Iquantity);

                prod.child("Product20").updateChildren(productValue);
            }
        });

        return v;

    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data){
//        if(requestCode == 0){
//            if(resultCode == RESULT_OK){
//                try{
//                    InputStream in = getContentResolver().openInputStream(data.getData());
//                    Bitmap img = BitmapFactory.decodeStream(in);
//                    in.close();
//                    imageView.setImageBitmap(img);
//                }catch(Exception e){
//                    exit(1);
//                }
//            }
//        }
//    }
}