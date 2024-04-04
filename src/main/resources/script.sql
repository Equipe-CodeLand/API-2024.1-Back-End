INSERT INTO status (nome_status) VALUES ("Disponível");
INSERT INTO status (nome_status) VALUES ("Ocupado");
INSERT INTO Status (nome_status) VALUES ("Em manutenção");

INSERT INTO setor (nome_setor) VALUES ("TI");
INSERT INTO setor (nome_setor) VALUES ("Financeiro");
INSERT INTO setor (nome_setor) VALUES ("Logística");

INSERT INTO turno (nome_turno) VALUES ("Manhã");
INSERT INTO turno (nome_turno) VALUES ("Tarde");
INSERT INTO turno (nome_turno) VALUES ("Noite");

INSERT INTO funcionario (nome_funcionario, cpf, setor_id, turno_id) VALUES ("Felipe", 12345678910, 1, 1);
INSERT INTO funcionario (nome_funcionario, cpf, setor_id, turno_id) VALUES ("Iago", 12345678911, 2, 2);
INSERT INTO funcionario (nome_funcionario, cpf, setor_id, turno_id) VALUES ("Laura", 12345678912, 3, 3);
INSERT INTO funcionario (nome_funcionario, cpf, setor_id, turno_id) VALUES ("Letícia", 12345678913, 1, 2);
INSERT INTO funcionario (nome_funcionario, cpf, setor_id, turno_id) VALUES ("Lívia", 12345678914, 2, 3);
INSERT INTO funcionario (nome_funcionario, cpf, setor_id, turno_id) VALUES ("Luiz", 12345678915, 3, 1);
INSERT INTO funcionario (nome_funcionario, cpf, setor_id, turno_id) VALUES ("Pedro", 12345678916, 3, 2);

INSERT INTO ativos (nome, descricao, preco_aquisicao, marca, modelo, func_id, setor_id, status_id) VALUES ("Máquina de impressão 3D Industrial",
"Descrição da Máquina de impressão 3D Industrial", 5000, "Marca sei lá", "Modelo sei lá", null, 1, 1);
INSERT INTO ativos (nome, descricao, preco_aquisicao, marca, modelo, func_id, setor_id, status_id) VALUES ("Carro 1",
"Uno da firma", 25000, "Fiat", "Uno", 3, 3, 3);
INSERT INTO ativos (nome, descricao, preco_aquisicao, marca, modelo, func_id, setor_id, status_id) VALUES ("Servidor HP",
"O HP ProLiant MicroServer Gen8 é um servidor pequeno, silencioso e com design elegante,
 ideal para micro e pequenas empresas que desejam criar seu primeiro ambiente de servidor de TI.",
  3104.00, "HP", "ProLiant MicroServer", null, 1, 2);
INSERT INTO ativos (nome, descricao, preco_aquisicao, marca, modelo, func_id, setor_id, status_id) VALUES ("Desktop 1",
"Desktop Windows 11, processador i5, 16gb ram", 5000, "HP", "Modelo sei lá", 1, 1, 3);
INSERT INTO ativos (nome, descricao, preco_aquisicao, marca, modelo, func_id, setor_id, status_id) VALUES ("Notebook 1",
"Desktop Windows 11, processador i5, 16gb ram", 7000, "Dell", "Modelo sei lá", 5, 2, 2);

INSERT INTO manutencao (ativos_id, data_inicio, data_final, localizacao, responsavel) VALUES (1, "2024-01-01", "2024-02-01", "local da manutenção"
, "Responsável pela manutenção");
INSERT INTO manutencao (ativos_id, data_inicio, data_final, localizacao, responsavel) VALUES (2, "2024-01-01", "2024-02-01", "local da manutenção"
, "Responsável pela manutenção");
INSERT INTO manutencao (ativos_id, data_inicio, data_final, localizacao, responsavel) VALUES (4, "2024-01-01", "2024-02-01", "local da manutenção"
, "Responsável pela manutenção");
INSERT INTO manutencao (ativos_id, data_inicio, data_final, localizacao, responsavel) VALUES (3, "2024-01-01", "2024-02-01", "local da manutenção"
, "Responsável pela manutenção");

