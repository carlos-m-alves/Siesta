create table pessoa (
   id_pessoa serial primary key
   ,nome character varying(100) NOT NULL  
   ,data_nascimento date NOT NULL  
   ,email character varying(100) NOT NULL  
   ,senha character varying(100) NOT NULL  
   ,tipo character varying(1) NOT NULL 
   ,ativo boolean NOT NULL
);
ALTER TABLE pessoa ADD CONSTRAINT pessoa_email_unique UNIQUE (email);

CREATE UNIQUE INDEX CONCURRENTLY unique_email ON pessoa (email);

ALTER TABLE pessoa ADD CONSTRAINT pessoa_email UNIQUE USING INDEX unique_email;

ALTER TABLE pessoa ADD COLUMN cpf VARCHAR;

-- insere usuario
insert into pessoa (nome,data_nascimento,email,senha,tipo,ativo) values
('Carlos Henrique','1994-09-06','riq@gmail.com','8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92','U',true);

insert into pessoa (nome,data_nascimento,email,senha,tipo,ativo) values 
('Parceiro','1994-09-06','carlos@gmail.com','8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92','P',true);

--senha 123456
insert into pessoa (nome,data_nascimento,email,senha,tipo,ativo) values
('Outro func do hotel 1','1900-01-01','carlos@gmail.com','8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92','P',true)

-- administrador
insert into pessoa (nome,data_nascimento,email,senha,tipo,ativo) values 
('Administrador','1990-09-06','adm@adm','A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3','A',true);

insert into pessoa (nome,data_nascimento,email,senha,tipo,ativo) values 
('Administrador','1990-09-06','adm2@adm2.com','8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92','A',true);

create table parceiro (
	id_parceiro integer REFERENCES pessoa (id_pessoa)
	,primary key(id_parceiro)
);
insert into parceiro (id_parceiro) values (2);
insert into parceiro (id_parceiro) values(3);

create table administrador (
	id_administrador integer REFERENCES pessoa (id_pessoa)
	,primary key(id_administrador)
);
insert into administrador (id_administrador) values (4);

create table usuario (
	id_usuario integer REFERENCES pessoa (id_pessoa)
	,primary key(id_usuario)
);
insert into usuario (id_usuario) values (1);

