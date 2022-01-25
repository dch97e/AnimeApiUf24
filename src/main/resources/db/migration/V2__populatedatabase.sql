    -- afegim dades de prova
    INSERT INTO anime(name, description, type, year, image) VALUES
        ('Anime One', 'El primero', 'Terror', 2021, 'anime1.jpg'),
        ('Anime Two', 'El segundo', 'Drama', 2015, 'anime2.jpg');

INSERT INTO genre(label,image) VALUES
        ('Genre One', 'image1'),
        ('Genre Two','image2');


        -- afegim dades de prova
INSERT INTO author(name, image) VALUES
        ('Author1', 'author1.jpg'),
        ('Author2', 'author2.jpg');

INSERT INTO usser (username, password) VALUES
        ('user1', crypt('pass', gen_salt('bf')));

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