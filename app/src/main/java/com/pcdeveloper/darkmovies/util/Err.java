package com.pcdeveloper.darkmovies.util;

public class Err {
    private String err;
    private Throwable t;

    public Err(String err, Throwable t) {
        this.err = err;
        this.t = t;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public Throwable getT() {
        return t;
    }

    public void setT(Throwable t) {
        this.t = t;
    }
}
