/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.Publisher;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.storage.Storage;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dandr
 */
public class EditorialTableController {
    
    public static void updateEditorialTable(DefaultTableModel model){
        try{
            Storage storage = Storage.getInstance();
            for (Publisher publisher : storage.getEditoriales()) {
                model.addRow(new Object[]{publisher.getNit(), publisher.getName(), publisher.getAddress(), publisher.getManager().getFullname(), publisher.getStandQuantity()});
            }
            }catch(Exception ex){
                Response response = new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
                JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
            
}
