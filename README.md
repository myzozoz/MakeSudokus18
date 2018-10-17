# MakeSudokus18
Tiralabran projekti. Sudokujen ratkontatyökalu.

[Linkki määrittelydokumenttiin](/documentation/maarittely.md)

[Testausdokumentaatio](/documentation/testausdokumentti.md)

[Toteutusdokumentaatio](/documentation/toteutusdokumentti.md)


#### Viikkoraportit
- [Viikkoraportti 1](/documentation/viikkoraportti1.md)
- [Viikkoraportti 2](/documentation/viikkoraportti2.md)
- [Viikkoraportti 3](/documentation/viikkoraportti3.md)
- [Viikkoraportti 4](/documentation/viikkoraportti4.md)
- [Viikkoraportti 5](/documentation/viikkoraportti5.md)
- [Viikkoraportti 6](/documentation/viikkoraportti6.md)

#### Muuta kivaa
Rakenna projekti ajamalla `gradle build` projektin juurihakemistossa.

Ajaminen toimii gradlea tukevalla IDE:llä tai ajamalla `gradle run`.

JavaDoc generoidaan komennolla `gradle javadoc`, minkä jälkeen dokumentit löytyvät hakemistosta `/build/docs/javadoc/index.html`.

#### Sovelluksen käyttö lyhyesti
Jos haluat syöttää oman sudokusi sovellukseen, on siihen muutama vaihtoehto. Molemmat vaativat pientä lähdekoodin muokkausta

Etsi luokka `makesudokus.logic.SudokuController` ja siellä näet rivin `this.sudoku.setContent(JOKU_SUDOKU);`. Voit nyt laittaa `JOKU_SUDOKU`:n tilalle omavalintaisen sudokun. Olen itse testisyistä tehnyt muutamaan luokkamuuttujaan esimerkkisudokuja.

Rivin voi myös kommentoida pois, jolloin sudoku on käynnistäessä tyhjä. Voit sitten syöttää kälin kautta oman sudokusi.

HUOM: Teoriassa voit antaa sovelluksen käynnistyä esimerkkisudokun kanssa, ja sitten vaihtaa siihen tilalle oman sudokun. Sovellus osaa ratkaista ainoastaan laillisia sudokuja, joten olet itse vastuussa omista syötteistäsi.
