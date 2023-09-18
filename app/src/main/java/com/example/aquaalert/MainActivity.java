package com.example.aquaalert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    final private String model_url = "https://huggingface.co/spaces/abanand132/aqua-alert";
    final private String github_url = "https://github.com/abanand132/Aqua-Alert";

    EditText place;
    EditText chemical;
    Button save;
    Button model;
    Button gihub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = findViewById(R.id.modelid);
        gihub = findViewById(R.id.sourceid);
        save = findViewById(R.id.saveid);
        place = findViewById(R.id.placeid);
        chemical = findViewById(R.id.chemicalid);

        model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("url",model_url);
                startActivity(intent);
            }
        });

        gihub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("url",github_url);
                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String placename = place.getText().toString();
                String chemicalname = chemical.getText().toString();
                if(placename.isEmpty()|| chemicalname.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Text fields can't be empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");
                    intent.putExtra(Intent.EXTRA_EMAIL, "sonuasif174@gmail.com");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Urgent Action Required: Contamination of "+ placename + "Water Bodies");
                    intent.putExtra(Intent.EXTRA_TEXT, "I hope this message finds you well. We are writing to you on behalf of Aqua Alert, a dedicated environmental organization committed to the preservation and conservation of water bodies across our region. We have recently come across a matter of utmost concern that requires your immediate attention and intervention.\n\n\n It has come to our attention that "+placename+", a vital natural resource and a source of livelihood for the surrounding community, is currently facing a severe contamination issue due to the presence of "+chemicalname+". The implications of this contamination are dire and pose a significant threat to the local ecosystem, as well as the health and well-being of the residents who rely on the lake for various purposes.");
                    startActivity(Intent.createChooser(intent, "Send Email"));
                }
            }
        });
    }
}