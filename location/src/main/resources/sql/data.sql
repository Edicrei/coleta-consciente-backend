-- delete dos dados existentes
DELETE FROM tb_location;

-- recontagem do auto_increment
ALTER SEQUENCE tb_location_id_seq RESTART WITH 1;

-- insercao de dados
INSERT INTO tb_location 
(name, street, number, complement, latitude, longitude, material, is_valid, registration_date, update_date, user_id) 
VALUES
('Catedral da Sé', 'Praça da Sé', 's/n', 'Catedral', -23.554233110937627, -46.627746988132216, 'PILHA', true, '2021-01-01 05:32:15', '2021-01-01 05:32:15', 1),
('MASP', 'Avenida Paulista', '1578', '', -23.559870550749405, -46.65633090013175, 'BATERIA', false, '2020-02-02 08:22:10', '2020-02-02 08:22:10', 1),
('Museu do Ipiranga', 'Rua dos Patriotas', '20', '', -23.58542037063774, -46.610242994775284, 'AMBOS', false, '2019-03-03 17:42:32', '2019-03-03 17:42:32', 1),
('Museu Afro Brasil', 'Avenida Pedro Álvares Cabral', 's/n', 'Portão 10', -23.58351754725252, -46.65950575002301, 'AMBOS', true, '2018-04-04 23:05:01', '2018-04-04 23:05:01', 1),
('Praça da República', 'Praça da República', 's/n', '', -23.543253682673445, -46.64261326059175, 'PILHA', true, '2017-05-05 23:05:01', '2017-05-05 23:05:01', 1);