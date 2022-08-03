insert into user (user_id, password, first_name, last_name, email, company, phone, address1, address2, country, postal, role, is_active, is_blocked, security_provider_id, default_customer_id, secret_question, secret_answer, enable_beta_testing, enable_renewal) values
('avinash', 'avinash', 'AVINASH REDDY', 'VAKA', 'avinash@ul.ie', 'University of Limerick', '+353123456789', 'Limerick', '4967', 'Ireland', '64890', 'USER' , 1, 0, 10001, 20000, 'Diverse', 'Yellow', 0, 0),
('admin', 'admin', 'admin'  , 'admin' , 'admin@metromeds.com', 'University of Limerick', '+353123456789', 'address', '99152' , 'Ireland', '51065', 'ADMIN', 1, 0, 10001, 20000, 'knowledge base', 'Mauv', 1, 0);

insert into employees (id, last_name, first_name, email, phone, address1, address2, city, state, postal_code, country, avatar, job_title, department, manager_id) values
(1, 'Employee', 'Name' , 'employee@metromeds.com', '+353123456789', 'Limerick', null, 'Limerick', 'Limerick', '78240', 'Ireland', 'avatar1', 'Administrative Assistant I', 'Medicines', null);

insert into products (id, product_code, product_name, description, standard_cost, list_price, target_level, reorder_level, minimum_reorder_quantity, quantity_per_unit, discontinued, category) values
(1, 'P1', 'Paracetamol 500mg', null, 5, 5, 80 , 14, 10, 11, 1, 'Medicine'),
(2, 'P2', 'Penicillin 250mg', null, 10, 10, 80 , 14, 10, 11, 1, 'Medicine'),
(3, 'P3', 'Face Mask', null, 15, 5, 80 , 14, 10, 11, 1, 'General'),
(4, 'P4', 'Hand Sanitizer', null, 5, 5, 80 , 14, 10, 11, 1, 'General');

insert into customers (id, last_name, first_name, customer_tier, wallet_balance, email, company, phone, address1, address2, city, state, postal_code, country) values
(1, 'VAKA', 'AVINASH REDDY' , 'silver', '100.20', 'avinash@ul.ie', 'Metromeds' , '+353123456789', 'Limerick' , null, 'Limerick', 'Limerick', '46805', 'Ireland'),
(2, 'JOHRI', 'KHYATI' , 'silver', '200.3', 'Khyati@ul.ie', 'Metromeds' , '+353123456789', 'Limerick' , null, 'Limerick', 'Limerick', '46805', 'Ireland'),
(3, 'MOVSESSIAN', 'ALEXANDRE', 'silver', '300.55', 'alex@ul.ie', 'Metromeds' , '+353123456789', 'Limerick' , null, 'Limerick', 'Limerick', '46805', 'Ireland'),
(4, 'JOY', 'AJIT F' , 'silver', '50.33','Ajit@ul.ie', 'Metromeds' , '+353123456789', 'Limerick' , null, 'Limerick', 'Limerick', '46805', 'Ireland');

insert into orders (id, employee_id, customer_id, order_date, shipped_date, ship_name, ship_address1, ship_address2, ship_city, ship_state, ship_postal_code, ship_country, shipping_fee, payment_type, paid_date, order_status, promo_code, cart_price, delivery_charge, amount_payable, service_type, order_type, is_prescribed,discount_amount) values
(1, 1, 1, '2020-11-05', '2020-12-06', 'Metromeds', 'Limerick', null, 'Limerick', null, null, 'Ireland', 8.14, 'Card', '2020-10-12', 'Delivered', 'WEEKEND10', '20.22', '30.22','33.33','Express' ,'Pickup', 1,10.0),
(2, 1, 1, '2020-11-05', '2020-12-16', 'Metromeds', 'Limerick', null, 'Limerick', null, null, 'Ireland', 8.14, 'Card', '2020-10-12', 'Processing', 'SUMMER15', '40.33', '50.33','44.33','Express','Delivery', 1,15.0);

insert into order_items (order_id, product_id, quantity, unit_price, discount, order_item_status, date_allocated) values
(1, 1, 1, 97.34, 8.73, 'Allocated', '2020-11-15');

insert into payments (id, bill_id, payment_type, payment_date, payment_status,payment_ref) values
(1, 1, 'wallet', null, 'SUCCESS',null);

insert into bill(id ,order_id ,total_amount ,customer_id,bill_generation_date) values
(1,1,100,1,'2020-12-06');
