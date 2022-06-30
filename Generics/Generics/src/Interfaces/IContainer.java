package Interfaces;

import java.util.List;

public interface IContainer<TElement extends IAggregable<TElement, TAggregateResult> & IDeeplyCloneable<TElement>, TAggregateResult> {

    List<TElement> elements();
    TAggregateResult aggregateElements();
    TElement cloneElementByIndex(int index);

    TElement get(int i);

}