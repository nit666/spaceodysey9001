
-- get all known factions for a particular faction
select f.faction_id, f.name, f.email, k.discovery_time
from faction f
join knows_about k on f.faction_id = k.obj_id
where k.obj_type = 'F'
and k.faction_id = '1'
;

-- get all known planets for a particular faction
select p.planet_id, p.faction_id, p.xloc, p.yloc, p.parent, p.type, p.minerals, p.gas, p.water, k.discovery_time
from planet p
join knows_about k on p.planet_id = k.obj_id
where p.faction_id = '1'
or (k.obj_type = 'P'
and k.faction_id = '1')
;

-- get all known units for a particular faction
select u.unit_id, u.faction_id, u.xloc, u.yloc, u.parent, u.current_action, u.current_target, u.minerals, u.gas, u.water, u.unit_type, k.discovery_time
from unit u
join knows_about k on u.unit_id = k.obj_id
where u.faction_id = '1'
or (k.obj_type = 'U'
and k.faction_id = '1')
;

-- get all unit_types known for a particular faction