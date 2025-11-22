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
            Storage storage = Storage.getInstance();
            for (Author author : storage.getAutores()) {
                model.addRow(new Object[]{author.getId(), author.getFullname(), "Autor", "-", author.getBookQuantity()});
            }
            for (Manager manager : storage.getGerentes()) {
                if (manager.getPublisher() == null){
                    model.addRow(new Object[]{manager.getId(), manager.getFullname(), "Gerente", "-", 0});
                }else{
                model.addRow(new Object[]{manager.getId(), manager.getFullname(), "Gerente", manager.getPublisher().getName(), 0});
                }
            }
            for (Narrator narrator : storage.getNarradores()) {
                model.addRow(new Object[]{narrator.getId(), narrator.getFullname(), "Narrador", "-", narrator.getBookQuantity()});
            }
            }catch(Exception ex){
                Response response = new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
                JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
}
