/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.strategies;

import core.Author;
import core.Narrator;
import core.PrintedBook;
import core.Publisher;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.dto.BookRequestDTO;
import core.models.storage.IMegaferiaStorage;
import java.util.ArrayList;

/**
 * Estrategia para crear libros impresos.
 * Maneja validación específica y lógica de creación para libros impresos.
 */
public class PrintedBookStrategy implements IBookCreationStrategy {

    @Override
    public Response createBook(BookRequestDTO request, IMegaferiaStorage storage) {
        try {
            ArrayList<Author> authors = new ArrayList<>();
            for (String authorId : request.getAuthorIds()) {
                long id = Long.parseLong(authorId);
                Author author = findAuthorById(id, storage);
                if (author == null) {
                    return new Response("Author with ID " + id + " not found", Status.BAD_REQUEST);
                }
                authors.add(author);
            }

            Publisher publisher = findPublisherByNit(request.getPublisherNit(), storage);
            if (publisher == null) {
                return new Response("Publisher with NIT " + request.getPublisherNit() + " not found",
                        Status.BAD_REQUEST);
            }

            PrintedBook printedBook = new PrintedBook(
                    request.getTitle(),
                    authors,
                    request.getIsbn(),
                    request.getGenre(),
                    request.getFormat(),
                    request.getValue(),
                    publisher,
                    request.getPages(),
                    request.getCopies());

            if (!storage.addPrintedBook(printedBook)) {
                return new Response("A printed book with that ISBN already exists", Status.BAD_REQUEST);
            }

            return new Response("Printed book created successfully", Status.CREATED);

        } catch (Exception ex) {
            return new Response("Unexpected error: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    private Author findAuthorById(long id, IMegaferiaStorage storage) {
        for (Author author : storage.getAutores()) {
            if (author.getId() == id) {
                return author;
            }
        }
        return null;
    }

    private Publisher findPublisherByNit(String nit, IMegaferiaStorage storage) {
        for (Publisher publisher : storage.getEditoriales()) {
            if (publisher.getNit().equals(nit)) {
                return publisher;
            }
        }
        return null;
    }
}
