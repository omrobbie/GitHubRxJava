package com.omrobbie.githubrxjava.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by omrobbie on 07/12/2017.
 */

public class GitHubRepo implements Parcelable {

    private int id;
    private String name;
    private String htmlUrl;
    private String description;
    private String language;
    private int stargazersCount;

    public GitHubRepo() {
    }

    public GitHubRepo(int id, String name, String htmlUrl, String description, String language, int stargazersCount) {
        this.id = id;
        this.name = name;
        this.htmlUrl = htmlUrl;
        this.description = description;
        this.language = language;
        this.stargazersCount = stargazersCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.htmlUrl);
        dest.writeString(this.description);
        dest.writeString(this.language);
        dest.writeInt(this.stargazersCount);
    }

    protected GitHubRepo(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.htmlUrl = in.readString();
        this.description = in.readString();
        this.language = in.readString();
        this.stargazersCount = in.readInt();
    }

    public static final Parcelable.Creator<GitHubRepo> CREATOR = new Parcelable.Creator<GitHubRepo>() {
        @Override
        public GitHubRepo createFromParcel(Parcel source) {
            return new GitHubRepo(source);
        }

        @Override
        public GitHubRepo[] newArray(int size) {
            return new GitHubRepo[size];
        }
    };
}
