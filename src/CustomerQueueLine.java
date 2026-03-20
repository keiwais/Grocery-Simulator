import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

    public class CustomerQueueLine {

        public static class Customer {
            public JButton sprite; // customer's sprite button
            public int priority;   // 1 = Senior, 2 = Normal
            public int sequence;   // arrival order

            public Customer(JButton sprite, int priority, int sequence) {
                this.sprite = sprite;
                this.priority = priority;
                this.sequence = sequence;
            }
        }

        private int sequenceCounter = 0; // tracks arrival order
        private final PriorityQueue<Customer> lineQueue;

        // Line positions from front (index 0) to back
        private final Point[] linePositions = {
                new Point(760, 219),
                new Point(760, 250),
                new Point(760, 280),
                new Point(760, 310)
        };

        public CustomerQueueLine() {
            lineQueue = new PriorityQueue<>(
                    (c1, c2) -> {
                        if (c1.priority != c2.priority) return c1.priority - c2.priority; // 1 (senior) first
                        return c1.sequence - c2.sequence; // FIFO among same priority
                    }
            );
        }

        public Customer addCustomer(JButton sprite, int type) {
            sequenceCounter++;
            Customer c = new Customer(sprite, type, sequenceCounter);
            lineQueue.add(c);
            return c;
        }

        // Sorted snapshot for UI layout
        public ArrayList<Customer> getCurrentQueue() {
            ArrayList<Customer> sorted = new ArrayList<>(lineQueue);
            sorted.sort((c1, c2) -> {
                if (c1.priority != c2.priority) return c1.priority - c2.priority;
                return c1.sequence - c2.sequence;
            });
            return sorted;
        }

        public Point getPositionForIndex(int index) {
            if (index < linePositions.length) return linePositions[index];
            return new Point(760, 350 + index * 30); // fallback positions
        }

        public boolean isEmpty() {
            return lineQueue.isEmpty();
        }
    }
