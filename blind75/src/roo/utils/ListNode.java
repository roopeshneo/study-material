package roo.utils;

/**
 * Simple singly linked list for ll problems
 */
public class ListNode {
    public int value;
    public ListNode next;

    public ListNode(int value){
        this.value = value;
    }

    // factory class for quick creation during testing with interviewer
    // var list = ListNode.of(1,2,3) will create
    // 1 -> 2 -> 3 -> null
    public static ListNode of(int... values){
        // set head and tail to dummy for easy swaps
        // and LL manipulation
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for(int value: values){
            tail.next = new ListNode(value);
            tail = tail.next;
        }
        return dummy.next;
    }
}
