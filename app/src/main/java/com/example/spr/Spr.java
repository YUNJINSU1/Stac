package com.example.spr;

import com.example.strapi.MyHttpClient;
import com.example.strapi.StrContext;

public class Spr {
    public static StrContext insert(StrContext context){
        MyHttpClient client=new MyHttpClient();
        context.setClient(client);
        return context;
    }
}
