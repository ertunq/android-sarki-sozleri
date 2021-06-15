package com.bsv.sarkisozleri;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SarkiSozuGirisActivity extends AppCompatActivity {

    EditText txtSarkiSozuBaslik, txtSarkiSozuIcerik;
    Button btnSarkiSozuKaydet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sarki_sozu_giris);
        ActionBar actBar=getSupportActionBar();
        if(actBar!=null)
            actBar.hide();
        olustur();
        islevAta();
    }

    private void olustur() {
        txtSarkiSozuBaslik=findViewById(R.id.txtSarkiSozuBaslik);
        txtSarkiSozuIcerik=findViewById(R.id.txtSarkiSozuIcerik);
        txtSarkiSozuIcerik.computeScroll();
        btnSarkiSozuKaydet=findViewById(R.id.btnSarkiSozuKaydet);
    }

    private void islevAta(){
        btnSarkiSozuKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtSarkiSozuBaslik.getText().toString().trim().length()>0 && txtSarkiSozuIcerik.getText().toString().trim().length()>0){
                    kaydet();
                }else{
                    AlertDialog.Builder builder=new AlertDialog.Builder(SarkiSozuGirisActivity.this);
                    builder.setTitle("Uyari");
                    builder.setMessage("Şarkı Başlığı / İçerik  Boş Olamaz..!")
                            .setCancelable(false)
                            .setNegativeButton("Tamam", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert=builder.create();
                    alert.show();
                }
            }
        });
    }

    private void kaydet(){
        DatabaseHelper db=DatabaseHelper.getInstance(getApplicationContext());
        SarkiSozu soz=new SarkiSozu();
        int id=db.enBuyukId();
        if (id==-1)
            soz.setId(1);
        else
            soz.setId(id+1);
        soz.setBaslik(txtSarkiSozuBaslik.getText().toString().trim());
        soz.setIcerik(txtSarkiSozuIcerik.getText().toString());

        long sonuc=db.yeniSarkiSozuKaydet(soz);
        if (sonuc>0){
            Toast.makeText(getApplicationContext(),"Kayıt Yapıldı...",Toast.LENGTH_SHORT).show();
            alanlariBosalt();
            db.veritabaniniKapat();
            db.close();
        }else{
            Toast.makeText(getApplicationContext(),"DİKKAT [Kayıt Yaparken Hata Oluştu!!!]",Toast.LENGTH_SHORT).show();
        }


    }

    private void alanlariBosalt(){
        txtSarkiSozuBaslik.setText("");
        txtSarkiSozuIcerik.setText("");
    }
}