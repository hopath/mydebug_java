package algorithms_java.class04;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Code01_ReverseList {
	public static class Node{
		public int value;
		public Node next;
		
		public Node(int data){
			value = data;
		}
	}

	public static class DoubleNode{
		public int value;
		public DoubleNode prev;
		public DoubleNode next;

		public DoubleNode(int data){
			value = data;
		}
	}
	
	public static void main(String[] args) {
		int time = 1000;
		int i = 0;
		for(; i < time; i++){
			Node head = getRandomLinkedList(100, 100);
			printLinkedList(head);
			System.out.println();
			Node rev2 = Code01_ReverseList.reverseLinkedList(head);
			printLinkedList(rev2);
			Code01_ReverseList.reverseLinkedList(rev2);
			System.out.println();
			Node rev1 = Code01_ReverseList.testReverseLinkedList(head);
			printLinkedList(rev1);			
			System.out.println();
			
			if(!(isSame(rev1, rev2))){
				System.out.println("no!!!!!");
				break;
			}
		}
		if(i == time){
			System.out.println("yes!!!!!");
		}
	}

	public static Node reverseLinkedList(Node head){
		Node prev = null;
		Node next = null;
		while(head != null){
			next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}

	public static Node testReverseLinkedList(Node head){
		if(head == null){
			return null;
		}
		ArrayList<Node> list = new ArrayList<>();
		while(head != null){
			list.add(head);
			head = head.next;
		}
		list.get(0).next = null;
		int N = list.size();
		for(int i = 1; i < N; i++){
			list.get(i).next = list.get(i - 1);
		}
		return list.get(N - 1);
	}



	private static boolean isSame(Node r1, Node r2){
		while(r1 != null){
			if(r1.value != r2.value){
				return false;
			}
			r1 = r1.next;
			r2 = r2.next;
		}
		return true;
	}



	public static Node getRandomLinkedList(int maxSize, int maxValue){
		int size = (int)(Math.random() * maxSize) + 1;
		int value = (int)(Math.random() * maxValue) + 1;
		Node head = new Node(value);
		Node cur = head;

		for(int i = 1; i < size; i++){
			value = (int)(Math.random() * maxValue) + 1;
			cur.next = new Node(value);
			cur = cur.next;
		}
		return head;
	}

	public static void printLinkedList(Node head){
		while(head != null){
			System.out.print(head.value + " ");
			head = head.next;
		}
	}


	@Test
	public void testDoubleList(){
		int time = 10;
		int i = 0;
		for(; i < time; i++){
			DoubleNode head = getRandomDoubleNode(10, 10);
			printLinkedList(head);
			System.out.println();
			DoubleNode rev1 = Code01_ReverseList.reverseDoubleLinkedList(head);
			printLinkedList(rev1);
			Code01_ReverseList.reverseDoubleLinkedList(rev1);
			System.out.println();
			DoubleNode rev2 = Code01_ReverseList.testDoubleReverseLikedList(head);
			printLinkedList(rev2);
			System.out.println();

			if(!(isSame(rev1, rev2))){
				System.out.println("no!!!!!");
				break;
			}
		}
		if(i == time){
			System.out.println("yes!!!!!");
		}

	}

	public static DoubleNode reverseDoubleLinkedList(DoubleNode head){
		DoubleNode prev = null;
		DoubleNode next = null;

		while(head != null){
			next = head.next;
			head.prev = next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}

	public static DoubleNode testDoubleReverseLikedList(DoubleNode head){
		if(head == null || head.next == null){
			return head;
		}
		if(head.next.next == null){
			DoubleNode prev = null;
			DoubleNode next = null;
			while(head != null){
				next = head.next;
				head.prev = next;
				head.next = prev;
				prev = head;
				head = next;
			}
			return prev;
		}

		ArrayList<DoubleNode> dlist = new ArrayList<>();
		while(head != null){
			dlist.add(head);
			head = head.next;
		}

		int N = dlist.size();
		for(int i = 1; i < N - 1; i++){
			dlist.get(i).prev = dlist.get(i + 1);
			dlist.get(i).next = dlist.get(i - 1);
		}
		return dlist.get(N - 1);
	}

	public static DoubleNode getRandomDoubleNode(int maxSize, int maxValue){
		int size = (int)(maxSize * Math.random()) + 1;
		int value = (int)(maxValue * Math.random()) + 1;

		DoubleNode head = new DoubleNode(value);
		DoubleNode cur = head;
		for(int i = 1; i < size - 1; i++){
			value = (int)(maxValue * Math.random()) + 1;
			DoubleNode dnode = new DoubleNode(value);
			cur.next = dnode;
			dnode.prev = cur;
			cur = cur.next;
		}
		return head;
	}

	private static boolean isSame(DoubleNode r1, DoubleNode r2){
		while(r1 != null){
			if(r1.value != r2.value){
				return false;
			}
			r1 = r1.next;
			r2 = r2.next;
		}
		return true;
	}

	public static void printLinkedList(DoubleNode head){
		while(head != null){
			System.out.print(head.value + " ");
			head = head.next;
		}
	}

}

