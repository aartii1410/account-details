insert into account (account_number, account_name, account_type, balance_date, currency, opening_balance)
values('585309209','SGSavings726', 'SAVINGS', CURRENT_TIMESTAMP, 'AUD', 84327);
insert into account (account_number, account_name, account_type, balance_date, currency, opening_balance)
values('791066619','AUSavings933', 'SAVINGS', CURRENT_TIMESTAMP, 'SGD', 88005);
insert into account (account_number, account_name, account_type, balance_date, currency, opening_balance)
values('791066620','AUSavings934', 'SAVINGS', CURRENT_TIMESTAMP, 'INR', 880005);
insert into account_transaction (id, account_number, account_name, credit_amount, currency, debit_amount, is_credit_debit, transaction_remarks, value_date)
values('1001', '585309209','SGSavings726', 100, 'AUD', 0 , 'Credit', 'Money Credited' ,CURRENT_TIMESTAMP);
insert into account_transaction (id, account_number, account_name, credit_amount, currency, debit_amount, is_credit_debit, transaction_remarks, value_date)
values('1002', '585309209','SGSavings726', 200, 'AUD', 0 , 'Credit', 'Money Credited1', CURRENT_TIMESTAMP);
insert into account_transaction (id, account_number, account_name, credit_amount, currency, debit_amount, is_credit_debit, transaction_remarks, value_date)
values('1003', '585309209','SGSavings726', 200, 'AUD', 0 , 'Credit', 'Money Credited2', CURRENT_TIMESTAMP);
insert into account_transaction (id, account_number, account_name, credit_amount, currency, debit_amount, is_credit_debit, transaction_remarks, value_date)
values('1004', '791066619','AUSavings933', 0, 'AUD', 10 , 'Debit', 'Grocery', CURRENT_TIMESTAMP);
insert into account_transaction (id, account_number, account_name, credit_amount, currency, debit_amount, is_credit_debit, transaction_remarks, value_date)
values('1005', '791066619','AUSavings933', 0, 'AUD', 20 , 'Debit', 'Grocery', CURRENT_TIMESTAMP);
insert into account_transaction (id, account_number, account_name, credit_amount, currency, debit_amount, is_credit_debit, transaction_remarks, value_date)
values('1006', '791066619','AUSavings933', 0, 'AUD', 30 , 'Debit', 'Shopping', CURRENT_TIMESTAMP);
insert into account_transaction (id, account_number, account_name, credit_amount, currency, debit_amount, is_credit_debit, transaction_remarks, value_date)
values('1007', '791066620','AUSavings933', 100, 'AUD', 0 , 'Credit', 'Amount Credited', CURRENT_TIMESTAMP);
insert into account_transaction (id, account_number, account_name, credit_amount, currency, debit_amount, is_credit_debit, transaction_remarks, value_date)
values('1008', '791066620','AUSavings933', 10000, 'AUD', 0 , 'Credit', 'Salary', CURRENT_TIMESTAMP);