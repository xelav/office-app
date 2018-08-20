package org.xelav.officeapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xelav.officeapp.model.Office;

@RestController
@RequestMapping("/api/offices")
public class OfficeController extends  GenericRestController<Office>{

}
