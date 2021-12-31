package com.daelim.sauce.items;

import java.util.ArrayList;

public class getStoryData {
    public ArrayList<Story_items> items = new ArrayList<>();


    public ArrayList<Story_items> getItems() {


        Story_items s1 = new Story_items("http://static.hubzum.zumst.com/hubzum/2018/02/06/09/962ec338ca3b4153b037168ec92756ac.jpg");
        Story_items s2 = new Story_items("https://i.ytimg.com/vi/5-mWvUR7_P0/maxresdefault.jpg");

        items.add(s1);
        items.add(s2);

        return items;
    }
}
