insert into cliente (nombre,ciudad,edad,altura) values ('Carlos','Oviedo',20,177);
insert into cliente (nombre,ciudad,edad,altura) values ('Aitor','Barcelona',20,183);
insert into cliente (nombre,ciudad,edad,altura) values ('Yeray','León',21,180);
insert into cliente (nombre,ciudad,edad,altura) values ('Adrián','Murcia',25,185);
insert into cliente (nombre,ciudad,edad,altura) values ('Pedro','Galicia',20,165);
insert into pedido (nombre,precio,compañia,FK_CLIENTE) values ('Fortnite', 20.00,'Epic Games',1);
insert into pedido (nombre,precio,compañia,FK_CLIENTE) values ('Valorant', 35.00,'Riot Games',3);
insert into pedido (nombre,precio,compañia,FK_CLIENTE) values ('FC 24', 50.00,'Origin',2);
insert into pedido (nombre,precio,compañia,FK_CLIENTE) values ('Rocket League', 30.00,'Epic Games',5);
insert into pedido (nombre,precio,compañia,FK_CLIENTE) values ('CSGO', 40.00,'Steam',4);
insert into reseña (comentario,valoracion,FK_CLIENTE_OPINION, FK_NUM_PEDIDO) values ('Buen juego, lo recomiendo',8,4,1);
insert into reseña (comentario,valoracion,FK_CLIENTE_OPINION, FK_NUM_PEDIDO) values ('Muy divertido para jugar con tus amigos',9,3,2);
insert into reseña (comentario,valoracion,FK_CLIENTE_OPINION, FK_NUM_PEDIDO) values ('Me lo esperaba mejor, aun así está bien',6,2,3);
insert into reseña (comentario,valoracion,FK_CLIENTE_OPINION, FK_NUM_PEDIDO) values ('Demasiado bueno, me encanta!!',10,1,4);
insert into reseña (comentario,valoracion,FK_CLIENTE_OPINION, FK_NUM_PEDIDO) values ('Lo probé hace tiempo y no me pareció para tanto, pero con la nueva actualización cambia mucho la forma de jugarlo. Top.',8,5,5);
insert into empleado (nombre,edad,rol,horario) values ('Marcos',28,'Atención al cliente y caja','Mañanas');
insert into empleado (nombre,edad,rol,horario) values ('Federico',35,'Soporte técnico','Mañanas');
insert into empleado (nombre,edad,rol,horario) values ('Rodrigo',22,'Gestión de inventario','Mañanas');
insert into empleado (nombre,edad,rol,horario) values ('Jesús',31,'Soporte técnico','Tardes');
insert into empleado (nombre,edad,rol,horario) values ('Pedro',25,'Atención al cliente y caja','Tardes');
insert into empleado (nombre,edad,rol,horario) values ('Sergio',27,'Gestión de inventario','Tardes');
insert into encargo (pedido_id, empleado_id) values (1,1);
insert into encargo (pedido_id, empleado_id) values (2,4);
insert into encargo (pedido_id, empleado_id) values (2,3);
insert into encargo (pedido_id, empleado_id) values (4,5);
insert into encargo (pedido_id, empleado_id) values (4,2);
insert into encargo (pedido_id, empleado_id) values (5,6);
insert into encargo (pedido_id, empleado_id) values (1,3);
insert into encargo (pedido_id, empleado_id) values (3,3);
insert into encargo (pedido_id, empleado_id) values (3,5);
insert into encargo (pedido_id, empleado_id) values (2,1);
insert into encargo (pedido_id, empleado_id) values (5,4);
insert into encargo (pedido_id, empleado_id) values (4,4);

/* ADMIN */
insert into usuario (usuario,password) values ('carlos','$2a$12$PMaWN399mMtKXh0KkUxkQetqHFYpKLnVN7wJuO6WogbPPwA.WpAmS');
/* USER */
insert into usuario (usuario,password) values ('sergio','$2a$12$D.hpT5pA7FF6MKGrlIczvu1NswM85ii/i0rLH8guM9Qf6Z7z9S5Km');