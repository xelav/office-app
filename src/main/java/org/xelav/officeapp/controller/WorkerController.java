package org.xelav.officeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xelav.officeapp.dao.BaseDao;
import org.xelav.officeapp.dao.WorkerDao;
import org.xelav.officeapp.model.Worker;


@RestController
@RequestMapping("/api/workers")
public class WorkerController extends GenericRestController<Worker> {

    @Autowired
    private WorkerDao dao;

    @GetMapping
    public Page<Worker> list(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "20") int limit,
            @RequestParam(value = "sortField", required = false) String sortField,
            @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection
    ) {
        Pageable pageable;
        if (sortField != null) {
            Sort sort;
            switch (sortDirection.toLowerCase()) {
                case "asc":
                    sort = new Sort(Sort.Direction.ASC, sortField);
                    break;
                case "desc":
                    sort = new Sort(Sort.Direction.DESC, sortField);
                    break;
                default:
                    throw new Error ("Wrong parameter!"); //FIXME: throws code 500 instead of 400
            }
            pageable = PageRequest.of(page, limit, sort);
        } else {
            pageable = PageRequest.of(page, limit);
        }
        return dao.findAll(pageable);
    }

}
