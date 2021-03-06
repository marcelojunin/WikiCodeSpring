package br.com.wikicode.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.wikicode.domain.Comment;
import br.com.wikicode.domain.Post;

public class PostDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String title;
	private String description;
	private String subcategoryId;
	private String categoryId;
	private List<Comment> comments = new ArrayList<>();
	
	public PostDTO() { }
	
	public PostDTO(String title, String description, String subcategoryId) {
		this.title = title;
		this.description = description;
		this.subcategoryId = subcategoryId;
	}
	
	public PostDTO(Post post) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.description = post.getDescription();
		this.subcategoryId = post.getSubcategory().getId();
		this.categoryId = post.getSubcategory().getCategory().getId();
		this.comments = post.getComments();
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSubcategoryId() {
		return subcategoryId;
	}
	public void setSubcategoryId(String subcategoryId) {
		this.subcategoryId = subcategoryId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public static PostDTO fromDTO(Post post) {
		return new PostDTO(post);
	}
}
