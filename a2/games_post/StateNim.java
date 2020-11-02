

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