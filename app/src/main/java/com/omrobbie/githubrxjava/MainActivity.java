package com.omrobbie.githubrxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.omrobbie.githubrxjava.adapter.GitHubRepoAdapter;
import com.omrobbie.githubrxjava.api.GitHubClient;
import com.omrobbie.githubrxjava.data.GitHubRepo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_repos)
    RecyclerView rv_repos;

    @BindView(R.id.et_search)
    EditText et_search;

    @BindView(R.id.btn_search)
    Button btn_search;

    private List<GitHubRepo> list;
    private GitHubRepoAdapter adapter;

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupEnv();
        setupList();
    }

    @Override
    protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
        super.onDestroy();
    }

    private void setupEnv() {
        ButterKnife.bind(this);
        list = new ArrayList<>();
        adapter = new GitHubRepoAdapter(list);

        btn_search.setOnClickListener(view -> {
            String username = et_search.getText().toString();
            if (!username.isEmpty()) loadStarredRepo(username);
        });
    }

    private void setupList() {
        rv_repos.setLayoutManager(new LinearLayoutManager(this));
        rv_repos.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv_repos.setAdapter(adapter);
    }

    private void loadStarredRepo(String username) {
        subscription = GitHubClient.getGitHubClient()
                .getGitHubService().getStarredRepositories(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GitHubRepo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        loadFailed();
                    }

                    @Override
                    public void onNext(List<GitHubRepo> gitHubRepos) {
                        adapter.replaceAll(gitHubRepos);
                    }
                });
    }

    private void loadFailed() {
        Toast.makeText(this, "Gagal mengunduh data!", Toast.LENGTH_SHORT).show();
    }
}
