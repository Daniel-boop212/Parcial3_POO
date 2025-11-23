/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.Author;
import core.DigitalBook;
import core.Narrator;
import core.Publisher;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.storage.Storage;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author dandr
 */
public class CreateDigitalBook implements BookService{
    
    @Override
    public Response createBook(String title, ArrayList<Author> authors, String isbn, String genre, String format, double valueDou, Publisher publisher, int pages, int copies, String hyperlink, int duration, Narrator narrator){
        try{  
            Storage storage = Storage.getInstance();
            if (hyperlink.equals("")){
                if (!storage.addDigitalBook(new DigitalBook(title, authors, isbn, genre, format, valueDou, publisher))) {
                    return new Response("A digital book with that isbn already exists", Status.BAD_REQUEST);
                }
                return new Response("Digital book created successfully", Status.CREATED);
            }else{
                if (!storage.addDigitalBook(new DigitalBook(title, authors, isbn, genre, format, valueDou, publisher, hyperlink))) {
                    return new Response("A digital book with that isbn already exists", Status.BAD_REQUEST);
                }
                return new Response("Digital book created successfully", Status.CREATED); 
            }
            
        }catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
}
