package com.qf;

import javax.swing.text.Caret;
public class CatWrapper implements Cat {

    private Cat cat;

    public CatWrapper(Cat cat) {
        this.cat = cat;
    }

    @Override
    public String getName() {
        System.out.println("cat wrapper");
        return cat.getName();
    }
}
