package com.example.loginregister;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{
    private Context context = null;
    private ArrayList <Post> postArr = null;
    private PostViewListener postViewListener = null;
    private LayoutInflater inflater;
    private View showView;

    public PostAdapter(Context context, ArrayList<Post> postArr, PostViewListener postViewListener) {
        this.context = context;
        this.postArr = postArr;
        this.postViewListener = postViewListener;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_post_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i){
        viewHolder.titleView.setText(postArr.get(i).getPostTitle());
        viewHolder.menuView.setText(postArr.get(i).getPostMenu());
        viewHolder.locationView.setText(postArr.get(i).getPostEatLocation());
        viewHolder.peopleNumView.setText(postArr.get(i).getPostPeopleNum());
        viewHolder.endTimeView.setText(postArr.get(i).getPostEndTime());
        viewHolder.writtenDateView.setText(postArr.get(i).getPostWrittenDate());
    }

    @Override
    public int getItemCount() {
        return postArr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView titleView = null;
        public TextView menuView = null;
        public TextView locationView = null;
        public TextView peopleNumView = null;
        public TextView endTimeView = null;
        public TextView writtenDateView = null;

        public ViewHolder(@NonNull View view){
            super(view);
            titleView = (TextView) view.findViewById(R.id.title);
            menuView = (TextView) view.findViewById(R.id.menu);
            locationView = (TextView) view.findViewById(R.id.location);
            peopleNumView = (TextView) view.findViewById(R.id.peopleNum);
            endTimeView = (TextView) view.findViewById(R.id.endTime);
            writtenDateView = (TextView) view.findViewById(R.id.writtenDate);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Post post = postArr.get(pos);

                        Intent intent = new Intent((RecyclerviewActivity)RecyclerviewActivity.recyclerContext, ShowPostActivity.class);
                        intent.putExtra("post",post);

                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        RecyclerviewActivity.recyclerContext.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            postViewListener.onPostClick(getAdapterPosition(), v);
        }

    }
}
