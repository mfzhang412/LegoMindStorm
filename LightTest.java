import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

public class LightTest {
  public static void main(String[] args) throws Exception {
    LightSensor light = new LightSensor(SensorPort.S3);

    while (true) {
      LCD.drawInt(light.getLightValue(), 4, 0, 0);
    }
  }
}