-- auto messaages

insert into auto_message(name, title, message) 
select 'construction_complete', 'Construction Complete', 'Construction of a new {0} has been completed at {1}.'
where not exists (
	select 1 from auto_message
	where name = 'construction_complete'
);

insert into auto_message(name, title, message) 
select 'move_complete', 'Travel Complete', 'The {0} travelling to {1} has arrived.'
where not exists (
	select 1 from auto_message
	where name = 'move_complete'
);

-- faction

insert into faction(faction_id, name, email, password)
select 0, '<univ>', 'none', ''
where not exists (
	select 1 from faction where faction_id = 0
);

-- planet

insert into planet (planet_id, faction_id, xloc, yloc, parent, type, minerals, gas, water, name)
select 0, 0, 0, 0, 0, 'none', 0, 0, 0, 'Open Space'
where not exists (
	select 1 from planet where planet_id = 0
);

-- web actions
insert into web_action (page_name, page_text)
select 'ViewPlanet', ''
where not exists (
	select 1 from web_action 
	where page_name = 'ViewPlanet'
);

insert into web_action (page_name, page_text)
select 'ViewAllPlanets', ''
where not exists (
	select 1 from web_action 
	where page_name = 'ViewAllPlanets'
);

insert into web_action (page_name, page_text)
select 'Build', ''
where not exists (
	select 1 from web_action 
	where page_name = 'Build'
);

insert into web_action (page_name, page_text)
select 'UnitActions', ''
where not exists (
	select 1 from web_action 
	where page_name = 'UnitActions'
);

insert into web_action (page_name, page_text)
select 'MoveUnit', ''
where not exists (
	select 1 from web_action 
	where page_name = 'MoveUnit'
);

insert into web_action (page_name, page_text)
select 'MoveCargo', ''
where not exists (
	select 1 from web_action 
	where page_name = 'MoveCargo'
);

-- web action links

insert into web_action_link(page_name, link_action, link_order, link_text)
select 'ViewPlanet','Build','1','Build Something'
where not exists (
	select 1 from web_action_link 
	where page_name = 'ViewPlanet'
	and link_action = 'Build'
);


insert into web_action_link(page_name, link_action, link_order, link_text)
select 'ViewPlanet','ViewAllPlanets','2','View a different planet'
where not exists (
	select 1 from web_action_link 
	where page_name = 'ViewPlanet'
	and link_action = 'ViewAllPlanets'
);


insert into web_action_link(page_name, link_action, link_order, link_text)
select 'Build','ViewPlanet','1','Back to planet view'
where not exists (
	select 1 from web_action_link 
	where page_name = 'Build'
	and link_action = 'ViewPlanet'
);


insert into web_action_link(page_name, link_action, link_order, link_text)
select 'Build','ViewAllPlanets','2','View a different planet'
where not exists (
	select 1 from web_action_link 
	where page_name = 'Build'
	and link_action = 'ViewAllPlanets'
);

insert into web_action_link(page_name, link_action, link_order, link_text)
select 'UnitActions','MoveUnit','1','Move to a different planet'
where not exists (
	select 1 from web_action_link 
	where page_name = 'UnitActions'
	and link_action = 'MoveUnit'
);

insert into web_action_link(page_name, link_action, link_order, link_text)
select 'UnitActions','MoveCargo','2','Load/unload cargo'
where not exists (
	select 1 from web_action_link 
	where page_name = 'UnitActions'
	and link_action = 'MoveCargo'
);

insert into web_action_link(page_name, link_action, link_order, link_text)
select 'UnitActions','ViewPlanet','3','Back to planet view'
where not exists (
	select 1 from web_action_link 
	where page_name = 'UnitActions'
	and link_action = 'ViewPlanet'
);

insert into web_action_link(page_name, link_action, link_order, link_text)
select 'MoveUnit','ViewPlanet','1','Back to planet view'
where not exists (
	select 1 from web_action_link 
	where page_name = 'MoveUnit'
	and link_action = 'ViewPlanet'
);

insert into web_action_link(page_name, link_action, link_order, link_text)
select 'MoveCargo','ViewPlanet','1','Back to planet view'
where not exists (
	select 1 from web_action_link 
	where page_name = 'MoveCargo'
	and link_action = 'ViewPlanet'
);

-- unit types
insert into unit_type (type_id)
select 'Army'
where not exists (
	select 1 from unit_type where type_id = 'Army'
);

insert into unit_type (type_id)
select 'Bomber'
where not exists (
	select 1 from unit_type where type_id = 'Bomber'
);


insert into unit_type (type_id)
select 'ColonyShip'
where not exists (
	select 1 from unit_type where type_id = 'ColonyShip'
);

insert into unit_type (type_id)
select 'Crusier'
where not exists (
	select 1 from unit_type where type_id = 'Crusier'
);

insert into unit_type (type_id)
select 'Destroyer'
where not exists (
	select 1 from unit_type where type_id = 'Destroyer'
);

insert into unit_type (type_id)
select 'Fighter'
where not exists (
	select 1 from unit_type where type_id = 'Fighter'
);

insert into unit_type (type_id)
select 'MiningColony'
where not exists (
	select 1 from unit_type where type_id = 'MiningColony'
);

insert into unit_type (type_id)
select 'Missile'
where not exists (
	select 1 from unit_type where type_id = 'Missile'
);

insert into unit_type (type_id)
select 'MissileLauncher'
where not exists (
	select 1 from unit_type where type_id = 'MissileLauncher'
);

insert into unit_type (type_id)
select 'Probe'
where not exists (
	select 1 from unit_type where type_id = 'Probe'
);

insert into unit_type (type_id)
select 'Tank'
where not exists (
	select 1 from unit_type where type_id = 'Tank'
);

insert into unit_type (type_id)
select 'Transport'
where not exists (
	select 1 from unit_type where type_id = 'Transport'
);