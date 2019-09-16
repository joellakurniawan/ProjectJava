/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectjava.Controller;

import java.sql.Date;

/**
 *
 * @author JOELLA
 */
public class suratjalan_class {
    private int id;
    private Date tanggal;
    private int lokasi_awal;
    private int lokasi_tujuan;

    public suratjalan_class(int id, Date tanggal, int lokasi_awal, int lokasi_tujuan) {
        this.id = id;
        this.tanggal = tanggal;
        this.lokasi_awal = lokasi_awal;
        this.lokasi_tujuan = lokasi_tujuan;
    }

    public suratjalan_class() {
    }

    public int getId() {
        return id;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public int getLokasi_awal() {
        return lokasi_awal;
    }

    public int getLokasi_tujuan() {
        return lokasi_tujuan;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public void setLokasi_awal(int lokasi_awal) {
        this.lokasi_awal = lokasi_awal;
    }

    public void setLokasi_tujuan(int lokasi_tujuan) {
        this.lokasi_tujuan = lokasi_tujuan;
    }
    
    
}
