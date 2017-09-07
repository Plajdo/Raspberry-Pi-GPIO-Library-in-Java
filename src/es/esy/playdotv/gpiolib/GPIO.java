package es.esy.playdotv.gpiolib;

import java.io.File;
import java.lang.AutoCloseable;

public class GPIO implements AutoCloseable{

	private int pinID;
	private Mode GpioMode;
	
	/**
	 * Create new GPIO pin
	 * @param GpioID Pin number (BCM numbering)
	 * @param mode Mode enum
	 */
	public GPIO(int GpioID, Mode mode) {
		pinID = GpioID;
		GpioMode = mode;

	}
	
	/**
	 * Write version of this and Pi GPIO libraries
	 */
	public static void version(){
		System.out.println("Java GPIO Library by Plajdo.");
		System.out.println("Version 1.0");
		System.out.println("Raspberry Pi GPIO library version: " + getVersion());

	}
	
	/**
	 * Turn the pin on
	 */
	public void setON(){
		if(GpioMode == Mode.OUT){
			File pyFile = PythonConverter.getName(pinID, Function.NORMAL_OUTPUT_ON, GpioMode, null, null);
			if(!pyFile.exists()){
				PythonConverter.generateFile(pinID, Function.NORMAL_OUTPUT_ON, GpioMode, null, null);
				RunPythonCode.fromFile(pyFile);
			}else if(pyFile.exists()){
				RunPythonCode.fromFile(pyFile);
			}
		}else{
			System.err.println("You can not turn GPIO on/off when in Input mode!");
		}
	}
	
	/**
	 * Turn the pin off
	 */
	public void setOFF(){
		if(GpioMode == Mode.OUT){
			File pyFile = PythonConverter.getName(pinID, Function.NORMAL_OUTPUT_OFF, GpioMode, null, null);
			if(!pyFile.exists()){
				PythonConverter.generateFile(pinID, Function.NORMAL_OUTPUT_OFF, GpioMode, null, null);
				RunPythonCode.fromFile(pyFile);
			}else if(pyFile.exists()){
				RunPythonCode.fromFile(pyFile);
			}
		}else{
			System.err.println("You can not turn GPIO on/off when in Input mode!");
		}
		
	}
	
	/**
	 * Check if the pin is on
	 * @param p GPIO.PUD enum
	 * @return boolean whether the pin is on or not
	 */
	public boolean isON(PUD p){
		if(GpioMode == Mode.IN){
			if(p == PUD.DOWN){
				File pyFile = PythonConverter.getName(pinID, Function.NORMAL_INPUT, GpioMode, p, null);
				if(!pyFile.exists()){
					PythonConverter.generateFile(pinID, Function.NORMAL_INPUT, GpioMode, p, null);
					return RunPythonCode.fromFile(pyFile);
				}else if(pyFile.exists()){
					return RunPythonCode.fromFile(pyFile);
				}
			}else if(p == PUD.UP){
				File pyFile = PythonConverter.getName(pinID, Function.NORMAL_INPUT, GpioMode, p, null);
				if(!pyFile.exists()){
					PythonConverter.generateFile(pinID, Function.NORMAL_INPUT, GpioMode, p, null);
					return RunPythonCode.fromFile(pyFile);
				}else if(pyFile.exists()){
					return RunPythonCode.fromFile(pyFile);
				}
			}
		}else{
			System.err.println("You can not check for pin if it is in Output mode!");
		}
		return false;
	}
	
	/**
	 * Check if the pin is off
	 * @param p GPIO.PUD enum
	 * @return boolean whether the pin is off or not
	 */
	public boolean isOFF(PUD p){

		if(isON(p) == true ){
			return false;
		}else if(isON(p) == false){
			return true;
		}
		return false;
	}

	private static String getVersion(){
		File pyFile = PythonConverter.getName(-1, Function.VERSION, null, null, null);
		if(!pyFile.exists()){
			PythonConverter.generateFile(-1, Function.VERSION, null, null, null);
			RunPythonCode.fromFile(pyFile);
		}else if(pyFile.exists()){
			RunPythonCode.fromFile(pyFile);
		}
		return "";
		
	}

	@Override
	public void close() throws Exception {
		File pyFile = PythonConverter.getName(-1, Function.CLEANUP, null, null, null);
		if(!pyFile.exists()){
			PythonConverter.generateFile(-1, Function.CLEANUP, null, null, null);
			RunPythonCode.fromFile(pyFile);
		}else if(pyFile.exists()){
			RunPythonCode.fromFile(pyFile);
		}
		
	}

}
