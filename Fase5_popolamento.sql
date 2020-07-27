USE impresa;

DELETE FROM personale;
insert into personale(CF, nome, cognome, professione, stipendio, via, citta, CAP) values ('FG57S2R7B5S7F650', 'Lucia', 'Biondi', 'Dirigente', 1700.54, 'Garibaldi', 'Milano', 47827);
insert into personale(CF, nome, cognome, professione, stipendio, via, citta, CAP) values ('DF78G65FVFD4F7BF', 'Luigi', 'Verdi', 'Operaio', 1500.74, 'Liberta', 'Genova', 53768);
insert into personale(CF, nome, cognome, professione, stipendio, via, citta, CAP) values ('DSAF87C68DCV78FV', 'Bruno', 'Ciani', 'Quadro', 1870.22, 'Duomo', 'Bologna', 75446);
insert into personale(CF, nome, cognome, professione, stipendio, via, citta, CAP) values ('YTIJK7YN4GH68478', 'Giacomo', 'De Conti', 'Operaio', 1500.74, 'Spina', 'Milano', 47827);
insert into personale(CF, nome, cognome, professione, stipendio, via, citta, CAP) values ('5S4DF688DV4GFG78', 'Matteo', 'Fiore', 'Operaio', 1500.74, 'Giusti', 'Genova', 53768);
insert into personale(CF, nome, cognome, professione, stipendio, via, citta, CAP) values ('GNT9YH8RS7GDFG76', 'Giovanni', 'Monti', 'Operaio', 1500.74, 'Viraldi', 'Roma', 16785);
insert into personale(CF, nome, cognome, professione, stipendio, via, citta, CAP) values ('Q5DSF8768E7F65F7', 'Lorenzo', 'Nero', 'Operaio', 1500.74, 'Ferri', 'Milano', 78424);
insert into personale(CF, nome, cognome, professione, stipendio, via, citta, CAP) values ('247FR767GR688G7G', 'Alessandro', 'Pagani', 'Operaio', 1500.74, 'Morelli', 'Genova', 53768);
insert into personale(CF, nome, cognome, professione, stipendio, via, citta, CAP) values ('VD7F65VFD78SAD7F', 'Giorgio', 'Ciani', 'Quadro', 1870.22, 'Croce', 'Milano', 47827);
insert into personale(CF, nome, cognome, professione, stipendio, via, citta, CAP) values ('UK7JF8G7BFFDG887', 'Paolo', 'Sala', 'Quadro', 1870.22, 'Duomo', 'Bologna', 75446);
insert into personale(CF, nome, cognome, professione, stipendio, via, citta, CAP) values ('NGHN7HF87V7DVDS6', 'Carlo', 'Valentini', 'Quadro', 1870.22, 'Spina', 'Roma', 16785);
insert into personale(CF, nome, cognome, professione, stipendio, via, citta, CAP) values ('MTTGSADG6878V8DD', 'Aurora', 'Biondi', 'Impiegato', 1500.14, 'Garibaldi', 'Milano', 47827);
insert into personale(CF, nome, cognome, professione, stipendio, via, citta, CAP) values ('AFES7G687RBF6878', 'Giovanni', 'Fortunato', 'Impiegato', 1500.14, 'Lancia', 'Genova', 53768);

DELETE FROM cliente;
insert into cliente(telefono, CF, nome, cognome, via, citta, CAP) values (781697457, 'B6EGSFV6D7VDV45D', 'Michele', 'Sardo', 'Valentini', 'Salerno', 157545);
insert into cliente(telefono, CF, nome, cognome, via, citta, CAP) values (298778080, 'WETER687H68TRHN9', 'Veronica', 'Di Spezia', 'Verdi', 'Rimini', 8684868);
insert into cliente(telefono, CF, nome, cognome, via, citta, CAP) values (208787756, 'LY8F7H87XF7G8DF7', 'Maria', 'Luonghi', 'Fiore', 'Roma', 16785);
insert into cliente(telefono, CF, nome, cognome, via, citta, CAP) values (378169745, 'IUYJ8G7B68GF8FBD', 'Mario', 'Monte', 'Emanuaele', 'Milano', 47827);

DELETE FROM cantiere;
insert into cantiere(codice, valore, localita, `#personale`) values (51968, 57860.57, 'Milano', 2);
insert into cantiere(codice, valore, localita, `#personale`) values (61981, 789624.77, 'Genova', 2);

DELETE FROM `ente locale`;
insert into `ente locale`(telefono) values(816767576);
insert into `ente locale`(telefono) values(128457688);

DELETE FROM permesso;
insert into permesso(stato, `codice cantiere`, `telefono ente locale`) values('concesso', 51968, 816767576);

DELETE FROM magazzino;
insert into magazzino(telefono, via, citta, CAP) values (687654537, 'Garibaldi', 'Milano', 47827);
insert into magazzino(telefono, via, citta, CAP) values (287557778, 'Valentini', 'Genova', 53768);

