package ca.courseplannerv1.model.list;

import ca.courseplannerv1.controllers.CoursePlannerController;
import ca.courseplannerv1.model.watchers.Watcher;

public class WatcherList extends CustomList<Watcher>{
    @Override
    public void printItems() {
        System.out.println("Watchers:");
        for(Watcher watcher : getList()) {
            System.out.println(watcher.getCourse());
        }
    }

    @Override
    public void insertSorted(Watcher watcher) {
        //if list is empty, add to end
        if(size() == 0) {
            insert(watcher);
            return;
        }

        int currentIndex = 0;
        for(Watcher thisWatcher : getList()) {

            if(thisWatcher.getWatcherId() == watcher.getWatcherId()) {
                return;
            }

            if(thisWatcher.getWatcherId() < watcher.getWatcherId()) {
                insert(currentIndex, watcher);
                return;
            }
            else {
                currentIndex++;
            }
        }

        insert(watcher);
        return;
    }

    public Watcher findWatcherByWatcherId(long watcherId) {
        for(Watcher watcher : getList()) {
            if(watcher.getWatcherId() == watcherId) {
                return watcher;
            }
        }

        throw new CoursePlannerController.WatcherNotFoundException(watcherId);
    }

    public void deleteWatcher(long watcherId) {
        Watcher watcher = findWatcherByWatcherId(watcherId);
        this.remove(watcher);
        watcher.deregisterAsObserver();
    }

}
