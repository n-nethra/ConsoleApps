import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NNTicTacToe {
    public NNTicTacToe(){
        read("TicTac.txt");
    }

    public void read(String fileName) {
        int []scores = {0,0,0};
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = "";
            while ((line = br.readLine()) != null) {
                
                switch (winner(line.substring(0,1), line.substring(1))){
                    case "X": 
                        scores[0]++;
                        break;
                    case "O": 
                        scores[1]++;
                        break;
                    case "draw": 
                        scores[2]++;
                        break;  
                }
                
            }
        } catch (IOException io) {
            System.err.println("File Error: " + io);
        }
        System.out.println("X wins: " + scores[0] + ", O wins: " + scores[1] +  ", Draws: " + scores[2]);
    }

    public String winner(String player1, String nums){
        //make x and o places, make boolean for who wins
        String player2 = "";
        if (player1.equals("X")){
            player2 = "O";
        }
        else{
            player2 = "X";
        }
        
        String player1Moves = "";
        String player2Moves = "";

        for (int i = 0; i < nums.length(); i++){
            if (i%2 == 0){
                player1Moves += nums.charAt(i);
            }
            else{
                player2Moves += nums.charAt(i);
            }
        }
        
        //fix output
    
        if (isAWinner(player1Moves)){
            //System.out.println(player1 + " won " +player1 + nums);
            return player1;
        }
        else if (isAWinner(player2Moves)){
            //System.out.println(player2 + " won " +player1  + nums);
            return player2;
        }
        else{
            //System.out.println("Draw! " +player1  + nums);
            return "draw";
        }

    }

    public boolean isAWinner(String nums){
        String[][]winningSequences = {
            {"1", "2", "3"},
            {"4", "5", "6"},
            {"7", "8", "9"},
            {"1", "5", "9"},
            {"3", "5", "7"},
            {"1", "4", "7"},
            {"2", "5", "8"},
            {"3", "6", "9"}
        };

        for (int i = 0; i < winningSequences.length; i++){
            int count = 0;
            for (int j = 0; j < 3; j++){
                if (nums.indexOf(winningSequences[i][j]) >= 0){
                    count++;
                }
            }
            if (count == 3){
                return true;
            }
        }

        return false;
    }



    public static void main(String[] args) {
        new NNTicTacToe();
    }
}
