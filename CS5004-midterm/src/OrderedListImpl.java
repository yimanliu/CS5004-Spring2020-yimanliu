import java.util.Objects;

/**
 * @ by Yiman Liu
 * @ CS5004 Midterm
 */
public class OrderedListImpl implements OrderedList {
    private ListNode head;
    private int capacity;
    private int count;

    public OrderedListImpl(int capacity) throws IllegalArgumentException{
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity for OrderedList should be positive.");
        }
        this.head = new ListNode(0);
        this.capacity = capacity;
        this.count = 0;
    }
    public int getMin() throws IllegalStateException {
        if (count == 0) {
            throw new IllegalStateException("The OrderedList is empty.");
        }
        return head.next.val;
    }

    public int getMax() throws IllegalStateException {
        if (count == 0) {
            throw new IllegalStateException("The OrderedList is empty.");
        }
        ListNode curr = head.next;
        while (curr.next != null) {
            curr = curr.next;
        }
        return curr.val;
    }

    public double getMedian() throws IllegalStateException {
        if (count == 0) {
            throw new IllegalStateException("The OrderedList is empty.");
        }
        ListNode curr = head.next;
        ListNode prev = null;
        int n = 0;
        while (n < count / 2) {
            prev = curr;
            curr = curr.next;
            n++;
        }
        if (count % 2 == 0) {
            return (prev.val + curr.val) / 2.0;
        } else {
            return curr.val;
        }
    }
    /*
        1 -> 2 -> 3 -> 4
     */

    public void add(int val) {
        if (count == this.capacity) {
            if (val <= getMin()) return;
            remove(getMin());
        }
        ListNode node = new ListNode(val);
        ListNode curr = head;
        while (curr.next != null && curr.next.val <= val) {
            curr = curr.next;
        }
        node.next = curr.next;
        curr.next = node;
        count++;
    }

    public OrderedList merge(OrderedList other) {
        OrderedListImpl that = (OrderedListImpl) other;
        OrderedListImpl list = new OrderedListImpl(this.capacity + that.capacity);
        ListNode curr1 = this.head.next;
        ListNode curr2 = that.head.next;

        while (curr1 != null || curr2 != null) {
            if (curr1 != null) {
                list.add(curr1.val);
                curr1 = curr1.next;
            }
            if (curr2 != null) {
                list.add(curr2.val);
                curr2 = curr2.next;
            }
        }
        return list;
    }

    public void remove(int val) {
        ListNode curr = head;
        while (curr.next != null && curr.next.val != val) {
            curr = curr.next;
        }
        if (curr.next == null) return;
        curr.next = curr.next.next;
        count--;
    }
    //removes all the numbers that are larger than or equal to `cap` from the list.
    public void removeAll(int cap) {
        ListNode curr = head;
        int n = 0;
        while (curr.next != null && curr.next.val < cap) {
            curr = curr.next;
            n++;
        }
        if (curr.next == null) return;
        curr.next = null;
        count = n;
    }

    // 1 2 2 4 | 5 6 6 6 7 8

    public boolean isEmpty() {
        return count == 0;
    }

    public OrderedList intersection(OrderedList other) {
        OrderedListImpl that = (OrderedListImpl) other;
        OrderedListImpl list = new OrderedListImpl(Math.min(this.capacity, that.capacity));
        ListNode curr1 = this.head.next;
        ListNode curr2 = that.head.next;

        while (curr1 != null && curr2 != null) {
            if (curr1.val == curr2.val) {
                list.add(curr1.val);
                curr1 = curr1.next;
                curr2 = curr2.next;
            } else if (curr1.val < curr2.val) {
                curr1 = curr1.next;
            } else {
                curr2 = curr2.next;
            }
        }
        return list;
    }

    public OrderedList union(OrderedList other) {
        OrderedListImpl that = (OrderedListImpl) other;
        OrderedListImpl list = new OrderedListImpl(this.capacity + that.capacity);
        ListNode curr1 = this.head.next;
        ListNode curr2 = that.head.next;

        while (curr1 != null && curr2 != null) {
            if (curr1.val < curr2.val) {
                list.add(curr1.val);
                curr1 = curr1.next;
            } else if (curr1.val == curr2.val) {
                list.add(curr1.val);
                curr1 = curr1.next;
                curr2 = curr2.next;
            } else {
                list.add(curr2.val);
                curr2 = curr2.next;
            }
        }
        while (curr1 != null) {
            list.add(curr1.val);
            curr1 = curr1.next;
        }
        while (curr2 != null) {
            list.add(curr2.val);
            curr2 = curr2.next;
        }
        return list;
    }

    public int size() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedListImpl that = (OrderedListImpl) o;
        int sum1 = 0;
        int sum2 = 0;
        ListNode curr1 = this.head.next;
        while (curr1 != null) {
            sum1 += curr1.val;
            curr1 = curr1.next;
        }
        ListNode curr2 = that.head.next;
        while (curr2 != null) {
            sum2 += curr2.val;
            curr2 = curr2.next;
        }
        return this.capacity == that.capacity && sum1 == sum2;
    }

    @Override
    public String toString() {
        int n = this.capacity;
        String s = "[";
        ListNode curr = head.next;
        while (curr != null) {
            if (curr.next == null && n == 1) {
                s += curr.val + "]";
            } else {
                s += curr.val + " ";
            }
            curr = curr.next;
            n--;
        }
        while (n > 0) {
            if (n == 1) {
                s += "?]";
            } else {
                s += "? ";
            }
            n--;
        }
        return s;
    }

    public static void main(String[] args) {
        OrderedList list = new OrderedListImpl(10);
        list.add(3);
        list.add(1);
        list.add(0);
        list.add(15);
        list.add(4);
        list.add(4);
        System.out.println(list.toString());
        System.out.println(list.getMin());
        System.out.println(list.getMax());
        System.out.println(list.getMedian());
        OrderedList list1 = new OrderedListImpl(7);
        list1.add(3);
        list1.add(1289);
        list1.add(89);
        System.out.println(list1.toString());
        System.out.println(list1.toString());
        System.out.println(list1.getMin());
        System.out.println(list1.getMax());
        System.out.println(list1.getMedian());

        list.add(-1);
        list.add(16);
        list.add(-1);
        list.add(0);
        System.out.println(list.toString());
        list.add(-2);
        System.out.println(list.toString());
        list.add(55);
        System.out.println(list.toString());
        list.add(-1);
        System.out.println(list.toString());
        System.out.println("====================");
        OrderedList mergeList = list.merge(list1);
        System.out.println(mergeList.toString());
        System.out.println(mergeList.size());
        System.out.println("====================");
        list.remove(0);
        System.out.println(list.toString());
        list.remove(15);
        System.out.println(list.toString());
        list.remove(80);
        System.out.println(list.toString());
        System.out.println("====================");
        list.removeAll(4);
        System.out.println(list.toString());
        System.out.println("====================");
        System.out.println(list.isEmpty());
        list.removeAll(-5);
        System.out.println(list.toString());
        System.out.println(list.isEmpty());

        OrderedList list2 = new OrderedListImpl(7);
        list2.add(3);
        list2.add(89);
        list2.add(1289);
        System.out.println("====================");
        System.out.println(list2.equals(list1));
        list.add(3);
        list.add(89);
        list.add(1289);
        System.out.println(list2.equals(list));
        System.out.println(list2.size());

        list.add(0);
        list.add(-5);
        list.add(18);
        System.out.println(list.toString());
        System.out.println(list2.toString());
        OrderedList intersection = list.intersection(list2);
        System.out.println(intersection.toString());
        OrderedList union = list.union(list2);
        System.out.println(union.toString());
        System.out.println(union.getMedian());
        System.out.println(union.getMax());
        System.out.println(union.getMin());
        union.remove(3);
        System.out.println(union.toString());
        union.removeAll(0);
        System.out.println(union.toString());
        System.out.println(union.equals(list));
        System.out.println(list.toString());
        OrderedList list3 = new OrderedListImpl(10);
        list3.add(-5);
        list3.add(0);
        list3.add(3);
        list3.add(18);
        list3.add(88);
        list3.add(1290);
        System.out.println(list.equals(list3));
    }
}