--CREATE DATABASE "bookstore";
CREATE TABLE books (
	book_id BIGSERIAL PRIMARY KEY,
	title TEXT NOT NULL,
	author TEXT NOT NULL,
	isbn TEXT UNIQUE NOT NULL,
	pages SMALLINT,
	price DECIMAL (6, 2)
);

INSERT INTO books (title, author, isbn, pages, price)
VALUES ('An Immense World: How Animal Senses Reveal the Hidden Realms Around Us', 'Ed Yong','9780593133231', 464, 30 ),
	('Parable of the Sower','Octavia E. Butler','9781538732182', 368, 15.8),
	('Growing Joy: The Plant Lover''s Guide to Cultivating Happiness (and Plants)','Maria Failla','9781250814890', 272, 20.45),
	('Good Inside: A Guide to Becoming the Parent You Want to Be','Becky Kennedy','9780063159488', 336, 26.96),
	('New Handbook for a Post-Roe America: The Complete Guide to Abortion Legality, Access, and Practical Support','Robin Marty','9781644210581', 320, 16.69),
	('No More Police: A Case for Abolition','Mariame Kaba','9781620977323', 400, 17.65),
	('Parable of the Talents','Octavia E. Butler','9781538732199', 448, 17.65),
	('Heal Your Way Forward: The Co-Conspirator''s Guide to an Antiracist Future','Myisha T. Hill','9781955905022', 240, 22.31),
	('Braiding Sweetgrass','Robin Wall Kimmerer','9781571313560', 408, 32.55),
	('What We Owe the Future','William Macaskill','9781541618626', 352, 29.76),
	('Book Lovers','Emily Henry','9780593334836', 400, 25.11),
	('I''m Still Here: Black Dignity in a World Made for Whiteness','Austin Channing Brow','9781524760854', 192, 23.25),
	('The Seven Husbands of Evelyn Hugo','Taylor Jenkins Reid','9781501161933', 400, 27.9),
	('Essential Labor: Mothering as Social Change','Angela Garbes','9780062937360', 256, 24.17),
	('Tanqueray','Stephanie Johnson','9781250278272', 192, 24.99),
	('Violeta','Isabel Allende','9780593496206', 336, 26.04),
	('Crying in the Bathroom: A Memoir','Erika L. Sanchez','9780593296936', 256, 25.11),
	('Velvet Was the Night','Silvia Moreno-Garcia','9780593356821', 304, 26.04),
	('Part of Your World','Abby Jimenez','9781538704370', 400, 24.18),
	('Trust','Hernan Diaz','9780593420317', 416, 26.04),
	('Secret Identity','Alex Segura','9781250801746', 368, 26.96),
	('Thrill of the Hunt','Rita Mae Brown','9780593357606', 304, 26.04),
	('The Self-Made Widow','Fabian Nicieza','9780593191293', 400, 25.11),
	('Local Gone Missing','Fiona Barton','9781984803047', 384, 25.11),
	('The Drowning Sea: A Maggie d''Arcy Mystery','Sarah Stewart Taylor','9781250826657', 352, 26.03);

CREATE TABLE users (
	user_id BIGSERIAL PRIMARY KEY,
	first_name TEXT NOT NULL,
	last_name TEXT NOT NULL,
	email TEXT UNIQUE NOT NULL,
	password TEXT NOT NULL
);

INSERT INTO users (first_name, last_name, email, password)
VALUES ('Neena', 'Kochar','kochhar@gmail.com', 'kochhar022005'),
	('Lex', 'De Haan','haan@gmail.com', 'haan1986'),
	('Alexander', 'Hunold','hunold@gmail.com', 'hunol02051786'),
	('Bruce', 'Ernst','ernst@gmail.com', 'ernst2022'),
	('David', 'Austin','austin@gmail.com', 'austin2006'),
	('Valli', 'Pataballa','pataballa@gmail.com', 'pataballa071983'),
	('Diana', 'Lorentz','lorentz@gmail.com', 'lorentz12345'),
	('Nancy', 'Greenberg','greenberg@gmail.com', 'greenberg121314'),
	('Daniel', 'Faviet','faviet@gmail.com', 'faviet1812'),
	('Johnr', 'Chen','chen@gmail.com', 'chen654321'),
	('Ismael', 'Sciarra','sciarra@gmail.com', 'sciarra262823'),
	('Jose Manuel', 'Urman','urman@gmail.com', 'urman1985'),
	('Luis', 'Popp','popp@gmail.com', 'popp19450905'),
	('Den', 'Raphaely','raphaely@gmail.com', 'raphaely22061941'),
	('Alexander', 'Khoo','khoo@gmail.com', 'khoo01091939'),
	('Shelli', 'Baida','baida@gmail.com', 'baida10051945'),
	('Sigal', 'Tobias','tobias@gmail.com', 'tobias2007'),
	('Guy', 'Himuro','himuro@gmail.com', 'himuro12311986'),
	('Karen', 'Colmenares','colmenares@gmail.com', 'colmenares1816'),
	('Matthew', 'Weiss','weiss@gmail.com', 'weiss101'),
	('Adam', 'Fripp','fripp@gmail.com', 'fripp103'),
	('Payam', 'Kaufling','kaufling@gmail.com', 'kaufling112'),
	('Shanta', 'Vollman','vollman@gmail.com', 'vollman911'),
	('Kevin', 'Mourgos','mourgos@gmail.com', 'mourgos5957'),
	('Julia', 'Nayer','nayer@gmail.com', 'nayer02031982');
