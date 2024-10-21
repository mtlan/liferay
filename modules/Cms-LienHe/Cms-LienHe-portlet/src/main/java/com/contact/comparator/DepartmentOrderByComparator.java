package com.contact.comparator;

import com.contact.model.Department;
import com.liferay.portal.kernel.util.OrderByComparator;

public class DepartmentOrderByComparator extends OrderByComparator<Department> {
	
	public static String ORDER_BY_ASC = "Department.dptName ASC";
	public static String ORDER_BY_DESC = "Department.dptName DESC";
	
	private boolean _ascending;
	
	public DepartmentOrderByComparator(boolean ascending) {
        _ascending = ascending;
    }

	@Override
	public int compare(Department o1, Department o2) {
		String name1 = o1.getDptName();
		String name2 = o2.getDptName();
		int value = name1.compareToIgnoreCase(name2);
		
//		if(value == 0) {
//			// If names are equal, compare by position
//            String desc1 = o1.getDptDesc();
//            String desc2 = o2.getDptDesc();
//            value = desc1.compareToIgnoreCase(desc2);
//		}
		
//        if (_ascending) {
//            return value;
//        } else {
//            return -value;
//        }
		return _ascending ? value: -value;
	}
	
	@Override
    public String getOrderBy() {
        return _ascending ? ORDER_BY_ASC : ORDER_BY_DESC;
    }
}
