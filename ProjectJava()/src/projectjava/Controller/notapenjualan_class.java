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
public class notapenjualan_class {
    private int id;
    private String nama_customer;
    private Date tanggal;
    private int total_harga;

    public notapenjualan_class(int id, String nama_customer, Date tanggal, int total_harga) {
        this.id = id;
        this.nama_customer = nama_customer;
        this.tanggal = tanggal;
        this.total_harga = total_harga;
    }

    public notapenjualan_class() {
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

    public int getYear(){
        return tanggal.getYear();
    }
    
    public int getMonth(){
        return tanggal.getMonth();
    }
    
    public int getDate(){
        return tanggal.getDate();
    }
    
    public int getTotal_harga() {
        return total_harga;
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

    public void setTotal_harga(int total_harga) {
        this.total_harga = total_harga;
    }
    
    
}
