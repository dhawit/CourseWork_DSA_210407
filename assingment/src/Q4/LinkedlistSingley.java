package Q4;



public class LinkedlistSingley {
    public static class Node{
        int data;
        Node next;
        Node(int data){
            this.data=data;
            this.next=null;
        }
    }
    int size=0;
    Node head=null;
    Node tail=null;
    public void appendNode(int data){
        Node newnode=new Node(data);
        if(head==null){
            head=newnode;
            tail=newnode;
        }
        else {
            tail.next = newnode;
            tail = newnode;


            //order of n
//            Node current=head;
//            while(current.next!=null){
//                current=current.next;
//            }
//            current.next=newnode;

            // constant ma rakhna
        }
        size++;
    }
    public void printList(){
        Node current=head;
        while(current!=null){
            System.out.println(current.data);
            current=current.next;
        }
    }
    public int getDataAtAnyPos(int pos){
        //order of n
        Node current=head;
        for(int i=2;i<=pos;i++){//hamle 1 bata count gareko ho
            current=current.next;
        }
        return current.data;
    }
    public void removeDataAtAnyPos(int pos) {
        if (pos == 1) {
            Node temp = head;
            head = head.next;
            temp.next = null;
        } else {
            Node current = head;
            for (int i = 2; i < pos; i++) {//hamle 1 bata count gareko ho
                current = current.next;
            }
            Node nextnode=current.next;
            current.next=nextnode.next;
            nextnode.next=null;
        }
        size--;
    }
    public int getSize(){
        return size;
    }
}

