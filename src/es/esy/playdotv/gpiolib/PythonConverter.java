package es.esy.playdotv.gpiolib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PythonConverter {
	
	private static final String eol = System.getProperty("line.separator");
	
	static File getName(int gpioID, Function func, Mode gpioMode, PUD gpioPud, Edge gpioEdge){
		if(func == Function.VERSION){
			return new File("python_code/piversion.py");
		}else if(func == Function.CLEANUP){
			return new File("python_code/gpiocleanup.py");
		}else if(func == Function.NORMAL_OUTPUT_ON){
			return new File("python_code/no_on_" + Integer.toString(gpioID) + ".py");
		}else if(func == Function.NORMAL_OUTPUT_OFF){
			return new File("python_code/no_off_" + Integer.toString(gpioID) + ".py");
		}else if(func == Function.NORMAL_INPUT){
			if(gpioPud == PUD.DOWN){
				return new File("python_code/ni_" + Integer.toString(gpioID) + "_" + "pd.py");
			}else if(gpioPud == PUD.UP){
				return new File("python_code/ni_" + Integer.toString(gpioID) + "_" + "pup.py");
			}
			
		}
		return new File("");
		
	}
	
	static File generateFile(int gpioID, Function func, Mode gpioMode, PUD gpioPud, Edge gpioEdge){
		if(func == Function.VERSION){
			String textToSave = "import RPi.GPIO as GPIO" + eol + "GPIO.setmode(GPIO.BCM)" + eol + "GPIO.VERSION";
			saveFile(textToSave, getName(gpioID, func, gpioMode, gpioPud, gpioEdge));
		}else if(func == Function.CLEANUP){
			String textToSave = "import RPi.GPIO as GPIO" + eol + "GPIO.setmode(GPIO.BCM)" + eol + "GPIO.cleanup()";
			saveFile(textToSave, getName(gpioID, func, gpioMode, gpioPud, gpioEdge));
		}else if(func == Function.NORMAL_OUTPUT_ON){
			String textToSave = "import RPi.GPIO as GPIO" + eol + "GPIO.setmode(GPIO.BCM)" + eol + "GPIO.setup(" + Integer.toString(gpioID) + ", GPIO.OUT)" + eol + "GPIO.output(" + Integer.toString(gpioID) + ", 1)";
			saveFile(textToSave, getName(gpioID, func, gpioMode, gpioPud, gpioEdge));
		}else if(func == Function.NORMAL_OUTPUT_OFF){
			String textToSave = "import RPi.GPIO as GPIO" + eol + "GPIO.setmode(GPIO.BCM)" + eol + "GPIO.setup(" + Integer.toString(gpioID) + ", GPIO.OUT)" + eol + "GPIO.output(" + Integer.toString(gpioID) + ", 0)";
			saveFile(textToSave, getName(gpioID, func, gpioMode, gpioPud, gpioEdge));
		}else if(func == Function.NORMAL_INPUT){
			if(gpioPud == PUD.DOWN){
				String textToSave = "import RPi.GPIO as GPIO" + eol + "import sys" + eol + "GPIO.setmode(GPIO.BCM)" + eol + "GPIO.setup(" + Integer.toString(gpioID) + ", GPIO.IN, pull_up_down = GPIO.PUD_DOWN)" + eol + "print(GPIO.input(" + Integer.toString(gpioID) + "))" + eol + "sys.stdout.flush()";
				saveFile(textToSave, getName(gpioID, func, gpioMode, gpioPud, gpioEdge));
			}else if(gpioPud == PUD.UP){
				String textToSave = "import RPi.GPIO as GPIO" + eol + "import sys" + eol + "GPIO.setmode(GPIO.BCM)" + eol + "GPIO.setup(" + Integer.toString(gpioID) + ", GPIO.IN, pull_up_down = GPIO.PUD_UP)" + eol + "print(GPIO.input(" + Integer.toString(gpioID) + "))" + eol + "sys.stdout.flush()";
				saveFile(textToSave, getName(gpioID, func, gpioMode, gpioPud, gpioEdge));
			}
			
		}
		
		return new File("");
	}
	
	private static void saveFile(String text, File fileName){
		
		new File("python_code").mkdir();
		try(BufferedWriter out = new BufferedWriter(new FileWriter(fileName));){
			out.write(text);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
