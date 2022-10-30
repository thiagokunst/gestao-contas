CREATE SCHEMA gestao_contas

GO

CREATE TABLE pessoas(
    id INTEGER PRIMARY KEY IDENTITY(1, 1) NOT NULL,
    nome VARCHAR (100) NOT NULL,
    cpf VARCHAR (11) NOT NULL,
    data_nascimento DATETIME NOT null
);

CREATE TABLE contas(
    id INTEGER PRIMARY KEY IDENTITY(1, 1) NOT NULL,
    id_pessoa INTEGER NOT NULL,
    saldo NUMERIC NOT NULL,
    limite_saque_diario NUMERIC NOT NULL,
    flag_ativo BIT NOT NULL,
    tipo_conta INTEGER NOT NULL,
    data_criacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
	FOREIGN KEY (id_pessoa) REFERENCES pessoas(id)
);


CREATE TABLE transacoes(
    id INTEGER PRIMARY KEY IDENTITY(1, 1) NOT NULL,
    id_conta INTEGER NOT NULL,
    valor NUMERIC NOT NULL,
    data_transacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_conta) REFERENCES contas(id)
);

CREATE INDEX data_transcao_idx
ON transacoes (id, data_transacao);