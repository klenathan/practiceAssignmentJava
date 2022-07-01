# JAVA ASSIGNMENT PRACTICE

by Nam Thai Tran

---
## Getting started

---
### Description

 The project is to prepared me for future Java course.
The application is a Java console-based application. It displays Covid-related data in some regions in the world, with grouping function.

Covid-related data fields are new cases and new deaths number per day, vaccinated number to the day of query and the country population.
The application also display some additional information about the country: iso code and its continent.

### Input data

The input data is in csv form, which can be found in [Data folder](https://github.com/klenathan/practiceAssignmentJava/tree/main/data).


---
## Objects

Since the main objective of this project is to help me to practice Java OOP, I tried my best to divide the program into small objects
for the ease of maintenance. 

Main flow:
> Data() -> Summary() -> Display() -> Main()
---

#### Country and DailyData (Object)

Country and DailyData are 2 supporting Object in this project. 

DailyData represent the data each day of a country: 
```java
public class DailyData {
    private String date, new_cases, new_death, vaxed, population;
}
```
Country class represent the data of a country:
```java
public class Country {
    private String isoCode, continent, location;
    private ArrayList<DailyData> countryData;
}
```
The idea of Country class is similar to HashMap :) with iso_code and country's information as the key and the main value of the object is the ArrayList of DailyData. 

#### Data

The Data class in charge of gathering the data and generating Country object. Those Country objects are then added into an ArrayList.
The ArrayList is then transferred to Summary for further processing.
```java
public class Data {
    ArrayList<Country> countryArrayList = new ArrayList<>();
    HashMap<String, ArrayList<DailyData>> countryDataArray = new HashMap<>();
    String path;
}
```

#### Summary

The class is in charge of dividing and grouping DailyDate into ArrayList according to the requirement of the project.

> This one is under construction :D!


