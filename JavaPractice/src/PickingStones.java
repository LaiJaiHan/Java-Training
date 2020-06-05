import java.util.*;


class GameState {
	
	private int size;
	private boolean[] stones;
	private int lastMove;
	private boolean maximize = true;
	
	public GameState (int size, int takenStones, int[] arrTakenStones, boolean isMax ) {
		
		this.size = size;
		this.stones = new boolean[size+1];
		stones[0] = false;
		for (int i = 1; i<stones.length; i++) {
		
			for (int a = 0; a<arrTakenStones.length; a++) {
				
				if (a == i) stones[i] = false;
				else stones[i] = true;
			}
			
			
		}
	}
	
	public GameState (GameState state, boolean isMax, int childTaken) {
		
		this.size = state.size;
		this.stones[childTaken] = false;
		this.lastMove = childTaken;
		
		if (isMax) this.maximize = false;
		else this.maximize = true; 
		
	} // to generate child's GameState
	
	int get_lastMove(){
		return lastMove;
	}
	
	int get_sizeof_stones() {
		return stones.length-1;
	}
	
	boolean[] get_stones() {
		return stones;
	}

	
	int countTaken() {
		
		int count = 0;
		
		for (int i=1; i<stones.length; i++) {
			
			if (stones[i] == false) count++;
		}
		
		return count;
	}
	
	boolean isStonesTaken(int i) {
		
		if (stones[i] == false)
		
		return true;
		
		else return false;
	}
	
	boolean isMax() {
		return maximize;
	}
	
}

class GameTreeNode {
	
	private GameState state;
	private double value;
	private boolean end;
	
	GameTreeNode[] Child;

	
	public GameTreeNode (GameState state) {
		
		this.state = state;
		this.value = evaluateState(this.state);
		this.generateChildStates(this.state); // To make children
		
	}
	
	public double get_value() {
		return this.value;
	}
	
	public GameState getState() {
		return this.state;
	}
	
	public GameTreeNode[] getChildren() {
		return this.Child;
	}
	
	public boolean isEnd() {
		return end;
	}
	
	public void generateChildStates (GameState state) {
		
		state = this.state;
	
		int lastMove = state.get_lastMove();
		int childTaken = 0;
		int count = 0;
		
		for (int i = 1; i<state.get_sizeof_stones()+1; i++) {
			
			if ( i%lastMove == 0 || lastMove%i == 0) {
				childTaken = i; 
				count ++;
				GameState childState = new GameState (state, state.isMax(), childTaken);
				Child[i] = new GameTreeNode (childState);
			}
		
			
		}
		
		// if this node is a leaf node
		if (count == 0) {
			this.end = true;
			
			if (this.state.isMax()) this.value = -1.0; // max win
			else this.value = +1.0; // min win
		}
		
	}
	
	
	public double evaluateState(GameState state) {
		
		double value = 0.0;
		
		if (state.isMax()) {
		
			if ( !state.isStonesTaken(1) ) {
				
				value = 0.0;
			}
			else if ( state.get_lastMove() == 1 ) {
				
				int count = state.get_sizeof_stones() - state.countTaken();
				
				if (count % 2 == 0) value = -0.5;
				else value = +0.5;
				
			}
			else if ( isPrime(state.get_lastMove())) {
				
				int lastMove = state.get_lastMove();
				int count = 0;
				
				for (int i = 1; i<state.get_sizeof_stones()+1; i++) {
					
					if ( (!state.isStonesTaken(i)) && (i%lastMove == 0) )  count ++;
				}
				
				if (count % 2 == 0) value = -0.7;
				else value = +0.7;
				
			}
			
			else if ( !isPrime(state.get_lastMove())) {
				
				int count = 0;
				
				for (int i = 1; i<state.get_sizeof_stones()+1; i++) {
					
					if ( (!state.isStonesTaken(i)) && isPrime(i) )  count ++;
				}
				
				if (count % 2 == 0) value = -0.6;
				else value = +0.6;
				
			}
		}
		
		else {

			if ( !state.isStonesTaken(1) ) {
				
				value = 0.0;
			}
			else if ( state.get_lastMove() == 1 ) {
				
				int count = state.get_sizeof_stones() - state.countTaken();
				
				if (count % 2 == 0) value = +0.5;
				else value = -0.5;
				
			}
			else if ( isPrime(state.get_lastMove())) {
				
				int lastMove = state.get_lastMove();
				int count = 0;
				
				for (int i = 1; i<state.get_sizeof_stones()+1; i++) {
					
					if ( (!state.isStonesTaken(i)) && (i%lastMove == 0) )  count ++;
				}
				
				if (count % 2 == 0) value = +0.7;
				else value = -0.7;
				
			}
			
			else if ( !isPrime(state.get_lastMove())) {
				
				int count = 0;
				
				for (int i = 1; i<state.get_sizeof_stones()+1; i++) {
					
					if ( (!state.isStonesTaken(i)) && isPrime(i) )  count ++;
				}
				
				if (count % 2 == 0) value = +0.6;
				else value = -0.6;
				
			}
			
		}
		return value;
		
	}
	
	
	
