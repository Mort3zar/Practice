-- 1. Агенты
INSERT INTO agents (full_name, rating) VALUES
('Jorge Mendes', 9.50),
('Mino Raiola Agency', 9.10),
('Jonathan Barnett', 8.80),
('Pini Zahavi', 8.20),
('Giuliano Bertolucci', 7.90),
('Volker Struth', 8.50),
('Kia Joorabchian', 7.50),
('Federico Pastorello', 8.00),
('Alessandro Lucci', 7.80),
('Meissa NDiaye', 8.30);

-- 2. Клубы
INSERT INTO clubs (name, city, budget) VALUES
('Real Madrid', 'Madrid', 150000000),
('FC Barcelona', 'Barcelona', 90000000),
('Manchester City', 'Manchester', 200000000),
('Bayern Munich', 'Munich', 120000000),
('PSG', 'Paris', 180000000),
('Chelsea', 'London', 160000000),
('Juventus', 'Turin', 85000000),
('AC Milan', 'Milan', 70000000),
('Borussia Dortmund', 'Dortmund', 65000000),
('Arsenal', 'London', 110000000);

-- 3. Игроки
INSERT INTO players (agent_id, full_name, position, nationality)
VALUES
    (1, 'Cristiano Ronaldo', 'Forward', 'Portugal'),
    (1, 'Bernardo Silva', 'Midfielder', 'Portugal'),
    (1, 'Ruben Dias', 'Defender', 'Portugal'),
    (2, 'Erling Haaland', 'Forward', 'Norway'),
    (2, 'Matthijs de Ligt', 'Defender', 'Netherlands'),
    (2, 'Marco Verratti', 'Midfielder', 'Italy'),
    (3, 'Jack Grealish', 'Midfielder', 'England'),
    (3, 'Eduardo Camavinga', 'Midfielder', 'France'),
    (4, 'Robert Lewandowski', 'Forward', 'Poland'),
    (4, 'Christopher Nkunku', 'Forward', 'France'),
    (5, 'Marquinhos', 'Defender', 'Brazil'),
    (5, 'Bruno Guimaraes', 'Midfielder', 'Brazil'),
    (6, 'Toni Kroos', 'Midfielder', 'Germany'),
    (6, 'Dayot Upamecano', 'Defender', 'France'),
    (7, 'Willian', 'Winger', 'Brazil'),
    (8, 'Romelu Lukaku', 'Forward', 'Belgium'),
    (9, 'Leonardo Bonucci', 'Defender', 'Italy'),
    (NULL, 'Kylian Mbappe', 'Forward', 'France'),
    (NULL, 'Jude Bellingham', 'Midfielder', 'England')

-- 4. Контракты
INSERT INTO contracts (club_id, player_id, weekly_salary, start_date, end_date) VALUES
(1, 1, 500000, '2023-01-15', '2026-06-30'),
(3, 2, 350000, '2022-07-01', '2027-06-30'),
(3, 3, 300000, '2021-08-05', '2026-06-30'),
(3, 4, 450000, '2022-07-16', '2027-06-30'),
(4, 5, 280000, '2022-07-19', '2027-06-30'),
(5, 6, 250000, '2020-07-01', '2026-06-30'),
(3, 7, 300000, '2021-08-05', '2026-06-30'),
(1, 8, 210000, '2021-08-31', '2027-06-30'),
(2, 9, 400000, '2022-07-16', '2026-06-30'),
(6, 10, 220000, '2023-07-01', '2028-06-30'),
(5, 11, 270000, '2020-07-01', '2028-06-30'),
(10, 12, 310000, '2024-01-01', '2028-06-30'),
(1, 13, 310000, '2019-07-01', '2025-06-30'),
(4, 14, 190000, '2021-07-01', '2026-06-30'),
(6, 15, 120000, '2023-09-01', '2025-06-30'),
(7, 16, 280000, '2023-08-10', '2026-06-30'),
(8, 17, 150000, '2021-07-01', '2025-06-30'),
(5, 18, 600000, '2022-05-21', '2028-06-30'),
(1, 19, 340000, '2023-06-14', '2029-06-30');