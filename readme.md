# Online Mobile Catalogue

eCatalogue is a mobile based product catalogue application. The purpose is remove the need to paper-based catalogue for the client and reduce costs.

## Features
  - Uses firebase to fetch the data for products
  - Uses firebase authentication for signin and signup
  - Uses firebase storage for media storage
  - Supports English and French languages
  - Uses SQLite for local data storage to work offline
  - Supports multiple currencies

## Technical Aspects
  - Based on MVVM architecture
  - Uses Dagger2 for dependancy injection
  - Uses retrofit to fetch currency conversion rates
  - Uses Room for database management
  - Uses Butterknife for view binding
  - Uses Glide for fetching images
  - Crashlytics integration

## Installation
  - Setup a firebase account
  - Add the key into local.properties
  - Setup currency converter account at https://free.currconv.com/login
  - Add the key to local.properties
  - Import the firebase collections from Setup folder using https://github.com/dalenguyen/firestore-import-export
  - Add images for the products using VueJS project https://github.com/shashankg1991/ecatalogueadmin
  - Build SDK and install it into an emulator or actual device
 
## Demo
##### Home
------
![alt text](https://github.com/shashankg1991/eCatalogue/blob/master/setup/demoimages/Home.PNG?raw=true)

##### Login
------
![alt text](https://github.com/shashankg1991/eCatalogue/blob/master/setup/demoimages/SignIn.PNG?raw=true)

##### Brands
------
![alt text](https://github.com/shashankg1991/eCatalogue/blob/master/setup/demoimages/Brands.PNG?raw=true)

##### Categories
------
![alt text](https://github.com/shashankg1991/eCatalogue/blob/master/setup/demoimages/Categories.PNG?raw=true)

##### Products
------
![alt text](https://github.com/shashankg1991/eCatalogue/blob/master/setup/demoimages/Products.PNG?raw=true)
  

 
  


