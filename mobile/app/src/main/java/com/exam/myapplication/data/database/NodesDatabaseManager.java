package com.exam.myapplication.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.exam.myapplication.data.model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

public class NodesDatabaseManager {
    private DatabaseHelper dbHelper;

    private final Context context;

    private SQLiteDatabase database;

    public NodesDatabaseManager(Context context) {
        this.context = context;
    }

    public NodesDatabaseManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        loadNodes();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public MutableLiveData<List<Node>> nodesLiveData = new MutableLiveData<>(new ArrayList<>());

    private void loadNodes() {
        Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME, null);
        List<Node> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                list.add(
                        new Node(
                                cursor.getLong(0),
                                cursor.getString(1),
                                cursor.getString(2)
                        )
                );
            } while (cursor.moveToNext());
        }
        nodesLiveData.postValue(list);
        cursor.close();
    }

    public LiveData<Node> getNode(long id) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE  id=" + id, null);
        Node node = null;
        if (cursor.moveToFirst()) {
            node = new Node(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );
        }
        MutableLiveData<Node> liveData = new MutableLiveData<>();
        if (node != null) {
            liveData.postValue(node);
        }
        return liveData;
    }

    public void insert(Node node) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.TITLE, node.getTitle());
        contentValue.put(DatabaseHelper.DESCRIPTION, node.getDescription());
        long id = database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);

        List<Node> nodes = nodesLiveData.getValue();
        if (nodes != null) {
            nodes.add(node.copy(id, null, null));
            nodesLiveData.postValue(nodes);
        }
    }

    public void update(Node node) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TITLE, node.getTitle());
        contentValues.put(DatabaseHelper.DESCRIPTION, node.getDescription());
        long id = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper.ID + " = " + node.getId(), null);

        List<Node> nodes = new ArrayList<>(Objects.requireNonNull(nodesLiveData.getValue()));
        OptionalInt optIndex = IntStream.range(0, nodes.size())
                .filter(i -> nodes.get(i).getId().equals(node.getId()))
                .findFirst();
        int index = -1;
        if (optIndex.isPresent()) {
            index = optIndex.getAsInt();
        }
        if (index != -1) {
            nodes.set(index, node.copy(id, null, null));
            nodesLiveData.postValue(nodes);
        }
    }

    public void delete(long id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.ID + "=" + id, null);

        List<Node> nodes = new ArrayList<>(Objects.requireNonNull(nodesLiveData.getValue()));
        OptionalInt optIndex = IntStream.range(0, nodes.size())
                .filter(i -> nodes.get(i).getId().equals(id))
                .findFirst();
        int index = -1;
        if (optIndex.isPresent()) {
            index = optIndex.getAsInt();
        }
        if (index != -1) {
            nodes.remove(index);
            nodesLiveData.postValue(nodes);
        }
    }
}
