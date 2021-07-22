package com.microservices.UsersApi.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.UsersApi.model.PostModel;

@FeignClient(name = "posts-ws")
public interface PostFeignClient {
	
	@GetMapping("/users/{id}/posts")
	public List<PostModel> getPosts(@PathVariable String id);
}
