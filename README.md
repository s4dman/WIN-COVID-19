# WIN-COVID-19
<img src="https://img.shields.io/badge/alpha-1.0.0-blue"/> <img src="https://img.shields.io/badge/dependencies-up--to--date-brightgreen" /> <img src="https://img.shields.io/github/license/s4dman/WIN-COVID-19"/> <img src="https://img.shields.io/github/repo-size/s4dman/WIN-COVID-19"/> <img src="https://img.shields.io/github/last-commit/s4dman/WIN-COVID-19"/>

WIN COVID-19 will help us to fight against the coronavirus by raising awareness, supporting the community, and by reducing the risk - all in one place.

This project was built from scratch by Team WinDroid, reieved Winner Honorable Mention at WinHacks 2020 and also submitted for DSC Solution Challenge 2020.

## Screenshots:

<p float="center">
  <img src="https://user-images.githubusercontent.com/9642377/80726646-adb05080-8ad2-11ea-806e-5c713ddeee91.png" width="200" />
  <img src="https://user-images.githubusercontent.com/9642377/80726661-b4d75e80-8ad2-11ea-8b7f-1da496ff4780.png" width="200" /> 
  <img src="https://user-images.githubusercontent.com/9642377/80726672-b86ae580-8ad2-11ea-9e18-1901bbe18776.png" width="200" />
  <img src="https://user-images.githubusercontent.com/9642377/80726677-b9037c00-8ad2-11ea-80b3-2314d6b31798.png" width="200" />
</p>

## The User Journey:
After starting the app, it will ask the user's current address. After inserting the address and by tapping continue it will take the user to the Community screen.

**Community:**

There are two tabs- Offers and Requests. Offers contain all the product listings that other users are willing to share from their stocks. Requests contain the requested items by other users who are in need. To create a listing in the Offers section, the user needs to tap the add icon on the bottom right corner. It will ask the user about the product name, quantity, category, and his phone number by which he will be contacted by other users. The same way users can create listings in the Requests section.

**Reminders:**

The reminder feature will help people by reminding them to wash their hands, avoid face touching, and clean their house at a regular interval. For example, if the user selects the Hand Wash reminder for every 1 hour, the app will notify the user to wash their hands every 1 hour. Same works for the Avoid Face Touch and Clean House Reminder.

**Self-Assessment:**

The self-assessment tool will help people assess their health condition. There are some standard questions, by answering those he will get necessary instructions according to their answers. The assessment result will also show the necessary information about the user's nearest Test center. **_This nearest test center will be fetched based on the address the user provided at the beginning._**

From the profile section, the user can log in/signup and manage their listings. Once the purpose is served, the user can delete their listings.

## Google Technologies Used:
* Android
* Google Places API
* Firebase Authentication
* Cloud Firestore

## Installation:
Download and install the apk from [Release](https://github.com/s4dman/WIN-COVID-19/releases/tag/1.0.0-alpha)

Or

To clone the full source code:

`git clone https://github.com/s4dman/WIN-COVID-19.git`

Limitations:

> Only 3 cities(Windsor, London, Markham) are stored in the current DB for fetching the nearest test centers. Other addresses outside these cities will not show any nearest test centers.  

## Video Presentation:

[Youtube: WIN COVID-19](https://www.youtube.com/watch?v=pqs61VxVObs)

## Team WinDroid:

* [Sadman Hasan](http://sadmanhasan.com/)
* Samir Shahriar
