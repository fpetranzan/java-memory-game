# java-memory-game

lang [IT]: [en](https://github.com/fpetranzan/java-memory-game/blob/master/README.md) | [it](https://github.com/fpetranzan/java-memory-game/blob/master/README_it.md)

Realizzato durante il mio periodo di studio, questo è il mio primo progetto di maggior complessità.

Memory è un gioco in cui tutte le carte vengono disposte a faccia in giù su una superficie e due carte vengono girate a faccia in su ad ogni turno. Lo scopo del gioco è scoprire coppie di carte corrispondenti.

Il turno di un giocatore termina quando, girando due carte, non trova nessuna coppia. In caso contrario, ha diritto a poter giare altre due carte.

Memory può essere giocato sia da più giocatori che in solitario.

## Tecnologia

- Java 8
- Java Swing

Il progetto da me realizzato cerca di replicare questo gioco, cercando di realizzare le diverse modalità di gioco e implementando alcune funzionalità affini a un gioco per computer. Le carte presenti e le coppie disponibili da dover completare saranno rappresentate con dei numeri.

PREMESSA: i testi all'interno delle immagini saranno in lingua italiana

<img src="https://github.com/user-attachments/assets/eda1aae9-62a3-46b7-b3d4-7f76fa8c4248" alt="Inserisci il Nome" width="400"/>

<img src="https://github.com/user-attachments/assets/99f224f1-aa55-450b-b4c3-1acc2f5484e5" alt="Avvia Partita" width="400"/>

Tutte le funzionalità disponibili:

File

- Nuova Partita
- Carica Partita
- Ricomincia Partita
- Salva Partita
- Esci

Modifica

- Risoluzione
- Modalità
- Difficoltà
- Suoni

Visualizza

- Regole gioco
- Classifica

Le principali funzionalità di cui voglio spiegare maggiormente sono:

### Carica Partita

<img src="https://github.com/user-attachments/assets/c58d8e58-7223-4243-a811-affc769e4f3f" alt="Carica Partita" width="400"/>

Durante una partita è possibile selezionare la voce “Salva Partita” e fornendo solo un nome per il salvataggio questa verrà memorizzata.

In un secondo momento sarà possibile recuperare il salvataggio e riprendere il gioco da dove si era lasciato.

Il salvataggio avviene con scrittura su file, che viene letto e caricato quando richiesto

### Difficoltà

<img src="https://github.com/user-attachments/assets/3a6fba2a-cf0d-4dfd-a068-2add9b321c32" alt="Cambia Difficoltà" width="400"/>

Nel menu “Modifica > Difficoltà” è possibile scegliere fra tre diverse opzioni:

- Facile (4x4)
- Medio (6x6)
- Difficile (8x8)

A seconda della difficoltà scelta il campo del giocatore verrà modificato mostrandogli un campo più grande, con un maggior numero di carte e di coppie da dover trovare.

### Modalità

<img src="https://github.com/user-attachments/assets/f8b8fe59-ee87-46b4-bd4a-0a3a2a2ffeb6" alt="Cambia Modalità" width="400"/>

È possibile scegliere fra tre diverse modalità di gioco:

- Giocatore Singolo
- Due Giocatori
- Computer

Nella modalità “Due giocatori” verrà chiesto di inserire un nuovo nome e i due giocatori potranno sfidarsi.

In modalità “Computer”, l’avversario non sarà un giocatore ma il programma. La logica implementata per la scelta delle carte da parte del programma è molto semplice, la prima carta estratta è sempre casuale tra quelle ancora disponibili sul campo, se la carta scelta è uguale a una delle ultime 4 carte girate in tutta la partita, la coppia viene completata (questa logica permette di “simulare” una memoria da parte del programma) altrimenti, se non è possibile completare la coppia anche la seconda carta viene estratta in maniera casuale

N.B Questa “memoria” del programma presenta alcuni difetti in quanto, estraendo la prima carta sempre casualmente, se durante la partita avviene la seguente interazione:

| Turno | Carta 1 | Carta 2 |
| --- | --- | --- |
| Computer | “5” | “12” (1) |
| Giocatore | “9” | “12” (2) |

Avendo trovato la coppia di “12”, un giocatore sarebbe in grado di completarla. Il computer in questo caso riuscirebbe a completare la coppia solo se in maniera casuale dovesse pescare come prima carta ancora il “12”

| Turno | Carta 1 | Carta 2 | Risultato |
| --- | --- | --- | --- |
| Computer | “12” (1) | “12” (2) | ✅ |
| Computer | “7” | … | ❌ |

N.B È possibile combinare la scelta della Modalità con la Difficoltà quindi sarà possibile giocare:

| Difficoltà | Modalità |
| --- | --- |
| Facile | Giocatore Singolo |
| Difficile | Giocatore Singolo |
| Medio | Due Giocatori |
| Difficile | Computer |
| … | … |

### Turni e Punteggio

Al fine di rendere un po più “sfida” il gioco e le diverse modalità ho introdotto due nuovi aspetti all'interno del gioco.

Il primo riguarda la gestione dei turni, infatti, ogni giocatore (ma anche in modalità “Giocatore Singolo”) per ogni turno avrà un tempo limitato per girare le due carte. (quadrato giallo dell’immagine)

Il tempo a disposizione è rappresentato nel timer presente nella barra delle informazioni sopra al campo da gioco e corrisponde ad un tempo pari 9 secondi.

Al termine di questo tempo se il giocatore non avrà girato entrambe le due carte, la prima carta scelta verrà ricoperta e il turno terminerà.

<img src="https://github.com/user-attachments/assets/e1f4110c-2d11-462a-804e-1e4d8ef16d06" alt="Turni e Punti" width="400"/>

Il secondo aspetto riguarda il punteggio (quadrato rosso dell’immagine), quando il giocatore che sta eseguendo il turno trova una coppia gli verranno aggiunti 5 punti al suo punteggio, mentre per ogni coppia sbagliata gli verrà sottratto 1 punto.

Ovviamente il punteggio non potrà mai andare sotto lo 0 e in caso di modalità diverse da “Giocatore Singolo” il vincitore sarà chi, al termine della partite e con tutte le coppie trovate, avrà il punteggio maggiore.

Oltre a queste ci sono altre funzionalità, ma di minore impatto per cui la loro spiegazione trovavo secondaria.

Ricordo che questo è stato, quello che per me è definibile, il mio primo progetto.

Ovviamente presenta diversi BUG e soprattutto la qualità del codice non è sicuramente buona.