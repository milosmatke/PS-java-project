/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form.components;

import domain.Knjiga;
import domain.StavkaPozajmice;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class TableModelStavke extends AbstractTableModel{

    private List<StavkaPozajmice> listaStavki;
    private String[] columnNames=new String[]{"RB","Knjiga"};
    private int rb=0;

    public TableModelStavke() {
        listaStavki=new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        if(listaStavki==null){
            return 0;
        }else{
            return listaStavki.size();
        }
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        if(column>columnNames.length){
            return "n/a";
        }else{
            return columnNames[column];
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaPozajmice sp=listaStavki.get(rowIndex);
        switch(columnIndex){
            case 0:
                return sp.getRbStavke();
            case 1:
                return sp.getKnjiga();
            default:
                return "n/a";
        }
    }

    public boolean postojiKnjiga(Knjiga knjiga) {
        
        for (StavkaPozajmice stavka : listaStavki) {
            if(stavka.getKnjiga().equals(knjiga)){
                System.out.println(stavka.getKnjiga());
                return true;
            }
        }
        return false;
    }

    public void dodajStavku(StavkaPozajmice stavka) {
        rb=listaStavki.size();
        stavka.setRbStavke(++rb);
        listaStavki.add(stavka);
        fireTableDataChanged();
    }

    public void deleteStavka(int rowIndex) {
        listaStavki.remove(rowIndex);
        rb=0;
        for (StavkaPozajmice stavka : listaStavki) {
            stavka.setRbStavke(++rb);
        }
        fireTableDataChanged();
    }

    public List<StavkaPozajmice> getListaStavki() {
        return listaStavki;
    }
    
    public StavkaPozajmice getStavkaPozajmice(int rowIndex){
        return listaStavki.get(rowIndex);
    }

    public void setListaStavki(List<StavkaPozajmice> listaStavki) {
        this.listaStavki = listaStavki;
        fireTableDataChanged();
    }
    
}
