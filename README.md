# Music Visualiser Project

Name:Rana Hayes, Isadora Valentini, Ceren Ucan

Student Number: C21920856, D21124013, D21125681

## Instructions
- this repository and use it a starter project for your assignment
- Create a new package named your student number and put all your code in this package.
- You should start by creating a subclass of ie.tudublin.Visual
- There is an example visualiser called MyVisual in the example package
- Check out the WaveForm and AudioBandsVisual for examples of how to call the Processing functions from other classes that are not subclasses of PApplet

# Description of the assignment
##Ceren
The code provided is a part of a Java program called "CerensSongVisualizer." It is a song visualizer that creates colorful particles that move and change size based on audio input. The assignment aims to implement a particle system and visualize the audio waveform and a rotating star shape. The code utilizes the Minim library for audio processing and the Processing library for graphics rendering.

## Extra
The Music Visualizer is a program developed in Java using the Processing library. Its purpose is to create a visual representation of music by analyzing the audio for beats and generating visual effects accordingly. The program utilizes the Minim library for audio playback, beat detection, and Fast Fourier Transform (FFT) analysis. The visual effect consists of rotating squares in each corner of the screen, and particles are emitted when beats are detected in the music.

# Instructions
## Main Menu

## Ceren's Visual
1.	The program requires the Minim library to be imported.
2.	The CerensSongVisualizer class extends the PApplet class from the Processing library.
3.	The program sets the size of the display window in the settings() method.
4.	In the setup() method, the program initializes the Minim library, creates an array of particles, loads an audio file, sets the color mode, and converts the audio buffer to an array.
5.	The draw() method is called repeatedly and updates and displays the particles, draws the audio waveform, and rotates the star shape.
6.	The drawWaveform() method draws the audio waveform graph using the beginShape() and vertex() functions.
7.	The rotateStar() method translates and rotates the star shape based on the audio amplitude and draws it using the drawStar() and drawStarShape() methods.
8.	The Particle class defines the properties and behavior of each particle.
9.	The Particle constructor initializes the particle's angle, radius, speed, size, and rotation.
10.	The update() method updates the particle's radius and handles wrapping around the screen if the radius exceeds a certain value.
11.	The display() method calculates the particle's position, hue, size, and draws an ellipse representing the particle.
12.	The main() method runs the CerensSongVisualizer program.

##Extra
Ensure you have Java and Processing installed on your system.
Import the required libraries: ddf.minim.*, ddf.minim.analysis.*, and processing.core.PApplet.
Set the size of the sketch window by calling the settings() function and specifying the desired width and height.
Implement the setup() function to initialize the program. Load the audio file using the Minim library, configure the beat detection and FFT analysis objects, and create an ArrayList to store the particles.
Implement the draw() function, which is the main loop of the program. Within this function, the audio is analyzed for beats, and the visual effect is updated and rendered accordingly. Particles are created and updated based on beat detection, and the squares in the corners of the screen are drawn and rotated.
Implement the changeVisualEffect() function to modify the design element based on the beat. This function randomly adjusts the size, speed, and background color of the visual effect.
Implement the drawVisualEffect() function to draw the squares in the four corners of the screen. Particles are also emitted from the squares when beats are detected.
Implement the Particle class to define the behavior of the particles. Each particle has position, velocity, and acceleration vectors, as well as properties such as lifespan, size, and hue. The particles are updated, displayed, and eventually removed when their lifespan reaches zero.
Finally, implement the stop() function to handle the clean-up tasks, such as closing the audio player and stopping the Minim library.



# How it works
## Ceren
1.	The program sets up the display window, initializes the Minim library, and loads the audio file.
2.	An array of particles is created, each with a random angle, radius, speed, and size.
3.	In the draw() method, the particles are updated and displayed.
4.	The audio waveform is drawn using the drawWaveform() method.
5.	The star shape is rotated based on the audio amplitude and drawn using the rotateStar(), drawStar(), and drawStarShape() methods.
6.	The Particle class defines the behavior of each particle. The update() method updates the particle's position, and the display() method draws the particle on the screen.
7.	The program continuously loops through the draw() method, creating an animated visualization of the audio.

## Extra
The Music Visualizer program utilizes audio analysis techniques to generate visual effects synchronized with the beats of the music. The audio file is loaded and played using the Minim library. Beat detection is performed to identify the beats, triggering changes in the visual effect. The design element, represented by rotating squares, is modified in size, speed, and color when a beat is detected. Additionally, particles are emitted from the squares, creating an animated effect. The particles move and fade out over time, giving a sense of motion and dynamics to the visualization. The program continuously updates the position and direction of the design element, checks for boundaries, and ensures it stays within the screen. The FFT analysis is used to obtain frequency data, which can be used for further visualizations or spectrum analysis.


# What I am most proud of in the assignment
## Ceren
In this assignment, I'm actually most proud of how comfortable I have become with using git, branches, merging, committing and collaborating. I am most proud of successfully implementing the particle system and integrating it with audio visualization. The particles move and change size based on the audio input, creating an engaging and visually appealing effect. Additionally, the waveform and rotating star shape add an extra layer of visual interest to the overall visualization

# Markdown Tutorial

This is *emphasis*

This is a bulleted list

- Item
- Item

This is a numbered list

1. Item
1. Item

This is a [hyperlink](http://bryanduggan.org)

# Headings
## Headings
#### Headings
##### Headings

This is code:

```Java
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

So is this without specifying the language:

```
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

This is an image using a relative URL:

![An image](images/p8.png)

This is an image using an absolute URL:

![A different image](https://bryanduggandotorg.files.wordpress.com/2019/02/infinite-forms-00045.png?w=595&h=&zoom=2)

This is a youtube video:

[![YouTube](http://img.youtube.com/vi/J2kHSSFA4NU/0.jpg)](https://www.youtube.com/watch?v=J2kHSSFA4NU)

This is a table:

| Heading 1 | Heading 2 |
|-----------|-----------|
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |

