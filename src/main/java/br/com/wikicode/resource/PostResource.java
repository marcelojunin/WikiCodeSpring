package br.com.wikicode.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.wikicode.domain.Post;
import br.com.wikicode.domain.Subcategory;
import br.com.wikicode.dto.PostDTO;
import br.com.wikicode.service.PostService;

@RestController
@RequestMapping("/v1/posts")
public class PostResource {

	@Autowired
	private PostService postService;
	
	@GetMapping
	public ResponseEntity<List<Post>> getAll() {
		List<Post> list = postService.getAll();
		return ResponseEntity.ok(list);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody PostDTO dto) {
		Subcategory subcategory = new Subcategory(dto.getSubcategoryId());
		Post post = new Post(null, dto.getTitle(), dto.getDescription(), subcategory);
		Post object = postService.save(post);
		
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(object.getId())
					.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> findOne(@PathVariable String id) {
		Post post = postService.findOne(id);
		return ResponseEntity.ok().body(post);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable String id, PostDTO dto) {
		Subcategory subcategory = new Subcategory(dto.getSubcategoryId());
		Post post = new Post(null, dto.getTitle(), dto.getDescription(), subcategory);
		postService.update(id, post);
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<?> delete(@PathVariable String id) {
		postService.delete(id);
		return ResponseEntity.noContent().build();		
	}
}
