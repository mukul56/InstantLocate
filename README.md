# InstantLocate

### An Android Library to get Accurate Location with ease

Fetching an Current Accurate Location in android is not an easy task, So to make this task very simple with just 2-3 lines of code there is an InstantLocate Library which comes in picture.

##  **How to Use**
### 1. Add repository to build.gradle (project level)
  ![](https://github.com/mukul56/InstantLocate/blob/master/app/src/main/res/drawable/jit.png).

### 2. Add dependency to build.gradle (app level)
  ![](https://github.com/mukul56/InstantLocate/blob/master/app/src/main/res/drawable/dep.png).


### 3. Add Permissions in your Manifest
  ![](https://github.com/mukul56/InstantLocate/blob/master/app/src/main/res/drawable/permis.png).



# Documentation

**First you have to request for permissions which you declared  in your Manifest**

1. Create an Array of Permissions 
![](https://github.com/mukul56/InstantLocate/blob/master/app/src/main/res/drawable/perarra.png).

2. Request For Permissions
![](https://github.com/mukul56/InstantLocate/blob/master/app/src/main/res/drawable/reqper.png).


**Initialize the InstantLocate by using following line:**
![](https://github.com/mukul56/InstantLocate/blob/master/app/src/main/res/drawable/init.png).



**Methods of InstantLocate:**

 1. **instantLocation()**  *This method is used to fetch instantLocation*
 2. **getContinuousLocation()** *This method is used to fetch continuous Location untill you call stop() method*
 3. **getLatitude()** *This method is used to get latitude of a location and return double value*
 4. **getLongitude()** *This method is udes to get longitude of a location and return double value*
 5. **stop()** *This method is used to stop fetching of location*
 6. **isGpsEnabled()** *This method is used to check Gps is enabled or not. If Gps is not enabled then you should call* **showSettingAlert()** *to enable Gps*


