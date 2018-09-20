# Viikkoraportti 3

19.9.2018
Yhdistin kälin ja logiikan. Nyt pystyy sudokun teoriassa ratkomaan käsin. Huraa. Aikaa kului 2-3h.

20.9.2018.
Aamupäivä alkaa sillä, että SuokuExaminer luokkaan rakennettiin toiminnallisuus tarkastaa, onko syötetyssä sudokussa ilmiselviä virheitä. Ilmiselvillä virheillä tarkoitetaan sudokun ehtoja rikkovia tilanteita (duplikaatti rivillä/sarakkeella/3x3 solulla), mutta ei oteta huomioon tyhjiä paikkoja (pinnan alla nollia tai muita ei-1-9-välin arvoja). Konsoli myös herjaa, jos sudokuun syöttää käsin numeron, joka on laiton. Aikaa kului n. 45 minuuttia.

Tämän jälkeen Backtracking algoritmin toteutus. Tällä hetkellä, kun painaa "Solve" -nappia kälissä, ratkaisee kone näytöllä näkyvän sudokun. Valitettavasti (toistaiseksi) ratkaisu ei tule näkyviin graafiseen käyttöliittymään, vaan konsoliin. Ratkaisu on kuitenkin oikea. Ohjelmaa on jo testattu oikeassa käyttötilanteessa. ![Huijasin sudokussa][sudoku_cheat] Käytin sovellusta parantaakseni laittomasti suoritustani puhelimestani löytyvässä sudokuapplikaatiossa. Aikaa meni edelleen jonkin verran, sillä jouduin käsin näppäilemään ratkottavan sudokun lähdekoodiin ja sitten konsolitulostuksen perusteella ratkaisun kännykkään. Aikaa tähään osioon meni n. 5 tuntia.

Oli sen verran hyvä mieli päivän työn jälkeen, että päätin sitten heittää päätä seinään JavaFX:n kanssa. Tavoitteena oli siis saada ruudullakin näkyvät numerot päivittymään ratkaisun myötä. Tätä varten loin vähän omia tapahtumankäsittelijöitä ja tapahtumatyyppejä ainoastaan tullakseni torpatuksi päästyäni omasta mielestä aika lähelle vastausta. Tähän tuskaan käytin ilokseni noin 1.5h.

Vähän ikävästi jäi suurin osa työstä tälle päivälle. Saa nähdä jatkanko vielä huomenna (perjantaina) projektin työstöä, mutta tällä hetkellä aikataulu näyttää sangen täydeltä. Vaikka summattuna tähänastisista merkatuista tunneista tulee 10h, uskon todellisuudessa luvun olevan hieman isompi. Olen nimittäin työstänyt projektissa muitakin pienempiä ominaisuuksia (kuten dokumentaatiota), jota en välttämättä ole aina laskenut mukaan.


Kaikenkaikkiaan olen tyytyväinen tällä viikolla tapahtuneeseen edistykseen. Ohjelma ratkoo nyt vähintään jollain tapaa sudokuja, joten omalla tavalla ollaan saavutettu MVP. Tästä mennään enää ylöspäin. Seuraavalla viikolla yritän saavuttaa samanlaisen tasapainon ajankäytön suhteen: n. 1/4 ajasta käyttöliittymään ja sovelluksen käytettävyyteen ja loput ajasta sovelluksen logiikkaan.



[sudoku_cheat]: ./pictures/sudoku_test.jpg
