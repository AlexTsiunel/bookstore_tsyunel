TRUNCATE TABLE books CASCADE;
TRUNCATE TABLE users CASCADE;
TRUNCATE TABLE covers CASCADE;

INSERT INTO covers (name)
VALUES ('SOFT'),
	   ('HARD'),
	   ('SPECIAL');
	  
INSERT INTO roles (name)
VALUES ('ADMIN'),
	   ('MANAGER'),
	   ('USER');

INSERT INTO books (title, author, isbn, pages, price, cover_id)
VALUES ('An Immense World: How Animal Senses Reveal the Hidden Realms Around Us', 'Ed Yong','9780593133231', 464, 30, (SELECT id FROM covers WHERE name = 'SOFT')),
	('Parable of the Sower','Octavia E. Butler','9781538732182', 368, 15.8, (SELECT id FROM covers WHERE name = 'SOFT')),
	('Growing Joy: The Plant Lover''s Guide to Cultivating Happiness (and Plants)','Maria Failla','9781250814890', 272, 20.45, (SELECT id FROM covers WHERE name = 'SOFT')),
	('Good Inside: A Guide to Becoming the Parent You Want to Be','Becky Kennedy','9780063159488', 336, 26.96, (SELECT id FROM covers WHERE name = 'HARD')),
	('New Handbook for a Post-Roe America: The Complete Guide to Abortion Legality, Access, and Practical Support','Robin Marty','9781644210581', 320, 16.69, (SELECT id FROM covers WHERE name = 'SOFT')),
	('No More Police: A Case for Abolition','Mariame Kaba','9781620977323', 400, 17.65, (SELECT id FROM covers WHERE name = 'HARD')),
	('Parable of the Talents','Octavia E. Butler','9781538732199', 448, 17.65, (SELECT id FROM covers WHERE name = 'SOFT')),
	('Heal Your Way Forward: The Co-Conspirator''s Guide to an Antiracist Future','Myisha T. Hill','9781955905022', 240, 22.31, (SELECT id FROM covers WHERE name = 'SOFT')),
	('Braiding Sweetgrass','Robin Wall Kimmerer','9781571313560', 408, 32.55, (SELECT id FROM covers WHERE name = 'SOFT')),
	('What We Owe the Future','William Macaskill','9781541618626', 352, 29.76, (SELECT id FROM covers  WHERE name = 'SOFT')),
	('Book Lovers','Emily Henry','9780593334836', 400, 25.11, (SELECT id FROM covers WHERE name = 'SOFT')),
	('I''m Still Here: Black Dignity in a World Made for Whiteness','Austin Channing Brow','9781524760854', 192, 23.25, (SELECT id FROM covers WHERE name = 'SOFT')),
	('The Seven Husbands of Evelyn Hugo','Taylor Jenkins Reid','9781501161933', 400, 27.9, (SELECT id FROM covers WHERE name = 'SOFT')),
	('Essential Labor: Mothering as Social Change','Angela Garbes','9780062937360', 256, 24.17, (SELECT id FROM covers WHERE name = 'SOFT')),
	('Tanqueray','Stephanie Johnson','9781250278272', 192, 24.99, (SELECT id FROM covers WHERE name = 'SOFT')),
	('Violeta','Isabel Allende','9780593496206', 336, 26.04, (SELECT id FROM covers WHERE name = 'HARD')),
	('Crying in the Bathroom: A Memoir','Erika L. Sanchez','9780593296936', 256, 25.11, (SELECT id FROM covers WHERE name = 'SOFT')),
	('Velvet Was the Night','Silvia Moreno-Garcia','9780593356821', 304, 26.04, (SELECT id FROM covers WHERE name = 'SPECIAL')),
	('Part of Your World','Abby Jimenez','9781538704370', 400, 24.18, (SELECT id FROM covers WHERE name = 'SOFT')),
	('Trust','Hernan Diaz','9780593420317', 416, 26.04, (SELECT id FROM covers WHERE name = 'SOFT')),
	('Secret Identity','Alex Segura','9781250801746', 368, 26.96, (SELECT id FROM covers WHERE name = 'HARD')),
	('Thrill of the Hunt','Rita Mae Brown','9780593357606', 304, 26.04, (SELECT id FROM covers WHERE name = 'SOFT')),
	('The Self-Made Widow','Fabian Nicieza','9780593191293', 400, 25.11, (SELECT id FROM covers WHERE name = 'SPECIAL')),
	('Local Gone Missing','Fiona Barton','9781984803047', 384, 25.11, (SELECT id FROM covers WHERE name = 'SOFT')),
	('The Drowning Sea: A Maggie d''Arcy Mystery','Sarah Stewart Taylor','9781250826657', 352, 26.03, (SELECT id FROM covers WHERE name = 'SPECIAL'));

