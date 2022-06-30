package Classes;

import Interfaces.IAggregable;
import Interfaces.IContainer;
import Interfaces.IDeeplyCloneable;

import java.util.ArrayList;
import java.util.List;

public class Container<TElement extends IAggregable<TElement, TAggregateResult> & IDeeplyCloneable<TElement>, TAggregateResult>
        implements IContainer<TElement, TAggregateResult> {

    private List<TElement> list = new ArrayList<>();

    public Container() {
    }

    public List<TElement> elements() {
        return list;
    }

    public void adds(TElement element) {
        list.add(element);
    }

    public TElement get(int index) {
        if (index < 0 || index >= list.size()) {
            return null;
        }
        return list.get(index);
    }

    public TAggregateResult aggregateElements() {

        TAggregateResult result = null;

        if (list == null) {
            return null;
        }

        for (int i = 0; i < list.size(); i++) {
            TElement compare = list.get(i);
            result = compare.aggregate(result);
        }
        return result;
    }

    public TElement cloneElementByIndex(int index) {

        if (index < 0 || index > list.size() - 1) {
            return null;
        }

        return list.get(index).deepClone();
    }

}