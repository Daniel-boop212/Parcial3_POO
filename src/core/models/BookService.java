/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.controllers.utils.Response;

/**
 *
 * @author dandr
 */
public interface BookService{
    
    Response createBook(String title, String[] authorsData, String isbn, String genre, String format, String value, String publisherData, String pages, String copies, String hyperlink, String duration, String[] narratorData);

}
