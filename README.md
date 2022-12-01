# Open-Team4-AndroidApp



One of the good practices when writing code is “Reusability”. Instead of writing the same code multiple times, we should write it once and use it wherever we need to.

![](https://github.com/404SolutionsGH/Open-Team4-AndroidApp/blob/Dev/TrotroDev.png)

As android incoming AAD i used Activities Only in this branch. 
I used BaseActivity for Authentication. so codes like Dialog, Snackbar, Progress Dialog and other features common between screens are coded once in the base Activity and other Activities inherit it instead of Appcompativity. Which is already in the BaseActivity.


Download APK HERE and TRY it -https://drive.google.com/drive/folders/1FSb9xdOpVntasEngP1aFC1rYZw-6zjWJ?usp=share_link


TECHNOLOGY used in this project

1. Constraints Layout
2. ViewBinding
3. FireBase Authentication 
4. Firebase Store 
5. ViewPager 2


Issues With This app 
1. On some phones more API 27, The onboarding screen textView dont appear. 
2. Payment Screen breaks for no reason, I am still investigating 
3. its uses intent to nagivate screen which old approach. Some of these issues were addressed on GrandeFinaleWorking Branch 



The Solutions to the New Solutions On the GrandeFinaleWorking branch 
i tried with the Architure component to achieve same tasks with different approach
1. I used Navigation Compnent to avoid the uses of too much INTENTS
2. i also used fragment and activites with findNavController
3. Data Persistance - I saved create a Room database on the branch 
4. The App crash for no reason on the GrandeFInaleWorking branch i still working on it 
