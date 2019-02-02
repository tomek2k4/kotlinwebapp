# kotlin-restful-webapp
Example implementation of money transfers

## Flow
![transfer flow](https://raw.githubusercontent.com/tomek2k4/kotlin-restful-webapp/master/img/transfer_flow.png)

### TransferRequest
```
{  
   "ammount":"400.00",
   "account_to":"1234-5678"
}
```

### TransferResponse
```
{  
   "id":"5444341",
   "clientapproval":"accept|deny",
   "transfer":{  
      "ammount":"400.00",
      "account_to":"1234-5678",
      "valid_time_ms":"5000"
   }
}
```

### TransferSign
```
{
   "clientapproval":"accept",
   "secret":"BicmFrxYJvIGltLArFvGUgc8SFIGphayBva"  //signature of transfer json string, for simplicity of this example base64
}
```

### TransferSignResponse
```
{  
   "response":"success|failure",
   "clientapproval":"accept",
   "msg":"money transfered"
}
```
