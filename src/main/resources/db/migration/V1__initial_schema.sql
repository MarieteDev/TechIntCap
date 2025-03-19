-- Creación de la tabla "categories"
CREATE TABLE categories (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(100) NOT NULL UNIQUE
);

-- Creación de la tabla "products"
CREATE TABLE products (
                          id SERIAL PRIMARY KEY,
                          sku VARCHAR(20) NOT NULL UNIQUE,
                          price DECIMAL(10,2) NOT NULL,
                          description TEXT NOT NULL,
                          category_id INT NOT NULL,
                          FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);

-- Insertar datos en la tabla "categories"
INSERT INTO categories (name) VALUES
                                  ('Electronics'), ('Home & Kitchen'), ('Clothing'), ('Accessories'), ('Sports'),
                                  ('Musical Instr.'), ('Footwear'), ('Home Appliances'), ('Toys & Games'), ('Stationery');

-- Insertar datos en la tabla "products"
INSERT INTO products (sku, price, description, category_id) VALUES
                                                                ('SKU0001', 19.99, 'Wireless Mouse with ergonomic design', (SELECT id FROM categories WHERE name = 'Electronics')),
                                                                ('SKU0002', 499.00, '4K Ultra HD Smart TV, 55 inches', (SELECT id FROM categories WHERE name = 'Electronics')),
                                                                ('SKU0003', 29.50, 'Stainless Steel Water Bottle, 1L', (SELECT id FROM categories WHERE name = 'Home & Kitchen')),
                                                                ('SKU0004', 15.00, 'Cotton T-Shirt, Unisex, Size M', (SELECT id FROM categories WHERE name = 'Clothing')),
                                                                ('SKU0005', 120.00, 'Noise-Cancelling Over-Ear Headphones', (SELECT id FROM categories WHERE name = 'Electronics')),
                                                                ('SKU0006', 9.99, 'USB-C to USB Adapter', (SELECT id FROM categories WHERE name = 'Electronics')),
                                                                ('SKU0007', 75.00, 'Leather Wallet with RFID Protection', (SELECT id FROM categories WHERE name = 'Accessories')),
                                                                ('SKU0008', 35.00, 'Yoga Mat with Non-Slip Surface', (SELECT id FROM categories WHERE name = 'Sports')),
                                                                ('SKU0009', 220.00, 'Smartwatch with Heart Rate Monitor', (SELECT id FROM categories WHERE name = 'Electronics')),
                                                                ('SKU0010', 12.50, 'Ceramic Coffee Mug, 350ml', (SELECT id FROM categories WHERE name = 'Home & Kitchen')),
                                                                ('SKU0011', 60.00, 'Bluetooth Portable Speaker', (SELECT id FROM categories WHERE name = 'Electronics')),
                                                                ('SKU0012', 85.00, 'Backpack with Laptop Compartment', (SELECT id FROM categories WHERE name = 'Accessories')),
                                                                ('SKU0013', 18.00, 'Stainless Steel Cutlery Set, 24 Pieces', (SELECT id FROM categories WHERE name = 'Home & Kitchen')),
                                                                ('SKU0014', 250.00, 'Electric Guitar Starter Pack', (SELECT id FROM categories WHERE name = 'Musical Instr.')),
                                                                ('SKU0015', 42.00, 'Running Shoes, Men''s Size 42', (SELECT id FROM categories WHERE name = 'Footwear')),
                                                                ('SKU0016', 27.99, 'Digital Bathroom Scale with Body Fat Analyzer', (SELECT id FROM categories WHERE name = 'Home Appliances')),
                                                                ('SKU0017', 14.99, 'Set of 6 Organic Cotton Socks', (SELECT id FROM categories WHERE name = 'Clothing')),
                                                                ('SKU0018', 300.00, 'DSLR Camera with 18-55mm Lens', (SELECT id FROM categories WHERE name = 'Electronics')),
                                                                ('SKU0019', 8.99, 'Hardcover Notebook, A5, 200 Pages', (SELECT id FROM categories WHERE name = 'Stationery')),
                                                                ('SKU0020', 65.00, 'Microwave Oven, 20L Capacity', (SELECT id FROM categories WHERE name = 'Home Appliances')),
                                                                ('SKU0021', 23.50, 'LED Desk Lamp with Adjustable Brightness', (SELECT id FROM categories WHERE name = 'Home & Kitchen')),
                                                                ('SKU0022', 19.00, 'Wireless Charger Pad for Smartphones', (SELECT id FROM categories WHERE name = 'Electronics')),
                                                                ('SKU0023', 55.00, 'Men''s Quartz Analog Watch with Leather Strap', (SELECT id FROM categories WHERE name = 'Accessories')),
                                                                ('SKU0024', 30.00, 'Wooden Chess Set with Folding Board', (SELECT id FROM categories WHERE name = 'Toys & Games')),
                                                                ('SKU0025', 99.00, 'Home Security Camera with Night Vision', (SELECT id FROM categories WHERE name = 'Electronics')),
                                                                ('SKU0026', 16.50, 'Aromatherapy Essential Oil Diffuser', (SELECT id FROM categories WHERE name = 'Home & Kitchen')),
                                                                ('SKU0027', 40.00, 'Professional Blender with 2L Jar', (SELECT id FROM categories WHERE name = 'Home Appliances')),
                                                                ('SKU0028', 22.00, 'Kids'' Educational Tablet Toy', (SELECT id FROM categories WHERE name = 'Toys & Games')),
                                                                ('SKU0029', 110.00, 'Mechanical Gaming Keyboard with RGB Lighting', (SELECT id FROM categories WHERE name = 'Electronics')),
                                                                ('SKU0030', 7.50, 'Pack of 10 Ballpoint Pens, Blue Ink', (SELECT id FROM categories WHERE name = 'Stationery'));
