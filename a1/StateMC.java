public class StateMC {
    
        int N; // size
        int[] puzzleArray; // only need 1d array for the MC states
    
        
        public StateMC(int[] puzzleArray) { 
            this.puzzleArray = puzzleArray; 
            N = puzzleArray.length;
            
            
        }
        
        //It has to be a copy of values not reference because we will 
        //create many states and don't want to overwrite the same array.
        public StateMC (StateMC state) {
            N = state.N;
            puzzleArray = new int[N];
            
            for(int i=0; i<N; i++){
                    puzzleArray[i] = state.puzzleArray[i];
            }
           
        }
        
        public boolean equals(Object o) {
            return java.util.Arrays.equals(puzzleArray, ((StateMC) o).puzzleArray);
        }
        
        public int hashCode() {
            return java.util.Arrays.hashCode(puzzleArray);
        }    
        
        public String toString() {
            return java.util.Arrays.toString(puzzleArray);
        }
}
