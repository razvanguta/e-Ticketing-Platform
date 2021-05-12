# e-Ticketing Platform
 
 Proiectul acesta dezvolta o platforma de e-Ticketing, unde se gestioneaza achizitia de bilete de catre clienti la diferite evenimente!
# Citirea si scriera in CSV:
Pentru citire si scriere am folosit metode, care se folosesc de prelucrarea sirurilor de caractere. Pentru citire am extras fiecare linie din CSV si pentru fiecare tip de eveniment, l-am adaugat in clasa serviciu intr-un array corespunzator obiectul aferent(se executa aceasta operatie la pornirea programului). In clasa serviciu am 9 actiuni( mai exista alte 2 actiuni pentru clasa locatii si clienti care se suprapun cu cele 9 actiuni descrise). Dupa fiecare actiune, pentru fiecare eveniment, se apeleaza metoda de scriere in CSV, transformandu-se fiecare camp in string si ulterior adaugandu-se in CSV(astfel se mentine lista evenimentelor actualizata). Pe ideea anterioara am creat si un fisier CSV pentru audit care contine istoricul comenzilor si ora la care acestea au fost realizate. **Metodele de citire si scriere se afla intr-o clasa generica, care este instantiata in clasa singleton serviciu.** De asemenea, pentru fiecare CSV al unei clase exista un fisier secundar in care scriem si pastram datele, fara a citi din el.

# Clasele implementate si utilizarea lor: 
# 0. Main
-> Utilizata pentru demonstrarea functionalitatilor din cadrul proiectului.
# 1. Locations
-> Clasa Locations este utilizata pentru a seta un loc unde va avea loc un eveniment. Poate fi initializata prin specificarea atributelor sau prin utilizarea metodei addLocation, unde puteti selecta atributele citindu-le

# 2. Clients
-> Clasa Clients este utilizata pentru a vedea lista participantilor la un eveniment. Puteti adauga preferințele unui client prin introducerea lor intr-un set.

# 3. Events
-> Clasa abstracta Events este utilizata pentru a stabili atributele principale ale unui eveniment. Puteti adauga un eveniment, puteti adauga un client sau puteti actualiza informatiile personale despre un client.

# 3.1. Sports
-> Clasa abstracta Sports este o subclasa a clasei Events si este folosita pentru a stabili atributele principale ale unui eveniment sportiv. Suprascrie metoda addEvent si are o metoda abstracta care este utilizata de subclasele sale pentru a da o predictie despre un castigator.

# 3.1.1. TennisGames
-> Clasa TennisGames este o subclasa a clasei Sports si este folosita pentru a avea toate detaliile despre un joc de tenis. Suprascrie metoda addEvent și are o formula specifica pentru a determina posibilul castigator al unei competitii.

# 3.1.2. FootballGames
-> Clasa FootballGames este o subclasa a clasei Sports si este folosita pentru a avea toate detaliile despre un joc de fotbal. Suprascire metoda addEvent, are o formulă specifica pentru a determina castigatorul posibil al unei competitii si are o metoda care va spune despre importanta unui meci in functie de numarul de jocuri pe care le-au avut doua echipe.

# 3.2. Cultural
-> Clasa abstracta Cultural este o subclasa a clasei Events care este utilizata pentru a stabili atributele principale ale unui eveniment de spectacol. Suprascire metoda addEvent si are o metoda abstracta care este utilizata de subclasele ei pentru acordarea de recenzii unui spectacol.

# 3.2.1. Movies
-> Clasa Movies este o subclasă a clasei Cultural si are scopul de a implementa toate caracteristicile unui film. Suprascrie metodele addEvent si addClient si are posibilitatea de a acorda recenzii unui film dand un numar de stele.

# 3.3. Tours

-> Clasa Tours este o subclasa a clasei Events și implementeaza datele necesare unei vacante / excursii. Suprascrie metoda addEvent si ofera posibilitatea de a modifica numarul de zile, care va recalcula automat pretul.

# 3.4. Concerts
-> Clasa Concerts este o subclasa a clasei Events si implementeaza datele necesare unui concert muzical. Suprascrie metoda addEvent si are o metoda care calculeaza distributia locurilor din arena/sala dupa numarul de bilete.

*Actiunile sunt semnalizate in dreptul lor pentru fiecare clasa!!*
