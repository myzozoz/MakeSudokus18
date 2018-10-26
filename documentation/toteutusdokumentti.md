# Toteutusdokumentaatio
### Ohjelman rakennetta
Ohjelman rakenne on kaksiosainen, missä käyttöliittymä on eroteltu sovelluksen logiikasta. Käyttöliittymä on yhdessä ainoassa luokassa `gui.JavaFXController`.

Logiikka on paketissa `logic`. Siellä on ylimmällä tasolla luokka `SudokuController`. SudokuController toimii välittäjä- ja kontrolleriluokkana muulle sovellukselle.

### Aika- ja tilavaativuudet

### Backtracking
 Yksinkertaisen toimintaperiaatteensa takia myös Backtrackerin aikavaativuuden analysoiminen on helppoa. Tarvittaessa algoritmi käy läpi kaikki mahdolliset kombinaatiot, kunnes löytää oikean ratkaisun. Jos tuntemattomia ruutuja on n kappaletta, voi algoritmi joutua pahimmillaan testaamaan jokaiseen ruutuun useatakin eri vaihtoehtoa. Tällöin tilavaativuus rupeaa lähentelemään kokoluokkaa O(2^n) (Kuvitellaan, että tyhjiä ruutuja on n kpl, ja ensimmäiseen testataan yhdeksää eri numeroa, toiseen yhdeksän eri numeroa, kolmanteen yhdeksän eli 9x9x9...).

Käytännössä luku on kuitenkin huomattavasti pienempi. Tämä johtuu siitä, että kun rekursiivista metodia kutsutaan, tarkistetaan suoraan onko tehty laittomia sijoituksia. Jos meillä on n tyhjää ruutua ja sijoitamme ensimmäiseen numeron 1 ja seuraava tutkittava ruutu on samalla rivillä, samassa sarakkeessa tai 3x3 laatikossa, tulee kombinaatio 1 ja 1 saman tien suljettua pois. Kun olemme kutsuneet rekursiivista funktiota kahdesti, olemme saaneet suljettua pois k^(n-1) haaraa.

Silti sudokun vaikeustaso vaikuttaa suuresti kuluneeseen aikaan. Helpot sudokut pyörivät kymmenien millisekuntien ratkaisuaikojen ympärillä, vaikeammat taas satojen. Käytettyyn aikaan vaikuttaa myös tuuri. Jos ensimmäinen etsittävä numero on 1 ja toinen 2, säästytään suoraan yli 90% haarojen laskemiselta. Helpolle sudokulle, jossa ensimmäinen ratkottava numero oli 1, oli ratkaisuaika minun koneellani n. 7ms parhaimmillaan ja vaikeimmalla löytämälläni sudokulla n. 500ms.

Ohjelmani laskee myös montako kertaa rekursiivista funktiota kutsuttiin. Tässä oli myös huomattavasti eroja. Helppojen sudokujen kutsukerrat pyörivät sadoissa kerroissa, mutta vaikeammat kymmenissätuhansissa. 500ms kellottanut sudoku vaati yli yhdeksänkymmentätuhatta kutsua.

Tilavaativuus ei ole algoritmilla kummoinen. Aputietorakenteita ei laadita ja ratkaisupuu ei tallennu muistiin. Muistivaativuus on luokkaa O(1), sillä rekursiivisia funktioita voi stackissä olla vain ratkomattomien ruutujen verran päällekkäin. Kun huomaamme virheen tapahtuneen ja peruutamme, päättyy myös päällimmäisen funktion elinkaari.


### Crookin algoritmi
Crookin algoritmin soveltaminen ohjelmistossa oli hieman haastavahkoa. Algoritmi on nimittäin tarkoitettu ihmisille. Päälogiikka pyörii kuitenkin yhdessä while-loopissa, joka pyörii, kunnes sudoku on valmis tai emme pysty enää päättelemään lisää algoritmillisesti. Tästä johtuen myös aikavaativuus on hieman monimutkaisempi laskea. Algoritmi toimii iteratiivisesti.

Jokaisen iteraation aikana teemme kolme merkittävää asiaa:
1. Etsimme soluja, joissa on vain yksi mahdollinen numero ja käsittelemme solun.
2. Luomme ennakoivia sarjoja ja poistamme apumerkintöjä muista soluista löytöjen perusteella.
3. Tarkastamme, onko muutosta tapahtunut kuluvan iteraation aikana.

*Merkitään  
s: yksittäisen sarjan (rivi,sarake, 3x3 laatikko) pituutena  
c: ennakoivaan sarjaan kuulumattomien solujen määrä  
t: tuntemattomien solujen määrä*

