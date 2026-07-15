package com.github.warren_bank.m3u_viewer.models;

import com.github.warren_bank.m3u_viewer.parsers.M3uParser;

import com.github.warren_bank.filterablerecyclerview.FilterableListItem;

import android.content.Context;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ChannelListItem implements FilterableListItem {
    public final String name;
    public final String url;

    protected final int nonce;

    public ChannelListItem(String name, String url, int nonce) {
        if (name == null) name = "[undefined]";

        this.name  = name;
        this.url   = url;
        this.nonce = nonce;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getFilterableValue() {
        return name;
    }

    public boolean equals(ChannelListItem that) {
        if (that == null) return false;

        return this.url.equals(that.url);
    }

    // Helper

    private static String privateFileName = "playlist.m3u";

    public static boolean writeFile(String filename, InputStream inputStream, Context context) {
        try {
            M3uParser.writeFile(
                filename,
                inputStream,
                context.openFileOutput(privateFileName, Context.MODE_PRIVATE)
            );
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public static List<FilterableListItem> readFile(Context context) {
        try {
            ArrayList<ChannelListItem> channels = M3uParser.readFile(
                context.openFileInput(privateFileName)
            );
            return (List<FilterableListItem>)(List<?>) channels;
        }
        catch(Exception e) {
            return (List<FilterableListItem>)(List<?>) new ArrayList<FilterableListItem>();
        }
    }

    // Comparator private classes

    private static class SequentialOrderComparator implements Comparator<FilterableListItem> {
        @Override
        public int compare(FilterableListItem x, FilterableListItem y) {
            if ((x == null) || (y == null)) throw new NullPointerException();

            ChannelListItem a = (ChannelListItem) x;
            ChannelListItem b = (ChannelListItem) y;

            if (a.nonce < b.nonce) return -1;
            if (a.nonce > b.nonce) return 1;
            return 0;
        }
    }

    private static class AlphabeticOrderComparator implements Comparator<FilterableListItem> {
        @Override
        public int compare(FilterableListItem x, FilterableListItem y) {
            if ((x == null) || (y == null)) throw new NullPointerException();

            ChannelListItem a = (ChannelListItem) x;
            ChannelListItem b = (ChannelListItem) y;

            return a.name.compareTo(b.name);
        }
    }

    // Comparator static instances

    public static final SequentialOrderComparator sequentialOrderComparator = new SequentialOrderComparator();
    public static final AlphabeticOrderComparator alphabeticOrderComparator = new AlphabeticOrderComparator();
}
