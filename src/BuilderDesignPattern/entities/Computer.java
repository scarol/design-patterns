package BuilderDesignPattern.entities;

public class Computer {

    // mandatory attributes
    private String HDD;
    private String RAM;
    private String CPU;

    // optional attributes
    private String manufacturer;
    private boolean isTouchscreen;
    private boolean isBluetoothEnabled;

    // do not expose the product constructor to client
    // instead expose the builder constructor and builder setters
    private Computer (ComputerBuilder builderObj) {
        this.HDD = builderObj.HDD;
        this.RAM = builderObj.RAM;
        this.CPU = builderObj.CPU;
        this.manufacturer = builderObj.manufacturer;
        this.isTouchscreen = builderObj.isTouchscreen;
        this.isBluetoothEnabled = builderObj.isBluetoothEnabled;
    }

    // adding Outer class getter methods for use by client
    public String getHDD() {
        return HDD;
    }
    public String getRAM() {
        return RAM;
    }
    public String getCPU() {
        return CPU;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public boolean isTouchscreen() {
        return isTouchscreen;
    }
    public boolean isBluetoothEnabled() {
        return isBluetoothEnabled;
    }


    // Builder is a static inner class that has all the attributes of the outer class. It is static
    // because the Outer class object creation is dependent on the builder object, whereas since
    // builder class is an inner class, its object creation is dependent on the Outer class
    // instance like, new OuterClass().new InnerClass(), as inner class is accessed just like any
    // other class attribute using the class instance object. To avoid this paradox, we make the
    // builder class as a static so that it can be accessed directly using the Outer class name
    // and does require its object since being static means it belongs directly to the class and
    // not to any instance of the class.
    public static class ComputerBuilder {

        // mandatory attributes
        private String HDD;
        private String RAM;
        private String CPU;

        // optional attributes
        private String manufacturer;
        private boolean isTouchscreen;
        private boolean isBluetoothEnabled;

        // mandatory attributes are part of the constructor call
        public ComputerBuilder(String hardDisk, String memory, String processor) {
            this.HDD = hardDisk;
            this.RAM = memory;
            this.CPU = processor;
        }

        // define setter methods for the optional parameters with return type as this class itself
        // to make it easy to use for the client
        public ComputerBuilder setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public ComputerBuilder setTouchscreen(boolean touchscreen) {
            this.isTouchscreen = touchscreen;
            return this;
        }

        public ComputerBuilder setBluetooth(boolean bluetooth) {
            this.isBluetoothEnabled = bluetooth;
            return this;
        }

        // define the build() method that returns the final product
        // remember that product constructor takes builder object as argument, so use 'this'
        public Computer build() {
            return new Computer(this);
        }

    }
}
