import java.util.HashSet;
import java.util.Set;

public class ProblemMissionaries extends Problem {
    
    //goal: 3 missionaries and 3 cannibals on the right side of the river (start on left side)
    //example: from {3,3,0,0,0} to {0,0,3,3,1} OR {0,0,3,3,0} //boat can be on either side
	boolean goal_test(Object state) {
        StateMC MC_state = (StateMC) state;
        if (MC_state.puzzleArray[0] == 0 &&  //there are three missionaries on the left side of riv
            MC_state.puzzleArray[1] == 0 &&  //there are three cannibals on the left side of riv
            MC_state.puzzleArray[2] == 3 &&  //there are 0 missionaries on the right side of riv
            MC_state.puzzleArray[3] == 3 &&  //there are 0 cannibals on the right side of riv
            (MC_state.puzzleArray[4] == 0 || MC_state.puzzleArray[4] == 1)) //the boat is either on right side or left side of riv  CHECK ThIS LATER MAY BE SIMPLIFIED
            {
                return true;
            }
        return false;
	}
  
    Set<Object> getSuccessors(Object state) {
        
        //notes on getSuccessor function - for MC 
        //the successors of each river crossing will be moving crossing at most two missionaries or cannibals at a time such that 
        //there are never more cannibals than missionaries on one side.
        //array index  0 = number of missionaries on left side (starts as 3)
        //array index  1 = number of cannibals on left side (starts at 3)
        //array index  2 = number of missionaries on right side (starts as 0)
        //array index  3 = number of cannibals on right side (starts as 0)
        //array index  4 = boat
        //examples of valid crossings
        /*  we can cross
            - two cannibals 
            - two missionaries
            - one cannibal
            - one missionary
            - one cannibal and one missionary
            
            now also, we must consider that we can do this from both sides (left and right side of the river). Also just crossing without a cannibal or missionary,
            
            - two cannibals                                  (from left)    1
            - two cannibals                                  (from right)   2                                    
            - two missionaries                               (from left)    3
            - two missionaries                               (from right)   4
            - one cannibal                                   (from left)    5
            - one cannibal                                   (from right)   6
            - one missionary                                 (from left)    7
            - one missionary                                 (from right)   8
            - one cannibal and one missionary                (from left)    9
            - one cannibal and one missionary                (from right)   10
            - empty cross (0 cannibals or missionaries)      (from left)    11
            - empty cross (0 cannibals or missionaries)      (from right )  12

            and finally: we must check the validity of each of these operations before adding them to the successors. 
            to do this: we would subtract the amount of missionaries or cannibals if we were to do said operation,
            and if there are any more cannibals than missionaries at either side (based on array indices) we would NOT add this to successors


            markers: please factor these above examples ^ to see my thought process on how to design this getSuccessor function
        */        

        Set<Object> set = new HashSet<Object>();
        StateMC MC_state = (StateMC) state; // the original state
        StateMC s = (StateMC) state; //take a copy of the state to not modify original state reference
        StateMC ss; //successor states
        
        //System.out.println("Original state" + s.toString());
        //two cannibals (from left to right) 1
        // condition: boat on left must have at least value of 2 in index 1
        // iteration: decrease index 1 by two, increase index 3 by two, flip index 4 to 1
        // validity: 
        //          if more or equal missionaries than cannibals on both side as a result, then add to successors
        ss = new StateMC(s);
        if (ss.puzzleArray[4] == 0 && ss.puzzleArray[1] >= 2){
            ss.puzzleArray[4] = 1 ;
            ss.puzzleArray[1] = ss.puzzleArray[1] - 2;
            ss.puzzleArray[3] = ss.puzzleArray[3] + 2;
            // check if valid
            if ((ss.puzzleArray[0] >= ss.puzzleArray[1] || ss.puzzleArray[0] == 0) && (ss.puzzleArray[2] >= ss.puzzleArray[3] || ss.puzzleArray[2] == 0)){
                //System.out.println("added successor 1 = " + ss.toString());
                set.add(ss);
            }
        }

        //two cannibals (from left to right) 2
        // condition: boat on right must have at least value of 2 in index 3
        // iteration: decrease index 1 by two, increase index 3 by two, flip index 1 to 0
        // validity: 
        //         if more or equal missionaries than cannibals on both side as a result, then add to successors
        ss = new StateMC(s);
        if (ss.puzzleArray[4] == 1 && ss.puzzleArray[3] >= 2){
            ss.puzzleArray[4] = 0;
            ss.puzzleArray[3] = ss.puzzleArray[3] - 2;
            ss.puzzleArray[1] = ss.puzzleArray[1] + 2;
            // check if valid
            if ((ss.puzzleArray[0] >= ss.puzzleArray[1] || ss.puzzleArray[0] == 0) && (ss.puzzleArray[2] >= ss.puzzleArray[3] || ss.puzzleArray[2] == 0)){
                //System.out.println("added successor 2 = " + ss.toString());
                set.add(ss);
            }
        }

        //two missionaries (from left to right) 3
        // condition: at least two missionairies on left, boat is 0
        // iteration: decrease index 0 by 2, increase index 2 by 2, change boat sign  
        ss = new StateMC(s);
        if (ss.puzzleArray[4] == 0 && ss.puzzleArray[0] >= 2){
            ss.puzzleArray[4] = 1;
            ss.puzzleArray[0] = ss.puzzleArray[0] - 2;
            ss.puzzleArray[2] = ss.puzzleArray[2] + 2;
            // check if valid
            if ((ss.puzzleArray[0] >= ss.puzzleArray[1] || ss.puzzleArray[0] == 0) && (ss.puzzleArray[2] >= ss.puzzleArray[3] || ss.puzzleArray[2] == 0)){
                //System.out.println("added successor 3 = " + ss.toString());
                set.add(ss);
            }
        }
        


        //two missionaries (from right to left) 4
        // condition: at least two missionairies on right, boat is 1
        // iteration: decrease index 0 by 2, increase index 2 by 2, change boat sign  
        ss = new StateMC(s);
        if (ss.puzzleArray[4] == 1 && ss.puzzleArray[2] >= 2){
            ss.puzzleArray[4] = 0;
            ss.puzzleArray[0] = ss.puzzleArray[0] + 2;
            ss.puzzleArray[2] = ss.puzzleArray[2] - 2;
            // check if valid
            if ((ss.puzzleArray[0] >= ss.puzzleArray[1] || ss.puzzleArray[0] == 0) && (ss.puzzleArray[2] >= ss.puzzleArray[3]  || ss.puzzleArray[2] == 0 )){
                //System.out.println("added successor 4 = " + ss.toString());
                set.add(ss);
            }
        }

        //- one cannibal                                   (from left)    5
        // condition: boat on left, must have at least value of 1 in index 1
        // iteration: decrease index 1 by 1, increase index 3 by 1, flip index 4 to 1
        // validity: 
        //         if more or equal missionaries than cannibals on both side as a result, then add to successors

        ss = new StateMC(s);
        if (ss.puzzleArray[4] == 0 && ss.puzzleArray[1] >= 1){
            ss.puzzleArray[4] = 1;
            ss.puzzleArray[1] = ss.puzzleArray[1] - 1;
            ss.puzzleArray[3] = ss.puzzleArray[3] + 1;
            // check if valid
            if ((ss.puzzleArray[0] >= ss.puzzleArray[1] || ss.puzzleArray[0] == 0) && (ss.puzzleArray[2] >= ss.puzzleArray[3] || ss.puzzleArray[2] == 0 )){
                //System.out.println("added successor 5 = " + ss.toString());
                set.add(ss);
            }
        }

        //- one cannibal                                   (from right)   6
        // condition: boat on right must have at least value of 1 in index 1
        // iteration: decrease index 3 by 1, increase index 1 by 1 flip index 4 to 0
        // validity: 
        //         if more or equal missionaries than cannibals on both side as a result, then add to successors
        ss = new StateMC(s);
        if (ss.puzzleArray[4] == 1 && ss.puzzleArray[3] >= 1){
            ss.puzzleArray[4] = 0;
            ss.puzzleArray[1] = ss.puzzleArray[1] + 1;
            ss.puzzleArray[3] = ss.puzzleArray[3] - 1;
            // check if valid
            if ((ss.puzzleArray[0] >= ss.puzzleArray[1]|| ss.puzzleArray[0] == 0) && (ss.puzzleArray[2] >= ss.puzzleArray[3] || ss.puzzleArray[2] == 0 )){
                //System.out.println("added successor 6 = " + ss.toString());
                set.add(ss);
            }
        }
        

        //- one missionary  (from left to right)    7
        //  condition: at least one in index 0, boat is 0 
        //  iteration: change boat, decrease [0] by 1, add [2] by 1
        ss = new StateMC(s);
        if (ss.puzzleArray[4] == 0 && ss.puzzleArray[0] >= 1){
            ss.puzzleArray[4] = 1;
            ss.puzzleArray[0] = ss.puzzleArray[0] - 1;
            ss.puzzleArray[2] = ss.puzzleArray[2] + 1;
            // check if valid
            if ((ss.puzzleArray[0] >= ss.puzzleArray[1] || ss.puzzleArray[0] == 0) && (ss.puzzleArray[2] >= ss.puzzleArray[3] || ss.puzzleArray[2] == 0)){
                //System.out.println("added successor 7 = " + ss.toString());
                set.add(ss);
            }
        }


        //- one missionary  (from right to left)   8
        //  condition: at least one in index 2, boat is 1 
        //  iteration: change boat, decrease [2] by 1, add [0] by 1
        ss = new StateMC(s);
        if (ss.puzzleArray[4] == 1 && ss.puzzleArray[2] >= 1){
            ss.puzzleArray[4] = 0;
            ss.puzzleArray[0] = ss.puzzleArray[0] + 1;
            ss.puzzleArray[2] = ss.puzzleArray[2] - 1;
            // check if valid
            if ((ss.puzzleArray[0] >= ss.puzzleArray[1] || ss.puzzleArray[0] == 0 ) && (ss.puzzleArray[2] >= ss.puzzleArray[3] || ss.puzzleArray[2] == 0 )){
                //System.out.println("added successor 9 = " + ss.toString());
                set.add(ss);
            }
        }


        //- one cannibal and one missionary (from left to right)    9
        //  condition: boat is 0, there is at least one canibal on the left, and at least one missionary on the left
        //  iteration: flip boat, [3]++, [2]++ [0]-- [1]--
        ss = new StateMC(s);
        if (ss.puzzleArray[4] == 0 && ss.puzzleArray[0] >= 1 && ss.puzzleArray[1] >= 1){
            ss.puzzleArray[4] = 1;
            ss.puzzleArray[0] = ss.puzzleArray[0] - 1;
            ss.puzzleArray[1] = ss.puzzleArray[1] - 1;
            ss.puzzleArray[2] = ss.puzzleArray[2] + 1;
            ss.puzzleArray[3] = ss.puzzleArray[3] + 1;
            // check if valid
            if ((ss.puzzleArray[0] >= ss.puzzleArray[1] || ss.puzzleArray[0] == 0 ) && (ss.puzzleArray[2] >= ss.puzzleArray[3] || ss.puzzleArray[2] == 0 )){
                //System.out.println("added successor 9 = " + ss.toString());
                set.add(ss);
            }
        }


        //- one cannibal and one missionary (from right to left)   10
        //  condition: boat is 0, there is at least one canibal on the right, and at least one missionary on the right
        //  iteration: flip boat, [3]--, [2]-- [0]++ [1]++
        ss = new StateMC(s);
        if (ss.puzzleArray[4] == 0 && ss.puzzleArray[2] >= 1 && ss.puzzleArray[3] >= 1){
            ss.puzzleArray[4] = 0;
            ss.puzzleArray[0] = ss.puzzleArray[0] + 1;
            ss.puzzleArray[1] = ss.puzzleArray[1] + 1;
            ss.puzzleArray[2] = ss.puzzleArray[2] - 1;
            ss.puzzleArray[3] = ss.puzzleArray[3] - 1;
            // check if valid
            if ((ss.puzzleArray[0] >= ss.puzzleArray[1] || ss.puzzleArray[0] == 0 ) && (ss.puzzleArray[2] >= ss.puzzleArray[3] || ss.puzzleArray[2] == 0 )){
                //System.out.println("added successor 10 = " + ss.toString());
                set.add(ss);
            }
        }


        //- empty cross (0 cannibals or missionaries)   (from left to right)    11
        //  condition: boat is 0
        //  iteration: flip boat
        ss = new StateMC(s);
        if (ss.puzzleArray[4] == 0){
            ss.puzzleArray[4] = 1;
            // check if valid
            if ((ss.puzzleArray[0] >= ss.puzzleArray[1] || ss.puzzleArray[0] == 0 ) && (ss.puzzleArray[2] >= ss.puzzleArray[3] || ss.puzzleArray[2] == 0 )){
                //System.out.println("added successor 11 = " + ss.toString());
                set.add(ss);
            }
        }



        //- empty cross (0 cannibals or missionaries)   (from right to left)  12
        //  condition: boat is 1
        //  iteration: flip boat
        ss = new StateMC(s);
        if (ss.puzzleArray[4] == 1){
            ss.puzzleArray[4] = 0;
            // check if valid
            if ((ss.puzzleArray[0] >= ss.puzzleArray[1] || ss.puzzleArray[0] == 0 ) && (ss.puzzleArray[2] >= ss.puzzleArray[3] || ss.puzzleArray[2] == 0 )){
                //System.out.println("added successor 12 = " + ss.toString());
                set.add(ss);
            }
        }
        return set;
        
    }


	
	double step_cost(Object fromState, Object toState) { return 1; }

	public double h(Object state) { return 0; }


	public static void main(String[] args) throws Exception {

        ProblemMissionaries problem = new ProblemMissionaries();
		//starting state
		int[] puzzleArray = {3,3,0,0,0};
		problem.initialState = new StateMC(puzzleArray);

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
