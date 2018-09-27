# Viikkoraportti 4

27.9.2018
Neljännellä viikolla olen saanut hiottua taas käyttöliittymää ja sen lisäksi saanut aloitettua Crookin algoritmin toteutuksen.

Aloitin aivan ensimmäisenä korjaamalla asian, joka oli vaivannut minua jo alusta asti: sain ratkotun sudokun päivittymään näytölle. Ratkaisu ei ollut kauhean elegantti, mutta toimiva. Pinnan alla sovellus alustaa melkein koko käyttöliittymän uudestaan, kun ratkaisu- tai resetnappia painetaa.

Ainiin. Tein myös resetnapin. Se tehostaa jonkin verran sovelluksen käyttöä, kun sitä ei tarvitse koko ajan olla uudelleenkäynnistämässä. Toiminnallisuuden toteuttaminen oli kohtuullisen suoraviivaista. Käyttöliittymässä on myös kaksi erillistä nappia riippuen siitä, millä algoritmilla sovellusta halutaan ratkoa.

Niinkuin ylhäällä lukee, on Crookin algoritmi jäänyt vähän vaiheeseen. Nyt kun palautuksia on kaksi jäljellä, olen kuitenkin luottavainen, että saan sen ja Dancing Links -algoritmin toteutettua. Sen lisäksi Crookin algoritmin tässä vaiheessa implementoitua toiminnallisuutta voi jo testatata nappia painamalla. Sovellus löytää eliminointimenetelmällä tilanteet, joissa ruutuun ei voi laittaa muita, kuin yhden luvun. Algoritmi myös sijoitta luvun ruutuun.

Nyt kun nappia painaa, voikin huomata, että numeroita pomppaa usea kappale lisää ruudulle. Ainakin helpommilla sudokuilla. Koodista löytyvä `EXAMPLE_SUDOKU_EASY` ratkeaa peräti viidellä napinpainalluksella, mikä herättää suuria toiveita algoritmin tehokkuuden osalta. Tällä hetkellä vaikeammat sudokut eivät kuitenkaan ratkea ollenkaan tällä algoritmin tyngällä, vaan nappia hakkaamalla sakkaa etenemistahti äkkiä.

Sen lisäksi olen tällä viikolla hakannut tietenkin dokumentaatiota sekä testejä. Kummassakaan ei ole mitään hirveän kummallista, mutta testausdokumentaatiota en ainakaan tänään ehdi kauheasti enempää tekemään. Minulla on muutto ylihuomenna, enkä ole ehtinyt pakkaamista juuri aloittakaan koulukiireiden takia. Olen silti erittäin tyytyväinen tähänastiseen edistykseeni ja odotan innolla, ensi viikkoa, jolloin kalenterin mukaan tulisi olla enemmän aikaa istahtaa alas sovelluksen kanssa.

Aikaa tällä viikolla olen projektin parissa kuluttanut n. 10 tuntia. 
