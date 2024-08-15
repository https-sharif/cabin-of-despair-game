import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class EntranceHall {
    LivingRoom livingRoom;
    public  boolean escaped = false;
    public boolean haveCoin;
    private boolean lookedAround;
    private boolean lightTurnedOn;
    private boolean safeHint;
    private boolean exitDoorHint;
    public boolean cureCreated;
    public Vector<String> entranceHallOption;
    public String wendyCondition;
    public boolean emerald;
    public boolean ruby;
    public boolean sapphire;
    public boolean gemPlaced;
    private static final String safeLock = "llrlr";
    public static final String correctCode = "96403";
    public Scanner s = new Scanner(System.in);

    // entrance hall constructor
    public EntranceHall(){
        livingRoom = new LivingRoom(this);
        this.lookedAround = false;
        this.haveCoin = false;
        this.cureCreated = false;
        this.exitDoorHint = false;
        this.safeHint = false;
        this.ruby = false;
        this.emerald = false;
        this.sapphire = false;
        this.gemPlaced = false;
        this.wendyCondition = "zombie";
        this.entranceHallOption = new Vector<>();
        entranceHallOption.addElement("Look around the entrance hall.");
        entranceHallOption.addElement("Turn on the light.");
    }

    // entrance hall starting point
    public void explore(){
        System.out.println("You find yourself in the dark Entrance Hall, having just woken up with no memory of how you got here.");
        System.out.println("With your mind foggy and disoriented, you are determined to find a way to escape.");

        System.out.println();
        int input;
        while(!escaped){
            printOption();

            try{
                System.out.print("\nWhat do you want to do?: ");
                input = s.nextInt();
                s.nextLine();
                System.out.println();
                System.out.println();
                System.out.println();
                if(input >= 1 && input <= entranceHallOption.size()){
                    handleOption(input-1);
                }
                else{
                    System.out.println("Invalid choice. Enter number between 1 and "+ entranceHallOption.size());
                }
            }
            catch(InputMismatchException e){
                System.out.println("Invalid input. Please enter a number.");
                s.nextLine();
            }
            System.out.println();
            if(escaped && !wendyCondition.equals("zombie")){
                concludeStory();
            }
        }
    }
    // print option
    private void printOption(){
        int i = 1;
        for(String s : entranceHallOption){
            System.out.println(i + ". "+ s);
            i++;
        }
    }

    // handle option
    private void handleOption(int input){
        switch (entranceHallOption.elementAt(input)) {
            case "Turn on the light.":
                entranceHallOption.remove("Turn on the light.");
                System.out.println("The chandelier lights up, illuminating the hall. As the light turns on, a letter falls from the chandelier.");
                System.out.println("You can now look around the hall.");
                entranceHallOption.addElement("Pick up the letter.");
                lightTurnedOn = true;
                break;
            case "Look around the entrance hall.": 
                this.lookAround();
                break;
            case "Examine the painting.":
                examinePainting();
                entranceHallOption.remove("Examine the painting.");
                break;
            case "Open the safe.":
                System.out.print("The safe is an old metal box with a row of five levers. Each lever can be turned either left or right.\n\nEnter the safe's combination using 'L' for left and 'R' for right: ");
                String combination = s.next();
                s.nextLine();
                System.out.println();
                if(combination.equalsIgnoreCase(safeLock)){
                    System.out.println("The safe has been unlocked. You have found a copper coin and a piece of paper.");
                    entranceHallOption.addElement("Read the piece of paper.");
                    livingRoom.livingRoomOption.addElement("Read the piece of paper from the entrance hall.");
                    livingRoom.hallway.hallwayOption.addElement("Read the piece of paper from the entrance hall.");
                    livingRoom.hallway.bedroom.bedroomOption.addElement("Read the piece of paper from the entrance hall.");
                    livingRoom.hallway.kitchen.kitchenOption.addElement("Read the piece of paper from the entrance hall.");
                    livingRoom.hallway.basement.basementOption.addElement("Read the piece of paper from the entrance hall.");
                    this.haveCoin = true;
                    entranceHallOption.remove("Open the safe.");
                }
                else {
                    System.out.println("The combination failed. The answer lies hidden somewhere in the cabin.");
                    if(safeHint){
                        System.out.println();
                        String choice = YesNo("Do you want to see a hint? (Y/N): ");
                        if (choice.equalsIgnoreCase("Y")) {
                            System.out.println("HINT: Consider searching the wardrobe in the bedroom for anything useful.");
                        } else {
                            System.out.println("You decide to rely on your own instincts and memory.");
                        }
                    }
                    else{
                        safeHint = true;
                    }
                }
                break;
            case "Pick up the letter.":
                System.out.println("You have retrieved the letter. You may now read its contents.");
                entranceHallOption.addElement("Read the letter.");
                entranceHallOption.remove("Pick up the letter.");
                entranceHallOption.addElement("Dispose of the letter.");
                break;
            case "Dispose of the letter.":
                entranceHallOption.remove("Read the letter.");
                entranceHallOption.remove("Dispose of the letter.");
                System.out.println("You have discarded the letter.");
                break;
            case "Read the notepad.":
                livingRoom.hallway.kitchen.notepadContent();
                break;
            case "Read the piece of paper.":
                livingRoom.digitOfWarmth();
                break;
            case "Read the letter.":
                System.out.println("Dear Denver,");
                System.out.println();
                System.out.println("As I write this, I find myself reflecting on the conversations we had so many years ago about unlocking");
                System.out.println("human potential through scientific experimentation. I remember how passionate I was about the");
                System.out.println("possibilities, and how firmly you opposed the idea, deeming it inhumane and illegal. At the time, I was");
                System.out.println("disappointed by your refusal to join me, but now I realize it was your wisdom and foresight that guided us both.");
                System.out.println();
                System.out.println("In the years since, I chose not to pursue those experiments, and I am grateful for your influence.");
                System.out.println("Who knows what consequences might have arisen had we crossed that line? I am relieved we never found out.");
                System.out.println("As I face my final days, I am filled with a sense of peace, knowing that we chose the right path.");
                System.out.println("Yet, I feel an overwhelming desire to see you one last time. Our friendship has meant the world to me,");
                System.out.println("and there are things I wish to say in person.");
                System.out.println();
                System.out.println("If you can, please visit me at Meadowview General Hospital. It would bring me great comfort to see you again.");
                System.out.println("Thank you, Denver, for being the voice of reason and a true friend.");
                System.out.println();
                System.out.println("With gratitude and warm regards,");
                System.out.println("Dr. Colin Hatman");
                System.out.println();
                System.out.println();
                break;
            case "Move to the living room.":
                livingRoom.explore();
                break;
            case "Find the remote.":
                System.out.println("You search the hall but can't find the remote. Perhaps it's in another room.");
                break;
            case "Light up the candle.":
                lightCandle();
                break;
            case "Open the exit door.":
                openExitDoor();
                break;
            case "Escape.":
                escape();
                break;
            default:
                System.out.println("Invalid input!");
                break;
        }
    }

    // look around entrance hall
    public void lookAround(){
        if (!lightTurnedOn) {
            System.out.println("The hall is shrouded in darkness. You must turn on the light first.");
            return;
        }
        System.out.println("You survey the entrance hall.");
        System.out.println("Above, the grand chandelier hangs ominously.");
        System.out.println("To the left, a painting catches your eye, its presence unsettling.");
        System.out.println("Behind you, a grand oak door looms, its surface adorned with intricate carvings.");
        System.out.println("Three empty slots on the door await the insertion of an emerald, ruby, and sapphire, hidden somewhere in the cabin.");
        System.out.println("Beneath the slots, a code lock remains, its secret sequence needed to unlock the path forward.");
        

        if(!lookedAround){
            entranceHallOption.addElement("Examine the painting.");
            entranceHallOption.addElement("Move to the living room.");
            entranceHallOption.addElement("Open the exit door.");
            this.lookedAround = true;
        }
    }

    // examine painting 
    public void examinePainting(){
        System.out.println("You scrutinize the painting and uncover a hidden safe concealed behind it.");
        System.out.println("The safe has five levers arranged in a column. Each lever can be turned left or right.");
        entranceHallOption.addElement("Open the safe.");
    }
    
    // function to for repetitive yes or no input 
    public String YesNo(String prompt) {
        String choice;
        do {
            System.out.print(prompt);
            choice = s.nextLine().trim().toUpperCase();
            
            if (!choice.equals("Y") && !choice.equals("N")) {
                System.out.println("Invalid input. Please enter 'Y' for Yes or 'N' for No.");
            }
            System.out.println();
        } while (!choice.equals("Y") && !choice.equals("N"));

        return choice;
    }
    


    //  open the exit door
    private void openExitDoor(){
        if (!emerald || !ruby || !sapphire) {
            System.out.println("You attempt to escape, but the door remains unyielding.");
            System.out.println("A voice echoes ominously: 'You must gather all the gems to unlock this path.'");
            System.out.println("You realize you still need to locate the missing gems before you can move forward.");
            return;
        } else if (!gemPlaced) {
            System.out.println("With all the gems collected, you place each one into its designated slot on the door.");
            System.out.println("As the final gem is set, a golden glow envelops the door, casting a haunting light across the room.");
            gemPlaced = true;
        }
        System.out.println("A panel on the door activates, urging you to enter the secret code sequence to escape.");        
        System.out.println();

        System.out.print("Enter the secret code sequence: ");
        String enteredCode = s.next();
        s.nextLine();
        if (enteredCode.equals(correctCode)) {
            System.out.println("You enter the correct code, and with a satisfying click, the door unlocks and swings open.");
            entranceHallOption.addElement("Escape.");
            entranceHallOption.remove("Open the exit door.");
        } else {
            System.out.println("The code is incorrect. The door remains firmly locked, thwarting your escape.");
            System.out.println("You must uncover the correct code to continue your attempt at freedom.");
            if(exitDoorHint){
                String choice = YesNo("Do you want to see a hint? (Y/N): ");
                if (choice.equalsIgnoreCase("Y")) {
                    System.out.println("HINT: A memory surfaces, hinting that the code to the exit door is hidden in the laboratory's drawer.");
                } else {
                    System.out.println("You decide to rely on your own instincts and memory.");
                }
            }
            else{
                exitDoorHint = true;
            }
        }

    }

    // Escape
    private void escape(){
        if (wendyCondition.equals("zombie")) {
            System.out.println("A chilling sound echoes behind you. Wendy, now a fully-fledged zombie, has escaped and begun spreading the infection.");
            System.out.println("As you step outside, you realize the horror is beyond control. The onset of the zombie apocalypse spells doom for humanity.");
            System.out.println("Your brief taste of freedom is overshadowed by the impending global catastrophe. By 2050, all of humanity will have perished.");
        } else if (wendyCondition.equals("human")) {
            System.out.println("You open the door and carefully help Wendy outside.");
            System.out.println("Though frail and still recovering, Wendy is safe for now.");
            System.out.println("Together, you escape, prepared to confront whatever challenges lie ahead.");
            System.out.println("Your journey continues, carrying the hope for a fresh start despite the trials endured.");
        } else if (wendyCondition.equals("dead")) {
            System.out.println("You manage to escape, but your reprieve is short-lived.");
            System.out.println("Seven months later, the FBI descends upon your home in Greenwood.");
            System.out.println("You are taken into custody for Wendy’s murder and your illegal experiments.");
            System.out.println("The weight of your actions finally catches up with you as you face the consequences of your transgressions.");
        } else {
            System.out.println("The code is accepted. The door creaks open, revealing a path to freedom.");
            System.out.println("As you step out of the cabin, a fleeting sense of liberation washes over you, momentarily easing the burden of your past deeds.");
        }
        s.close();
        escaped = true;
    }

    // conclusion
    public void concludeStory() {
        try {
            Thread.sleep(500);
            System.out.print(". ");
            Thread.sleep(500);
            System.out.print(". ");
            Thread.sleep(500);
            System.out.print(". ");
            Thread.sleep(500);
            System.out.print(". ");
            Thread.sleep(500);
            System.out.print(". ");
            Thread.sleep(500);
            System.out.println(". ");
        } catch (InterruptedException e) {
            System.out.print(".");
            System.out.print(".");
            System.out.print(".");
            System.out.print(".");
            System.out.println(".");
            // e.printStackTrace();
        }
        System.out.println();
        System.out.println("As Dr. Denver Blood comes to terms with his situation, he believes the nightmare is over.");
        System.out.println("Yet, he realizes too late that his son, John, was bitten by Wendy during the chaos.");
        System.out.println("Weeks later, John has fully transformed into a zombie and is now on the loose, spreading the infection.");
        System.out.println();
        System.out.println("The nightmare continues, with a new terror emerging from the shadows.");
        System.out.println();
        System.out.println("To be continued in the next journey...");
        System.out.println();
    }

    // light up the candle
    public void lightCandle(){
        if(!livingRoom.haveMatches){
            System.out.println("You need to find the matches first to light up the candle.");
        }
        else{
            System.out.println("You light the candle, its feeble glow casting flickering shadows and offering a fleeting warmth.");
            livingRoom.hallway.candleLitUp = true;
            entranceHallOption.remove("Light up the candle.");
            livingRoom.livingRoomOption.remove("Light up the candle.");
            livingRoom.hallway.hallwayOption.remove("Light up the candle.");
            livingRoom.hallway.basement.basementOption.remove("Light up the candle.");
            livingRoom.hallway.library.libraryOption.remove("Light up the candle.");
            livingRoom.hallway.kitchen.kitchenOption.remove("Light up the candle.");
            livingRoom.hallway.bedroom.bedroomOption.remove("Light up the candle.");
        }
    }

    
}
