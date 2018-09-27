# Toteutusdokumentaatio
### Ohjelman rakennetta
Ohjelman rakenne on kaksiosainen, missä käyttöliittymä on eroteltu sovelluksen logiikasta. Käyttöliittymä on yhdessä ainoassa luokassa `gui.JavaFXController`.

Logiikka on paketista `logic`. Siellä on ylimmällä tasolla luokka `SudokuController`. SudokuController toimii välittäjä- ja kontrolleriluokkana muulle sovellukselle.

### Aika- ja tilavaativuudet

##### Backtracking
Tällä hetkellä tarkempaa analyysiä voi tarjota vasta backtracker -luokan ratkaisijasta. Yksinkertaisen toimintaperiaatteensa takia myös aikavaativuuden analysoiminen on helppoa. Tarvittaessa algoritmi käy läpi kaikki mahdolliset kombinaatiot, kunnes löytää oikean ratkaisun. Jos tuntemattomia ruutuja on n kappaletta, voi algoritmi joutua pahimmillaan testaamaan jokaiseen ruutuun useatakin eri vaihtoehtoa. Tällöin tilavaativuus rupeaa lähentelemään kokoluokkaa O(2^n) (Kuvitellaan, että tyhjiä ruutuja on n kpl, ja ensimmäiseen testataan yhdeksää eri numeroa, toiseen yhdeksän eri numero, kolmanteen yhdeksän eli 9x9x9...).

Käytännössä luku on kuitenkin huomattavasti pienempi. Tämä johtuu siitä, että kun rekursiivista metodia kutsutaan, tarkistetaan suoraan onko tehty laittomia sijoituksia. Jos meillä on n tyhjää ruutua ja sijoitamme ensimmäiseen numeron 1 ja seuraava tutkittava ruutu on samalla rivillä, samassa sarakkeessa tai 3x3 laatikossa, tulee kombinaatio 1 ja 1 saman tien suljettua pois. Kun olemme kutsuneet rekursiivista funktiota kahdesti, olemme saaneet suljettua pois k^(n-1) haaraa.

Silti sudokun vaikeustaso vaikuttaa suuresti kuluneeseen aikaan. Helpot sudokut pyörivät kymmenien millisekuntien ratkaisuaikojen ympärillä, vaikeammat taas satojen. Käytettyyn aikaan vaikuttaa myös tuuri. Jos ensimmäinen etsittävä numero on 1 ja toinen 2, säästytään suoraan n. 90% haarojen laskemiselta. Helpolle sudokulle, jossa ensimmäinen ratkottava numero oli 1, oli ratkaisuaika minun koneellani n. 7ms parhaimmillaan ja vaikeimmalla löytämälläni sudokulla n. 500ms.

Ohjelmani laskee myös montako kertaa rekursiivista funktiota kutsuttiin. Siinä oli myös huomattavasti eroja. Helppojen sudokujen kutsukerrat pyörivät sadoissa kerroissa, mutta vaikeammat kymmenissätuhansissa. 500ms kellottanut sudoku vaati yli yhdeksänkymmentätuhatta kutsua.

Tilavaativuus ei ole algoritmilla kummoinen. Aputietorakenteita ei laadita ja ratkaisupuu ei tallennu muistiin. Muistivaativuus on luokkaa O(1), sillä rekursiivisia funktioita voi stackissä olla vain ratkomattomien ruutujen verran päällekkäin. Kun huomaamme virheen tapahtuneen ja peruutamme, päättyy myös päällimmäisen funktion elinkaari.
