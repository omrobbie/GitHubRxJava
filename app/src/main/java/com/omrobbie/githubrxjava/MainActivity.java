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
import com.omrobbie.githubrxjava.data.GitHubRepo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_repos)
    RecyclerView rv_repos;

    @BindView(R.id.et_search)
    EditText et_search;

    @BindView(R.id.btn_search)
    Button btn_search;

    private List<GitHubRepo> list;
    private GitHubRepoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupEnv();
        setupList();
    }

    private void setupEnv() {
        ButterKnife.bind(this);
        list = new ArrayList<>();
        adapter = new GitHubRepoAdapter(list);

        btn_search.setOnClickListener(view -> {
            String username = et_search.getText().toString();

            if (!username.isEmpty()) {
                Toast.makeText(this, "Anda mencari repo bernama " + username, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupList() {
        rv_repos.setLayoutManager(new LinearLayoutManager(this));
        rv_repos.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv_repos.setAdapter(adapter);
    }
}
