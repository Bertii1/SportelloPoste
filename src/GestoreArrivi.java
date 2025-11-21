/**
 * Classe che implementa il thread per il totem touch screen che aggiunge
 * i clienti alla lista di attesa e genera il numero di attesa
 * rappresenta il produttore
 * @author frida
 * @version 1.0
 */
public class GestoreArrivi implements Runnable {

    /* variabili d'istanza sono;
     * la risorsa condivisa listaClienti
     * e la costante per il numero massimo di arrivi */
    private ListaClienti listaClienti;
    private final int attesaArrivi = 3000; //millisecondi
    /**
     * constructor
     * @param listaClienti
     */
    public GestoreArrivi(ListaClienti listaClienti) {
        this.listaClienti = listaClienti;
    }
    /**
     * TODO: cosa fa?
     * @see Runnable
     */
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Thread.sleep(attesaArrivi);
                Integer clienteArrivato = listaClienti.addCliente();
                if (clienteArrivato == null) {
                    break;
                }
                System.out.println("Arrivo Cliente Numero \t " + clienteArrivato);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrotto durante lo sleep");
        } finally {
            System.out.println("Posta Chiusa");
        }
    }
}
