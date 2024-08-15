import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class Library {
    public Hallway hallway;
    public Vector<String> libraryOption;
    private boolean lookedAround;
    private boolean readBook;
    public Scanner s = new Scanner(System.in);

    // library constructor
    public Library(Hallway hallway){
        this.hallway = hallway;
        this.lookedAround = false;
        this.readBook = false;
        this.libraryOption = new Vector<String>();
        libraryOption.addElement("Return to the Hallway.");
        libraryOption.addElement("Look around the Library.");
    }

    // library starting point
    public void explore(){
        System.out.println("___________________________");
        System.out.println("You are now in the Library.");
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
                if(input < 1 || input > libraryOption.size()){
                    System.out.println("Invalid choice. Enter number between 1 and "+ libraryOption.size());
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
        for(String s : libraryOption){
            System.out.println(i + ". "+ s);
            i++;
        }
    }

    // handle option
    private boolean handleOption(int input){
        switch (libraryOption.elementAt(input)) {
            case "Return to the Hallway.":
                System.out.println("___________________________");
                System.out.println("You are now in the Hallway.");
                System.out.println("___________________________");
                return true;
            case "Look around the Library.":
                this.lookAround();
                break;
            case "Look at the armchair.":
                this.lookAtArmchair();
                break;
            case "Find the remote.":
                System.out.println("You searched the library and found the remote hidden behind the armchair.");
                hallway.livingRoom.remoteFound = true;
                libraryOption.remove("Find the remote.");
                hallway.hallwayOption.remove("Find the remote.");
                hallway.livingRoom.livingRoomOption.remove("Find the remote.");
                hallway.bedroom.bedroomOption.remove("Find the remote.");
                hallway.kitchen.kitchenOption.remove("Find the remote.");
                hallway.basement.basementOption.remove("Find the remote.");
                hallway.livingRoom.entranceHall.entranceHallOption.remove("Find the remote.");
                break;
            case "Light up the candle.":
                hallway.livingRoom.entranceHall.lightCandle();
                break;
            case "Read the notepad.":
            hallway.kitchen.notepadContent();
                break;
            case "Read the piece of paper from the entrance hall.":
                hallway.livingRoom.digitOfWarmth();
                break;
            case "Examine the peculiar book.":
                System.out.println("You pull the peculiar book from the shelf. It's titled 'Fire and Flames.'");
                System.out.println("As you flip through the pages, you find a handwritten note tucked inside.");
                System.out.println("The note states: \"Whisper the digits of warmth by the hearth, and a passage from the heart of the home will reveal itself.\"");
                if(!readBook){
                    readBook = true;
                }
                else{
                    String choice = hallway.livingRoom.entranceHall.YesNo("Do you want to see a hint? (Y/N): ");
                    if(choice.equalsIgnoreCase("Y")){
                        System.out.println("HINT: Find the 'digit of warmth' in the entrance hall and write it when in the living room.");
                    }
                    else{
                        System.out.println("You press on and continue your adventure.");
                    }
                }
                break;
            case "Pick up the sapphire gem.":
                System.out.println("Your eyes catch a glint of blue high up on the dusty shelf.");
                System.out.println("You maneuver the 8-foot rolling ladder into position and carefully climb to reach the sapphire.");
                System.out.println("With a steady hand, you retrieve the gem, feeling its cool, smooth surface as a reward for your effort.");
                System.out.println("With the sapphire now in your possession, you're one step closer to unlocking the door's secrets.");
                hallway.livingRoom.entranceHall.sapphire = true;
                libraryOption.remove("Pick up the sapphire gem.");
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return false;
    }

    // look around the library
    private void lookAround() {
        System.out.println("The library is filled with dusty shelves, each packed with numerous books.");
        System.out.println("A soft, inviting armchair sits in the corner.");
        System.out.println("The atmosphere is quiet and still, the only sound being the faint rustle of pages.");
        System.out.println("The air is heavy with the scent of aged paper and leather.");
        if (!hallway.livingRoom.entranceHall.sapphire) {
            System.out.println("Amidst the books, you spot a glint of blue—a sapphire nestled among the dusty shelves.");
        }
        System.out.println("You also notice a peculiar book that seems out of place.");
    
        if (!lookedAround) {
            lookedAround = true;
            libraryOption.addElement("Examine the peculiar book.");
            libraryOption.addElement("Look at the armchair.");
            libraryOption.addElement("Pick up the sapphire gem.");
        }
    }

    // examine the armchair
    private void lookAtArmchair() {
        System.out.println("The armchair looks comfortable, with a folded blanket draped over it.");
        System.out.println("There's nothing of particular interest here, but it would be a good place to relax if it weren't for the circumstances.");
        libraryOption.remove("Look at the armchair.");
    }
}
