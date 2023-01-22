package com.codecool.hackernews.news;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum NewsTypes {
    NEWS("news"),
    NEWEST("newest"),
    ASK("ask"),
    SHOW("show"),
    JOBS("jobs"),
    ITEM("item"),
    USER("user");

    private final String newsType;

    public static NewsTypes getNewsTypeEnum(String stringNews) {
        return Arrays.stream(NewsTypes.values()).filter(newsTypes -> newsTypes.getNewsType().equals(stringNews)).findFirst().get();
    }

}
