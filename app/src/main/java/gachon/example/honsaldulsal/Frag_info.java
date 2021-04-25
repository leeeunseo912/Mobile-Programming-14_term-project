package gachon.example.honsaldulsal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.IOException;

public class Frag_info extends Fragment {

    private Button my_point_btn;
    private Button my_info_btn;
    private Button my_transaction_btn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_frag_info, container, false);

        my_point_btn = (Button) v.findViewById(R.id.my_point);
        my_point_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity)getActivity()).setFrag(3);
            }

        });

        my_info_btn = (Button) v.findViewById(R.id.my_info);
        my_info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity)getActivity()).setFrag(4);
            }

        });

        my_transaction_btn = (Button) v.findViewById(R.id.trans_list);
        my_transaction_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity)getActivity()).setFrag(5);
            }

        });


        return v;

    }




}
