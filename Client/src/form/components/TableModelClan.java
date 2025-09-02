/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form.components;

import controller.ClientController;
import domain.Clan;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class TableModelClan extends AbstractTableModel{
    private List<Clan> listaClanova;
    private String[] columnNames=new String[]{"Ime","Prezime","Email","Kontakt telefon","Datum isteka clanarine", "Status"};
    private String parametar="";
    
    public TableModelClan(){
        try {
            listaClanova=ClientController.getInstance().getAllClan();
        } catch (Exception ex) {
            Logger.getLogger(TableModelClan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setListaClanova(List<Clan> listaClanova) {
        this.listaClanova = listaClanova;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        if(listaClanova==null){
            return 0;
        }else{
            return listaClanova.size();
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
        Clan clan=listaClanova.get(rowIndex);
        switch(columnIndex){
            case 0:
                return clan.getIme();
            case 1:
                return clan.getPrezime();
            case 2:
                return clan.getEmail();
            case 3:
                return clan.getTelefon();
            case 4:
                return clan.getDatumIsteka();
            case 5:
                return clan.getStatus();
            default:
                return "n/a";
        }
    }
        
        public Clan getClan(int rowIndex) {
        return listaClanova.get(rowIndex);
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }
    

    public void refreshTable() {
        try {
            listaClanova=ClientController.getInstance().getAllClan();
            if(!parametar.equals("")){
                ArrayList<Clan> novaLista=new ArrayList<>();
                for (Clan c:listaClanova) {
                    if(c.getIme().toLowerCase().contains(parametar.toLowerCase()) 
                            || c.getPrezime().toLowerCase().contains(parametar.toLowerCase())){
                        novaLista.add(c);
                    }
                }
                listaClanova=novaLista;
            }
            fireTableDataChanged();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Clan getListItem(int selectedRow) {
        return listaClanova.get(selectedRow);
    }
    
}
