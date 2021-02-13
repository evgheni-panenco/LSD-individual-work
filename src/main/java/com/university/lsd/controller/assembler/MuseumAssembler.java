package com.university.lsd.controller.assembler;

import com.university.lsd.controller.MuseumController;
import com.university.lsd.model.Museum;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MuseumAssembler implements RepresentationModelAssembler<Museum, EntityModel<Museum>> {

    @Override
    public EntityModel<Museum> toModel(final Museum museum) {
        return EntityModel.of(museum,
                linkTo(methodOn(MuseumController.class).getById(museum.getId())).withSelfRel(),
                linkTo(methodOn(MuseumController.class).getAll()).withRel("museums"));
    }
}
