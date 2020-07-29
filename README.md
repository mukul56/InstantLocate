# InstantLocate

### An Android Library to get Accurate Location with ease

Fetching an Current Accurate Location in android is not an easy task, So to make this task very simple with just 2-3 lines of code there is an InstantLocate Library which comes in picture.

##  **How to Use**
### 1. Add repository to build.gradle (project level)
    allprojects{
		  repositories{
			  maven{ url 'https://jitpack.io'}  
	           }
     }
### 2. Add dependency to build.gradle (app level)
    dependencies {
         implementation 'com.github.mukul56:InstantLocate:1.0'        
         }


### 3. Add Permissions in your Manifest
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>     
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>                      
    <uses-permission android:name="android.permission.INTERNET"/>     


# Documentation

**First you have to request for permissions which you declared  in your Manifest**

1. Create an Array of Permissions

       String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION};



    
2. Request For Permissions

        ActivityCompat.requestPermissions(this, permissions, 1); 



> For fethching location it is necessary to first Request for required permissions


**Initialize the InstantLocate by using following line:**
    InstantLocate instantLocate = new InstantLocate(this);      


**Methods of InstantLocate:**

 1. **instantLocation()**  *This method is used to fetch instantLocation*
 2. **getContinuousLocation()** *This method is used to fetch continuous Location untill you call stop() method*
 3. **getLatitude()** *This method is used to get latitude of a location and return double value*
 4. **getLongitude()** *This method is udes to get longitude of a location and return double value*
 5. **stop()** *This method is used to stop fetching of location*
 6. **isGpsEnabled()** *This method is used to check Gps is enabled or not. If Gps is not enabled then you should call* **showSettingAlert()** *to enable Gps*

 ## Demonstration
1. If you want to get instantLocation then you need **not to call stop()** method
![](https://github.com/mukul56/InstantLocate/blob/master/app/src/main/res/drawable/instloCode.png)

2. If you call getContinuousLocation then you should **call stop()** method to stop fetching of location
![](https://github.com/mukul56/InstantLocate/blob/master/app/src/main/res/drawable/contlocation.png)

__________________________________________________________________________________________________________________________________________________________________

