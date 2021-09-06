# stub

This program generates function stubs for Java classes.

## Usage

The program takes in `javap` style function signatures via stdin and prints
corresponding stubs to stdout, e.g.

```console
$ javap Order
Compiled from "Order.java"
public class Order {
  static final boolean $assertionsDisabled;
  public Order(java.lang.String, boolean, double, double, Trader, java.lang.String);
  public java.lang.String getProduct();
  public boolean isBuy();
  public double getAmount();
  public Trader getTrader();
  public void close();
  public boolean isClosed();
  public double getPrice();
  public java.lang.String getID();
  public void adjustAmount(double);
  public java.lang.String toString();
  static {};
}

$ javap Order | stub > Order.stubs.java
```

## Why

I have to learn Java for University, and my lecturer likes to provide us with
some compiled JavaDoc HTML files as documentation for our assignments.

I've gotten into the habit of stubbing out all of the functions in a given
class and writing up some brief test cases before actually implementing
anything. The stubs are very simple

```java
public String someFunction(String someParam, int anotherParam) {
    assert false : "TODO: Implement someFunction";
    return null;
}
```

but copy-pasting this skeleton and changing the assertion message, and the
return value 20+ times gets very repetitive and I end up making mistakes.


