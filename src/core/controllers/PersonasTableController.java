/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.Author;
import core.Manager;
import core.Narrator;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import static core.models.PersonasTable.updatePersonasTableM;
import core.models.storage.Storage;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dandr
 */
public class PersonasTableController {
    
    public static void updatePersonasTable(DefaultTableModel model){
        try{
            updatePersonasTableM(model);
            }catch(Exception ex){
                Response response = new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
                JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
}
