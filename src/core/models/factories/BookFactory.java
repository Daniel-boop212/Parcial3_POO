/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.factories;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.dto.BookRequestDTO;
import core.models.storage.IMegaferiaStorage;
import core.models.strategies.AudioBookStrategy;
import core.models.strategies.DigitalBookStrategy;
import core.models.strategies.IBookCreationStrategy;
import core.models.strategies.PrintedBookStrategy;

/**
 * Fábrica para crear libros utilizando el patrón Strategy.
 * Elimina lógica condicional en el controlador (OCP - Principio
 * Abierto/Cerrado).
 */
public class BookFactory {

    private static final IBookCreationStrategy PRINTED_STRATEGY = new PrintedBookStrategy();
    private static final IBookCreationStrategy DIGITAL_STRATEGY = new DigitalBookStrategy();
    private static final IBookCreationStrategy AUDIO_STRATEGY = new AudioBookStrategy();

    /**
     * Crea un libro utilizando la estrategia apropiada.
     * 
     * @param request La solicitud de creación de libro
     * @param storage El almacenamiento para persistir el libro
     * @return Respuesta indicando éxito o fallo
     */
    public static Response createBook(BookRequestDTO request, IMegaferiaStorage storage) {
        IBookCreationStrategy strategy = selectStrategy(request);

        if (strategy == null) {
            return new Response("Tipo de libro no válido especificado", Status.BAD_REQUEST);
        }

        return strategy.createBook(request, storage);
    }

    /**
     * Selecciona la estrategia apropiada basada en las banderas de tipo de libro.
     * 
     * @param request La solicitud de libro con información de tipo
     * @return La estrategia apropiada o null si no se especifica ningún tipo
     */
    private static IBookCreationStrategy selectStrategy(BookRequestDTO request) {
        if (request.isPrinted()) {
            return PRINTED_STRATEGY;
        } else if (request.isDigital()) {
            return DIGITAL_STRATEGY;
        } else if (request.isAudio()) {
            return AUDIO_STRATEGY;
        }
        return null;
    }
}
