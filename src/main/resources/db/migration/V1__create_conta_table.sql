CREATE TABLE conta (
    id BIGINT NOT NULL AUTO_INCREMENT,
    conta_origem VARCHAR(20) NOT NULL,
    conta_destino VARCHAR(20) NOT NULL,
    valor DECIMAL(15,2) NOT NULL,
    taxa DECIMAL(15,2),
    data_transferencia DATE,
    data_agendamento DATE,
    PRIMARY KEY (id)
);