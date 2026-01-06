BEGIN;
DELETE FROM orders;
ALTER SEQUENCE orders_id_seq RESTART WITH 1;
DELETE FROM artworks;
ALTER SEQUENCE artworks_id_seq RESTART WITH 1;
DELETE FROM shops;
ALTER SEQUENCE shops_id_seq RESTART WITH 1;
DELETE FROM users;
ALTER SEQUENCE users_id_seq RESTART WITH 1;

INSERT INTO users (email, password, role, username) VALUES
    ('margo@example.com', '$2a$10$AgP3bI7MoBd7YdVqHpo57.z86q20actuE/JLn8otaip9OzlEjW9ou', 'ARTIST', 'margo'),
    ('foxesgifts@example.com', '$2a$10$AgP3bI7MoBd7YdVqHpo57.z86q20actuE/JLn8otaip9OzlEjW9ou', 'ARTIST', 'foxesgifts'),
    ('customer@example.com', '$2a$10$e3PTYjm4ohKkOjGM2pAcMOQTKwRYzrP/hgHhNPKup0WmTty7dQwJa', 'CUSTOMER', 'customer');
INSERT INTO shops (ai_tags, description, name, slug, owner_id) VALUES
    (false, 'This is my online shop where I''m planning to sell my artworks! I do digital and physical art :)', 'margo''s shop', 'margos-shop', 1),
    (false, 'I draw emotions with watercolors', 'foxesgifts', 'foxesgifts', 2);
INSERT INTO artworks (description, image_url, price, status, tags, title, type, shop_id) VALUES
    ('Watercolor artwork of two friends', '/uploads/photo_2026-01-04_20-15-01.jpg', 50.00, 'AVAILABLE', '{watercolor,friends,traditional}', 'Friends', 'PHYSICAL', 2),
    ('Artwork of a girl holding a bunny', '/uploads/photo_2026-01-04_20-26-54.jpg', 50.00, 'AVAILABLE', '{watercolor,girl,cute,bunny}', 'Bunny', 'PHYSICAL', 2),
    ('Artwork of still life', '/uploads/photo_2026-01-04_20-18-05.jpg', 45.00, 'AVAILABLE', '{watercolor,food,traditional}', 'Still Life', 'PHYSICAL', 2),
    ('Easter cake with a bunny decoration', '/uploads/photo_2026-01-04_20-16-36.jpg', 45.00, 'AVAILABLE', '{watercolor,food,bunny,holiday}', 'Easter', 'PHYSICAL', 2),
    ('Watercolor artwork depicting a girl and a bunny', 'uploads/photo_2026-01-04_20-26-54.jpg', 50.00, 'SOLD', '{watercolor,bunny,girl,friends}', 'Girl And Bunny', 'PHYSICAL', 2),
    ('Digital artwork of a bunny and a fennec fox decorating a Christmas Tree', '/uploads/christmas.png', 20.00, 'AVAILABLE', '{digital,cute,bunny,fox,holiday}', 'Christmas Preparations', 'DIGITAL', 1);
INSERT INTO orders (address, city, country, created_at, full_name, phone, price, status, zip_code, artwork_id, buyer_id, shop_id) VALUES
    ('Sample Street 53', 'Krakow', 'Poland', '2026-01-05 15:32:18.683259', 'Customer Name', '+48555555555', 50.00, 'COMPLETED', '40-234', 5, 3, 2);
COMMIT;