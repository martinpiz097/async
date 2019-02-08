package org.netutils.async;

import java.io.IOException;
import java.util.concurrent.Callable;

@FunctionalInterface
public interface AsyncAction<R> extends Callable<R> {
    @Override
    public R call() throws IOException;
}
