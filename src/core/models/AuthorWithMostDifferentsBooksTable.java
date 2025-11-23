/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.Author;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dandr
 */
public class AuthorWithMostDifferentsBooksTable {
    public static void updateAuthorWithMostDifferentsBooksM(DefaultTableModel model, ArrayList<Author> authorsMax, int maxPublishers){
            
            for (Author author : authorsMax) {
                model.addRow(new Object[]{author.getId(), author.getFullname(), maxPublishers});
            }
    }
}
