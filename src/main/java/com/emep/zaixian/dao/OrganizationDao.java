package com.emep.zaixian.dao;

import java.util.List;

import com.emep.zaixian.model.Organization;

/**
 * <p>User: anyj
 * <p>Date: 17-1-19
 * <p>Version: 1.0
 */
public interface OrganizationDao {

    public Organization createOrganization(Organization organization);
    public Organization updateOrganization(Organization organization);
    public void deleteOrganization(Long organizationId);

    Organization findOne(Long organizationId);
    List<Organization> findAll();

    List<Organization> findAllWithExclude(Organization excludeOraganization);

    void move(Organization source, Organization target);
}
