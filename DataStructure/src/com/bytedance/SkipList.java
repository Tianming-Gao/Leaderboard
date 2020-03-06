package com.bytedance;

import java.util.Random;

public class SkipList {
    public SkipListEntry head;  //头节点
    public SkipListEntry tail;  //尾节点

    public int size;            // 跳表的元素数
    public int level;           // 层数

    public Random random;       // 随机数

    SkipList () {
        SkipListEntry entry1, entry2;
        entry1 = new SkipListEntry(SkipListEntry.negativeInf, null);
        entry2 = new SkipListEntry(SkipListEntry.positiveInf, null);

        entry1.pRight = entry2;
        entry2.pLeft = entry1;

        head = entry1;
        tail = entry2;

        size = 0;
        level = 0;

        random = new Random();  //新增节点的level TODO:可以考虑类似redis的幂次定律
    }

    private SkipListEntry findEntry(String targetKey) {
        SkipListEntry entry = head;

        while (true) {
            while (entry.pRight.key != SkipListEntry.positiveInf && entry.pRight.key.compareTo(targetKey) <= 0) {
                entry = entry.pRight;
            }
            if (entry.pDown != null) {
                entry = entry.pDown;
            } else {
                break;
            }
        }
        return entry;
    }

    public Long get(String targetKey) {
        SkipListEntry entry;
        entry = findEntry(targetKey);
        if (entry.key.equals(targetKey)) {
            return entry.value;
        } else {
            return null;
        }
    }

    public Long put(String key, Long value) {
        SkipListEntry p, newEntry;
        int i = 0;

        p = findEntry(key);
        if (p.key.equals(key)) {
            Long oldValue = p.value;
            p.value = value;
            return oldValue;
        }

        newEntry = new SkipListEntry(key, value);
        newEntry.pLeft = p;
        newEntry.pRight = p.pRight;
        p.pRight.pLeft = newEntry;
        p.pRight = newEntry;


        while (random.nextDouble() < 0.5) {
            if (i >= level) {
                addEmptyLevel();
            }

            while (p.pUp == null) {
                p = p.pLeft;
            }
            p = p.pUp;
            SkipListEntry tmp = new SkipListEntry(key, null);

            tmp.pLeft = p;
            tmp.pRight = p.pRight;
            p.pRight.pLeft = tmp;
            p.pRight = tmp;

            tmp.pDown = newEntry;
            newEntry.pUp = tmp;

            newEntry = tmp;
            i = i + 1;
        }
        return null;
    }

    private void addEmptyLevel() {

        SkipListEntry p1, p2;

        p1 = new SkipListEntry(SkipListEntry.negativeInf, null);
        p2 = new SkipListEntry(SkipListEntry.positiveInf, null);

        p1.pRight = p2;
        p1.pDown = head;

        p2.pLeft = p1;
        p2.pDown = tail;

        head.pUp = p1;
        tail.pUp = p2;

        head = p1;
        tail = p2;

        level = level + 1;
    }

    public Long remove(String targetKey) {
        SkipListEntry p, q;

        p = findEntry(targetKey);

        if(!p.key.equals(targetKey)) {
            return null;
        }

        Long oldValue = p.value;
        while(p != null) {
            q = p.pUp;
            p.pLeft.pRight = p.pRight;
            p.pRight.pLeft = p.pLeft;
            p = q;
        }

        return oldValue;
    }

}

