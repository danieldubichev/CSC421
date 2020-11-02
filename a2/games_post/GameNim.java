import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class GameNim extends Game{
    public GameNim(){
        currentState = new StateNim(13);
    }
    public boolean isWinState(State state){
        StateNim state1 = (StateNim) state;
        return state1.number_coins == 0;
    }
    public boolean isStuckState(State state) {
        return false;
    }
    public Set<State> getSuccessors(State state) {
        if(isWinState(state)){
            return null;
        }
        Set<State> set = new HashSet<>();
        StateNim state1 = (StateNim) state;

        int removable_coins = Math.min(state1.number_coins, 3);
        for (int i = 1; i <= removable_coins; i++) {
            StateNim successor_state = new StateNim(state1.number_coins-i);
            successor_state.player = state.player == 0 ? 1 : 0;

            set.add(successor_state);
        }
        return set;
    }
    public double eval(State state){
        if(isWinState(state)){
            if(state.player == 0){
                return 10;
            }else{
                return -10;
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        Game game = new GameNim();
        Search search = new Search(game);
        int depth = 8;

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            StateNim nextState = null;

            switch ( game.currentState.player ) {

                case 1: //Human

                    //get human's move
                    System.out.print("Enter a number between 1 and 3: ");
                    int p = Integer.parseInt( in.readLine() );
                    if(p > 3 || p < 0){
                        System.out.println("Invalid Move");
                        continue;
                    }

                    nextState = new StateNim((StateNim)game.currentState);
                    nextState.player = 1;
                    nextState.number_coins -= p;
                    System.out.println("Human: \n" + nextState);
                    System.out.println("There are " + nextState.number_coins + " coins left");
                    break;

                case 0: //Computer
                    nextState = (StateNim)search.bestSuccessorState(depth);
                    nextState.player = 0;
                    System.out.println("Computer: \n" + nextState);
                    System.out.println("There are " + nextState.number_coins + " coins left");
                    break;
            }

            game.currentState = nextState;
            //change player
            game.currentState.player = (game.currentState.player==0 ? 1 : 0);

            //Who wins?
            if ( game.isWinState(game.currentState) ) {

                if (game.currentState.player == 1) //i.e. last move was by the computer
                    System.out.println("You win!");
                else
                    System.out.println("Computer wins!");

                break;
            }
        }
    }
}