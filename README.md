## Basic banking application

---

# Api Documentation

## Table of Contents


- [Installation](#installation)

- [Dependencies](#dependencies)

- [API](#api)



## Installation

1. Clone the repo
   ```sh
   git clone https://github.com/PB-Digital/ms-bundle.git
   ```
   
2. Open project in your favorite IDEA and run. If you wish you can build and run project via terminal

   3.1. Build project
    ```sh
    gradle build
    ```
   3.2. Run project. Project will run with this url: https://localhost:8443




# Dependencies

I'm using ***PostgreSQL***


## API

# ms-banking

<details>
<summary>ENDPOINTS</summary>

- #### Get:

<details>
    <summary>/v1/bank/customer - HTTP GET:</summary>
Endpoint: /v1/bank/customer

## Parameters
```yaml
{
    "name": "Mushvig",
    "surname": "MM",
    "gsmNumber": "2002",
    "balance": 100.0,
    "birthDate": "1996-12-01"
}
```

Returned data example(Status Code: 201):

```yaml
{
  "id": 1,
  "name": "Mushvig",
  "surname": "MM",
  "gsmNumber": "2002",
  "balance": 100.0,
  "birthDate": "1996-12-01"
  "createdAt": "2023-09-22T14:07:34.933652",
  "lastUpdatedAt": "2023-09-22T14:07:34.934938"
}
```
Note: I'am creating a user via liquibase
</details>

- #### CUSTOMER LIST :

<details>
    <summary>/v1/bank/customer - HTTP GET:</summary>

Endpoint:  /v1/bank/customer

## Parameters


Returned data example(Status Code: 200):

```yaml
[
   {
      "id": 1,
      "name": "Mushvig",
      "surname": "Manafli",
      "gsmNumber": "20002",
      "balance": 51.33,
      "birthDate": "1996-01-12",
      "createdAt": "2023-09-22T14:58:53.778013",
      "lastUpdatedAt": "2023-09-22T15:21:41.179571"
   }
]
```

</details>

- #### TRANSACTION LIST BY customerIds:

<details>
    <summary>/v1/bank/transaction/{customerId} - HTTP GET:</summary>

Endpoint:  /v1/bank/transaction/{customerId}

## Parameters


Returned data example(Status Code: 200):

```yaml
[
   {
      "id": 3,
      "customerId": 1,
      "transactionType": "PURCHASE",
      "amount": 1.0,
      "createdAt": "2023-09-22T15:12:00.586052"
   },
   {
      "id": 4,
      "customerId": 1,
      "transactionType": "TOP_UP",
      "amount": 1.0,
      "createdAt": "2023-09-22T15:12:07.983494"
   },
   {
      "id": 5,
      "customerId": 1,
      "transactionType": "REFUND",
      "amount": 0.33,
      "createdAt": "2023-09-22T15:14:48.333284"
   }
]
```

</details>

- #### TOP-UP money:

<details>
    <summary>/v1/bank/payment/top-up/{customerId} - HTTP POST:</summary>

Endpoint:  /v1/bank/payment/top-up/{customerId}

## Parameters
```sh
    double amount
```


Returned data example(Status Code: 202):

```yaml
{
  "transactionId": 35,
  "customerId": 1,
  "amount": 31.0,
  "balance": 172.0,
  "date": "2023-09-22T14:31:21.520105"
}
```

</details>

- #### PURCHASE money :

<details>
    <summary>/v1/bank/payment/purchase/{customerId} - HTTP POST:</summary>

Endpoint:  /v1/bank/payment/purchase/{customerId}

## Parameters
```sh
    double amount
```


Returned data example(Status Code: 202):

```yaml
{
  "transactionId": 36,
  "customerId": 1,
  "amount": 31.0,
  "balance": 141.0,
  "date": "2023-09-22T14:33:45.409824"
}
```

</details>

- #### REFUND money :

<details>
    <summary>/v1/bank/payment/refund/{transactionId} - HTTP POST:</summary>

Endpoint:  /v1/bank/payment/refund/{transactionId}

## Parameters
```sh
    double amount
```


Returned data example(Status Code: 202):

```yaml
{
  "transactionId": 36,
  "customerId": 1,
  "amount": 31.0,
  "balance": 141.0,
  "date": "2023-09-22T14:33:45.409824"
}
```

</details>
</details>

