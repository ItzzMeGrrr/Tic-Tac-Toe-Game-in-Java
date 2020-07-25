import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;

class SinglePlayer {
    static char location[][] = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };
    static Scanner sc = new Scanner(System.in);
    static boolean bot_ = false;
    static char bot = 'B';
    static char player = 'X';
    static boolean start = true;
    static char winner = ' ';
    static boolean space = false;
    static boolean game_Over = false;
    static String choice;
    static int num;
    static boolean skip = false;

    // -----------------------
    public static void cls() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
    }

    static void draw() {
        cls();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(" [ " + location[row][col] + " ] ");
            }
            System.out.println();
        }
        check();
    }

    static void Input() {
        if (start == true) {
            if (bot_ == false) {
                System.out.println("Enter an index: ");
                try {
                    num = sc.nextInt();
                    insert_player(num);
                } catch (InputMismatchException e) {
                    sc.reset();
                    sc = new Scanner(System.in);
                    insert_player(0);
                }

            } else {
                insert_bot(num);
            }
        }
    }

    static void select_player() {
        if (skip == false) {
            if (bot_ == true)
                bot_ = false;
            else
                bot_ = true;
        }
    }

    static void Game() {
        while (start) {
            draw();
            Input();
            select_player();
            Check_Game_Over();
        }
        if (game_Over == true) {
            draw();
            System.out.println("Match Draw!!");
        } else {
            draw();
            System.out.println("The winner is " + winner);
        }
    }

    static void check() {
        if (location[0][0] == player && location[0][1] == player && location[0][2] == player) {
            winner = player;
            start = false;
        } else if (location[1][0] == player && location[1][1] == player && location[1][2] == player) {
            winner = player;
            start = false;
        } else if (location[2][0] == player && location[2][1] == player && location[2][2] == player) {
            winner = player;
            start = false;
        } else if (location[0][0] == player && location[1][1] == player && location[2][2] == player) {
            winner = player;
            start = false;
        } else if (location[0][2] == player && location[1][1] == player && location[2][0] == player) {
            winner = player;
            start = false;
        } else if (location[0][0] == player && location[1][0] == player && location[2][0] == player) {
            winner = player;
            start = false;
        } else if (location[0][1] == player && location[1][1] == player && location[2][1] == player) {
            winner = player;
            start = false;
        } else if (location[0][2] == player && location[1][2] == player && location[2][2] == player) {
            winner = player;
            start = false;
        }

        // When player 2 can be the Winner:
        if (location[0][0] == bot && location[0][1] == bot && location[0][2] == bot) {
            winner = bot;
            start = false;
        } else if (location[1][0] == bot && location[1][1] == bot && location[1][2] == bot) {
            winner = bot;
            start = false;
        } else if (location[2][0] == bot && location[2][1] == bot && location[2][2] == bot) {
            winner = bot;
            start = false;
        } else if (location[0][0] == bot && location[1][1] == bot && location[2][2] == bot) {
            winner = bot;
            start = false;
        } else if (location[0][2] == bot && location[1][1] == bot && location[2][0] == bot) {
            winner = bot;
            start = false;
        } else if (location[0][0] == bot && location[1][0] == bot && location[2][0] == bot) {
            winner = bot;
            start = false;
        } else if (location[0][1] == bot && location[1][1] == bot && location[2][1] == bot) {
            winner = bot;
            start = false;
        } else if (location[0][2] == bot && location[1][2] == bot && location[2][2] == bot) {
            winner = bot;
            start = false;
        }
    }

    static void Check_Game_Over() {
        space = false;
        for (int row = 0; row < 4; row++) {
            if (space == false) {
                for (int col = 0; col < 3; col++) {
                    if (row < 3) {
                        if (location[row][col] == ' ') {
                            // System.out.println("space == true breaking...");
                            space = true;
                            break;
                        }
                    } else {
                        // System.out.println("No space calling game_Over()");
                        check();
                        game_Over();
                        break;
                    }
                }
            } else
                break;
        }
    }

    static void game_Over() {
        if (winner == ' ') {
            game_Over = true;
            start = false;
        }
    }

    static void choices() {
        cls();
        System.out.println("Welcome To The SinglePlayer Tic Tac Toe. Choose an Option.");
        System.out.println("1. Play game");
        System.out.println("2. Change player character, Current Player = " + player + " Bot = " + bot);
        System.out.println("3. Exit");
        choice = sc.next();

        switch (choice) {
            case "1":
                start_game();
                break;
            case "2":
                player_character();
                break;
            case "3":
                exit_game();
                break;
            default:
                System.out.println("Wrong Choice Try again!! ");
                choices();
        }
    }

    static void start_game() {
        Game();
        ask();
    }

    static void player_character() {
        System.out.println("Enter Player Character: ");
        player = sc.next().charAt(0);
        if (player == bot) {
            System.out.println("Player can't be same as bot");
            player_character();
        }
        choices();
    }

    static void exit_game() {
        System.out.println("Do you really want to Quit? ");
        System.out.println("Enter 1 to Cancel, Anyother key to quit");
        if (sc.next().charAt(0) == '1')
            choices();
        else
            System.out.println("Quiting...");
    }

    static void ask() {
        String again;
        System.out.println("Do you wanna Play it again ?(Enter 1 if yes)");
        again = sc.next();
        if (again == "1")
            play_again();
        else
            choices();
    }

    static void play_again() {
        location[0][0] = ' ';
        location[0][1] = ' ';
        location[0][2] = ' ';
        location[1][0] = ' ';
        location[1][1] = ' ';
        location[1][2] = ' ';
        location[2][0] = ' ';
        location[2][1] = ' ';
        location[2][2] = ' ';
        start = true;
        game_Over = false;
        space = false;
        bot_ = false;
        skip = false;
        winner = ' ';
        Game();
        ask();
    }

    static void insert_player(int val) {
        skip = false;
        if (val == 1 && location[0][0] == ' ') {
            location[0][0] = player;
        } else if (val == 2 && location[0][1] == ' ') {
            location[0][1] = player;
        } else if (val == 3 && location[0][2] == ' ') {
            location[0][2] = player;
        } else if (val == 4 && location[1][0] == ' ') {
            location[1][0] = player;
        } else if (val == 5 && location[1][1] == ' ') {
            location[1][1] = player;
        } else if (val == 6 && location[1][2] == ' ') {
            location[1][2] = player;
        } else if (val == 7 && location[2][0] == ' ') {
            location[2][0] = player;
        } else if (val == 8 && location[2][1] == ' ') {
            location[2][1] = player;
        } else if (val == 9 && location[2][2] == ' ') {
            location[2][2] = player;
        } else
            skip = true;
    }

    static void insert_bot(int val) {
        if (location[0][1] == bot && location[0][2] == bot && location[0][0] == ' ')
            location[0][0] = bot;

        else if (location[0][0] == bot && location[0][2] == bot && location[0][1] == ' ')
            location[0][1] = bot;

        else if (location[0][0] == bot && location[0][1] == bot && location[0][2] == ' ')
            location[0][2] = bot;

        else if (location[1][1] == bot && location[1][2] == bot && location[1][0] == ' ')
            location[1][0] = bot;

        else if (location[1][0] == bot && location[1][2] == bot && location[1][1] == ' ')
            location[1][1] = bot;

        else if (location[1][0] == bot && location[1][1] == bot && location[1][2] == ' ')
            location[1][2] = bot;

        else if (location[2][1] == bot && location[2][2] == bot && location[2][0] == ' ')
            location[2][0] = bot;

        else if (location[2][0] == bot && location[2][2] == bot && location[2][1] == ' ')
            location[2][1] = bot;

        else if (location[2][0] == bot && location[2][1] == bot && location[2][2] == ' ')
            location[2][2] = bot;

        else if (location[1][0] == bot && location[2][0] == bot && location[0][0] == ' ')
            location[0][0] = bot;

        else if (location[0][0] == bot && location[2][0] == bot && location[1][0] == ' ')
            location[1][0] = bot;

        else if (location[0][0] == bot && location[1][0] == bot && location[1][0] == ' ')
            location[2][0] = bot;

        else if (location[1][1] == bot && location[2][1] == bot && location[0][1] == ' ')
            location[0][1] = bot;

        else if (location[0][1] == bot && location[2][1] == bot && location[1][1] == ' ')
            location[1][1] = bot;

        else if (location[0][1] == bot && location[1][1] == bot && location[2][1] == ' ')
            location[2][1] = bot;

        else if (location[1][2] == bot && location[2][2] == bot && location[0][2] == ' ')
            location[0][2] = bot;

        else if (location[0][2] == bot && location[2][2] == bot && location[1][2] == ' ')
            location[1][2] = bot;

        else if (location[0][2] == bot && location[1][2] == bot && location[2][2] == ' ')
            location[2][2] = bot;

        else if (location[1][1] == bot && location[2][2] == bot && location[0][0] == ' ')
            location[0][0] = bot;

        else if (location[0][0] == bot && location[2][2] == bot && location[1][1] == ' ')
            location[1][1] = bot;

        else if (location[0][0] == bot && location[1][1] == bot && location[2][2] == ' ')
            location[2][2] = bot;

        else if (location[1][1] == bot && location[2][0] == bot && location[0][2] == ' ')
            location[0][2] = bot;

        else if (location[0][2] == bot && location[2][0] == bot && location[1][1] == ' ')
            location[1][1] = bot;

        else if (location[0][2] == bot && location[1][1] == bot && location[2][0] == ' ')
            location[2][0] = bot;

        else if (num == 1) {
            if (location[0][1] == player && location[0][2] == ' ')
                location[0][2] = bot;

            else if (location[1][0] == player && location[2][0] == ' ')
                location[2][0] = bot;

            else if (location[1][1] == player && location[2][2] == ' ')
                location[2][2] = bot;

            else if (location[0][2] == player && location[0][1] == ' ')
                location[0][1] = bot;

            else if (location[2][0] == player && location[1][0] == ' ')
                location[1][0] = bot;

            else if (location[2][2] == player && location[1][1] == ' ')
                location[1][1] = bot;

            //
            else if (location[1][1] == ' ' && location[2][2] == ' ')
                location[1][1] = bot;

            else if (location[0][1] == ' ' && location[0][2] == ' ')
                location[0][2] = bot;

            else if (location[1][0] == ' ' && location[2][0] == ' ')
                location[2][0] = bot;

            //
            else if (location[0][1] == bot && location[0][2] == ' ')
                location[0][2] = bot;

            else if (location[0][2] == bot && location[0][1] == ' ')
                location[0][1] = bot;

            else if (location[1][0] == bot && location[2][0] == ' ')
                location[2][0] = bot;

            else if (location[2][0] == bot && location[1][0] == ' ')
                location[1][0] = bot;

            else if (location[1][1] == bot && location[2][2] == ' ')
                location[2][2] = bot;

            else if (location[2][2] == bot && location[1][1] == ' ')
                location[1][1] = bot;
        } else if (num == 2) {
            if (location[0][0] == player && location[0][2] == ' ')
                location[0][2] = bot;

            else if (location[0][2] == player && location[0][0] == ' ')
                location[0][0] = bot;

            else if (location[1][1] == player && location[2][1] == ' ')
                location[2][1] = bot;

            else if (location[2][1] == player && location[1][1] == ' ')
                location[1][1] = bot;

            //
            else if (location[0][0] == ' ' && location[0][2] == ' ')
                location[0][0] = bot;

            else if (location[1][1] == ' ' && location[2][1] == ' ')
                location[1][1] = bot;

            //
            else if (location[0][0] == bot && location[0][2] == ' ')
                location[0][2] = bot;

            else if (location[0][2] == bot && location[0][0] == ' ')
                location[0][0] = bot;

            else if (location[1][1] == bot && location[2][1] == ' ')
                location[2][1] = bot;

            else if (location[2][1] == bot && location[1][1] == ' ')
                location[1][1] = bot;
        } else if (num == 3) {
            if (location[0][1] == player && location[0][0] == ' ')
                location[0][0] = bot;

            else if (location[0][0] == player && location[0][1] == ' ')
                location[0][1] = bot;

            else if (location[1][1] == player && location[2][0] == ' ')
                location[2][0] = bot;

            else if (location[2][0] == player && location[1][1] == ' ')
                location[1][1] = bot;

            else if (location[1][2] == player && location[2][2] == ' ')
                location[2][2] = bot;

            else if (location[2][2] == player && location[1][2] == ' ')
                location[1][2] = bot;

            //
            else if (location[1][1] == ' ' && location[2][0] == ' ')
                location[1][1] = bot;

            else if (location[0][0] == ' ' && location[0][1] == ' ')
                location[0][0] = bot;

            else if (location[1][2] == ' ' && location[2][2] == ' ')
                location[2][2] = bot;

            //
            else if (location[0][0] == bot && location[0][1] == ' ')
                location[0][1] = bot;

            else if (location[0][1] == bot && location[0][0] == ' ')
                location[0][0] = bot;

            else if (location[1][1] == bot && location[2][1] == ' ')
                location[2][1] = bot;

            else if (location[2][1] == bot && location[1][1] == ' ')
                location[1][1] = bot;

            else if (location[1][2] == bot && location[2][2] == ' ')
                location[2][2] = bot;

            else if (location[2][2] == bot && location[1][2] == ' ')
                location[1][2] = bot;
        } else if (num == 4) {
            if (location[1][1] == player && location[1][2] == ' ')
                location[1][2] = bot;

            else if (location[1][2] == player && location[1][1] == ' ')
                location[1][1] = bot;

            else if (location[0][0] == player && location[2][0] == ' ')
                location[2][0] = bot;

            else if (location[2][0] == player && location[0][0] == ' ')
                location[0][0] = bot;

            //
            else if (location[1][1] == ' ' && location[1][2] == ' ')
                location[1][1] = bot;

            else if (location[0][0] == ' ' && location[2][0] == ' ')
                location[0][0] = bot;

            //
            else if (location[0][0] == bot && location[2][0] == ' ')
                location[2][0] = bot;

            else if (location[2][0] == bot && location[0][0] == ' ')
                location[0][0] = bot;

            else if (location[1][1] == bot && location[1][2] == ' ')
                location[1][2] = bot;

            else if (location[1][2] == bot && location[2][1] == ' ')
                location[1][1] = bot;
        } else if (num == 5) {
            if (location[0][0] == player && location[2][2] == ' ')
                location[2][2] = bot;

            else if (location[2][2] == player && location[0][0] == ' ')
                location[0][0] = bot;

            else if (location[0][2] == player && location[2][0] == ' ')
                location[2][0] = bot;

            else if (location[2][0] == player && location[0][2] == ' ')
                location[0][2] = bot;

            else if (location[1][0] == player && location[1][2] == ' ')
                location[1][2] = bot;

            else if (location[1][2] == player && location[1][0] == ' ')
                location[1][0] = bot;

            else if (location[0][1] == player && location[2][1] == ' ')
                location[2][1] = bot;

            else if (location[2][1] == player && location[0][1] == ' ')
                location[0][1] = bot;

            //
            else if (location[0][0] == ' ' && location[2][2] == ' ')
                location[0][0] = bot;

            else if (location[0][2] == ' ' && location[2][0] == ' ')
                location[2][0] = bot;

            else if (location[1][0] == ' ' && location[1][2] == ' ')
                location[1][0] = bot;

            else if (location[0][1] == ' ' && location[2][1] == ' ')
                location[0][1] = bot;

            //
            else if (location[0][0] == bot && location[2][2] == ' ')
                location[2][2] = bot;

            else if (location[2][2] == bot && location[0][0] == ' ')
                location[0][0] = bot;

            else if (location[0][2] == bot && location[2][0] == ' ')
                location[2][0] = bot;

            else if (location[2][0] == bot && location[0][2] == ' ')
                location[0][2] = bot;

            else if (location[0][1] == bot && location[2][1] == ' ')
                location[2][1] = bot;

            else if (location[2][1] == bot && location[0][1] == ' ')
                location[0][1] = bot;

            else if (location[1][0] == bot && location[1][2] == ' ')
                location[1][2] = bot;

            else if (location[1][2] == bot && location[1][0] == ' ')
                location[1][0] = bot;
        } else if (num == 6) {
            if (location[0][2] == player && location[2][2] == ' ')
                location[2][2] = bot;

            else if (location[2][2] == player && location[0][2] == ' ')
                location[0][2] = bot;

            else if (location[1][0] == player && location[1][1] == ' ')
                location[1][1] = bot;

            else if (location[1][1] == player && location[1][0] == ' ')
                location[1][0] = bot;

            //
            else if (location[0][2] == ' ' && location[2][2] == ' ')
                location[2][2] = bot;

            else if (location[1][0] == ' ' && location[1][1] == ' ')
                location[1][1] = bot;

            //
            else if (location[1][0] == bot && location[1][1] == ' ')
                location[1][1] = bot;

            else if (location[1][1] == bot && location[1][0] == ' ')
                location[1][0] = bot;

            else if (location[0][2] == bot && location[2][2] == ' ')
                location[2][2] = bot;

            else if (location[2][2] == bot && location[0][2] == ' ')
                location[0][2] = bot;

        } else if (num == 7) {
            if (location[1][0] == player && location[0][0] == ' ')
                location[0][0] = bot;

            else if (location[0][0] == player && location[1][0] == ' ')
                location[1][0] = bot;

            else if (location[1][1] == player && location[0][2] == ' ')
                location[0][2] = bot;

            else if (location[0][2] == player && location[1][1] == ' ')
                location[1][1] = bot;

            else if (location[2][1] == player && location[2][2] == ' ')
                location[2][2] = bot;

            else if (location[2][2] == player && location[2][1] == ' ')
                location[2][1] = bot;

            //
            else if (location[0][0] == ' ' && location[1][0] == ' ')
                location[1][0] = bot;

            else if (location[1][1] == ' ' && location[0][2] == ' ')
                location[1][1] = bot;

            else if (location[2][1] == ' ' && location[2][2] == ' ')
                location[2][2] = bot;

            //
            else if (location[1][1] == bot && location[0][2] == ' ')
                location[0][2] = bot;

            else if (location[0][2] == bot && location[1][1] == ' ')
                location[1][1] = bot;

            else if (location[0][0] == bot && location[1][0] == ' ')
                location[1][0] = bot;

            else if (location[1][0] == bot && location[0][0] == ' ')
                location[0][0] = bot;

            else if (location[2][1] == bot && location[2][2] == ' ')
                location[2][2] = bot;

            else if (location[2][2] == bot && location[2][1] == ' ')
                location[2][1] = bot;
        } else if (num == 8) {
            if (location[1][1] == player && location[0][1] == ' ')
                location[0][1] = bot;

            else if (location[0][1] == player && location[1][1] == ' ')
                location[1][1] = bot;

            else if (location[2][0] == player && location[2][2] == ' ')
                location[2][2] = bot;

            else if (location[2][2] == player && location[2][0] == ' ')
                location[2][0] = bot;

            //
            else if (location[2][0] == ' ' && location[2][2] == ' ')
                location[2][2] = bot;

            else if (location[1][1] == ' ' && location[0][1] == ' ')
                location[1][1] = bot;

            //
            else if (location[2][0] == bot && location[2][2] == ' ')
                location[2][2] = bot;

            else if (location[2][2] == bot && location[2][0] == ' ')
                location[2][0] = bot;

            else if (location[1][1] == bot && location[0][1] == ' ')
                location[0][1] = bot;

            else if (location[0][1] == bot && location[1][1] == ' ')
                location[1][1] = bot;
        } else if (num == 9) {
            if (location[2][1] == player && location[2][0] == ' ')
                location[2][0] = bot;

            else if (location[2][0] == player && location[2][1] == ' ')
                location[2][1] = bot;

            else if (location[1][1] == player && location[0][0] == ' ')
                location[0][0] = bot;

            else if (location[0][0] == player && location[1][1] == ' ')
                location[1][1] = bot;

            //
            else if (location[0][2] == ' ' && location[1][2] == ' ')
                location[0][2] = bot;

            else if (location[0][0] == ' ' && location[1][1] == ' ')
                location[1][1] = bot;

            else if (location[2][0] == ' ' && location[2][1] == ' ')
                location[2][0] = bot;

            //
            else if (location[2][1] == bot && location[2][0] == ' ')
                location[2][0] = bot;

            else if (location[2][0] == bot && location[2][1] == ' ')
                location[2][1] = bot;

            else if (location[1][2] == bot && location[0][2] == ' ')
                location[0][2] = bot;

            else if (location[0][2] == bot && location[1][2] == ' ')
                location[1][2] = bot;
        }
    }

    static public void main(String[] args) {
        choices();
    }
}
