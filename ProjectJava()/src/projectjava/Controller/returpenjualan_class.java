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
public class returpenjualan_class {
    private int id;
    private String nama_customer;
    private Date tanggal;
    private int total;
    private int id_notapenjualan;

    public returpenjualan_class(int id, String nama_customer, Date tanggal, int total, int id_notapenjualan) {
        this.id = id;
        this.nama_customer = nama_customer;
        this.tanggal = tanggal;
        this.total = total;
        this.id_notapenjualan = id_notapenjualan;
    }

    public void setId_notapenjualan(int id_notapenjualan) {
        this.id_notapenjualan = id_notapenjualan;
    }

    public int getId_notapenjualan() {
        return id_notapenjualan;
    }

    public returpenjualan_class() {
    }

    public int getId() {
        return id;
    }

    public String getNama_customer() {
        return nama_customer;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public int getTotal() {
        return total;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNama_customer(String nama_customer) {
        this.nama_customer = nama_customer;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
    
}
