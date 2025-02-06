[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/VP1meAEa)
# Portfolio project IDATA1003
Finished school project Autumn 2024

## Project description

This app is a digital representation of a fridge, where you can add or remove
food from the fridge, and see which food has expired.

It also includes a digital representation of a recipe book,
where you can add your favourite recipe, remove recipes, get the recipes you can
make with the food in your food storage and even get a recipe recommended.

## Project structure

All the source files are stored in the main directory. All except the main method are 
divided into three packages: data, logic and ui. The Ingredient class  is the only
class in the data package. The logic package contains the classes Calculator, 
FoodStorage, IteratorConvertor, Recipe and RecipeBook. The ui contains the InputParser
and the UserInterface.

All the tests are stored in the test directory. The tests are divided into two 
packages, logic and data. The test classes are placed in the directory that 
matches the package their corresponding class is placed in.


## Link to repository

https://github.com/NTNU-IE-IIR/mappe-idata1003-2024-foodwaste-fridser

## How to run the project

Run the program by running the main class, simply called main, or the main method
inside the main class.

Most of the menus contain multiple-choice options, with a number assigned to the 
option. To choose one the user simply enters the number next to the option they
want to use on their keyboard.

Sometimes the user is asked to input values instead of choosing options. Each time 
the user is clearly told what input is expected from them.

## How to run the tests

Run the tests by going to the maven sidebar to the left, going to the lifecycle
part and double-clicking the test option.

## References
Most code used is taken from the AdressBook project shown in class.

Method removeAllIngredientsWithName() is from Objects First with Java
chapter 5.

Method used to sort objects:
https://stackoverflow.com/questions/19471005/sorting-an-arraylist-of-objects-alphabetically
