package org.xelav.officeapp.controller;

import org.springframework.web.bind.annotation.*;
import org.xelav.officeapp.model.Office;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/offices")
public class OfficeController extends  GenericRestController<Office>{

}
