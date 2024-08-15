import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class LivingRoom {
    public Vector<String> livingRoomOption;
    private boolean lookedAround;
    public boolean remoteFound;
    private boolean secretDoorOpened;
    public boolean haveMatches;
    private boolean fireplaceLit;
    public final int secretCode = 6479;
    public EntranceHall entranceHall;
    public Hallway hallway;
    private boolean fireplaceHint;
    public Scanner s = new Scanner(System.in);

    // living room constructor 
    public LivingRoom(EntranceHall entranceHall) {
        this.entranceHall = entranceHall;
        this.hallway = new Hallway(this);
        this.livingRoomOption = new Vector<>();
        this.lookedAround = false;
        this.remoteFound = false;
        this.fireplaceLit = false;
        this.fireplaceHint = false;
        this.secretDoorOpened = false;
        this.haveMatches = false;
        livingRoomOption.addElement("Return to the Entrance Hall.");
        livingRoomOption.addElement("Look around the Living Room.");
    }

    // living room starting point
    public void explore() {
        System.out.println("_______________________________");
        System.out.println("You are now in the Living Room.");
        System.out.println("_______________________________");
        System.out.println();
        int input;
        while (!entranceHall.escaped) {
            printOption();

            try{
                System.out.print("\nWhat do you want to do?: ");
                input = s.nextInt();
                s.nextLine();
                System.out.println();
                System.out.println();
                System.out.println();

                if (input == secretCode && !secretDoorOpened) {
                    System.out.println("A grinding noise fills the air as the hidden door beside the fireplace slowly creaks open.");
                    System.out.println("You've discovered a concealed exit.");
                    System.out.println("This is your chance to escape the confines of the cabin.");
                    System.out.println();
                    secretDoorOpened = true;
                    livingRoomOption.addElement("Escape through the secret door.");
                    livingRoomOption.remove("Read the piece of paper from the entrance hall.");
                    entranceHall.entranceHallOption.remove("Read the piece of paper.");
                    hallway.hallwayOption.remove("Read the piece of paper from the entrance hall.");
                    hallway.library.libraryOption.remove("Read the piece of paper from the entrance hall.");
                    hallway.bedroom.bedroomOption.remove("Read the piece of paper from the entrance hall.");
                    hallway.kitchen.kitchenOption.remove("Read the piece of paper from the entrance hall.");
                    hallway.basement.basementOption.remove("Read the piece of paper from the entrance hall.");
                    hallway.basement.laboratory.laboratoryOption.remove("Read the piece of paper from the entrance hall.");
                    livingRoomOption.remove("Examine the fireplace.");
                }
                else if(input >= 1 && input <= livingRoomOption.size()){
                    if(handleOption(input-1)){
                        return;
                    }
                }
                else{
                    System.out.println("Invalid choice. Enter number between 1 and "+ livingRoomOption.size());
                }
            }
            catch(InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                s.next();
            }
            System.out.println();
        }
    }

    // print option
    private void printOption(){
        int i = 1;
        for(String s : livingRoomOption){
            System.out.println(i + ". "+ s);
            i++;
        }
    }

    // handle option
    private boolean handleOption(int input){
        switch (livingRoomOption.elementAt(input)) {
            case "Look around the Living Room.":
                this.lookAround();
                break;
            case "Turn on the TV.":
                if (remoteFound) {
                    System.out.println("The television powers on, revealing a news anchor with a solemn expression.");
                    System.out.println();
                    System.out.println("\"Breaking news: The FBI has mobilized a large contingent to a remote, wooded area.");
                    System.out.println("Details are scarce. Authorities remain tight-lipped about the nature of the operation,");
                    System.out.println("though heightened security and restricted access are evident. Two helicopters have also been deployed to aid in the search.\"");
                    System.out.println();
                    System.out.println("The broadcast briefly shows footage of FBI agents in tactical gear moving deliberately through dense forest.");
                    System.out.println("The camera also captures the helicopters hovering above, their presence adding to the sense of urgency.");
                    System.out.println();
                    System.out.println("\"While specifics are not disclosed, the operation clearly involves substantial resources and secrecy,\" the anchor adds.");
                    System.out.println();
                    System.out.println("The screen then fades back to the studio, leaving the report incomplete and the reasons for the FBI’s presence unclear.");
                    livingRoomOption.remove("Turn on the TV.");
                } else {
                    System.out.println("You attempt to turn on the TV, but without the remote, it's futile.");
                }
            
                break;
            case "Read the piece of paper from the entrance hall.":
                    digitOfWarmth();
                break;
            case "Find the remote.":
                System.out.println("You search the room but can't find the remote. Perhaps it's in another room.");
                if(!entranceHall.entranceHallOption.contains("Find the remote.")){
                    entranceHall.entranceHallOption.addElement("Find the remote.");
                    hallway.hallwayOption.addElement("Find the remote.");
                    hallway.library.libraryOption.addElement("Find the remote.");
                    hallway.bedroom.bedroomOption.addElement("Find the remote.");
                    hallway.kitchen.kitchenOption.addElement("Find the remote.");
                    hallway.basement.basementOption.addElement("Find the remote.");
                }
                break;
            case "Read the notepad.":
                hallway.kitchen.notepadContent();
                break;
            case "Examine the fireplace.":
                if (!fireplaceLit) {
                    System.out.println("The fireplace remains cold and uninviting. You need to find a source of fire to ignite it.");
                } else {
                    System.out.println("The fire in the fireplace seems to whisper, 'Speak the digit of warmth, and I shall reveal the path to escape.'");
                    if(fireplaceHint){
                        System.out.println();
                        String choice = entranceHall.YesNo("Do you want to see a hint? (Y/N): ");
                        System.out.println();

                        if(choice.equals("Y")){
                            System.out.println("HINT: Find a specific book titled \"Fire and Flames\" in the library.");
                        }
                        else{
                            System.out.println("You decide to rely on your own instincts and memory.");
                        }
                    }
                    else{
                        fireplaceHint = true;
                    }
                }            
                break;
            case "Light up the candle.":
                entranceHall.lightCandle();
                break;
            case "Light the fireplace.":
                if (haveMatches) {
                    System.out.println("You light the fireplace. Its warm glow begins to fill the room.");
                    System.out.println("You can now examine the fireplace more closely.");
                    fireplaceLit = true;
                    livingRoomOption.remove("Light the fireplace.");
                } else {
                    System.out.println("Without matches or a lit candle, you cannot ignite the fireplace.");
                }
                break;
            case "Return to the Entrance Hall.":
                System.out.println("_________________________________");
                System.out.println("You are now in the Entrance Hall.");
                System.out.println("_________________________________");
                return true;
            case "Escape through the secret door.":
                if(secretDoorOpened) {
                    escapeThroughSecretDoor();
                }
                else{
                    System.out.println("There's no visible secret door in the room.");
                }
                break;
            case "Move to the Hallway.":
                hallway.explore();
                break;
            default:
                System.out.println("Invalid input!");
                break;
        }
        return false;
    }

    // look around living room
    private void lookAround() {
        System.out.println("The living room is dimly lit, with a large television mounted on the wall.");
        if(!remoteFound){
            System.out.println("The remote is missing; you'll need it to watch the TV.");
        }
        if(!fireplaceLit){
            System.out.println("A cold, unlit fireplace sits quietly in the corner.");
        }
        else{
            System.out.println("A lit fireplace sits quietly in the corner.");
        }
        System.out.println("There is also a passage in the living room that leads to the hallway.");

        if (!lookedAround) {
            lookedAround = true;
            livingRoomOption.addElement("Turn on the TV.");
            livingRoomOption.addElement("Find the remote.");
            livingRoomOption.addElement("Examine the fireplace.");
            livingRoomOption.addElement("Light the fireplace.");
            livingRoomOption.addElement("Move to the Hallway.");
        }
    }

    // different outcomes through escaping through secret door.
    private void escapeThroughSecretDoor(){
        String choice = entranceHall.YesNo("Do you wish to attempt an escape through the secret door? (Y/N): ");

        if(choice.equals("Y")){
            entranceHall.escaped = true;
            switch(entranceHall.wendyCondition){
                case "zombie":
                    System.out.println("You escape through the secret door, leaving Wendy behind as a zombie.");
                    System.out.println("Upon stepping outside, you find yourself surrounded by FBI agents, prepared to take you into custody.");
                    choice = entranceHall.YesNo("Will you surrender? (Y/N): ");

                    if (choice.equals("Y")) {
                        System.out.println("Having surrendered, you now face a 15-year sentence.");
                        System.out.println("You are confined to a maximum-security prison.");
                        System.out.println("The charges: illegal experimentation.");
                        System.out.println("The consequences of your actions weigh heavily as you serve your time.");
                    } else {
                        System.out.println("You refuse to surrender and run into the night, fleeing through the dense woods.");
                        System.out.println("Exhausted and desperate, you attempt to hide among the trees.");
                        System.out.println("But alas, your efforts are in vain. A police dog quickly tracks you down.");
                        System.out.println("You are captured and now locked in a maximum-security prison, under constant FBI surveillance.");
                    }
                    System.out.println();

                    if (entranceHall.cureCreated) {
                        System.out.println("The FBI discovers the cure you developed during their search of the cabin.");
                        System.out.println("They administer it to Wendy, restoring her body, though her memories are lost forever.");
                        System.out.println("Wendy is placed under FBI care, living without any recollection of her past, a mere shadow of her former self.");
                    } else {
                        System.out.println("The FBI takes Wendy into custody and attempts to prepare a cure but fails.");
                        System.out.println("They request your assistance to develop an effective cure.");
                        System.out.println("Reluctantly, you agree, driven by your love for Wendy.");
                        System.out.println("With your help, a cure is finally created and Wendy is cured.");
                        System.out.println("However, Wendy has no recollection of her past life.");
                    }
                    break;

                case "human":
                    System.out.println("You escape through the secret door, leaving Wendy behind.");
                    System.out.println("As you emerge, FBI agents surround you, prepared to arrest you.");
                    choice = entranceHall.YesNo("Will you surrender? (Y/N): ");
                
                    if (choice.equals("Y")) {
                        System.out.println("Having surrendered, you now face a 15-year sentence.");
                        System.out.println("You are confined to a maximum-security prison.");
                        System.out.println("The charges: illegal experimentation.");
                        System.out.println("The consequences of your actions weigh heavily as you serve your time.");
                    } else {
                        System.out.println("You refuse to surrender and run into the night, fleeing through the dense woods.");
                        System.out.println("Exhausted and desperate, you attempt to hide among the trees.");
                        System.out.println("But alas, your efforts are in vain. A police dog quickly tracks you down.");
                        System.out.println("You are captured and now locked in a maximum-security prison, under constant FBI surveillance.");
                    }
                    System.out.println();
                
                    System.out.println("The FBI finds Wendy, who is profoundly shaken by the ordeal.");
                    System.out.println("She is taken into protective custody and receives medical and psychological care.");
                    System.out.println("Though safe from immediate danger, the trauma leaves deep emotional scars.");
                    System.out.println("Wendy's future is uncertain as she begins the difficult process of healing and rebuilding her life.");
                    break;
                
                default:
                    System.out.println("As you step outside, you find yourself surrounded by FBI agents, ready to arrest you.");
                    choice = entranceHall.YesNo("Will you surrender? (Y/N): ");
                
                    if (choice.equals("Y")) {
                        System.out.println("Having surrendered, you are sentenced to life imprisonment.");
                        System.out.println("You are now locked in a maximum-security prison.");
                        System.out.println("The charges: illegal experimentation and the death of Wendy.");
                        System.out.println("Your freedom is a distant memory, and the consequences of your actions have caught up with you.");
                    } else {
                        System.out.println("You refuse to surrender and run into the night, fleeing through the dense woods.");
                        System.out.println("Exhausted and desperate, you attempt to hide among the trees.");
                        System.out.println("But alas, your efforts are in vain. A police dog quickly tracks you down.");
                        System.out.println("You are captured and now locked in a maximum-security prison, under constant FBI surveillance.");
                    }
                    System.out.println();
                
                    System.out.println("Wendy, now deceased, remains a tragic victim of your actions.");
                    System.out.println("The FBI is left to grapple with the aftermath of her death.");
                    System.out.println("Your choices have led to profound tragedy, leaving a legacy of sorrow and loss.");
                    break;
                
            }
        }
        else{
            System.out.println("You decided not to attempt an escape.");
        }
    }

    // fireplace code
    public void digitOfWarmth(){
        System.out.println("You carefully scrutinize the old, crinkled piece of paper:");
        System.out.println();
        System.out.println("The paper reads: 'The digits of warmth: " + secretCode + "'");
        System.out.println("This code may hold the key to uncovering the hidden passage.");
    }
}
