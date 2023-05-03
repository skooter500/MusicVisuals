# Project Title

Name:
	Ron Syhmon Pingol,
	Ernest Decina,
	Alex Tsang,
	Daniel Wu.

Student Number:
	C21782059,
	C21394933,
	C21751999,
	C21460524.
	

Class Group: TU858

Include all team members names and student numbers if a team project

# Description

## Video:

(https://www.youtube.com/watch?v=zinRCbnw260&ab_channel=ErnestJohnDecina)

## Screenshots

### Ron Screenshots:
	
![](https://github.com/ronpingol/MusicVisuals/blob/master/Images/RonVisualOne.jpg)
![](https://github.com/ronpingol/MusicVisuals/blob/master/Images/RonVisualTwo.jpg)


### Daniel (C21460524) Screenshots:

![](https://github.com/Dan21460/MusicVisuals/blob/master/Images/dan1.png)
![](https://github.com/Dan21460/MusicVisuals/blob/master/Images/dan2.png)
![](https://github.com/Dan21460/MusicVisuals/blob/master/Images/dan3.png)

### Alex Screenshots: 

![](https://github.com/Entroshock/MusicVisuals/blob/master/Images/alexvisual1.png)
![](https://github.com/Entroshock/MusicVisuals/blob/master/Images/alexvisual2.png)

## Ernest (C21394933) Screenshots:
![](https://github.com/ErnestDecina/MusicVisuals/blob/master/Images/ErnestVisualOne.png)
![](https://github.com/ErnestDecina/MusicVisuals/blob/master/Images/ErnestVisualTwo.png)
![](https://github.com/ErnestDecina/MusicVisuals/blob/master/Images/ErnestVisualThree.png)


# Instructions to work Visualizer

## Step 1

	Make sure to be on a connected network that has a DNS that doesnt block the following domain name: ernestjohndecina.com

## Step 2

	Run the project using F5 (With Debugging) || CTRL + F5 (Without Debugging)

## Step 3

	Enter an APPROPRIATE prompt. Then wait for the Application to process your AI Generated Image. (Any inappropriate prompts will be met with a error and user must reinput a prompt). If it still doesnt work make sure you are connected to the internet for visualizer to create a AI Image

# How it works
![](https://raw.githubusercontent.com/ErnestDecina/MusicVisuals/master/Images/HowItWork.jpg)

## Overall functionality
We first run the program in main and it initializes the MusicVisual 
and it then has a counter that determines which visual will render.

### StartMenuVisual (Ernest John Decina: C21394933)
This is the menu where the user will choose their AI Generated image.
It uses a textbox and get a text user input and a button to submit.
This will then send the prompt to a API server that processes the AI Image.


### Visual 3 (Daniel Wu: C21460524)

##### drawExample
This code draws a visual scene consisting of an earthquake effect, starfield, circle, particle system, and screen flash effect, while preserving the original transformation and style settings in Processing

##### drawCircle
This code draws a rotating, pulsating, and color-changing circle consisting of one large and four smaller circles, with the size and rotation speed influenced by audio input, while also adding particles and explosions based on the amplitude of the audio.#

##### drawStarfield
This code creates a starfield by drawing glowing stars of varying sizes that move to the right, with the stars wrapping around the screen width.

##### drawEarthquakeEffect
This code simulates an earthquake effect by translating the screen based on audio amplitude, creating a shaking movement when the amplitude exceeds a specified measurements.

##### createExplosion
This code uses the Particle class to use the particles to create an explosion effect

##### Particle + Update Particle
This code defines a particle system with each particle having a position, velocity, lifespan, and color, while the updateParticles function updates their positions, displays them, and removes the dead ones.


### Visual 2 (Ernest John Decina: C21394933)
#### BigBangUniverse
This shows a visual of a blackhole expanding and eating the universe, This first renders a 2d array with positions for the stars in the background

Then renders a circle that keeps increasing by a counter.

The Rings around the blackhole was rendered with a circle in a for loop and translating it in the 3d space

#### DisplayAIGenerateImage
This visuals renders the AI generated image using the pixels of the images with circles and increasing in resolution over time.

#### FlyingThroughSound
This visual renders points and vertexs then applies bass to the bottom terrain and trebble to the top terrain so it moves up and down.


### Visual 4 (Alex Tsang: )
For my visualizer; what I did was use math to create a rose pattern. By using sin and cos functions I was able to create the petals of the flower and also I was able to use it to give my petal lines dynamic colour. The mathematic equations using trigonometry function and polar coordinates gave this project life.


### Visual 1 (Ron Pingol: )

This class, it got a constructor, you know, that takes in some parameters, like a PApplet object (the core thing in processing), an AudioBuffer object (the audio data), an FFT object (some analysis thingy called Fast Fourier Transform), and also the window size.
So, like, the render() method is where the real action happens, you know. It sets the background color to black and turns on the lights. Then, it calls the drawShapeField() method five times, each with a different translation to create these cool fields of spheres in different spots on the screen.
Now, the drawShapeField() method, that's the one responsible for drawing the spheres, man. First thing it does is it sets up this array of colors for the spheres. Then, it starts spinning the spheres around, you know, rotating them on the X, Y, and Z axes using the angle variable.
The audio data, it gets processed using the FFT analysis, you know. That's where the magic happens. We figure out the size of each frequency band based on the audio data we got.
Oh, and by the way, we update the angle and colorAngle variables based on the audio data, you know, using this thing called linear interpolation to make it look smoother and stuff.
Alright, so we loop through some angles, like from 0 to 360, you know, in steps of 3. For each angle, we calculate the X and Y positions of the sphere, making it move in a circle with a radius of r. The Z position is always zero, man.
We also figure out the size of the sphere based on the value of the corresponding frequency band. And we throw in some Y offset to make it bounce up and down, you know, based on the rotation angle.
The color of the sphere, it changes depending on the angle. We set the fill color accordingly, man.
And finally, we draw the sphere or box at the calculated position and size, you know. Oh, and we use this pushMatrix() and popMatrix() thing to make sure the transformations we do to one sphere don't mess up the others, man.

# List of classes/assets

| Class/asset | Source | Author |
|-----------|-----------|--------------|
| Galaxy.java | Self written | Daniel Wu: C21460524 |
| Galaxy2.java | Self written From [reference]() | Daniel Wu: C21460524  |
| AudioHm.java | Self written | Ron Pingol: C21782059 |
| SquaresSpace.java | Self written | Ron Pingol: C21782059  |
| HanaSaku.java | Modified from [Coding train](https://thecodingtrain.com/challenges/55-mathematical-rose-patterns) | Alex Tsang: C21751999|
| AIGenerationMenu.java | Self written | Ernest John Decina: C21394933|
| BigBangUniverse.java | Self written | Ernest John Decina: C21394933|
| DisplayAIGeneratedImage.java | Self written | Ernest John Decina: C21394933|
| FlyingThroughSound.java | Self written | Ernest John Decina: C21394933|
| TEXTBOX.java | Modified from [mitkonikov](https://github.com/mitkonikov/Processing/tree/master/Text_Box) | Ernest John Decina: C21394933|
| SubmitButton.java | Self written | Ernest John Decina: C21394933|
| DownloadAIGeneratedImage.java | Self written | Ernest John Decina: C21394933|
| PostOpenAPI.java | Self written | Ernest John Decina: C21394933|
| background-image.jpg | Refrenced From [Your Name Movie](https://www.imdb.com/title/tt5311514/) | Film Director: Mokoto Shinkai|



Each team member or individual needs to write a paragraph or two explaining what they contributed to the project

- What they did
- What they are most proud of
- What they learned

## Visual Dimension 4: Alex Tsang (C21751999)

### Desc
	My visual consists of a rose shape pattern with added petals because of my own preference. 
	
### Part 1
	
	The rose shape petals will constantly change colour and size will change depending on the amplitude and frequency.
	
## Learning Outcomes
	
### What I did
	For my visualizer; what I did was use math to create a rose pattern. By using sin and cos functions I was able to create the petals of the flower and also I was able to use it to give my petal lines dynamic colour. The mathematic equations using trigonometry function and polar coordinates gave this project life.
	
```Java	
		
	pApplet.rotate(PApplet.radians(rot));
        for(float i = 0; i < PApplet.TWO_PI; i+= 0.0004f){
            float color = PApplet.map(i, 0, PApplet.TWO_PI, 0, 255);
            float colorTwo = PApplet.map(i, 0, PApplet.TWO_PI, 0, 255);
            float colorThree = PApplet.map(i, 0, PApplet.TWO_PI, 0, 255);

            // Use sin() and cos() functions to smoothly interpolate between colors
            float timeFactor = 0.00005f * pApplet.millis(); // Adjust this factor to control the speed of color change every miilisecond
            color += 255 + 255 * PApplet.sin(timeFactor);
            colorTwo += 255 + 255 * PApplet.cos(timeFactor);
            colorThree += 255 + 255 * PApplet.sin(timeFactor + PApplet.radians(120));

            // Keep the colors within the range of 0 to 255
            color = color % 255;
            colorTwo = colorTwo % 255;
            colorThree = colorThree % 255;

            pApplet.stroke(color, colorTwo, colorThree);
            
            //outer petals aka dots
            float r = 100 * PApplet.cos(6*i);
            float x = r * PApplet.cos(i);
            float y = r * PApplet.sin(i);
    
            pApplet.point(50 * (x * lerpedAverage), 50 *(y * lerpedAverage)); //2nd petal formation
            
            
            // first 4 petals
            r = 100 * PApplet.cos(4 * i);
            x = r * PApplet.cos(i);
            y = r * PApplet.sin(i);
            pApplet.point(50 * (x * lerpedAverage), 50 *(y * lerpedAverage)); 
        }
    
        rot += 0.75;
        pApplet.popMatrix();
    }
    
```

### What I am most proud of
	I am most proud of being able to implement the dynamic colors on the petal lines, making the flower more attractive to look at. I think implementing the math functions was hard as well but I had guidance for that due to the tutorial.
	
### What I learned
	I learned how OOP can be implemented for bigger projects with multiple people. Through the usage of different classes and objects I learned how polymorphism and things like abstract classes can work in projects with multiple people. I also learned how processing works and how you can draw beautiful things with code. In the future I would like to challenge myself and draw something from scratch, though I think it will be a huge challenge. Learning minim and its methods were also very cool.

## Visual Dimension 1: Ron Pingol (C21782059)

### Desc
	
	My visual consists of multiple shapes that make up multiple rings then transitioning into a cube filled dimesnion, looking like the tesseract area in the movie 	Interstellar
	
### Part 1

	Multiple coloured shapes will be moving and they will be rotating on a ring.
	
### Part 2

	My Second visual, will transition from the multicoloured shaped. Then lots and lots (like lots), of cubes will appear and when they rotate their colours will change.

### Part 3

	This was a difficult assignment as i have never done processing before and learning these things all in one semester was a very tough task. i am proud of drawing my shapes, it made me learn alot of new methods and has opened my eyes to the more art aspect of coding.
	
```    private void drawCubeField() {
        pApplet.rotateX(angle / 30.0f);
        pApplet.rotateY(angle * 1.3f / 30.0f);
        pApplet.rotateZ(angle * 0.7f / 30.0f);
        pApplet.noStroke();
        fft.forward(audioBuffer);
        for (int i = 0; i < numBands; i++) {
            bandSize[i] = fft.getBand(i) * 5;
        }
        angle += PApplet.map(bandSize[0], 0, 100, 0.01f, 0.1f);
        colorAngle += PApplet.map(bandSize[1], 0, 100, 0.001f, 0.01f);
        for (int i = -200; i < 200; i += 20) {
            for (int j = -200; j < 200; j += 20) {
                for (int k = -200; k < 200; k += 20) {
                    float x = i;
                    float y = j;
                    float z = k;
                    float size = PApplet.map(bandSize[(i + 200) / 20], 0, 255, 0, 50);
                    float yOffset = pApplet.noise(i * noiseScale, j * noiseScale, k * noiseScale) * noiseStrength;
                    // Assign a different color for each cube based on its position
                    int cubeColor = pApplet.color(PApplet.map(i + colorAngle, 0, 512, 0, 255),
                            PApplet.map(j + colorAngle, 0, 512, 0, 255),
                            PApplet.map(k + colorAngle, 0, 512, 0, 255));
                    pApplet.fill(cubeColor);
                    pApplet.pushMatrix();
                    pApplet.translate(x, y + yOffset, z);
                    pApplet.box(size);
                    pApplet.popMatrix();
                }
            }
        }
    }
```

## Visual Dimension 3: Daniel Wu (C21460524)

### Desc
	
	In my visual inspired by red giant phase of the sun it first shows a big Sun that is going through its giant phase, it then splits into 4 different Sun's and then it shows the universe collapsing
        and shaking and showing lots of chaos.
	
### Part 1

	The first part of my visual shows the Sun slowly growing according to the amplitude of the music.
	
### Part 2

	The second part of my visual shows the Sun split into 4 smaller suns rotating around the center of the screen.
	
### Part 3

	The last part of my visual shows the screen shaking and explosions happening according to amplitude.
	
### What i am Proud of

      There was lots of things that i was proud of in this assignment but the thing that i was most proud of was getting the sphere to split into four seperate sphere and adding the screen shake. I am also proud that i got the spheres to rotate around the center.
      
### What i learned
      
        I learned a lot of things while doing this assignement but the things i learned the most was using the sin and cos libraries to help put detail on the sphere. Most importantly i learned how to work with a group to get work completed on time and to a respectable standard.

```  private void drawCircle() {
        float x = windowWidth / 2;
        float y = windowHeight / 2;
        float amplitude = audioBuffer.level() * 1000;
        float diameter = (initialDiameter + amplitude + (timeElapsed * timeScale)) / 2;
        // Map the amplitude value to a hue value
        pApplet.colorMode(PConstants.HSB, 360, 100, 100);
        float hue = PApplet.map(amplitude, 0, 200, 0, 360);
        // Calculate the opacity based on a sine wave
        float opacity = PApplet.map(PApplet.sin(pApplet.frameCount * 0.05f), -1, 1, 50, 255);
        for (int k = 0; k < 4; k++) {
            float offsetX = PApplet.cos(rotationAngle) * diameter * 1.5f;
            float offsetY = PApplet.sin(rotationAngle) * diameter * 1.5f;
            pApplet.pushMatrix();
            pApplet.pushStyle();
            pApplet.translate(x + offsetX, y + offsetY, -diameter / 2);
            pApplet.noStroke();
            float adjustedRotationSpeed = PApplet.map(amplitude, 0, 200, rotationSpeed, rotationSpeed * 10);
            rotationAngle += adjustedRotationSpeed;
            pApplet.fill(hue, 100, 100, opacity); // Use the calculated opacity value
            pApplet.rotate(rotationAngle, 1, 1, 0);
            pApplet.beginShape(PConstants.TRIANGLES);
            int detail = 100;
            for (int j = 0; j < detail; j++) {
                float theta1 = PApplet.map(j, 0, detail, 0, PConstants.TWO_PI);
                float theta2 = PApplet.map(j + 1, 0, detail, 0, PConstants.TWO_PI);
                for (int i = 0; i < detail; i++) {
                    float phi1 = PApplet.map(i, 0, detail, 0, PConstants.PI);
                    float phi2 = PApplet.map(i + 1, 0, detail, 0, PConstants.PI);
                    float x1 = diameter * PApplet.sin(phi1) * PApplet.cos(theta1);
                    float y1 = diameter * PApplet.sin(phi1) * PApplet.sin(theta1);
                    float z1 = diameter * PApplet.cos(phi1);
                    float x2 = diameter * PApplet.sin(phi1) * PApplet.cos(theta2);
                    float y2 = diameter * PApplet.sin(phi1) * PApplet.sin(theta2);
                    float z2 = diameter * PApplet.cos(phi1);
                    float x3 = diameter * PApplet.sin(phi2) * PApplet.cos(theta2);
                    float y3 = diameter * PApplet.sin(phi2) * PApplet.sin(theta2);
                    float z3 = diameter * PApplet.cos(phi2);
                    pApplet.vertex(x1, y1, z1);
                    pApplet.vertex(x2, y2, z2);
                    pApplet.vertex(x3, y3, z3);
                    pApplet.vertex(x1, y1, z1);
                    pApplet.vertex(x3, y3, z3);
                    pApplet.vertex(x2, y2, z2);
                }
            }
            pApplet.endShape();
            if (amplitude > 20) {
                particles.add(new Particle(new PVector(x + offsetX, y + offsetY), hue));
                if (amplitude > 220) {
                    createExplosion(x + offsetX, y + offsetY, hue, 100);
                }
            }
            pApplet.popMatrix();
            pApplet.popStyle();
            rotationAngle += PConstants.PI / 2; // Divide the full circle into 4 equal parts for each circle
        }
        pApplet.noStroke();
        // Increment time elapsed
        timeElapsed += 1;
    }
    
 ```

 ## Visual Dimension 2: Ernest John Decina (C21394933)

### Part 1

	Blackhole eating the universe and eating the stars around it.
	
### Part 2

    An AI taking over from the enventual death of the universe and producing images from the past civilations of human kind.
	
### Part 3

	The last visual shows the Terrain slowly forming again over time after the take over of the AI and represents the circle of life.

### What im most proud of?
	I am most proud of getting an AI Generated image to render to the users screen, this was alot of learning using Oracle's java http library and making sure that a error is prompt to the user if there was any problems.

### What I learned
    I learned how to create POST requests using java and getting information from an API server and then processing that data else where.

```java
 String link= "";
 String payload = String.format("{\"prompt\": \"%s\"}", prompt);
 try(CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
	HttpPost request = new HttpPost("https://api.ernestjohndecina.com/api/dalle/image");
	StringEntity params = new StringEntity(payload, ContentType.APPLICATION_JSON);
	request.addHeader("content-type", "application/json");
	
	request.setEntity(params);
	ClassicHttpResponse response = (ClassicHttpResponse) httpClient.execute(request);
	System.out.println(response);
	String json = EntityUtils.toString(response.getEntity());
	JSONObject responseJson = new JSONObject(json);
	String data = responseJson.getString("link");
	link = data;
 } catch(Exception e) {
	return null;
 }
 return link;
```

# References
* ["Something Comforting" - Porter Robinson](https://music.youtube.com/watch?v=9uT-dsBBZew&feature=share)
* [TEXTBOX.java](https://github.com/mitkonikov/Processing/tree/master/Text_Box)
* [Your Name Background Image](https://www.imdb.com/title/tt5311514/)
