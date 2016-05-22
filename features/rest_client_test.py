import json

import requests

from hamcrest import *
import pytest
from requests.auth import AuthBase


def test():
    # with pytest.raises(requests.exceptions.ConnectionError):
    #     response = requests.get("https://google.com")

    requests.request('GET', 'https://api.github.com/users/qinyu/gists')

    r = requests.get("https://api.github.com/user", auth=('qinyu', 'hateu4lovingme'))
    r_json = r.json()
    print(json.dumps(r_json, indent=4))
    print(r_json['created_at'])
    assert_that(r.status_code, is_(200))
    assert_that(r_json['public_repos'], is_(36))
    assert_that(r_json['created_at'], is_('2008-11-06T10:24:03Z'))
    assert_that(r_json['site_admin'], is_(False))

    repos = requests.get('https://api.github.com/users/qinyu/gists', auth=('qinyu', 'hateu4lovingme'))
    repos_json = repos.json()
    print(json.dumps(repos_json, indent=4))


# pytest.main()

test()