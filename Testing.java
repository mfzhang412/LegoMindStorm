import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.Sound;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.MotorPort;
import lejos.nxt.*;

import lejos.robotics.objectdetection.*;

import java.lang.*;

/**
 * Write a description of class Testing here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Testing
{
    NXTRegulatedMotor rightWheel = new NXTRegulatedMotor(MotorPort.C);
    NXTRegulatedMotor leftWheel = new NXTRegulatedMotor(MotorPort.B);
    
    public static void main(String[] args) throws Exception
    {
        Testing car = new Testing();
        int angle = 1;
        boolean con = car.scan();
        while (con == false)
        {
            car.turning(angle);
            con = car.scan();
        }
        boolean black = car.lightScan();
        while (black == false)
        {
            car.forward();
            black = car.lightScan();
        }
        // gets out of box
       
    }
    
    /**
     * turn to right is positive angle
     * turn to left is negative angle
     */
    public void turning(int angle)
    {
        if (angle > 0)
        {
            rightWheel.rotate(-angle/2);
            leftWheel.rotate(angle/2);
        }
        else
        {
            rightWheel.rotate(angle/2);
            leftWheel.rotate(-angle/2);
        }
    }
    
    /**
     * distance in meters
     * positive is forward
     * negative is backwards
     */
    public void forward()
    {
            rightWheel.setSpeed(1440);
            leftWheel.setSpeed(1440);
        
            rightWheel.forward();
            leftWheel.forward();
            /*rightWheel.wait((new Double(Math.abs(distance) / .45)).longValue());
            leftWheel.wait((new Double(Math.abs(distance) / .45)).longValue());
            rightWheel.stop();
            leftWheel.stop();*/
        
    }
   
    public boolean scan()
    {
        int MAX_DISTANCE = 30;
        int PERIOD = 1;
        UltrasonicSensor us = new UltrasonicSensor(SensorPort.S4);
        FeatureDetector fd = new RangeFeatureDetector(us, MAX_DISTANCE, PERIOD);
        Feature result = fd.scan();
        if (result != null){
            return true;
        }
        return false;
    }
    
    public boolean lightScan()
    {
        LightSensor light = new LightSensor(SensorPort.S3);
        int value = light.getLightValue();
        if (value < 35 && value > 25)
        {
            return true;
        }
        return false;
    }
}
