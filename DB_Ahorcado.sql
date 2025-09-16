drop database if exists DB_Ahorcado;
create database DB_Ahorcado;
use DB_Ahorcado;

create table Palabra(
	id_Palabra int not null auto_increment,
    palabra varchar(256),
    pista_uno varchar(256),
    pista_dos varchar(256),
    pista_tres varchar(256),
    PRIMARY KEY PK_id_Palabra(id_Palabra)
);

INSERT INTO Palabra (palabra, pistaUno, pistaDos, pistaTres) VALUES ("MURCIELAGO", "Es un mamifero volador", "Vive en cuevas", "Es ciego de dia");
INSERT INTO Palabra (palabra, pistaUno, pistaDos, pistaTres) VALUES ("UNIVERSO", "Todo lo que existe", "Contiene estrellas y galaxias", "Es inmenso y misterioso");
INSERT INTO Palabra (palabra, pistaUno, pistaDos, pistaTres) VALUES ("CHOCOLATE", "Es un alimento dulce", "Se obtiene del cacao", "Puede ser blanco, negro o con leche");
INSERT INTO Palabra (palabra, pistaUno, pistaDos, pistaTres) VALUES ("IMAGINACION", "Es una habilidad humana", "Permite crear ideas nuevas", "No tiene limites");
INSERT INTO Palabra (palabra, pistaUno, pistaDos, pistaTres) VALUES ("ABRACADABRA", "Es una palabra magica", "Se usa en trucos", "Usada por magos");

delimiter //
create procedure sp_ListarPalabra()
begin
	select id_Palabra, palabra, pistaUno, pistaDos, pistaTres from Palabra;
end//
delimiter ; 
call sp_ListarPalabra;

DELIMITER //

create procedure sp_ObtenerPalabraAleatoria()
begin
    select palabra, pistaUno, pistaDos, pistaTres from Palabra order by RAND() limit 1;
end //

DELIMITER ;
call sp_ObtenerPalabraAleatoria;