package com.exam.myapplication.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.exam.myapplication.data.database.NodesDatabaseManager;
import com.exam.myapplication.data.model.Node;

import java.util.List;

public class NodeRepositoryImpl implements NodeRepository {
    private final NodesDatabaseManager databaseManager;

    public NodeRepositoryImpl(NodesDatabaseManager databaseManager){
        this.databaseManager = databaseManager;
    }
    @Override
    public LiveData<List<Node>> getNodesLiveData() {
        return databaseManager.nodesLiveData;
    }

    public LiveData<Node> getNode(long id) {
        return databaseManager.getNode(id);
    }

    @Override
    public void addNode(Node node) {
        databaseManager.insert(node);
    }

    @Override
    public void updateNode(Node node) {
        databaseManager.update(node);
    }

    @Override
    public void deleteNode(long id) {
        databaseManager.delete(id);
    }
}