Sovelluksen alustusvaiheessa kopioimme sudokun sisällön sisäiseen aputietorakenteeseen, minkä aikavaativuus on arviolta s². Sen jälkeen luomme alustavat apumerkinnät. Luontivaiheessa käymme läpi jokaisen ruudun kerran ja jokaisen tunnetun ruudun kohdalla poistetaan apumerkinnät jokaisesta saman sarjan ruudusta. Tästä seuraa aikavaativuus joka on pahimmillaan s^²\*3\*s=3s³. Aikavaativuusluokka alustukselle on siis O(s³).  

Iteraation ensimmäsiessä vaiheessa käymme uudestaan jokaisen ruudun läpi. Jos ruudussa on vain yksi merkintä, laitetaan ruutuun merkinnän numero ja karsitaan jälleen apumerkintöjä asianmukaisista ruuduista. Huomaamme, että tämänkin operaation aikavaativuusluokka on O(s³).

Toisessa vaiheessa etsimme ennakoivia sarjoja. Ennakoivia sarjoja etsitään jokaisesta mahdollisesta sarjasta (9*3). Etsiminen taas tapahtuu seuraavanlaisesti. Syötämme sarjan `PreemptiveSetExaminer`-luokan funktiolle `examine(sarja)` ja parsimme pois kaikki tunnetut solut. Tällöin meille jää enimmillään 9 solua jäljelle.

Näistä soluista etsimme ennakoivaa sarjaa käyttämällä sovellettua versiota Dijkstran algoritmista. Koska käsittelemme täysin kytkettyä verkkoa ja käyttämämme apurakenne ei ole maailmanluokkaa, voimme pessimistisesti arvioida aikavaativuusluokaksi O(n^2), missä pahimmillaan n=s=9. Jos olemme löytäneet ennakoivan sarjan, pitää vielä poistaa merkintöjä jokaisesta sarjan ulkopuolisesta solusta. Operaatiossa suoritetaan pahimmillaan c\*((s-1)-c) operaatiota, missä  c on väliltä 1-8. Huomataan, että pahimmillaankin tilanne on c=(s-1)/2=4. Eli c\*((s-1)-c) = 4\*((9-1)-4)=16. Ei siis kovin paljon.

Tämä suoritetaan kerran jokaiselle solulle, eli pahimmillaan 9 kertaa. Kaikista huonoimmassa tapauksessa ennakoivien sarjojen etsimiseen tarvitaan siis 9\*3\*9\*s²\*16 kutsua, jolloin aikavaativuusluokaksi tulee O(s²).

Iteraation 1. ja 2. vaihe suoritetaan niin monta kertaa, kuin vaaditaan. Empiirisesti olen huomannut, että vaaditut kerrat pyörivät 1-6 välillä. Voi olla, että todella vaativalle sudokulle saattaa tarvita seitsemännenkin, mutta luku pysyy silti hyvin vaatimattomana. Erittäin karkeana arviona voisi sanoa, että vaadittujen iteraatioiden määrä on luokkaa O(log(t)).

Kokonaisvaativuusluokaksi saadaan O(s³) + O(log(t)) \* (O(s³) \* O(s²)) = O(log(t) \* s³). s on kuitenkin tunnettu vakio (s=9), joten aikavaativuusluokka on erittäin raa'asti yksinkertaistettuna O(log(t)).

Tilavaativuus on jälleen luokkaa O(1). Käytämme ohjelmaa ajaessa jonkin verran aputietorakenteita, mutta ne hylätään saman tien käytön jälkeen, jolloin niitä ei pääse pakkaantumaan muistiin liialti.

Toteutuksessa käytetään tietyissä tapauksessa myös backtracking -algoritmia. Mikäli havaitaan, että jonkin iteraation aikana ei tapahdu muutosta, jää teteutettu algoritmi jumiin. Algoritmissa ei ole tällöin tapahtunut virhettä, vaan voi olla, että ei vain saada enää vähennettyä informaatiota sudokusta. Oikeaoppinen ratkaisu olisi arvata yhden ruudun sisältö ja jatkaa Crookilla. Mikäli harvaitaan virhetilanne, palautetaan arvausta edeltävä tilanne ja arvataan toista numeroa, kunnes voitetaan tai joudutaan uudestaan arvaamaan. Omassa ohjelmassani jouduttiin kuitenkin käyttämään helpompaa ratkaisua, jolloin saavutettu tilanne luovutetaan backtrackerille ja annetaan sen hoitaa loput.

