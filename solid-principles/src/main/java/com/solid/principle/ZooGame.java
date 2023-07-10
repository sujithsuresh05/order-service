package com.solid.principle;

public class ZooGame {

    // Modeling various animals

    /**
     *
     * Design an Animal
     *  concepts -> class ( blueprint/ idea)
     *
     * class Animal {
     *     // attribute [ properties ]
     *     String color;
     *     String gender;
     *     String species;
     *     Integer age;
     *     Double weight;
     *     Boolean hasWings;
     *     Boolean canBreathUnderwater;
     *
     *
     *     // behaviour
     *
     *     void eat();
     *     void run();
     *     void swim();
     *     void attack();
     * }
     *
     * Different animals will behave differently
     *
     * // incomplete concepts - Abstraction
     * // Java - Abstract class
     * // Python - from abc import ABC @abstract
     * C# - abstract class
     * Typescript - Interfaces
     * c++ - pure virtual methods
     *
     *
     * S ->
     * class Animal {
     *   String species;
     *   String color;
     *
     *   abstrct void run();
     * }
     *
     * class Reptile extends Animal {
     *     void run() {
     *         print("I'm a reptile - I ain't got  legs - I can only crawls");
     *     }
     * }
     *
     * class Bird extends Animal {
     *     void run() {
     *         print("I'm a reptile - I ain't got  legs - I can only crawls");
     *     }
     * }
     *
     *  class Mammal extends Animal {
     *      void run(){
     *
     *      }
     *  }
     *
     * // Readable
     * There are so many classes noe
     *   -- not really an issuer
     *      you can use metprogrammin
     *      templated / macros/ generics
     *
     *      as a developer you will NEVER  have to read all the files at the same time
     *
     *      -- every single file is itself is very readable
     *
     *
     *  - Testable
     *
     *  No: - More testable
     *
     * - Extensible
     *  Can we still add new species
     *
     *  - Maintainable
     *
     *
     *  [Library] SimpleZooLibrary
     *  abstract class Animal {}
     *
     *  class Bird extends Animal {
     *      void fly() {
     *          if(species == "Sparrow") {
     *              print("fly low")
     *          }
     *          else if (species == "Eagle") {
     *              print("glide high")
     *          }
     *      }
     *  }
     *
     *  Extensible - FOCUS!
     *
     *  As the client we don't have the access the library
     *
     *  here come the Open closed principle
     *  -- even people who don't have acess to your code , it should be closed for modification
     *
     *  ? Why is modification bad?
     *  typical dev cycle for a new feature
     *   dev spend hours to write code. Test it locally. Finally submit a Pull Request
     *   Team will review and ask you tyo make changes/improvements
     *   QA team - write new tests, integration tests
     *   - Deployment
     *     + Staging server
     *     + Canary deployment / AB deployments
     *       * deployed to 5% of the user base
     *         - are there new exceptions
     *         - are the people complaining
     *         - have the rating gone down
     *       * finally deploy the code
     *
     *
     *
     *
     *  [Library] SimpleZooLibrary
     *  abstract class Animal {}
     *
     *  abstract class Bird extends Animal {
     *      void fly();
     *
     *    Class Sparrow extends Bird {
     *          void fly() {
     *              print("fly low")
                        *          }
     *          }
     *   Class Eagle extends Bird {
     *          void fly() {
     *              print("glide high")
     *              }
     *      }
     *  }
     *
     *
     *   - Extension
     *   The client can extend the code and ass new functionality without having to modify
     *
     *   The fix was - remove if-else ladder and convert into inheritence
     *   SRP === O?C ?
     *   NO. The solution was the same, but the intent was different
     *   All the SOLID principle are tightly linked to each other
     *
     *
     *   class Kiwi extends Bird {
     *       void fly() {
     *           throw new FlightLessBirdException("Kiwi can't fly");
     *       }
     *   }
     *
     *   This will violate expectations
     *
     *   class MyAwesomeZooGame {
     *       Bird getBirdFromSelection() {
     *           if(usereSelection == "Sparrow") {
     *
     *           }
     *           else if ( userSelection == "Parrot") {
     *               Parrot p = new Parrot();
     *               return p;
     *           }
     *           //other cases
     *
     *       }
     *
     *       void main() {
     *           Bird b = getBirdFromSelection();
     *           b.fly();
     *       }
     *   }
     *
     *   --> existing code breaks with out touching
     *
     *   Liskov's Substitution Principle
     *
     *  - Any functionality in the
     *
     *   We understand that all birds can fly
     *   So let's make a distinction. let us Not have the fly() method inside Bird
     *
     *   abstract class Animal{}
     *
     *   abstract class Bird extends Animal {
     *       abstract void eat();
     *       abstract void poop();
     *   }
     *
     *   interface IcanFly {
     *       void fly();
     *   }
     *
     *   class Sparow extends Bird implments IcanFly {
     *
     *   }
     *   class Parrot extends Bird implments IcanFly {
     *
     *   }
     *
     *
     *   class MyAwesomeZooGame {
     *       IcanFly getBirdFromSelection() {
     *           if(usereSelection == "Sparrow") {
     *
     *           }
     *           else if ( userSelection == "Parrot") {
     *               Parrot p = new Parrot();
     *               return p;
     *           }
     *           //other cases
     *
     *       }
     *
     *       void main() {
     *           Bird b = getBirdFromSelection();
     *           b.fly();
     *       }
     *   }
     *
     *
     *   What should you anticipate ?
     *
     *   - changes in requirements
     *   - databse migrations / adding new columns in tables / adding new indexes /
     *   optimizing certain queries / sharding the database
     *
     *   - specilizing a particular class
     *    + user
     *    + free / paid/ premium user
     *
     *
     *    Low level design
     *    OOP
     *    solid principle
     *    design pattern
     *      + Singleton
     *      + Builder
     *        * language specific
     *        * Python / Javascript
     *      + Factory
     *    Database Schema Design
     *      + Idexes
     *      + Normalize
     *      + Optimize queries
     *   ER-diagrams / class diagram
     *   REST API design
     *   A ton of case studies
     *    + Snake-Ladder
     *    + Chess
     *    + Parking Lot
     *  - Machine Coding round / Take home assessments
     *
     *  --------------------------------------------------------------------------
     *
     *  What language do you to know to be a developer
     *  - doesn't matter
     *  - typists / thinkers
     *    + problem solving - Algorithms & Data Structures
     *    + design - HLD / LLD/ Database
     *    + communicaition - HM rounds
     *  - know at least 1 programming language
     *     * Python, C++, Java, C#, F#, Scala
     *
     *   Effect of AI
     *
     *   short tern ( 5 years)
     *   - The barrier to entry for conding will reduce
     *     + increase your competition
     *   - hiring bar will increase
     *     + salaries also go u
     *
     *   Recession
     *
     *     - the number of jobs has decereased
     *     - the competition is higher
     *     - the salaries are also sky-high
     *
     *
     *    what do you have to do - make sure that you have in-depth understating of things
     *
     *    Superficial knowledge will no loger work
     *
     *
     *
     *   Long Term ( 10+ years)
     *   - I've absolutly no idea
     *   - If we have strong AGI, then it becomes impossible to predict the future
     *
     *
     *   abstract class Animal{}
     *
     *   abstract class Bird extends Animal {
     *       abstract void eat();
     *       abstract void poop();
     *   }
     *
     *   interface IcanFly {
     *       void fly();
     *       void spreadWings();
     *       void smalljump()
     *   }
     *
     *   class Sparow extends Bird implments IcanFly {
     *      void fly(){....}
     *   }
     *   class Parrot extends Bird implments IcanFly {
     * void fly(){....}
     *   }
     *   class Kiwi extends Bird {
     *
     *   }
     *   class Shakitiman implements IcanFly {
     *      void fly(){....}
     *      void spreadWings() { // sorry shaktiman }
     *   }
     *
     *   class MyAwesomeZooGame {
     *       IcanFly getBirdFromSelection() {
     *           if(usereSelection == "Sparrow") {
     *
     *           }
     *           else if ( userSelection == "Parrot") {
     *               Parrot p = new Parrot();
     *               return p;
     *           }
     *           //other cases
     *
     *       }
     *
     *       void main() {
     *           Bird b = getBirdFromSelection();
     *           b.fly();
     *       }
     *   }
     *
     *   // apart from birds, wha else can fly?
     *   - Kites
     *   - Aeroplanes
     *   - Drones
     *   - Abhisheks's mummy's chappel can fly
     *   - Shakitiman
     *   - Baloons
     *
     *   spreadWings() and smalljump() cann't be part of IcanFly
     *   - No code should force to implment methods that they don't need
     *
     *  Split interface
     *   interface IcanFly {
     *       void fly();
     *   }
     *
     *   interface IFliesLikeBird {
     *       void spreadWings();
     *       void smalljump()
     *   }
     *
     *   Split the large priniciple into multiple smaller, more specific interfaces
     *
     *
     *   Rules vs Guidence
     *
     *   - rules must followed
     *   - if u break them - somenthing will happen
     *     - may be u will die
     *     - go to jail
     *   - Guidelines
     *     + good to follow
     *     + it's okay to sometimes not follow the guidelines
     *     + very importent to know when & why to violate the guidelines
     *
     *   Hackathon - 2 hrs to build a running app end to end
     *
     *
     *   We've designed a bunch of animals - so now let's shift focus and look at the
     *   infrastructures of the Zoo
     *
     *   // High-level-code - abstractions
     *   // Low-level code - implmentation details ( exact code)
     *
     *   Design a Bird Cage
     *   =================
     *
     *   interface IBowls {
     *       void fill();
     *       void clean();
     *       void startMeal();
     *   }
     *
     *   class MeatBowl implements IBowl {}
     *   class FriutBowl implements IBowl {}
     *   class GrainBowl implements IBowl {}
     *
     *   interface IDoor { vod lock(){}, void unlock(){}, void resistAttack();}
     *   class WoodenDoor implements IDoor{}
     *   class IronDoor implements IDoor{}
     *   class AdamantiumDoor implements IDoor{}
     *
     *  // Controller / Manager - High level abstration
     *   class Cage1 {
     *       FriutBowl bowl = new FriutBowl("apples", "grapes");
     *       WoodenDoor woodenDoor = new WoodenDoor();
     *
     *       List<Bird> residents;
     *
     *       public void startLunch() {
     *       for(Bird b: residents) {
     *           bowl.feed(b) // delegating the task
     *       }
     *
     *       public void resistAttack(Attack attack) {
     *           door.resistAttack(attack); // delegating the task
     *       }
     *   }
     *
     *   class Cage2 {
     *       MeatBowl bowl = new MeatBowl("apples", "grapes");
     *       WoodenDoor woodenDoor = new WoodenDoor();
     *
     *       List<Bird> residents;
     *
     *       public void startLunch() {
     *       for(Cat c:  residents) {
     *           bowl.feed(b)
     *       }
     *
     *       public void resistAttack(Attack attack) {
     *           door.resistAttack(attack);
     *       }
     *   }
     *
     *   class MyAwesomeZooGame {
     *       void main() {
     *
     *       }
     *   }
     *
     *   // Hight-level code class Cage1 depends on Low level details MeatBowl, Tiger and IronDoor
     *
     * - Dependency Inversion priciple
     *
     *   // Hight-level code should only depend on high level abstartction
     *   // achieve this dependency injections
     *
     *
     *   class Cage {
     *       Ibowl bowl;
     *       Idoor iDoor;
     *       public Cage(IBowl bowl, IDoor door) {
     *           this.bowl = bowl;
     *           this.door = door;
     *       }
     *       List<Bird> residents;
     *
     *       public void startLunch() {
     *       for(Animal b: residents) {
     *           bowl.feed(b) // delegating the task
     *       }
     *
     *       public void resistAttack(Attack attack) {
     *           door.resistAttack(attack); // delegating the task
     *       }
     *   }
     *
     *   Class MyAwesomeZooGame {
     *
     *     void main () {
     *     Cage birdCage = new Cage( new FruitBowl(), new WoodenDoor());
     *     Cage kittyCage = new Cage( new MeatBowl(), new IronDoor());
     *   }
     *
     */
}
