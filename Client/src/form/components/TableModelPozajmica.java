/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form.components;

import controller.ClientController;
import domain.Clan;
import domain.Pozajmica;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class TableModelPozajmica extends AbstractTableModel implements Runnable{
    
    private List<Pozajmica> listaPozajmica;
    private String[] columnNames=new String[]{"Korisnik","Iznajmljeno","Rok zaduzenja"};
    private String parametar="";

    public TableModelPozajmica(){
        try {
            listaPozajmica=ClientController.getInstance().getAllPozajmica();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setListaClanova(List<Clan> listaClanova) {
        this.listaPozajmica = listaPozajmica;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        if(listaPozajmica==null){
            return 0;
        }else{
            return listaPozajmica.size();
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
        Pozajmica p=listaPozajmica.get(rowIndex);
        switch(columnIndex){
            case 0:
                return p.getClan();
            case 1:
                return p.getDatumIzdavanja();
            case 2:
                return p.getRokVracanja();
            default:
                return "n/a";
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(5000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelClan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletePozajmica(int rowIndex) {
        try {
            Pozajmica p=listaPozajmica.get(rowIndex);
            ClientController.getInstance().deletePozajmica(p);
        } catch (Exception ex) {
            
            Logger.getLogger(TableModelPozajmica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Pozajmica getPozajmica(int rowIndex) {
        return listaPozajmica.get(rowIndex);
    }
    
    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            listaPozajmica=ClientController.getInstance().getAllPozajmica();
            if(!parametar.equals("")){
                ArrayList<Pozajmica> novaLista=new ArrayList<>();
                for (Pozajmica p : listaPozajmica) {
                    if(p.getClan().getIme().toLowerCase().contains(parametar.toLowerCase()) 
                            || p.getClan().getPrezime().toLowerCase().contains(parametar.toLowerCase())){
                        novaLista.add(p);
                    }
                }
                listaPozajmica=novaLista;
            }
            fireTableDataChanged();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
