class MyLinkedList {
	Node head;
	Node tail;
	int no; 

	/** Initialize your data structure here. */
	public MyLinkedList(){
		this.head = new Node();
		this.tail = new Node();
        head.next = tail;
        tail.prev = head;
        no = 0;
	}

    
	/** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
	public int get(int index) {
		int count =0;
        int value=-1;
		if(index<0||index>=this.no)return value;
		for(Node iter= head.next; iter!=this.tail&&count<no; iter = iter.next){
            if(count==index)return iter.val;
            count++;
        }
        return value;
    }

	/** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
	public void addAtHead(int val) {
		Node newNode = new Node(val);
        Node firstNode = this.head.next;
        newNode.next = firstNode;
        newNode.prev = this.head;
        firstNode.prev = newNode;
        this.head.next= newNode;
        this.no++;

	}

	/** Append a node of value val to the last element of the linked list. */
	public void addAtTail(int val) {
		Node newNode = new Node(val);
        Node lastNode = this.tail.prev;
        newNode.prev = lastNode;
        newNode.next = this.tail;
        lastNode.next = newNode;
        this.tail.prev= newNode;
        this.no++;
	}


	/** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
	public void addAtIndex(int index, int val) {
		if(index>this.no)return;
		else if(index==this.no){
			this.addAtTail(val);
		}
		else if(index<=0){
			this.addAtHead(val);
		}
        else{
        Node newNode = new Node(val);
		int count=0;
		for(Node iter=head.next; iter!=this.tail&&count<no; iter = iter.next){
            if(count==index){
                Node previous = iter.prev;
                newNode.next = iter;
                newNode.prev = previous;
                previous.next = newNode;
                iter.prev = newNode;
                this.no++;
                return;
            }
            count++;
        }
                }
	}


	/** Delete the index-th node in the linked list, if the index is valid. */
	public void deleteAtIndex(int index){
		if(index>this.no||index<0)return;
                else{
		int count=0;
		for(Node iter=head.next; iter!=null&&count<no; iter = iter.next){
            if(count==index){
                Node previous = iter.prev;
                Node post   = iter.next;
                previous.next = post;
                post.prev = previous;
                this.no--;
                return;
            }
            count++;
        }
                }
	}
    
	public class Node{
		Node next;
		Node prev;
		int val;

		public Node(int val){
			this.val = val;
			this.next = null;
			this.prev = null;
		}
		public Node(){
			this.val = 0;
			this.next = null;
			this.prev = null;   
		}
	}
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */