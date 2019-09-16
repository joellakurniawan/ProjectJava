/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectjava.Controller;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author tlabe
 */
public class item_class {
    private SimpleStringProperty ID;
    private SimpleStringProperty nama;
    private SimpleStringProperty merk;
    private int stock;
    private int harga_pokok;
    private int harga_jual;
    private String id_lokasi;

    public item_class(String ID, String nama, String merk, int stock, int harga_pokok, int harga_jual, String id_lokasi) {
        this.ID = new SimpleStringProperty(ID);
        this.nama = new SimpleStringProperty(nama);
        this.merk = new SimpleStringProperty(merk);
        this.stock = stock;
        this.harga_pokok = harga_pokok;
        this.harga_jual = harga_jual;
        this.id_lokasi = id_lokasi;
    }

    public item_class() {
    }



    public String getID() {
        return ID.get();
    }

    public String getNama() {
        return nama.get();
    }

    public String getMerk() {
        return merk.get();
    }

    public int getStock() {
        return stock;
    }

    public int getHarga_pokok() {
        return harga_pokok;
    }

    public int getHarga_jual() {
        return harga_jual;
    }

    public void setID(SimpleStringProperty ID) {
        this.ID = ID;
    }

    public void setNama(SimpleStringProperty nama) {
        this.nama = nama;
    }

    public void setMerk(SimpleStringProperty merk) {
        this.merk = merk;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setHarga_pokok(int harga_pokok) {
        this.harga_pokok = harga_pokok;
    }

    public void setHarga_jual(int harga_jual) {
        this.harga_jual = harga_jual;
    }

    public String getId_lokasi() {
        return id_lokasi;
    }

    public void setId_lokasi(String id_lokasi) {
        this.id_lokasi = id_lokasi;
    }

    
    
}
