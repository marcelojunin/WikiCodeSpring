package br.com.wikicode.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.wikicode.domain.Post;
import br.com.wikicode.domain.Subcategory;
import br.com.wikicode.dto.PostDTO;
import br.com.wikicode.service.PostService;

@RestController
@RequestMapping("/api/posts")
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
		Post object = postService.save(dto);
		
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(object.getId())
					.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> findOne(@PathVariable String id) {
		Post post = postService.findOne(id);
		PostDTO dto = PostDTO.fromDTO(post);
		return ResponseEntity.ok().body(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable String id, @RequestBody PostDTO dto) {
		final Post post = postService.findOne(id);
		post.setDescription(dto.getDescription());
		post.setTitle(dto.getTitle());
		postService.update(post);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		postService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/paginate")
	public ResponseEntity<Page<Post>> paginate(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC")String direction){
		Page<Post> list = postService.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/fingByTitle")
	public ResponseEntity<List<Post>> findByTitle(
			@RequestParam("categoryId") String categoryId,
			@RequestParam("subcategoryId") String subcategoryId,
			@RequestParam("title") String title) {
		List<Post> list = postService.findByTitle(categoryId, subcategoryId, title);
		return ResponseEntity.ok(list);
	}

	@GetMapping("/addView/{id}")
	public ResponseEntity<Void> addView(@PathVariable String id) {
		postService.addView(id);
		return ResponseEntity.ok().build();
	}
}
