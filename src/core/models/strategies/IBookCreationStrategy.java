/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.strategies;

import core.controllers.utils.Response;
import core.models.dto.BookRequestDTO;
import core.models.storage.IMegaferiaStorage;

/**
 * Interfaz de estrategia para crear diferentes tipos de libros.
 * Implementa el patrón Strategy para manejar la creación de libros sin lógica
 * condicional en el controlador.
 */
public interface IBookCreationStrategy {

    /**
     * Crea un libro basado en la solicitud y almacenamiento proporcionados.
     * Cada implementación maneja un tipo específico de libro (Impreso, Digital,
     * Audio).
     * 
     * @param request La solicitud de creación de libro con todos los datos
     *                necesarios
     * @param storage El almacenamiento para persistir el libro
     * @return Respuesta indicando éxito o fallo
     */
    Response createBook(BookRequestDTO request, IMegaferiaStorage storage);
}