create table bairro (
   id_bairro serial primary key
  ,descricao character varying(100) NOT NULL 
);
insert into bairro (descricao) values ('Abranches');
insert into bairro (descricao) values ('Água Verde');
insert into bairro (descricao) values ('Ahú');
insert into bairro (descricao) values ('Alto Boqueirão');
insert into bairro (descricao) values ('Alto da Glória');
insert into bairro (descricao) values ('Alto da XV');
insert into bairro (descricao) values ('Atuba');
insert into bairro (descricao) values ('Augusta');
insert into bairro (descricao) values ('Bacacheri');
insert into bairro (descricao) values ('Bairro Alto');
insert into bairro (descricao) values ('Barreirinha');
insert into bairro (descricao) values ('Batel');
insert into bairro (descricao) values ('Bigorrilho');
insert into bairro (descricao) values ('Boa Vista');
insert into bairro (descricao) values ('Bom Retiro');
insert into bairro (descricao) values ('Boqueirão');
insert into bairro (descricao) values ('Butiatuvinha');
insert into bairro (descricao) values ('Cabral');
insert into bairro (descricao) values ('Cachoeira');
insert into bairro (descricao) values ('Cajuru');
insert into bairro (descricao) values ('Campina do Siqueira');
insert into bairro (descricao) values ('Campo Comprido');
insert into bairro (descricao) values ('Campo de Santana');
insert into bairro (descricao) values ('Capão da Imbuia');
insert into bairro (descricao) values ('Capão Raso');
insert into bairro (descricao) values ('Cascatinha');
insert into bairro (descricao) values ('Centro');
insert into bairro (descricao) values ('Centro Histórico');
insert into bairro (descricao) values ('Caximba');
insert into bairro (descricao) values ('Centro Cívico');
insert into bairro (descricao) values ('Cidade Industrial');
insert into bairro (descricao) values ('Cristo Rei');
insert into bairro (descricao) values ('Fanny');
insert into bairro (descricao) values ('Fazendinha');
insert into bairro (descricao) values ('Ganchinho');
insert into bairro (descricao) values ('Guabirotuba');
insert into bairro (descricao) values ('Guaíra');
insert into bairro (descricao) values ('Hauer');
insert into bairro (descricao) values ('Hugo Lange');
insert into bairro (descricao) values ('Jardim Botânico');
insert into bairro (descricao) values ('Jardim Social');
insert into bairro (descricao) values ('Jardim das Américas');
insert into bairro (descricao) values ('Juvevê');
insert into bairro (descricao) values ('Lamenha Pequena');
insert into bairro (descricao) values ('Lindoia');
insert into bairro (descricao) values ('Mercês');
insert into bairro (descricao) values ('Mossunguê (Ecoville)');
insert into bairro (descricao) values ('Novo Mundo');
insert into bairro (descricao) values ('Orleans');
insert into bairro (descricao) values ('Parolin');
insert into bairro (descricao) values ('Pilarzinho');
insert into bairro (descricao) values ('Pinheirinho');
insert into bairro (descricao) values ('Portão');
insert into bairro (descricao) values ('Prado Velho');
insert into bairro (descricao) values ('Rebouças');
insert into bairro (descricao) values ('Riviera');
insert into bairro (descricao) values ('Santa Cândida');
insert into bairro (descricao) values ('Santa Felicidade');
insert into bairro (descricao) values ('Santa Quitéria');
insert into bairro (descricao) values ('Santo Inácio');
insert into bairro (descricao) values ('São Braz');
insert into bairro (descricao) values ('São Francisco');
insert into bairro (descricao) values ('São João');
insert into bairro (descricao) values ('São Lourenço');
insert into bairro (descricao) values ('São Miguel');
insert into bairro (descricao) values ('Seminário');
insert into bairro (descricao) values ('Sítio Cercado');
insert into bairro (descricao) values ('Taboão');
insert into bairro (descricao) values ('Tarumã');
insert into bairro (descricao) values ('Tatuquara');
insert into bairro (descricao) values ('Tingui');
insert into bairro (descricao) values ('Uberaba');
insert into bairro (descricao) values ('Umbará');
insert into bairro (descricao) values ('Vila Izabel');
insert into bairro (descricao) values ('Vista Alegre');
insert into bairro (descricao) values ('Xaxim');

create table forma_pagamento (
  id_forma_pagamento serial primary key
  ,desc_pagamento character varying(50) 
);
insert into forma_pagamento (desc_pagamento) values ('DINHEIRO');
insert into forma_pagamento (desc_pagamento) values ('CARTÃO-DÉBITO');
insert into forma_pagamento (desc_pagamento) values ('CARTÃO-CRÉDITO');

create table horas (
   id_horas serial primary key
  ,horario time NOT NULL 
);
insert into horas (horario) values (time '11:00');
insert into horas (horario) values (time '11:30');
insert into horas (horario) values (time '12:00');
insert into horas (horario) values (time '12:30');
insert into horas (horario) values (time '13:00');
insert into horas (horario) values (time '13:30');
insert into horas (horario) values (time '14:00');
insert into horas (horario) values (time '14:30');

create table adicional (
   id_adicional serial primary key
  ,descricao character varying(100) NOT NULL 
);
insert into adicional (descricao) values ('Ar-condicionado');
insert into adicional (descricao) values ('Wi-fi');
insert into adicional (descricao) values ('Televisão');

