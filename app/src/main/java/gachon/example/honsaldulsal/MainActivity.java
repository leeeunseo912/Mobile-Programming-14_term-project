package gachon.example.honsaldulsal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
//    Frag_home homeFragment;
//    Frag_item itemFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        homeFragment = new Frag_home();
//        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
//        itemFragment = new Frag_item();
    }
//    public void onFragmentChanged(int index){
//        if(index == 0){
//            getSupportFragmentManager().beginTransaction().replace(R.id.container, itemFragment).commit();
//        }else if(index == 1){
//            getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
//        }
//    }
}