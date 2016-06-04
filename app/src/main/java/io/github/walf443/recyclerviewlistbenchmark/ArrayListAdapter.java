package io.github.walf443.recyclerviewlistbenchmark;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class ArrayListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<Integer> items = new ArrayList<>();

    public ArrayListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        Integer item;
        long start = System.nanoTime();
        item = items.get(position);
        Log.d("benchmark", "get(" + String.valueOf(position) + "):" + String.valueOf((System.nanoTime() - start) / 1000000f) + "ms");
        viewHolder.updateItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(List<Integer> items) {
        int startIndex = this.items.size();
        this.items.addAll(items);
        notifyItemRangeInserted(startIndex, items.size());
    }

    public void unshiftItems(ArrayList<Integer> list) {
        this.items.addAll(0, list);
        notifyItemRangeInserted(0, list.size());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.text_view);
        }

        public void updateItem(Integer number) {
            textView.setText(String.valueOf(number));
        }
    }
}
