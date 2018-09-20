# Viikkoraportti 3

19.9.2018
Yhdistin kälin ja logiikan. Nyt pystyy sudokun teoriassa ratkomaan käsin. Huraa. Aikaa kului 2-3h.

20.9.2018.
Aamupäivä alkaa sillä, että SuokuExaminer luokkaan rakennettiin toiminnallisuus tarkastaa, onko syötetyssä sudokussa ilmiselviä virheitä. Ilmiselvillä virheillä tarkoitetaan sudokun ehtoja rikkovia tilanteita (duplikaatti rivillä/sarakkeella/3x3 solulla), mutta ei oteta huomioon tyhjiä paikkoja (pinnan alla nollia tai muita ei-1-9-välin arvoja). Konsoli myös herjaa, jos sudokuun syöttää käsin numeron, joka on laiton. Aikaa kului n. 45 minuuttia.

Tämän jälkeen Backtracking algoritmin toteutus. Tällä hetkellä, kun painaa "Solve" -nappia kälissä, ratkaisee kone näytöllä näkyvän sudokun. Valitettavasti (toistaiseksi) ratkaisu ei tule näkyviin graafiseen käyttöliittymään, vaan konsoliin. Ratkaisu on kuitenkin oikea. Ohjelmaa on jo testattu oikeassa käyttötilanteessa.
![Huijasin sudokussa][sudoku_cheat]
Käytin sovellusta parantaakseni laittomasti suoritustani puhelimestani löytyvässä sudokuapplikaatiossa. Aikaa meni edelleen jonkin verran, sillä jouduin käsin näppäilemään ratkottavan sudokun lähdekoodiin ja sitten konsolitulostuksen perusteella ratkaisun kännykkään. Aikaa tähään osioon meni n. 5 tuntia.





[sudoku_cheat]: ./pictures/sudoku_test.jpg
