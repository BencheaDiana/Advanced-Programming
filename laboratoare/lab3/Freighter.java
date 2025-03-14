package lab3;

public class Freighter extends Aircraft implements CargoCapable {
	 private double maxPayload;
	 
	 public Freighter(String model, String name, double wingspan, double maxPayload) {
	     super(model, name, wingspan);
	     this.maxPayload = maxPayload;
	 }
	 
	 @Override
	 public double getMaxPayload() {
	     return maxPayload;
	 }
	 
	 @Override
	 public void setMaxPayload(double maxPayload) {
	     this.maxPayload = maxPayload;
	 }
	 
	 @Override
	 public String toString() {
	     return "Freighter{" +"model='" + getModel() + '\'' +", name='" + getName() + '\'' + ", wingspan=" + getWingspan() + ", maxPayload=" + maxPayload +'}';
	 }
	}
