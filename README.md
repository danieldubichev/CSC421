# CSC421
This course explored the theory, techniques, and applications of artificial intelligence. Assignment scope included the following topics: Solving Problems by Searching, Informed Search and Exploration, Adversarial Search (Games), Logic and Inference, Knowledge Representation, Statistical Methods, Machine Learning. This repository documents Assignment 1 which covereed uninformed and informed searching algorithms, and assignment 2 which highlighted games & constraint satisfaction problems. I received an A in the course.

# Assignment 1
### Romania Map
Use the straight-line heuristic function to solve the Romania route problem (starting from Timisoara going to Bucharest) efficiently using A* TreeSearch and A* GraphSearch. Print out the final search tree, and add at each node its path cost value (g), the heuristic function value (h), their sum (f=g+h), and the order of expansion.

### Tree and Graph Search
Develop tree and graph search frameworks for A*, Breadth first, Uniform Cost, Greedy Best First, Depth First searching algorithms.

### Modelling search problems
The following three problems were modelled as search problems, implemented using accompanying code and used to find solutions to the search algorithms on the particular problems. Report solution cost and number of expansions for each search algorithm that is able to (quickly) terminate on a problem. For each problem, find a heuristic function that
improves the performance of A* compared to UCS. See the implemented NPuzzle problem in the accompanying code for an example. 

### Wolf-goat-cabbage problem
You are on the bank of a river with a boat, a cabbage, a goat, and a wolf. Your task is to
get everything to the other side. Restrictions are as follows.
1. only you can handle the boat
2. when you're in the boat, there is only space for one more item
3. you can't leave the goat alone with the wolf, nor with the cabbage (or something will
be eaten).

### Missionaries and cannibals problem
Three missionaries and three cannibals seek to cross a river, say from the left bank to the right bank. A boat that may be navigated by any combination of one or two people is
available on their side of the river. If cannibals outnumber the missionaries on either side of the river at any time, the cannibals will indulge in their anthropophagic tendencies and do away with the missionaries. Find a sequence of boat trips that will permit all the missionaries and cannibals to cross the river safely. 

### Pancake Sorting Problem
Given a stack of pancakes of various sizes, can you sort them into a stack of decreasing sizes, largest on bottom to smallest on top? You have a spatula with which you can flip the top i pancakes. 


# Assignment 2
### Nim
Nim is a kind of game in which players take turn to removing objects from some initial configuration. A particular version of the Nim is: there is a pile of
13 coins on the table, on each turn, players take either 1, 2, or 3 coins from the pile and put them aside. The objective of the game is to avoid being forced to take the
last coin. Using the framework provided in connex, and seeing the TicTacToe game as an example, implement the Nim game.

### Einstein Riddle
Consider the bike-riding puzzle from https://www.brainzilla.com/logic/zebra/bike-riding/. Formulate the problem as CSP, code it, and find the solution, i.e. a consistent
assignment for all the variables. 