create table endereco (
   id_endereco serial primary key
  ,rua character varying(100) NOT NULL 
  ,numero character varying(100) NOT NULL 
  ,complemento character varying(100) NOT NULL 
  ,id_bairro integer REFERENCES bairro (id_bairro)
  ,cep character varying(100) NOT NULL 
);
insert into endereco (rua,numero,complemento,id_bairro,cep) values 
('Rua Brasílio Itiberê',1000,'',2,'81700520');
insert into endereco (rua,numero,complemento,id_bairro,cep) values 
('Rua dos Pioneiros',520,'',4,'81444520');

-- ------------------------------------------------------------------------------------------
create table hospedagem (
   id_hospedagem serial primary key
   ,nome character varying(100) NOT NULL  
   ,ativo boolean NOT NULL  
   ,id_parceiro integer REFERENCES parceiro (id_parceiro)  
   ,id_endereco integer REFERENCES endereco (id_endereco)
);
insert into hospedagem (nome,ativo,id_parceiro,id_endereco) values
('Casa 1',true,2,1);

create table quarto (
   id_quarto serial primary key
   ,id_hospedagem integer REFERENCES hospedagem (id_hospedagem)       
   ,nome character varying(100) NOT NULL  
   ,ativo boolean NOT NULL  
   ,preco decimal NOT NULL  
);
ALTER TABLE quarto ADD COLUMN descricao VARCHAR(255);

insert into quarto (id_hospedagem,nome,ativo,preco,descricao) values
(1,'Quarto 1',true,15,'Essa casa está localizada em uma região nobre da cidade de curitiba próxima de pontos turísticos e à 15min do centro. O quarto possui conforto extremo e a região calma te proporcionará bons minutos de descanso.');

create table imagem (
   id_imagem serial primary key
  ,id_quarto integer REFERENCES quarto (id_quarto)
  ,id_firebase character varying(50) NOT NULL 
);

create table quarto_adicional (
   id_quarto integer REFERENCES quarto (id_quarto)
  ,id_adicional integer REFERENCES adicional (id_adicional)
  ,primary key(id_quarto, id_adicional)
);
insert into quarto_adicional (id_quarto,id_adicional) values (1,1);
insert into quarto_adicional (id_quarto,id_adicional) values (1,2);
insert into quarto_adicional (id_quarto,id_adicional) values (1,3);


-- --------------------------------------------------------------------------------------------
create table reserva (
  id_reserva serial primary key
  ,id_quarto integer REFERENCES quarto (id_quarto)
  ,id_usuario integer REFERENCES usuario (id_usuario)
  ,id_hr_chegada integer REFERENCES horas (id_horas)
  ,hr_saida time 
  ,pago boolean NOT NULL
  ,dt_reserva date --dt em q foi feita a reserva
);

create table reserva_pagamento(
  id_forma_pagamento integer REFERENCES forma_pagamento (id_forma_pagamento)
  ,id_reserva integer REFERENCES reserva (id_reserva)
  ,preco decimal
  ,primary key(id_reserva,id_forma_pagamento)
);

create table quarto_horas (
  id_quarto integer REFERENCES quarto (id_quarto)
  ,id_horas integer REFERENCES horas (id_horas)
  ,disponibilidade character varying(50) CHECK (disponibilidade SIMILAR TO 'DISPONÍVEL|RESERVADO|CHECK-IN|REGISTRADO') NOT NULL 
  ,dt_reservada DATE --dia q o quarto estah reservado
  ,primary key(id_quarto, id_horas, dt_reservada)
);

create table avaliacao (
  id_quarto integer REFERENCES quarto (id_quarto)
  ,id_reserva integer REFERENCES reserva (id_reserva)
  ,id_usuario integer REFERENCES usuario (id_usuario)
  ,classificacao integer
  ,primary key(id_reserva)
);

-- insere usuario
insert into pessoa (nome,data_nascimento,email,senha,tipo,ativo,cpf) values
('José da Silva','1994-09-06','jose@gmail.com','8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92','U',true,'34643117087');

