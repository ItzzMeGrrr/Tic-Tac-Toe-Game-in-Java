import java.util.Scanner;
import java.io.IOException;

class MultiPlayer {
    static char location[][] = { { ' ', ' ', ' ' }, { ' ', ' ', ' ', }, { ' ', ' ', ' ' } };
    static char p1='X';
    static char p2='O';
    static char player=p1;
    static int num;
    static char winner;
    static boolean start = true;
    static boolean skip = false;
    static boolean space = false;
    
    static boolean game_Over=false;
    static int choice;
    static char exit;
    static Scanner sc = new Scanner(System.in);
    
    ///Clears console
    public static void cls()
    {
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }
    static void draw() //Draws the frames of the game
    {
        cls();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) 
            {
                System.out.print(" [ "+location[row][col]+" ] ");
            }
            System.out.println();
        }
        check();             
    }
    static void Input()//Takes input from user
    {
        if(start==true)
        {
            System.out.println("Select the number player "+player+":");
            num=sc.nextInt();
            select_index(num);
        }
    }
    static void check()//Checks if anyone Won the game
    {
        //When player 1 can be the Winner:
        if(location[0][0]==p1 && location[0][1]==p1 && location[0][2]==p1)
        {
            winner=p1;
            start=false;
        }
        else if(location[1][0]==p1 && location[1][1]==p1 && location[1][2]==p1)
        {
            winner=p1;
            start=false;
        }
        else if(location[2][0]==p1 && location[2][1]==p1 && location[2][2]==p1)
        {
            winner=p1;
            start=false;
        }
        else if(location[0][0]==p1 && location[1][1]==p1 && location[2][2]==p1)
        {
            winner=p1;
            start=false;
        }
        else if(location[0][2]==p1 && location[1][1]==p1 && location[2][0]==p1)
        {
            winner=p1;
            start=false;
        }
        else if(location[0][0]==p1 && location[1][0]==p1 && location[2][0]==p1)
        {
            winner=p1;
            start=false;
        }
        else if(location[0][1]==p1 && location[1][1]==p1 && location[2][1]==p1)
        {
            winner=p1;
            start=false;
        }
        else if(location[0][2]==p1 && location[1][2]==p1 && location[2][2]==p1)
        {
            winner=p1;
            start=false;
        }

        //When player 2 can be the Winner:
        if(location[0][0]==p2 && location[0][1]==p2 && location[0][2]==p2)
        {
            winner=p2;
            start=false;
        }
        else if(location[1][0]==p2 && location[1][1]==p2 && location[1][2]==p2)
        {
            winner=p2;
            start=false;
        }
        else if(location[2][0]==p2 && location[2][1]==p2 && location[2][2]==p2)
        {
            winner=p2;
            start=false;
        }
        else if(location[0][0]==p2 && location[1][1]==p2 && location[2][2]==p2)
        {
            winner=p2;
            start=false;
        }
        else if(location[0][2]==p2 && location[1][1]==p2 && location[2][0]==p2)
        {
            winner=p2;
            start=false;
        }
        else if(location[0][0]==p2 && location[1][0]==p2 && location[2][0]==p2)
        {
            winner=p2;
            start=false;
        }
        else if(location[0][1]==p2 && location[1][1]==p2 && location[2][1]==p2)
        {
            winner=p2;
            start=false;
        }
        else if(location[0][2]==p2 && location[1][2]==p2 && location[2][2]==p2)
        {
            winner=p2;
            start=false;
        }
    }
    static void select_index(int val) //Enters player in selected space
    {
        if(val==1 && location[0][0]==' ')
            location[0][0]=player;

        else if(val==2 && location[0][1]==' ')
            location[0][1]=player;

        else if(val==3 && location[0][2]==' ')
            location[0][2]=player;

        else if(val==4 && location[1][0]==' ')
            location[1][0]=player;

        else if(val==5 && location[1][1]==' ')
            location[1][1]=player;

        else if(val==6 && location[1][2]==' ')
            location[1][2]=player;

        else if(val==7 && location[2][0]==' ')
            location[2][0]=player;

        else if(val==8 && location[2][1]==' ')
            location[2][1]=player;

        else if(val==9 && location[2][2]==' ')
            location[2][2]=player;

        else if (val==1010)
            start=false;
        else
            skip=true;
    }
    static void select_player()//Selects player 1 or 2
    {
        if(skip==false)
        {
            if(player==p1)
                player=p2;
            else
                player=p1;
        }
        else                    
            skip=false;
                     
    }
    static void MultiPlayer()//Main game loop
    {
        while(start)
        {
            draw();                                   
            Input();                        
            select_player();
            Check_Game_Over();                        
        }
        if(game_Over==true)
        {
            draw();
            System.out.println("Match Draw!!");
        }
        else
            System.out.println("The winner is "+player);
        
    }
    static void ask()//Asks player to play again
    {
        int again;
        System.out.println("Do you wanna Play it again ?(Enter 1 if yes)");
        again=sc.nextInt();
        if(again==1)
            play_again();
        else
            choices();
    }
    static void play_again()//Reset the game to be played again
    {
        location[0][0]=' ';location[0][1]=' ';location[0][2]=' ';
        location[1][0]=' ';location[1][1]=' ';location[1][2]=' ';
        location[2][0]=' ';location[2][1]=' ';location[2][2]=' ';
        start=true;
        player=p1;
        game_Over=false;
        space = false;
        skip = false;
        MultiPlayer();
        ask(); 
    }
    static void Check_Game_Over()//Checks if no space available in array then execute game_Over()
    {
        space=false;
        for(int row=0;row<4;row++)
        {
            if(space==false)
            {
                for(int col=0;col<3;col++)
                {
                    if(row<3)
                    {
                        if(location[row][col]==' ')
                        {
                            space=true;
                            break;
                        }
                        else
                        {
                            //NOP 
                        }
                    }
                    else
                    {
                        game_Over();
                        break;
                    }
                }
            }
            else
                break;
        }
    }
    static void game_Over()//game is over
    {        
        start=false;
        game_Over=true;
    }
    static void choices()//Asks User for selection
    {
        cls();
        System.out.println("Welcome To The Tic Tac Toe. Choose an Option.");        
        System.out.println("1. Play game");
        System.out.println("2. Change player character Current p1 = "+p1+" p2 = "+p2);
        System.out.println("3. Exit");
        choice=sc.nextInt();

        switch(choice)
        {
            case 1:
                    start_game();
                    break;
            case 2:
                    player_character();
                    break;
            case 3:
                    exit_game();
                    break;
            default:
                    System.out.println("Wrong Choice Try again!!");
                    choices();
        }
    }
    static void player_character()//Change player character
    {
        System.out.println("Enter First Player Character: ");
        p1=sc.next().charAt(0);
        player=p1;
        System.out.println("Enter Second Player Character: ");
        p2=sc.next().charAt(0);
        choices();
    }
    static void start_game()//starts game
    {
        MultiPlayer();
        ask();
    }
    static void exit_game()//Exit the Game
    {
       System.out.println("Do you really want to Quit? ");
       System.out.println("Enter 1 to exit and Anyother key to cancel");
       if(sc.nextInt()==1)
            System.out.println("Quiting...");
        else 
            choices();
    
    }
    
    static public void main(String[] args)//Main function;)
    {        
        choices();
    }
}   
