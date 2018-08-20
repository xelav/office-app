package org.xelav.officeapp.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.xelav.officeapp.model.Subdivision;
import org.xelav.officeapp.model.Worker;

public interface WorkerDao extends BaseDao<Worker> {

    Page<Worker> findBySubdivision(Subdivision subdivision, Pageable pageable);

}
