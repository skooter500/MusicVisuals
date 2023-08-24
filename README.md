My music visualizer project

Name: Mabu chemelie michael

Student Number: D19123717

Description: this program is like  nature as i would like to call it, firstly i adopted a music that plays when you run the program. the music has this ecstaic rave vibe, which is the reason i choose it. However, the music is not where the fun ends, the program is menu-driven with just two options, frist option to exit the program and the other to visualize the project.


Instruction: the instruction of the code is pretty much self explanatory but i will go through it. so when the program runs it initiaties the GUI which shows a menu with calm colours and music playing. in the menu you have a promtp message with two options as mentioned earlier in the description. when 1 is pressed it takes you to the visulaizer primarily designed to visualize an audio input using clouds flowers and other natural elemnts implemented in the program


The Visualizer program is designed to create a visual representation based on some form of audio input. a liitle summary on how the program works.
  
  for the audio input and amplitude calculation, the program processes audio data to compute an amplitude value using the getAmplitude() method, which converts the audio buffer to an array. the applitude is calaculated by squaring each value in the buffer, summing them up, and then taking the square root of the average of those squared values. The amplitude will typically rise and fall based on the loudness and intensity of the audio.
   
   for visual representation like drawing static components, the method drawsun is used to draw the sun on the canvas as well as the drawGround method which draws a rectangle that visually represents the ground. the render() method is responsible for drawing the entire scene on the canvas. The sequence of drawing is:
   Draw the sun.
   Draw the flowers.
   Draw the ground.
   Draw ellipses based on the amplitude (perhaps some visual feedback).
   Draw clouds, with sizes influenced by the audio's amplitude.
   It seems the render() method is called repeatedly (probably in a loop), which means the visualization will be dynamic and continuously update as the audio plays.

finally When audio plays, this program visualizes it by displaying a scene with a sun, ground, flowers, and clouds. The flowers rotate, and the clouds change size based on the audio's amplitude, providing a dynamic visual representation of the audio.

i am proud of many things in this project although some of the methods in the program were very diffcult to implement because of its complexity and also the kind of design i had in mind for the methods. The code is organized using an object-oriented approach. The use of classes, methods, encapsulation, and inheritance (e.g., extending the Visual class) is a clear indication of a structured and modular design. attention to details was one of the great factor that helped me with some design implementaion like having the flowers rotate in opposite directions relative to their stems.
finally this project serves as a learning experience, although i faced some challenges working on the program buh at the same time i gained some skills that i would be adding in my arsenal.


