import requests
import json

url = 'http://localhost:9090/api/getAllDemands'

response = requests.get(url)

# Convert the response content to a Python dictionary
resp_dict = response.json()

# Pretty print the dictionary with an indentation of 4 spaces
pretty_json = json.dumps(resp_dict, indent=4)
print(pretty_json)
