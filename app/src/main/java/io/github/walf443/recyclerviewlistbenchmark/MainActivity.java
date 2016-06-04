package io.github.walf443.recyclerviewlistbenchmark;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerView;
    private FloatingActionButton addButton;
    private ArrayListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        swipeRefresh = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        addButton = (FloatingActionButton)findViewById(R.id.add_button);

        setupRecyclerView();
        setupSwipeRefresh();
        setupAddButton();
        addList();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ArrayListAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void setupSwipeRefresh() {
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int i = 0; i < 30; i++) {
                    list.add(i);
                }

                long start = System.nanoTime();
                adapter.unshiftItems(list);
                Log.d("benchmark", "unshiftItems: " + String.valueOf((System.nanoTime() - start) / 1000000f) + "ms");
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void setupAddButton() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "clicked!!");
                addList();
            }
        });
    }

    private void addList() {
        ArrayList<Integer> list = new ArrayList();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }

        long start = System.nanoTime();
        adapter.addItems(list);
        Log.d("benchmark", "addList: " + String.valueOf((System.nanoTime() - start) / 1000000f) + "ms");
    }
}
