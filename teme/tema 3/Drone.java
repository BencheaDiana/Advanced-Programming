
public class Drone extends Aircraft implements CargoCapable {
	 private double maxPayload;
	 private int batteryLife;
	 
	 public Drone(String model, String name, double wingspan, double maxPayload, int batteryLife) {
	     super(model, name, wingspan);
	     this.maxPayload = maxPayload;
	     this.batteryLife = batteryLife;
	 }
	 
	 @Override
	 public double getMaxPayload() {
	     return maxPayload;
	 }
	 
	 @Override
	 public void setMaxPayload(double maxPayload) {
	     this.maxPayload = maxPayload;
	 }
	 
	 public int getBatteryLife() {
	     return batteryLife;
	 }
	 
	 public void setBatteryLife(int batteryLife) {
	     this.batteryLife = batteryLife;
	 }
	 
	 @Override
	 public String toString() {
	     
	     return "Drone{" +"model='" + getModel() + '\'' +", name='" + getName() + '\'' + ", wingspan=" + getWingspan() + ", maxPayload=" + maxPayload + '\'' + ", batteryLife=" + batteryLife +'}';
	 }
	}