insert into pessoa (nome,data_nascimento,email,senha,tipo,ativo,cpf) values
('Marta Oliveira','1990-10-16','martaoli@gmail.com','8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92','U',true,'765766810547');

insert into usuario values (23);
insert into usuario values (24);

insert into pessoa (nome,data_nascimento,email,senha,tipo,ativo,cpf) values 
('Guilherme Alves','1964-02-06','guialves@gmail.com','8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92','P',true,'35288679061');

insert into pessoa (nome,data_nascimento,email,senha,tipo,ativo,cpf) values 
('Leandro Dias','1984-06-18','leandro@gmail.com','8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92','P',true,'71291759077');

insert into pessoa (nome,data_nascimento,email,senha,tipo,ativo,cpf) values 
('Ivete dos Anjos','1969-09-09','ivete@yahoo.com','8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92','P',true,'33504753021');

insert into parceiro (id_parceiro) values (25);
insert into parceiro (id_parceiro) values (26);
insert into parceiro (id_parceiro) values (27);


insert into endereco (rua,numero,complemento,id_bairro,cep) values ('R. Inácio Lustosa', 643 ,'',62,'80510000');
insert into endereco (rua,numero,complemento,id_bairro,cep) values ('Av. Mal. Floriano Peixoto', 355 ,'',27,'82590300');
insert into endereco (rua,numero,complemento,id_bairro,cep) values ('R. Barão do Rio Branco', 173 ,'',27,'80010180');
insert into endereco (rua,numero,complemento,id_bairro,cep) values ('Rua XV de Novembro', 1182 ,'',27,'80045270');
insert into endereco (rua,numero,complemento,id_bairro,cep) values ('Rua Lysimaco Ferreira da Costa', 376,'',30,'82590300');
insert into endereco (rua,numero,complemento,id_bairro,cep) values ('R. Dr. Roberto Barrozo', 471,'',30,'82590300');
insert into endereco (rua,numero,complemento,id_bairro,cep) values ('R. Jacarezinho', 754,'',46,'80710150');

insert into hospedagem (nome,ativo,id_parceiro,id_endereco) values ('Mendes',true,2,11);
insert into hospedagem (nome,ativo,id_parceiro,id_endereco) values ('Garcia',true,2,12);
insert into hospedagem (nome,ativo,id_parceiro,id_endereco) values ('Pensionato Albertini',true,2,13);
insert into hospedagem (nome,ativo,id_parceiro,id_endereco) values ('Casa Villani',true,2,14);
insert into hospedagem (nome,ativo,id_parceiro,id_endereco) values ('Prédio Vich',true,2,15);
insert into hospedagem (nome,ativo,id_parceiro,id_endereco) values ('Prédio Urban',true,6,16);
insert into hospedagem (nome,ativo,id_parceiro,id_endereco) values ('Edifício Isay',true,27,17);

insert into quarto (id_hospedagem,nome,ativo,preco,descricao) values (11,'Quarto um', true, 15, 'Essa casa está localizada em uma região nobre da cidade de curitiba próxima de pontos turísticos e à 15min do centro. O quarto possui conforto extremo e a região calma te proporcionará bons minutos de descanso.');

insert into quarto (id_hospedagem,nome,ativo,preco,descricao) values (12,'Studio 55', true, 25, 'Tenha uma experiência fantástica. Um apartamento moderno, aconchegante e totalmente equipado, muito bem localizado no Centro de Curitiba.');

insert into quarto (id_hospedagem,nome,ativo,preco,descricao) values (12,'Studio 50', true, 22, 'Quarto espaçoso, arejado e aconchegante com uma cama de casal, tudo isso acompanhado de ar condicionado e TV por assinatura em HD. Ótima localização e várias linhas de ônibus.');

insert into quarto (id_hospedagem,nome,ativo,preco,descricao) values (13,'Quarto com ar-condicionado', true, 13, 'Quarto super aconchegante. Perto de bancos, supermercados, comércio, lazer a céu aberto, prática de esportes e diversos bares e restaurantes para todos os gostos!');

