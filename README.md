# InstantLocate

### An Android Library to get Accurate Location with ease

Fetching an Current Accurate Location in android is not an easy task, So to make this task very simple with just 2-3 lines of code there is an InstantLocate Library which comes in picture.

##  **How to Use**
### 1. Add repository to build.gradle (project level)


### 2. Add dependency to build.gradle (app level)


### 3. Ask for Permissions in your Manifest


# Documentation

**Initialize the InstantLocate by using following line:**

**Methods of InstantLocate:**

 1. **instantLocation()**  *This method is used to get instantLocation*
 2. **getContinuousLocation()** *This method is used to fetch continuous Location*
 3. **getLatitude()** *This method is used to get latitude of a location*
 4. **getLongitude()** *This method is udes to get longitude of a location*
 5. **stop()** *This method is used to stop fetching of location*
 6. **isGpsEnabled()** *This method is used to check Gps is enabled or not. If Gps is not enabled then you should call* **showSettingAlert()** *to enable Gps*

