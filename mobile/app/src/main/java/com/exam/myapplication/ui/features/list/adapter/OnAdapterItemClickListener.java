package com.exam.myapplication.ui.features.list.adapter;

import com.exam.myapplication.data.model.Node;

public interface OnAdapterItemClickListener {
    void onClick(Node node);

    void onDeleteClick(long id);
}
