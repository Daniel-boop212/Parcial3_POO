/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.Author;
import core.Narrator;
import core.Publisher;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.CreateAudioBook;
import core.models.CreateDigitalBook;
import core.models.CreatePrintedBook;
import core.models.storage.Storage;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author dandr
 */
public class BookControler {
    
    private static final Pattern CUSTOM_CODE_PATTERN = Pattern.compile("^\\d{3}-\\d-\\d{2}-\\d{6}-\\d$");
   
    public static Response createBook(String title, String[] authorsData, String isbn, String genre, String format, String value, String publisherData, boolean impreso, boolean digital, boolean audioBook, String pages, String copies, String hyperlink, String duration, String[] narratorData){
        if (!CUSTOM_CODE_PATTERN.matcher(isbn).matches()) {
                return new Response("ISBN must follow XXX-X-XX-XXXXXX-X format.", Status.BAD_REQUEST);
            }
            
            Narrator narrator = null;
            double valueDou; 
            String publisherNit;
            try{
                valueDou = Double.parseDouble(value);
                if (valueDou < 0) {
                    return new Response("price must be positive", Status.BAD_REQUEST);
                }
            }catch (NumberFormatException ex) {
                return new Response("price must be numeric", Status.BAD_REQUEST);
            }
            
            if (publisherData.trim().equals("Seleccione uno...")){
                return new Response("select a publisher", Status.BAD_REQUEST);
            }
           
            publisherNit = publisherData.split(" ")[1].replace("(", "").replace(")", "");
            
            if (isbn.trim().equals("")) {
                return new Response("isbn must be not empty", Status.BAD_REQUEST);
            }
            
            if (title.trim().equals("")) {
                return new Response("Title must be not empty", Status.BAD_REQUEST);
            }
            
            if (format.trim().equals("Seleccione uno...")){
                return new Response("select a format", Status.BAD_REQUEST);
            }
            
            if (genre.trim().equals("Seleccione uno...")){
                return new Response("select a genre", Status.BAD_REQUEST);
            }
            
            Storage storage = Storage.getInstance();
            
            if (authorsData.length == 1 && authorsData[0].trim().equals("Seleccione uno...")){
                return new Response("select at least 1 author", Status.BAD_REQUEST);
            }
            
            ArrayList<Author> authors = new ArrayList<>();
            for (String authorData : authorsData) {
                long authorId = Long.parseLong(authorData.split(" - ")[0]);
                for (Author author : storage.getAutores()) {
                    if (author.getId() == authorId) {
                        authors.add(author);
                    }
                }
            }
            
            Publisher publisher = null;
            for (Publisher publish : storage.getEditoriales()) {
                if (publish.getNit().equals(publisherNit)) {
                    publisher = publish;
                }
            }
            
        if (impreso){
            int pagesInt, copiesInt;
            int durationInt = 0;
            try {
                pagesInt = Integer.parseInt(pages.trim());
                if (pagesInt < 0) {
                    return new Response("pages must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("pages must be numeric", Status.BAD_REQUEST);
            }
                
            try {
                copiesInt = Integer.parseInt(copies.trim());
                if(copiesInt < 0){
                    return new Response ("copies must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex){
                return new Response("copies must be numeric", Status.BAD_REQUEST);
            }
            CreatePrintedBook create = new CreatePrintedBook();
            return create.createBook(title, authors, isbn, genre, format, valueDou, publisher, pagesInt, copiesInt, hyperlink, durationInt, narrator);  
        }
        if (digital){
            int pagesInt = 0;
            int copiesInt = 0;
            int durationInt = 0;
            CreateDigitalBook create = new CreateDigitalBook();
            return create.createBook(title, authors, isbn, genre, format, valueDou, publisher, pagesInt, copiesInt, hyperlink, durationInt, narrator);  
        }
        if (audioBook){
            int pagesInt = 0;
            int copiesInt = 0;
            int durationInt;
            try {
                durationInt = Integer.parseInt(duration.trim());
                if (durationInt < 0) {
                    return new Response("duration must be positive", Status.BAD_REQUEST);
                }
                   
                if (narratorData.length == 1 && narratorData[0].trim().equals("Seleccione uno...")){
                    return new Response("select a narrator", Status.BAD_REQUEST);
                }       
                  
                long narratorId = Long.parseLong(narratorData[0]);
                
                for (Narrator narrat : storage.getNarradores()) {
                    if (narrat.getId() == narratorId) {
                        narrator = narrat;
                    }   
                }
            } catch (NumberFormatException ex) {
                return new Response("duration must be numeric", Status.BAD_REQUEST);
            }
            
            CreateAudioBook create = new CreateAudioBook();
            return create.createBook(title, authors, isbn, genre, format, valueDou, publisher, pagesInt, copiesInt, hyperlink, durationInt, narrator);  
        }
        return new Response("Fatal error", Status.INTERNAL_SERVER_ERROR);
    }
}
