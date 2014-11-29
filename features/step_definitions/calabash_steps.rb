require 'calabash-android/calabash_steps'


When(/^I start the app$/) do
end

Then(/^I scroll down to the list bottom$/) do
  #pan_up()
  scroll("android.support.v7.widget.RecyclerView id:'book_list'", :down)
end