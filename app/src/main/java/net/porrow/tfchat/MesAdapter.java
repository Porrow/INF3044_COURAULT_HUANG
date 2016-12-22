package net.porrow.tfchat;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MesAdapter extends RecyclerView.Adapter<MesHolder> {

	private Context c;
	private ArrayList<String> conv;

	public MesAdapter(Context c, ArrayList<String> conv) {
		this.c = c;
		this.conv = conv;
	}

	@Override
	public MesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View v = inflater.inflate(R.layout.rv_display_element, null);
		return new MesHolder(v);
	}

	@Override
	public void onBindViewHolder(MesHolder holder, int position) {
		String line = conv.get(position);
		holder.tv.setText(line);
		String n = Connection.name;
		if(line.length() > n.length()+1)
			if (line.substring(0, n.length()).equals(n))
				holder.tv.setTextColor(Color.BLUE);
			else
				holder.tv.setTextColor(Color.rgb(0, 124, 2));
		else
			holder.tv.setTextColor(Color.rgb(0, 124, 2));
	}

	public void update(){
		notifyDataSetChanged();
	}

	@Override
	public int getItemCount() {
		return conv.size();
	}
}
