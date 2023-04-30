# Come funziona
1. Player connesso
2. Stop movimenti + adventure mode [ connection handler ]
3. < È tra gli utenti pre autenticati da meno di 5gg ? > [ connection handler ]

    1. SE SI...
        1. rimuovi blocchi + adventure mode [ connection handler ]
    2. SE NO...
       1. < PASSWORD RICHIESTA > [ psk ]
          1. PASSWORD CORRETTA [ psk ]
             1. rimuovi blocchi + adventure mode [ psk ]
             2. Aggiunto tra gli account autenticati [ psk ]
          2. PASSWORD ERRATA [ psk ]
             1. Kick dell'utente fuori dal server [ psk ]
             2. Aggiunto al registro dell'attività sospetta [ psk ]