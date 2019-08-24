package interview20190821alibaba;

/**
 * @author poldi.chen
 * @className LinkListReverse
 * @description TODO
 * @date 2019/8/24 10:57
 **/
public class LinkListReverse {

    SimpleLinkedList reverseRecurit(SimpleLinkedList list) {
        if (list == null || list.next == null) {
            return list;
        }
        SimpleLinkedList result = reverseRecurit(list.next);
        list.next.next = list;
        list.next = null;
        return result;
    }

    SimpleLinkedList reverse(SimpleLinkedList list) {
        SimpleLinkedList newHead = new SimpleLinkedList();
        newHead.value = list.value;
        while (list.next != null) {
            SimpleLinkedList current = new SimpleLinkedList();
            current.value = list.next.value;
            current.next = newHead;
            newHead = current;
            list = list.next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        SimpleLinkedList node1 = new SimpleLinkedList();
        SimpleLinkedList node2 = new SimpleLinkedList();
        SimpleLinkedList node3 = new SimpleLinkedList();
        node1.value = 1;
        node2.value = 2;
        node3.value = 3;
        node1.next = node2;
        node2.next = node3;

        printList(node1);
//        SimpleLinkedList newHead = new LinkListReverse().reverseRecurit(node1);
//        printList(newHead);
        SimpleLinkedList newHead2 = new LinkListReverse().reverse(node1);
        printList(newHead2);
    }

    private static void printList(SimpleLinkedList head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }
}

class SimpleLinkedList {
    Object value;
    SimpleLinkedList next;
}
