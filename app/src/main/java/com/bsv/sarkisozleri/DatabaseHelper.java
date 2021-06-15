package com.bsv.sarkisozleri;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {



    private static DatabaseHelper sInstance=null;
    private static final String VERITABANI_ADI="muzik";
    private static final int SURUM=3;
    private static final String TABLO_ADI="sarkisozu";

    private static final String alan1="id";
    private static final String alan2="baslik";
    private static final String alan3="icerik";

    private static final String TABLO_OLUSTUR="CREATE TABLE IF NOT EXISTS "+TABLO_ADI+" ("+
            alan1+" INTEGER PRIMARY KEY  NOT NULL  UNIQUE , "+
            alan2+" VARCHAR,"+
            alan3+" TEXT);";

    public static DatabaseHelper getInstance(Context context){
        if(sInstance==null){
            sInstance=new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public DatabaseHelper(@Nullable Context context) {
        super(context, VERITABANI_ADI, null, SURUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLO_OLUSTUR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLO_ADI);
        onCreate(db);
    }

    public void veritabaniniKapat(){
        SQLiteDatabase db=this.getReadableDatabase();
        if (db!=null && db.isOpen()){
            db.close();
        }
    }

    public int enBuyukId() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT MAX(id) AS enBuyukId FROM " + TABLO_ADI;

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null) {
            c.moveToFirst();
            int sonuc=c.getInt(c.getColumnIndex("enBuyukId"));
            c.close();
            db.close();
            return sonuc;

        } else {
            c.close();
            db.close();
            return -1;
        }
    }

    public long yeniSarkiSozuKaydet(SarkiSozu soz) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(alan1, soz.getId());
        values.put(alan2, soz.getBaslik());
        values.put(alan3, soz.getIcerik());

        // kaydedilen şarkı sözünün id'si veya hata olustu ise -1 döner
        long kayit=db.insert(TABLO_ADI, null, values);
        db.close();
        return kayit;
    }

    public SarkiSozu idyeGoreSarkiSozuAl(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLO_ADI + " WHERE "
                + alan1 + " = " + id;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null) {
            c.moveToFirst();
            SarkiSozu soz = new SarkiSozu();
            soz.setId(c.getInt(c.getColumnIndex(alan1)));
            soz.setBaslik(c.getString(c.getColumnIndex(alan2)));
            soz.setIcerik(c.getString(c.getColumnIndex(alan3)));
            c.close();
            db.close();
            return soz;
        } else {
            c.close();
            db.close();
            return null;
        }
    }

    public List<SarkiSozu> tumSarkiSozleriniGetir() {
        List<SarkiSozu> lstSarkiSozleri = new ArrayList<SarkiSozu>();
        String selectQuery = "SELECT  * FROM " + TABLO_ADI;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                SarkiSozu soz = new SarkiSozu();
                soz.setId(c.getInt(c.getColumnIndex(alan1)));
                soz.setBaslik(c.getString(c.getColumnIndex(alan2)));
                soz.setIcerik(c.getString(c.getColumnIndex(alan3)));
                lstSarkiSozleri.add(soz);
            } while (c.moveToNext());
            c.close();
            db.close();
        }else {
            c.close();
            db.close();
        }
        return lstSarkiSozleri;
    }

    public int sarkiSozuBilgisiGuncelle(SarkiSozu soz) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(alan1, soz.getId());
        values.put(alan2, soz.getBaslik());
        values.put(alan3, soz.getIcerik());
        // güncellenen satır sayısı
        int guncellenenSatirSayisi=db.update(TABLO_ADI, values, alan1 + " = ?",
                new String[]{String.valueOf(soz.getId())});
        db.close();
        return guncellenenSatirSayisi ;
    }

    public void sarkiSozuSil(Integer sozId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLO_ADI, alan1 + " = ?",
                new String[]{String.valueOf(sozId)});
        db.close();
    }

}
