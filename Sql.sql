CREATE DATABASE grocerydb;
use grocerydb;
show tables;
SELECT * FROM grocerydb.users;
INSERT INTO users (name, email, password, mobile_number, role, created_at) 
VALUES (
    'Admin',
    'admin@freshmart.com',
    '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM5lZkByS3bJBmeFUOxm',
    '9000000000',
    'ADMIN',
    NOW()
);
select * from products;
select * from categories;
DELETE FROM categories;
DELETE FROM categories WHERE id > 0;