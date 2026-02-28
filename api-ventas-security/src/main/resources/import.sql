insert into  tbl_categorias(nombre_corto,nombre_largo,estado) values('Juguetes','Juguestes importados','1')
insert into  tbl_categorias(nombre_corto,nombre_largo,estado) values('Comida','Comida Preparadas','1')
insert into  tbl_categorias(nombre_corto,nombre_largo,estado) values('Accesorios','Accesorios importados','1')
insert into  tbl_categorias(nombre_corto,nombre_largo,estado) values('Comida 1','Comida 1 Preparadas 1','1')
insert into  tbl_categorias(nombre_corto,nombre_largo,estado) values('Comida 2','Comida 2 Preparadas 1','1')
insert into  tbl_categorias(nombre_corto,nombre_largo,estado) values('Comida 3','Comida 3 Preparadas 1','1')
insert into  tbl_categorias(nombre_corto,nombre_largo,estado) values('Comida 4','Comida 4 Preparadas 1','1')
insert into  tbl_categorias(nombre_corto,nombre_largo,estado) values('Comida 5','Comida 5 Preparadas 1','1')
insert into  tbl_categorias(nombre_corto,nombre_largo,estado) values('Comida 6','Comida 6 Preparadas 1','1')
insert into  tbl_categorias(nombre_corto,nombre_largo,estado) values('Comida 7','Comida 7 Preparadas 1','1')
insert into  tbl_categorias(nombre_corto,nombre_largo,estado) values('Comida 8','Comida 8 Preparadas 1','1')
insert into  tbl_categorias(nombre_corto,nombre_largo,estado) values('Comida 9','Comida 9 Preparadas 1','1')
insert into  tbl_categorias(nombre_corto,nombre_largo,estado) values('Comida 10','Comida 10 Preparadas 1','1')

insert into tbl_usuario(nombre_completo,usuario,clave,estado)values('Juan Perez','jperez','123','1')
insert into tbl_usuario(nombre_completo,usuario,clave,estado)values('María Luna','mluna','abc','1')


INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

INSERT INTO users (username,full_name, password_hash, enabled) VALUES ('admin', 'Juan Perez','$2a$10$N/0f9Q0CIo2X2I6yjQkKteX/K6fSEOgXhdDUrpIfEisFLWHvh2ely', true);

INSERT INTO users (username,full_name, password_hash, enabled) VALUES ('user', 'María Chavez','$2a$10$N/0f9Q0CIo2X2I6yjQkKteX/K6fSEOgXhdDUrpIfEisFLWHvh2ely', true);

-- admin tiene ROLE_ADMIN y ROLE_USER
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (1, 2);

-- user solo tiene ROLE_USER
INSERT INTO user_roles (user_id, role_id) VALUES (2, 1);

INSERT INTO refresh_tokens (token, user_id, expires_at, revoked)VALUES ('test-refresh-token-123',1,DATE_ADD(NOW(), INTERVAL 7 DAY),false);
