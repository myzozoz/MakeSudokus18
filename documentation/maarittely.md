# Sudokuratkoja
Tavoitteena on tehdä sovellus, joka ratkoo minkä tahansa sudokuongelman. Aikomuksena on käyttää kolmea eri algoritmia ja vertailla näiden aikavaativuuksia.

[Mikä on sudoku?](https://en.wikipedia.org/wiki/Sudoku)

### Algoritmit ja tietorakenteet
##### Tietorakenteet
Sudokuhan itsessään on vain kaksiulotteinen taulukko täynnä numeroita. Sudokun tilaa lienee siis järkevintä tallettaa kaksiulotteisessa listassa. Tätä tietorakennetta ei ole tarvetta järjestää eikä sinne ole tarvetta tehdä hakuja sisällön perusteella, joten voidaan käyttää niinkin yksinkertaista rakennetta kuin kaksiulotteista taulukkoa.

Sudokua ratkoessa tarvitsemme kuitenkin aputietorakenteita. Esimerkiksi [Crooken algoritmi](http://www.ams.org/notices/200904/rtx090400460p.pdf) vaatii sen, että voimme tallettaa jokaisen yksittäisen solun tilaa. Tila tarkoittaa tässä tapauksessa sitä, montako mahdollista numeroa solulla on kullakin hetkellä. Kun mahdollisuuksia on enää 1, tarkoittaa se että tiedämme varmasti, mikä luku soluun tulee. Tässä tapauksessa tarvitsemme listaa, johon mahtuu vähintään yhdeksän alkiota, mutta alkioiden määrä tulee vaihtelemaan.

Tästä listasta halutaan todennäköisesti tietää, onko siellä jokin tietty alkio. Sen lisäksi meitä kiinnostaa mitkä kaikki alkiot listassa vielä ovat. Nopein vaihtoehto lienee siis taulukollinen booleaneita, jotka merkitsevät onko oman järjestysnumeronsa +1 luku mahdollinen soluun.

Donald Knuth käyttää Dancing Links -algoritmissaan matriisia, joka koostuu kaksiulotteista linkitetyistä listoista. Tätä varten pitää siis implementoida linkitetyn listan tietorakenne.

##### Algoritmit
Tavoite on ratkoa sudokuja kolmella eri algoritmilla. Ensimmäinen on backtracking, eli raa'an voiman lähestyminen. Syvyyssuuntainen haku aloittaa ensimmäisestä tuntemattomasta ruudusta ja täyttää ruutuja, kunnes havaitsee virheen. Tällöin otetaan viimeisin lisäys pois ja kokeillaan sen tilalle jotain muuta, kunnes kaikki vaihtoehdot on käyty läpi. Jos mikään vaihtoehto ei toimi, mennään vielä yksi askel takaisinpäin. Algoritmi ei ole kovin nopea, mutta helppo toteuttaa.

Toinen ratkaisu on yllä linkattu Crooken algoritmi. Sen tavoite on rajata mahdollisuuksia, missä mikäkin luku voi olla. Tämä algoritmi on suunniteltu toteutettavaksi kynällä ja paperilla, joten se lienee lähimpänä ihmisen käyttämää intuitiivista lähestymistä ongelmaan.

Kolmas käytettävä ratkaisumenetelmä on Donald Knuthin ["Dancing Links" -algoritmi](https://www.ocf.berkeley.edu/~jchu/publicportal/sudoku/0011047.pdf). Algoritmi on "älykkäämpi" raa'an voiman ratkaisumenetelmä, joka perustuu oletukseen, että numeroilla on tasan yksi laillinen järjestys.

Algoritmit on valittu seuraavalla perusteella: Backtracking on yksinkertainen toteuttaa ja tarjoaa pohjatason muille algoritmeille. Crooken algoritmi vaikuttaa hauskimmalta toteuttaa, sillä se mukailee ihmismäistä päättelyä. Sinne voi myös yrittää rakentaa jotain optimointeja, kuten vaihto backtrackingiin, kun on enää tietty määrä tuntemattomia ruutuja jäljellä. Dancing links taas on hienostunein ja "kaunein" ratkaisu ongelmaan. Googlailun jälkeen se vaikuttaisi myös olevan suosituin tehokas ratkaisu tilanteeseen.


### Aikavaativuus
Backtrackingin aikavaativuus on luokkaa O(n^2). Kyseessä on hyvin yksinkertainen raa'an voiman lähestymistapa.

Crooken algoritmin aikavaativuuden arviointi on huomattavasti vaikeampaa. Käytännössä algoritmilla pompitaan useamman vähennyksen välillä ja toivotaan parasta. Aikavaativuus tippuu edelleen O(n^2) -luokkaan, mutta vakiokertoimet ovat pienemmät, kuin backtrackingissä.

Dancing Linksin aikavaativuus on pahimmillaan myös O(n^2), mutta on käytännössä huomattavasti nopeampi, kuin backtracking.

### Lähteet
1. [Donald Knuthin paperi Dancing Linksistä](https://www.ocf.berkeley.edu/~jchu/publicportal/sudoku/0011047.pdf)
2. [Wikipedia, Sudoku](https://en.wikipedia.org/wiki/Sudoku)
3. [Crooken Algoritmi](http://www.ams.org/notices/200904/rtx090400460p.pdf)
