package org.xelav.officeapp.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.xelav.officeapp.model.BaseModel;

import java.io.Serializable;

@Repository
public interface BaseDao<T extends BaseModel> extends JpaRepository<T, Serializable> {

}
