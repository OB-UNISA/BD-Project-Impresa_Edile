DROP database IF EXISTS impresa;
CREATE database impresa;
USE impresa;

CREATE TABLE cantiere (
    codice INT PRIMARY KEY,
    valore DOUBLE NOT NULL,
    localita VARCHAR(20) NOT NULL,
    `#personale` INT DEFAULT '0'
);

CREATE TABLE `ente locale` (
    telefono INT PRIMARY KEY
);

CREATE TABLE personale (
    CF CHAR(16) PRIMARY KEY,
    nome VARCHAR(15) NOT NULL,
    cognome VARCHAR(15) NOT NULL,
    professione VARCHAR(20) NOT NULL,
    stipendio DOUBLE NOT NULL,
    via VARCHAR(20) NOT NULL,
    citta VARCHAR(20) NOT NULL,
    CAP INT NOT NULL
);

CREATE TABLE cliente (
    telefono INT PRIMARY KEY,
    CF CHAR(16) NOT NULL,
    nome VARCHAR(15) NOT NULL,
    cognome VARCHAR(15) NOT NULL,
    via VARCHAR(20) NOT NULL,
    citta VARCHAR(20) NOT NULL,
    CAP INT NOT NULL
);

CREATE TABLE magazzino (
    telefono INT PRIMARY KEY,
    via VARCHAR(20) NOT NULL,
    citta VARCHAR(20) NOT NULL,
    CAP INT NOT NULL
);

CREATE TABLE prodotto (
    codice INT PRIMARY KEY,
    nome VARCHAR(20) NOT NULL,
    prezzo DOUBLE NOT NULL,
    `data acquisto` DATE NOT NULL,
    `telefono magazzino` INT NOT NULL,
    FOREIGN KEY (`telefono magazzino`)
        REFERENCES magazzino (telefono)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE fornitore (
    telefono INT PRIMARY KEY,
    nome VARCHAR(30) NOT NULL,
    `partita IVA` CHAR(11) NOT NULL
);
    
CREATE TABLE veicolo (
    targa VARCHAR(10) PRIMARY KEY,
    modello VARCHAR(30) NOT NULL,
    `anno fabbricazione` YEAR NOT NULL
);

CREATE TABLE telefono (
    telefono INT NOT NULL,
    `CF personale` CHAR(16) NOT NULL,
    PRIMARY KEY (telefono),
    FOREIGN KEY (`CF personale`)
        REFERENCES personale (CF)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE permesso (
    stato ENUM('negato', 'lavorazione', 'concesso') NOT NULL,
    `codice cantiere` INT PRIMARY KEY,
    `telefono ente locale` INT NOT NULL,
    FOREIGN KEY (`codice cantiere`)
        REFERENCES cantiere (codice)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`telefono ente locale`)
        REFERENCES `ente locale` (telefono)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `ha responsabile` (
    `codice cantiere` INT PRIMARY KEY,
    `CF personale` CHAR(16) NOT NULL,
    FOREIGN KEY (`codice cantiere`)
        REFERENCES cantiere (codice)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`CF personale`)
        REFERENCES personale (CF)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `ha caposquadra` (
    `codice cantiere` INT PRIMARY KEY,
    `CF personale` CHAR(16) NOT NULL,
    FOREIGN KEY (`codice cantiere`)
        REFERENCES cantiere (codice)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`CF personale`)
        REFERENCES personale (CF)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `ha personale` (
    `codice cantiere` INT NOT NULL,
    `CF personale` CHAR(16) NOT NULL,
    PRIMARY KEY (`codice cantiere` , `CF personale`),
    FOREIGN KEY (`codice cantiere`)
        REFERENCES cantiere (codice)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`CF personale`)
        REFERENCES personale (CF)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `puo guidare` (
    `CF personale` CHAR(16) NOT NULL,
    `targa veicolo` VARCHAR(10) NOT NULL,
    PRIMARY KEY (`CF personale` , `targa veicolo`),
    FOREIGN KEY (`CF personale`)
        REFERENCES personale (CF)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`targa veicolo`)
        REFERENCES veicolo (targa)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `e commissionato` (
    `codice cantiere` INT NOT NULL,
    `telefono cliente` INT NOT NULL,
    PRIMARY KEY (`codice cantiere`),
    FOREIGN KEY (`codice cantiere`)
        REFERENCES cantiere (codice)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`telefono cliente`)
        REFERENCES cliente (telefono)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE usa (
    `codice cantiere` INT NOT NULL,
    `codice prodotto` INT NOT NULL,
    PRIMARY KEY (`codice prodotto`),
    FOREIGN KEY (`codice cantiere`)
        REFERENCES cantiere (codice)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`codice prodotto`)
        REFERENCES prodotto (codice)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `e fornito` (
    `codice prodotto` INT NOT NULL,
    `telefono fornitore` INT NOT NULL,
    PRIMARY KEY (`codice prodotto` , `telefono fornitore`),
    FOREIGN KEY (`codice prodotto`)
        REFERENCES prodotto (codice)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`telefono fornitore`)
        REFERENCES fornitore (telefono)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE impiega (
    `codice cantiere` INT NOT NULL,
    `targa veicolo` VARCHAR(10) NOT NULL,
    PRIMARY KEY (`targa veicolo`),
    FOREIGN KEY (`codice cantiere`)
        REFERENCES cantiere (codice)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`targa veicolo`)
        REFERENCES veicolo (targa)
        ON DELETE CASCADE ON UPDATE CASCADE
);