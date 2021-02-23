package Context;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private AtomicInteger counter;

    public Counter() {  counter = new AtomicInteger(); }

    public int getCounter() { return counter.get(); }

    public void setCounter(int newValue) { counter.set(newValue); }

    public int incrementAndGet() { return counter.incrementAndGet(); }

    public int addAndGet(int value) { return counter.addAndGet(value); }
}
