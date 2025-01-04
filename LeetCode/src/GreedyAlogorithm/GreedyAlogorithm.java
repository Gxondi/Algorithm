package GreedyAlogorithm;

import java.util.Arrays;
import java.util.Comparator;

//贪心算法
public class GreedyAlogorithm {
    public class program{
        public int start;
        public int end;

        public program(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public program(int start) {
            this.start = start;
        }
    }
    //1.贪心算法
    public static int bestArrange1(program[] programs){
        Arrays.sort(programs,new ProgramComparator());
        int timeLine = 0;
        int result = 0;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].end>=timeLine){
                result++;
                timeLine = programs[i].end;
            }
        }
        return result;
    }
    public static class ProgramComparator implements Comparator<program>{
        @Override
        public int compare(program o1, program o2) {
            return o1.end - o2.end;
        }
    }
    //1.暴力算法
    public static int bestArrange2(program[] programs){
        if (programs.length==0||programs == null){
            return 0;
        }
        return process(programs,0,0);
    }
    public static int process(program[] programs,int done,int timeline){
        if (programs.length==0){
            return done;
        }
        int max = done;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].end>=timeline){
                program[] nexts = copyButExcept(programs,i);
                max = Math.max(max,process(nexts,done+1,programs[i].end));
            }
        }
        return max;
    }
    private static program[] copyButExcept(program[] programs, int i) {
        program[] ans = new program[programs.length-1];
        int index = 0;
        for (int j = 0; j < programs.length; j++) {
            if (j!=i){
                ans[index++] = programs[j];
            }
        }
        return ans;
    }
}
