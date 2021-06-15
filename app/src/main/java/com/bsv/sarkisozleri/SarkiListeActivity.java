package com.bsv.sarkisozleri;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SarkiListeActivity extends AppCompatActivity {

    ListView listViewSarkiBaslik;
    List<SarkiSozu> lstSarkisozleri;
    List<SarkiSozu> lstTemp;
    adapterSarkiSozleri adp;
    EditText txtSarkiAra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sarki_liste);
        ActionBar actBar=getSupportActionBar();
        if (actBar!=null)
            actBar.hide();
        olustur();
    }

    private void olustur() {

        listViewSarkiBaslik=(ListView)findViewById(R.id.listViewSarkiBaslik);
        DatabaseHelper helper=new DatabaseHelper(getApplicationContext());
        lstSarkisozleri=new ArrayList<SarkiSozu>();
        lstSarkisozleri=helper.tumSarkiSozleriniGetir();
        helper.veritabaniniKapat();
        helper.close();

        adp=new adapterSarkiSozleri(getApplicationContext(),lstSarkisozleri,this);

        listViewSarkiBaslik.setAdapter(adp);

        lstTemp=new ArrayList<SarkiSozu>();
        txtSarkiAra=findViewById(R.id.txtAra);
        txtSarkiAra.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lstTemp.clear();
                if (txtSarkiAra.getText().toString().trim().length()==0){
                    adp=new adapterSarkiSozleri(getApplicationContext(),lstSarkisozleri,SarkiListeActivity.this);
                    listViewSarkiBaslik.setAdapter(adp);
                    return;
                }
                for (int i=0;i<lstSarkisozleri.size();i++){
                    SarkiSozu soz=lstSarkisozleri.get(i);
                    if (soz.getBaslik().toString().contains(s)){
                        lstTemp.add(soz);
                    }
                }
                if (lstTemp!=null){
                    adp=new adapterSarkiSozleri(getApplication(),lstTemp,SarkiListeActivity.this);
                    listViewSarkiBaslik.setAdapter(adp);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}