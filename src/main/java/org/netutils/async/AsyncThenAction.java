package org.netutils.async;

@FunctionalInterface
public interface AsyncThenAction<R> {
    public void then(R result);
}
