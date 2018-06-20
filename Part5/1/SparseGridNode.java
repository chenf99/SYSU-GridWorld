public class SparseGridNode
{
	private Object occupant;
	private int col;
	private SparseGridNode next;

	public SparseGridNode(Object o, int c, SparseGridNode n) {
		occupant = o;
		col = c;
		next = n;
	}

	public Object getOccupant() {
		return occupant;
	}

	public int getCol() {
		return col;
	}

	public SparseGridNode getNext() {
		return next;
	}

	public void setOccupant(Object o) {
		occupant = o;
	}


	public void setNext(SparseGridNode n) {
		next = n;
	}
}