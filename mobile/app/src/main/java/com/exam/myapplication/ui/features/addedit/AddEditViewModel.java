package com.exam.myapplication.ui.features.addedit;

import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.exam.myapplication.App;
import com.exam.myapplication.data.model.Node;
import com.exam.myapplication.data.repository.NodeRepository;

public class AddEditViewModel extends ViewModel {
    private final NodeRepository nodeRepository;

    private AddEditViewModel(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public LiveData<Node> getNode(long id) {
        return nodeRepository.getNode(id);
    }

    public void addNode(String title, String description) {
        nodeRepository.addNode(new Node(title, description));
    }

    public void updateNode(long id, String title, String description) {
        nodeRepository.updateNode(new Node(id, title, description));
    }

    static final ViewModelInitializer<AddEditViewModel> initializer = new ViewModelInitializer<>(
            AddEditViewModel.class,
            creationExtras -> {
                App app = (App) creationExtras.get(APPLICATION_KEY);
                assert app != null;
                return new AddEditViewModel(app.getNodeRepository());
            }
    );
}