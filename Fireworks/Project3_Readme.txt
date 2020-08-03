GUI was constructed by making a JFrame(called Projectile) and adding two JPanels:
1 JPanel(Control Panel) contains controls and is at the top of the JFrame, and 
the other JPanel(Tapestry) contains the canvas(though I called it Tapestry
because I was having issues with the canvas name for some reason. 
Tapestry is a synonym for canvas) which will display the simulation.
The methods for the two JPanels--Control Panel and Tapestry are contained in two inner classes with the same names.
I limit the user's input in terms of velocity and angle and launch speed 
to ensure that the projectile will always appear on screen.
My GUI controls don't have listeners except the launcher button.
Pressing it will prompt the paint component to get the state of all other
controls and use their state as appropriate.

Notes on the GUI controls: 

JSlider Angle: Practically speaking, the firework cannot be deployed at an angle of 0 degrees,
so to make a simulation more akin to real-life, the angle range has been limited to between
30 and 89 degrees.

JSlider timeDelay: Practically speaking, the firework should not explode instantly, 
so the minimum value of this slider is 10(seconds). the maximum value, to ensure the graphics
are displayed within the bounds of the JFrame, is 20(seconds).

JSlider speed: For practical reasons, the firework should have an initial velocity between 50m/s 
and 150ms so that it can travel a reasonable distance from the starting point but not so much that
it goes beyond the display.



Notes on methods:

Tapestry inner Class: 
Colorize(Graphics g): Gets the state of the JComboBox colour and sets the color of the projectile to
the color currently chosen in the JComboBox.