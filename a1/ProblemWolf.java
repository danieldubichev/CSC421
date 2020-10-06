import java.util.HashSet;
import java.util.Set;
import java.util.*;

class ProblemWolf extends Problem{

    //index 0: boat; 1: cabbage; 2:wolf; 3:goat
    //Goal state represents all the goods and the boat are on the other side of the river
     boolean goal_test(Object state){
         StateWolf origin = (StateWolf) state;
         if(origin.possible[0] == 1 && origin.possible[1] == 1 && origin.possible[2] == 1 && origin.possible[3] == 1){
             return true;
         }
         return false;
    }

    Set<Object> getSuccessors(Object state){
         Set<Object> set = new HashSet<Object>();
         StateWolf s1 = (StateWolf) state;
         StateWolf ss;

         ss = new StateWolf(s1);
         if((s1.possible[0] == 1) && (s1.possible[1] == 0) && (s1.possible[2] == 0) && (s1.possible[3] == 1)){
             set.add(ss);
         }

         ss = new StateWolf(s1);
         if((s1.possible[0] == 0) && (s1.possible[1] == 1) && (s1.possible[2] == 0) && (s1.possible[3] == 0)){
             set.add(ss);
         }
         ss = new StateWolf(s1);
         if((s1.possible[0] == 1) && (s1.possible[1] == 1) && (s1.possible[2] == 1) && (s1.possible[3] == 0)){
             set.add(ss);
         }
         ss = new StateWolf(s1);
         if((s1.possible[0] == 1) && (s1.possible[1] == 1) && (s1.possible[2] == 1) && (s1.possible[3] == 1)){
             set.add(ss);
         }

         return set;

    }

    double step_cost(Object fromState, Object toState){return 0;}
    public double h(Object state){return 0;}

    public static void main(String[] args) throws Exception {
        ProblemWolf problem = new ProblemWolf();
        int[] array = {0,0,0,0};
        problem.initialState = new StateWolf(array);

        Search search  = new Search(problem);

        System.out.println("TreeSearch------------------------");
        System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
        System.out.println("UniformCostTreeSearch:\t\t" + search.UniformCostTreeSearch());
        //System.out.println("DepthFirstTreeSearch:\t\t" + search.DepthFirstTreeSearch());
        //System.out.println("GreedyBestFirstTreeSearch:\t" + search.GreedyBestFirstTreeSearch());
        //System.out.println("AstarTreeSearch:\t\t" + search.AstarTreeSearch());

        System.out.println("\n\nGraphSearch----------------------");
        System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
        System.out.println("UniformCostGraphSearch:\t\t" + search.UniformCostGraphSearch());
        //System.out.println("DepthFirstGraphSearch:\t\t" + search.DepthFirstGraphSearch());
        //System.out.println("GreedyBestGraphSearch:\t\t" + search.GreedyBestFirstGraphSearch());
        System.out.println("AstarGraphSearch:\t\t" + search.AstarGraphSearch());

        System.out.println("\n\nIterativeDeepening----------------------");
        //System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());
        System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
    }
}