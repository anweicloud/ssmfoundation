package com.anwei.common.util;

import java.util.Set;
import java.util.TreeSet;

public class CollectionUtil {
	public static Set<?> array2Set(Object[] array) {
		Set<Object> set = new TreeSet<Object>();
		for (Object id : array) {
			if(null != id){
				set.add(id);
			}
		}
		return set;
	}
}
