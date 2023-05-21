package com.exam.myapplication;

import android.app.Application;

import com.exam.myapplication.data.database.NodesDatabaseManager;
import com.exam.myapplication.data.repository.NodeRepository;
import com.exam.myapplication.data.repository.NodeRepositoryImpl;

public class App extends Application {
    private NodeRepository nodeRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        NodesDatabaseManager databaseManager = new NodesDatabaseManager(this);
        nodeRepository = new NodeRepositoryImpl(databaseManager.open());
    }

    public NodeRepository getNodeRepository() {
        return nodeRepository;
    }
}
