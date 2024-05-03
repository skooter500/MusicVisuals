# Music Visualiser Project

Name:Seán Flood & Ralph Cruz

Student Number: C22421292 & C22427602

## Instructions
- Fork this repository and use it a starter project for your assignment
- Create a new package named your student number and put all your code in this package.
- You should start by creating a subclass of ie.tudublin.Visual
- There is an example visualiser called MyVisual in the example package
- Check out the WaveForm and AudioBandsVisual for examples of how to call the Processing functions from other classes that are not subclasses of PApplet

# Description of the assignment

# Instructions
Using keyboard keys ‘1’ and ‘2’ you can change scenes:
!['1'](https://i.imgur.com/s2JUPJh.png)
!['2'](https://i.imgur.com/7RC3WF0.png)
!['2 again'](https://i.imgur.com/lPGM8d2.png)
!['2 again again'](https://i.imgur.com/l2kReYW.png)

‘ ‘ (spacebar) pauses and plays the song
![' '](https://i.imgur.com/J8qGTCL.png)
![' ' again](https://i.imgur.com/7RC3WF0.png)

‘r’ clears the current scene just leaving the screen black with the lyrics still playing
!['r'](https://i.imgur.com/YgO8pgW.png)
!['r again'](https://i.imgur.com/dPyangf.png)

‘s’ enters “spam mode” on when scene 2 is active. Spam mode just calls the “sceneChange” function in SeanVisuals.java very fast.
Not actually screenshotable because it uses motion and screenshots are freezeframes.

# How it works
In the Main.java file we call our "Seno.java" file and create that sketch
```Java
package ie.tudublin;

public class Main {

    public void startUI() {
        String[] a = { "MAIN" };
        processing.core.PApplet.runSketch(a, new Seno()); // <-- Call Seno file here
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
    }
}
```

In the Seno.java file we then import our respective files
```Java
import c22427602.RalphVisuals;
import c22421292.SeanVisuals;

public class Seno extends Visual {
    RalphVisuals Ralph;
    private boolean drawSphere = false;
    SeanVisuals Sean;
    private boolean drawCube = false;

    public void setup() {
	Ralph = new RalphVisuals();  // Instantiate Ralph object
        Ralph.setParent(this);

        Sean = new SeanVisuals(); // Instantiate Sean object
        Sean.setParent(this);

    public void draw() {
	if (drawSphere) {
            Ralph.draw();
        }
        else if (drawCube) {
            Sean.draw(); 
        }
```

# What I am most proud of in the assignment
I am most proud of making a scene that I beleive is fun to watch and interactable in many ways which can keep the user entertained and interested. There are many objects that move based on the music which makes it visually apealing and being able to change the appearance using keyboard keys makes it interesting.

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

