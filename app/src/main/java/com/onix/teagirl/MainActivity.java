package com.onix.teagirl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseRecyclerAdapter adapter;
    private String name,pos,order,details,time,dp;
    public  TextView txtdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout root;
        TextView txtName;
        TextView txtPos;
        TextView txtOrder;
        TextView txtDetails;
        TextView txtTime;

        ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            txtName = itemView.findViewById(R.id.list_name);
            txtPos = itemView.findViewById(R.id.list_pos);
            txtOrder = itemView.findViewById(R.id.list_order);
            txtDetails = itemView.findViewById(R.id.list_details);
            txtTime = itemView.findViewById(R.id.list_time);
            txtdp = itemView.findViewById(R.id.dp_tv);

        }

        void setTxtName(String string) {
            txtName.setText(string);
        }

        void setTxtOrder(String string) {
            txtOrder.setText(string);
        }

        void setTxtDetails(String string) {
            txtDetails.setText(string);
        }

        void setTxtPos(String string) {
            txtPos.setText(string);
        }

        void setTxtTime(String string) {txtTime.setText(string);}

    }

    private void fetch() {
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Orders").child("Pending");

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(query, new SnapshotParser<Model>() {
                            @NonNull
                            @Override
                            public Model parseSnapshot(@NonNull DataSnapshot snapshot) {
                                    return new Model(
                                            snapshot.child("Username").getValue().toString(),
                                            snapshot.child("Username").getValue().toString(),
                                            snapshot.child("Position").getValue().toString(),
                                            snapshot.child("Order")   .getValue().toString(),
                                            snapshot.child("Details") .getValue().toString(),
                                            snapshot.child("Time")    .getValue().toString(),
                                            snapshot.child("Time")    .getValue().toString()

                                    );
                            }
                        })
                        .build();

        adapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(options) {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item, parent, false);

                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(ViewHolder holder, final int position, Model model) {
                name    = model.getmName();
                pos     = model.getmPos();
                order   = model.getmOrder();
                details = model.getmDetails();
                time    = model.getmTime();
                dp = String.valueOf(name.charAt(0));


                String mNull = "null";

                if (name != null)holder.setTxtName(name);
                else holder.setTxtName(mNull);
                if (pos != null)holder.setTxtPos(pos);
                else holder.setTxtPos(mNull);

                holder.setTxtOrder(order);
                holder.setTxtDetails(details);
                holder.setTxtTime(time);
                txtdp.setText(dp);

                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}