# Renovation Company

## Task:
A renovation company needs to hang new wallpaper in a bunch of rooms. They need to
cover even the floors and ceilings with it.
They run out of wallpaper so they need to submit an order for more. Knowing the dimensions
(length l, width w, and height h) of each room, they want to order only as much as they need.
Every room is a rectangular prism, which makes calculations easier: the surface area of
room is 2*l*w + 2*w*h + 2*h*l.
The company also need a little extra wallpaper for each room: the area of the smallest side.
For example:
- A room with dimensions 1x2x3 requires 2*2 + 2*6 + 2*3 = 22 square feet of wallpaper
  plus additional 2 square feet,
  for a total of 24 square feet.
- A room with dimensions 1x1x5 requires 2*1 + 2*5 + 2*5 = 22 square feet of wallpaper
  paper plus 1 additional square foot,
  for a total of 23 square feet.
  All numbers in the rooms list are in feet. Write functionalities taking into account our
  expectations that will:
- calculate number of total square feet of wallpaper the company should order for all
  rooms from input (use input1 as an example)
- list all rooms from input that have a cubic shape (order by total needed wallpaper
  descending)
- list all rooms from input that are appearing more than once (order is irrelevant)

## run application
- from idea, copy an input file to anywhere in the project (preferably root or `src/main/resources`) and run main class with path to input file as arg. If no path is given it will process the `input1.txt` file from the email.
- you can build jar with `gradlew shadowJar` with this you can test application with given input file as arg for the jar file