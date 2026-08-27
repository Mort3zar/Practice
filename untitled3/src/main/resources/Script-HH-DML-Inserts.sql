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
INSERT INTO players (agent_id, full_name, position, nationality) VALUES
(1, 'Cristiano Ronaldo', 'Forward', 'Portugal'),
(2, 'Erling Haaland', 'Forward', 'Norway'),
(3, 'Jack Grealish', 'Midfielder', 'England'),
(4, 'Robert Lewandowski', 'Forward', 'Poland'),
(5, 'Marquinhos', 'Defender', 'Brazil'),
(6, 'Toni Kroos', 'Midfielder', 'Germany'),
(7, 'Willian', 'Winger', 'Brazil'),
(8, 'Romelu Lukaku', 'Forward', 'Belgium'),
(9, 'Leonardo Bonucci', 'Defender', 'Italy'),
(NULL, 'Kylian Mbappe', 'Forward', 'France');

-- 4. Контракты
INSERT INTO contracts (club_id, player_id, weekly_salary, start_date, end_date) VALUES
(1, 1, 500000, '2023-01-15', '2025-06-30'),
(3, 2, 450000, '2022-07-01', '2027-06-30'),
(3, 3, 300000, '2021-08-05', '2026-06-30'),
(2, 4, 400000, '2022-07-16', '2026-06-30'),
(5, 5, 250000, '2020-07-01', '2024-06-30'),
(1, 6, 310000, '2019-07-01', '2024-06-30'),
(6, 7, 120000, '2023-09-01', '2025-06-30'),
(7, 8, 280000, '2023-08-10', '2024-06-30'),
(8, 9, 150000, '2021-07-01', '2023-06-30'),
(5, 10, 600000, '2022-05-21', '2028-06-30');