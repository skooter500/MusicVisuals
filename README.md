# Music Visualiser Project

Name: Ryan Keogh

Student Number: C19444404

## Instructions
- Fork this repository and use it a starter project for your assignment
- Create a new package named your student number and put all your code in this package.
- You should start by creating a subclass of ie.tudublin.Visual
- There is an example visualiser called MyVisual in the example package
- Check out the WaveForm and AudioBandsVisual for examples of how to call the Processing functions from other classes that are not subclasses of PApplet

# Description of the assignment
For my assignment I decided to try and implement as much as I could of what Bryan has taught us. From experiencing him first hand teaching us the art of audio visuals with java I found myself intrigued as someone who has a strong passion for music. I decided to choose my favourite song 'Live Forever' by my favourit band 'Oasis'. I tried to get creative for the project and implement some of Bryans ideas but also some of my own creations that I tried to brainstorm. Although some may be random I feel like that is what is special about this project, there may be grading for code, but in terms of design and creativity there is no right or wrong, just whatever the students can conjure up in our minds. Of course I had troubles and stressful times doing this project but I also had a great time doing it. There is also no better feeling than finally getting something youve been trying at for ages to finally compile. I hope you enjoy.
# Instructions
To use my assignment the user is displayed with a screen. The user must then push the space bar to start the music and then can scroll through all of my visuals using the numbers 1-7.

# How it works
I used a lot of polymorphism to complete my assignment. I incorporated a separate vision class to allow all of my files to be accessed in the the RyansVisuals file. This allows me to simple call each file from that file instead of having to clump up all of my code into one switch in the RyansVisual file. This incorpotates neatness and also was great help in identifying visuals which had errors. The project links my visuals to an audio file and reacts based on things such as the audio buffer size, the amplitude and the sample size of the audio in my attempt to create as smooth an experience for the viewer. I tried my best to have all my visuals different and unique.
# What I am most proud of in the assignment
I am most proud of my used of polymorphism in this assignment. At times I can be a slow learner and polymorphism was something that i was struggling to get my head around for some time. But thanks to Bryans help and a lot of my own research I can now say that i can completely understand what i was originally doing wrong in my code. So i am very happy that i managed to learn a great deal in my assignment as well as creating cool audio visuals.
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

