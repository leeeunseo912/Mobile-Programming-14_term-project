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
// 이미지 선택
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
//      location
//      FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String email = user != null ? user.getEmail() : null;
//        email = email.substring(0, email.indexOf("@"));

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
                productValue.put("price", Iprice.getText().toString());
                productValue.put("quantity", Iquantity.getText().toString());
                productValue.put("chat", "");
                myRef.child(product).child("Product519").setValue(productValue);

                HashMap<String, Object> userValue = new HashMap<>();
                userValue.put("username", "");
                myRef.child(product).child("Product519").child("chat").setValue(userValue);

                HashMap<String, Object> chatValue = new HashMap<>();
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat sdf = new SimpleDateFormat("MM.dd HH:mm");
                String getTime = sdf.format(date);
                chatValue.put("message", "testing");
                chatValue.put("time", getTime);
                myRef.child(product).child("Product519").child("chat").child("username").setValue(chatValue);

                Toast.makeText(getActivity(), "포스팅 완료", Toast.LENGTH_LONG).show();
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