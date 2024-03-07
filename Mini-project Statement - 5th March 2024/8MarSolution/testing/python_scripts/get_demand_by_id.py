import requests
import json

url = 'http://localhost:9090/api/getDemandById/'

id = input("Insert Id >>> ")
response = requests.get(url + id)

if response.status_code in range(200,203):
    # Convert the response content to a Python dictionary
    resp_dict = response.json()

    # Pretty print the dictionary with an indentation of 4 spaces
    pretty_json = json.dumps(resp_dict, indent=4)
    print(pretty_json)
else:
    print("Response code: " + str(response.status_code))