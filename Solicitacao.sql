CREATE TABLE alunos
(
  id integer NOT NULL,
  nome character(20),
  matricula character(30),
  cpf character(20),
  datanascimento date,
  email character(40),
  CONSTRAINT pk_aluno PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE alunos
  OWNER TO postgres;

-- Table: computadores

-- DROP TABLE computadores;

CREATE TABLE computadores
(
  id integer NOT NULL,
  numero integer,
  descricao character(60),
  CONSTRAINT pk_computador PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE computadores
  OWNER TO postgres;

-- Table: funcionarios

-- DROP TABLE funcionarios;

CREATE TABLE funcionarios
(
  id integer NOT NULL,
  nome character(20),
  cpf character(20),
  datanascimento date,
  email character(40),
  CONSTRAINT pk_funcionario PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE funcionarios
  OWNER TO postgres;

-- Table: laboratorios

-- DROP TABLE laboratorios;

CREATE TABLE laboratorios
(
  id integer NOT NULL,
  numero integer,
  descricao character(60),
  CONSTRAINT pk_laboratorio PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE laboratorios
  OWNER TO postgres;

-- Table: retorno_solicitacao

-- DROP TABLE retorno_solicitacao;

CREATE TABLE retorno_solicitacao
(
  id integer NOT NULL,
  descricao character(60),
  data_solicitacao date,
  id_funcionario integer,
  id_status_retorno integer,
  id_solicitacao integer,
  CONSTRAINT pk_retorno_solicitacao PRIMARY KEY (id),
  CONSTRAINT "FK_retorno_solicitacao_id_funcionario" FOREIGN KEY (id_funcionario)
      REFERENCES funcionarios (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_retorno_solicitacao_id_solicitacoes" FOREIGN KEY (id_solicitacao)
      REFERENCES solicitacoes (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_retorno_solicitacao_id_status_retorno" FOREIGN KEY (id_status_retorno)
      REFERENCES status_retorno (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE retorno_solicitacao
  OWNER TO postgres;

-- Table: solicitacoes

-- DROP TABLE solicitacoes;

CREATE TABLE solicitacoes
(
  id integer NOT NULL,
  descricao character(400),
  data_solicitacao date,
  id_laboratorio integer,
  id_tipo_solicitacao integer,
  id_aluno integer,
  id_computador integer,
  CONSTRAINT pk_solicitacao PRIMARY KEY (id),
  CONSTRAINT "FK_solicitacoes_id_laboratorio" FOREIGN KEY (id_laboratorio)
      REFERENCES laboratorios (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_solocitacoes_id_aluno" FOREIGN KEY (id_aluno)
      REFERENCES alunos (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_solocitacoes_id_computador" FOREIGN KEY (id_computador)
      REFERENCES computadores (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_solocitacoes_id_tipo_solicitacao" FOREIGN KEY (id_tipo_solicitacao)
      REFERENCES tipo_solicitacao (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE solicitacoes
  OWNER TO postgres;


-- Table: status_retorno

-- DROP TABLE status_retorno;

CREATE TABLE status_retorno
(
  id integer NOT NULL,
  descricao character(60),
  CONSTRAINT pk_statusretorno PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE status_retorno
  OWNER TO postgres;

-- Table: tipo_solicitacao

-- DROP TABLE tipo_solicitacao;

CREATE TABLE tipo_solicitacao
(
  id integer NOT NULL,
  nome character(20),
  descricao character(60),
  CONSTRAINT pk_tipo_solicitacao PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tipo_solicitacao
  OWNER TO postgres;

