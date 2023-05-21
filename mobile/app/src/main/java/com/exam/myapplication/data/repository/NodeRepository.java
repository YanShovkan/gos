package com.exam.myapplication.data.repository;

import androidx.lifecycle.LiveData;

import com.exam.myapplication.data.model.Node;

import java.util.List;

public interface NodeRepository {
    LiveData<List<Node>> getNodesLiveData();

    LiveData<Node> getNode(long id);

    void addNode(Node node);

    void updateNode(Node node);

    void deleteNode(long id);
}
