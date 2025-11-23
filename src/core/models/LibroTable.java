/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.Audiobook;
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
public class LibroTable {
    public static void updateBookTableM(DefaultTableModel model, String search){
        Storage storage = Storage.getInstance();
            if (search.equals("Seleccione uno...")){
                Response response = new Response("Select a book type", Status.BAD_REQUEST);
                JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (search.equals("Libros Impresos")) {
                for (Book book : storage.getLibrosImpresos()) {
                    if (book instanceof PrintedBook printedBook) {
                        String authors = printedBook.getAuthors().get(0).getFullname();
                        for (int i = 1; i < printedBook.getAuthors().size(); i++) {
                            authors += (", " + printedBook.getAuthors().get(i).getFullname());
                        }
                        model.addRow(new Object[]{printedBook.getTitle(), authors, printedBook.getIsbn(), printedBook.getGenre(), printedBook.getFormat(), printedBook.getValue(), printedBook.getPublisher().getName(), printedBook.getCopies(), printedBook.getPages(), "-", "-", "-"});
                    }
                }
            }
            if (search.equals("Libros Digitales")) {
                for (Book book : storage.getLibrosDigital()) {
                    if (book instanceof DigitalBook digitalBook) {
                        String authors = digitalBook.getAuthors().get(0).getFullname();
                        for (int i = 1; i < digitalBook.getAuthors().size(); i++) {
                            authors += (", " + digitalBook.getAuthors().get(i).getFullname());
                        }
                        model.addRow(new Object[]{digitalBook.getTitle(), authors, digitalBook.getIsbn(), digitalBook.getGenre(), digitalBook.getFormat(), digitalBook.getValue(), digitalBook.getPublisher().getName(), "-", "-", digitalBook.hasHyperlink() ? digitalBook.getHyperlink() : "No", "-", "-"});
                    }
                }
            }
            if (search.equals("Audiolibros")) {
                for (Book book : storage.getAudioLibros()) {
                    if (book instanceof Audiobook audiobook) {
                        String authors = audiobook.getAuthors().get(0).getFullname();
                        for (int i = 1; i < audiobook.getAuthors().size(); i++) {
                            authors += (", " + audiobook.getAuthors().get(i).getFullname());
                        }
                        model.addRow(new Object[]{audiobook.getTitle(), authors, audiobook.getIsbn(), audiobook.getGenre(), audiobook.getFormat(), audiobook.getValue(), audiobook.getPublisher().getName(), "-", "-", "-", audiobook.getNarrador().getFullname(), audiobook.getDuration()});
                    }
                }
            }
            if (search.equals("Todos los Libros")) {
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
            }
    }
}
