    -- afegim dades de prova
    INSERT INTO anime(name, description, type, year, image) VALUES
        ('Anime One', 'El primero', 'Terror', 2021, 'anime1.jpg'),
        ('Anime Two', 'El segundo', 'Drama', 2015, 'anime2.jpg');

INSERT INTO genre(label,image) VALUES
        ('Genre One', 'image1'),
        ('Genre Two','image2');

INSERT INTO author(name, image) VALUES
        ('Author1', 'author1.jpg'),
        ('Author2', 'author2.jpg');

INSERT INTO usser (username, password) VALUES
        ('user1', crypt('pass', gen_salt('bf'))),
        ('user2', crypt('pass2', gen_salt('bf')));

INSERT INTO anime_author VALUES
    ((SELECT animeid FROM anime WHERE name = 'Anime One'),(SELECT authorid FROM author WHERE name = 'Author1')),
    ((SELECT animeid FROM anime WHERE name = 'Anime Two'),(SELECT authorid FROM author WHERE name = 'Author2'));

INSERT INTO anime_genre VALUES
    ((SELECT animeid FROM anime WHERE name = 'Anime One'),(SELECT genreid FROM genre WHERE label = 'Genre One')),
    ((SELECT animeid FROM anime WHERE name = 'Anime Two'),(SELECT genreid FROM genre WHERE label = 'Genre Two'));

INSERT INTO favorite VALUES
 ((SELECT userid FROM usser WHERE username = 'user1'),(SELECT animeid FROM anime WHERE name = 'Anime One'));

INSERT INTO block_anime VALUES
 ((SELECT userid FROM usser WHERE username = 'user1'),(SELECT animeid FROM anime WHERE name = 'Anime Two')),
 ((SELECT userid FROM usser WHERE username = 'user1'),(SELECT animeid FROM anime WHERE name = 'Anime One'));

INSERT INTO follow_user VALUES
 ((SELECT userid FROM usser WHERE username = 'user1'),(SELECT userid FROM usser WHERE username = 'user2'));

INSERT INTO images (animeid,imageurl) VALUES
((SELECT animeid FROM anime WHERE name = 'Anime One'), 'anime1_image1.jpg'),
((SELECT animeid FROM anime WHERE name = 'Anime One'), 'anime1_image2.jpg'),
((SELECT animeid FROM anime WHERE name = 'Anime Two'), 'anime2_image1.jpg'),
((SELECT animeid FROM anime WHERE name = 'Anime Two'), 'anime2_image2.jpg');

