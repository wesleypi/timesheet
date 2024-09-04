CREATE SCHEMA IF NOT EXISTS timesheet;

CREATE TABLE IF NOT EXISTS banco_horas
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    id_usuario BIGINT NOT NULL,
    data       date   NOT NULL,
    hora       BIGINT NOT NULL,
    descricao  VARCHAR(255) NULL,
    CONSTRAINT pk_banco_horas PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS feriados
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    ano  INT  NOT NULL,
    data date NOT NULL,
    CONSTRAINT pk_feriados PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS grupos
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    nome       VARCHAR(255) NOT NULL,
    id_externo VARCHAR(255) NOT NULL,
    CONSTRAINT pk_grupos PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS lancamentos
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    id_usuario  BIGINT NOT NULL,
    data        date   NOT NULL,
    feriado     BIT(1) NOT NULL,
    periodos    JSON   NOT NULL,
    hora_inicio time   NOT NULL,
    hora_fim    time   NOT NULL,
    descricao   VARCHAR(255) NULL,
    total       BIGINT NOT NULL,
    CONSTRAINT pk_lancamentos PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pagamentos
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    id_usuario        BIGINT   NOT NULL,
    mes               SMALLINT NOT NULL,
    ano               SMALLINT NOT NULL,
    total_horas       BIGINT   NOT NULL,
    data_pagamento    date     NOT NULL,
    valor_hora        DECIMAL  NOT NULL,
    valor_total       DECIMAL  NOT NULL,
    banco_horas_atual datetime NOT NULL,
    CONSTRAINT pk_pagamentos PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS password_recovery
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    id_usuario BIGINT       NOT NULL,
    codigo     VARCHAR(255) NOT NULL,
    CONSTRAINT pk_password_recovery PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS recursos
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    CONSTRAINT pk_recursos PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS recursos_grupo
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    id_grupo   BIGINT NOT NULL,
    id_recurso BIGINT NOT NULL,
    CONSTRAINT pk_recursos_grupo PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS refresh_token
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    id_usuario    BIGINT       NOT NULL,
    refresh_token VARCHAR(255) NOT NULL,
    expires_at    datetime     NOT NULL,
    CONSTRAINT pk_refresh_token PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS usuario
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    id_externo  VARCHAR(255) NOT NULL,
    id_grupo    BIGINT       NOT NULL,
    nome        VARCHAR(255) NOT NULL,
    email       VARCHAR(255) NOT NULL,
    time        VARCHAR(255) NULL,
    senha       VARCHAR(255) NOT NULL,
    data_inicio date         NOT NULL,
    valor_hora   DECIMAL     NOT NULL,
    valor_mensal DECIMAL     NOT NULL,
    banco_horas BIGINT       NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (id)
);

ALTER TABLE feriados
    ADD CONSTRAINT uc_feriados_data UNIQUE (data);

ALTER TABLE usuario
    ADD CONSTRAINT uc_usuario_email UNIQUE (email);

ALTER TABLE usuario
    ADD CONSTRAINT uc_usuario_uuid UNIQUE (id_externo);

ALTER TABLE grupos
    ADD CONSTRAINT uc_grupos_id_externo UNIQUE (id_externo);

CREATE INDEX date_idx ON lancamentos (data);

CREATE INDEX idx_id_externo_user ON usuario (id_externo);

CREATE INDEX idx_id_externo_grupos ON grupos (id_externo);

CREATE INDEX idx_year ON feriados (ano);

ALTER TABLE banco_horas
    ADD CONSTRAINT FK_BANCO_HORAS_ON_ID_USUARIO FOREIGN KEY (id_usuario) REFERENCES usuario (id);

ALTER TABLE lancamentos
    ADD CONSTRAINT FK_LANCAMENTOS_ON_ID_USUARIO FOREIGN KEY (id_usuario) REFERENCES usuario (id);

ALTER TABLE pagamentos
    ADD CONSTRAINT FK_PAGAMENTOS_ON_ID_USUARIO FOREIGN KEY (id_usuario) REFERENCES usuario (id);

ALTER TABLE password_recovery
    ADD CONSTRAINT FK_PASSWORD_RECOVERY_ON_ID_USUARIO FOREIGN KEY (id_usuario) REFERENCES usuario (id);

ALTER TABLE recursos_grupo
    ADD CONSTRAINT FK_RECURSOS_GRUPO_ON_ID_GRUPO FOREIGN KEY (id_grupo) REFERENCES grupos (id);

ALTER TABLE recursos_grupo
    ADD CONSTRAINT FK_RECURSOS_GRUPO_ON_ID_RECURSO FOREIGN KEY (id_recurso) REFERENCES recursos (id);

ALTER TABLE refresh_token
    ADD CONSTRAINT FK_REFRESH_TOKEN_ON_ID_USUARIO FOREIGN KEY (id_usuario) REFERENCES usuario (id);

ALTER TABLE usuario
    ADD CONSTRAINT FK_USUARIO_ON_ID_GRUPO FOREIGN KEY (id_grupo) REFERENCES grupos (id);

INSERT INTO grupos (id_externo, nome) VALUES ('87034936' ,'ADMIN'), ('60758696' ,'USER'), ('15432696' ,'MODERATOR') ;

INSERT INTO usuario (id_externo, id_grupo, nome, email, time, senha, data_inicio, valor_hora, banco_horas, valor_mensal)
VALUES ('c898f86e-4547-4964-96a5-2a2ea53594c9', 1, 'Default User', 'default@example.com', 'bhut', '$2a$10$pYaIjb7zAzu/sJezec.iGewGN/4HQsKiYLtn8.hCTLoZTrWXvU.IO', '2024-12-05', 14.07, 0, 2500.96);