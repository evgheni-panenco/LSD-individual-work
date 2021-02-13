package com.university.lsd.controller;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.List;
import java.util.UUID;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.university.lsd.controller.assembler.MuseumAssembler;
import com.university.lsd.model.Museum;
import com.university.lsd.repository.MuseumRepository;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping(MuseumController.CONTROLLER_PATH)
public class MuseumController {

	public static final String CONTROLLER_PATH = "/museums";
	public static final String CTRL_PATH_WITH_ID = "/{id}";

	private final MuseumRepository museumRepository;
	private final MuseumAssembler museumAssembler;

	@PostMapping
	public ResponseEntity<?> saveMuseum(@RequestBody final Museum museum) {
		val optionalMuseum = museumRepository.findByName(museum.getName());
		if (optionalMuseum.isEmpty()) {
			museumRepository.save(museum);
		}
		val museumModel = museumAssembler.toModel(museumRepository.save(optionalMuseum.orElse(museum)));
		return ResponseEntity.status(CREATED).contentType(APPLICATION_JSON).body(museumModel);
	}

	@GetMapping
	public CollectionModel<?> getAll() {
		List<EntityModel<Museum>> museums = museumRepository.findAll().stream().map(museumAssembler::toModel)
				.collect(toList());
		return CollectionModel.of(museums, linkTo(methodOn(MuseumController.class).getAll()).withSelfRel());
	}

	@GetMapping(CTRL_PATH_WITH_ID)
	public EntityModel<?> getById(@PathVariable final UUID id) {
		return EntityModel.of(museumRepository.findById(id).orElseThrow());
	}

	@DeleteMapping(CTRL_PATH_WITH_ID)
	public ResponseEntity<?> deleteById(@PathVariable final UUID id) {
		museumRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(CTRL_PATH_WITH_ID)
	public ResponseEntity<?> updateById(@PathVariable final UUID id, @RequestBody final Museum museum) {
		museumRepository.deleteById(id);
		return saveMuseum(museum);
	}
}
