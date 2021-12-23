package com.gagana.emediatest.View;

/**
 * Create by Gagana Lakruwan 24/12/2021
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gagana.emediatest.R;
import com.gagana.emediatest.Model.sampleDataModel;
import com.squareup.picasso.Picasso;

public class SingleDataViewActivity extends AppCompatActivity {

    TextView txtTitle,txtDescription,headerTitle;
    ImageView imageView;
    ImageView location,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_data_view);

        txtTitle=(TextView)findViewById(R.id.txtTitle);
        txtDescription=(TextView)findViewById(R.id.txtDescription);
        imageView=(ImageView)findViewById(R.id.imageView);
        location=(ImageView)findViewById(R.id.main_page_toolbar).findViewById(R.id.iconLocation);
        back=(ImageView)findViewById(R.id.main_page_toolbar).findViewById(R.id.iconBack);
        headerTitle=(TextView) findViewById(R.id.main_page_toolbar).findViewById(R.id.title);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        sampleDataModel model = (sampleDataModel) bundle.getSerializable("Oblect");

        txtTitle.setText(model.getTitle());
        txtDescription.setText(model.getDescription());
        headerTitle.setText("Details");
        Picasso.with(SingleDataViewActivity.this).load("https://cdn.britannica.com/91/181391-050-1DA18304/cat-toes-paw-number-paws-tiger-tabby.jpg").into(imageView);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent1=new Intent(SingleDataViewActivity.this, HomeActivity.class);
//                startActivity(intent1);
                finish();
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(SingleDataViewActivity.this, MapsActivity.class);
                intent1.putExtra("lat",model.getLatitude());
                intent1.putExtra("lang",model.getLongitude());
                intent1.putExtra("title",model.getTitle());
                intent1.putExtra("address",model.getAddress());
                startActivity(intent1);
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}