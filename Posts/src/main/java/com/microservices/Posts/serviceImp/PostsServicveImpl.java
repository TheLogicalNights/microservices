package com.microservices.Posts.serviceImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.Posts.model.PostModel;
import com.microservices.Posts.service.PostsService;

@Service
public class PostsServicveImpl implements PostsService {
	@Override
	public List<PostModel> getPosts(String id) 
	{
		List<PostModel> returnValue = new ArrayList<>();
		PostModel postModel1 = new PostModel();
		postModel1.setPostId(UUID.randomUUID().toString());
		postModel1.setUserId(id);
		postModel1.setPostTitle("Demo post 1");
		postModel1.setPostDescription("This is the first demo post");
		postModel1.setPostDate(new Date());
		
		PostModel postModel2 = new PostModel();
		postModel2.setPostId(UUID.randomUUID().toString());
		postModel2.setUserId(id);
		postModel2.setPostTitle("Demo post 2");
		postModel2.setPostDescription("This is second demo post");
		postModel2.setPostDate(new Date());
		
		returnValue.add(postModel1);
		returnValue.add(postModel2);
		
		return returnValue;
	}

}
