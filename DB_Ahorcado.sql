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

create table Usuario(
	id_usuario int not null auto_increment,
    correo varchar(256) not null unique,
    contrasena varchar(256) not null,
    primary key PK_id_usuario(id_usuario)
);

INSERT INTO Palabra (palabra, pista_uno, pista_dos, pista_tres) VALUES ("MURCIELAGO", "Es un mamifero volador", "Vive en cuevas", "Es ciego de dia");
INSERT INTO Palabra (palabra, pista_uno, pista_dos, pista_tres) VALUES ("UNIVERSO", "Todo lo que existe", "Contiene estrellas y galaxias", "Es inmenso y misterioso");
INSERT INTO Palabra (palabra, pista_uno, pista_dos, pista_tres) VALUES ("CHOCOLATE", "Es un alimento dulce", "Se obtiene del cacao", "Puede ser blanco, negro o con leche");
INSERT INTO Palabra (palabra, pista_uno, pista_dos, pista_tres) VALUES ("IMAGINACION", "Es una habilidad humana", "Permite crear ideas nuevas", "No tiene limites");
INSERT INTO Palabra (palabra, pista_uno, pista_dos, pista_tres) VALUES ("ABRACADABRA", "Es una palabra magica", "Se usa en trucos", "Usada por magos");

insert into Usuario (correo, contrasena) values ("soxcal@kinal.edu.gt", "1");
insert into Usuario (correo, contrasena) values ("mlara@kinal.edu.gt", "2");
insert into Usuario (correo, contrasena) values ("jajcabul@kinal.edu.gt", "3");
insert into Usuario (correo, contrasena) values ("xportillo@kinal.edu.gt", "4");
insert into Usuario (correo, contrasena) values ("ocumatz@kinal.edu.gt", "5");

select * from Palabra;

select * from Usuario;

delimiter //
create procedure sp_ObtenerPalabraAleatoria()
begin
    select palabra, pista_uno, pista_dos, pista_tres from Palabra order by RAND() limit 1;
end //

DELIMITER ;
call sp_ObtenerPalabraAleatoria;