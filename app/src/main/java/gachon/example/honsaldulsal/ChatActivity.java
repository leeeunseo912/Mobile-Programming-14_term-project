package gachon.example.honsaldulsal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    ArrayList<Chat> list = new ArrayList<>();
    ListView lv;
    Button btn;
    Button complete;
    EditText edt;
    String id = "";
    String productKey = "";
    int cnum;
    int tnum;
    int in;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent intent = getIntent();
        productKey = intent.getStringExtra("productKey");
        lv = findViewById(R.id.listView);
        edt = findViewById(R.id.editText);
        btn = findViewById(R.id.bnt_send);
        complete = findViewById(R.id.complete);


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child("Product").child(productKey).child("chat");
        DatabaseReference mycRef = database.getReference().child("Product").child(productKey);
        DatabaseReference dRef = database.getReference().child("Product").child(productKey);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user != null ? user.getEmail() : null;
        email = email.substring(0, email.indexOf("@"));
        id = email;


        final ChatAdapter adapter = new ChatAdapter(getApplicationContext(), R.layout.talklist, list, id);
        ((ListView) findViewById(R.id.listView)).setAdapter(adapter);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edt.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "내용을 입력하세요.", Toast.LENGTH_LONG).show();
                } else {
                    Date today = new Date();
                    SimpleDateFormat timeNow = new SimpleDateFormat("a K:mm");

                    StringBuffer sb = new StringBuffer(edt.getText().toString());
                    if (sb.length() >= 15) {
                        for (int i = 1; i <= sb.length() / 15; i++) {
                            sb.insert(15 * i, "\n");
                        }
                    }

                    myRef.push().setValue(new Chat(id, sb.toString(), timeNow.format(today)));
                    edt.setText("");

                }
            }
        });



        myRef.child("com").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Par p = snapshot.getValue(Par.class);
                    String gid = p.getId();
                    if (id.equals(gid)) {
                        in = 1; //있으면 1
                        complete.setEnabled(false);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        mycRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Complete com = snapshot.getValue(Complete.class);
                Product pd = snapshot.getValue(Product.class);
                try{
                cnum = com.getComplete();
                tnum = pd.getPeopleNum();

                    if(cnum == tnum){
                        if(count==0) {
                            Intent intent = new Intent();
                            intent.putExtra("deleteProductKey", productKey); // productKey 전달
                            setResult(RESULT_OK, intent);
                            finish();
                            count++;
                        }
                    }
                }catch (NullPointerException e){
                    Log.e("Null","null");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(in !=1) {
                    mycRef.child("complete").setValue(++cnum);
                    myRef.child("com").push().child("id").setValue(id);
                }

            }
        });

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Chat value = dataSnapshot.getValue(Chat.class); // 괄호 안 : 꺼낼 자료 형태
                list.add(value);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
