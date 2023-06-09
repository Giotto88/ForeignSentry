# Come funziona
1. Player connesso
2. Stop movimenti + adventure mode [ connection handler ]
3. < È tra gli utenti già autenticati da meno di 5gg ? > [ connection handler ]

    1. SE SI...
        1. rimuovi blocco movimento e adventure mode [ connection handler ]
    2. SE NO...
       1. < PASSWORD RICHIESTA > [ psk ]
          1. PASSWORD CORRETTA [ psk ]
             1. Rimuovi blocco movimento e adventure mode [ psk ]
             2. Aggiunto tra gli account autenticati [ psk ]
          2. PASSWORD ERRATA [ psk ]
             1. Kick dell'utente fuori dal server [ psk ]
             2. Aggiunto al registro dell'attività sospetta [ psk ]

# Casistica
Nella maggior parte dei casi i bot si collegano al server senza entrare direttamente rendendo inutile il captcha.
Inoltre è necessario considerare una soluzione per monitorare i **ping** eseguiti al server e i **pacchetti malformati**.

Il bot è pensato per un uso domestico non enterprise. Non prevede un blocco ai ping ne a dos ne a ddos.
## monitoraggio
 - si considera di monitorare gli utenti che accedono **senza password**.
 - si considera di non richiedere la password da un particolare ip con utente se quest' ultimo si è **già autenticato** entro tot giorni.
## verifica BOT e/o intrusi
 - è richiesto inserire un chapta in chat: bot semplici
 - è richiesto inserire una password

## azioni a seguito
- adventure mod nel' tempo di login
- ban-ip a seguito di vari tentativi falliti
