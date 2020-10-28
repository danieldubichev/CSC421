import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CSPBikeRiding extends CSP {
	
	static Set<Object> varBike = new HashSet<Object>(Arrays.asList(new String[] {"black", "blue", "green", "red", "white"}));
	static Set<Object> varName = new HashSet<Object>(Arrays.asList(new String[] {"Adrian", "Charles", "Henry", "Joel", "Richard"}));
	static Set<Object> varSandwich = new HashSet<Object>(Arrays.asList(new String[] {"bacon", "chicken", "cheese", "pepperoni", "tuna"}));
	static Set<Object> varJuice = new HashSet<Object>(Arrays.asList(new String[] {"apple", "cranberry", "grapefruit", "orange", "pineapple"}));
	static Set<Object> varAge = new HashSet<Object>(Arrays.asList(new String[] {"12 years", "13 years", "14 years", "15 years", "16 years"}));
    	static Set<Object> varSport = new HashSet<Object>(Arrays.asList(new String[] {"baseball", "basketball", "hockey", "soccer", "swimming"}));
	
	public boolean isGood(Object X, Object Y, Object x, Object y) {
		//System.out.println("X = " + X + ", Y = " + Y + ", x = " + x + ", y = " + y);             // for testing
		
		//all constraints correct
		
		//if X is not even mentioned in by the constraints, just return true
		//as nothing can be violated
		if(!C.containsKey(X))
			return true;
		
		//check to see if there is an arc between X and Y
		//if there isn't an arc, then no constraint, i.e. it is good
		if(!C.get(X).contains(Y))
			return true;


        	//The 16-year-old brought Cheese sandwich. 
        	if(X.equals("16 years") && Y.equals("cheese") && !x.equals(y)){
            		return false;
       		}
		//Henry is exactly to the left of the soccer fan.
		if(X.equals("Henry") && Y.equals("soccer") && (Integer)x-(Integer)y!=-1){
			return false;
		}
		//Joel is next to the 16 year old cyclist. 
		if(X.equals("Joel") && Y.equals("16 years") && Math.abs((Integer)x-(Integer)y)!=1){
			return false;
		}
		//the one who likes swimming is next to the one that like baseball. 
		if(X.equals("swimming") && Y.equals("baseball") && Math.abs((Integer)x-(Integer)y)!=1){
           		return false;
        	}

		//the baseball fan is next to the one who drinks apple juice. 
		if(X.equals("baseball") && Y.equals("apple") && Math.abs((Integer)x-(Integer)y)!=1){
            		return false;
        	}

		//the boy who likes the sport on ice (hockey) will eat pepperoni sandwich. 
		if(X.equals("hockey") && Y.equals("pepperoni") && !x.equals(y)){
           		return false;
        	}
		
		//The boy who is going to eat Bacon sandwich is somewhere to the right of the owner of the White bicycle.
		if(X.equals("bacon") && Y.equals("white") && (Integer)x-(Integer)y <= 0){
			return false;
		}


		//The owner of the white bike is in between the 15 year old boy and 12 year old boy
		
		//A: Lower Bound isGood
		if(X.equals("15 years") && Y.equals("white") && (Integer)x-(Integer)y >= 0){
			return false;
		}
		//B: Higher Bound isGood
		if(X.equals("12 years") && Y.equals("white") && (Integer)x-(Integer)y <= 0){
			return false;
		}
		//C: Implied relationship between lower and upper bound: 15 before 12
		if(X.equals("15 years") && Y.equals("12 years") && (Integer)x-(Integer)y >= 0){
			return false;
		}

		//The boy who is going to drink Grapefruit juice 
		//is somewhere between who brought Tuna sandwich and who brought Pineapple juice, in that order.

		//A: Lower Bound isGood
		if(X.equals("tuna") && Y.equals("grapefruit") && (Integer)x-(Integer)y >= 0){
			return false;
		}
		//B: Higher Bound isGood
		if(X.equals("pineapple") && Y.equals("grapefruit") && (Integer)x-(Integer)y <= 0){
			return false;
		}
		//C: Implied relationship between lower and upper bound: tuna before pineapple juice
		if(X.equals("tuna") && Y.equals("pineapple") && (Integer)x-(Integer)y >= 0){
			return false;
		}



		//The boy riding the White bike 
		//is somewhere between the boys riding the blue and the black bicycles, in that order
		
		//A: Lower Bound isGood
		if(X.equals("blue") && Y.equals("white") && (Integer)x-(Integer)y >= 0){
			return false;
		}
		//B: Higher Bound isGood
		if(X.equals("black") && Y.equals("white") && (Integer)x-(Integer)y <= 0){
			return false;
		}
		//C: Implied relationship between lower and upper bound: Blue before black
		if(X.equals("blue") && Y.equals("black") && (Integer)x-(Integer)y >= 0){
			return false;
		}


		//The cyclist that brought Pineapple juice 
		//is somewhere between the 14-year-old and the boy that brought Orange juice, in that order

		//A: Lower Bound isGood
		if(X.equals("14 years") && Y.equals("pineapple") && (Integer)x-(Integer)y >= 0){
			return false;
		}
		//B: Higher Bound isGood
		if(X.equals("orange") && Y.equals("pineapple") && (Integer)x-(Integer)y <= 0){
			return false;
		}
		//C: Implied relationship between lower and upper bound: 14 year old before orange juice
		if(X.equals("14 years") && Y.equals("orange") && (Integer)x-(Integer)y >= 0){
			return false;
		}

		//The 12-year-old is somewhere between the 14-year-old and the oldest boy, in that order.
		//A: Lower Bound isGood
		if(X.equals("14 years") && Y.equals("12 years") && (Integer)x-(Integer)y >= 0){
			return false;
		}
		//B: Higher Bound isGood
		if(X.equals("16 years") && Y.equals("12 years") && (Integer)x-(Integer)y <= 0){
			return false;
		}
		//C: Implied relationship between lower and upper bound: 14 years before 16 years
		if(X.equals("14 years") && Y.equals("16 years") && (Integer)x-(Integer)y >= 0){
			return false;
		}

		//The cyclist riding the White bike 
		//is somewhere between Richard and the boy riding the Red bike, in that order
		//A: Lower Bound isGood
		if(X.equals("Richard") && Y.equals("white") && (Integer)x-(Integer)y >= 0){
			return false;
		}
		//B: Higher Bound isGood
		if(X.equals("red") && Y.equals("white") && (Integer)x-(Integer)y <= 0){
			return false;
		}
		//C: Implied relationship between lower and upper bound: Richard before red bike
		if(X.equals("Richard") && Y.equals("red") && (Integer)x-(Integer)y >= 0){
			return false;
		}



		//Charles is somewhere between Richard and Adrian, in that order.
		//A: Lower Bound isGood
		if(X.equals("Richard") && Y.equals("Charles") && (Integer)x-(Integer)y >= 0){
			return false;
		}
		//B: Higher Bound isGood
		if(X.equals("Adrian") && Y.equals("Charles") && (Integer)x-(Integer)y <= 0){
			return false;
		}
		//C: Implied relationship between lower and upper bound: Richard comes before Adrian
		if(X.equals("Richard") && Y.equals("Adrian") && (Integer)x-(Integer)y >= 0){
			return false;
		}



		//Uniqueness constraints
		
		if(varBike.contains(X) && varBike.contains(Y) && !X.equals(Y) && x.equals(y))
			return false;
		
		if(varName.contains(X) && varName.contains(Y) && !X.equals(Y) && x.equals(y))
			return false;
		
		if(varSandwich.contains(X) && varSandwich.contains(Y) && !X.equals(Y) && x.equals(y))
			return false;
		
		if(varJuice.contains(X) && varJuice.contains(Y) && !X.equals(Y) && x.equals(y)){
			return false;
		}
		if(varAge.contains(X) && varAge.contains(Y) && !X.equals(Y) && x.equals(y)){
			return false;
		}
		if(varSport.contains(X) && varSport.contains(Y) && !X.equals(Y) && x.equals(y)){
			return false;
		}
		
		return true;
	}
		
	public static void main(String[] args) throws Exception {
		CSPBikeRiding csp = new CSPBikeRiding();
		
		Integer[] dom = {1,2,3,4,5};
		
		for(Object X : varBike) 
			csp.addDomain(X, dom);
		
		for(Object X : varName) 
			csp.addDomain(X, dom);
		
		for(Object X : varSandwich) 
			csp.addDomain(X, dom);
		
		for(Object X : varJuice) 
			csp.addDomain(X, dom);
		
		for(Object X : varAge) 
			csp.addDomain(X, dom);

		for(Object X : varSport) 
			csp.addDomain(X, dom);
		
		
		//unary constraints: just remove values from domains
		
		//1)	In the middle is the boy that likes baseball
        for(int i=1; i<=5; i++)
            if(i != 3)
                csp.D.get("baseball").remove(i);


		//2)	In the fifth position is the 13 year old boy
        for(int i=1; i<=5; i++)
            if(i != 5)
                csp.D.get("13 years").remove(i);


		//3)	The Boy who likes Hockey is in the fifth position

        for(int i=1; i<=5; i++)
            if(i != 5)
                csp.D.get("hockey").remove(i);

		//4)	The Boy who rides a black bicycle is at the third position

        for(int i=1; i<=5; i++)
            if(i != 3)
                csp.D.get("black").remove(i);

		//5)	The boy who is going to drink pineapple juice is at the fourth position

  		for(int i=1; i<=5; i++)
            if(i != 4)
                csp.D.get("pineapple").remove(i);

		//6)	Tuna is at eaten on one of the ends

		for(int i=1; i<=5; i++){
            if((i != 1)){
				if (i!= 5){
                	csp.D.get("tuna").remove(i);
				}
			}
		}	
		//7)	Green bike is on one of the ends 

		for(int i=1; i<=5; i++){
            if((i != 1)){
				if (i!= 5){
                	csp.D.get("green").remove(i);
				}
			}
		}	
		//binary constraints: add constraint arcs
		
		//The owner of the White bike is somewhere between the 15-year-old boy and the youngest boy, in that order.
		csp.addBidirectionalArc("15 years", "white");		
		csp.addBidirectionalArc("white", "12 years");
		csp.addBidirectionalArc("15 years", "12 years");
		
		//Henry is exactly to the left of the Soccer fan.
		csp.addBidirectionalArc("Henry", "soccer");

		//The boy who is going to drink Grapefruit juice is 
		//somewhere between who brought Tuna sandwich and who brought Pineapple juice, in that order.
		csp.addBidirectionalArc("tuna", "grapefruit");
		csp.addBidirectionalArc("grapefruit", "pineapple");
		csp.addBidirectionalArc("tuna", "pineapple");

		//The one who likes Swimming is next to the friend who likes Baseball.
		csp.addBidirectionalArc("swimming", "baseball");

		//The cyclist that brought Pineapple juice 
		//is somewhere between the 14-year-old and the boy that brought Orange juice, in that order.
		csp.addBidirectionalArc("14 years", "pineapple");
		csp.addBidirectionalArc("pineapple", "orange");
		csp.addBidirectionalArc("14 years", "orange");


		//The boy who likes the sport played on ice is going to eat Pepperoni sandwich.
		csp.addBidirectionalArc("hockey", "pepperoni");

		//The boy riding the White bike 
		//is somewhere between the boys riding the blue and the black bicycles, in that order.
		csp.addBidirectionalArc("blue", "white");
		csp.addBidirectionalArc("white", "black");
		csp.addBidirectionalArc("blue", "black");
		//Joel is next to the 16-year-old cyclist.
		csp.addBidirectionalArc("Joel", "16 years");

		//Adrian is exactly to the left of the boy who is going to eat Pepperoni sandwich.
		csp.addBidirectionalArc("Adrian", "pepperoni");

		//The 12-year-old is somewhere between the 14-year-old and the oldest boy, in that order
		csp.addBidirectionalArc("14 years", "12 years");
		csp.addBidirectionalArc("12 years", "16 years");
		csp.addBidirectionalArc("14 years", "16 years");

		//The boy who is going to eat Bacon sandwich 
		//is somewhere to the right of the owner of the White bicycle.
		csp.addBidirectionalArc("bacon", "white");

		//The 16-year-old brought Cheese sandwich.
		csp.addBidirectionalArc("cheese", "16 years");

		//The cyclist riding the White bike is somewhere 
		//between Richard and the boy riding the Red bike, in that order.
		csp.addBidirectionalArc("Richard", "white");
		csp.addBidirectionalArc("white", "red");
		csp.addBidirectionalArc("Richard", "red");

		//The Baseball fan is next to the boy who is going to drink Apple juice.
		csp.addBidirectionalArc("baseball", "apple");

		//Charles is somewhere between Richard and Adrian, in that order
		csp.addBidirectionalArc("Richard", "Charles");
		csp.addBidirectionalArc("Charles", "Adrian");
		csp.addBidirectionalArc("Richard", "Adrian");

		//Uniqueness constraints
		
		for(Object X : varBike)
			for(Object Y : varBike)
				csp.addBidirectionalArc(X,Y);
		
		for(Object X : varName)
			for(Object Y : varName)
				csp.addBidirectionalArc(X,Y);

		for(Object X : varSandwich)
			for(Object Y : varSandwich)
				csp.addBidirectionalArc(X,Y);

		for(Object X : varJuice)
			for(Object Y : varJuice)
				csp.addBidirectionalArc(X,Y);

		for(Object X : varAge)
			for(Object Y : varAge)
				csp.addBidirectionalArc(X,Y);

		for(Object X : varSport)
			for(Object Y : varSport)
				csp.addBidirectionalArc(X,Y);
		//Now let's search for solution 
		
		Search search = new Search(csp);
		System.out.println(search.BacktrackingSearch());
	}
}
