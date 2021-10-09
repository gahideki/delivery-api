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

INSERT INTO forma_pagamento (id, descricao) VALUES (1, 'Cartão de crédito');
INSERT INTO forma_pagamento (id, descricao) VALUES (2, 'Cartão de débito');
INSERT INTO forma_pagamento (id, descricao) VALUES (3, 'Dinheiro');

INSERT INTO permissao (id, nome, descricao) VALUES (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO permissao (id, nome, descricao) VALUES (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);
