package br.com.wikicode.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wikicode.domain.Wiki;
import br.com.wikicode.dto.WikiDTO;
import br.com.wikicode.service.WikiService;

@RestController
@RequestMapping("/api/wikis")
public class WikiResource {

	@Autowired
	private WikiService wikiService;
	
	@GetMapping
	public ResponseEntity<List<WikiDTO>> list() {
		List<Wiki> list = wikiService.list();
		List<WikiDTO> wikiDTO = list
				.stream()
				.map(wiki -> new WikiDTO(wiki))
				.collect(Collectors.toList());
		return ResponseEntity.ok(wikiDTO);
	}
}
