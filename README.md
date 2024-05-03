# Music Visualiser Project

Name 1:Éadaoin Ó Snodaigh
<br>Name 2:Fionán Ó Ceallaigh 
<br>Name 3:Alannah Walsh

Student Number 1: C22398106
<br>Student Number 2: C22337521
<br>Student Number 3: C22339066


## Instructions
- Fork this repository and use it a starter project for your assignment
- Create a new package named your student number and put all your code in this package.
- You should start by creating a subclass of ie.tudublin.Visual
- There is an example visualiser called MyVisual in the example package
- Check out the WaveForm and AudioBandsVisual for examples of how to call the Processing functions from other classes that are not subclasses of PApplet

# Description of the assignment
For this assignment, we were tasked as a group to make a music visualizer using Processing in Java. Each of us was assigned a specific case to add our visuals to the program. Each member took on the task and made creative additions to the program. Using github we  there are 7 cases in total, 2 made by Alannah, 2 made by Fionán and 3 made by Éadaoin. across each of our cases we explored a various amount of functions. Fionán explored 3D models and matrixes, Alannah employed mouse input for one of her cases, and Éadaoin explored her creativity by combining two of her cases to making a unique 3rd case.

# Instructions
We used a keypressed and switch case to access the individual visuals. We were able to swap between visuals by pressing between 0-7. As well as this we included keys to change song, this was 'g' for We Like To Party and 'h' for Glue. We also used 'w','a','s' and 'd' to change the camera angles to view different dimensions of our visuals.

# How it works
<b>Éadaoin</b>
<p><b>vertex.java - </b>This visual is 8 octagons that circle around each other. Thee octagons join together so it looks like 1 shape, this is because I used vertex which joins each point. The shape rotates based on frame count. I used maths functions like TWO_PI and sin and cos. The octagons get bigger and smaller depending on the beat. Also the background changes colour based on the framecount, meaning each time the background changes it looks like one octagon goes missing!</p>
<br>
<p><b>circles.java - </b>This visual shows loads of circles overlapping each other to creat a cool triskele style art, it goes from left to right in rainbow colours. There are 2 nested loops. The outer loop iterates over the width of the screen, and the inner loop iterates over the height, both adding 25 pixels each iteration(to create evenly spaced circles). It draws a circle at the current position of i and j (both used in the for loops). The size of the circles change based on the beat which makes the really cool visual.</p>
<br>
<p><b>circlevertex.java - </b>Like Hannah Montana's Song, The Best of Both Worlds, this code combines both my visuals to create an epic visual. The octagons are now created with a line of circles instead of just a line. The visual rotates and changes background colour with frame count. The code includes a for loop to create the octagons made of circles using the same maths functions as vertex.java. Each octagon(set of circles) has a different colour.The size of the circles, like the octagons, change based on the beat in the song, meaning there is a lot of movement in this visual.</p>
<br>
<b>Fionán</b>
<br>
<p><b>TriMatrix.java - </b>
This is the first of two visuals I created. It makes use of a 3D Matrix from earlier processing examples, however it shifts the cubes in the grid on the X and Y axis based on the music, additionally there is a traingle that reavts dynamically to the music.		<br>	       
This visual conists of:
- A matrix grid of cubes that shift in response to music.
- The background color shifts in response to the music
- A triangle rotates, changes size colour, synchronised with the music.</p>
<br>
<p><b>Tricar.java - </b>
This is the second visual I created. It repurposes the traiangle from earlier, scales it down and dupliactes it to created a grid accross teh top and bottom of the screen, it also makes use of a .obj file to insert a sports car model that spins and bounces to the music.
<br>	
This visual conists of:
- A dynamic grid of triangles that rotate and change color based on sound.
- A 3D sports car model that bounces in rhythm with the music.
</p>
<br>
<b>Alannah</b>
<p>i did not use seprate java files i just used cases so i will discuss them now.</p>
<br><b>Case 3</b>
<p> Here I had eyeballs that follow the mouses movements. I also had a circle inn the middle on the screen that would react to the music.</p>
<p>The eyeball effect was created by using 2 ellipses , one bigger solid filled and then one smaller no filled one (since the background was black i could use the nofill() and create the effect of pupils). The eye positions are pre-defined as an array of PVector objects, representing the coordinates where the eyes will be drawn on the screen.To create the pupil movement effect, the code calculates a vector (pupilDifference) based on the difference between the eye's position and the current mouse position. This difference vector is scaled to ensure the pupils stay within the eye's boundary.</p>
<p>The  background effecct was created using the sin function to generate a wave-like motion based on the current frame count and the smoothed audio amplitude (smoothedAmplitude), which is updated as audio is processed.The wave's height is modulated by the smoothed amplitude, providing a reactive component. As the music's volume increases, the wave becomes more pronounced. The effect is drawn by creating a circular shape (beginShape()...endShape()) and using a loop to define vertices based on the sin function's output. This creates a dynamic circular ripple effect.</p>
<br><b>Case 4</b>
<p>Here i had 3D cubes moving foward and 2 digonal lines reacting to the music</p>
<p>The code uses an FFT (Fast Fourier Transform) to analyze the audio data. FFT converts audio signals from the time domain to the frequency domain, allowing you to break down the audio into different frequency bands. As part of the visual effect i made a cube class...
<p>Cube class: This is a custom class representing a 3D cube. It has properties for position (x, y, z), rotation (rotX, rotY, rotZ), and size, with additional fields to manage speed and cumulative rotation.In the Cube constructor, the cubes are initialized with random positions and rotations to create a varied 3D scene. The display method in the Cube class is responsible for rendering the cubes. The cubes are translated in 3D space, rotated, and then drawn using the box function from Processing.</p>
<p>Line Segments: The code defines two sets of diagonal lines that cross the screen: one from top-left to bottom-right and one from top-right to bottom-left. These lines are made up of a series of points, whose positions are influenced by the FFT data. The updateLineOffsets method updates the displacement values for these lines based on the FFT data. The displacement determines how much each segment is offset from a straight line, creating a wave-like motion. The drawLine method is used to draw the lines, using the offsets to adjust the y-coordinates of the line segments. This creates a visual effect where the lines appear to react to the audio data.


