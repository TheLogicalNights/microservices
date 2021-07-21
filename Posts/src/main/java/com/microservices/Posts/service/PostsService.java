package com.microservices.Posts.service;

import java.util.List;

import com.microservices.Posts.model.PostModel;

public interface PostsService {
	public List<PostModel> getPosts(String id);
}
