package BuilderDesignPattern.client;

import BuilderDesignPattern.entities.Computer;

public class Driver {

    public static void main(String[] args) {

        //Using builder to get the object in a single line of code and
        //without any inconsistent state or arguments management issues
        Computer myComp1 = new Computer.ComputerBuilder("100GB", "16GB",
                "Intel Core i5").setTouchscreen(true).setManufacturer("HP")
                .setBluetooth(true).build();

        Computer myComp2 = new Computer.ComputerBuilder("500GB", "8GB",
                "Intel Core i10").setTouchscreen(true).setManufacturer("Dell")
                .setBluetooth(true).build();

        // note that the builder pattern provides ability to create multiple products
        System.out.println(myComp1.getHDD() + " disk, " + myComp1.getRAM() + " memory, " + myComp1.getCPU() +
                " processor, " + myComp1.getManufacturer() + " manufactured, " + myComp1.isBluetoothEnabled() +
                " bluetooth, " + myComp1.isTouchscreen() + " touchscreen.");
        System.out.println(myComp2.getHDD() + " disk, " + myComp2.getRAM() + " memory, " + myComp2.getCPU() +
                " processor, " + myComp2.getManufacturer() + " manufactured, " + myComp2.isBluetoothEnabled() +
                " bluetooth, " + myComp2.isTouchscreen() + " touchscreen.");
    }
}
