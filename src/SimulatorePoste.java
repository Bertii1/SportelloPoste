import java.time.LocalTime;

/**
 * Classe con il main, che avvia l'app
 * che rappresenta il flusso dei clienti di un ufficio postale
 * messi in attesa da un totem elettronico che assegna
 * un numero progressivo e stampa il ticket
 * Clienti gestiti da un solo sportello
 * @author frida
 * @version 1.0
 */
public class SimulatorePoste {
    public static void main(String[] args) {
        System.out.println("Apertura ufficio postale: " + LocalTime.now());
        long startTime = System.currentTimeMillis();

        ListaClienti listaClienti = new ListaClienti();
        Thread arriviThread = new Thread(new GestoreArrivi(listaClienti, 1));
        Thread arriviThread2 = new Thread(new GestoreArrivi(listaClienti, 2));
        Thread sportelloThread = new Thread(new Sportello(listaClienti, " paolo"));
        Thread sportelloThread2 = new Thread(new Sportello(listaClienti , " marzia"));
        arriviThread.start();
        arriviThread2.start();
        sportelloThread.start();
        sportelloThread2.start();

        try {
            // Attendiamo la fine dei thread che generano arrivi (totem)
            arriviThread.join();
            arriviThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Interrompiamo gli sportelli una volta che i totem sono chiusi
        sportelloThread.interrupt();
        sportelloThread2.interrupt();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        long seconds = duration / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        minutes = minutes % 60;

        System.out.println("Tempo di apertura delle poste: " + hours + " ore e " + minutes + " minuti.");
    }
}
