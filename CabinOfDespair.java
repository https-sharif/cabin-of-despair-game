public class CabinOfDespair {
        public static void main(String[] args){
                EntranceHall entranceHall = new EntranceHall();

                printIntroduction();
                entranceHall.explore();
                printEnding();
        }

        private static void printIntroduction(){
                System.out.println("Welcome to 'Cabin of Despair'");
                System.out.println();
                System.out.println("It's the year 2035, and Dr. Denver Blood, a once-renowned scientist, is trapped in a nightmare of his own making.\n" +
                                "Driven by grief and desperation, he defied government warnings and continued forbidden experiments in his secluded cabin.\n" +
                                "He used his wife, Wendy, as a test subject, but the experiment went horribly wrong, turning her into a zombie.\n\n" +
                                "During a violent episode, Wendy attacked Dr. Blood, leaving him with a head injury and partial memory loss.\n" +
                                "Now, with the FBI closing in and his wife's fate uncertain, Dr. Blood must piece together his fractured memories\n" +
                                "to find a way out of the cabin while confronting the consequences of his actions.\n\n" +
                                "You will now play as Dr. Denver Blood. Can you unravel the secrets of the cabin and escape?\n");

        }

        private static void printEnding(){
                System.out.println("Thank you for playing the game. Your journey has come to an end.");
                System.out.println("Can you uncover all the possible endings? Try different choices to discover the various outcomes.");
                System.out.println();
        }
}
