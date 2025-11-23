/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.CreateAudioBook;
import core.models.CreateDigitalBook;
import core.models.CreatePrintedBook;
import java.util.regex.Pattern;

/**
 *
 * @author dandr
 */
public class BookControler {
    
    private static final Pattern CUSTOM_CODE_PATTERN = Pattern.compile("^\\d{3}-\\d-\\d{2}-\\d{6}-\\d$");
   
    public static Response createBook(String title, String[] authorsData, String isbn, String genre, String format, String value, String publisherData, boolean impreso, boolean digital, boolean audioBook, String pages, String copies, String hyperlink, String duration, String[] narratorData){
        if (impreso){
            CreatePrintedBook create = new CreatePrintedBook();
            return create.createBook(title, authorsData, isbn, genre, format, value, publisherData, pages, copies, hyperlink, duration, narratorData);  
        }
        if (digital){
            CreateDigitalBook create = new CreateDigitalBook();
            return create.createBook(title, authorsData, isbn, genre, format, value, publisherData, pages, copies, hyperlink, duration, narratorData);  
        }
        if (audioBook){
            CreateAudioBook create = new CreateAudioBook();
            return create.createBook(title, authorsData, isbn, genre, format, value, publisherData, pages, copies, hyperlink, duration, narratorData);  
        }
        return new Response("Fatal error", Status.INTERNAL_SERVER_ERROR);
    }
}
