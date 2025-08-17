CREATE TABLE topicos (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    fecha_de_creacion TIMESTAMP NOT NULL,
    autor VARCHAR(100) NOT NULL,
    curso VARCHAR(50),
    status_topico VARCHAR(50)
);
