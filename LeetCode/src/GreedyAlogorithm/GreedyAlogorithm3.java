package GreedyAlogorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
* 输入正数数组costs，正数数组profits 正数k 正数m
* cost代表项目的花费
* profit代表项目利润
* k代表可以做几个项目
* m代表起始资金
* */
public class GreedyAlogorithm3 {
    public int getMaxMoney(int[] costs,int[] profits,int k,int m){
        return process(costs,profits,k,m);
    }

    private int process(int[] costs, int[] profits, int k, int m) {
        PriorityQueue<program> minCosts = new PriorityQueue<>(new minCostsCom());//小根堆，花费最小的
        PriorityQueue<program> maxProfits = new PriorityQueue<>(new maxProfitsCom());//大根堆收益最大的
        for (int i = 0; i < k; i++) {
            while (!minCosts.isEmpty()&&minCosts.peek().c<m){
                maxProfits.add(minCosts.poll());
            }
            if (maxProfits.isEmpty()){
                return m;
            }
            m += maxProfits.poll().p;
        }
        return m;
    }
    public class minCostsCom implements Comparator<program> {


        @Override
        public int compare(program o1, program o2) {
            return o1.c - o2.c;
        }
    }
    public class maxProfitsCom implements Comparator<program> {


        @Override
        public int compare(program o1, program o2) {
            return o2.c - o1.c;
        }
    }
    public class program{
        public int c;
        public int p;

        public program() {
        }

        public program(int c, int p) {
            this.c = c;
            this.p = p;
        }
    }
}