	boolean isPrime(int num) {
		
		boolean result = true;
        for(int i = 2; i < num; i++) {
            if( num%i == 0) {
                result = false;
                break; 
                
            } 
            else {
                result = true;
            }
        }        
        return result;        
	}

	
	
}



public class PickingStones {


	public boolean isLeafNode(GameTreeNode currentNode) {
		if(currentNode.isEnd()) return true;
	    else return false;
	   
	}
	
	

	public double alphabeta(GameTreeNode currentNode, int depth, double alpha, double beta) {
		
	    if(depth <= 0 || isLeafNode(currentNode)) {
	        return currentNode.get_value();
	    }
	    if(currentNode.getState().isMax()) {
	        for(GameTreeNode child: currentNode.getChildren()) {
	            alpha = Math.max(alpha, alphabeta(child, depth - 1, alpha, beta));

	            if(alpha >= beta) return beta;
	           
	        }
	        return alpha;
	    }
	    else {
	        for(GameTreeNode child: currentNode.getChildren()) {
	            beta = Math.min(beta, alphabeta(child, depth - 1, alpha, beta));

	            if(alpha >= beta) return alpha;
	            
	        }
	        return beta;
	    }
	}
	

	public GameState move(GameState state) 
	{
	    double alpha = Double.POSITIVE_INFINITY;
	    double beta = Double.NEGATIVE_INFINITY;
	    
	    double bestScore = 0.0;
	    GameTreeNode gameTreeRoot = new GameTreeNode(state);
	    GameState bestMove = null;
	    
	    int depth = state.get_sizeof_stones();
	    
	    for(GameTreeNode child: gameTreeRoot.getChildren()) {
	        
	    	if(bestMove == null) {
	            bestMove = child.getState();
	        }
	        
	        alpha = Math.max(alpha, alphabeta(child, depth, alpha, beta));
	        
	        if(alpha > bestScore) {
	            bestMove = child.getState();
	            bestScore = alpha;
	        }
	    }
	    
	    return bestMove;
	}	// to chose next movement 

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PickingStones pick = new PickingStones();
		
		int size; 
		int takenStones;
		int[] arryTakenStones;
		boolean isMax;
		
		
		System.out.print("Number of stones >");
		
		size = sc.nextInt();
		
		System.out.print("Number of taken stones>");
		
		takenStones = sc.nextInt();
		
		arryTakenStones = new int[takenStones];
		
		for (int i=0; i<arryTakenStones.length; i++) {
			
			System.out.print((i+1)+"th time taken Stones");
			arryTakenStones[i] = sc.nextInt();
		}
		
		if (takenStones % 2 == 0) isMax = false;
		else isMax = true;
		
		GameState initialState = new GameState(size,takenStones,arryTakenStones,isMax);
		GameState bestMove = pick.move(initialState);
		
		System.out.println("Best choice >>"+bestMove.get_lastMove());
		

	}

}
