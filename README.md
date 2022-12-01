# Open-Team4-AndroidApp

One of the good practices when writing code is “Reusability”. Instead of writing the same code multiple times, we should write it once and use it wherever we need to.

As android incoming AAD i used Activities Only in this branch. 
I used BaseActivity for Authentication. so codes like Dialog, Snackbar, Progress Dialog and other features common between screens are coded once in the base Activity and other Activities inherit it instead of Appcompativity. Which is already in the BaseActivity.


TECHNOLOGY used in this project

1. Constraints Layout
2. ViewBinding
3. FireBase Authentication 
4. Firebase Store 
5. ViewPager 2


Issues With This app 
1. On some photos, The onboarding screen textView dont appear. 
2. Payment Screen breaks for no reason, I am still investigating 
3. its uses intent to nagivate screen which old approach. Some of these issues were addressed on GrandeFinaleWorking Branch 
