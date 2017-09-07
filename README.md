# Raspberry-Pi-GPIO-Library-in-Java
Java library to control Raspberry Pi GPIO pins
--------------------------------------------------
Simple library to control GPIO pins.


<h2>Code examples</h2>

```java
import es.esy.playdotv.gpiolib.GPIO;
import es.esy.playdotv.gpiolib.Mode;

GPIO g18 = new GPIO(18, Mode.OUT);
g18.gpioON();
```
That's it.

To turn GPIO back off just use
```java 
g18.gpioOFF();
```
Easy.

Code for checking whether the pin is on or off:

```java
import es.esy.playdotv.gpiolib.GPIO;
import es.esy.playdotv.gpiolib.Mode;
import es.esy.playdotv.gpiolib.PUD;

GPIO g18 = new GPIO(18, Mode.IN);
if(g18.isON(PUD.UP)){
  //Your code in there
}
```
