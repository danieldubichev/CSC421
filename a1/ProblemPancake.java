import java.util.HashSet;
import java.util.Set;

public class ProblemPancake extends Problem {
    

    //goal: for the pancakes is for them to be stacked is fattest pancake (0) on the bottom, second fattest (1) on (0) and so on
    //example: "0 1 2 3 4 5 .... (N -1)" pancakes are stacked from fattest to smallest 
	boolean goal_test(Object state) {
        StatePancake pancake_state = (StatePancake) state;
        int k=0;
        int finalpancake = ((pancake_state.N) - 1);
        for(int i=0; i<pancake_state.N; i++){
            if (k == finalpancake){
                return true;
            } else {
                if (pancake_state.puzzleArray[i] == i){
                    k++;
                } else {
                    return false;
                }
            }
        }
        return true;
	}
  
    Set<Object> getSuccessors(Object state) {
        
        //notes on getSuccessor function
        //the successors of each pancake will be all valid flips of the pancake at that provided state
        //e.g. {0,1}, the only valid successors would be {1,0} (flipping the whole stack) and {0,1} flipping "1"
        //e.g. {2,0,1}, the successors are listed as follows and how they are achieved
        /*        - {1, 0, 2} (flipping the entire stack)
                  - {2, 1, 0} (flipping 1 and 0)
                  - {2, 0, 1} (the state itself)
                    
          e.g  {2,0,1,3}, the successors are:
                  - {3, 1, 0, 2} (flipping the entire stack)
                  - {2, 3, 1, 0} (flipping {0, 1, 3})
                  - {2, 0, 3, 1} (flipping {1, 3})
                  - {2, 0, 1, 3} (the state itself)

            markers: please factor these above examples ^ to see my thought process on how to design this getSuccessor function
        */        

        Set<Object> set = new HashSet<Object>();
        StatePancake pancake_state = (StatePancake) state; // the original state
        StatePancake s = (StatePancake) state; //take a copy of the state to not modify original state reference
        StatePancake ss; //successor states
        int pancakesize = pancake_state.N; //the amount of pancakes
        int flipcount = pancakesize; // size of the pancake
        int cur = 0; // what index we are currently performing a pancake flip at
        while (flipcount != 0 ){
            
            //create new pancake state with each flip to preserve original state
            ss = new StatePancake(s);
            //System.out.println("Cur " + ss.toString());
            //starting at the bottom pancake, flip all the way to the  top pancake, creating successors of all viable flips
            //cur is used to index the bottom of the pancake, and increase by one each time
            for(int i = cur, j = 0; i<(pancakesize/2 + cur); i++, j++){ 
                
                //if we have reached the top pancake break immediately 
                if (i == pancakesize-1){
                    break;
                }
                //flip code
                int temp = ss.puzzleArray[i]; 
                ss.puzzleArray[i] = ss.puzzleArray[pancakesize -j -1]; 
                ss.puzzleArray[pancakesize -j -1] = temp; 
            }
            
            
            
            cur++;
            flipcount--;
            set.add(ss);
            

        }
        
        return set;
        
    }


	
	double step_cost(Object fromState, Object toState) { return 1; }

	public double h(Object state) { return 0; }


	public static void main(String[] args) throws Exception {

        ProblemPancake problem = new ProblemPancake();
		//starting state
		int[] puzzleArray = {1,0,3,5,2,4,6,7,8};
		problem.initialState = new StatePancake(puzzleArray);

		Search search  = new Search(problem);
		
		System.out.println("TreeSearch------------------------");
		System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
		System.out.println("UniformCostTreeSearch:\t\t" + search.UniformCostTreeSearch());
		//System.out.println("DepthFirstTreeSearch:\t\t" + search.DepthFirstTreeSearch());
		//System.out.println("GreedyBestFirstTreeSearch:\t" + search.GreedyBestFirstTreeSearch());
		System.out.println("AstarTreeSearch:\t\t" + search.AstarTreeSearch());
		
		System.out.println("\n\nGraphSearch----------------------");
		System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
		System.out.println("UniformCostGraphSearch:\t\t" + search.UniformCostGraphSearch());
		//System.out.println("DepthFirstGraphSearch:\t\t" + search.DepthFirstGraphSearch());
		//System.out.println("GreedyBestGraphSearch:\t\t" + search.GreedyBestFirstGraphSearch());
		System.out.println("AstarGraphSearch:\t\t" + search.AstarGraphSearch());
		
		System.out.println("\n\nIterativeDeepening----------------------");
		System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());
		System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
	}
	
}
