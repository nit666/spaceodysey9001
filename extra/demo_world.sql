-- test set up

-- faction

insert into faction(faction_id, name, email, password)
select 1, 'test', 'test@here.com', 'test'
where not exists (
	select 1 from faction where faction_id = 1
);

-- planets
insert into planet (planet_id, faction_id, xloc, yloc, parent, type, minerals, gas, water, name)
select 1, 1, 0, 0, 0, 'Ghia', 2, 2, 2, 'New Earth'
where not exists (
	select 1 from planet where planet_id = 1
);

insert into planet (planet_id, faction_id, xloc, yloc, parent, type, minerals, gas, water, name)
select 2, 0, 0, 0, 0, 'Small Rock', 2, 0, 0, 'Glogon I'
where not exists (
	select 1 from planet where planet_id = 2
);

insert into planet (planet_id, faction_id, xloc, yloc, parent, type, minerals, gas, water, name)
select 3, 0, 0, 0, 0, 'Gas Giant', 0, 3, 0, 'Glogon II'
where not exists (
	select 1 from planet where planet_id = 3
);

insert into planet (planet_id, faction_id, xloc, yloc, parent, type, minerals, gas, water, name)
select 4, 0, 0, 0, 0, 'Medium Water', 0, 1, 2, 'Glogon III'
where not exists (
	select 1 from planet where planet_id = 4
);

insert into planet (planet_id, faction_id, xloc, yloc, parent, type, minerals, gas, water, name)
select 5, 0, 0, 0, 0, 'Medium Rock', 3, 0, 0, 'XVII'
where not exists (
	select 1 from planet where planet_id = 5
);

insert into planet (planet_id, faction_id, xloc, yloc, parent, type, minerals, gas, water, name)
select 6, 0, 0, 0, 0, 'Ice World', 0, 0, 3, 'Hedros'
where not exists (
	select 1 from planet where planet_id = 6
);

insert into planet (planet_id, faction_id, xloc, yloc, parent, type, minerals, gas, water, name)
select 7, 0, 0, 0, 0, 'Medium Rock', 2, 0, 1, 'Oparious'
where not exists (
	select 1 from planet where planet_id = 7
);

insert into planet (planet_id, faction_id, xloc, yloc, parent, type, minerals, gas, water, name)
select 8, 0, 0, 0, 0, 'Gas Giant', 0, 3, 0, 'Juno'
where not exists (
	select 1 from planet where planet_id = 8
);

insert into planet (planet_id, faction_id, xloc, yloc, parent, type, minerals, gas, water, name)
select 9, 0, 0, 0, 0, 'Small Rock', 1, 0, 1, 'Noveri'
where not exists (
	select 1 from planet where planet_id = 9
);

insert into planet (planet_id, faction_id, xloc, yloc, parent, type, minerals, gas, water, name)
select 10, 0, 0, 0, 0, 'Meduim Rock', 3, 0, 0, '3rd Rock From the sun'
where not exists (
	select 1 from planet where planet_id = 10
);


