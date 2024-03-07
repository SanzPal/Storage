import random
import requests


_firstName = ['golu', 'motu', 'chiku', 'chhotu', 'piku']
_lastName = ['saz', 'naz', 'mak', 'jak', 'will']
_level = ['p01', 'p02', 'p03', 'p04', 'p05']
_location = ["Pune", "Bangalore", "Chennai", "Hyderabad"]
_doj = [1,2,3,4,5]
_startDate = "2023-01-25"
_skills = ["Java", "Maven", "Spring", "Python", "CPP", "MongoDB", "Skill-X", "Skill-Y", "Skill-Z"]
_setOfEids = set()

def generateRandomMember():
    no_of_skills = random.randrange(0, 5)
    firstName = random.choice(_firstName)
    lastName = random.choice(_lastName)
    level = random.choice(_level)
    location = random.choice(_location)
    doj = "2023-01-25"
    skillsList = random.sample(_skills, no_of_skills)

    while True:
        eid = random.randint(70000, 77500)
        if (eid not in _setOfEids):
            _setOfEids.add(eid)
            break
    
    overallExp = random.randint(1, 15)
    skillsMap = {skill:random.randint(1, 15) for skill in skillsList}
    # print('{\n' + f' "projectName": "{projectName}",\n "mgrName": "{mgrName}",\n "level": "{level}",\n "city": "{city}",\n "duration": {duration},\n "startDate": "2023-01-25",\n "skills": {skills}\n' + '}')

    
    return {"firstName": firstName, "lastName": lastName, "level": level, "location": location, "overallExp": overallExp, "doj": doj, "skills": skillsMap, "eid": eid}
for _ in range(30):
    # print(generateRandomDemand())


    url = 'http://localhost:9090/api/addMember'
    myobj = generateRandomMember()

    x = requests.post(url, json = myobj)

    print(x.text)
    input()