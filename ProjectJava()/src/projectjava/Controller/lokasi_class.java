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
public class lokasi_class {
    private int id;
    private SimpleStringProperty nama;

    public lokasi_class(int id, String nama) {
        this.id = id;
        this.nama = new SimpleStringProperty(nama);
    }

    public lokasi_class() {
    }

    public int getId() {
        return id;
    }

    public SimpleStringProperty getNama() {
        return nama;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNama(SimpleStringProperty nama) {
        this.nama = nama;
    }
    
}
