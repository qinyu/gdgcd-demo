require 'calabash-android/calabash_steps'


Given(/^I start the app$/) do
end

Given(/^I wait the book list loading finishes$/) do
    wait_for_elements_exist("android.support.v7.widget.CardView", :timeouts => 5)
end

When(/^I scroll down to the list bottom$/) do
  3.times do
    pan_up()
  end
end