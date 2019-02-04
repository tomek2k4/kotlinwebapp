# kotlinwebapp
Example implementation of money transfers

## Flow
![transfer flow](https://raw.githubusercontent.com/tomek2k4/kotlinwebapp/master/img/transfer_flow.png)

### TransferRequest
```
{  
   "amount":"400.00",
   "account_to":"2"			// set recipient as user id
}
```

### TransferResponse
```
{  
   "response":"Success|Failure",
   "msg":"money transfered"
}
```

## Usage example
HAR structure that makes a transfer from user with id 2 to user with id 1 with amount 50.0

{
  "log": {
    "version": "1.2",
    "creator": "RESTED REST Client",
    "Comment": "An exported collection from RESTED",
    "entries": [
      {
        "request": {
          "method": "POST",
          "url": "http://localhost:4567/2/transfer",
          "headers": [
            {
              "name": "Content-Type",
              "value": "application/json"
            }
          ],
          "postData": {
            "mimeType": "application/x-www-form-urlencoded",
            "params": [
              {
                "name": "amount",
                "value": "50"
              },
              {
                "name": "account_to",
                "value": "1"
              }
            ],
            "text": "amount=50&account_to=1"
          }
        }
      }
    ]
  }
}
