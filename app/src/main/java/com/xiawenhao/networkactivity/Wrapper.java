package com.xiawenhao.networkactivity;

import java.util.List;

public class Wrapper {
    private List<Person> data;

    public Wrapper() {
    }

    public Wrapper(List<Person> data) {
        this.data = data;
    }

    public List<Person> getData() {
        return data;
    }

    public void setData(List<Person> data) {
        this.data = data;
    }
}
