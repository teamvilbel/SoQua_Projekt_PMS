# Softwarequalität Übung: Systemmodellierung

### Organisatorisches

Organisation: [https://github.com/teamvilbel](https://github.com/teamvilbel)

Repository: [https://github.com/teamvilbel/SoQua\_Projekt\_PMS](https://github.com/teamvilbel/SoQua_Projekt_PMS)

Projekt und Kanban-Board: [https://github.com/teamvilbel/SoQua\_Projekt\_PMS/projects/1](https://github.com/teamvilbel/SoQua_Projekt_PMS/projects/1)

CSV-Parser: [https://gist.github.com/SirMoM/5e483565237a8d163e96373f44845519](https://gist.github.com/SirMoM/5e483565237a8d163e96373f44845519)

### Benutzeranforderungen

Ein Patientenmanagementsystem soll **auf Anfrage** einen **Verwaltungsbericht erzeugen,** der **alle Medikamente** und die **damit verbundenen Kosten** für **Sprechstunden** ausgibt.

Die Applikation soll mit einer **fehlertolerante GUI** erstellt werden.

Mögliche fehlerhafte Nutzung soll abgefangen werden bzw. dem Nutzer als Feedback geliefert werden.

Nichtfunktionale Anforderungen

Es soll eine **strikte Trennung** zwischen den **Daten, der Logik** und der **GUI** realisiert werden.

Die Anwendung soll flüssig reagieren und benutzerfreundlich umgesetzt werden.

### Durchführbarkeitsstudie

Das Budget für das Projekt und die Anforderungen ist unlimitiert und gegeben. Zeitlich ist es bis um 13 Uhr am 23.08 abzuschließen. Zur Verfügung stehen 3 Entwickler, 1 Tester und 2 Softwarcharchitekt / Requirements-Engineer. Zur Umsetzung werden folgende technologien verwendet: Java als Programmiersprache und zur Umsetzung der Kernfunktionalitäten, JavaFX zur darstellung der Oberfläche und Datenhaltung.





## Systemanforderungen

### Benutzeranforderungen

- Ein Patientenmanagementsystem soll auf Anfrage einen Verwaltungsbericht erzeugen, der alle Medikamente und die damit verbundenen Kosten für Sprechstunden ausgibt.
- Die Applikation soll mit einer fehlertolerante GUI erstellt werden.
- Mögliche fehlerhafte Nutzung soll abgefangen werden bzw. dem Nutzer als Feedback geliefert werden.

### Nichtfunktionale Anforderungen

- Es soll eine strikte Trennung zwischen den Daten, der Logik und der GUI realisiert werden.
- Die Anwendung soll flüssig reagieren und benutzerfreundlich umgesetzt werden.

### reale Systemanforderungen:

CSV-Einlesen:

- CSV liegt in einem bestimmten Verzeichnis bereit und abhängig vom Zeitstempel der Datei wird eine Vorauswahl getroffen.
- CSV besteht aus den Feldern &quot;Datum der Sprechstunde&quot; (Date),  &quot;Medikamentname&quot; (String), &quot;Anzahl der Verschreibungen&quot; (Integer) und &quot;Kosten pro Verschreibung&quot; (Double)
- Anwendung muss fehlertolerant sein da es die Möglichkeit gibt das die CSV fehlerhaft ist. D.h. Fehlerhafte und leere Felder sollten dem Nutzer kommuniziert werden, bspw. soll ein PopUp mit der Meldung: &quot;Es existiert kein(e) [Preis, Verschreibung] für Medikament XY!&quot; generiert werden oder eine Meldung innerhalb des Anwendungsfensters erscheinen.

GUI:

- JAVA-FX Anwendung nach MVC-Pattern
- Pages der GUI: Einstellungsseite, erzeugter Bericht und Diagrammanzeige
- Navigation zwischen der Einstellungsseite und dem Bericht ist jederzeit möglich
- Möglichkeit zum Filtern einzelner Felder

Aufbau der GUI:

Einstellungsfenster

Die Navigation der Anwendung beginnt mit einem Einstellungsfenster in dem es Möglich ist:
→ den Speicherort der CSV-Datei anzugeben

→ einen Tag nach dem gefiltert werden soll auszuwählen

→ ein Medikament nach dem gefiltert werden soll auszuwählen

→ auswählen ob ein Diagramm angezeigt werden soll oder nicht

Am Ende des Fensters gibt es eine Taste mit der die Berichterzeugung gestartet werden kann, wodurch das Berichtsfenster geöffnet wird.

Einstellungsmöglichkeiten

| Einstellung | Funktion |
| --- | --- |
| Ein bestimmter Tag wird ausgewählt | In der Übersicht sind nur Einträge zum ausgewählten Tag zu sehen. Dazu werden die Tageskosten aufgeführt. |
| Ein bestimmtes Medikament wird ausgewählt | In der Übersicht sind nur Einträge zum ausgewählten Medikament zu sehen. Dazu werden die Gesamtkosten des Medikaments aufgeführt. |
| Ein bestimmter Tag und ein Bestimmtes Medikament wird ausgewählt | In der Übersicht sind nur Einträge zum ausgewählten Medikament und zum ausgewählten Tag zu sehen. Darüber hinaus werden die Tageskosten und die Gesamtkosten des Medikaments ausgeführt |
| Die Diagramm Checkbox wird ausgewählt | Im Berichtsfenster wird der Button zur Diagrammerzeugung eingeblendet. |

Berichtsfenster

In dieser Übersicht sollte es für den Benutzer möglich sein die Daten der CSV anhand der getroffenen einstellungen im Einstellungsfenster zu sehen und zusätzlich die einzelnen Spalten auf und -absteigend zu sortieren::

→ Tabellenübersicht der enthaltenen Daten mit Sortierfunktion

→ Zurück-Button um zurück zum Einstellungsfenster zu wechseln

→ je nach Einstellung wird der Button um eine Visualisierung (Diagramm) der Daten angezeigt

→ Informationen zu Gesamtkosten des Monats und Gesamtanzahl der Verschreibungen

→ je nach getroffenen Einstellungen die Anzeige der Kosten und der Verschreibungen des jeweiligen Tages

→ je nach getroffenen Einstellungen die Anzeige der anteiligen Tageskosten des ausgewählten Medikaments

Diagrammfenster

In dieser Ansicht werden die CSV-Daten visualisiert. Hier werden je nach Einstellung die besten 5 Einträge der CSV-Datei nach prozentualer Verteilung der Kosten oder der Anzahl der Verschreibungen  im Kreisdiagramm angezeigt.

→ Kreisdiagramm

→ Legende und dazugehörige Werte

→ ToggleButton zum wechseln zwischen prozentualer Verteilung nach Kosten und prozentualer Verteilung nach Anzahl der Verschreibungen

### Mit dem Kunden Besprochene Fragen:

Wie soll der Verwaltungsbericht aussehen? Als externes Dokument(Excel,CSV,PDF usw…) oder Anzeigen in der Anwendung? Nur Anzeige in App reicht.

Soll der Bericht als E-Mail versendet werden? Nein

Soll der Bericht Filterbar sein? Gruppierung von Medikamenten,Preis und Dosierung sinnvoll.

Fehlertolerante GUI, Corporate Design oder nicht? Gibt es eine bestimmte Richtlinie wie die GUI aufgebaut sein soll? Intuitives design? Ganz normal ohne CorporateDesign

Wo soll die Anwendung laufen? (Intranet,Betriebssystemunabhängig,Webbasiert)-\&gt; EGAL

Welche Schnittstellen werden benötigt? Anbindung an ein Kernsystem? Keine Schnittstellen oder Fremdsysteme

Ist damit zu rechnen das die Last wächst bzw. werden die Kundendaten wachsen? Nein

Welche Datenoperationen sollen umgesetzt werden? Nur einlesen

Crud  nein ? Nein

Ausfallsicherheit ? Backups? Nein

Datenbank Ja oder Nein?  Nein

Anmelderoutine?  Nein

Unittests: Jedes Feld könnte leer sein. Datum hat immer gleiches Format, Preise/Dosierungen ebenfalls
