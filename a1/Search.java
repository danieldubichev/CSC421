import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Search {
	Problem problem;
	
	public Search(Problem problem) { this.problem = problem; }
	
	//Tree-search methods
	public String BreadthFirstTreeSearch() {
		return TreeSearch(new FrontierFIFO());
	}
	
	public String DepthFirstTreeSearch() {
		return TreeSearch(new FrontierLIFO());
	}
	
	public String UniformCostTreeSearch() {
		return TreeSearch(new FrontierPriorityQueue(new ComparatorG()));
	}	

	public String GreedyBestFirstTreeSearch() {
		return TreeSearch(new FrontierPriorityQueue(new ComparatorH(problem)));
	}
	
	public String AstarTreeSearch() {
		return TreeSearch(new FrontierPriorityQueue(new ComparatorF(problem)));
	}
	public String AstarTreeSearch2() {
		return TreeSearch2(new FrontierPriorityQueue(new ComparatorF(problem)));
	}
	
	//Graph-search methods
	public String BreadthFirstGraphSearch() {
		return GraphSearch(new FrontierFIFO());
	}
	
	public String DepthFirstGraphSearch() {
		return GraphSearch(new FrontierLIFO());
	}
	
	public String UniformCostGraphSearch() {
		return GraphSearch(new FrontierPriorityQueue(new ComparatorG()));
	}	

	public String GreedyBestFirstGraphSearch() {
		return GraphSearch(new FrontierPriorityQueue(new ComparatorH(problem)));
	}
	
	public String AstarGraphSearch() {
		return GraphSearch(new FrontierPriorityQueue(new ComparatorF(problem)));
	}
	public String AstarGraphSearch2() {
		return GraphSearch2(new FrontierPriorityQueue(new ComparatorF(problem)));
	}

	
	//Iterative deepening, tree-search and graph-search
	public String IterativeDeepeningTreeSearch() {
		int limit = 0;		
		while(true){
			String result = TreeSearchDepthLimited(new FrontierLIFO(), limit);
			if (result == null){
				limit++;
				
			} else {
				return result;
			}
		}
	}	
	public String IterativeDeepeningGraphSearch() {
		int limit = 0;
		while(true){
			String result = GraphSearchDepthLimited(new FrontierLIFO(), limit);
			if (result == null){
				limit++;
			} else {
				return result;
			}
		}
	}
	private String TreeSearchDepthLimited(Frontier frontier, int limit) {
		cnt = 0;
		node_list = new ArrayList<Node>();

		initialNode = MakeNode(problem.initialState);
		node_list.add( initialNode );

		frontier.insert( initialNode );
		while(true) {

			if(frontier.isEmpty()){
				return null;
			}

			Node node = frontier.remove();

			if( problem.goal_test(node.state) ) {
				return Solution(node);
			}
			if (node.depth < limit){
				frontier.insertAll(Expand(node,problem));
				cnt++;
			}
		}
	}
	private String GraphSearchDepthLimited(Frontier frontier, int limit) {
		//Graph search but if the state of n is not in explored AND the depth of n is less than limit
		cnt = 0; 
		node_list = new ArrayList<Node>();
		
		initialNode = MakeNode(problem.initialState); 
		node_list.add( initialNode );

		//queue to store info about traversal comment out for submission only  --- for testing
		/*
		Queue<String> visited = new ArrayDeque<String>();
		*/

		
		Set<Object> explored = new HashSet<Object>(); //empty set
		frontier.insert( initialNode );
		while(true) {

			if(frontier.isEmpty()){
				return null;
			}

			Node node = frontier.remove();
			
			/* for testing purposes
			String cur = (( node.state + "(g=" + node.path_cost  + " , h=" + problem.h(node.state)+ ", f=" 
			+ ((double)problem.h(node.state) + (double)node.path_cost) + " node depth= " + node.depth));
			*/

			
			/*//save node info into queue for testing
			visited.add(cur);		
			*/
			
			if( problem.goal_test(node.state) ){
				//to update goal with order
				node.order = cnt;
				return Solution(node);
			}
			if( !explored.contains(node.state) &&  node.depth < limit) {
				explored.add(node.state);
				frontier.insertAll(Expand(node,problem));
				cnt++;
			} 
		}
		
	}
	//For statistics purposes
	int cnt; //count expansions
	List<Node> node_list; //store all nodes ever generated
	Node initialNode; //initial node based on initial state
	//
	private String TreeSearch2(Frontier frontier) {
		cnt = 0; 
		node_list = new ArrayList<Node>();
		
		initialNode = MakeNode(problem.initialState); 

		//queue to store info about traversal
		Queue<String> visited = new ArrayDeque<String>();

		node_list.add( initialNode );
		
		frontier.insert( initialNode );
		while(true) {
			
			if(frontier.isEmpty()){
				return null;
			}
			Node node = frontier.remove();
			
			// ****** all my added code for depth / printing function 
			//create the indent according to each node's depth
			String indent = "";
			for (int i = node.depth; i > 0; i--){
				indent = indent + "    ";
			}
			//get information about node
			String cur = indent + ( node.state + "(g=" + node.path_cost  + " , h=" + problem.h(node.state)+ ", f=" 
			+ ((double)problem.h(node.state) + (double)node.path_cost) + ") order=" + (cnt));
			//save node info into queue - it will
			visited.add(cur);		
			// ****** end of added code for depth / printing function
			
			if( problem.goal_test(node.state) ){
				//quick function to make the order updated to reflect bucharest; so it does not return -1;
				node.order = cnt;
				//print all visited route of the tree
				System.out.println("Entire traversal of tree in order: Execution of TreeSearch");
				PrintTreeAllVisited(visited);
				//print the tree valid route
				System.out.println("Traversal of solution route: Using TreeSearch");

				return Solution(node);
			}
			frontier.insertAll(Expand(node,problem));
			cnt++;
		}
	}
	private String TreeSearch(Frontier frontier) {
		cnt = 0; 
		node_list = new ArrayList<Node>();
		
		initialNode = MakeNode(problem.initialState); 
		node_list.add( initialNode );
		
		frontier.insert( initialNode );
		while(true) {
			
			if(frontier.isEmpty())
				return null;
			
			Node node = frontier.remove();
			
			if( problem.goal_test(node.state) )
				return Solution(node);
			
			frontier.insertAll(Expand(node,problem));
			cnt++;
		}
	}

	private String GraphSearch(Frontier frontier) {
		cnt = 0; 
		node_list = new ArrayList<Node>();
		
		initialNode = MakeNode(problem.initialState); 
		node_list.add( initialNode );
		
		Set<Object> explored = new HashSet<Object>(); //empty set
		frontier.insert( initialNode );
		while(true) {
			
			if(frontier.isEmpty())
				return null;
			
			Node node = frontier.remove();
			
			if( problem.goal_test(node.state) ){
				
				return Solution(node);
			}
			
			if( !explored.contains(node.state) ) {
				explored.add(node.state);
				frontier.insertAll(Expand(node,problem));
				cnt++;
			}
		}
	}


	



	private String GraphSearch2(Frontier frontier) {
		cnt = 0; 
		node_list = new ArrayList<Node>();
		
		//queue to store info about traversal
		Queue<String> visited = new ArrayDeque<String>();

		initialNode = MakeNode(problem.initialState); 
		node_list.add( initialNode );
		
		Set<Object> explored = new HashSet<Object>(); //empty set
		frontier.insert( initialNode );
		while(true) {
			
			if(frontier.isEmpty())
				return null;
			
			Node node = frontier.remove();
			//create the indent according to each node's depth
			String indent = "";
			for (int i = node.depth; i > 0; i--){
				indent = indent + "    ";
			}
				
						
			if( problem.goal_test(node.state) ){
				//add the goal to the traversal queue
				//get information about node
				String cur = indent + ( node.state + "(g=" + node.path_cost  + " , h=" + problem.h(node.state)+ ", f=" 
				+ ((double)problem.h(node.state) + (double)node.path_cost) + ") order=" + (cnt));
			
				//save node info into queue
				visited.add(cur);



				//quick function to make the order updated to reflect bucharest; so it does not return -1;
				node.order = cnt;
				//print the tree execution route
				System.out.println("Entire traversal of graph in order: Execution of GraphSearch");
				PrintTreeAllVisited(visited);
				//print the trees solution route
				System.out.println("Solution(node) = " + Solution(node));
				System.out.println("Node.state = " + node.state);
				return Solution(node);
			}
			
			if( !explored.contains(node.state) ) {
				String cur = indent + ( node.state + "(g=" + node.path_cost  + " , h=" + problem.h(node.state)+ ", f=" 
				+ ((double)problem.h(node.state) + (double)node.path_cost) + ") order=" + (cnt));

				visited.add(cur);
				//originalcode
				explored.add(node.state);
				frontier.insertAll(Expand(node,problem));
				cnt++;
			}
		}
	}
	

	private Node MakeNode(Object state) {
		Node node = new Node();
		node.state = state;
		node.parent_node = null;
		node.path_cost = 0;
		node.depth = 0;
		return node;
	}
	
	private Set<Node> Expand(Node node, Problem problem) {
		node.order = cnt;
		
		Set<Node> successors = new HashSet<Node>(); //empty set
		Set<Object> successor_states = problem.getSuccessors(node.state);
		
		for(Object result : successor_states) {
			Node s = new Node();
			s.state = result;
			s.parent_node = node;
			s.path_cost = node.path_cost + problem.step_cost(node.state, result); 
			s.depth = node.depth + 1; 
			successors.add(s);
			
			node_list.add( s );
		}
		
		return successors;
	}

	private void PrintTreeAllVisited(Queue<String> visited){
		
		//error handling
		if (visited.peek() == null){
			System.out.println("node is null; no solution found");
		}

		for(String s : visited) { 
  			System.out.println(s.toString()); 
		}	
		System.out.println();
	}


	//Create a string to print solution. 
	private String Solution(Node node) {
		
		String solution_str = "(cost=" + node.path_cost + ", expansions=" + cnt + ")\t";
		
		Deque<Object> solution = new ArrayDeque<Object>();
		do {
			//System.out.println(node.order); for testing purposes
			solution.push(node.state);
			node = node.parent_node;
		} while(node != null);
		
		while(!solution.isEmpty())
			solution_str += solution.pop() + " ";
		
		return solution_str;
	}
}