INSERT INTO users (first_name, last_name, email, password, role_id )
VALUES ('Neena', 'Kochar','kochhar@gmail.com', 'kochhar022005', (SELECT id FROM roles WHERE name = 'ADMIN')),
	('Lex', 'De Haan','haan@gmail.com', 'haan1986', (SELECT id FROM roles WHERE name = 'MANAGER')),
	('Alexander', 'Hunold','hunold@gmail.com', 'hunol02051786', (SELECT id FROM roles WHERE name = 'MANAGER')),
	('Bruce', 'Ernst','ernst@gmail.com', 'ernst2022', (SELECT id FROM roles WHERE name = 'USER')),
	('David', 'Austin','austin@gmail.com', 'austin2006', (SELECT id FROM roles WHERE name = 'USER')),
	('Valli', 'Pataballa','pataballa@gmail.com', 'pataballa071983', (SELECT id FROM roles WHERE name = 'USER')),
	('Diana', 'Lorentz','lorentz@gmail.com', 'lorentz12345', (SELECT id FROM roles WHERE name = 'USER')),
	('Nancy', 'Greenberg','greenberg@gmail.com', 'greenberg121314', (SELECT id FROM roles WHERE name = 'USER')),
	('Daniel', 'Faviet','faviet@gmail.com', 'faviet1812', (SELECT id FROM roles WHERE name = 'USER')),
	('Johnr', 'Chen','chen@gmail.com', 'chen654321', (SELECT id FROM roles WHERE name = 'USER')),
	('Ismael', 'Sciarra','sciarra@gmail.com', 'sciarra262823', (SELECT id FROM roles WHERE name = 'USER')),
	('Jose Manuel', 'Urman','urman@gmail.com', 'urman1985', (SELECT id FROM roles WHERE name = 'USER')),
	('Luis', 'Popp','popp@gmail.com', 'popp19450905', (SELECT id FROM roles WHERE name = 'USER')),
	('Den', 'Raphaely','raphaely@gmail.com', 'raphaely22061941', (SELECT id FROM roles WHERE name = 'USER')),
	('Alexander', 'Khoo','khoo@gmail.com', 'khoo01091939', (SELECT id FROM roles WHERE name = 'USER')),
	('Shelli', 'Baida','baida@gmail.com', 'baida10051945', (SELECT id FROM roles WHERE name = 'USER')),
	('Sigal', 'Tobias','tobias@gmail.com', 'tobias2007', (SELECT id FROM roles WHERE name = 'USER')),
	('Guy', 'Himuro','himuro@gmail.com', 'himuro12311986', (SELECT id FROM roles WHERE name = 'USER')),
	('Karen', 'Colmenares','colmenares@gmail.com', 'colmenares1816', (SELECT id FROM roles WHERE name = 'USER')),
	('Matthew', 'Weiss','weiss@gmail.com', 'weiss101', (SELECT id FROM roles WHERE name = 'USER')),
	('Adam', 'Fripp','fripp@gmail.com', 'fripp103', (SELECT id FROM roles WHERE name = 'USER')),
	('Payam', 'Kaufling','kaufling@gmail.com', 'kaufling112', (SELECT id FROM roles WHERE name = 'USER')),
	('Shanta', 'Vollman','vollman@gmail.com', 'vollman911', (SELECT id FROM roles WHERE name = 'USER')),
	('Kevin', 'Mourgos','mourgos@gmail.com', 'mourgos5957', (SELECT id FROM roles WHERE name = 'USER')),
	('Julia', 'Nayer','nayer@gmail.com', 'nayer02031982', (SELECT id FROM roles WHERE name = 'USER'));


