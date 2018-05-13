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

    public static void exportToTxt() {

    }


}

/*
*
*       1. Wszędzie gdzie masz komentarze w stylu: // 1. WRITE TO DAT FILE powinna być nowa metoda.
        2. Po co robisz coś takiego: Integer k = Integer.valueOf(i);? zobacz jakim typem jest i
3.      Po co klasa Garage implementuje interfejs Map? Przez to niepotrzebnie masz takie kwiatki jak metoda entrySet która zwraca nulla.

4.      Formatowanie kodu!! Puste linie, wcięcia itp...
5.      Taki catch niewiele daje: catch(IOException io) {System.out.println("IO Exception" + io.getMessage());}

1.      zamiast zapisu do takiego pliku txt mógłbyś pomyśleć o csv
2.      zmienne w Car mogą być final
3.      klasa Garage według mnie jakaś taka bez sensu - część operacji implementujących interfejs map działa poprawnie, część niepoprawnie, zastanowiłbym się czy rzeczywiście warto implementować mapę
4.      Ta metoda printGarage po pierwsze nie printuje, po drugie zwraca reprezentacje tekstową pierwszego samochodu lub nulla - zła jest albo nazwa funkcji albo implementacja, a w ogóle wywaliłbym tą metodę z tej klasy i zrobił nową odpowiedzialną za to
5. cała logika w mainie - konkretne funkcje do wydzielenia do innych metod, a najlepiej klas
6. jak już używasz serializacji, to może lepiej serializować cały garaż a nie każdy samochód osobno
7. staroświecki język, java 8 wyszła kilka lat temu ( ͡° ͜ʖ ͡°)*/