import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class Basement {
    public Hallway hallway;
    public Laboratory laboratory;
    public Vector<String> basementOption;
    private boolean labDoorUnlocked;
    private boolean lookedAround;
    public boolean haveBloodSample;

    public Scanner s = new Scanner(System.in);

    // library constructor
    public Basement(Hallway hallway){
        this.hallway = hallway;
        this.laboratory = new Laboratory(this);
        this.lookedAround = false;
        labDoorUnlocked = false;
        haveBloodSample = false;
        this.basementOption = new Vector<String>();
        basementOption.addElement("Return to the Hallway.");
        basementOption.addElement("Look around the Basement.");
    }

    // library starting point
    public void explore(){
        System.out.println("_______________________________________");
        System.out.println("You find yourself in the dark basement.");
        System.out.println("_______________________________________");
        System.out.println();
        System.out.println("The light bulb isn’t working, leaving the space shrouded in darkness.");
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
                if(input < 1 || input > basementOption.size()){
                    System.out.println("Invalid choice. Enter number between 1 and "+ basementOption.size());
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
        for(String s : basementOption){
            System.out.println(i + ". "+ s);
            i++;
        }
    }

    // handle option
    private boolean handleOption(int input){
        switch (basementOption.elementAt(input)) {
            case "Look around the Basement.":
                lookAround();
                break;
            case "Return to the Hallway.":
                System.out.println("___________________________");
                System.out.println("You are now in the Hallway.");
                System.out.println("___________________________");
                return true;
            case "Find the remote.":
                System.out.println("The remote is unlikely to be found in the basement; it feels out of place in this dark and forgotten space.");
                break;
            case "Read the notepad.":
                hallway.kitchen.notepadContent();
                break;
            case "Read the piece of paper from the entrance hall.":
                hallway.livingRoom.digitOfWarmth();
                break;
            case "Move to the Laboratory.":
                laboratory.explore();
                break;
            case "Open the Laboratory door.":
                if(hallway.haveLabKey){
                    System.out.println("The laboratory door is now unlocked. You can now explore the laboratory.");
                    basementOption.remove("Open the Laboratory door.");
                    basementOption.addElement("Move to the Laboratory.");
                }
                else{
                    System.out.println("The door to the laboratory remains locked, you must first find the key.");
                }
                break;
            case "Light up the candle.":
                hallway.livingRoom.entranceHall.lightCandle();
                break;
            case "Examine your son's body.":
                System.out.println("You kneel beside your son's lifeless body, your heart heavy with sorrow.");
                System.out.println("His once vibrant face is now pale and still, a stark contrast to the joy it used to hold.");
                System.out.println("As you gently brush a lock of hair from his forehead, a wave of memories overwhelms you.");
                System.out.println("In his hand, you find a small locket—a cherished family heirloom. It's engraved with the initials of your family and contains a small photograph.");
                System.out.println("You notice bite marks around his neck, a grim reminder of the tragedy that has befallen him.");
                System.out.println("You take a moment to hold his hand, whispering words of love and regret.");
                System.out.println("This tragic scene strengthens your resolve to uncover the truth.");
                System.out.println("You rise, carrying a renewed sense of purpose despite the deep grief.");
                break;
            case "Use the vending machine.":
                System.out.println("The vending machine is out of order, offering no solace or sustenance.");
                System.out.println("There is nothing of significance or interest here.");
                basementOption.remove("Use the vending machine.");
                break;
            case "Pick up the emerald gem.":
                System.out.println("Amidst the dust on the vending machine, the emerald gem catches your eye with a faint glimmer.");
                System.out.println("You reach up slowly and take the gem, its cool, smooth surface a stark contrast to the decay around you.");
                System.out.println("Holding the emerald, a sense of accomplishment mingles with the somber reality of your surroundings.");
                hallway.livingRoom.entranceHall.emerald = true;
                basementOption.remove("Pick up the emerald gem.");
                break;
            default:
                System.out.println("Invalid input!");
                break;
        }
        return false;
    }

    // look around the basement
    private void lookAround() {
        
        if (!hallway.candleLitUp) {
            System.out.println("The basement is shrouded in darkness.");
            System.out.println("In the absence of light, the faint outlines of objects in the basement are barely visible.");   
            System.out.println("Find a light source to help you see and explore the basement.");         
            return;
        }

        if (!lookedAround) {
            System.out.println("As you enter the basement, a strong, unpleasant odor fills the air.");
            System.out.println("The smell of decay is overpowering, making it difficult to breathe.");
            System.out.println("In the dim light from the candle, you see the lifeless body of your son lying on the ground.");
            System.out.println("His body is cold and unresponsive, a stark reminder of the tragic events that have unfolded.");
            System.out.println("Overcome with grief and anger, you begin to wail, struggling to process the devastating sight.");
            System.out.println();
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
            System.out.println("As you stare at John's body, a painful memory resurfaces. You recall the horrifying moment when Wendy, in a berserk state, attacked and killed your son.");
            System.out.println("You were then struck in the head by Wendy and struggled to keep your consciousness.");
            System.out.println("In a desperate effort, you managed to lock her in the lab.");
            System.out.println("You then tried to escape through the entrance hall, but lost consciousness and some of your memory.");
            System.out.println();
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
                System.out.print(". ");
            } catch (InterruptedException e) {
                System.out.print(".");
                System.out.print(".");
                System.out.print(".");
                System.out.print(".");
                System.out.println(".");
                // e.printStackTrace();
            }
            System.out.println();
            System.out.println("In the dim light from the candle, you notice a heavy metal door leading to what seems to be a laboratory.");
            System.out.println("The door appears to be locked, requiring a key to open it.");
            System.out.println("Nearby, an old vending machine stands against the wall. It’s dusty and has a few out-of-order signs.");
            System.out.println("On top of the vending machine, you spot an emerald gem, gleaming amidst the dust.");
            lookedAround = true;
            basementOption.addElement("Examine your son's body.");
            basementOption.addElement("Open the Laboratory door.");
            basementOption.addElement("Use the vending machine.");
            basementOption.addElement("Pick up the emerald gem.");
        } else {
            System.out.println("In the dim light, you notice a heavy metal door leading to what seems to be a laboratory.");
            if (!labDoorUnlocked) {
                System.out.println("The door appears to be locked, requiring a key or other means to open it.");
            }
            System.out.println("Your son's lifeless body lies in the center of the basement.");
            System.out.println("Nearby, an old vending machine stands against the wall. It’s dusty and has a few out-of-order signs.");
            if(!hallway.livingRoom.entranceHall.emerald){
                System.out.println("On top of the vending machine, you still see the emerald gem, resting there quietly.");
            }
        }
        
    }

}
