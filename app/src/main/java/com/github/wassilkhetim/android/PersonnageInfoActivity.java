package com.github.wassilkhetim.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PersonnageInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnage_info);

        Button retour_bouton = this.findViewById(R.id.button_return);
        retour_bouton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

        Bundle b = getIntent().getExtras();

        TextView titre_text_view = this.findViewById(R.id.nom_personnage_text_view);
        TextView sub_info_tv = this.findViewById(R.id.sub_info_text_view);
        TextView origin_tv = this.findViewById(R.id.origine_personnage_edit_text_view);
        TextView current_loc_tv = this.findViewById(R.id.current_location_edit_textview);
        ImageView image_iv = this.findViewById(R.id.personnage_imageView);
        if(b !=null){
            if(b.getString("name") != null) titre_text_view.setText(b.getString("name"));
            if(b.getString("status") != null && b.getString("species") != null) sub_info_tv.setText(b.getString("status") + " - " + b.getString("species"));
            if(b.getString("origin") != null) origin_tv.setText("Origin : "+b.getString("origin"));
            if(b.getString("location") != null) current_loc_tv.setText(b.getString("location"));
            if(b.getString("image") != null) Glide.with(getApplicationContext()).load(b.getString("image")).centerCrop().into(image_iv);
        }


    }
}
