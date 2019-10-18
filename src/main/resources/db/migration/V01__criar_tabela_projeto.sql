CREATE TABLE projeto (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    chave_painel VARCHAR(60),
    link_sonar VARCHAR(60),
    pass_sonar VARCHAR(25),
    nome_projeto VARCHAR(60),
    login_sonar VARCHAR(25),
    caminho_projeto VARCHAR(100)

)ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;