insert into quarto (id_hospedagem,nome,ativo,preco,descricao) values (13,'Fantástico quarto com localização perfeita', true, 28, 'Lindo apartamento com localização perfeita, tudo novo, equipamentos de primeira qualidade, camas King size e banheiro. Ar condicionado, tv completa e internet Wi- Fi.');

insert into imagem (id_quarto,id_firebase) values (1,'');
insert into imagem (id_quarto,id_firebase) values (1,'');
insert into imagem (id_quarto,id_firebase) values (1,'');
insert into imagem (id_quarto,id_firebase) values (1,'');
insert into imagem (id_quarto,id_firebase) values (1,'');
insert into imagem (id_quarto,id_firebase) values (1,'');
insert into imagem (id_quarto,id_firebase) values (1,'');
insert into imagem (id_quarto,id_firebase) values (1,'');

insert into quarto_adicional (id_quarto,id_adicional) values (11,1);
insert into quarto_adicional (id_quarto,id_adicional) values (12,1);
insert into quarto_adicional (id_quarto,id_adicional) values (12,2);
insert into quarto_adicional (id_quarto,id_adicional) values (13,1);
insert into quarto_adicional (id_quarto,id_adicional) values (13,2);
insert into quarto_adicional (id_quarto,id_adicional) values (13,3);

insert into reserva (id_quarto,id_usuario,id_hr_chegada,hr_saida,pago,dt_reserva) values (13,21,1,null,false,'2019-10-28');
insert into reserva (id_quarto,id_usuario,id_hr_chegada,hr_saida,pago,dt_reserva) values (12,21,4,null,false,'2019-10-28');
insert into reserva (id_quarto,id_usuario,id_hr_chegada,hr_saida,pago,dt_reserva) values (13,21,1,null,false,'2019-10-27');
insert into reserva (id_quarto,id_usuario,id_hr_chegada,hr_saida,pago,dt_reserva) values (11,22,2,null,false,'2019-10-23');
insert into reserva (id_quarto,id_usuario,id_hr_chegada,hr_saida,pago,dt_reserva) values (13,22,3,null,false,'2019-10-22');
insert into reserva (id_quarto,id_usuario,id_hr_chegada,hr_saida,pago,dt_reserva) values (11,22,4,null,false,'2019-10-21');
insert into reserva (id_quarto,id_usuario,id_hr_chegada,hr_saida,pago,dt_reserva) values (12,22,2,null,false,'2019-10-20');

insert into quarto_horas (id_quarto,id_horas,disponibilidade,dt_reservada) values (13,1,'RESERVADO','2019-10-28');
insert into quarto_horas (id_quarto,id_horas,disponibilidade,dt_reservada) values (12,4,'RESERVADO','2019-10-28');
insert into quarto_horas (id_quarto,id_horas,disponibilidade,dt_reservada) values (13,1,'CHECK-IN','2019-10-27');
insert into quarto_horas (id_quarto,id_horas,disponibilidade,dt_reservada) values (11,2,'CHECK-IN','2019-10-23');
insert into quarto_horas (id_quarto,id_horas,disponibilidade,dt_reservada) values (13,3,'CHECK-IN','2019-10-22');
insert into quarto_horas (id_quarto,id_horas,disponibilidade,dt_reservada) values (11,4,'REGISTRADO','2019-10-21');
insert into quarto_horas (id_quarto,id_horas,disponibilidade,dt_reservada) values (12,2,'REGISTRADO','2019-10-20');

insert into reserva_pagamento (id_forma_pagamento, id_reserva,preco) values (2,15,22);
insert into reserva_pagamento (id_forma_pagamento, id_reserva,preco) values (3,16,13);

insert into avaliacao (id_quarto,id_reserva,id_usuario,classificacao) values (11,15,22,4.5);
insert into avaliacao (id_quarto,id_reserva,id_usuario,classificacao) values (12,16,22,3.5);
