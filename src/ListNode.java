public class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
    void print() {
        ListNode current = this;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
            if(current!=null){
                System.out.print("-> ");
            }
        }
        System.out.println("\n");
    }
    ListNode reverseNode(){
        ListNode head = this;
        ListNode prev = null;
        while(head!=null){
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
