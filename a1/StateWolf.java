public class StateWolf {
    int[] possible;

    //An initial constructor for the states that assigns 0 to wolf, cabbage, goat and the boat to signal
    //that they are on the starting side of the river. 1 on the other hand signals the other side of the
    //river.
    public StateWolf(int[] possible){
        this.possible = possible;
        int i = 0;
        while(i < 4){
            this.possible[i] = 0;
            i++;
        }
    }

    public StateWolf(StateWolf state){
        int [] possible = new int[4];
        int i = 0;
        while(i < possible.length-1){
            possible[i] = state.possible[i];
            i++;
        }
    }
    public String toString() {
        return java.util.Arrays.toString(possible);
    }
}
