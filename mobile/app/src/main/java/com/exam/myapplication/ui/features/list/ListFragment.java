package com.exam.myapplication.ui.features.list;

import static androidx.navigation.ViewKt.findNavController;
import static com.exam.myapplication.ui.features.addedit.AddEditFragment.NO_ID_VALUE;
import static com.exam.myapplication.ui.features.report.ReportActivity.KEY_LIST;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exam.myapplication.data.model.Node;
import com.exam.myapplication.databinding.ListFragmentBinding;
import com.exam.myapplication.ui.features.list.adapter.ListAdapter;
import com.exam.myapplication.ui.features.list.adapter.OnAdapterItemClickListener;
import com.exam.myapplication.ui.features.report.ReportActivity;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ListFragment extends Fragment {
    private ListFragmentBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = ListFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListViewModel viewModel = new ViewModelProvider(
                this,
                ViewModelProvider.Factory.from(ListViewModel.initializer)
        ).get(ListViewModel.class);

        ListAdapter listAdapter = new ListAdapter();
        listAdapter.setListener(new OnAdapterItemClickListener() {
            @Override
            public void onClick(Node node) {
                findNavController(binding.getRoot()).navigate(ListFragmentDirections.actionListFragmentToAddEditFragment(node.getId()));
            }

            @Override
            public void onDeleteClick(long id) {
                viewModel.deleteNode(id);
            }
        });
        binding.rvList.setAdapter(listAdapter);

        viewModel.getNotesLiveData().observe(getViewLifecycleOwner(), listAdapter::submitItems);

        binding.fabAdd.setOnClickListener(v ->
                findNavController(binding.getRoot()).navigate(ListFragmentDirections.actionListFragmentToAddEditFragment(NO_ID_VALUE)));

        binding.btnSubmitFilter.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ReportActivity.class);
            Bundle args = new Bundle();

            ArrayList<Node> nodes = listAdapter.getList().stream().filter(n -> n.getTitle().contains(
                    binding.etFilter.getText().toString().toLowerCase()
            )).collect(Collectors.toCollection(ArrayList::new));

            args.putParcelableArrayList(KEY_LIST, nodes);
            intent.putExtras(args);
            startActivity(intent);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}