package com.example.absensi;

public class ListModelSiswa {
    String nama,kehadiran,jk,kelas,keterangan,nis;

    public ListModelSiswa() {
    }

    public ListModelSiswa(String nama, String kehadiran, String jk, String kelas, String keterangan, String nis) {
        this.nama = nama;
        this.kehadiran = kehadiran;
        this.jk = jk;
        this.kelas = kelas;
        this.keterangan = keterangan;
        this.nis = nis;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKehadiran() {
        return kehadiran;
    }

    public void setKehadiran(String kehadiran) {
        this.kehadiran = kehadiran;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }
}
