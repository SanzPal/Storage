import random
import requests


_projectName = ['Project-1', 'Project-2', 'Project-3', 'Project-4', 'Project-5']
_mgrName = ['Ravi', 'Subu', 'Vishu', "Dobu", 'Kaju']
_level = ['p01', 'p02', 'p03', 'p04', 'p05']
_city = ["Pune", "Bangalore", "Chennai", "Hyderabad"]
_duration = [1,2,3,4,5]
_startDate = "2023-01-25"
_skills = ["Java", "Maven", "Spring", "Python", "CPP", "MongoDB", "Skill-X", "Skill-Y", "Skill-Z"]

def generateRandomDemand():
    no_of_skills = random.randrange(0, 5)
    projectName = random.choice(_projectName)
    mgrName = random.choice(_mgrName)
    level = random.choice(_level)
    city = random.choice(_city)
    duration = random.randrange(1,5)
    startDate = "2023-01-25"
    skills = random.sample(_skills, no_of_skills)

    # print('{\n' + f' "projectName": "{projectName}",\n "mgrName": "{mgrName}",\n "level": "{level}",\n "city": "{city}",\n "duration": {duration},\n "startDate": "2023-01-25",\n "skills": {skills}\n' + '}')

    
    return {"projectName": projectName, "mgrName": mgrName, "level": level, "city": city, "duration": duration, "startDate": startDate, "skills": skills}
for _ in range(20):
    # print(generateRandomDemand())


    url = 'http://localhost:9090/api/addDemand'
    myobj = generateRandomDemand()

    x = requests.post(url, json = myobj)

    print(x.text)