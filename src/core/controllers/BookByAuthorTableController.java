/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import static core.models.BookByAuthorTable.updateBookByAuthorTableM;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dandr
 */
public class BookByAuthorTableController {
    
    public static void updateBookByAuthorTable(DefaultTableModel model, String[] authorData){
        try{         
            String authorId = authorData[0];

            
            if (authorId.equals("Seleccione uno...")){
                Response response = new Response("Select a book author", Status.BAD_REQUEST);
                JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            updateBookByAuthorTableM(model, authorId);
            }catch(Exception ex){
                Response response = new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
                JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }
}
