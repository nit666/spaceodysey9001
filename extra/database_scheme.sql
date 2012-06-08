-- Table: faction

-- DROP TABLE faction;
drop table auto_message;
drop table message;
drop table web_action_link;
drop table web_action;
drop table knows_about;
drop table unit_type;
drop table unit;
drop table planet_link;
drop table planet;
drop table faction;

CREATE TABLE faction
(
  faction_id bigint NOT NULL,
  name character varying(40) NOT NULL,
  email character varying(255),
  password character varying(20),
  minerals integer default 0,
  gas integer default 0,
  water integer default 0,
  CONSTRAINT faction_pkey PRIMARY KEY (faction_id),
  CONSTRAINT faction_name_key UNIQUE (name)
);

-- Table: planet

-- DROP TABLE planet;

CREATE TABLE planet
(
  planet_id bigint NOT NULL,
  faction_id bigint NOT NULL,
  xloc integer NOT NULL,
  yloc integer NOT NULL,
  parent bigint,
  "type" character varying(20) NOT NULL,
  minerals integer NOT NULL,
  gas integer NOT NULL,
  water integer NOT NULL,
  name varchar(40) NOT NULL,
  CONSTRAINT planet_pkey PRIMARY KEY (planet_id),
  CONSTRAINT planet_faction_id_fkey FOREIGN KEY (faction_id)
      REFERENCES faction (faction_id)
);

create table unit_type (
    type_id varchar(40) not null,
	primary key(type_id)
);

create table unit (
    unit_id bigint not null, 
	faction_id bigint not null,
	xloc int not null, 
	yloc int not null,
    parent bigint,
	current_action int,
	current_target bigint,
	minerals int,
	gas int,
	water int,
	primary key(unit_id),
	foreign key(faction_id) references faction
);

create table knows_about (
    faction_id bigint not null,
	obj_id bigint not null,
	obj_type char(1) not null,
	primary key(faction_id, obj_id),
	foreign key(faction_id) references faction
);

create table message (
	message_id bigint not null,
    sender bigint not null,
	reciever bigint not null,
	title varchar(255) not null,
	message text not null,
	date_sent timestamp not null,
	is_read char(1),
	primary key(message_id),
	foreign key(sender) references faction,
	foreign key(reciever) references faction
);

create table auto_message (
	name varchar(40) not null,
	title varchar(255) not null,
	message text not null,
	primary key(name)
);

create table web_action (
	page_name varchar(40) primary key, 
	page_text text not null
);

create table web_action_link (
	page_name varchar(40) not null,
	link_action varchar(40) not null,
	link_order int not null,
	link_text varchar not null,
	primary key (page_name, link_action),
	foreign key (page_name) references web_action(page_name),
	foreign key (link_action) references web_action(page_name)
);

create table planet_link (
	link_id bigint not null,
	source bigint not null,
	destination bigint not null,
	travel_time int not null,
	travel_cost int not null,
	primary key (link_id),
	foreign key (source) references planet,
	foreign key (destination) references planet
);
