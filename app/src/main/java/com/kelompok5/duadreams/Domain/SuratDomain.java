package com.kelompok5.duadreams.Domain;

import java.io.Serializable;

public class SuratDomain implements Serializable {
    String nomor_ayat, arabic, terjemahan;

    public String getNomor_ayat() {
        return nomor_ayat;
    }

    public void setNomor_ayat(String nomor_ayat) {
        this.nomor_ayat = nomor_ayat;
    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public String getTerjemahan() {
        return terjemahan;
    }

    public void setTerjemahan(String terjemahan) {
        this.terjemahan = terjemahan;
    }
}
