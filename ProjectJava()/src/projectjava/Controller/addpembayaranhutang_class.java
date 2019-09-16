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
public class addpembayaranhutang_class {
    private int id;
    private int id_notapembelian;
    private int saldo_hutang;
    private int pembayaran;

    public addpembayaranhutang_class(int id, int id_notapembelian, int saldo_hutang, int pembayaran) {
        this.id = id;
        this.id_notapembelian = id_notapembelian;
        this.saldo_hutang = saldo_hutang;
        this.pembayaran = pembayaran;
    }

    public addpembayaranhutang_class() {
    }

    public int getId() {
        return id;
    }

    public int getId_notapembelian() {
        return id_notapembelian;
    }

    public int getSaldo_hutang() {
        return saldo_hutang;
    }

    public int getPembayaran() {
        return pembayaran;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_notapembelian(int id_notapembelian) {
        this.id_notapembelian = id_notapembelian;
    }

    public void setSaldo_hutang(int saldo_hutang) {
        this.saldo_hutang = saldo_hutang;
    }

    public void setPembayaran(int pembayaran) {
        this.pembayaran = pembayaran;
    }
    
    
}
