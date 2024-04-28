# Music Visualiser Project

Audio Name: Crazy Frog - Axel F

| Name | Student Number |
|-----------|-----------|
|Amy Ibourk | C22308773 |
|Roxana Rakhmatullina | C21374751 |
|Ciara Trani | C22787471 |
|Aleena Mattathil | C22790201 |

## Video
[![YouTube](images/AleenaVisual2.png)](https://youtu.be/r7nN0u-_N_8)

# Screenshots
### Visual 1
![An image](images/AmyVisual.png)

### Visual 2
![An image](images/RoxanaVisual1-1.png)
![An image](images/RoxanaVisual1-2.png)

### Visual 3
![An image](images/CiaraVisual1-1.png)
### Visual 4
![An image](images/AleenaVisual2.png)

### Visual 5
![An image](images/AleenaVisual1.png)

# Description of the Project
Using Processing, our team created visuals for our chosen audio Axel F by Crazy Frog. Aleena had to have 2 visuals as the music repeated and also so we all had a similar limit of time to work on. Her visual shows a swirl. Ciaras visual shows rainbow scribbles moving around.Amys visual shows a rose like flower shape and then exposes another abstract shape inside it. It expands to the beat.Roxanas visual is of a frog image that bounces up and down the screen bouncing off the wall and every time it lands on the ground it squishes. There is a rainow aura around the frog and also rainbow grass at the bottom of the screen hip hop .

# How it works
There is a switch statement with the number keys allowing us to switch between each visual. Amy's visual is 1, Roxana's is 2, Ciara's is 3 and Aleena's are 4 and 5. We also implemented the visuals automatically changing according to the timing of the song. Time stamps : 0-27s Amy, 28-42s Aleena, 43-1.10s Roxana, 1.11-1.24s Aleena, 1.24-1.50s Ciara

### AmyVisual1
In this visual, there are 3 elements:
1. There are 24 rotating cubes displayed using a for loop to create a visual in the centre of the screen. The size and colour are dependant on the amplitude of the audio. The rotation variable gives it a spinning effect as it increments by 0.1 each time render() is called (60 times per second).
1. Then there is a lines from centre visual. 30 lines are created using a for loop. The lines start from the centre and radiate outwards. The colour and length of each line is based on the amplitude.
1. The outer pattern is created by an array of squares. It begins by every square being initialised, each one being slightly bigger and rotated more than the previous. In render() it calls the method display() from the square class to  change the colour and sized depending on the almplitude and spin the squares around.

### AleenaVisual1
This visual displays 4 waveforms. 
3 waveforms make the background layer. The screen is divided into 3 sections - one for each waveform. Each section of the screen displays a waveform representing a different portion of the audio signal. The waveform color changes based on the audio amplitude, creating a dynamic visual representation of the audio input. 
The foreground layer renders a spectrum analyzer by dividing the screen into bars, each representing a frequency range, and dynamically changing their height and color based on the audio input

### AleenaVisual2
The first element in this visual are the colour changing spirals. They are rendered by calculating their size, position, and color based on the audio amplitude and drawing them using trigonometric functions
The second element features a radial burst which is rendered by drawing lines that radiate outward from the center of the screen. The brightness of the lines is determined by the amplitude of the audio signal

### RoxanaVisual1
I am also happy with how my visual turned out. Though, I had a few issues throughout the process of making it perfect, I managed to do what I aimed to do. RoxanaVisual1.java works by firstly declaring the package in this instance C21374751 student number,after import statements and class declaration it declares the fields such as ourvisual ov, images and floats variables which will be used in RoxanaVisual1 . Then I constructed the body for the frog image and grass such as positioning and moveent like the frog hopping then with render i set the background colours to fade, and make it so the frog bounces back when it reaches the walls and changes directions and change and crates the raimbow aura around the frog image by altering an ellipse , also im render i coded the squish effect and the grass to alter in height

### CiaraVisual1
The way my code works is that it iterates over the audio buffer to draw dynamic visualizations. By adjusting stroke colors, drawing ellipses based on audio amplitudes, and rotating lines around them, it creates engaging visual representations of the audio input.


# What I am most proud of in the assignment
### Amy
- Firstly, I am proud of how well our group worked together. We met up a few times to work on our project together, as well as asked eachother for help when we needed it.
- I am also happy with how my visual turned out. Though, I had a few issues throughout the process of making it perfect, I managed to do what I aimed to do.

### Aleena
- At first this assignment had me very overwhelmed. The fact that it was a group project really alienated the stress, knowing that 4 of us would be working together to achieve this common goal. I am really proud of how the 4 of us worked together - meeting up on our days off and, if we were stuck or had an issue, our communication in the groupchat where we would work together to solve one person's bug.
- While coding my own visuals, I really had a creative block and imaginitave visuals were not coming to me. I'm proud at how I tackled this obstacle. I started with basic visuals - a waveoform and a spiral, and just trialed and errorred different visuals that would add to the base visual
 
### Roxana
- I chose the song for this and im half proud of myself and half hate myself for choosing such an upbeat, classic and fun yet such an annoying somewhat repetitive song,
- I also notes the time stamps for the code which we then changed it to just have a switch case but its the thought that counts
- I am also proud of how I got the frog to squish whever it landed while hopping, I found it quite hard to do and it did have issues with it like it would stay squished the whole time or not be there at all. To fix this i had to fix the statement so that the size changed on the frogs vertical posotion.

### Ciara
- I'm most proud of how this project showcases our creative individuality. I like how different and unique each of our visuals are from each other and I think it suits the music that we've chosen. The aspect I love the most about my part is the fast movement of the shapes and lines. It goes well with the upbeat song in the background.