# What I am most proud of in the assignment
<b>Éadaoin</b>
<p>I am most proud of being able to join both of my initial visuals into a 3rd visual with a mixture of my code, creating an even cooler visual. When I initially tried this it looked okay but not great, and with more editing and trial and erroring I was able to get my circle vertex to a place that I was really happy with and have it looking good. I think this visual was easier than my other 2 visuals, but only because I had already done the hard work and dealt with the difficulties involved with creating them. I had already learned a lot from my first 2 visuals as well so it was quicker when I was editing as I knew each of the variables and what they would change.</p>
<br>
<b>Fionán</b>
<p>I'm most pround of being able to significantly improve my skills with using Version Control System (VCS) throughout this assignemnet, along with improving my Java skills and getting a better understanding of creating sketches with Processing, putting my skills in Git to use was very rewarding, when moving code from my own testing environment into our shared repository, I ran into a few merge conflicts which couyld not be fast-forwarded or rebased without manually resolving, this tested my skills and was the first chance I got properly using a VCS during this programme, which I was very proud of. I f I was to do this again, I would make use of feature branches and use a VCS from the beginning to avoid issues with clashing variable names, when incorporating my code in with the rest of the team. Additionally, I was proud with how I managed to overcome an issue in the trimatrix.java file where the traingle was not visible in front of the matrix, it was during this that I discovered the `translate()` function in processing and how it could help me place object effectively on the z-axis to overcome this issue.</p>
<br>
<b>Alannah</b>
<p>The aspect I'm most proud of in my part of the assignment was my ability to adapt. I faced several instances where my code threw errors, and I wasn't sure why. These challenges were frustrating, but they became learning opportunities for me. Instead of giving up, I adapted. I researched, i watched videos and i ran my code again and again to try and improve it. One specific example comes to mind, before my finished product, in my case 4, i.e the cube case, i used to also have a wall class. the wall class was a long piece of code that i worked really hard on to get working, it reacted with the music and looked great along my cube class. But unfortunately, when putting it in the cases, the wall code stopped working, i tried for hours to try and fix it to no avail. i was frustrated and disappointed, 'why cant i get it' i thought. so i had to go back to the drawing board. i adapted and made the decision t remove the wall code and instead add two lines diagonally across the screen(as seen in the final program). Even though it was frustrating to remove the wall code, it was my ability to adapt and emply a new creative solution that i am really proud of, and will take forward with me. </p>


Youtube video:

[Watch the video on YouTube](https://youtu.be/O8TJEiXIKQE) 


