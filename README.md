# Hex-Map-Generator---Minimal-Viable-Product-Example
This is a small project I was playing around with. It represents my philosophy of rapid prototyping. This project would be approximately a day and a half of work, and is designed to be put in front of a customer as a proof of concept. There fore it lacks proper tests, comments and several links/buttons don't actually work. However the core functionality of being able to generate a hex map works. It could be used as the center piece of a discussion with a customer to solicit input while prioritizing features and establishing business value.


To run this project clone it and open in ecllipse. The only button that works is the "new" button. 
Clicking it will generate a new basic hex map.

Interesting bits of logic:
It attempts to ensure that surrounding hexes have similar levels of fertility, wetness and elevation. 
Aak it randomly varies fertility, wetness and elevation for each hex from the average of the surrounding hexes. 
It uses fertility, wetness and elevation to select which type of terrain is potentially valid, then choose amongst those at random.
It can handle vertical resizing to a limited degree.
