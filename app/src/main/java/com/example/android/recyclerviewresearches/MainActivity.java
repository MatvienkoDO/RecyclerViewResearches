package com.example.android.recyclerviewresearches;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final int ITEMS_COUNT = 50;
    private static int viewHolderCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new MyAdapter(ITEMS_COUNT));
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setHasFixedSize(true);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private int itemCount;

        public MyAdapter(int itemCount) {
            this.itemCount = itemCount;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, null);
            final MyViewHolder itemViewHolder = new MyViewHolder(itemView);
            itemView.setTag(itemViewHolder);
            return itemViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return itemCount;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            private final TextView itemNumberView;
            private final int viewHolderNumber;
            public MyViewHolder(View itemView) {
                super(itemView);
                itemNumberView = itemView.findViewById(R.id.item_number);
                viewHolderNumber = viewHolderCounter++;
                ((TextView)itemView.findViewById(R.id.view_holder_number)).setText(String.valueOf(viewHolderNumber));
                itemView.findViewById(R.id.item_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println("Item number is " + itemNumberView.getText() +
                                "\nViewHolder number is " + viewHolderNumber +
                                "\nviewHolderCounter is " + viewHolderCounter);
                    }
                });
            }

            public void bind(int position){
                itemNumberView.setText(String.valueOf(position));
            }
        }
    }
}
