# Käyttöohje
Projekti käyttää riippuvaisuuksien hallintaan gradlea. Jos haluat rakentaa lähdekoodia, pidä siis huoli, että omaat gradlen koneellasi. Projektia tehdessä on käytetty versiota 4.10., mutta arvioisin kaikkien 4+ versioiden toimivan.

### Hyödyllisiä komentoja
Aja kukin komento projektin juurihakemistossa.
- Rakentaminen toimii konsolista yksinkertaisesti komennolla `gradle build`.
- Ohjelman voi ajaa komennolla `gradle run`.
- `gradle jar` luo suoritettavan .jar -tiedoston kansioon `/build/libs/`.
- JavaDocin saa luotua ajamalla komennon `gradle javadoc`.
- Testit voi ajaa erikseen komennolla `gradle test`, vaikkakin gradle ei kauhean verbaalisti testejä kommentoikaan.

### JAR
Suorittamista vaille valmis .jar löytyy hakemistosta `/jar`.

### Sovelluksen käyttö lyhyesti
##### Sudokun syöttäminen
Jos haluat syöttää oman sudokusi sovellukseen, on siihen muutama vaihtoehto. Molemmat vaativat pientä lähdekoodin muokkausta

Etsi luokka `makesudokus.logic.SudokuController` ja siellä näet rivin `this.sudoku.setContent(JOKU_SUDOKU);`. Voit nyt laittaa `JOKU_SUDOKU`:n tilalle omavalintaisen sudokun. Olen itse testisyistä tehnyt muutamaan luokkamuuttujaan esimerkkisudokuja.

Rivin voi myös kommentoida pois, jolloin sudoku on käynnistäessä tyhjä. Voit sitten syöttää kälin kautta oman sudokusi.

Teoriassa voit antaa sovelluksen käynnistyä esimerkkisudokun kanssa, ja sitten vaihtaa siihen tilalle oman sudokun. Sovellus osaa ratkaista ainoastaan laillisia sudokuja, joten olet itse vastuussa omista syötteistäsi. Kumpikaan algoritmeista ei osaa kertoa, onko sudoku ratkottavissa, sillä se on NP -täydellinen ongelma, eikä projektin mittakaavassa. Jos sudoku on syötetty virheellisesti, kaataa Crookin algoritmi ohjelman. Backtracker taas ei yksinkertaisesti syötä mitään ohjelmaan eikä kyllä herjaakaan mitään.

##### Ohjelman muu käyttö
Aja ohjelma haluamallasi tavalla, joko IDE:stä käsin, komentoriviltä gradlella tai ajamalla suoritettava .jar-tiedosto. Sen jälkeen, jos ratkottava sudoku on jo syötetty ohjelmaan, paina vain kummalla algoritmilla haluat ajaa ohjelman. "Reset" palauttaa sudokun siihen tilaan, missä se oli ohjelman käynnistyessä.

### JavaDocin
Javadocin voi generoida ite, jolloin se löytyy build-kansion alta. Muulloin sen löytää valmiiksi generoituna `documentation/javadoc`-hakemistosta. Avaa `index.html` haluamallasi selaimella. 
