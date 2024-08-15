import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class Hallway {
    public LivingRoom livingRoom;
    public Library library;
    public Bedroom bedroom;
    public Kitchen kitchen;
    public Basement basement;
    private boolean lookedAround;
    public boolean candleLitUp;
    public boolean candlePicked;
    public boolean haveLabKey;

    public Vector<String> hallwayOption;

    public Scanner s = new Scanner(System.in);

    // hallway constructor
    public Hallway(LivingRoom livingRoom){
        this.livingRoom = livingRoom;
        this.library = new Library(this);
        this.bedroom = new Bedroom(this);
        this.kitchen = new Kitchen(this);
        this.basement = new Basement(this);
        this.candleLitUp = false;
        this.lookedAround = false;
        this.candlePicked = false;
        this.haveLabKey = false;
        this.hallwayOption = new Vector<>();
        hallwayOption.addElement("Return to the Living Room.");
        hallwayOption.addElement("Look around the Hallway.");
    }

    // hallway starting point
    public void explore(){
        System.out.println("_________________________________");
        System.out.println("You are now in the Hallway.");
        System.out.println("_________________________________");
        System.out.println();
        int input;
        while (!livingRoom.entranceHall.escaped) {
            printOption();

            try{
                System.out.print("\nWhat do you want to do?: ");
                input = s.nextInt();
                s.nextLine();
                System.out.println();
                System.out.println();
                System.out.println();
                if(input < 1 || input > hallwayOption.size()){
                    System.out.println("Invalid choice. Enter number between 1 and "+ hallwayOption.size());
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
        for(String s : hallwayOption){
        System.out.println(i + ". "+ s);
        i++;
        }
    }

    // handle option
    private boolean handleOption(int input){
        switch (hallwayOption.elementAt(input)) {
            case "Move to the Library.":
                library.explore();
                break;
            case "Move to the Bedroom.":
                bedroom.explore();
                break;
            case "Move to the Kitchen.":
                kitchen.explore();
                break;
            case "Move to the Basement.":
                basement.explore();
                break;
            case "Return to the Living Room.":
                System.out.println("_______________________________");
                System.out.println("You are now in the Living Room.");
                System.out.println("_______________________________");
                return true;
            case "Look around the Hallway.":
                this.lookAround();
                break;
            case "Pick up the old candle.":
                System.out.println("You pick up the candle, feeling its cool wax in your hand.");
                hallwayOption.remove("Pick up the old candle.");
                hallwayOption.addElement("Light up the candle.");
                livingRoom.livingRoomOption.addElement("Light up the candle.");
                livingRoom.entranceHall.entranceHallOption.addElement("Light up the candle.");
                library.libraryOption.addElement("Light up the candle.");
                bedroom.bedroomOption.addElement("Light up the candle.");
                kitchen.kitchenOption.addElement("Light up the candle.");
                basement.basementOption.addElement("Light up the candle.");
                break;
            case "Light up the candle.":
                livingRoom.entranceHall.lightCandle();
                break;
            case "Pick up the rusty key.":
                System.out.println("You have retrieved the rusty key.");
                System.out.println("The key bears the engraving: 'Lab'.");
                haveLabKey = true;
                hallwayOption.remove("Pick up the rusty key.");
                break;
            case "Read the notepad.":
                kitchen.notepadContent();
                break;
            case "Read the piece of paper from the entrance hall.":
                livingRoom.digitOfWarmth();
                break;
            case "Find the remote.":
                System.out.println("You search the hallway but can't find the remote. Perhaps it's in another room.");
                break;
            case "Pick up the ruby gem.":
                System.out.println("You approach the console table and see the ruby gem glinting in the dim light.");
                System.out.println("You pick up the gem, feeling its cold and smooth surface in your hand.");
                System.out.println("With the ruby in your possession, you feel one step closer to unlocking the door.");
                livingRoom.entranceHall.ruby = true;
                hallwayOption.remove("Pick up the ruby gem.");
                break;
            default:
                System.out.println("Invalid input!");
                break;
        }
        return false;
    }
    // look around hallway
    private void lookAround(){

        System.out.println("A faint light casts unsettling shadows across the walls, shrouding the room in an eerie atmosphere.");
        System.out.println("The air is cold, and a musty odor of mold hangs, revealing the cabin’s age.");
        System.out.println("To your left, old family photographs are displayed, their glass frames cracked and encrusted with dust.");
        System.out.println("Each image captures a moment of happier times, now seeming to watch you as you move.");
        String description = "On the opposite wall, a console table holds";

        if (livingRoom.entranceHall.ruby && haveLabKey && candlePicked) {
            description += " a few scattered papers.";
        } else {
            description += (livingRoom.entranceHall.ruby ? "" : " a glinting ruby gem,") +
                           (haveLabKey ? "" : " a rusty key,") + 
                           (candlePicked ? "" : " an old, unlit candle") +
                           " and a few scattered papers.";
        }
        System.out.println(description);
        System.out.println("The floorboards groan beneath your weight, their creaks echoing down the hallway.");
        System.out.println("To the right, a door opens into the library, its wood heavy with the scent of old books.");
        System.out.println("Further down the hall, a doorway to the left leads into the bedroom, its door slightly ajar.");
        System.out.println("Across from the bedroom, the kitchen stands dark, hinting at forgotten meals.");
        System.out.println("At the end of the hallway, a staircase descends into the basement, where the shadows grow deeper.");
        System.out.println("What was once a simple corridor now harbors secrets waiting to be uncovered.");


        if(!lookedAround){
            hallwayOption.addElement("Move to the Library.");
            hallwayOption.addElement("Move to the Bedroom.");
            hallwayOption.addElement("Move to the Kitchen.");
            hallwayOption.addElement("Move to the Basement.");
            hallwayOption.addElement("Pick up the old candle.");
            hallwayOption.addElement("Pick up the ruby gem.");
            hallwayOption.addElement("Pick up the rusty key.");
            lookedAround = true;
        }
    }
}
