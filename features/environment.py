from appium import webdriver
from behave import runner
import os

# Returns abs path relative to this file and not cwd
PATH = lambda p: os.path.abspath(
    os.path.join(os.path.dirname(__file__), p)
)

desired_caps = {
    'platformName': 'Android',
    'app': PATH(
        '../app/build/outputs/apk/app-mock-debug.apk'
    ),
    'deviceName': "192.168.58.101:5555"
}


def before_all(context: runner.Context):
    context.driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps)


def after_all(context):
    context.driver.quit()
