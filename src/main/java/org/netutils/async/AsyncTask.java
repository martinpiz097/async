package org.netutils.async;

import java.io.IOException;

public class AsyncTask<R> {

    private volatile AsyncAction<R> action;
    private volatile R result;
    private volatile Thread executor;
    private volatile Thread tResults;

    //private Runnable executorRunnable;

    public AsyncTask() {}

    public AsyncTask(AsyncAction<R> action) {
        this.action = action;
    }

    public boolean isDone() {
        return !executor.isAlive();
    }

    public AsyncTask<R> execute() {
        // se ejcuta accion
        executor = new Thread(() -> {
            try {
                result = action.call();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        executor.start();
        return this;
    }

    public AsyncTask<R> execute(AsyncAction<R> action) {
        // se ejcuta accion
        this.action = action;
        return execute();
    }

    public AsyncTask<R> then(AsyncThenAction<R> action) {
        // ejecutar accion sobre objeto ya obtenido
        while (executor == null || executor.isAlive());
        tResults = new Thread(() -> action.then(result));
        tResults.start();
        return this;
    }

    public R get() {
        return result;
    }

}