DELETE FROM prodotto;
insert into prodotto(codice, nome, prezzo, `data acquisto`, `telefono magazzino`) values (8404817, 'Martello', 27.69, '2020-11-01', 687654537);
insert into prodotto(codice, nome, prezzo, `data acquisto`, `telefono magazzino`) values (1897575, 'Trapano', 78.00, '2019-08-07', 687654537);
insert into prodotto(codice, nome, prezzo, `data acquisto`, `telefono magazzino`) values (2757, 'Cemento', 789.24, '2020-12-03', 287557778);
insert into prodotto(codice, nome, prezzo, `data acquisto`, `telefono magazzino`) values (16897, 'Piastra', 144.87, '2018-01-09', 287557778);
insert into prodotto(codice, nome, prezzo, `data acquisto`, `telefono magazzino`) values (888545, 'Legno', 88.99, '2019-08-12', 687654537);
insert into prodotto(codice, nome, prezzo, `data acquisto`, `telefono magazzino`) values (3597, 'Porta', 49.99, '2017-04-07', 287557778);

DELETE FROM fornitore;
insert into fornitore(telefono, nome, `partita IVA`) values (876878768, 'FornitureAndCo', 'GB569712547');
insert into fornitore(telefono, nome, `partita IVA`) values (157535747, 'CompanyForniture', 'IT946572494');

DELETE FROM veicolo;
insert into veicolo(targa, modello,  `anno fabbricazione`) values ('FD845NG', 'IVECO-78578', '2002');
insert into veicolo(targa, modello,  `anno fabbricazione`) values ('ER457LK', 'IVECO-78578', '2002');
insert into veicolo(targa, modello,  `anno fabbricazione`) values ('OP658VB', 'MAN-GY5754', '2007');
insert into veicolo(targa, modello,  `anno fabbricazione`) values ('MN385GE', 'IVECO-367447', '2009');
insert into veicolo(targa, modello,  `anno fabbricazione`) values ('PL249SH', 'MAN-GY5754', '2007');
insert into veicolo(targa, modello,  `anno fabbricazione`) values ('LE083NN', 'FORD-MG75754', '2018');

DELETE FROM telefono;
insert into telefono(telefono, `CF personale`) values (48765687, '247FR767GR688G7G');
insert into telefono(telefono, `CF personale`) values (68945465, '247FR767GR688G7G');
insert into telefono(telefono, `CF personale`) values (28981418, 'DF78G65FVFD4F7BF');

DELETE FROM `ha responsabile`;
insert into `ha responsabile`(`codice cantiere`, `CF personale`) values (51968, 'DSAF87C68DCV78FV');
insert into `ha responsabile`(`codice cantiere`, `CF personale`) values (61981, 'FG57S2R7B5S7F650');

DELETE FROM `ha caposquadra`;
insert into `ha caposquadra`(`codice cantiere`, `CF personale`) values (51968, 'DSAF87C68DCV78FV');
insert into `ha caposquadra`(`codice cantiere`, `CF personale`) values (61981, 'VD7F65VFD78SAD7F');

DELETE FROM `ha personale`;
insert into `ha personale`(`codice cantiere`, `CF personale`) values (51968, 'YTIJK7YN4GH68478');
insert into `ha personale`(`codice cantiere`, `CF personale`) values (61981, 'GNT9YH8RS7GDFG76');
insert into `ha personale`(`codice cantiere`, `CF personale`) values (51968, 'Q5DSF8768E7F65F7');
insert into `ha personale`(`codice cantiere`, `CF personale`) values (61981, 'DF78G65FVFD4F7BF');

DELETE FROM `puo guidare`;
insert into `puo guidare`(`CF personale`, `targa veicolo`) values ('5S4DF688DV4GFG78', 'FD845NG');
insert into `puo guidare`(`CF personale`, `targa veicolo`) values ('DSAF87C68DCV78FV', 'FD845NG');
insert into `puo guidare`(`CF personale`, `targa veicolo`) values ('5S4DF688DV4GFG78', 'MN385GE');
insert into `puo guidare`(`CF personale`, `targa veicolo`) values ('GNT9YH8RS7GDFG76', 'FD845NG');
insert into `puo guidare`(`CF personale`, `targa veicolo`) values ('5S4DF688DV4GFG78','LE083NN');

DELETE FROM `e commissionato`;
insert into `e commissionato`(`codice cantiere`, `telefono cliente`) values (51968, 781697457);
insert into `e commissionato`(`codice cantiere`, `telefono cliente`) values (61981, 378169745);

DELETE FROM usa;
insert into usa(`codice cantiere`, `codice prodotto`) values (51968, 2757);
insert into usa(`codice cantiere`, `codice prodotto`) values (51968, 1897575);
insert into usa(`codice cantiere`, `codice prodotto`) values (61981, 3597);
insert into usa(`codice cantiere`, `codice prodotto`) values (61981, 16897);

DELETE FROM `e fornito`;
insert into `e fornito`(`codice prodotto`, `telefono fornitore`) values (8404817, 876878768);
insert into `e fornito`(`codice prodotto`, `telefono fornitore`) values (1897575, 876878768);
insert into `e fornito`(`codice prodotto`, `telefono fornitore`) values (2757, 157535747);
insert into `e fornito`(`codice prodotto`, `telefono fornitore`) values (16897, 876878768);
insert into `e fornito`(`codice prodotto`, `telefono fornitore`) values (888545, 157535747);
insert into `e fornito`(`codice prodotto`, `telefono fornitore`) values (3597, 157535747);

DELETE FROM impiega;
insert into impiega(`codice cantiere`, `targa veicolo`) values (51968, 'FD845NG');
insert into impiega(`codice cantiere`, `targa veicolo`) values (51968, 'MN385GE');
insert into impiega(`codice cantiere`, `targa veicolo`) values (61981, 'LE083NN');