package model;

/**
 * Factory Method Pattern
 * Classe responsabile della creazione delle istanze di Utente e delle sue sottoclassi.
 */
public class UtenteFactory {

    // Costruttore privato perché è una classe di utilità statica
    private UtenteFactory() {}

    /**
     * Crea un'istanza specifica di Utente in base al ruolo fornito.
     * * @param id        ID dell'utente (dal DB)
     * @param nome      Nome dell'utente
     * @param cognome   Cognome dell'utente
     * @param email     Email dell'utente
     * @param password  Password dell'utente
     * @param telefono  Numero di telefono
     * @param ruolo     Stringa che rappresenta il ruolo (es. "CLIENTE", "ADMIN")
     * @return          Un oggetto Cliente, Venditore, Amministratore o Utente generico.
     */
    public static Utente createUtente(int id, String nome, String cognome, String email,
                                      String password, String telefono, String ruolo) {

        if (ruolo == null) {
            return null;
        }

        // Normalizziamo la stringa in maiuscolo per evitare errori di case-sensitive
        switch (ruolo.toUpperCase()) {
            case "CLIENTE":
                return new Cliente(id, nome, cognome, email, password, telefono);

            case "VENDITORE":
                return new Venditore(id, nome, cognome, email, password, telefono);

            case "AMMINISTRATORE":
            case "ADMIN":
                // Mappa sia "AMMINISTRATORE" che "ADMIN" sulla classe Amministratore
                return new Amministratore(id, nome, cognome, email, password, telefono);

            default:
                // Fallback: se il ruolo non è riconosciuto, crea un Utente generico
                return new Utente(id, nome, cognome, email, password, telefono, ruolo);
        }
    }
}