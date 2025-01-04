package HeapSort;

import javax.imageio.stream.ImageInputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class test2_jia_qiang_Heap {
    public static class StartComparator implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static int MAXCover(int[][] m) {
        Line[] lines = new Line[m.length];
        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        Arrays.sort(lines, new StartComparator());
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < lines.length; i++) {
            while (!queue.isEmpty() && queue.peek() <= lines[i].start) {
                queue.poll();
            }
            queue.add(lines[i].end);
            max = Math.max(max, queue.size());
        }
        return max;
    }

    public static void main(String[] args) {
        Line l1 = new Line(2,5);
        Line l2 = new Line(2,6);
        Line l3 = new Line(4,1);
        Line l4 = new Line(5,5);
        Line l5 = new Line(7,5);
        Line l6 = new Line(8,13);
        Line l7 = new Line(2,10);
        int[][] m = {
                {l1.start,l1.end},
                {l2.start,l2.end},
                {l3.start,l3.end},
                {l4.start,l4.end},
                {l5.start,l5.end},
                {l6.start,l6.end},
                {l7.start,l7.end},
        };
        System.out.println(MAXCover(m));
    }
}
