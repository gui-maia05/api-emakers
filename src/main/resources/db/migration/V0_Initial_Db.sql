CREATE TABLE IF NOT EXISTS pessoa (
    id_pessoa SERIAL PRIMARY KEY,
    nome VARCHAR(80) NOT NULL,
    cep CHAR(9) NOT NULL
);

CREATE TABLE IF NOT EXISTS livro (
    id_livro SERIAL PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    autor VARCHAR(45) NOT NULL,
    data_lancamento DATE NOT NULL,
    disponivel BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS emprestimo (
    id_emprestimo BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_pessoa BIGINT NOT NULL,
    id_livro BIGINT NOT NULL,
    data_emprestimo DATE NOT NULL,
    data_devolucao DATE NULL,

    CONSTRAINT fk_emprestimo_pessoa FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa),
    CONSTRAINT fk_emprestimo_livro FOREIGN KEY (id_livro) REFERENCES livro(id_livro)
);
