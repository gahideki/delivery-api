INSERT INTO cozinha (nome) VALUES ('Tailandesa');
INSERT INTO cozinha (nome) VALUES ('Indiana');

INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Thai Gourmet', 10, 1);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Thai Delivery', 9.50, 2);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Tuk Tuk Comida Indiana', 15, 2);

INSERT INTO estado (nome) VALUES ('São Paulo');
INSERT INTO estado (nome) VALUES ('Rio de Janeiro');
INSERT INTO estado (nome) VALUES ('Minas Gerais');

INSERT INTO cidade (nome, estado_id) VALUES('Suzano', 1);
INSERT INTO cidade (nome, estado_id) VALUES('Copacabana', 2);
INSERT INTO cidade (nome, estado_id) VALUES('Belo Horizonte', 3);
