/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.Author;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import static core.models.AuthorWithMostDifferentsBooksTable.updateAuthorWithMostDifferentsBooksM;
import core.models.storage.Storage;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dandr
 */
public class AuthorWithMostDifferentsBooksTableController {
    
    public static void updateAuthorWithMostDifferentsBooks(DefaultTableModel model){
        try{
            Storage storage = Storage.getInstance();
            
            ArrayList<Author> authorsMax = new ArrayList<>();
            int maxPublishers = -1;
            for (Author author : storage.getAutores()) {
                if (author.getPublisherQuantity() > maxPublishers) {
                    maxPublishers = author.getPublisherQuantity();
                    authorsMax.clear();
                    authorsMax.add(author);
                } else if (author.getPublisherQuantity() == maxPublishers) {
                    authorsMax.add(author);
                }
            }
            if (authorsMax.isEmpty()){
                Response response = new Response("There`s no authors", Status.NOT_FOUND);
                JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);      
                return;
            }
            updateAuthorWithMostDifferentsBooksM(model, authorsMax, maxPublishers);
            }catch(Exception ex){
                Response response = new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
                JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
