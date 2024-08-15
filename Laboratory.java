import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class Laboratory {
    public Basement basement;
    private boolean explored;
    private boolean lookedAround;
    public boolean ingredientCollected;
    private boolean ingredientHint;
    public Vector<String> laboratoryOption;

    public Scanner s = new Scanner(System.in);

    // laboratory constructor
    public Laboratory(Basement basement){
        this.basement = basement;
        this.explored = false;
        this.lookedAround = false;
        this.ingredientCollected = false;
        this.ingredientHint = false;
        laboratoryOption = new Vector<String>();
        laboratoryOption.addElement("Return to the Basement.");
        laboratoryOption.addElement("Look around the Laboratory.");

    }

    // laboratory starting point
    public void explore(){
        if(!explored){
            System.out.println("___________________________________________________________________________________________________________________________");
            System.out.println("As you enter the laboratory, the dim light reveals a grim scene.");
            System.out.println("In the center, a restrained figure stirs — it's Wendy, Denver's wife, now a zombified shadow of her former self.");
            System.out.println("Her eyes, though glazed, focus on you. She mutters weakly, 'Denver, is that you?' before starting to twitch uncontrollably.");
            System.out.println("'Why did you do it? Why did you experiment on me?' she asks, her voice faltering.\n'Where is John? Is he safe?' she inquires, confused and unaware of her actions.");
            System.out.println("Her questions trail off as she loses consciousness once more.");
            System.out.println("The room is filled with an eerie silence as Denver contemplates the consequences of his actions.");
            System.out.println("___________________________________________________________________________________________________________________________");
            System.out.println();
            System.out.println();
            System.out.println("You are now faced with a critical decision that will determine the ending:");
            System.out.println("1. Attempt to cure Wendy using the experimental compound.");
            System.out.println("2. Put her out of her misery to end her suffering.");
            System.out.println("3. Leave her and escape, accepting the uncertain fate of what may come.");
            System.out.println("Choose wisely, as each path leads to a different outcome.");
        }
        else{
            System.out.println("You are now in the Laboratory.");
        }
        System.out.println();
        int input;
        while (!basement.hallway.livingRoom.entranceHall.escaped) {
            printOption();

            try{
                System.out.print("\nWhat do you want to do?: ");
                input = s.nextInt();
                s.nextLine();
                System.out.println();
                System.out.println();
                System.out.println();
                if(input < 1 || input > laboratoryOption.size()){
                    System.out.println("Invalid choice. Enter number between 1 and "+ laboratoryOption.size());
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
        for(String s : laboratoryOption){
            System.out.println(i + ". "+ s);
            i++;
        }
    }

    // handle option
    private boolean handleOption(int input){
        switch (laboratoryOption.elementAt(input)) {
            case "Return to the Basement.":
                System.out.println("____________________________");
                System.out.println("You are now in the Basement.");
                System.out.println("____________________________");
                return true;
            case "Look around the Laboratory.":
                lookAround();
                break;
            case "Read the notepad.":
                basement.hallway.kitchen.notepadContent();
                break;
            case "Read the piece of paper from the entrance hall.":
                basement.hallway.livingRoom.digitOfWarmth();
                break;
            case "Collect all the ingredient for the cure from the shelf.":
                System.out.println("You have collected the graphene powder and the vial of enzyme extract.");
                ingredientCollected = true;
                laboratoryOption.remove("Collect all the ingredient for the cure from the shelf.");
                break;
            case "Collect a sample of Wendy's blood.":
                basement.haveBloodSample = true;
                System.out.println("You carefully approach Wendy, who lies motionless on the floor.");
                System.out.println("With steady hands, you draw a small sample of her blood, ensuring you have enough for the cure.");
                System.out.println("The sample could be crucial in creating a remedy for the zombie infection.");
                System.out.println("You place the vial securely in your bag, determined to find a way to save her.");
                laboratoryOption.remove("Collect a sample of Wendy's blood.");
                break;
            case "Create the cure.":
                if(!laboratoryOption.contains("Read the notepad.")){
                    System.out.println("You don't know how to create the cure yet. Find the recipe to proceed.");
                    break;
                }
                if(!basement.haveBloodSample || !ingredientCollected || !basement.hallway.livingRoom.entranceHall.haveCoin || !basement.hallway.kitchen.vialFound){
                    System.out.println("You haven't collected all the required items to proceed.");
                    System.out.println();
                    System.out.println("You still need to gather the following items:");
                    if (!basement.haveBloodSample) {
                        System.out.println("- A sample of Wendy's blood");
                    }
                    if (!ingredientCollected) {
                        System.out.println("- The graphene powder");
                        System.out.println("- The concentrated enzyme extract");
                    }
                    if (!basement.hallway.livingRoom.entranceHall.haveCoin) {
                        System.out.println("- A trace of copper (any copper item would suffice)");
                    }
                    if (!basement.hallway.kitchen.vialFound) {
                        System.out.println("- The green vial ('BioRegen Compound')");
                    }

                    System.out.println("Collect all the items before proceeding.");

                    if(ingredientHint){
                        System.out.println();
                        String choice = basement.hallway.livingRoom.entranceHall.YesNo("Do you want a hint about the locations of each ingredient for the cure? (Y/N): ");

                        if (choice.equalsIgnoreCase("Y")) {
                            System.out.println("|----|");
                            System.out.println("|HINT|");
                            System.out.println("|----|");
                            System.out.println();
                            if (!basement.haveBloodSample) {
                                System.out.println();
                                System.out.println("\tApproach Wendy and collect her blood.");
                            }
                            if (!ingredientCollected) {
                                System.out.println();
                                System.out.println("\tThe laboratory shelves contain the graphene powder and enzyme extract you need.");
                            }
                            if (!basement.hallway.livingRoom.entranceHall.haveCoin) {
                                System.out.println();
                                System.out.println("\tOpen the safe in the entrance hall.");
                            }
                            if (!basement.hallway.kitchen.vialFound) {
                                System.out.println();
                                System.out.println("\tInvestigate the refrigerator in the kitchen.");
                            }
                        } else {
                            System.out.println("You decide to find the ingredients on your own.");
                        }
                    }
                    else{
                        ingredientHint = true;
                    }

                }
                else{
                    System.out.println("With all the necessary items in hand, you begin crafting the cure:");
                    System.out.println("- You mix the 'BioRegen Compound' with purified water you found in the kitchen.");
                    System.out.println("- Using the knife, you grate the copper coin to obtain a trace of copper.");
                    System.out.println("- You add the enzyme extract and activated charcoal.");
                    System.out.println("- Finally, you add a drop of Wendy's blood to the mixture.");
                    System.out.println("You let the mixture sit for 2 hours, hoping it will save Wendy.");
                    System.out.println("You have successfully created the cure for the zombie infection.");
                    System.out.println("Now, decide what you want to do with it.");
                    basement.hallway.livingRoom.entranceHall.cureCreated = true;
                    laboratoryOption.remove("Create the cure.");
                    laboratoryOption.addElement("Administer the cure to Wendy.");
                    laboratoryOption.remove("Read the notepad.");
                    basement.basementOption.remove("Read the notepad.");
                    basement.hallway.hallwayOption.remove("Read the notepad.");
                    basement.hallway.library.libraryOption.remove("Read the notepad.");
                    basement.hallway.kitchen.kitchenOption.remove("Read the notepad.");
                    basement.hallway.bedroom.bedroomOption.remove("Read the notepad.");
                    basement.hallway.livingRoom.livingRoomOption.remove("Read the notepad.");
                    basement.hallway.livingRoom.entranceHall.entranceHallOption.remove("Read the notepad.");
                }
                break;
            case "End Wendy's suffering.":
                if (!basement.hallway.kitchen.haveKnife) {
                    System.out.println("You lack a weapon in your inventory to end her suffering.");
                    System.out.println("Perhaps you should find a knife or another tool before considering this option.");
                } else {
                    System.out.println("You hold the knife tightly, torn between choices that weigh heavily on your soul.");
                    System.out.println("A part of you wants to blame her for your son's death and seeks vengeance.");
                    System.out.println("Yet, another part of you is engulfed in guilt, knowing you are responsible for her becoming a zombie.");
                    System.out.println("You briefly consider ending your own life, tormented by the weight of your actions.");
                    System.out.println();
                    System.out.print("Do you want to end Wendy's suffering?: ");
                    String choice = basement.hallway.livingRoom.entranceHall.YesNo("Do you want to end Wendy's suffering?: ");
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
                        System.out.println();
                    } catch (InterruptedException e) {
                        System.out.println(".");
                        System.out.println(".");
                        System.out.println(".");
                        System.out.println(".");
                        System.out.println(".");
                        System.out.println();
                        // e.printStackTrace();
                    }
                    System.out.println();
                    if(choice.equals("Y")){
                        
                        System.out.println("After a moment of contemplation, you decide to end Wendy's suffering.");
                        System.out.println("With a heavy heart, you proceed, knowing that this is the only way to release her from this torment.");
                        System.out.println("The room is silent once again, as you quietly mourn the loss.");
                        basement.hallway.livingRoom.entranceHall.wendyCondition = "dead";
                        laboratoryOption.remove("End Wendy's suffering.");
                    }
                    else{
                        System.out.println("Overwhelmed with guilt, you decide not to end Wendy's life.");
                        System.out.println("Despite the part of you that wants revenge for your son's death,");
                        System.out.println("you realize that you are the one responsible for her transformation.");
                        System.out.println("The weight of your actions leaves you conflicted, and you can't bring yourself to harm her further.");
                    }
                }
                break;
            case "Administer the cure to Wendy.":
                    if(basement.hallway.livingRoom.entranceHall.wendyCondition.equals("dead")) {
                    System.out.println("Wendy is deceased, and the cure cannot be administered.");
                    break;
                }
                if(basement.hallway.livingRoom.entranceHall.cureCreated){
                    System.out.println("With the cure prepared, you gently administer it to Wendy.");
                    System.out.println("You carefully inject the solution into her, hoping that it will counteract the infection.");

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
                        System.out.println();
                    } catch (InterruptedException e) {
                        System.out.println(".");
                        System.out.println(".");
                        System.out.println(".");
                        System.out.println(".");
                        System.out.println(".");
                        System.out.println();
                        // e.printStackTrace();
                    }

                    System.out.println("After five hours, Wendy begins to show signs of recovery.");
                    System.out.println("Her breathing stabilizes, and her skin starts to regain some color.");
                    System.out.println("You watch with hope and relief as the cure seems to be working.");
                    System.out.println("Wendy slowly opens her eyes and looks at you; though still weak, she appears more aware.");
                    basement.hallway.livingRoom.entranceHall.wendyCondition = "human";
                    laboratoryOption.remove("Administer the cure to Wendy.");
                    laboratoryOption.remove("End Wendy's suffering.");
                }
                else{
                    System.out.println("You have not created the cure yet.");
                }
                break;
            case "Investigate the drawer.":
                System.out.println("You open the secure drawer in the lab's workbench.");
                System.out.println("Inside, you find that a code is engraved on the drawer itself.");
                System.out.println("The code reads: 96403.");            
                System.out.println("This code must be the key to escape.");
                break;
            default:
                System.out.println("Invalid input!");
                break;
        }
        return false;
    }

    // Look around the laboratory
    private void lookAround(){
        System.out.println("The laboratory is dimly lit and cluttered with the remnants of countless experiments.");
        if (basement.hallway.livingRoom.entranceHall.wendyCondition.equals("human")) {
            System.out.println("Wendy, now cured, lies on the floor.");
            System.out.println("Her transformation has been reversed, but she remains weak and unconscious.");
            System.out.println("You must decide how to proceed with her.");
        } else if (basement.hallway.livingRoom.entranceHall.wendyCondition.equals("dead")) {
            System.out.println("Wendy's lifeless body lies in the center of the laboratory.");
            System.out.println("Her condition serves as a grim reminder of the consequences of the experiments. The room is filled with a heavy silence.");
        } else {
            System.out.println("In the center of the room, Wendy lies on the ground, unconscious, showing early signs of zombie infection.");
            System.out.println("You must act quickly if you wish to cure her before it's too late, or decide to leave her to her fate.");
        }
        System.out.println("In one corner, a shelf is filled with various lab specimens and chemicals.");
        if (!ingredientCollected) {
            System.out.println("Among them, you notice a jar labeled 'Graphene Powder' and a vial of 'Enzyme Extract'—both necessary for the cure.");
        }
        System.out.println("Next to the shelf, you discover a secure drawer in the lab's workbench.");


        if(!lookedAround){
            lookedAround = true;
            laboratoryOption.addElement("Collect all the ingredient for the cure from the shelf.");
            laboratoryOption.addElement("Collect a sample of Wendy's blood.");
            laboratoryOption.addElement("Create the cure.");
            laboratoryOption.addElement("End Wendy's suffering.");
            laboratoryOption.addElement("Investigate the drawer.");
        }

    }
}
