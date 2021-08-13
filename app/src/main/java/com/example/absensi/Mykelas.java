package com.example.absensi;

public class Mykelas {
    String kelas,id_absen,tgl_absen;

    public Mykelas(){}

    public Mykelas(String kelas, String id_absen, String tgl_absen) {
        this.kelas = kelas;
        this.id_absen = id_absen;
        this.tgl_absen = tgl_absen;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getId_absen() {
        return id_absen;
    }

    public void setId_absen(String id_absen) {
        this.id_absen = id_absen;
    }

    public String getTgl_absen() {
        return tgl_absen;
    }

    public void setTgl_absen(String tgl_absen) {
        this.tgl_absen = tgl_absen;
    }
}
