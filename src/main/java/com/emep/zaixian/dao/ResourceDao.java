package com.emep.zaixian.dao;

import java.util.List;

import com.emep.zaixian.model.Resource;


/**
 * <p>User: anyj
 * <p>Date: 17-1-19
 * <p>Version: 1.0
 */
public interface ResourceDao {

    public Resource createResource(Resource resource);
    public Resource updateResource(Resource resource);
    public void deleteResource(Long resourceId);

    Resource findOne(Long resourceId);
    List<Resource> findAll();

}
