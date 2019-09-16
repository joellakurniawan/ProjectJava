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
public class returpembelian_class {
    private int id;
    private int id_notapembelian;
    private String nama_supplier;
    private Date tanggal;
    private int total_harga;

    public returpembelian_class(int id, int id_notapembelian, String nama_supplier, Date tanggal, int total_harga) {
        this.id = id;
        this.id_notapembelian = id_notapembelian;
        this.nama_supplier = nama_supplier;
        this.tanggal = tanggal;
        this.total_harga = total_harga;
    }

    public int getId() {
        return id;
    }

    public int getId_notapembelian() {
        return id_notapembelian;
    }

    public String getNama_supplier() {
        return nama_supplier;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public int getTotal_harga() {
        return total_harga;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_notapembelian(int id_notapembelian) {
        this.id_notapembelian = id_notapembelian;
    }

    public void setNama_supplier(String nama_supplier) {
        this.nama_supplier = nama_supplier;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public void setTotal_harga(int total_harga) {
        this.total_harga = total_harga;
    }
    
    
}
