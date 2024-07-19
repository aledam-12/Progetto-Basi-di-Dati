-- Inserimento dati nella tabella circuito
INSERT INTO circuito (numerocurve, lunghezzacircuito, paese, nome) 
VALUES (10, 5000, 'Italia', 'Monza'),
       (8, 4500, 'Spagna', 'Barcellona'),
       (12, 6000, 'Regno Unito', 'Silverstone');

-- Inserimento dati nella tabella scuderia
INSERT INTO scuderia (nome, paesesede)
VALUES ('Ferrari', 'Italia'),
       ('Mercedes', 'Germania'),
       ('Red Bull Racing', 'Austria');

-- Inserimento dati nella tabella vettura
INSERT INTO vettura (modello, numerogara, scuderia)
VALUES ('SF21', '001', 'Ferrari'),
       ('W12', '002', 'Mercedes'),
       ('RB16B', '003', 'Red Bull Racing');

-- Inserimento dati nella tabella gara
INSERT INTO gara (circuito, nomegara, datagara, durata, tipo)
VALUES ('Monza', 'Gran Premio d''Italia', '2023-09-10', 120, 'asciutto'),
       ('Barcellona', 'Gran Premio di Spagna', '2023-05-14', 110, 'asciutto'),
       ('Silverstone', 'British Grand Prix', '2023-07-16', 125, 'bagnato');

-- Inserimento dati nella tabella costruttore
INSERT INTO costruttore (nome, numerocomponenti, ragionesociale, via, città, cap)
VALUES ('Costruttore A', 1, 'Costruttore A SNC', 'Via X', 'Città X', '12345'),
       ('Costruttore B', 1, 'Costruttore B SRL', 'Via Y', 'Città Y', '54321');

-- Inserimento dati nella tabella componente
INSERT INTO componente (tipo, costospecifico, peso, numerocilindri, tipomotore, materiale, codice, vettura, cilindrata, numeromarce, costruttore)
VALUES ('motore', 5000, NULL, 8, 'turbo', NULL, 'ABC123', '001', 1600, NULL, 'Costruttore B SRL'),
       ('cambio', 3000, 150, NULL, NULL, 'Acciaio', 'DEF456', '002', NULL, 7, 'Costruttore A SNC');

-- Inserimento dati nella tabella competizione
INSERT INTO competizione (motivoritiro, puntipiazzamento, gara, vettura)
VALUES (NULL, 10, 'Gran Premio d''Italia', '001'),
       ('incidente', 0, 'British Grand Prix', '003');

-- Inserimento dati nella tabella pilota
INSERT INTO pilota (scuderia, vettura, nome, datanascita, cognome, tipo, numerolicenze, dataprimalicenza, nazionalità, categoria)
VALUES ('Ferrari', '001', 'Charles', '1997-10-16', 'Leclerc', 'NULL', 3, '2015-02-15', 'Monegasco', 'PRO'),
       ('Red Bull Racing', '003', 'Max', '1997-09-30', 'Verstappen', 'GM', 4, '2014-01-20', 'Olandese', 'AM');

-- Inserimento dati nella tabella finanziamento
INSERT INTO finanziamento (importo, dataFinanziamento, codicefinanziamento, vetturapilota, nomepilota, datanascitapilota, cognomepilota)
VALUES (500000.00, '2023-10-01', '0001', '003', 'Max', '1997-09-30', 'Verstappen'),
       (750000.00, '2023-08-15', '0002', '003', 'Max', '1997-09-30', 'Verstappen');

