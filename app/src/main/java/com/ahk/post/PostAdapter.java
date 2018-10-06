package com.ahk.post;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder>{
    List<Post> posts;

    public PostAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(parent.getContext()).inflate(R.layout.post_layout,parent,false);
        PostHolder holder=new PostHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        Post post=posts.get(position);
        holder.writer.setText(post.writer);
        holder.postContent.setText(post.postContent);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
    public class PostHolder extends RecyclerView.ViewHolder{
        TextView writer,postContent;

        public PostHolder(View itemView) {
            super(itemView);
            writer=(TextView) itemView.findViewById(R.id.txt_writer);
            postContent=(TextView)itemView.findViewById(R.id.txt_postcontent);

        }
    }
}
