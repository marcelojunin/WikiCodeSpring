package br.com.wikicode.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.wikicode.domain.Post;
import br.com.wikicode.reposiroty.PostRepository;
import br.com.wikicode.service.PostService;

public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public List<Post> getAll() {
		return postRepository.findAll();
	}

	@Override
	public Post save(Post post) {
		return postRepository.save(post);
	}

	@Override
	public Post findOne(String id) {
		return postRepository.findOne(id);
	}

	@Override
	public void update(String id, Post post) {
		Post object = findOne(id);
		object.setDescription(post.getDescription());
		object.setTitle(post.getTitle());
		object.setSubcategory(post.getSubcategory());
		save(object);
	}

	@Override
	public void delete(String id) {
		postRepository.delete(id);
	}

}
