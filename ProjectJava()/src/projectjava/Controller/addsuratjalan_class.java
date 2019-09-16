/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectjava.Controller;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author JOELLA
 */
public class addsuratjalan_class {
    private int id;
    private String nama_barang;
    private int harga_satuan;
    private int jumlah;

    public addsuratjalan_class(int id, String nama_barang, int harga_satuan, int jumlah) {
        this.id = id;
        this.nama_barang = nama_barang;
        this.harga_satuan = harga_satuan;
        this.jumlah = jumlah;
    }

    public addsuratjalan_class() {
    }


    public int getId() {
        return id;
    }


    public String getNama_barang() {
        return nama_barang;
    }

    public int getHarga_satuan() {
        return harga_satuan;
    }

    public int getJumlah() {
        return jumlah;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public void setHarga_satuan(int harga_satuan) {
        this.harga_satuan = harga_satuan;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    
    
}
