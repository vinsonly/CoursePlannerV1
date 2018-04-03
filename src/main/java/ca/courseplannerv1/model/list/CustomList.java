package ca.courseplannerv1.model.list;

import ca.courseplannerv1.model.system.Department;
import ca.courseplannerv1.model.watchers.Observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class CustomList<T> implements Iterable<T>{

    private List<T> list = new ArrayList<>();
    private int numElements = 0;

    //insert new obj to end of list
    public void insert(T obj) {
        list.add(obj);
        notifyObservers(obj);
        this.numElements++;
    }

    public void insert(int index, T obj) {
        list.add(index, obj);
        notifyObservers(obj);
        this.numElements++;
    }

    //insert new obj into list, in sorted ascending order
    //returns true if successful, false otherwise.
    public abstract void insertSorted(T obj);

    //remove obj from list at the given index
    public void remove(int index) {
        list.remove(index);
    }

    //remove obj from list
    public void remove(T obj) {
        list.remove(obj);
    }

    //retreive the obj from list at the given index
    public T get(int index) {
        return list.get(index);
    }

    //return the number of elements in the list
    public int size() {
        return this.numElements;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getNumElements() {
        return numElements;
    }

    public void setNumElements(int numElements) {
        this.numElements = numElements;
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public abstract void printItems();

    @Override
    public Iterator<T> iterator() {
        return Collections.unmodifiableList(list).iterator();
    }

    //make observable
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    protected void notifyObservers(T obj) {
        for(Observer observer : observers) {
            observer.stateChanged(obj);
        }
    }


}
