# Testausdokumentaatio
Tähän mennessä ohjelmaa on lähinnä yksikkötestattu JUnitilla ja järjestelmätestattu käsin.

### Yksikkötestit
Suurinta osaa logiikkaluokista on testattu kattavasti yksikkötesteillä. Etenkin SudokuExaminer luokalle on tehty laajasti testejä, sillä on kriittistä, että voimme olla varmoja luokan toimivuudesta. Luokan metodeja on testattu antamalla niille sudokuja, jotka täyttävät vain hyvin tietyt kriteerit.  

Olen yrittänyt pitää isoa osaa logiikasta Crookin algoritmin osiossa private -muodossa. Tämä tapahtuu sen hinnalla, että suoria yksikkötestejä on vaikeampi tehdä.

Käyttöliittymää ei ole testattu.

### Järjestelmätestaus
Olen käyttänyt sovellusta usean sudokun ratkomiseen. [Viikkoraportissa 3](./viikkoraportti3.md) on kuva siitä, kun ratkoin puhelimeni sudokuapplikaatiossa vaikeimman tason sudokun sovellukseni avulla ja sain huomattavasti parannettua aiempaa ennätystäni. Käyttöliittymän testaus toimii pitkälti käsin kokeilemalla.
