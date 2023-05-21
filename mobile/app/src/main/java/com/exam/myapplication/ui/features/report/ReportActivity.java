package com.exam.myapplication.ui.features.report;

import android.os.Bundle;

import com.exam.myapplication.data.model.Node;
import com.exam.myapplication.ui.features.list.adapter.ListAdapter;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.exam.myapplication.databinding.ReportActivityBinding;

import com.exam.myapplication.R;

import java.util.List;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ReportActivityBinding binding = ReportActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            ListAdapter adapter = new ListAdapter();
            List<Node> nodes = extras.getParcelableArrayList(KEY_LIST);
            adapter.submitItems(nodes);
            binding.rvList.setAdapter(adapter);
        }
    }

    public static final String KEY_LIST = "key_list";
}