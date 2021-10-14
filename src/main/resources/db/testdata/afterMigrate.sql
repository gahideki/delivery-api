SET foreign_key_checks = 0; -- Desabilita validação de foreign key

DELETE FROM cidade;
DELETE FROM cozinha;
DELETE FROM estado;

SET foreign_key_checks = 1; -- Habilita validação de foreign key

ALTER TABLE cidade auto_increment = 1; -- Reseta o auto increment para 1
ALTER TABLE cozinha auto_increment = 1;
ALTER TABLE estado auto_increment = 1;

INSERT INTO cozinha (id, nome) VALUES (1, 'Tailandesa');
INSERT INTO cozinha (id, nome) VALUES (2, 'Indiana');
INSERT INTO cozinha (id, nome) VALUES (3, 'Argentina');
INSERT INTO cozinha (id, nome) VALUES (4, 'Brasileira');

INSERT INTO estado (id, nome) VALUES (1, 'São Paulo');
INSERT INTO estado (id, nome) VALUES (2, 'Rio de Janeiro');
INSERT INTO estado (id, nome) VALUES (3, 'Minas Gerais');

INSERT INTO cidade (id, nome, estado_id) VALUES(1, 'Suzano', 1);
INSERT INTO cidade (id, nome, estado_id) VALUES(2, 'Copacabana', 2);
INSERT INTO cidade (id, nome, estado_id) VALUES(3, 'Belo Horizonte', 3);
