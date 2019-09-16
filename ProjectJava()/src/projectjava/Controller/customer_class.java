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
public class customer_class {
    private SimpleStringProperty ID;
    private SimpleStringProperty nama;
    private SimpleStringProperty alamat;
    private SimpleStringProperty notlp;
    private SimpleStringProperty email;

    public customer_class(String ID, String nama, String alamat, String notlp, String email) {
        this.ID = new SimpleStringProperty(ID);
        this.nama = new SimpleStringProperty(nama);
        this.alamat = new SimpleStringProperty(alamat);
        this.notlp = new SimpleStringProperty(notlp);
        this.email = new SimpleStringProperty(email);
    }

    public customer_class() {
    }

    public String getID() {
        return ID.get();
    }

    public String getNama() {
        return nama.get();
    }

    public String getAlamat() {
        return alamat.get();
    }

    public String getNotlp() {
        return notlp.get();
    }

    public String getEmail() {
        return email.get();
    }

    public void setID(SimpleStringProperty ID) {
        this.ID = ID;
    }

    public void setNama(SimpleStringProperty nama) {
        this.nama = nama;
    }

    public void setAlamat(SimpleStringProperty alamat) {
        this.alamat = alamat;
    }

    public void setNotlp(SimpleStringProperty notlp) {
        this.notlp = notlp;
    }

    public void setEmail(SimpleStringProperty email) {
        this.email = email;
    }
    
    
}
