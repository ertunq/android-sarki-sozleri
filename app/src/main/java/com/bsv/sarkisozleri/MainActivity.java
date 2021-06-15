package com.bsv.sarkisozleri;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnYeniKayit,btnListele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actBar=getSupportActionBar();
        if(actBar!=null)
            actBar.hide();
        gorselOlustur();
        islevAta();
    }

    private void gorselOlustur() {
        btnYeniKayit=findViewById(R.id.btnYeniKayit);
        btnListele=findViewById(R.id.btnListele);
    }

    private void islevAta() {
        btnYeniKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentYeniKayit=new Intent(getApplicationContext(),AdminGirisActivity.class);
                startActivity(intentYeniKayit);
            }
        });
        btnListele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SarkiListeActivity.class);
                startActivity(intent);
            }
        });
    }

}