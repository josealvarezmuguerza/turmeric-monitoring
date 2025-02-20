/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.monitoring.provider.dao;

import java.util.List;
import java.util.Set;


/**
 * The Interface MetricsErrorDAO.
 * @author jose alvarez muguerza
 */
public interface MetricsErrorValuesDAO<K>  {
	Set<org.ebayopensource.turmeric.runtime.error.cassandra.model.ErrorValue> findItems(List<K> keys, String rangeFrom, String rangeTo );
	org.ebayopensource.turmeric.runtime.error.cassandra.model.ErrorValue find(K key );
	
}
