import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class Kitchen {
    public Hallway hallway;
    public Vector<String> kitchenOption;
    private boolean lookedAround;
    public boolean vialFound;
    public boolean haveKnife;
    public Scanner s = new Scanner(System.in);

    // kitchen constructor
    public Kitchen(Hallway hallway){
        this.hallway = hallway;
        this.lookedAround = false;
        this.vialFound = false;
        this.haveKnife = false;
        this.kitchenOption = new Vector<String>();
        kitchenOption.addElement("Return to the Hallway.");
        kitchenOption.addElement("Look around the Kitchen.");
    }

    // kitchen starting point
    public void explore(){
        System.out.println("___________________________");
        System.out.println("You are now in the Kitchen.");
        System.out.println("___________________________");
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
                if(input < 1 || input > kitchenOption.size()){
                    System.out.println("Invalid choice. Enter number between 1 and "+ kitchenOption.size());
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
        for(String s : kitchenOption){
            System.out.println(i + ". "+ s);
            i++;
        }
    }

    // handle option
    private boolean handleOption(int input){
        switch (kitchenOption.elementAt(input)) {
            case "Return to the Hallway.":
                System.out.println("___________________________");
                System.out.println("You are now in the Hallway.");
                System.out.println("___________________________");
                return true;
            case "Look around the Kitchen.":
                lookAround();
                break;
            case "Search the refrigerator.":
                searchRefrigerator();
                break;
            case "Look through the cabinets.":
                lookThroughCabinets();
                break;
            case "Check the countertop.":
                checkCountertop();
                break;
            case "Find the remote.":
                System.out.println("The remote is unlikely to be found in the kitchen; it seems out of place here.");
                break;
            case "Light up the candle.":
                hallway.livingRoom.entranceHall.lightCandle();
                break;
            case "Read the piece of paper from the entrance hall.":
                hallway.livingRoom.digitOfWarmth();
                break;
            case "Read the notepad.":
                notepadContent();
                break;
            default:
                System.out.println("Invalid input!");
                break;
        }
        return false;
    }

    // look around the kitchen
    private void lookAround(){
        System.out.println("The kitchen is dimly lit, with a single bulb flickering overhead.");
        System.out.println("Dust covers the countertops, and the air carries a faint smell of stale food.");
        System.out.println("The cabinets are slightly ajar, revealing old pots and pans.");
        System.out.println("Cobwebs hang in the corners, suggesting that the kitchen hasn't been used for some time.");

        if(!lookedAround){
            lookedAround = true;
            kitchenOption.addElement("Search the refrigerator.");
            kitchenOption.addElement("Look through the cabinets.");
            kitchenOption.addElement("Check the countertop.");
        }
    }

    // search the refrigerator
    private void searchRefrigerator(){
        System.out.println("You open the refrigerator and find only a few expired food items, covered in frost.");
        if (!vialFound) {
            System.out.println("Among the frost-covered items, you notice a small, mysterious green vial.");
            System.out.println("Its contents swirl intriguingly, and you wonder what purpose it serves.");
            System.out.println("You carefully pick up the vial.");
            vialFound = true;
        } else {
            System.out.println("Nothing of interest here, just a reminder of the past.");
        }
    }

    // check the countertop
    private void checkCountertop(){
        System.out.println("You examine the countertop, cluttered with dusty dishes and cutlery.");
        if(!hallway.livingRoom.haveMatches){
            System.out.println("Among the mess, you spot a box of matches, which might come in handy.");
            System.out.println("The box of matches could be useful for lighting a candle or other purposes.");
            System.out.println("You also discover a notepad and decide to pick it up.");
            hallway.livingRoom.haveMatches = true;
            kitchenOption.addElement("Read the notepad.");
            hallway.hallwayOption.addElement("Read the notepad.");
            hallway.livingRoom.livingRoomOption.addElement("Read the notepad.");
            hallway.bedroom.bedroomOption.addElement("Read the notepad.");
            hallway.basement.basementOption.addElement("Read the notepad.");
            hallway.basement.laboratory.laboratoryOption.addElement("Read the notepad.");
            hallway.livingRoom.entranceHall.entranceHallOption.addElement("Read the notepad.");
            hallway.library.libraryOption.addElement("Read the notepad.");
        }
        else{
            System.out.println("There is nothing else of interest.");
        }
    }

    // look through the cabinets
    private void lookThroughCabinets(){
        System.out.println("You search through the cabinets, finding mismatched crockery and utensils.");
        if (!haveKnife) {
            System.out.println("In one drawer, you discover some old receipts and a knife.");
            System.out.println("You pick up the knife.");
            haveKnife = true;
        } else {
            System.out.println("In one drawer, you find some old receipts.");
            System.out.println("Nothing else of interest here.");
        }
    }

    // cure recipe / notepad contents
    public void notepadContent(){
        System.out.println("You pick up the notepad and begin reading the recipe for the cure.");
        System.out.println();
        System.out.println("**Zombie Infection Cure Recipe**");
        System.out.println("---------------------------------");
        System.out.println("Ingredients:");
        System.out.println("- 1 green vial ('BioRegen Compound')");
        System.out.println("- Trace of copper");
        System.out.println("- Dash of purified water");
        System.out.println("- Pinch of graphene powder");
        System.out.println("- Few drops of concentrated enzyme extract");
        System.out.println("- Sample of blood from the infected.");
        System.out.println();
        System.out.println("Instructions:");
        System.out.println("1. Combine the 'BioRegen Compound' with purified water in a sterile container.");
        System.out.println("2. Add the copper trace and stir gently until dissolved.");
        System.out.println("3. Introduce the graphene powder and enzyme extract.");
        System.out.println("4. Carefully add the sample of blood from the infected.");
        System.out.println("5. Mix thoroughly to ensure even distribution of components.");
        System.out.println("6. Allow the mixture sit for 2 hours in a cool, dark place.");
        System.out.println();
        System.out.println("Note: Handle with extreme caution. This compound is experimental and may not be fully stable.");
    }
}
