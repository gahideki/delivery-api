INSERT INTO cozinha (id, nome) VALUES (1, 'Tailandesa');
INSERT INTO cozinha (id, nome) VALUES (2, 'Indiana');

INSERT INTO estado (id, nome) VALUES (1, 'São Paulo');
INSERT INTO estado (id, nome) VALUES (2, 'Rio de Janeiro');
INSERT INTO estado (id, nome) VALUES (3, 'Minas Gerais');

INSERT INTO cidade (id, nome, estado_id) VALUES(1, 'Suzano', 1);
INSERT INTO cidade (id, nome, estado_id) VALUES(2, 'Copacabana', 2);
INSERT INTO cidade (id, nome, estado_id) VALUES(3, 'Belo Horizonte', 3);

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) VALUES (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) VALUES (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) VALUES (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp);

INSERT INTO forma_pagamento (id, descricao) VALUES (1, 'Cartão de crédito');
INSERT INTO forma_pagamento (id, descricao) VALUES (2, 'Cartão de débito');
INSERT INTO forma_pagamento (id, descricao) VALUES (3, 'Dinheiro');

INSERT INTO permissao (id, nome, descricao) VALUES (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO permissao (id, nome, descricao) VALUES (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);
