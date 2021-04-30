insert into users (user_id, username, password, first_name, last_name, admin, staff, visitor) values (1, 'admin', '$2a$10$JQOfG5Tqnf97SbGcKsalz.XpDQbXi1APOf2SHPVW27bWNioi9nI8y', 'Super', 'Admin', 'yes', 'no', 'no');
insert into users (user_id, username, password, first_name, last_name, admin, staff, visitor) values (2, 'worker', '$2y$10$lViGykUJyTBg0yW.x7hT1.OcTtEvxsSgsu2QOX8gYpZKpFZDFyYsO', 'Regular', 'Worker', 'no', 'yes', 'no');
insert into users (user_id, username, password, first_name, last_name, admin, staff, visitor) values (3, 'user', '$2y$10$XeL4DkX/Ufn86DqFGdDeV.i7E5lus.ehKoRs9dEAOr57P46WXV9AK', 'Some', 'User', 'no', 'no', 'yes');

insert into poll (poll_id, question) values (1, 'What is your favorite color?');
insert into option (option_id, option_value, poll_id) values (1, 'Red', 1);
insert into option (option_id, option_value, poll_id) values (2, 'Black', 1);
insert into option (option_id, option_value, poll_id) values (3, 'Blue', 1);
insert into option (option_id, option_value, poll_id) values (4, 'White', 1);

insert into poll (poll_id, question) values (2, 'What is your favorite Credit Card?');
insert into option (option_id, option_value, poll_id) values (5, 'American Express', 2);
insert into option (option_id, option_value, poll_id) values (6, 'Visa', 2);
insert into option (option_id, option_value, poll_id) values (7, 'Master Card', 2);
insert into option (option_id, option_value, poll_id) values (8, 'Discover', 2);

insert into poll (poll_id, question) values (3, 'What is your favorite Sport?');
insert into option (option_id, option_value, poll_id) values (9, 'Football', 3);
insert into option (option_id, option_value, poll_id) values (10, 'Basketball', 3);
insert into option (option_id, option_value, poll_id) values (11, 'Cricket', 3);
insert into option (option_id, option_value, poll_id) values (12, 'Baseball', 3);

insert into poll (poll_id, question) values (4, 'How long have you used spring framework?');
insert into option (option_id, option_value, poll_id) values (13, 'One Year', 4);
insert into option (option_id, option_value, poll_id) values (14, 'Two Years', 4);
insert into option (option_id, option_value, poll_id) values (15, 'Three Years', 4);
insert into option (option_id, option_value, poll_id) values (16, 'Four Years', 4);

insert into poll (poll_id, question) values (5, 'How do you rate overall satisfaction with Apress Books?');
insert into option (option_id, option_value, poll_id) values (17, 'Very Satisfied', 5);
insert into option (option_id, option_value, poll_id) values (18, 'Somewhat Satisfied', 5);
insert into option (option_id, option_value, poll_id) values (19, 'Neutral', 5);
insert into option (option_id, option_value, poll_id) values (20, 'Somewhat Dissatisfied', 5);
insert into option (option_id, option_value, poll_id) values (21, 'Dissatisfied', 5);