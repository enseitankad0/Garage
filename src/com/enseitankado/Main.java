package com.enseitankado;

import java.io.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {

//        The purpose of this program is to generate a number of random cars and to store them in the data structure.
//        Then the data is saved to the format .dat and .txt and then read by the program.

        Garage garage = new Garage();
        Garage newGarage = new Garage();

        String[] cars = {"Fiat", "Peugeot", "Ford", "Toyota", "Nissan", "Opel", "Saab"};
        String[] colors = {"Red", "Orange", "Yellow", "Green", "Blue", "Navy", "Violet"};
        int randomNumber;
        System.out.println("\n\n\t CREATING NEW RANDOM CARS");
        for (int i = 0; i < 16; i++) {
            Random random = new Random();
            randomNumber = random.nextInt(cars.length);

            System.out.println(i + " - " + cars[randomNumber] + " - " + colors[randomNumber]);
            garage.put(i, new Car(i, cars[randomNumber], colors[randomNumber]));
        }

        // 1.   WRITE TO DAT FILE

        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("garage.dat")))) {
            for (Car car : garage.values()) {
                locFile.writeObject(car);
            }
        }

        // 2.   READ FROM DAT FILE

        try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("garage.dat")))) {
            System.out.println("\n\n\t SAVING DATA IN garage.dat");
            boolean eof = false;
            while (!eof) {
                try {
                    Car car = (Car) locFile.readObject();
                    newGarage.put(car.getNumber(), car);
                    System.out.println(car.toString());

                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (IOException io) {
            System.out.println("IO Exception" + io.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException " + e.getMessage());
        }

        System.out.println("\n\n\t CONFIRMATION OF SUCCESFULL DATA IMPORT");
        for (Car car : newGarage.values()) {
            System.out.println(car);
        }

        // 3.   WRITE TO TXT FILE


        try (BufferedWriter locFile = new BufferedWriter(new FileWriter("garageTXT.txt"))) {
            System.out.println("\n\n\t WRITING DATA TO garageTXT.txt file");

            for (Car car : garage.values()) {


                locFile.write(car.toString() + "\n");
                System.out.println(car.toString());
            }
        }


        // 4.   READ FROM TXT FILE

        try (BufferedReader dirFile = new BufferedReader(new FileReader("garageTXT.txt"))) {
            System.out.println("\n\n\t READING DATA FROM garageTXT.txt FILE");
            String input;
            System.out.println("Importing from file");
            while ((input = dirFile.readLine()) != null) {
                String[] data = input.split(":");
                int loc = Integer.parseInt(data[0]);
                String model = data[1];
                String color = data[2];
                System.out.println(loc + ":" + model + ":" + color);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
