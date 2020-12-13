package com.example.bike_servicing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_bike extends AppCompatActivity {

    EditText et_modelname,et_regno,et_type;
    Button btnsub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bike);

        btnsub = findViewById(R.id.btnsub);
        et_modelname = findViewById(R.id.et_mdoelname);
        et_regno = findViewById(R.id.et_regno);
        et_type = findViewById(R.id.et_type);

        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et_modelname.getText().toString().isEmpty() || et_regno.getText().toString().isEmpty() || et_type.getText().toString().isEmpty()){
                    Toast.makeText(add_bike.this,"Enter All Fields!!",Toast.LENGTH_SHORT).show();
                }else{
                    String title = et_modelname.getText().toString().trim();
                    String author = et_regno.getText().toString().trim();
                    String genre = et_type.getText().toString().trim();

                    Intent intent = new Intent();
                    intent.putExtra("modelname",title);
                    intent.putExtra("regno",author);
                    intent.putExtra("type",genre);
                    setResult(RESULT_OK,intent);
                    add_bike.this.finish();
                }

            }
        });

    }
}