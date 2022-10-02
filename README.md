URL Shortener
=============
Service which is used to shorten URLs. Built with Spring Boot, running with inmemory H2 DB.

Author
------
Lukas Lamper


Build
=====

    mvn package


Start application
=================

    java -jar target/url-shortener-0.0.1-SNAPSHOT.jar 

By default application starts on url: *http://localhost:8080*


Usage
=====

Create account
--------------

    curl -d '{"AccountId":"myAccountId"}' -H "Content-Type: application/json" -X POST http://localhost:8080/account

Register URL
------------

    curl -d '{"URL":"http://fullurl.com/", "redirectType":301}' -H "Content-Type: application/json" -X POST http://localhost:8080/register -u myAccountId:<password>

Use short URL
-------------
Open returned short URL in browser or use in curl:

    curl http://localhost:8080/e9bf3e -v

Statistics
----------
Retrieve used count of short URLs:

    curl http://localhost:8080/statistic/myAccountId -u myAccountId:<password>

**!!!Not working yet**


Disclaimer
===========
Since the assignment is quite big, I've mostly focused on the architecture of the application, proper separation of concerns, following REST spec and demonstrating my Spring framework knowledge.
I could spend much more time on the assignment, but for the sake of time, I've decided to omit some requirements, as it would not give you much better overview of my skills.

Here is list of ommited requirements: 

- application is missing unit tests, Integration tests are covering base use cases
- dummy `/statistics` endpoint. There would be no new concept you cannot evaluate from other parts of the application. Statistics are collected, they are just not retrieved.
- there is no `/help`. This document contains short usage examples
- account password is not hashed in DB
- no duplication validation of short URL
- some validations are missing
- error responses could be improved
- hardcoded hostname of short URL
