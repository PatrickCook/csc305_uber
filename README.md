# Uber Assignment CSC 305
Author: Patrick Cook
Date: 10/10/2017

## To Run:
* Open project in Eclipse IDE
* The main driver for the program is UberApp.java


## How can this be used for UberEats you ask?
I designed this Uber implementation with two main objectives in mind, high cohesiveness and low coupling. 
Due to these design choices my Uber implementation is flexible and almost the entire project is reusable
for UberEats. 

UberEats would require the following additions:
* A Restaurant class
* A RestaurantMenu class
* A CustomerOrder class
* And possibly some smaller additional classes.

To convert my project into UberEats I would allow the user to select from a list or restaurants.
They would be able to select items from the menu and place an order. This order would be attached
to the requested ride object along with the restaurant location. My current implementation 
verifies that a user has the funds to pay for the Uber ride. The same verification would occur
to ensure they have enough funds to pay for the food and delivery. Because the verification 
code has been abstracted into its own method, any transaction can be verified -- flexible and reusable.

In addition to adding restaurant support, I would make a slight modification to my pick driver algorithm.
Currently the algorithm gives priority to drivers closest to the user requesting a ride. However, this would
be changed to giving priority to drivers nearest the restaurant selected by the user. I anticipate this
being a single line change -- flexible and reusable.

I would continue the conversion by changing the RideManager. By changing the pickup location of a ride to 
the location of the restaurant I would have the Uber Driver "pickup" the food at the restaurant. I would
then change the destination of the ride to the location of the user who requested it.The rest of my Uber 
implementation would stay the same besides a few minor changes.

The design choices I made when developing my Uber app would allow me to use the current design as the skeleton
of UberEats and would only require some simple additional features. I would anticipate low reconstruction of the
current implementation.