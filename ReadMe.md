# Codezync Boiler Plate (Java)
===========================

This is the Java Boiler Plate Code Base Of Codezync.This is created as a library so on any project
this is able to import as a gradle dependency and start using the features.

#### Project Includes Following Features

* Draw map View With Different Configurations (with Current Location,custom markers).
* Fetch Direction Using Google Direction API
* Draw Polyline for Location Updates.
* Read Users Location Updates.
* Helpers For Checking Runtime Permissions.

This is library is distributed through **Jitpack**  .

[ **
Jitpack**  Distribution Page Link for the Project ](https://jitpack.io/#Codezync/Codezync-boilerplate)

#### Project Structure

Project Has Following Models and Each Module Has its Own Purpose.Please Strict to them While
Maintaining

- app
- boilerplate

**NOTE :** All above modules except { app} are similar to libraries .you can include
them to any other module and use their features.If the project requires additional modifications on
this library , then include the 'boilerplate' module as a additional module to the targeted project,
and then do the modification on the module it self.

#### App
This Module includes a sample Usage of the features included in the module 'boilerplate'.

#### Boilerplate

All Above mentioned features are implemented under this module.

### Related Articles

* [Jitpack](https://docs.jitpack.io/android/#gradle) - Deploying Android Library on Jitpack
* [Publish Your Android Library on JitPack for Better Reachability](https://www.talentica.com/blogs/publish-your-android-library-on-jitpack-for-better-reachability/)

---
# **Developer Documentation**
---

##### Branch Descriptions in the Project

- main :- This branch contains the code for the latest public release..after releasing to
  production , this branch should be updated.When you want to make a new release on Jitpack, tag and
  commit the changes to the main branch.




