package ca.courseplannerv1.model.watchers;

//interface for observers to implement to be able to observe
//changes to myModel changes

public interface Observer {


    void stateChanged(Object obj);
}
