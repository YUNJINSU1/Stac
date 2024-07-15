package com.example.strapi;

import java.io.Serializable;

public class StrContext implements Serializable {
    private MyHttpClient client;

    public StrContext() {
        client = new MyHttpClient();
    }

    public void MyHttpClient_Format(String email, String id, String ps, String name) {
        client.postFormat(email, id, ps, name);
    }

    public void sendPostRequest() {
        client.start();
    }

    public void setClient(MyHttpClient client) {
        this.client = client;
    }

    public MyHttpClient getClient() {
        return client;
    }
}
