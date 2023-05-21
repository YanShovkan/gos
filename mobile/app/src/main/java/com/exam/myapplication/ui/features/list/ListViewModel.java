package com.exam.myapplication.ui.features.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.exam.myapplication.App;
import com.exam.myapplication.data.model.Node;
import com.exam.myapplication.data.repository.NodeRepository;

import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import java.util.List;

public class ListViewModel extends ViewModel {
    private final NodeRepository nodeRepository;

    private ListViewModel(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    LiveData<List<Node>> getNotesLiveData() {
        return nodeRepository.getNodesLiveData();
    }

    public void deleteNode(long id){
        nodeRepository.deleteNode(id);
    }

    static final ViewModelInitializer<ListViewModel> initializer = new ViewModelInitializer<>(
            ListViewModel.class,
            creationExtras -> {
                App app = (App) creationExtras.get(APPLICATION_KEY);
                assert app != null;
                return new ListViewModel(app.getNodeRepository());
            }
    );
}