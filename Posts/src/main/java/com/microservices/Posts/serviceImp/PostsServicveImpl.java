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
	@Autowired
	PostModel postModel1;
	@Autowired
	PostModel postModel2;
	@Override
	public List<PostModel> getPosts() 
	{
		List<PostModel> returnValue = new ArrayList<>();
		postModel1.setPostId(UUID.randomUUID().toString());
		postModel1.setPostTitle("Demo post");
		postModel1.setPostDescription("This is the first demo post");
		postModel1.setPostDate(new Date());
		
		postModel2.setPostId(UUID.randomUUID().toString());
		postModel2.setPostTitle("Demo post");
		postModel2.setPostDescription("This is the second demo post");
		postModel2.setPostDate(new Date());
		
		returnValue.add(postModel1);
		returnValue.add(postModel2);
		
		return returnValue;
	}

}
