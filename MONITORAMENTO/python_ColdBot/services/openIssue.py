# This code sample uses the 'requests' library:
# http://docs.python-requests.org
import requests
from requests.auth import HTTPBasicAuth
import json

url = "https://coldstock.atlassian.net/rest/api/3/issue"

auth = HTTPBasicAuth("201grupo10c@bandtec.com.br", "xYT7D7fZZRvpKWl0svMyC6C9")

headers = {
   "Accept": "application/json",
   "Content-Type": "application/json"
}

payload = json.dumps( {
  "update": {},
  "fields": {

    "summary": "Teste api no python",
     
    "issuetype": {
      "id": '10003'
    },

    "project": {
      "id": "10003"
    },

    "description": {
      "type": "doc",
      "version": 1,
      "content": [
        {
          "type": "paragraph",
          "content": [
            {
              "text": "Descrição da Api do jira",
              "type": "text"
            }
          ]
        }
      ]
    },
    
    "labels": [
      "Alerta-python",
      "Alerta-CPU"
    ],
  }
})



response = requests.request(
   "POST",
   url,
   data=payload,
   headers=headers,
   auth=auth
)

print(json.dumps(json.loads(response.text), sort_keys=True, indent=4, separators=(",", ": ")))

