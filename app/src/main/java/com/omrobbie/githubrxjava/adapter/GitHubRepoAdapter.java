package com.omrobbie.githubrxjava.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omrobbie.githubrxjava.R;
import com.omrobbie.githubrxjava.data.GitHubRepo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by omrobbie on 07/12/2017.
 */

public class GitHubRepoAdapter extends RecyclerView.Adapter<GitHubRepoAdapter.ViewHolder> {

    private List<GitHubRepo> list;

    public GitHubRepoAdapter(List<GitHubRepo> list) {
        this.list = list;
    }

    public void replaceAll(List<GitHubRepo> items) {
        list = items;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_repo, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_repo_name)
        TextView tv_repo_name;

        @BindView(R.id.tv_repo_desc)
        TextView tv_repo_desc;

        @BindView(R.id.tv_repo_lang)
        TextView tv_repo_lang;

        @BindView(R.id.tv_repo_stars)
        TextView tv_repo_stars;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(GitHubRepo item) {
            tv_repo_name.setText(item.getName());
            tv_repo_desc.setText(item.getDescription());
            tv_repo_lang.setText(itemView.getContext().getString(R.string.label_lang) + " " + item.getLanguage());
            tv_repo_stars.setText(itemView.getContext().getString(R.string.label_stars) + " " + item.getStargazersCount());
        }
    }
}
