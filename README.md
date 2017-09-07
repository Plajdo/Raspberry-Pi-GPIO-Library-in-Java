# Raspberry-Pi-GPIO-Library-in-Java
Java library to control Raspberry Pi GPIO pins
--------------------------------------------------
Simple library to control GPIO pins.

Example:

```java
import es.esy.playdotv.gpiolib.GPIO;
import es.esy.playdotv.gpiolib.Mode;
import es.esy.playdotv.gpiolib.PUD;

GPIO g18 = new GPIO(18, Mode.OUT);
g18.gpioON();```

That's it.

To turn GPIO back off just use

```java 
g18.gpioOFF();```

Easy.
