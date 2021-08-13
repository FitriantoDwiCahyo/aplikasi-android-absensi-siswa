package com.example.absensi;

public class AdapterListNama {

    String nama_lengkap,nis,url_photo_profile,key,kelas,jk;

    public AdapterListNama() {
    }

    public AdapterListNama(String nama_lengkap, String nis, String url_photo_profile, String key, String kelas, String jk) {
        this.nama_lengkap = nama_lengkap;
        this.nis = nis;
        this.url_photo_profile = url_photo_profile;
        this.key = key;
        this.kelas = kelas;
        this.jk = jk;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getUrl_photo_profile() {
        return url_photo_profile;
    }

    public void setUrl_photo_profile(String url_photo_profile) {
        this.url_photo_profile = url_photo_profile;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }
}
