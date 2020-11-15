# import requests
# from requests.auth import HTTPBasicAuth
# import json

# class SuporteBot:
#     def __init__(self, usuario):
#         self.usuario = usuario
    
#     def jira(self):
#         url = "https://coldstock.atlassian.net"

#         auth = HTTPBasicAuth("Nl876L38OjaKIptaiz54gsZrJRXCyiqw", "e8g3WZrGrmkDAZxbKlnrTgv7yovpttmhHn2dRRNMcYSYIZQfat9eiisKUL5lyMqt")

#         headers = {
#            "Accept": "application/json",
#            "Content-Type": "application/json"
#         }

#         # chamado = {
#         #     'project': {'id': 10000},
#         #     'summary': 'Testando api',
#         #     'description': 'Funcionou?',
#         #     'issuetype': {'name': 'Teste'},
#         # }
#         print("Iniciando a criação de chamado")
#         chamado = json.dumps( {
#           "update": {},
#           "fields": {
#             "summary": "Estou aqui testando, oi",
#             "parent": {
#             "key": "CDAT"
#             },
#             "issuetype": {
#               "id": "10321"
#             },
#             # "components": [
#             #   {
#             #     "id": "10000"
#             #   }
#             # ],
#             "customfield_20000": "06/Jul/19 3:25 PM",
#             "customfield_40000": {
#               "type": "doc",
#               "version": 1,
#               "content": [
#                 {
#                   "type": "paragraph",
#                   "content": [
#                     {
#                       "text": "Não sei não hein",
#                       "type": "text"
#                     }
#                   ]
#                 }
#               ]
#             },
#             # "customfield_70000": [
#             #   "jira-administrators",
#             #   "jira-software-users"
#             # ],
#             "project": {
#               "key": "CDAT"
#             },
#             "description": {
#               "type": "doc",
#               "version": 1,
#               "content": [
#                 {
#                   "type": "paragraph",
#                   "content": [
#                     {
#                       "text": "meu deus nada funciona.",
#                       "type": "text"
#                     }
#                   ]
#                 }
#               ]
#             },
#             # "reporter": {
#             #   "id": "5b10a2844c20165700ede21g"
#             # },
#             # "fixVersions": [
#             #   {
#             #     "id": "10001"
#             #   }
#             # ],
#             "customfield_10000": "14/11/20",
#             "priority": {
#               "id": "20000"
#             },
#             "labels": [
#               "testebot",
#               "testepython"
#             ],

#             "environment": {
#               "type": "doc",
#               "version": 1,
#               "content": [
#                 {
#                   "type": "paragraph",
#                   "content": [
#                     {
#                       "text": "UAT",
#                       "type": "text"
#                     }
#                   ]
#                 }
#               ]
#             },
#             "versions": [
#               {
#                 "id": "10000"
#               }
#             ],
#             "customfield_50000": {
#               "type": "doc",
#               "version": 1,
#               "content": [
#                 {
#                   "type": "paragraph",
#                   "content": [
#                     {
#                       "text": "Alguem ai?",
#                       "type": "text"
#                     }
#                   ]
#                 }
#               ]
#             },
#             # "assignee": {
#             #   "id": "5b109f2e9729b51b54dc274d"
#             # }
#           }
#         } )

#         response = requests.request(
#            "POST",
#            url,
#            data=chamado,
#            headers=headers,
#            auth=auth
#         )

#         #print(json.dumps(json.loads(response.text), sort_keys=True, indent=4, separators=(",", ": ")))
