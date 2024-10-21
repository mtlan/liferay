/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.contact.service.persistence.test;

import com.contact.exception.NoSuchDepartmentException;
import com.contact.model.Department;
import com.contact.service.DepartmentLocalServiceUtil;
import com.contact.service.persistence.DepartmentPersistence;
import com.contact.service.persistence.DepartmentUtil;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class DepartmentPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.contact.service"));

	@Before
	public void setUp() {
		_persistence = DepartmentUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Department> iterator = _departments.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Department department = _persistence.create(pk);

		Assert.assertNotNull(department);

		Assert.assertEquals(department.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Department newDepartment = addDepartment();

		_persistence.remove(newDepartment);

		Department existingDepartment = _persistence.fetchByPrimaryKey(
			newDepartment.getPrimaryKey());

		Assert.assertNull(existingDepartment);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDepartment();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Department newDepartment = _persistence.create(pk);

		newDepartment.setUuid(RandomTestUtil.randomString());

		newDepartment.setGroupId(RandomTestUtil.nextLong());

		newDepartment.setCompanyId(RandomTestUtil.nextLong());

		newDepartment.setUserId(RandomTestUtil.nextLong());

		newDepartment.setUserName(RandomTestUtil.randomString());

		newDepartment.setDptName(RandomTestUtil.randomString());

		newDepartment.setDptDesc(RandomTestUtil.randomString());

		newDepartment.setDptStatus(RandomTestUtil.nextLong());

		newDepartment.setDptDelete(RandomTestUtil.nextLong());

		newDepartment.setDptcreateDate(RandomTestUtil.nextDate());

		newDepartment.setDptmodifiedDate(RandomTestUtil.nextDate());

		_departments.add(_persistence.update(newDepartment));

		Department existingDepartment = _persistence.findByPrimaryKey(
			newDepartment.getPrimaryKey());

		Assert.assertEquals(
			existingDepartment.getUuid(), newDepartment.getUuid());
		Assert.assertEquals(
			existingDepartment.getGroupId(), newDepartment.getGroupId());
		Assert.assertEquals(
			existingDepartment.getCompanyId(), newDepartment.getCompanyId());
		Assert.assertEquals(
			existingDepartment.getUserId(), newDepartment.getUserId());
		Assert.assertEquals(
			existingDepartment.getUserName(), newDepartment.getUserName());
		Assert.assertEquals(
			existingDepartment.getDptId(), newDepartment.getDptId());
		Assert.assertEquals(
			existingDepartment.getDptName(), newDepartment.getDptName());
		Assert.assertEquals(
			existingDepartment.getDptDesc(), newDepartment.getDptDesc());
		Assert.assertEquals(
			existingDepartment.getDptStatus(), newDepartment.getDptStatus());
		Assert.assertEquals(
			existingDepartment.getDptDelete(), newDepartment.getDptDelete());
		Assert.assertEquals(
			Time.getShortTimestamp(existingDepartment.getDptcreateDate()),
			Time.getShortTimestamp(newDepartment.getDptcreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingDepartment.getDptmodifiedDate()),
			Time.getShortTimestamp(newDepartment.getDptmodifiedDate()));
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid("");

		_persistence.countByUuid("null");

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUUID_G() throws Exception {
		_persistence.countByUUID_G("", RandomTestUtil.nextLong());

		_persistence.countByUUID_G("null", 0L);

		_persistence.countByUUID_G((String)null, 0L);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C("", RandomTestUtil.nextLong());

		_persistence.countByUuid_C("null", 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testCountBydptName() throws Exception {
		_persistence.countBydptName("");

		_persistence.countBydptName("null");

		_persistence.countBydptName((String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Department newDepartment = addDepartment();

		Department existingDepartment = _persistence.findByPrimaryKey(
			newDepartment.getPrimaryKey());

		Assert.assertEquals(existingDepartment, newDepartment);
	}

	@Test(expected = NoSuchDepartmentException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<Department> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"Contact_Department", "uuid", true, "groupId", true, "companyId",
			true, "userId", true, "userName", true, "dptId", true, "dptName",
			true, "dptDesc", true, "dptStatus", true, "dptDelete", true,
			"dptcreateDate", true, "dptmodifiedDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Department newDepartment = addDepartment();

		Department existingDepartment = _persistence.fetchByPrimaryKey(
			newDepartment.getPrimaryKey());

		Assert.assertEquals(existingDepartment, newDepartment);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Department missingDepartment = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDepartment);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		Department newDepartment1 = addDepartment();
		Department newDepartment2 = addDepartment();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDepartment1.getPrimaryKey());
		primaryKeys.add(newDepartment2.getPrimaryKey());

		Map<Serializable, Department> departments =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, departments.size());
		Assert.assertEquals(
			newDepartment1, departments.get(newDepartment1.getPrimaryKey()));
		Assert.assertEquals(
			newDepartment2, departments.get(newDepartment2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Department> departments =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(departments.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		Department newDepartment = addDepartment();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDepartment.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Department> departments =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, departments.size());
		Assert.assertEquals(
			newDepartment, departments.get(newDepartment.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Department> departments =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(departments.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		Department newDepartment = addDepartment();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDepartment.getPrimaryKey());

		Map<Serializable, Department> departments =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, departments.size());
		Assert.assertEquals(
			newDepartment, departments.get(newDepartment.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			DepartmentLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Department>() {

				@Override
				public void performAction(Department department) {
					Assert.assertNotNull(department);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		Department newDepartment = addDepartment();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Department.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("dptId", newDepartment.getDptId()));

		List<Department> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		Department existingDepartment = result.get(0);

		Assert.assertEquals(existingDepartment, newDepartment);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Department.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("dptId", RandomTestUtil.nextLong()));

		List<Department> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		Department newDepartment = addDepartment();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Department.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("dptId"));

		Object newDptId = newDepartment.getDptId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in("dptId", new Object[] {newDptId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingDptId = result.get(0);

		Assert.assertEquals(existingDptId, newDptId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Department.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("dptId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"dptId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		Department newDepartment = addDepartment();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(newDepartment.getPrimaryKey()));
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromDatabase()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(true);
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromSession()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(false);
	}

	private void _testResetOriginalValuesWithDynamicQuery(boolean clearSession)
		throws Exception {

		Department newDepartment = addDepartment();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Department.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("dptId", newDepartment.getDptId()));

		List<Department> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(Department department) {
		Assert.assertEquals(
			department.getUuid(),
			ReflectionTestUtil.invoke(
				department, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "uuid_"));
		Assert.assertEquals(
			Long.valueOf(department.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				department, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "groupId"));
	}

	protected Department addDepartment() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Department department = _persistence.create(pk);

		department.setUuid(RandomTestUtil.randomString());

		department.setGroupId(RandomTestUtil.nextLong());

		department.setCompanyId(RandomTestUtil.nextLong());

		department.setUserId(RandomTestUtil.nextLong());

		department.setUserName(RandomTestUtil.randomString());

		department.setDptName(RandomTestUtil.randomString());

		department.setDptDesc(RandomTestUtil.randomString());

		department.setDptStatus(RandomTestUtil.nextLong());

		department.setDptDelete(RandomTestUtil.nextLong());

		department.setDptcreateDate(RandomTestUtil.nextDate());

		department.setDptmodifiedDate(RandomTestUtil.nextDate());

		_departments.add(_persistence.update(department));

		return department;
	}

	private List<Department> _departments = new ArrayList<Department>();
	private DepartmentPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}