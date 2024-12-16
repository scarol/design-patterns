package FactoryDesignPattern;

abstract class Vehicle {
    /**
     * create method as abstract to ensure that child classes HAVE TO implement this method
     */
    public abstract void createVehicle();
}

class Car extends Vehicle {

    @Override
    public void createVehicle() {
        System.out.println("Creating Car...");
    }
}

class Bike extends Vehicle {

    @Override
    public void createVehicle(){
        System.out.println("Creating Bike...");    }
}

class VehicleFactory {
   static Vehicle getVehicle(String type) {

       Vehicle vehicle = null;
       if (type.equals("car"))
           vehicle = new Car();
       else if (type.equals("bike")) {
           vehicle = new Bike();
       }
       return vehicle;
   }
}

class VehicleMain {
    public static void main(String[] args) {

        String type = args[0];
        /* non-factory method code uses hard coding for types - too tightly coupled
        Vehicle vehicle = null;
        if (type.equals("car"))
            vehicle = new Car();
        else if (type.equals("bike")) {
            vehicle = new Bike();
        }
        if (vehicle != null)
            vehicle.createVehicle();
        else System.out.println("No vehicle obj found!");
        */
        // factory method code hides the implementation details from the users
        Vehicle vehicle = VehicleFactory.getVehicle(type);
        vehicle.createVehicle();
    }
}
