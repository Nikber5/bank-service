# bank-service

I realized a little bank service app.

Here you can add client by post method "http://localhost:8080/clients" with body:

{
"first_name": "Name",
"last_name": "Surname",
"accounts" : [
{
"account_num": "123456789",
"account_type": "card/simple",
"balance": 5000.00
},
{
"account_num": "987654321",
"account_type": "card/simple",
"balance": 10000.00
}
]
}

You will receive id of a new client. 

There is an option to get account by its id with get method "http://localhost:8080/accounts/history?accountId=1". And you will receive information about accounts in JSON format.

You can also create payment by post method "http://localhost:8080/payments" with body, its id also will be back:

{
"source_acc_id": 1,
"dest_acc_id": 2,
"amount": 10.00,
"reason": "for school"
}

You can create few payments, as well, by post method "http://localhost:8080/payments/all" with body:

[
{
"source_acc_id": 1,
"dest_acc_id": 2,
"amount": 50.00,
"reason": "payment reason"
},
{
"source_acc_id": 2,
"dest_acc_id": 1,
"amount": 20.00,
"reason": "payment reason"
}
]

You will receive an object with payments id and status, after this method. 

I realized an option of getting history of payments by source account id, or destination account id, or both, or neither of them. You can do it with post method by url "http://localhost:8080/payments/params" with body like:

{"source_acc_id":1,
"dest_acc_id":2}

Another variant of getting payment history by account is by get method with url "http://localhost:8080/accounts/history?accountId=1"

There is a validation of essential fields @NotNull and minimal value of amount is 0.

Exceptions with http code 400 are handled by custom GlobalExceptionHandler, so user will receive object with exception code and its message.

App may receive requests in JSON, or XML formats, and replies in JSON format.

The logic of payments realized in such a way that valid payment will transfer money from one account to another (if they are not the same account). Payment will be saved to DB, after transferring. If there is no enough money on the account there will be no transaction, but payment will be saved with "error" status. 
