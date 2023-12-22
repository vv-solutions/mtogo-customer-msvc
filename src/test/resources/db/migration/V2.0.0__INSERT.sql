-- Inserting rows into the 'customer' table
INSERT INTO customer (first_name, last_name, create_stamp, active, email) VALUES
('John', 'Doe', NOW(), true, 'john.doe@example.com'),
('Jane', 'Smith', NOW(), true, 'jane.smith@example.com'),
('Bob', 'Johnson', NOW(), false, 'bob.johnson@example.com'),
('Alice', 'Williams', NOW(), true, 'alice.williams@example.com'),
('Charlie', 'Brown', NOW(), true, 'charlie.brown@example.com');

-- Inserting rows into the 'address' table
INSERT INTO address (zip_code, address, city, create_stamp, customer_id) VALUES
(2800, 'Ulrikkenborg Alle 33', 'Kgs. Lyngby',NOW(), 1),
(56789, '456 Oak Ave', 'Sometown',NOW(), 2),
(98765, '789 Pine Ln', 'Othercity',NOW(), 3),
(54321, '321 Elm St', 'Anothercity',NOW(), 1),
(87654, '654 Maple Rd', 'Newtown',NOW(), 2),
(23456, '456 Birch Blvd', 'Someplace',NOW(), 4),
(78901, '901 Cedar Ct', 'Anotherplace',NOW(), 5);