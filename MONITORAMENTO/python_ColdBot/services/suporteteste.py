# from jira import JIRA
# import requests
# from requests.auth import HTTPBasicAuth

# jira = JIRA()

# jac = JIRA('https://jira.atlassian.com')

# auth_jira = JIRA(basic_auth=('201grupo10c@bandtec.com.br', 'xYT7D7fZZRvpKWl0svMyC6C9'))
# auth_jira = JIRA(kerberos=True)
# auth_jira = JIRA(kerberos=True, kerberos_options={'mutual_authentication': 'DISABLED'})

# issue = jac.issue('CDAT-5')
# summary = issue.fields.summary

# print(summary)

from jira import JIRA
import re

# By default, the client will connect to a Jira instance started from the Atlassian Plugin SDK
# (see https://developer.atlassian.com/display/DOCS/Installing+the+Atlassian+Plugin+SDK for details).
# Override this with the options parameter.
# options = {"server": "https://coldstock.atlassian.net"}
# jira = JIRA(options)
key_cert_data = None
with open(key_cert_data, 'r') as key_cert_file:
    key_cert_data = key_cert_file.read()

oauth_dict = {
    'access_token': 'foo',
    'access_token_secret': 'bar',
    'consumer_key': 'Nl876L38OjaKIptaiz54gsZrJRXCyiqw',
    'key_cert': key_cert_data
}
auth_jira = JIRA(oauth=oauth_dict)

# Get all projects viewable by anonymous users.
projects = auth_jira.projects()

# Sort available project keys, then return the second, third, and fourth keys.
keys = sorted([project.key for project in projects])[2:5]

# Get an issue.
issue = auth_jira.issue("CDAT-5")

# Find all comments made by Atlassians on this issue.
# atl_comments = [
#     comment
#     for comment in issue.fields.comment.comments
#     if re.search(r"@atlassian.com$", comment.author.emailAddress)
# ]

# Add a comment to the issue.
auth_jira.add_comment(issue, "Olá, cliente! Estamos trabalhando no seu problema! :)")

# Change the issue's summary and description.
issue.update(
    summary="Ataque dos python loko", description="Pegadinha é só teste."
)

# Change the issue without sending updates
#issue.update(notify=False, description="Quiet summary update.")

# You can update the entire labels field like this
#issue.update(fields={"labels": ["AAA", "BBB"]})

# Or modify the List of existing labels. The new label is unicode with no
# spaces
#issue.fields.labels.append(u"new_text")
#issue.update(fields={"labels": issue.fields.labels})

# Send the issue away for good.
#issue.delete()

# Linking a remote jira issue (needs applinks to be configured to work)
# issue = jira.issue("JRA-1330")
# issue2 = jira.issue("XX-23")  # could also be another instance
# jira.add_remote_link(issue, issue2)