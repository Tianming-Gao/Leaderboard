package com.bytedance;

public class SkipListTest {
    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        /**
         * 玩家排名：怎么快速的知道自己排第几？？？
         * 玩家名字：该项只是节点附带的属性，节点初始化时初始一下即可
         * 玩家成就点：以此作为排名的基础(value),
         */

        skipList.put("12", (long) 12);
        skipList.put("b", (long) 17);
        skipList.put("c", (long) 20);
        skipList.put("d", (long) 25);
        skipList.put("e", (long) 31);
        skipList.put("f", (long) 38);
        skipList.put("h", (long) 39);
        skipList.put("i", (long) 44);
        skipList.put("j", (long) 50);
        skipList.put("k", (long) 55);


        var pause = true;

    }
}
