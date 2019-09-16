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
public class pembayaranhutang_class {
    private int id;
    private String nama_supplier;
    private Date tanggal;
    private int total_pembayaran;

    public pembayaranhutang_class(int id, String nama_supplier, Date tanggal, int total_pembayaran) {
        this.id = id;
        this.nama_supplier = nama_supplier;
        this.tanggal = tanggal;
        this.total_pembayaran = total_pembayaran;
    }

    public pembayaranhutang_class() {
    }

    public int getId() {
        return id;
    }

    public String getNama_supplier() {
        return nama_supplier;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public int getTotal_pembayaran() {
        return total_pembayaran;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNama_supplier(String nama_supplier) {
        this.nama_supplier = nama_supplier;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public void setTotal_pembayaran(int total_pembayaran) {
        this.total_pembayaran = total_pembayaran;
    }
    
    
}
