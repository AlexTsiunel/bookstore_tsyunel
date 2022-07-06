CREATE DATABASE "bookstore";
CREATE TABLE books (
	book_id BIGSERIAL PRIMARY KEY,
	title TEXT NOT NULL,
	author TEXT NOT NULL,
	isbn TEXT UNIQUE NOT NULL,
	pages SMALLINT,
	price DECIMAL 
);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (1, 'An Immense World: How Animal Senses Reveal the Hidden Realms Around Us', 'Ed Yong','9780593133231', 464, 30 );

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (2, 'Parable of the Sower','Octavia E. Butler','9781538732182', 368, 15.8);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (3, 'Growing Joy: The Plant Lover''s Guide to Cultivating Happiness (and Plants)','Maria Failla','9781250814890', 272, 20.45);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (4, 'Good Inside: A Guide to Becoming the Parent You Want to Be','Becky Kennedy','9780063159488', 336, 26.96);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (5, 'New Handbook for a Post-Roe America: The Complete Guide to Abortion Legality, Access, and Practical Support','Robin Marty','9781644210581', 320, 16.69);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (6, 'No More Police: A Case for Abolition','Mariame Kaba','9781620977323', 400, 17.65);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (7, 'Parable of the Talents','Octavia E. Butler','9781538732199', 448, 17.65);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (8, 'Heal Your Way Forward: The Co-Conspirator''s Guide to an Antiracist Future','Myisha T. Hill','9781955905022', 240, 22.31);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (9, 'Braiding Sweetgrass','Robin Wall Kimmerer','9781571313560', 408, 32.55);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (10, 'What We Owe the Future','William Macaskill','9781541618626', 352, 29.76);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (11, 'Book Lovers','Emily Henry','9780593334836', 400, 25.11);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (12, 'I''m Still Here: Black Dignity in a World Made for Whiteness','Austin Channing Brow','9781524760854', 192, 23.25);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (13, 'The Seven Husbands of Evelyn Hugo','Taylor Jenkins Reid','9781501161933', 400, 27.9);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (14, 'Essential Labor: Mothering as Social Change','Angela Garbes','9780062937360', 256, 24.17);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (15, 'Tanqueray','Stephanie Johnson','9781250278272', 192, 24.99);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (16, 'Violeta','Isabel Allende','9780593496206', 336, 26.04);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (17, 'Crying in the Bathroom: A Memoir','Erika L. Sánchez','9780593296936', 256, 25.11);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (18, 'Velvet Was the Night','Silvia Moreno-Garcia','9780593356821', 304, 26.04);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (19, 'Part of Your World','Abby Jimenez','9781538704370', 400, 24.18);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (20, 'Trust','Hernan Diaz','9780593420317', 416, 26.04);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (21, 'Secret Identity','Alex Segura','9781250801746', 368, 26.96);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (22, 'Thrill of the Hunt','Rita Mae Brown','9780593357606', 304, 26.04);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (23, 'The Self-Made Widow','Fabian Nicieza','9780593191293', 400, 25.11);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (24, 'Local Gone Missing','Fiona Barton','9781984803047', 384, 25.11);

INSERT INTO books (book_id, title, author, isbn, pages, price)
VALUES (25, 'The Drowning Sea: A Maggie d''Arcy Mystery','Sarah Stewart Taylor','9781250826657', 352, 26.03);
