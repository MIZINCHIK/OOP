package io.github.mizinchik;

import java.util.List;

public interface WareHouse<T extends Order> {
    int vacantSpace();
    int readyWares();
    int getCapacity();
    void addWare(T order);
    List<T> giveOrder(int capacity);
}
