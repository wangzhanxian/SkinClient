package com.wzx.skinmanager.fetchers;

import java.util.HashMap;

/**
 * Created by WangZhanXian on 2018/6/26.
 */

public class ResFetcherM {

    private static HashMap<String, ResFetcher> resFetchers = new HashMap<>(2);

    public static ResFetcher getFetcher(String name) {
        return resFetchers.get(name);
    }

    public static void addFetcher(ResFetcher fetcher) {
        resFetchers.put(fetcher.name(), fetcher);
    }

    public static void removeFetcher(ResFetcher fetcher) {
        removeFetcher(fetcher.name());
    }

    public static void removeFetcher(String name) {
        resFetchers.remove(name);
    }

}
