drop database if exists DB_Ahorcado;
create database DB_Ahorcado;
use DB_Ahorcado;

create table Palabra(
	idPalabra int not null auto_increment,
    palabra varchar(256),
    pistaUno varchar(256),
    pistaDos varchar(256),
    pistaTres varchar(256),
    PRIMARY KEY PK_idPalabra(idPalabra)
);

delimiter //
create procedure sp_AgregarPalabra(
	in palab varchar(256),
    in pistaU varchar(256),
    in pistaD varchar(256),
    in pistaT varchar(256)
    )
    begin
		insert into Palabra(palabra, pistaUno, pistaDos, pistaTres)
			values(palab, pistaU, pistaD, pistaT);
    end//
delimiter ;
call sp_AgregarPalabra("MURCIELAGO", "Es un mamifero volador", "Vive en cuevas", "Es ciego de dia");
call sp_AgregarPalabra("UNIVERSO", "Todo lo que existe", "Contiene estrellas y galaxias", "Es inmenso y misterioso");
call sp_AgregarPalabra("CHOCOLATE", "Es un alimento dulce", "Se obtiene del cacao", "Puede ser blanco, negro o con leche");
call sp_AgregarPalabra("IMAGINACION", "Es una habilidad humana", "Permite crear ideas nuevas", "No tiene limites");
call sp_AgregarPalabra("ABRACADABRA", "Es una palabra magica", "Se usa en trucos", "Usada por magos");

delimiter //
create procedure sp_ListarPalabra()
begin
	select idPalabra, palabra, pistaUno, pistaDos, pistaTres from Palabra;
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