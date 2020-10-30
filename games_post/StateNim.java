/* StateNim keeps track of the current state of the game. It tracks an array
of coins. 13 coins are represented by a 'O' and once a player makes his/her
move it represents the removed coins with an 'X'.

represents as state with 3 coins removed: X,X,X,O,O,O,O,O,O,O,O,O

In addition the state stores a player value indicating whose turn it is
*/

public class StateNim extends State{

    public int number_coins;

    public StateNim(int number_coins){
        this.number_coins = number_coins;
        player = 1;
    }

    public StateNim(StateNim statenim){

        this.number_coins = statenim.number_coins;

        player = statenim.player;
    }

    public String toString() {
        String ret = "";
        for(int i = 0; i < number_coins; i++){
            if(i == number_coins-1){
                ret += "O";
            }else{
                ret += "O,";
            }
        }
       return ret;
    }


}