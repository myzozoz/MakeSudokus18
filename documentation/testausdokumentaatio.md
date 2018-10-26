# Testausdokumentaatio
Ohjelmaa on kattavasti yksikkötestattu sekä hieman integraatiotestattu JUnitilla. Järjestelmätestausta on suoritettu käsin.

### Yksikkötestit
Kaikkia logiikkaluokkia on testattu kattavasti yksikkötesteillä. Etenkin SudokuExaminer luokalle on tehty laajasti testejä, sillä on kriittistä, että voimme olla varmoja luokan toimivuudesta. Luokan metodeja on testattu antamalla niille sudokuja, jotka täyttävät vain hyvin tietyt kriteerit.  

Kautta koodin esiintyy jonkin verraan `private` metodeja, joita ei ole suoraan yksikkötestattu. Näitä metodeja on testattu epäsuorasti testaamalla muita metodeja.

Käyttöliittymää ei ole testattu.

### Järjestelmätestaus
Olen käyttänyt sovellusta usean sudokun ratkomiseen. [Viikkoraportissa 3](./viikkoraportti3.md) on kuva siitä, kun ratkoin puhelimeni sudokuapplikaatiossa vaikeimman tason sudokun sovellukseni avulla ja sain huomattavasti parannettua aiempaa ennätystäni. Käyttöliittymän testaus toimii pitkälti käsin kokeilemalla.

### Algoritmien testaus ja vertailu
Algoritmien tehokkuutta on testattu käsin. Automatisoidakseni osaa työstä on luokkaan `SudokuController` kovakoodattu joitakin esimerkkisudokuja. Esimerkkisudokut on generoitu sudoku-applikaatiolla kännykälläni (Löytyy Google Play storesta nimellä *Sudoku - The Clean One*), josta löytyy neljä vaikeustasoa: Easy, Normal, Hard ja Extreme. Mielenkiintoisimmat näistä ovat ääripäät, eli easy ja extreme. Ajankulutukset tulostetaan ajettaessa konsoliin.
