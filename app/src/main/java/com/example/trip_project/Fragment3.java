package com.example.trip_project;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment{

    Bitmap bitmap;
    private Button button;

    private ImageView fragmentImageView;

    public Fragment3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // LayoutInflater 사용해 Resource Layout을 View로 변환해준 후 findViewById() 호출
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab_frame3, container, false);

        fragmentImageView = view.findViewById(R.id.imageView2);
        button = view.findViewById(R.id.btnEditProfile);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditProfileActivity로 이동하는 Intent 생성
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        // EditProfileActivity에서 전달된 Uri를 받아서 ImageView에 설정
        if (getArguments() != null) {
            String imageUriString = getArguments().getString("imageUri");
            if (imageUriString != null) {
                Uri imageUri = Uri.parse(imageUriString);
                fragmentImageView.setImageURI(imageUri);
            }
        }



        return view;
    }

}
