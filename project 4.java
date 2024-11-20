

	import java.util.Scanner;
	import java.util.Random;
	abstract class Session {
	    protected String name;

	    // abstract session class
	    public abstract void setName(String name);
	    public abstract String getName();
	}
	class Game extends Session {
	    private int points;
	    private int rounds;

	    public Game(String playerName, int initialPoints) {
	        this.points = initialPoints;
	        setName(playerName);  // Set player name using abstract method
	    }

	    // Implement abstract methods from Session
	
	    public void setName(String name) {
	        this.name = name;
	    }

	   
	    public String getName() {
	        return this.name;
	    }
	    abstract class Player {
	        protected int id;
	        protected int score;

	        public Player(int id, int initialScore) {
	            this.id = id;
	            this.score = initialScore;
	        }

	        public int getId() {
	            return id;
	        }

	        public int getScore() {
	            return score;
	        }

	        // Abstract method that subclasses should implement to update score
	        public abstract void updateScore(int points);
	    }
	public abstract class App {
	    public abstract void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        Random random = new Random();
	        String playerName;
	        int rounds = 0;
	        int points = 20; // Initial points for the player

	        System.out.print("\n" + 
	                        "******* Game Instructions and Rules *******\r\n" + 
	                        "When the first roll occurs, the Roll_1 dice values and sum are set.\r\n" + 
	                        "After Roll_1, the player decides if the Roll_2 sum is likely to be \r\n" + 
	                        "over or under the Roll_1 sum.\r\n" + 
	                        "The player makes a wager based on their decision of over or under. \r\n" + 
	                        "Wagers can be from 0 to 10 points.\r\n" + 
	                        "If the player bets OVER or UNDER correctly, the player wins the amount\r\n" + 
	                        "of the wager, \r\n" + 
	                        "and that value is added to their point total.\r\n" + 
	                        "If the player bets OVER or UNDER incorrectly, the player loses points \r\n" + 
	                        "in the amount of the wager, and that value is subtracted from the \r\n" + 
	                        "Before we start, I need to get your name. Please enter it here: ");
	        playerName = scanner.nextLine();
	        System.out.println("Now, let's get this wild and crazy game underway!");

	        // Get the number of rounds
	        while (true) {
	            System.out.print("\nHow many rounds, between 1 and 5 inclusive, do you wish to play? ");
	            String input = scanner.nextLine();
	            try {
	                rounds = Integer.parseInt(input);
	                if (rounds < 1 || rounds > 5) {
	                    System.out.println("Rounds must be between 1 and 5 inclusive.");
	                } else {
	                    break;
	                }
	            } catch (NumberFormatException e) {
	                System.out.println("Rounds must be an integer.");
	            }
	        }

	        System.out.printf("\nGREAT %s! You have selected %d rounds.\n", playerName, rounds);

	        // Simulate a single round (for simplicity)
	        for (int i = 1; i <= rounds; i++) {
	            System.out.printf("\n******* Round %d *******\n", i);
	            System.out.print("\nPress and enter any character to \"roll\" the dice: ");
	            scanner.nextLine(); // simulate user pressing a key

	            int roll1 = random.nextInt(6) + 1;
	            int roll2 = random.nextInt(6) + 1;
	            int sum1 = roll1 + roll2;
	            System.out.printf("\nRolling the dice for roll_1...\n\nRoll_1 was %d,%d for a sum of %d\n", roll1, roll2, sum1);

	            String bet;
	            while (true) {
	                System.out.print("\nEnter the letter O or U to indicate if you believe Roll_2 will be over (O) or under (U) Roll_1: ");
	                bet = scanner.nextLine().toUpperCase();
	                if (!bet.equals("O") && !bet.equals("U")) {
	                    System.out.println("Bet must be either O or U.");
	                } else {
	                    break;
	                }
	            }

	            int wager = 0;
	            while (true) {
	                System.out.printf("\nEnter the number of points, between 1 and %d inclusive, that you wish to wager: ", points);
	                String input = scanner.nextLine();
	                try {
	                    wager = Integer.parseInt(input);
	                    if (wager < 1 || wager > points) {
	                        System.out.println("Wager must be between 1 and your point total, inclusive.");
	                    } else {
	                        break;
	                    }
	                } catch (NumberFormatException e) {
	                    System.out.println("Wager must be an integer.");
	                }
	            }

	            System.out.print("\nPress and enter any character to \"roll\" the dice: ");
	            scanner.nextLine(); // simulate user pressing a key

	            int roll3 = random.nextInt(6) + 1;
	            int roll4 = random.nextInt(6) + 1;
	            int sum2 = roll3 + roll4;
	            System.out.printf("\nRolling the dice for roll_2...\n\nRoll_2 was %d,%d for a sum of %d\n", roll3, roll4, sum2);

	            String outcome = sum2 > sum1 ? "OVER" : "UNDER";
	            boolean win = (bet.equals("O") && outcome.equals("OVER")) || (bet.equals("U") && outcome.equals("UNDER"));
	            points = win ? points + wager : points - wager;
	            System.out.printf("\n%s! %s, you %s!\n", outcome, win ? "Congratulations" : "Sorry", win ? "win" : "lose");
	            System.out.printf("\nRound: %d, Roll_1: %d, Roll_2: %d, Bet: %s, Wager: %d Outcome: %s, Points: %d\n", i, sum1, sum2, bet, wager, outcome, points);
	        }

	        System.out.println("-----------------------------------------------------------------------------");
	        System.out.printf("\n************************************************************\n\nGame over! Final points were %d. Thank you for playing, %s.\n", points, playerName);

	        // Play again prompt
	        String playAgain;
	        while (true) {
	            System.out.print("\nWould you like to play again? Enter Y or N: ");
	            playAgain = scanner.nextLine().toUpperCase();
	            if (!playAgain.equals("Y") && !playAgain.equals("N")) {
	                System.out.println("Sorry, you must enter a Y or N. Try again.");
	            } else {
	                break;
	            }
	        }

	        System.out.println("\nThank you for playing OVER UNDER DOUBLE SAME.");
	        System.out.printf("Have a great day, %s!\n", playerName);

	        scanner.close();
	    }
	}
}