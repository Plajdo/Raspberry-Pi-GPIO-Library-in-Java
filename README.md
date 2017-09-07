# Raspberry-Pi-GPIO-Library-in-Java
Raspberry Pi GPIO Library for Java to control Pi's GPIO pins

<h2>Code examples</h2>

Turn a pin on:
```java
import es.esy.playdotv.gpiolib.GPIO;
import es.esy.playdotv.gpiolib.Mode;

GPIO g18 = new GPIO(18, Mode.OUT);
g18.gpioON();
```
That's it.

To turn the pin back off just use
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
<h2>Download</h2>
[Click here to download][a]


[a]: https://github.com/Plajdo/Raspberry-Pi-GPIO-Library-in-Java/releases
