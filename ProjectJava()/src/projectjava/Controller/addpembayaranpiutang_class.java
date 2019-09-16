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
public class addpembayaranpiutang_class {
    private int id;
    private int id_notapenjualan;
    private int saldo_piutang;
    private int pembayaran;

    public addpembayaranpiutang_class(int id, int id_notapenjualan, int saldo_piutang, int pembayaran) {
        this.id = id;
        this.id_notapenjualan = id_notapenjualan;
        this.saldo_piutang = saldo_piutang;
        this.pembayaran = pembayaran;
    }

    public addpembayaranpiutang_class() {
    }

    public int getId() {
        return id;
    }

    public int getId_notapenjualan() {
        return id_notapenjualan;
    }

    public int getSaldo_piutang() {
        return saldo_piutang;
    }

    public int getPembayaran() {
        return pembayaran;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_notapenjualan(int id_notapenjualan) {
        this.id_notapenjualan = id_notapenjualan;
    }

    public void setSaldo_piutang(int saldo_piutang) {
        this.saldo_piutang = saldo_piutang;
    }

    public void setPembayaran(int pembayaran) {
        this.pembayaran = pembayaran;
    }
    
    
}
