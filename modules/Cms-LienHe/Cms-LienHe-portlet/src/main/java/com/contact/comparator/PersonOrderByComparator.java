package com.contact.comparator;

import com.contact.model.Person;
import com.liferay.portal.kernel.util.OrderByComparator;

public class PersonOrderByComparator extends OrderByComparator<Person> {
	
	public static String ORDER_BY_ASC = "Person.psName ASC";
	public static String ORDER_BY_DESC = "Person.psName DESC";
	
	private boolean _ascending;
	
	public PersonOrderByComparator(boolean ascending) {
        _ascending = ascending;
    }

	@Override
	public int compare(Person o1, Person o2) {
		String name1 = o1.getPsName();
		String name2 = o2.getPsName();
		int value = name1.compareToIgnoreCase(name2);

        if (_ascending) {
            return value;
        } else {
            return -value;
        }
	}
	
	@Override
    public String getOrderBy() {
        return _ascending ? ORDER_BY_ASC : ORDER_BY_DESC;
    }
}
