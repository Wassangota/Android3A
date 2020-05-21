package com.github.wassilkhetim.android.presentation.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.wassilkhetim.android.R;
import com.github.wassilkhetim.android.presentation.model.PersonnageInfo;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<PersonnageInfo> values;
    private View parent;
    private Context contextParentRow;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView txtHeader;
        TextView txtFooter;
        ImageView imageView;
        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            parent = layout;
            imageView = (ImageView) v.findViewById(R.id.icon);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }
    }

    public void add(int position, PersonnageInfo item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(List<PersonnageInfo> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        contextParentRow = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final PersonnageInfo name = values.get(position);
        if(name.getImage() != null && !name.getImage().equals("")) Glide.with(contextParentRow).load(name.getImage()).circleCrop().into(holder.imageView);
        holder.txtHeader.setText(name.getName());
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(), PersonnageInfoActivity.class);
                intent.putExtra("name", name.getName());
                intent.putExtra("status", name.getStatus());
                intent.putExtra("species", name.getSpecies());
                intent.putExtra("origin", name.getOrigin().getName());
                intent.putExtra("location", name.getLocation().getName());
                intent.putExtra("image", name.getImage());
                parent.getContext().startActivity(intent);
            }
        });

        holder.txtFooter.setText("Origin: " + name.getOrigin().getName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}