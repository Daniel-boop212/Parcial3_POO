/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.dto;

import java.util.ArrayList;

/**
 * Objeto de Transferencia de Datos para solicitudes de creación de libros.
 * Encapsula todos los parámetros posibles para crear diferentes tipos de
 * libros.
 * 
 */
public class BookRequestDTO {

    private String title;
    private ArrayList<String> authorIds;
    private String isbn;
    private String genre;
    private String format;
    private double value;
    private String publisherNit;

    // Campos de PrintedBook
    private int pages;
    private int copies;

    // Campos de DigitalBook
    private String hyperlink;

    // Campos de AudioBook
    private int duration;
    private String narratorId;

    private boolean isPrinted;
    private boolean isDigital;
    private boolean isAudio;

    public BookRequestDTO() {
        this.authorIds = new ArrayList<>();
    }

    // Getters y Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(ArrayList<String> authorIds) {
        this.authorIds = authorIds;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getPublisherNit() {
        return publisherNit;
    }

    public void setPublisherNit(String publisherNit) {
        this.publisherNit = publisherNit;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getNarratorId() {
        return narratorId;
    }

    public void setNarratorId(String narratorId) {
        this.narratorId = narratorId;
    }

    public boolean isPrinted() {
        return isPrinted;
    }

    public void setPrinted(boolean printed) {
        isPrinted = printed;
    }

    public boolean isDigital() {
        return isDigital;
    }

    public void setDigital(boolean digital) {
        isDigital = digital;
    }

    public boolean isAudio() {
        return isAudio;
    }

    public void setAudio(boolean audio) {
        isAudio = audio;
    }
}
