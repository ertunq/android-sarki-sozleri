package com.bsv.sarkisozleri;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminGirisActivity extends AppCompatActivity {

    EditText txtKullaniciAd,txtKullaniciSifre;
    Button btnAdminGiris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_giris);
        ActionBar actBar=getSupportActionBar();
        if(actBar!=null)
            actBar.hide();
        olustur();
        islevAta();
    }

    private void olustur(){
        txtKullaniciAd=findViewById(R.id.txtKullaniciAd);
        txtKullaniciSifre=findViewById(R.id.txtKullaniciSifre);
        btnAdminGiris=findViewById(R.id.btnAdminGiris);
    }
    private void islevAta(){
        btnAdminGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtKullaniciAd.getText().length()>0 && txtKullaniciSifre.getText().length()>0){
                    if (txtKullaniciAd.getText().toString().trim().equals("admin") && txtKullaniciSifre.getText().toString().trim().equals("1234")){
                        //Şarkı Giriş Sayfasına Geç
                        Intent intent=new Intent(getApplicationContext(),SarkiSozuGirisActivity.class);
                        startActivity(intent);
                    }else{
                        AlertDialog.Builder builder=new AlertDialog.Builder(AdminGirisActivity.this);
                        builder.setTitle("Uyari");
                        builder.setMessage("Kullanıcı Adı veya Şifre Hatalı..!")
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
                }else{
                    AlertDialog.Builder builder=new AlertDialog.Builder(AdminGirisActivity.this);
                    builder.setTitle("Uyari");
                    builder.setMessage("Kullanıcı Adı / Şifre Boş Olamaz..!")
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
}