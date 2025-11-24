/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.strategies;

import core.Author;
import core.DigitalBook;
import core.Publisher;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.dto.BookRequestDTO;
import core.models.storage.IMegaferiaStorage;
import java.util.ArrayList;

/**
 * Estrategia para crear libros digitales.
 * Maneja validación específica y lógica de creación para libros digitales.
 */
public class DigitalBookStrategy implements IBookCreationStrategy {

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

            if (publisher == null) {
                return new Response("Publisher with NIT " + request.getPublisherNit() + " not found",
                        Status.BAD_REQUEST);
            }

            DigitalBook digitalBook = new DigitalBook(
                    request.getTitle(),
                    authors,
                    request.getIsbn(),
                    request.getGenre(),
                    request.getFormat(),
                    request.getValue(),
                    publisher,
                    request.getHyperlink());

            if (!storage.addDigitalBook(digitalBook)) {
                return new Response("A digital book with that ISBN already exists", Status.BAD_REQUEST);
            }

            return new Response("Digital book created successfully", Status.CREATED);

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
