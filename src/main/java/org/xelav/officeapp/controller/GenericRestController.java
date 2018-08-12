package org.xelav.officeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import org.xelav.officeapp.dao.BaseDao;
import org.xelav.officeapp.model.BaseModel;

import java.util.List;

public class GenericRestController<T extends BaseModel> {

    @Autowired
    private BaseDao<T> dao;

    @GetMapping
    public Page<T> list(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "20") int limit,
            @RequestParam(value = "sortField", required = false) String sortField,
            @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection
            ) {
        return new PageImpl<>(dao.findAll());
    }

    @PostMapping
    public T create(@RequestBody T entity) { return dao.save(entity); }

    @PutMapping("{id}")
    public T update(@PathVariable("id") long id, @RequestBody T entity) { return dao.save(entity); }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") long id) {
        dao.deleteById(id);
    }

    @GetMapping("{id}")
    public T get(@PathVariable("id") long id) {
        return dao.getOne(id);
    }

}
