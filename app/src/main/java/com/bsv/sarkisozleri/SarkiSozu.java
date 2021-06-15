package com.bsv.sarkisozleri;

public class SarkiSozu {
    private int id;
    private String baslik;
    private String icerik;

    public SarkiSozu() {
        this.id=-1;
        this.baslik="-";
        this.icerik="-";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }
}
