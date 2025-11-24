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
import core.models.dto.BookRequestDTO;
import core.models.factories.BookFactory;
import core.models.storage.IMegaferiaStorage;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Controlador para la creación de libros.
 * Refactorizado como instancia con inyección de dependencias.
 * Utiliza BookFactory para eliminar lógica condicional (OCP).
 */
public class BookController extends BaseController {

    private static final Pattern ISBN_PATTERN = Pattern.compile("^\\d{3}-\\d-\\d{2}-\\d{6}-\\d$");

    /**
     * Constructor que inyecta el almacenamiento.
     * 
     * @param storage El almacenamiento a utilizar
     */
    public BookController(IMegaferiaStorage storage) {
        super(storage);
    }

    /**
     * Crea un libro del tipo especificado.
     * Valida los datos, construye el DTO y delega a la fábrica de libros.
     * 
     * @param title         Título del libro
     * @param authorsData   Array con IDs de autores
     * @param isbn          ISBN del libro
     * @param genre         Género del libro
     * @param format        Formato del libro
     * @param value         Valor del libro
     * @param publisherData Datos del editor
     * @param impreso       Indicador de libro impreso
     * @param digital       Indicador de libro digital
     * @param audioBook     Indicador de audiolibro
     * @param pages         Número de páginas (para libros impresos)
     * @param copies        Número de ejemplares (para libros impresos)
     * @param hyperlink     Hipervínculo (para libros digitales)
     * @param duration      Duración (para audiolibros)
     * @param narratorData  Datos del narrador (para audiolibros)
     * @return Respuesta indicando éxito o fallo
     */
    public Response createBook(String title, String[] authorsData, String isbn, String genre,
            String format, String value, String publisherData, boolean impreso,
            boolean digital, boolean audioBook, String pages, String copies,
            String hyperlink, String duration, String[] narratorData) {

        // Validaciones básicas
        Response validationResponse = validateBasicFields(title, isbn, format, genre,
                value, publisherData, authorsData);
        if (validationResponse != null) {
            return validationResponse;
        }

        // Construir DTO
        BookRequestDTO request = new BookRequestDTO();
        request.setTitle(title);
        request.setIsbn(isbn);
        request.setGenre(genre);
        request.setFormat(format);

        // Parsear valor
        try {
            double valueDou = Double.parseDouble(value);
            if (valueDou <= 0) {
                return new Response("El valor debe ser positivo", Status.BAD_REQUEST);
            }
            request.setValue(valueDou);
        } catch (NumberFormatException ex) {
            return new Response("El valor debe ser numérico", Status.BAD_REQUEST);
        }

        // Extraer NIT del editor
        String publisherNit = publisherData.split(" ")[1].replace("(", "").replace(")", "");
        request.setPublisherNit(publisherNit);

        // Procesar autores
        ArrayList<String> authorIds = new ArrayList<>();
        for (String authorData : authorsData) {
            if (!authorData.trim().isEmpty()) {
                authorIds.add(authorData.split(" - ")[0]);
            }
        }
        request.setAuthorIds(authorIds);

        // Establecer tipo de libro y validar parámetros específicos
        if (impreso) {
            request.setPrinted(true);
            try {
                int pagesInt = Integer.parseInt(pages.trim());
                if (pagesInt <= 0) {
                    return new Response("Las páginas deben ser positivas", Status.BAD_REQUEST);
                }
                int copiesInt = Integer.parseInt(copies.trim());
                if (copiesInt <= 0) {
                    return new Response("Los ejemplares deben ser positivos", Status.BAD_REQUEST);
                }
                request.setPages(pagesInt);
                request.setCopies(copiesInt);
            } catch (NumberFormatException ex) {
                return new Response("Páginas y ejemplares deben ser numéricos", Status.BAD_REQUEST);
            }
        } else if (digital) {
            request.setDigital(true);
            request.setHyperlink(hyperlink);
        } else if (audioBook) {
            request.setAudio(true);
            try {
                int durationInt = Integer.parseInt(duration.trim());
                if (durationInt <= 0) {
                    return new Response("La duración debe ser positiva", Status.BAD_REQUEST);
                }
                request.setDuration(durationInt);
            } catch (NumberFormatException ex) {
                return new Response("La duración debe ser numérica", Status.BAD_REQUEST);
            }

            if (narratorData.length > 0 && !narratorData[0].trim().isEmpty()) {
                request.setNarratorId(narratorData[0]);
            } else {
                return new Response("Se debe seleccionar un narrador para audiolibros", Status.BAD_REQUEST);
            }
        }

        // Delegar a la fábrica
        return BookFactory.createBook(request, storage);
    }

    /**
     * Valida los campos básicos comunes a todos los tipos de libros.
     * 
     * @param title         Título
     * @param isbn          ISBN
     * @param format        Formato
     * @param genre         Género
     * @param value         Valor
     * @param publisherData Datos del editor
     * @param authorsData   Datos de autores
     * @return Response si hay error, null si todo es válido
     */
    private Response validateBasicFields(String title, String isbn, String format, String genre,
            String value, String publisherData, String[] authorsData) {
        if (!ISBN_PATTERN.matcher(isbn).matches()) {
            return new Response("El ISBN debe seguir el formato XXX-X-XX-XXXXXX-X", Status.BAD_REQUEST);
        }

        if (title.trim().isEmpty()) {
            return new Response("El título no debe estar vacío", Status.BAD_REQUEST);
        }

        if (format.trim().equals("Seleccione uno...")) {
            return new Response("Debe seleccionar un formato", Status.BAD_REQUEST);
        }

        if (genre.trim().equals("Seleccione uno...")) {
            return new Response("Debe seleccionar un género", Status.BAD_REQUEST);
        }

        if (publisherData.trim().equals("Seleccione uno...")) {
            return new Response("Debe seleccionar una editorial", Status.BAD_REQUEST);
        }

        if (authorsData.length == 0 || (authorsData.length == 1 && authorsData[0].trim().equals("Seleccione uno..."))) {
            return new Response("Debe seleccionar al menos 1 autor", Status.BAD_REQUEST);
        }

        return null;
    }
}
