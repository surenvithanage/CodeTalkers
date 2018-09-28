package com.example.krishna.codetalkers;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StoreAdaptor extends RecyclerView.Adapter<StoreAdaptor.StoreViewHolder> {

    private Context mContex;
    private Cursor mCursor;

    public StoreAdaptor(Context context, Cursor cursor){
        mContex = context;
        mCursor = cursor;
    }
    public class StoreViewHolder extends RecyclerView.ViewHolder{

        public TextView nameText;
        public TextView countText;

        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.)
        }
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder storeViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
