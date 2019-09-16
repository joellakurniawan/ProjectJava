/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectjava.Controller;

/**
 *
 * @author JOELLA
 */
public class addpembelian_class {
    private int id;
    private int id_item;
    private String nama_item;
    private int jumlah;
    private int harga_satuan;
    private int total;

    public addpembelian_class(int id, int id_item, String nama_item, int jumlah, int harga_satuan, int total) {
        this.id = id;
        this.id_item = id_item;
        this.nama_item = nama_item;
        this.jumlah = jumlah;
        this.harga_satuan = harga_satuan;
        this.total = total;
    }

    public addpembelian_class() {
    }

    public int getId() {
        return id;
    }

    public int getId_item() {
        return id_item;
    }

    public String getNama_item() {
        return nama_item;
    }

    public int getJumlah() {
        return jumlah;
    }

    public int getHarga_satuan() {
        return harga_satuan;
    }

    public int getTotal() {
        return total;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public void setNama_item(String nama_item) {
        this.nama_item = nama_item;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public void setHarga_satuan(int harga_satuan) {
        this.harga_satuan = harga_satuan;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
}
