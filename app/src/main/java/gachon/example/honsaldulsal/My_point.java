package gachon.example.honsaldulsal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class My_point extends Fragment {

    private Button goback;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        ViewGroup rootView =(ViewGroup)inflater.inflate(R.layout.my_point,container,false);


        goback =  (Button)rootView.findViewById(R.id.goback1);
        goback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((HomeActivity)getActivity()).setFrag(2);
            }


        });

        return rootView;
    }
}
