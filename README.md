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

### Visual Dimension 1: Ron Pingol ()

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

## Daniel Wu ()

## Alex Tsang ()

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
