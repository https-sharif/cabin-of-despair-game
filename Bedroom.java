import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class Bedroom {
    public Hallway hallway;
    public Vector<String> bedroomOption;
    private boolean lookedAround;
    private boolean explored;
    public Scanner s = new Scanner(System.in);

    // bedroom constructor
    public Bedroom(Hallway hallway){
        this.hallway = hallway;
        this.lookedAround = false;
        this.explored = false;
        this.bedroomOption = new Vector<String>();
        bedroomOption.addElement("Return to the Hallway.");
        bedroomOption.addElement("Look around the Bedroom.");
    }


    // bedroom starting point
    public void explore(){
        if(!explored){
            System.out.println("____________________________________________________________________________________________________");
            System.out.println("As you enter the bedroom, a sudden distant whirring of helicopter blades reaches your ears.");
            System.out.println("You pause for a moment, the sound of the helicopters adding a new layer of urgency to your mission.");
            System.out.println("After a brief moment of distraction, you don’t give it much attention and focus on the task at hand.\n");
            System.out.println("You are now in the bedroom.");
            System.out.println("____________________________________________________________________________________________________");
            explored = true;
        }
        else{
            System.out.println("___________________________");
            System.out.println("You are now in the Bedroom.");
            System.out.println("___________________________");
        }
        System.out.println();
        int input;
        while (!hallway.livingRoom.entranceHall.escaped) {
            printOption();

            try{
                System.out.print("\nWhat do you want to do?: ");
                input = s.nextInt();
                s.nextLine();
                System.out.println();
                System.out.println();
                System.out.println();
                if(input < 1 || input > bedroomOption.size()){
                    System.out.println("Invalid choice. Enter number between 1 and "+ bedroomOption.size());
                }
                else{
                    if(handleOption(input-1)){
                        return;
                    }
                }
            }
            catch(InputMismatchException e){
                System.out.println("Invalid input. Please enter a number.");
                s.next();
            }
            System.out.println();
        }
    }

    // print option
    private void printOption(){
        int i = 1;
        for(String s : bedroomOption){
            System.out.println(i + ". "+ s);
            i++;
        }
    }

    // handle option
    private boolean handleOption(int input){
        switch (bedroomOption.elementAt(input)) {
            case "Return to the Hallway.":
                System.out.println("___________________________");
                System.out.println("You are now in the Hallway.");
                System.out.println("___________________________");
                return true;
            case "Look around the Bedroom.":
                lookAround();
                break;
            case "Find the remote.":
                System.out.println("You search the bedroom but can't find the remote. Perhaps it's in another room.");
                break;
            case "Light up the candle.":
                hallway.livingRoom.entranceHall.lightCandle();
                break;
            case "Read the piece of paper from the entrance hall.":
                hallway.livingRoom.digitOfWarmth();
                break;
            case "Read the notepad.":
               hallway.kitchen.notepadContent();
                break;
            case "Investigate the wardrobe.":
                investigateWardrobe();
                break;
            case "Look at the nightstand.":
                lookAtNightstand();
                bedroomOption.remove("Look at the nightstand.");
                break;
            case "Check under the bed.":
                checkUnderBed();
                bedroomOption.remove("Check under the bed.");
                break;
            case "Open the box.":
                openBox();
                break;
            default:
                System.out.println("Invalid input!");
                break;
        }
        return false;
    }

    // look around the bedroom
    private void lookAround(){
        System.out.println("The bedroom is dimly lit, with a serene yet eerie atmosphere.");
        System.out.println("A large, comfortable bed is against one wall, covered with an old, faded quilt.");
        System.out.println("The room is cluttered with various personal items, including a nightstand with a dusty lamp.");
        System.out.println("In the corner stands an old wardrobe with its doors slightly ajar, revealing a collection of clothes.");
        System.out.println("The air is heavy with the scent of old books and a faint hint of mustiness.");

        if (!lookedAround) {
            lookedAround = true;
            bedroomOption.addElement("Investigate the wardrobe.");
            bedroomOption.addElement("Look at the nightstand.");
            bedroomOption.addElement("Check under the bed.");
        }
    }

    // look at the nightstand
    private void lookAtNightstand() {
        System.out.println("You approach the nightstand and notice a small photograph in a dusty frame.");
        System.out.println("The photograph shows the scientist, Wendy, and John, all smiling and looking happy.");
        System.out.println("It’s a poignant reminder of a happier time, with the three of them together before everything changed.");
        System.out.println("The photograph is slightly faded, but the joy captured in the image is still evident.");
    }

    // investigate the wardrobe
    private void investigateWardrobe() {
        System.out.println("You open the wardrobe and see a collection of old clothes, including some formal wear.");
        System.out.println("Dust and cobwebs cover the garments, indicating they haven’t been used in a long time.");
        System.out.println("The clothes are arranged in an odd pattern: left, left, right, left, right.");
        System.out.println("There’s nothing else of interest here.");
    }

    // check the bed
    private void checkUnderBed() {
        System.out.println("You kneel down and peer under the bed, finding it mostly covered in dust and cobwebs.");
        System.out.println("In the corner, you discover an old, forgotten box.");
        bedroomOption.addElement("Open the box.");
    }

    private void openBox(){
        System.out.println("You carefully open the dusty box and examine its contents.");
        System.out.println("Inside, you find a few old letters and photographs.");
        System.out.println("One photograph shows you with a group of colleagues.");
        System.out.println("Another picture features you with another person, with a note on the back that reads: 'With my dear friend, Colin.'");
        System.out.println("There’s nothing else of interest here.");
        bedroomOption.remove("Open the box.");
    }

}
