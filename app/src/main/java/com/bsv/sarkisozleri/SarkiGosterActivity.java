package com.bsv.sarkisozleri;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SarkiGosterActivity extends AppCompatActivity {

    TextView lblSarkiSozuId,lblSarkiSozuBaslik,lblSarkiSozuIcerik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sarki_goster);
        ActionBar actBar=getSupportActionBar();
        if (actBar!=null)
            actBar.hide();
        olustur();
        veriAl();
    }

    private void olustur() {
        lblSarkiSozuId=findViewById(R.id.lblSarkiSozuId);
        lblSarkiSozuBaslik=findViewById(R.id.lblSarkiSozuBaslik);
        lblSarkiSozuIcerik=findViewById(R.id.lblSarkiSozuIcerik);
    }

    private void veriAl(){
        Bundle bundle=getIntent().getExtras();
        lblSarkiSozuId.setText(String.valueOf(bundle.getInt("id")));
        lblSarkiSozuBaslik.setText(bundle.getString("baslik"));
        lblSarkiSozuIcerik.setText(bundle.getString("icerik"));
    }

}