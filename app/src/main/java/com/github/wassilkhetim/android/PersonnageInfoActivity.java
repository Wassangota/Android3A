package com.github.wassilkhetim.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        if(b !=null && b.getString("name") != null){
            titre_text_view.setText(b.getString("name"));
        }

    }
}
