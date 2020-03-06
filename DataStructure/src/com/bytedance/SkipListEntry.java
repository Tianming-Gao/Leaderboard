package com.bytedance;

public class SkipListEntry {
    public String key;
    public Long value;

    public SkipListEntry pLeft;
    public SkipListEntry pRight;
    public SkipListEntry pUp;
    public SkipListEntry pDown;

    public static final String negativeInf = "-oo";
    public static final String positiveInf = "+oo";

    SkipListEntry(String key, Long value) {
        this.key = key;
        this.value = value;
    }
}
