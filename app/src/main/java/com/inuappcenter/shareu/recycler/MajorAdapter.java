package com.inuappcenter.shareu.recycler;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.inuappcenter.shareu.R;
import com.inuappcenter.shareu.activity.MajorActivity;
import com.inuappcenter.shareu.fragment.MajorFragment2;
import com.inuappcenter.shareu.my_class.Code;
import com.inuappcenter.shareu.my_class.Major;
import com.inuappcenter.shareu.my_interface.OnItemClick;
import com.viethoa.RecyclerViewFastScroller;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class MajorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements RecyclerViewFastScroller.BubbleTextGetter{

    private Context mContext;
    private ArrayList<Major> mitems;

    private FragmentManager fragmentManager;
    private MajorFragment2 fragmentMajor2;
    private FragmentTransaction transaction;


    private String name;
    public MajorAdapter(Context mContext, ArrayList<Major> mitems) {
        this.mContext = mContext;

        this.mitems = mitems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(viewType == Code.ViewType.INDEX)
        {
            view = inflater.inflate(R.layout.item_recyclerview_index, parent, false);
            return new IndexViewHolder(view);
        }
        else
        {
            view = inflater.inflate(R.layout.item_recyclerview_major, parent, false);
            return new MajorViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if(viewHolder instanceof IndexViewHolder)
        {
            ((IndexViewHolder) viewHolder).index.setText(mitems.get(position).getFirst());
        }
        else
        {

            ((MajorViewHolder) viewHolder).first.setText(mitems.get(position).getFirst());
            ((MajorViewHolder) viewHolder).second.setText(mitems.get(position).getSecond());
            ((MajorViewHolder) viewHolder).line_notice.setBackgroundColor(mContext.getResources().getColor(mitems.get(position).getLine()));
           viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {


                 //Toast.makeText(mContext,"sibal",Toast.LENGTH_LONG).show();
                  /* Intent intent = new Intent(mContext,MajorActivity2.class);
                   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   intent.putExtra("select_major",mitems.get(position).getFirst());
                   mContext.startActivity(intent);
                   ((Activity)mContext).overridePendingTransition(R.anim.slide_right,R.anim.hold);
*/
                   fragmentMajor2 = new MajorFragment2();
                   Bundle bundle = new Bundle();
                   bundle.putString("name",((MajorViewHolder) viewHolder).first.getText().toString());
                   name = ((MajorViewHolder) viewHolder).first.getText().toString();
                   fragmentMajor2.setArguments(bundle);
                   fragmentManager =((MajorActivity)mContext).getSupportFragmentManager();
                   transaction = fragmentManager.beginTransaction();

                   transaction.replace(R.id.layout_frame_category,fragmentMajor2);
                   transaction.addToBackStack(null);
                   transaction.commitAllowingStateLoss();
               }
           });
        }

    }

    @Override
    public int getItemCount() {
        return null!=mitems?mitems.size():0;
        //return mitems.size();
    }
    @Override
    public int getItemViewType(int position) {
        return mitems.get(position).getViewType();
    }

    public class IndexViewHolder extends RecyclerView.ViewHolder{
        TextView index;
        IndexViewHolder(View itemView)
        {
            super(itemView);
            index = itemView.findViewById(R.id.tv_major_index);
        }
    }

    public class MajorViewHolder extends RecyclerView.ViewHolder{
        TextView first;
        TextView second;
        View line_notice;
        MajorViewHolder(View itemView)
        {
            super(itemView);
            first = itemView.findViewById(R.id.tv_major_first);
            second=itemView.findViewById(R.id.tv_major_second);
            line_notice = itemView.findViewById(R.id.line_notice);


        }
    }

    @Override
    public String getTextToShowInBubble(int pos) {
        return null;
    }
}
