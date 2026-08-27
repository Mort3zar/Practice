drop table if exists contracts cascade;
drop table if exists players cascade;
drop table if exists agents  cascade;
drop table  if exists clubs cascade;


create table agents (
	id int4 generated always as identity not null,
	full_name varchar(50),
	rating numeric(4,2) default 5.00 not null,
	constraint agents_pk primary key (id),
	constraint agents_full_name_unique unique(full_name),
	constraint agents_full_name_not_empty check (full_name is not null and trim(full_name) <> ''),
	constraint agents_rating_not_degree check (rating >= 0 and rating <= 10)
);


create table clubs (
	id int4 generated always as identity not null,
	name varchar(30),
	city varchar(40),
	budget int4 not null,
	constraint clubs_pk primary key (id),
	constraint clubs_name_unique unique(name),
	constraint clubs_name_not_empty check (name is not null and trim(name) <> ''),
	constraint clubs_city_not_empty check (city is not null and trim(city) <> ''),
	constraint clubs_budget_not_degree check (budget >= 0)
);


create table players (
	id int4 generated always as identity not null,
	agent_id int4,
	full_name varchar(50),
	position varchar(30),
	nationality varchar(50),
	constraint players_pk primary key (id),
	constraint players_full_name_unique unique(full_name),
	constraint players_full_name_not_empty check (full_name is not null and trim(full_name) <> ''),
	constraint players_position_not_empty check (position is not null and trim(position) <> ''),
	constraint players_nationality_not_empty check (nationality is not null and trim(nationality) <> '')
);


alter table players
add constraint players_agents_fk
foreign key (agent_id) references agents(id) on delete set null;


create table contracts (
	id int4 generated always as identity not null,
	club_id int4 not null,
	player_id int4 not null,
	weekly_salary int4 not null,
	start_date date default current_date not null,
	end_date date not null,
	constraint contracts_pk primary key (id),
	constraint contracts_weekly_salary_not_degree check (weekly_salary > 0),
	constraint contracts_date check (start_date < end_date)
);



alter table contracts
add constraint contracts_players_fk
foreign key (player_id) references players(id) on delete cascade;

alter table contracts
add constraint contracts_clubs_fk
foreign key (club_id) references clubs(id) on delete cascade;


