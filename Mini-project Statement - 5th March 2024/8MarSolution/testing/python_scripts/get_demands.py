import requests
import json

print("You will be prompted to provide details for querying \nall the demands:")

level = input("Enter required level (Ex. p01, p02, etc.) >>> ")

location = input("Enter required location (Ex. Pune, Hyderabad, etc.) >>> ")

status = input("Enter status of demand (OPEN, CLOSED, NOT_FULFILLED) >>>")

manager = input("Enter manager name >>> ")

project = input("Enter project name >>> ")

skills = list()
n = input("Enter number of skills required >>> ")
if n.isnumeric():
    for i in range(1, n+1):
        skills.append( input("Enter skill-" + str(i) + ">>>") )



reqObj = dict()

if len(level): reqObj["requiredLevel"] = level
if len(status): reqObj["requiredDemandStatus"] = status
if len(location): reqObj["requiredCity"] = location
if len(manager): reqObj["requiredMgrName"] = manager
if len(project): reqObj["requiredProjectName"] = project
if len(skills): reqObj["requiredSkills"] = skills

# tempObj = {
#     "requiredLevel": "p04",
#     "requiredDemandStatus": "OPEN",
#     "requiredCity": "Bangalore",
#     "requiredMgrName": "Subu",
#     "requiredProjectName": "Project-3",
#     "requiredSkills": ["Python"]
# }

url = 'http://localhost:9090/api/getDemands'

response = requests.post(url, json = reqObj)


# Convert the response content to a Python dictionary
resp_dict = response.json()

# Pretty print the dictionary with an indentation of 4 spaces
pretty_json = json.dumps(resp_dict, indent=4)
print(pretty_json)