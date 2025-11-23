/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.Publisher;
import core.models.storage.Storage;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dandr
 */
public class EditorialTable {
    public static void updateEditorialTableM(DefaultTableModel model){
        
            Storage storage = Storage.getInstance();
            for (Publisher publisher : storage.getEditoriales()) {
                model.addRow(new Object[]{publisher.getNit(), publisher.getName(), publisher.getAddress(), publisher.getManager().getFullname(), publisher.getStandQuantity()});
            }
            
        
    }
}
