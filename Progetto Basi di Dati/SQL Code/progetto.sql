CREATE SCHEMA progettoBd;
USE progettoBd;
CREATE TABLE circuito (
		numerocurve 		NUMERIC NOT NULL,
        lunghezzacircuito	NUMERIC NOT NULL,
        paese				VARCHAR(30) NOT NULL,
        nome 				VARCHAR(30) PRIMARY KEY
	);
    
CREATE TABLE gara (
		circuito 			VARCHAR(30),
		nomegara			VARCHAR(30) PRIMARY KEY,
		datagara			DATE NOT NULL,
		durata				NUMERIC NOT NULL,
        tipo				CHAR(9) NOT NULL CHECK (tipo='bagnato'OR tipo='asciutto'),
        FOREIGN KEY(circuito) REFERENCES circuito(nome)
			ON DELETE RESTRICT
            ON UPDATE CASCADE
    );
CREATE TABLE scuderia(
		nome				VARCHAR(30) PRIMARY KEY,
        paesesede			VARCHAR(30) NOT NULL
    );
CREATE TABLE vettura(
		modello				VARCHAR(40) NOT NULL,
        numerogara			CHAR(3) PRIMARY KEY,
        scuderia			VARCHAR(40),
        FOREIGN KEY(scuderia) REFERENCES scuderia(nome)
			ON DELETE RESTRICT
            ON UPDATE CASCADE
	);
    CREATE TABLE competizione(
		motivoritiro VARCHAR(10) CHECK (motivoritiro IN ('incidente', 'guasto meccanico', 'squalifica')),
		puntipiazzamento NUMERIC NOT NULL,
		CHECK (
		(motivoritiro IN ('incidente', 'guasto meccanico', 'squalifica') AND puntipiazzamento = 0) OR
		(motivoritiro IS NULL AND puntipiazzamento IS NOT NULL)),
        gara				VARCHAR(30),
        vettura				CHAR(3),
        FOREIGN KEY(gara) REFERENCES gara(nomegara)
			ON DELETE SET NULL
            ON UPDATE CASCADE,
		FOREIGN KEY(vettura) REFERENCES vettura(numerogara)
			ON DELETE RESTRICT
            ON UPDATE CASCADE
	);
CREATE TABLE costruttore(
		nome				VARCHAR(30) NOT NULL,
        numerocomponenti	NUMERIC NOT NULL,
        ragionesociale		VARCHAR(30) PRIMARY KEY,
        via					VARCHAR(30) NOT NULL,
        città				VARCHAR(30) NOT NULL,
        cap 				CHAR(5) NOT NULL
	);

CREATE TABLE componente(
		tipo				CHAR(6) NOT NULL CHECK (tipo='motore' OR tipo='cambio' OR tipo='telaio'),
        costospecifico		NUMERIC NOT NULL,
        peso				NUMERIC,
		numerocilindri		NUMERIC,
        tipomotore			VARCHAR(8)	CHECK (tipomotore='turbo' OR tipomotore='aspirato'),
        materiale			VARCHAR(30),
        codice				CHAR(6),
        vettura				CHAR(3),
        cilindrata			NUMERIC,
        numeromarce			NUMERIC		CHECK (numeromarce=7 OR numeromarce=8),
        costruttore			VARCHAR(30),
        FOREIGN KEY(vettura) REFERENCES vettura(numerogara)
			ON DELETE RESTRICT
            ON UPDATE CASCADE,
		FOREIGN KEY(costruttore) REFERENCES costruttore(ragionesociale)
			ON DELETE RESTRICT 
            ON UPDATE CASCADE,
		PRIMARY KEY (vettura, codice)
);
    
CREATE TABLE pilota(
		scuderia			VARCHAR(30),
        vettura				CHAR(3),
        nome				VARCHAR(30),
        datanascita			DATE,
        cognome				VARCHAR(30),
        tipo				CHAR(4) NOT NULL CHECK (tipo='GM'OR tipo='NULL'),
        numerolicenze		NUMERIC,
		dataprimalicenza	DATE,
        nazionalità			VARCHAR(30) NOT NULL,
        categoria			CHAR(3) NOT NULL CHECK (categoria='PRO'OR categoria='AM'),
		FOREIGN KEY(vettura) REFERENCES vettura(numerogara)
			ON DELETE RESTRICT
            ON UPDATE CASCADE,
		FOREIGN KEY(scuderia) REFERENCES scuderia(nome)
			ON DELETE RESTRICT 
            ON UPDATE CASCADE,
		PRIMARY KEY (nome,cognome,datanascita,vettura),
        CHECK ((categoria='PRO' AND tipo='NULL') OR (categoria='AM' AND tipo='NULL') OR (categoria='AM' AND tipo='GM'))
    );
CREATE TABLE finanziamento(
		importo				NUMERIC(15,2) NOT NULL,
        dataFinanziamento	DATE NOT NULL,
        codicefinanziamento	CHAR(4) PRIMARY KEY,
        vetturapilota		CHAR(3),
        nomepilota			VARCHAR(30),
        datanascitapilota	DATE,
        cognomepilota		VARCHAR(30),
		FOREIGN KEY (nomepilota,cognomepilota,datanascitapilota,vetturapilota) REFERENCES pilota(nome,cognome,datanascita,vettura)
        ON UPDATE CASCADE 
        ON DELETE RESTRICT
	);