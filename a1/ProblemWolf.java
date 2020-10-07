import java.util.HashSet;
import java.util.Set;
import java.util.*;

public class ProblemWolf extends Problem{
    //0:boat;1:cabbage;2:goat;3:wolf

    public boolean check_conditions(StateWolf state){
        //if goat and cabbage on the same side and the boat on the opposite side
        if(state.next[1] == state.next[2] && state.next[0] != state.next[2]){
            return false;
        }
        //if goat and wolf on the same side with the boat on the opposite side
        if(state.next[2] == state.next[3] && state.next[0] != state.next[2]){
            return false;
        }
        return true;
    }

    //Goal state is the end state which is when all the objects are across the river
    boolean goal_test(Object state){
        StateWolf s = (StateWolf) state;
        if(s.next[0] == 1 && s.next[1] == 1 && s.next[2] == 1 && s.next[3] == 1){
            return true;
        }
        return false;
    }
    Set<Object> getSuccessors(Object state){
        HashSet<Object> set = new HashSet<Object>();
        StateWolf org = (StateWolf)state;
        StateWolf dupe;

        //4 objects have 8 possible states relating to which side they are on
        //move the boat to the other side
        dupe = new StateWolf(org);
        if(dupe.next[0] == 0){
            dupe.next[0] = 1;
            //check to see if the cabbage and goat or goat and wolf are on opposite sides to add to the valid state list
            if(check_conditions(dupe)){
                set.add(dupe);
            }
        }
        //move the boat to the start side
        dupe = new StateWolf(org);
        if(dupe.next[0] == 1){
            dupe.next[0] = 0;
            if(check_conditions(dupe)){
                set.add(dupe);
            }
        }

        //move the cabbage to the other side with the boat from start side
        dupe = new StateWolf(org);
        if(dupe.next[1] == 0 && dupe.next[0] == 0){
            dupe.next[0] = 1;
            dupe.next[1] = 1;
            if(check_conditions(dupe)){
                set.add(dupe);
            }
        }

        //move the cabbage to the start side with the boat from the other side
        dupe = new StateWolf(org);
        if(dupe.next[1] == 1 && dupe.next[0] == 1){
            dupe.next[0] = 0;
            dupe.next[1] = 0;
            if(check_conditions(dupe)){
                set.add(dupe);
            }
        }

        //move the goat to the finish side
        dupe = new StateWolf(org);
        if(dupe.next[2] == 0 && dupe.next[0] == 0){
            dupe.next[2] = 1;
            dupe.next[0] = 1;
            if(check_conditions(dupe)){
                set.add(dupe);
            }
        }

        //move the goat to the start side
        dupe = new StateWolf(org);
        if(dupe.next[2] == 1 && dupe.next[0] == 1){
            dupe.next[2] = 0;
            dupe.next[0] = 0;
            if(check_conditions(dupe)){
                set.add(dupe);
            }
        }

        //move the wolf to the finish side
        dupe = new StateWolf(org);
        if(dupe.next[3] == 0 && dupe.next[0] == 0){
            dupe.next[3] = 1;
            dupe.next[0] = 1;
            if(check_conditions(dupe)){
                set.add(dupe);
            }
        }

        //move the wolf back to the start
        dupe = new StateWolf(org);
        if(dupe.next[3] == 1 && dupe.next[0] == 1){
            dupe.next[3] = 0;
            dupe.next[0] = 0;
            if(check_conditions(dupe)){
                set.add(dupe);
            }
        }

        return set;
    }
    double step_cost(Object fromState, Object toState){return 1;}
    public double h(Object state){return 0;}

    public static void main(String[] args) throws Exception {
        ProblemWolf problem = new ProblemWolf();
        int[] puzzleArray = {0,0,0,0};
        problem.initialState = new StateWolf(puzzleArray);

        Search search  = new Search(problem);

        System.out.println("TreeSearch------------------------");
        System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
        System.out.println("UniformCostTreeSearch:\t\t" + search.UniformCostTreeSearch());
        System.out.println("DepthFirstTreeSearch:\t\t" + search.DepthFirstTreeSearch());
        System.out.println("GreedyBestFirstTreeSearch:\t" + search.GreedyBestFirstTreeSearch());
        System.out.println("AstarTreeSearch:\t\t" + search.AstarTreeSearch());

        System.out.println("\n\nGraphSearch----------------------");
        System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
        System.out.println("UniformCostGraphSearch:\t\t" + search.UniformCostGraphSearch());
        //System.out.println("DepthFirstGraphSearch:\t\t" + search.DepthFirstGraphSearch());
        //System.out.println("GreedyBestGraphSearch:\t\t" + search.GreedyBestFirstGraphSearch());
        System.out.println("AstarGraphSearch:\t\t" + search.AstarGraphSearch());

        //System.out.println("\n\nIterativeDeepening----------------------");
        //System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());
        //System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
    }
}