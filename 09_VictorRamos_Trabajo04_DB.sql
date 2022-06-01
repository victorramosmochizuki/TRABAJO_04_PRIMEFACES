USE master
GO
DROP DATABASE IF EXISTS DEMO04
GO
CREATE DATABASE DEMO04
GO
USE DEMO04;
-- Table: CLIENTE
CREATE TABLE CLIENTE (
    IDCLI int identity(1,1) NOT NULL,
    NOMCLIE varchar(50)  NOT NULL,
    APECLIE varchar(50)  NOT NULL,
    DOCCLIE char(8)  NOT NULL,
    EMACLIE varchar(50)  NOT NULL,
    CELCLIE char(9)  NOT NULL,
    ESTCLIE char(1)  NOT NULL,
	IDUBI int NOT NULL,
    CONSTRAINT CLIENTE_pk PRIMARY KEY  (IDCLI)
);


CREATE TABLE UBIGEO (
    IDUBI int identity(1,1) NOT NULL,
    PAISUBI varchar(50)  NOT NULL,
    DEPUBI varchar(50)  NOT NULL,
    PROVUBI char(50)  NOT NULL,
    DISUBI varchar(50)  NOT NULL,
    CONSTRAINT UBIGEO_pk PRIMARY KEY  (IDUBI)
);

-- foreign keys
-- Reference: COMPRA_DETALLE_COMPRA (table: COMPRA_DETALLE)
ALTER TABLE CLIENTE ADD CONSTRAINT UBIGEO_CLIENTE
    FOREIGN KEY (IDUBI)
    REFERENCES UBIGEO (IDUBI);



insert into UBIGEO
(PAISUBI,DEPUBI,PROVUBI,DISUBI)
values
('PERU','LIMA','CA�ETE','CHILCA'),
('PERU','LIMA','CA�ETE','CHILCA'),
('PERU','LIMA','CA�ETE','CHILCA'),
('PERU','LIMA','CA�ETE','SAN ANTONIO'),
('PERU','LIMA','CA�ETE','SANTA CRUZ DE FLORES'),
('PERU','LIMA','CA�ETE','CALANGO'),
('PERU','LIMA','CA�ETE','MALA'),
('PERU','LIMA','CA�ETE','COAYLLO'),
('PERU','LIMA','CA�ETE','ASIA'),
('PERU','LIMA','CA�ETE','CERRO AZUL'),
('PERU','LIMA','CA�ETE','QUILMANA'),
('PERU','LIMA','CA�ETE','SAN LUIS'),
('PERU','LIMA','CA�ETE','IMPERIAL'),
('PERU','LIMA','CA�ETE','NUEVO IMPERIAL'),
('PERU','LIMA','CA�ETE','SAN VICENTE DE CA�ETE'),
('PERU','LIMA','CA�ETE','LUNAHUANA'),
('PERU','LIMA','CA�ETE','PACARAN'),
('PERU','LIMA','CA�ETE','ZU�IGA'),
('PERU','ICA','ICA','ICA'),
('PERU','ICA','ICA','OCUCAJE'),
('PERU','ICA','ICA','PACHACUTEC'),
('PERU','ICA','ICA','PARCONA'),
('PERU','ICA','NAZCA','NAZCA'),
('PERU','ICA','NAZCA','EL INGENIO'),
('PERU','ICA','NAZCA','MARCONA'),
('CHILE','TARAPACA','IQUIQUE','IQUI');


insert into CLIENTE
(NOMCLIE,APECLIE,DOCCLIE,EMACLIE,CELCLIE,ESTCLIE, IDUBI)
values
('Miguel','Gutierrez','78452169','miguel@gmail.com','995687412','A', 6),
('Jose','Sivera','14256985','jose@gmail.com','992666961','A', 5),
('Mario','Quispe','74200697','mario@gmail.com','992693446','A', 4);