package org.xelav.officeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.xelav.officeapp.dao.BaseDao;
import org.xelav.officeapp.model.BaseModel;

public class GenericRestController<T extends BaseModel> {

    @Autowired
    private BaseDao<T> dao;

    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.ASC, "id");
    }

    @GetMapping
    public Page<T> list(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "20") int limit,
            @RequestParam(value = "sortField", required = false) String sortField,
            @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection
            ) {
        return new PageImpl<>(dao.findAll(sortByIdAsc()));
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
