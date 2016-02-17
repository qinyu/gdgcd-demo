from appium.webdriver.webdriver import WebDriver
from selenium.webdriver.support import expected_conditions as EC

from behave import given, then, when
from behave import runner
from nose.tools import ok_, eq_
from selenium.webdriver.support.wait import WebDriverWait


@given(u'I start the app')
def step_impl(context: runner.Context):
    driver = context.driver  # type:  WebDriver
    eq_(".activity.BooksActivity", driver.current_activity)
    driver.save_screenshot("snapshots/books.png")


@given(u'I wait the book list loading finishes')
def step_impl(context: runner.Context):
    driver = context.driver  # type: WebDriver



    search = driver.find_element_by_id("search")
    search.click()
    search.send_keys("google")



    def xx(driver):
        return driver.find_elements_by_id("title")

    els = WebDriverWait(driver, 5).until(xx)

    driver.implicitly_wait(5)
    # els = driver.find_elements_by_id("title")
    ok_(len(els) > 0)


@then(u'I see "Programming Google App Engine"')
def step_impl(context):
    driver = context.driver  # type: WebDriver
    titles = [el.text for el in driver.find_elements_by_id("title")]
    ok_(titles.index("Programming Google App Engine") >= 0)


@when(u'I scroll down to the list bottom')
def step_impl(context):
    driver = context.driver  # type: WebDriver


    driver.swipe(75, 1600, 75, 200, 400)
    driver.swipe(75, 1600, 75, 200, 400)


@then(u'I see "Google Maps API, 2nd Edition"')
def step_impl(context):
    driver = context.driver  # type: WebDriver


    titles = [el.text for el in driver.find_elements_by_id("title")]
    ok_(titles.index("Google Maps API, 2nd Edition") >= 0)
