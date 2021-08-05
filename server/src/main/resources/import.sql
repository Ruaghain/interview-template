INSERT INTO user (user_id, user_user_name, user_first_name, user_last_name, user_email, user_password, user_created_date, user_updated_date) VALUES (1, 'one.person', 'One', 'Person', 'one.person@workhuman.com', 'P@ssw0rd', CURRENT_DATE, CURRENT_DATE);
INSERT INTO user (user_id, user_user_name, user_first_name, user_last_name, user_email, user_password, user_created_date, user_updated_date) VALUES (2, 'one.person', 'Two', 'Person', 'two.person@workhuman.com', 'P@ssw0rd', CURRENT_DATE, CURRENT_DATE);

INSERT INTO account (account_number, account_name, account_balance, account_created_date, account_updated_date, account_user_id) VALUES ('123456', 'Current', 123.45, CURRENT_DATE, CURRENT_DATE, 1);
INSERT INTO account (account_number, account_name, account_balance, account_created_date, account_updated_date, account_user_id) VALUES ('654321', 'Savings', 1234.56, CURRENT_DATE, CURRENT_DATE, 1);
