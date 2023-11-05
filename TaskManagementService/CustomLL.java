package TaskManagementService;

import org.w3c.dom.Node;

public class CustomLL {
    node head = null;
    private int count = 0;
    // node a, b;
    public static class node {
        Task val;
        node next;
        public node(Task val){
            this.val = val;
        }
    }
    
    node sortedMerge(node a, node b){
        node result = null;
        /* Base cases */
        if (a == null)
            return b;
        if (b == null)
            return a;

        /* Pick either a or b, and recur */
        if ((a.val.due_date.isBefore(b.val.due_date)) ||
            (a.val.due_date.isEqual(b.val.due_date) && a.val.priority < b.val.priority)){
            result = a;
            result.next = sortedMerge(a.next, b);
        }
        else {
            result = b;
            result.next = sortedMerge(a, b.next);
        }
        return result;
    }
    
    node mergeSort(node h){
        // Base case : if head is null
        if (h == null || h.next == null) {
            return h;
        }

        // get the middle of the list
        node middle = getMiddle(h);
        node nextofmiddle = middle.next;

        // set the next of middle node to null
        middle.next = null;

        // Apply mergeSort on left list
        node left = mergeSort(h);

        // Apply mergeSort on right list
        node right = mergeSort(nextofmiddle);

        // Merge the left and right lists
        node sortedlist = sortedMerge(left, right);
        return sortedlist;
    }
    
    public static node getMiddle(node head){
        // Utility function to get the middle of the linked list
        if (head == null)
            return head;

        node slow = head, fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    public void push(Task new_data){
        /* allocate node */
        node new_node = new node(new_data);

        /* link the old list of the new node */
        new_node.next = head;

        /* move the head to point to the new node */
        head = new_node;
        count = count + 1;
    }

    public String deleteNode(String value){
        String response = "";
        if(count == 0) {
            return response;
        } else {
            node x = head; // This should be the first node in the list
            node prev = head;
            while(x != null) {
                if(x.val.taskName.equals(value)) {

                    response = x.val.taskName + "," + x.val.priority.toString() + "," + x.val.due_date_str;
                    if(x.val.taskName.equals(head.val.taskName)) {
                        head = x.next; // If your first node is bad, make 2nd node your first node
                    } else {
                        prev.next = x.next; // This skips the current node, with the sought-after value
                    }
                    count = count - 1;
                }
                prev = x;
                x = x.next; // Traverse to check the next node
                
            }
            return response;    
        }
    }

    public String deleteLastNode(){
        String response = "";
        if(count == 0) {
            return response;
        }
        node x = head; // This should be the first node in the list
        if (x.next == null) {
            response = x.val.taskName + "," + x.val.priority.toString() + "," + x.val.due_date_str;
            head = null;
            return response;
        }
        // Traverse the list to find the second-to-last node.
        node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        // Update the next reference of the second-to-last node to null.
        response = current.next.val.taskName + "," + current.next.val.priority.toString() + "," + current.next.val.due_date_str;
        current.next = null;
        return response;
    }
}
