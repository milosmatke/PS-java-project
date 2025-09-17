/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form.components;

import controller.ClientController;
import domain.Knjiga;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class TableModelKnjiga extends AbstractTableModel{

    private List<Knjiga> listaKnjiga;
    private String[] columnNames=new String[]{"Naslov","Autor","Zanr","Izdavac", "God. izd","Na stanju"};
    private String parametar="";

    public TableModelKnjiga() {
        try {
            listaKnjiga=ClientController.getInstance().getAllKnjiga();
        } catch (Exception ex) {
            Logger.getLogger(TableModelKnjiga.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setListaKnjiga(List<Knjiga> listaKnjiga) {
        this.listaKnjiga = listaKnjiga;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        if(listaKnjiga==null){
            return 0;
        }else{
            return listaKnjiga.size();
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
        Knjiga knjiga=listaKnjiga.get(rowIndex);
        switch(columnIndex){
            case 0:
                return knjiga.getNaslov();
            case 1:
                return knjiga.getAutor().toString();
            case 2:
                return knjiga.getZanr();
            case 3:
                return knjiga.getIzdavac();
            case 4:
                return knjiga.getGodinaIzdanja();
            case 5:
               
                return knjiga.getKolicina();
            default:
                return "n/a";
        }
    }
    public Knjiga getKnjiga(int rowIndex) {
        return listaKnjiga.get(rowIndex);
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            listaKnjiga=ClientController.getInstance().getAllKnjiga();
            if(!parametar.equals("")){
                ArrayList<Knjiga> novaLista=new ArrayList<>();
                for (Knjiga knjiga : listaKnjiga) {
                    if(knjiga.getNaslov().toLowerCase().contains(parametar.toLowerCase()) 
                            || knjiga.getAutor().getIme().toLowerCase().contains(parametar.toLowerCase())||
                            knjiga.getAutor().getPrezime().toLowerCase().contains(parametar.toLowerCase())){
                        novaLista.add(knjiga);
                    }
                }
                listaKnjiga=novaLista;
            }
            fireTableDataChanged();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void smanjiKolicinu(int selectedRow) {
        int staraKolicina=listaKnjiga.get(selectedRow).getKolicina();
        if(staraKolicina==0){
            return;
        }
        int novaKolicina=staraKolicina-1;
        listaKnjiga.get(selectedRow).setKolicina(novaKolicina);
        fireTableDataChanged();
    }

//    public void povecajKolicinu(Knjiga knjiga) {
//        for (Knjiga k : listaKnjiga) {
//            if(k.getId().equals(knjiga.getId())){
//                int novaKolicina=knjiga.getKolicina()+1;
//                knjiga.setKolicina(novaKolicina);
//                fireTableDataChanged();
//            }
//        }
//    }
    
    public void povecajKolicinu(Knjiga knjiga) {
    for (Knjiga k : listaKnjiga) {
        if(k.getId().equals(knjiga.getId())){
            int novaKolicina = k.getKolicina() + 1;  // menjaj k, ne knjiga
            k.setKolicina(novaKolicina);
            fireTableDataChanged();
            break;
        }
    }
}
    
}