Tässä vaiheessa pitää hieman vielä selostaa analyysistä. Ensinnäkin, iteraatioiden määrä ei ole log(t). Se toimii ainoastaan hyvin karkeana arviona tarvittavien operaatioiden suhteesta tuntemattomien solujen määrään. Vakiokertoimet vaikuttavat kuitenkin suuresti algoritmin tehokkuuteen. Yksinkertaisimman ratkaisunkin hakemiseen nimittäin tarvitaan vähintään yksi iteraatio. Iteraatiot voivat olla kovinkin raskaita, jolloin sudokun "helppous" ei välttämättä näy suoritusajassa.

Kuten aiemmin mainittua, on tarkkaa aikavaativuutta erittäin vaikea arvioida tälle algoritmille.

### Dancing links
Määrittelydokumentissa mainittu dancing links -algoritmi on jätetty loppupalautuksesta pois, sillä sen toteutukseen ei ollut aikaa. Toteutus on jatkokehityslistan kärjessä.

### Tietorakenteet

#### Järjestämätön lista
Tarve järjestämättömälle listalle nousi ennakoivia sarjoja koodatessa. Lista on hvyin yksinkertainen ja toteuttaa ainoastaan ohjelmiston vaatimat toimenpiteet: `add`, `get`, `set`, `remove`, `size`, `copy` ja `empty`. Rakenteessa ei ole mitään sen ihmeellisempää.

#### Järjestetty uniikkien kokonaislukujen lista
Tässä listassa voi tallettaa vain uniikkeja kokonaislukuja. Lista toteuttaa metodit `add`, `get`, `contains` ja `size`. Lisättävä alkio hylätään, mikäli se jo löytyy listasta. `contains` on toteutettu binäärihaulla.

### Algoritmien vertailu
Tämä on yksi projektin mielenkiintoisimmista kohdista, ainakin itselleni: Miten aikavaativuudet näkyvät käytännössä. Ollakseni täysin rehellinen, yllätyin huomatessani Crookin tehokkuuden haastavien sudokujen parissa. Vaikeus on tässä tapauksessa vaikeustaso ihmisen sudokua ratkoessa.

Backtrackerille hitain löytämäni sudoku kesti hieman alle 500ms ratkoa. Crookilla tähän taas kului aikaa n. 30 ms, eli alle kymmenesosa. Ero on huomattava. Mutta mitä helpompiin sudokuihin siirryttiin, sitä paremmin backtrackeri rupesi pärjäämään. Helpoimmilla sudokuilla backtrackeri saattoi olla jopa nopeampi kuin Crookin algoritmi. Tämä johtuu Crookin massiivisista vakiokertoimista. Yhden iteraation aikana kun joudutaan tekemään niin monta operaatiota. Näihin verrattuna yksi, kaksi tai kaksisataa backtracking-kutsua näyttävät kevyiltä.

Pitää kuitenkin huomioida, että oikeat suoritusajat riippuvat merkittävissä määrin koneesta, jolla ohjelmaa suoritetaan ja taustalla pyörivästä ohjelmistosta. Vaihtelu suoritusajoissa voi olla sangen hurjaakin.

## Jatkokehitys
Ohjelman jatkokehityksen aloittaisin implementoimalla Dancing Linksin. Sen jälkeen kehittäisin käyttöliittymää niin, että sillä olisi oikeasti miellyttävää ratkoa sudokuja käsin. Sen jälkeen käyttäisin jonkin verran aikaa backtrackingin ja Crookin algoritmin optimoimiseen. Molempiin olen jo keksinyt parannuksia, joita en kuitenkaan ole ehtinyt implementoimaan, sillä toimiva on toimiva.

Kun nämä olisivat kunnossa, lisäisin ohjelmaan sudokugeneraattorin. Sudokugeneraattori mahdollistaisi kaksi asiaa. Ensinnäkin, tämän jälkeen kyseessähän olisi täysimittainen sudokupeli, jonka kanssa viettää oikeasti aikaa. Toisekseen, tällöin pystyisi ajamaan massoittain vertailevia testejä algoritmeille. Suoritusajat vaihtelevat runsaasti riippuen suodkusta ja algoritmista, joten olisi mielenkiintoista kerätä tilastoja suorituskyvyistä. Voisin esimerkiksi verrata, olisiko Crookin algoritmin kokonaisteho parempi, jos se vaihtaisikin jossain vaiheessa backtrackingiin (vrt kun on 10/5/3 ratkomatonta ruutua jäljellä).
