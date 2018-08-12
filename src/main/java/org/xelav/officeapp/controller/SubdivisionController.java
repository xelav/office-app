package org.xelav.officeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.xelav.officeapp.dao.SubdivisionDao;
import org.xelav.officeapp.dao.WorkerDao;
import org.xelav.officeapp.model.Subdivision;
import org.xelav.officeapp.model.Worker;

import java.util.List;

@RestController
@RequestMapping("/api/subdivisions")
public class SubdivisionController extends  GenericRestController<Subdivision> {

    @Autowired
    private SubdivisionDao subdivisionDao;
    @Autowired
    private WorkerDao workerDao;

    @GetMapping("{id}/workers")
    public Page<Worker> workersList(
            @PathVariable("id") long id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
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

        Subdivision subdivision = subdivisionDao.getOne(id);
        return workerDao.findBySubdivision(subdivision ,pageable);
    }

}
