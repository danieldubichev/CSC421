public class StateWolf{
    int[] next;

    public StateWolf(int[] state){
        this.next = state;
    }

    public StateWolf(StateWolf state){
        next = new int[state.next.length];
        int i = 0;
        while(i < state.next.length){
            next[i] = state.next[i];
            i++;
        }
    }
    public boolean equals(Object o) {
        return java.util.Arrays.equals( next, ((StateWolf) o).next );
    }

    public String toString() {
        return java.util.Arrays.toString(next);
    }
}