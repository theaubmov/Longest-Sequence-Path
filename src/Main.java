
class Main {

	public static void main(String[] args) {
		
		System.out.println("Start");
		
		// Filling the matrix
		int matrix_size = 4;
		int[][] matrix = new int[matrix_size][matrix_size];
		matrix[0][0] = 9; matrix[1][0] = 6; matrix[2][0] = 5; matrix[3][0] = 2; 
		matrix[0][1] = 8; matrix[1][1] = 7; matrix[2][1] = 6; matrix[3][1] = 5; 
		matrix[0][2] = 7; matrix[1][2] = 3; matrix[2][2] = 1; matrix[3][2] = 6; 
		matrix[0][3] = 1; matrix[1][3] = 1; matrix[2][3] = 1; matrix[3][3] = 7; 
		
		// Filling the node's matrix
		Node[][] mNodes = new Node[matrix_size][matrix_size];
		for(int i = 0; i < matrix_size ; i++) {
			for(int j = 0; j < matrix_size ; j++) {
				mNodes[i][j] = new Node(matrix[i][j]);
			}
		}
		for(int i = 0; i < matrix_size ; i++) {
			for(int j = 0; j < matrix_size ; j++) {
				mNodes[i][j].bottom = (j + 1 < matrix_size && Math.abs(mNodes[i][j].valeur - mNodes[i][j+1].valeur) == 1 ) ? mNodes[i][j+1] : null;
				mNodes[i][j].right = (i + 1 < matrix_size  && Math.abs(mNodes[i][j].valeur - mNodes[i+1][j].valeur) == 1 ) ? mNodes[i+1][j] : null;
			}
		}
		
		// Get the node with the longest sequence
		Node maxNode = mNodes[0][0];
		int maxLength = mNodes[0][0].getSize(0);
		for(int i = 0; i < matrix_size ; i++) {
			for(int j = 0; j < matrix_size ; j++) {
				if(mNodes[i][j].getSize(0) > maxLength) {
					maxNode = mNodes[i][j];
				}
			}
		}
		
		System.out.println(maxNode.getSize(0));
		System.out.println(maxNode.getPath(""));

	}

}

class Node {
	
	int valeur;
	Node bottom;
	Node right;
	
	Node(){
		this.valeur = 0;
	}
	
	Node(int v){
		this.valeur = v;
	}
	
	Node(int v, Node b, Node r){
		this.valeur = v;
		this.bottom = b;
		this.right = r;
	}
	
	int getSize(int prec) {
		
		int relR = 0, relB = 0 , rel = 0;
		
		if(this.right != null) {
			relR = this.right.getSize(prec+1);
		}
		
		if(this.bottom != null) {
			relB = this.bottom.getSize(prec+1);
		}
		
		rel = Math.max(relR, relB);
		
		return Math.max(prec, rel);
	}
	
	String getPath(String path) {
		
		if( path == "") path = "" + this.valeur;
		String longestR = "", longestB = "";
		
		if(this.right != null) {
			longestR = this.right.getPath(path + "-" + this.right.valeur);
		}
		
		if(this.bottom != null) {
			longestB = this.bottom.getPath(path + "-" + this.bottom.valeur);
		}
		
		longestR = (longestB.length() > longestR.length()) ? longestB : longestR;

		return (longestR.length() > path.length()) ? longestR : path;
	}
}
