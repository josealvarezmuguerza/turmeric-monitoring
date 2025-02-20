/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.monitoring.provider.dao.impl;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.ebayopensource.turmeric.monitoring.provider.dao.MetricsServiceConsumerByIpDAO;
import org.ebayopensource.turmeric.monitoring.provider.model.BasicModel;
import org.ebayopensource.turmeric.monitoring.provider.model.SuperModel;
import org.ebayopensource.turmeric.utils.cassandra.dao.AbstractSuperColumnFamilyDao;

import edu.emory.mathcs.backport.java.util.Collections;



/**
 * The Class MetricsServiceOperationByIpDAOImpl.
 * 
 * @author jamuguerza
 */
public class MetricsServiceConsumerByIpDAOImpl<SK, K> extends
		AbstractSuperColumnFamilyDao<SK, SuperModel, String, BasicModel>
		implements MetricsServiceConsumerByIpDAO<SK, K> {

	/**
	 * Instantiates a new metrics error values dao impl.
	 * 
	 * @param clusterName
	 *            the cluster name
	 * @param host
	 *            the host
	 * @param s_keyspace
	 *            the s_keyspace
	 * @param columnFamilyName
	 *            the column family name
	 */
	public MetricsServiceConsumerByIpDAOImpl(String clusterName, String host,
			String s_keyspace, String columnFamilyName, final Class<SK> sKTypeClass, final Class<K> kTypeClass) {
		super(clusterName, host, s_keyspace, sKTypeClass, SuperModel.class,
				String.class, BasicModel.class, columnFamilyName);
	}

	@Override
	public List<String> findMetricConsumerNames(final List<String> serviceAdminNames) {
			Set<String> resultSet = new TreeSet<String>();

			List<SK> keys = new ArrayList<SK>();
			keys.add((SK)"All");
			String[] serviceNames = new String[serviceAdminNames.size()];
			serviceNames = serviceAdminNames.toArray(serviceNames);
			
			Map<SK, SuperModel> findItems = findItems(keys, serviceNames);

			for (Map.Entry<SK, SuperModel> findItem : findItems.entrySet()) {
				SK key = findItem.getKey(); //IP, in this case ALL
				
				SuperModel superModel = findItem.getValue();
				if(superModel != null) {
					Map<String, BasicModel> columns = superModel.getColumns();
									
					for (String consumerName : columns.keySet()) {
							resultSet.add(consumerName);
					}
				}
			}

		    List<String> resultList = new ArrayList<String>(resultSet);
		    Collections.sort(resultList);
		    
		    return resultList;
		}	

}
