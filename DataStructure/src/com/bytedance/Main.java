package com.bytedance;

import java.util.concurrent.ConcurrentSkipListMap;

public class Main {
    public static void main(String[] args) {
        ConcurrentSkipListMap<SortableObject, String> map = new ConcurrentSkipListMap<>();

//        /**
//         * @param onlineId
//         * @param score
//         * @param timeStamp
//         */
//        map.put(new SortableObject("a1", 100l,System.currentTimeMillis()), 2l);
//
//        map.put(new SortableObject("a", 100l, System.currentTimeMillis()), 1l);
//        map.put(new SortableObject("a2", 200l,System.currentTimeMillis()), 3l);
//        map.put(new SortableObject("a3", 110l,System.currentTimeMillis()), 1l);
//        map.put(new SortableObject("a4", 190l,System.currentTimeMillis()), 2l);
//        map.put(new SortableObject("a5", 200l,System.currentTimeMillis()), 3l);
//        map.put(new SortableObject("a6", 5000l,System.currentTimeMillis()), 1l);

        map.put(new SortableObject(100L,System.currentTimeMillis()),"hello");
        map.put(new SortableObject(100L,System.currentTimeMillis()),"hello2");
        map.put(new SortableObject(100L,System.currentTimeMillis()),"hello3");
        map.put(new SortableObject(100L,System.currentTimeMillis()),"hello4");
        map.put(new SortableObject(100L,System.currentTimeMillis()),"hello6");
        map.put(new SortableObject(100L,System.currentTimeMillis()),"hello5");
        System.out.println(map);
        System.out.println(map.size());

        var pause = true;
    }

    public static class SortableObject implements Comparable{
        public Long score;
        public Long stamp;

        public SortableObject(Long score, Long stamp) {
            this.score = score;
            this.stamp = stamp;
        }

        @Override
        public int compareTo(Object obj) {
            if (obj == null || !(obj instanceof SortableObject)) {
                return 1;
            }
            SortableObject o2 = (SortableObject) obj;
            int d = o2.score.compareTo(score);
            if (d == 0) {
                d = stamp.compareTo(o2.stamp);
            }
            return d;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof SortableObject) {
                SortableObject o2 = (SortableObject) obj;
                boolean eq = score.equals(o2.score);
                return eq;
            }
            return false;
        }

        @Override
        public String toString() {
            return stamp +" "+score.toString();
        }
    }
}
