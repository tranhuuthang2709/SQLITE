package com.example.sqlite_maytinh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class computerAdapter extends BaseAdapter {
    private MainActivity context;
    private  int layout;
    private List<computer> computerList;

    public computerAdapter(MainActivity context, int layout, List<computer> computerList) {
        this.context = context;
        this.layout = layout;
        this.computerList = computerList;
    }

    @Override
    public int getCount() {
        return computerList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
private class ViewHolder{
        TextView txtTen;
        ImageView imgDelete, imgEdit;
}
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view ==null){
            holder = new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view  = inflater.inflate(layout,null);
            holder.txtTen=(TextView) view.findViewById(R.id.textviewTen);
            holder.imgDelete =(ImageView) view.findViewById(R.id.imageviewDelete);
            holder.imgEdit =(ImageView) view.findViewById(R.id.imageviewEdit);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        computer computer = computerList.get(i);
        holder.txtTen.setText(computer.getTencomuter());

        // xoa sua
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogSuacomputer(computer.getTencomuter(),computer.getIdcomputer());
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogXoacomputer(computer.getTencomuter(),computer.getIdcomputer());
            }
        });
        return view;
    }
}
