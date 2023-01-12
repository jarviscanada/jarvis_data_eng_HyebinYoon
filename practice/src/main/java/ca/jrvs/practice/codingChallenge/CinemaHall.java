package ca.jrvs.practice.codingChallenge;

import java.util.*;
import java.util.Random;

public class CinemaHall {
    public static void main(String[] args) {
        Random r = new Random();
        int trials = 10000000;
        int success = 0;

        for (int t = 0; t < trials; t++) {
            boolean[] seats = new boolean[200]; // false means the seat is free, true means it's occupied
            seats[r.nextInt(200)] = true; // person 1 chooses a random seat

            int person = 2;
            while (person <= 200) {
                int seat = person - 1; // expected seat for current person
                if (seats[seat]) { // seat is not free
                    do {
                        seat = r.nextInt(200); // choose a random free seat
                    } while (seats[seat]);
                }
                seats[seat] = true; // occupy seat
                if (seat == 199 && person == 200) {
                    success++;
                }
                person++;
            }
        }

        double probability = (double) success / trials;
        System.out.println("Probability that person 200 sits in seat 200: " + probability);
    }
}
