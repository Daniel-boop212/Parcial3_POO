package core.models.storage;

/**
 * Interfaz Observer para notificaciones de cambios en el almacenamiento.
 * Permite que las vistas se suscriban a cambios sin acoplarse directamente al
 * Storage.
 * 
 * Patrón: Observer (Behavioral)
 * Beneficio: Desacoplamiento total entre modelos y vistas.
 */
public interface IStorageObserver {

    /**
     * Se invoca cuando se agrega un nuevo stand.
     */
    void onStandAdded();

    /**
     * Se invoca cuando se agrega un nuevo libro.
     */
    void onBookAdded();

    /**
     * Se invoca cuando se agrega un nuevo autor.
     */
    void onAuthorAdded();

    /**
     * Se invoca cuando se agrega un nuevo narrador.
     */
    void onNarratorAdded();

    /**
     * Se invoca cuando se agrega una nueva editorial.
     */
    void onPublisherAdded();

    /**
     * Se invoca cuando se agrega un nuevo gerente.
     */
    void onManagerAdded();

    /**
     * Se invoca cuando se produce una compra en un stand.
     */
    void onStandPurchased();
}
