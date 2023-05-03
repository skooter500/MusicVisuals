# Music Visualiser Project

C21782059 Ron Syhmon Pingol,
C21394933 Ernest Decina,
C21751999 Alex Tsang,
C21460524 Daniel Wu.

# Youtube Video

[Youtube Video of Music Visualizer Running](https://www.youtube.com/)

# Description of the assignment

This is a music visualizer that takes you the upper bounds of the universe, in the visual we explore multiple different variations of what we call "Visual Dimensions" Each Visual Dimentions shows you how it reacts to the sound in its enviroment.

## Visual Dimensions

Dimensions will be listed by the order they are played out

### Visual Dimension 3: Daniel Wu ()

### Visual Dimension 2: Ernest John Decina (C21394933)

#### Desc

	My visual shows the end of the universe then AI image symobilizes the take over and then through the AI image a new universe with land and terrain spawns.

#### Part 1

	The first part of my Dimension shows a black hole expanding and eating up the universe.

#### Part 2

	Then the second part of my visual shows the AI Generated image the user inputed at the start of the Application.

#### Part 3

	After the AI generated image a terrain the moves along with Bass and Trebble appears then finishing and leading into the next Dimension.

### Visual Dimension 4: Alex Tsang ()

### Desc
	My visualizer is based off the Rose Pattern used in mathematics. 
	
### Part 1 
	
	My part consists of a rotating flower pattern has colors changing on each petal throughout the duration of the song, it also zooms in and out.

### Visual Dimension 1: Ron Pingol (C21782059)

### Desc
	
	My visual consists of multiple shapes that make up multiple rings then transitioning into a cube filled dimesnion, looking like the tesseract area in the movie 	Interstellar
	
### Part 1

	Multiple coloured shapes will be moving and they will be rotating on a ring.
	
### Part 2

	My Second visual, will transition from the multicoloured shaped. Then lots and lots (like lots), of cubes will appear and when they rotate their colours will change.

# Instructions to work Visualizer

## Step 1

	Make sure to be on a connected network that has a DNS that doesnt block the following domain name: ernestjohndecina.com

## Step 2

	Run the project using F5 (With Debugging) || CTRL + F5 (Without Debugging)

## Step 3

	Enter an APPROPRIATE prompt. Then wait for the Application to process your AI Generated Image. (Any inappropriate prompts will be met with a error and user must reinput a prompt). If it still doesnt work make sure you are connected to the internet for visualizer to create a AI Image

# How it works

	After the user finished Entring a prompt and the proccessing is successful, the music visualizer will automatically play. The music visualizer also works with timings and there are automatic transitions to the music visualizer so sit back and relax and enoy the music visualizer.

# What I am most proud of in the assignment

## Ron Pingol ()

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

## Ernest John Decina (C21394933)

	I am most proud of getting an AI Generated image to render to the users screen, this was alot of learning using Oracle's java http library and making sure that a error is prompt to the user if there was any problems.

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

## Daniel Wu (C21460524)

             There was lots of things that i was proud of in this assignment but the thing that i was most proud of was getting the sphere to split into four seperate sphere and adding the screen shake. I am also proud that i got the spheres to rotate around the center.

## Alex Tsang ()
		The thing I am most proud of in this assignment is learning how to use the Rose(mathematics) pattern and implementing it into code. Learning processing was hard and implementing this pattern took a lot guidance. I am also really happy on having the colors change on each petal.


```
            // Use sin() and cos() functions to smoothly interpolate between colors
            float timeFactor = 0.00005f * pApplet.millis(); // Adjust this factor to control the speed of color change
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


```

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
