package com.bsv.sarkisozleri;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class adapterSarkiSozleri extends BaseAdapter implements Filterable {

    Context ctx;
    List<SarkiSozu> lstSarkiSozleri;
    List<SarkiSozu> lstFiltreSarkiSozleri;
    Activity activity;

    public adapterSarkiSozleri(Context ctx, List<SarkiSozu> lstSarkiSozleri, Activity activity) {
        this.ctx = ctx;
        this.lstSarkiSozleri = lstSarkiSozleri;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        int sonuc=0;
        if (lstSarkiSozleri!=null){
            sonuc=lstSarkiSozleri.size();
        }
        return sonuc;
    }

    @Override
    public Object getItem(int position){
        return lstSarkiSozleri.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    int siraNo=1;
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView= LayoutInflater.from(ctx).inflate(R.layout.listeelemanlayout,parent,false);

        LinearLayout sarkiSozuSatir=convertView.findViewById(R.id.sarkiSozuSatir);
        TextView lblListeId=convertView.findViewById(R.id.lblListeId);
        TextView lblListeSiraNo=convertView.findViewById(R.id.lblListeSiraNo);
        TextView lblListeSarkiBaslik=convertView.findViewById(R.id.lblListeSarkiBaslik);

        final int idD=lstSarkiSozleri.get(position).getId();
        final String baslikD=lstSarkiSozleri.get(position).getBaslik();

        lblListeId.setText(String.valueOf(idD));
        lblListeSiraNo.setText(String.valueOf(siraNo));
        siraNo++;
        lblListeSarkiBaslik.setText(lstSarkiSozleri.get(position).getBaslik());

        sarkiSozuSatir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ctx,SarkiGosterActivity.class);
                DatabaseHelper helper=new DatabaseHelper(ctx);
                SarkiSozu soz=new SarkiSozu();
                soz=helper.idyeGoreSarkiSozuAl(idD);
                helper.veritabaniniKapat();
                helper.close();
                intent.putExtra("id",soz.getId());
                intent.putExtra("baslik",soz.getBaslik());
                intent.putExtra("icerik",soz.getIcerik());
                activity.startActivity(intent);
            }
        });

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
