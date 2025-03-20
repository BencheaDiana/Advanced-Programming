
public class Airliner extends Aircraft implements PassengerCapable {
	 private int maxPassengers;
	 
	 public Airliner(String model, String name, double wingspan, int maxPassengers) {
	     super(model, name, wingspan);
	     this.maxPassengers = maxPassengers;
	 }
	 
	 @Override
	 public int getMaxPassengers() {
	     return maxPassengers;
	 }
	 
	 @Override
	 public void setMaxPassengers(int maxPassengers) {
	     this.maxPassengers = maxPassengers;
	 }
	 
	 @Override
	 public String toString() {
	     return "Airliner{" +"model='" + getModel() + '\'' +", name='" + getName() + '\'' + ", wingspan=" + getWingspan() + ", maxPassengers=" + maxPassengers +'}';
		 
	 }
	}