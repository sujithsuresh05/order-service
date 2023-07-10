package com.solid.principle;

public class Animal {

    // attributes [properties]
    String species;
    int numberOflegs = 9;

    // behaviour [methods]
    void run() {
        // what should I do here?

        if(species == "Cobra") {
            System.out.println("Hiss Hiss - I don't run");
        }
        else if (getCategoryOf(species)) {
            System.out.println("Let's run");
        }
        else if (numberOflegs == 4) {
            System.out.println("Go fast");
        }
    }

    private boolean getCategoryOf(String species) {
        return true;
    }


}
