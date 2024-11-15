# ForeignSentry
### Un plugin che chiede una password all'accesso uguale per tutti gli utenti.
Nel caso in cui il server sia impostato in offline mode e non sia un problema la registrazione, viene riechiesta un parolachiave uguale per tutti al fine di limitare gli accessi.

In caso il server venga esposto è prevista anche una funzionalità la quale bandisce l'ip del client sfruttando le api di bukkit.

Il plugin **compatibile con Spigot utilizzando le API della versione 1.21.3** ottenibili con [BuildTools](https://www.spigotmc.org/wiki/buildtools/) di Spigot.

Il plugin è stato testato solo su un server vanilla spigot in 1.21.3, per le altre versioni se funziona bene, senò nulla.

### Wiki comandi
/psk \<Nome della password in config\>

### Come modificare e/o compilare con l'idee di Eclipse
1. Installare Eclipse
2. Fare il clone della repo direttamente nella cartella di workspace
3. Tasto dx sulla cartella nella finestra *Package Explorer*, tasto dx sulla cartella *ForeignSentry*. Voce *Properties* e selezionare la voce nel menu *Java Build Path*. Selezionare la tab *Libraries* poi *Modulepath*, cliccare sul bottone *Add External JARs...* e selezionare il file spigotApi derivato da Buildtools.
4. Per compilare, nella finestra *Package Explorer*, tasto dx sulla cartella *ForeignSentry*, voce *Export..*.
5. Selezionare Java > JAR file, next, selezionare solo .classpath, config.yml, plugin.yml.
6. Selezionare dove scrivere il file compilato.



### Come funziona
1. Il player si connette e spawna
2. Viene messo il modalità adventure
3. Se l'utente si era già autenticato prima di 5gg viene messo nella modalità di gioco precedente.
4. Se l'utente non si era autenticato parte un timer di X secondi definito dal config.
5. L'utente può autenticarsi con il programma **/psk \<password\>**
6. Se alla fine del timer l'utente non si è autenticato O ha usato una parolachiave errata viene kickato. 
7. Se è impostato il flag di Ban il suo IP viene bannato.
 


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
