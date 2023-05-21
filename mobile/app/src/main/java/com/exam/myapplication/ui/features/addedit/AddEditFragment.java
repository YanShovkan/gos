package com.exam.myapplication.ui.features.addedit;

import static androidx.navigation.ViewKt.findNavController;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exam.myapplication.databinding.AddEditFragmentBinding;

public class AddEditFragment extends Fragment {

    private AddEditViewModel viewModel;
    private AddEditFragmentBinding binding;
    private AddEditFragmentArgs args;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AddEditFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(
                this,
                ViewModelProvider.Factory.from(AddEditViewModel.initializer)
        ).get(AddEditViewModel.class);

        args = AddEditFragmentArgs.fromBundle(getArguments());
        if (args.getId() != NO_ID_VALUE) {
            viewModel.getNode(args.getId()).observe(getViewLifecycleOwner(), node -> {
                if (node != null) {
                    binding.etTitle.setText(node.getTitle());
                    binding.etDescription.setText(node.getDescription());
                }
            });
        }

        binding.btnSubmit.setOnClickListener(v -> {
            if (args.getId() == NO_ID_VALUE) {
                viewModel.addNode(
                        binding.etTitle.getText().toString(),
                        binding.etDescription.getText().toString()
                );
            } else {
                viewModel.updateNode(
                        args.getId(),
                        binding.etTitle.getText().toString(),
                        binding.etDescription.getText().toString()
                );
            }
            findNavController(binding.getRoot()).navigateUp();
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    public static final long NO_ID_VALUE = -1L;
}