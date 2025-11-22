/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.Audiobook;
import core.Author;
import core.Book;
import core.DigitalBook;
import core.PrintedBook;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.storage.Storage;
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
            
            Storage storage = Storage.getInstance();
            
            if (authorId.equals("Seleccione uno...")){
                Response response = new Response("Select a book author", Status.BAD_REQUEST);
                JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            Author author = null;
            for (Author auth : storage.getAutores()) {
                if (String.valueOf(auth.getId()).equals( authorId)) {
                    author = auth;
                }
            }
            
            for (Book book : storage.getLibros()) { 
                String authors = book.getAuthors().get(0).getFullname();
                for (int i = 1; i < book.getAuthors().size(); i++) {
                    authors += (", " + book.getAuthors().get(i).getFullname());
                }
                if (book instanceof PrintedBook printedBook) {
                    model.addRow(new Object[]{printedBook.getTitle(), authors, printedBook.getIsbn(), printedBook.getGenre(), printedBook.getFormat(), printedBook.getValue(), printedBook.getPublisher().getName(), printedBook.getCopies(), printedBook.getPages(), "-", "-", "-"});
                }
                if (book instanceof DigitalBook digitalBook) {
                    model.addRow(new Object[]{digitalBook.getTitle(), authors, digitalBook.getIsbn(), digitalBook.getGenre(), digitalBook.getFormat(), digitalBook.getValue(), digitalBook.getPublisher().getName(), "-", "-", digitalBook.hasHyperlink() ? digitalBook.getHyperlink() : "No", "-", "-"});
                }
                if (book instanceof Audiobook audiobook) {
                    model.addRow(new Object[]{audiobook.getTitle(), authors, audiobook.getIsbn(), audiobook.getGenre(), audiobook.getFormat(), audiobook.getValue(), audiobook.getPublisher().getName(), "-", "-", "-", audiobook.getNarrador().getFullname(), audiobook.getDuration()});
                }
            }
            
            
            }catch(Exception ex){
                Response response = new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
                JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }
}
