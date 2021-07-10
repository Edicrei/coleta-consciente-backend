-- delete dos dados existentes
DELETE FROM tb_location;

-- recontagem do auto_increment
ALTER SEQUENCE tb_location_id_seq RESTART WITH 1;

-- insercao de dados
INSERT INTO tb_location (registration_date, update_date, latitude, longitude, street, number, complement, material) VALUES
('2021-01-01 05:32:15', '2021-01-01 05:32:15', -23.554233110937627, -46.627746988132216, 'Praça da Sé', 's\n', 'Catedral', 'PILHA'),
('2020-02-02 08:22:10', '2020-02-02 08:22:10', -23.559870550749405, -46.65633090013175, 'Avenida Paulista', '1578', NULL, 'BATERIA'),
('2019-03-03 17:42:32', '2019-03-03 17:42:32', -23.58542037063774, -46.610242994775284, 'Rua dos Patriotas', '20', NULL, 'AMBOS'),
('2018-04-04 23:05:01', '2018-04-04 23:05:01', -23.58351754725252, -46.65950575002301, 'Avenida Pedro Álvares Cabral', 's\n', 'Portão 10', 'AMBOS');