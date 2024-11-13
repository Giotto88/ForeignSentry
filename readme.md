# ForeignSentry
### Un plugin che chiede una password all'accesso uguale per tutti gli utenti.
Nel caso in cui il server sia impostato in offline mode e non sia un problema la registrazione, viene riechiesta un parolachiave uguale per tutti al fine di limitare gli accessi.

In caso il server venga esposto è prevista anche una funzionalità la quale bandisce l'ip del client sfruttando le api di bukkit.

Il plugin **compatibile con Spigot utilizzando le API della versione 1.21.3** ottenibili con [BuildTools](https://www.spigotmc.org/wiki/buildtools/) di Spigot.

Il plugin è stato testato solo su un server vanilla spigot in 1.21.3, per le altre versioni se funziona bene, senò nulla.

### Wiki comandi
/psk \<Nome della password in config\>



### Come funziona
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


<!-- TODO:
- [x] ListaAutenticazioni (IP+USERNAME)(data unix)
- [ ] ListaIPRegistrati
- [ ] Implementazione di geo-filtering
- [ ] Vulnerabile a username con simboli





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
- ban-ip a seguito di vari tentativi falliti -->
