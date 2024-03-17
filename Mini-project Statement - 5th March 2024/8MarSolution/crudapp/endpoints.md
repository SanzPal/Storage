## Members

1. GET
    * `/getAllMembers`

1. GET
    * `/getMemberById/{id}`
    * Path Variable: `id`

1. GET
    * `/getMembersByDemandId/{id}`
    * Path Variable: `id`
    * To be sorted by `name`,
`doj`,
`location` and number of matching `skills`
    * can use `overallExp` as well for sorting

1. POST
    * `/addMember`
    * Given below is example of JSON in post request body  
      ```json
        {
            "firstName": "chhotu",
            "lastName": "jak",
            "level": "p01",
            "location": "Hyderabad",
            "overallExp": 8,
            "doj": "2023-01-25",
            "eid": 71450,
            "skills": {
                "MongoDB": 9,
                "Python": 12,
                "Skill-Z": 12
            }
        }
        ```
    * All the above properties are required,
number of skills can be zero.

1. DELETE
    * `/deleteMemberById/{id}`
    * Path Variable: `id`
    * Requires dealing with "Unable to delete because of foreign key" error

1. DELETE
    * `/deleteAllMembers`
    * Simply empty the whole table


## Demands

1. GET
    * `/getAllDemands`

1. GET
    * `/getDemandById/{id}`
    * Path Variable: `id`

1. POST
    * `/getDemands`
    * Has a request body of following format
    ```json
        {
            "requiredLevel": "p04",
            "requiredDemandStatus": "OPEN",
            "requiredCity": "Bangalore",
            "requiredMgrName": "Subu",
            "requiredProjectName": "Project-3",
            "requiredSkills": ["Python"]
        }
    ```
    * All the above properties are optional and can be omitted.
    * Drawback of post request is that it isn"t cached
    * `HTTP QUERY` request can be more suitable for this purpose
    * A native dynamic query can be constructed and used to avoid fetching all records in db followed by filtering

1. POST
    * `/addDemand`
    * Given below is example of JSON in post request body  
      ```json
        {
            "projectName": "Project-1",
            "mgrName": "Kaju",
            "level": "p05",
            "city": "Chennai",
            "duration": 2,
            "startDate": "2023-01-25",
            "skills": ["CPP", "Spring"]
        }
        ```
    * All the above properties are required, number of skills can be zero.

1. DELETE
    * `/deleteDemandById/{id}`
    * Path Variable: `id`
    * Requires dealing with "Unable to delete because of foreign key" error

1. DELETE
    * `/deleteAllDemands`
    * Simply empty the whole table