INSERT INTO status (nome_status) VALUES ("Disponível");
INSERT INTO Status (nome_status) VALUES ("Em manutenção");
INSERT INTO status (nome_status) VALUES ("Ocupado");

INSERT INTO setor (nome_setor) VALUES ("TI");
INSERT INTO setor (nome_setor) VALUES ("Financeiro");
INSERT INTO setor (nome_setor) VALUES ("Logística");

INSERT INTO ativos (nome, descricao, preco_aquisicao, marca, modelo, nome_funcionario, setor_id, status_id) VALUES ("Máquina de impressão 3D Industrial",
"Descrição da Máquina de impressão 3D Industrial", 5000, "Marca sei lá", "Modelo sei lá", null, 1, 1);
INSERT INTO ativos (nome, descricao, preco_aquisicao, marca, modelo, nome_funcionario, setor_id, status_id) VALUES ("Carro 1",
"Uno da firma", 25000, "Fiat", "Uno", "Maria", 3, 3);
INSERT INTO ativos (nome, descricao, preco_aquisicao, marca, modelo, nome_funcionario, setor_id, status_id) VALUES ("Servidor HP",
"O HP ProLiant MicroServer Gen8 é um servidor pequeno, silencioso e com design elegante,
 ideal para micro e pequenas empresas que desejam criar seu primeiro ambiente de servidor de TI.",
  3104.00, "HP", "ProLiant MicroServer", "João", 1, 2);
INSERT INTO ativos (nome, descricao, preco_aquisicao, marca, modelo, nome_funcionario, setor_id, status_id) VALUES ("Desktop 1",
"Desktop Windows 11, processador i5, 16gb ram", 5000, "HP", "Modelo sei lá", "Chico", 1, 3);
INSERT INTO ativos (nome, descricao, preco_aquisicao, marca, modelo, nome_funcionario, setor_id, status_id) VALUES ("Notebook 1",
"Desktop Windows 11, processador i5, 16gb ram", 7000, "Dell", "Modelo sei lá", null, 2, 2);

INSERT INTO manutencao (ativos_id, data_inicio, data_final, localizacao, responsavel) VALUES (1, "2024-01-01", "2024-02-01", "local da manutenção"
, "Responsável pela manutenção");
INSERT INTO manutencao (ativos_id, data_inicio, data_final, localizacao, responsavel) VALUES (2, "2024-01-01", "2024-02-01", "local da manutenção"
, "Responsável pela manutenção");
INSERT INTO manutencao (ativos_id, data_inicio, data_final, localizacao, responsavel) VALUES (4, "2024-01-01", "2024-02-01", "local da manutenção"
, "Responsável pela manutenção");
INSERT INTO manutencao (ativos_id, data_inicio, data_final, localizacao, responsavel) VALUES (3, "2024-01-01", "2024-02-01", "local da manutenção"
, "Responsável pela manutenção");

