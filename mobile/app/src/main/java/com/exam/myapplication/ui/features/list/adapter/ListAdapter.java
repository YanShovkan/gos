package com.exam.myapplication.ui.features.list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exam.myapplication.data.model.Node;
import com.exam.myapplication.databinding.ListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListItemViewHolder> {

    private List<Node> list = new ArrayList<>();
    private OnAdapterItemClickListener listener;

    public List<Node> getList() {
        return list;
    }

    public void setListener(OnAdapterItemClickListener listener) {
        this.listener = listener;
    }

    public void submitItems(List<Node> items) {
        list = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListItemViewHolder(
                ListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ListItemViewHolder extends RecyclerView.ViewHolder {
        private final ListItemBinding binding;

        public ListItemViewHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Node node) {
            if (listener != null) {
                binding.getRoot().setOnClickListener(v -> listener.onClick(node));
                binding.btnDelete.setOnClickListener(v -> listener.onDeleteClick(node.getId()));
            } else {
                binding.btnDelete.setVisibility(View.GONE);
            }
            binding.itemTitle.setText(node.getTitle());
            binding.itemDescription.setText(node.getDescription());
        }
    }
}

