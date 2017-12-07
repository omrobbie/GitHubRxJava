package com.omrobbie.githubrxjava.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by omrobbie on 07/12/2017.
 */

public class GitHubClient {

    private static final String GITHUB_BASE_URL = "https://api.github.com/";

    private static GitHubClient gitHubClient;
    private GitHubService gitHubService;

    public GitHubClient() {
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GITHUB_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        gitHubService = retrofit.create(GitHubService.class);
    }

    public static GitHubClient getGitHubClient() {
        if (gitHubClient == null) gitHubClient = new GitHubClient();
        return gitHubClient;
    }

    public GitHubService getGitHubService() {
        return gitHubService;
    }
}
