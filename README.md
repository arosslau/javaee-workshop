Java EE 7/8 - Workshop
========================

Dies ist das Begleitprojekt zum "Java-EE-7/8-Workshop – Enterprise-Anwendungen ohne Ballast" am 6.11.2017 im Rahmen der W-JAX 2017 in München.

Zum Mitmachen benötigen Sie die folgenden Dinge:

- Einen PC (;-). Das Betriebssystem ist generell zweitrangig. Wir haben allerdings nur Erfahrungen mit Windows und (ein wenig) mit Linux. Zu MacOS können wir nichts sagen.

- Ein installiertes JDK. Wir benötigen Java 8 (Download für Windows und Linux: http://www.oracle.com/technetwork/java/javase/downloads/index.html)

- Einen Git-Client, z. B. Git Bash (Download: http://git-scm.com/downloads). Prüfen Sie bitte vor dem Workshop, dass Sie dieses Repository "clonen" können (git clone https://github.com/GEDOPLAN/javaee-workshop.git; cd javaee-workshop; git checkout wjax2017).

- Maven 3.x (Download: http://maven.apache.org/download.html). Maven sollte von der Kommandozeile aus aufrufbar sein, damit wir Probleme beim Build der Software unabhängig von den restlichen Tools - insb. der IDE - bearbeiten können.

- Eine IDE ihrer Wahl. Wichtig ist, dass Maven-Projekte importiert werden können und WildFly als Application Server eingebunden werden kann. Ich empfehle
  dazu das JBoss Developer Studio - kurz JBDS - (http://www.jboss.org/products/devstudio/download/, es reicht der sog. Stand-alone Installer). Anders als
  der Name es vermuten lässt, ist es nicht nur für den JBoss Application Server nutzbar. Lassen Sie sich durch den Text auf der Download-Seite nicht verwirren:
  Die Software ist kostenlos. Sie müssen sich lediglich registrieren.
  
  Das JBDS ist ein Eclipse mit hinzuinstallierten JBoss Tools. Sie können somit auch ihr bereits installiertes Eclipse aufrüsten. Sie finden die JBoss Tools im Marketplace. In der Vergangenheit traten sporadisch Probleme auf, die vermutlich auf inkompatibler Versionen diverser Plugins zurück zu führen waren. So konnten bspw. keine Maven-Projekte importiert werden oder die SCM-Anbindung funktionierte nicht mehr etc. etc. Die sichere Wahl ist daher auf jeden Fall das JBDS.
  
  Nutzen Sie bitte ein möglichst aktuelle Version der IDE. Ältere Versionen sind ggf. nicht in der Lage, den Application Server WildFly 10 einzubinden.
  
  Achten Sie bei der Installation des JBDS bitte darauf, das zuvor installierte JDK einzubinden, nicht nur das JRE. Sollten Sie sich nicht sicher sein, was
  Sie genau eingebunden haben, so schauen Sie in die Datei devstudio.ini im Verzeichnis studio der JBDS-Installation. Mit der Option -vm wird darin die
  für die Ausführung des JBDS zu nutzende Java-Version konfiguriert - meist in den ersten beiden Zeilen der Datei:
  
  ```
  -vm
  C:/Program Files/Java/jdk1.8.0_101/bin/javaw.exe
  ...
  ```
  
- JBDS (wie auch Eclipse) enthält diverse Validatoren, die die Korrektheit von Deskriptoren etc. testen sollen. Leider funktionieren diese Validatoren
  (zumindest für Maven-Projekte) nicht korrekt. Wenn nach dem Import der Projekte Validierungsfehler (z. B. "JPA Problem") angezeigt werden, sollten Sie
  die Validatoren deaktiviern (Window -> Preferences -> Validation: Disable All).

- WildFly (Download http://wildfly.org/downloads/). Wir setzen im Workshop diesen Server in der Version 10.1.0.Final ein. Die Version 11 ist zwar bereits verfügbar, aber uns liegen noch keine umfangreichen Erfahrungen vor. Generell ist jeder Java-EE-7-Server geeignet.

- Wir werden im Wokshop Lombok einsetzen, um Schreibarbeit für niedere Dinge (Getter etc.) einzusparen. Für Maven-Projekte und IDEs, die damit direkt arbeiten
  (z. B. Netbeans) ist dazu keine Vorarbeit nötig. Für Eclipse und damit auch für das JBDS muss Lombok als Java Agent eingetragen werden. Kopieren Sie dazu
  bitte die Datei lombok-1.16.12.jar aus dem Projektverzeichnis backend/additional/ide in das Verzeichnis studio Ihrer JBDS-Installation. In der Datei devstudio.ini
  im gleichen Verzeichnis finden Sie am Ende einige Start-Parameter für die JVM, eingeleitet durch -vmargs. Ergänzen Sie dort (nach der Zeile -vmargs) die 
  Zeile -javaagent:lombok-1.16.12.jar. Der gesamte Abschnitt der Ini-Datei sieht dann etwa so aus:
  
  ```
  -vmargs
  -javaagent:lombok-1.16.12.jar
  -Xms512m
  -Xmx1024m
  ...
  ```

- Node.js (Download https://nodejs.org/en/). Wir werden in diesem Workshop ein Angular-Frontend einsetzen. Der Fokus des Workshops liegt aber nicht auf dem JavaScript-Frontend, sondern auf dem Java-EE-Backend. Die Frontend-Anwendung ist daher schon fertig gestellt und im Projekt bereits eingecheckt. Node und npm werden nur dann benötigt, wenn Sie den Frontend-Teil im Workshop manipulieren und neu bauen möchten.

Wir werden den Workshop zum größten Teil auf Basis von Java EE 7 durchführen. Die Version 8 der Plattform ist zwar seit September verfügbar, aber der derzeit einzige diese Version unterstützende Server GlassFish / Payara 5 enthält noch so viele Bugs, dass wir uns im Sinne eines erfolgreichen Workshop-Tags dagegen entschieden haben. Wir werden am Ende des Workshops aber einige der Neuerungen der Java EE 8 vorstellen.

Fragen können während der Veranstaltung natürlich jederzeit gestellt werden und vorher oder nachher gerne an dirk.weil(at)gedoplan.de und jens.seekamp(at)gedoplan.de.

Dirk Weil & Jens Seekamp
