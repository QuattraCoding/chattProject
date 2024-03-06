package org.example;

import java.util.HashMap;
import java.util.List;

public class CheckerThreads {
    private HashMap<Integer, ThreadObj> threadHashMap;
    private List<ThreadObj> threadList;
    public CheckerThreads(List<ThreadObj> threadList){
        this.threadList = threadList;
        int index = 0;
        for(ThreadObj f : threadList){
            threadHashMap.put(index, f);
            index++;
        }
    }

    public List<ThreadObj> getThreadList() {
        return threadList;
    }

    public void setThreadList(List<ThreadObj> threadList) {
        this.threadList = threadList;
    }

public void startThreads(List<ThreadObj> threadList){
        int index = 0;
        for(ThreadObj f : threadList) {
            threadHashMap.get(index).start();
            index++;
        }
    }
public void stopThreads(){
    int index = 0;
    for(ThreadObj f : threadList) {
        threadHashMap.get(index).on = false;
        index++;
    }
}
}
