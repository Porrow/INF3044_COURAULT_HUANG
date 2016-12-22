package net.porrow.tfchat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MesHolder extends RecyclerView.ViewHolder {

	private Context c;
	public TextView tv;

	public MesHolder(View itemView) {
		super(itemView);
		c = itemView.getContext();
		tv =  (TextView)itemView.findViewById(R.id.rv_tv);
	}
}
