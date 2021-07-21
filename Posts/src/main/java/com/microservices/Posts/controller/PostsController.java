package com.microservices.Posts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.Posts.model.PostModel;
import com.microservices.Posts.serviceImp.PostsServicveImpl;

@RestController
@RequestMapping("users/{id}/posts")
public class PostsController {
	
	@Autowired
	PostsServicveImpl postServiceImplObj;

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<PostModel>> getPosts(@PathVariable String id)
	{
		List<PostModel> returnValue = postServiceImplObj.getPosts(id);
		return new ResponseEntity<List<PostModel>>(returnValue, HttpStatus.OK);
	}
}
