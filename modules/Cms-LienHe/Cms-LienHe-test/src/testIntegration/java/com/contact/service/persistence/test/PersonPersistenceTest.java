/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.contact.service.persistence.test;

import com.contact.exception.NoSuchPersonException;
import com.contact.model.Person;
import com.contact.service.PersonLocalServiceUtil;
import com.contact.service.persistence.PersonPersistence;
import com.contact.service.persistence.PersonUtil;

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
public class PersonPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.contact.service"));

	@Before
	public void setUp() {
		_persistence = PersonUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Person> iterator = _persons.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Person person = _persistence.create(pk);

		Assert.assertNotNull(person);

		Assert.assertEquals(person.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Person newPerson = addPerson();

		_persistence.remove(newPerson);

		Person existingPerson = _persistence.fetchByPrimaryKey(
			newPerson.getPrimaryKey());

		Assert.assertNull(existingPerson);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addPerson();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Person newPerson = _persistence.create(pk);

		newPerson.setUuid(RandomTestUtil.randomString());

		newPerson.setGroupId(RandomTestUtil.nextLong());

		newPerson.setCompanyId(RandomTestUtil.nextLong());

		newPerson.setUserId(RandomTestUtil.nextLong());

		newPerson.setUserName(RandomTestUtil.randomString());

		newPerson.setPsName(RandomTestUtil.randomString());

		newPerson.setPsPosition(RandomTestUtil.randomString());

		newPerson.setDptId(RandomTestUtil.nextLong());

		newPerson.setPsPhone(RandomTestUtil.randomString());

		newPerson.setPsEmail(RandomTestUtil.randomString());

		newPerson.setPsStatus(RandomTestUtil.nextLong());

		newPerson.setPsDelete(RandomTestUtil.nextLong());

		newPerson.setPscreate(RandomTestUtil.nextDate());

		newPerson.setPsmodifiedDate(RandomTestUtil.nextDate());

		_persons.add(_persistence.update(newPerson));

		Person existingPerson = _persistence.findByPrimaryKey(
			newPerson.getPrimaryKey());

		Assert.assertEquals(existingPerson.getUuid(), newPerson.getUuid());
		Assert.assertEquals(
			existingPerson.getGroupId(), newPerson.getGroupId());
		Assert.assertEquals(
			existingPerson.getCompanyId(), newPerson.getCompanyId());
		Assert.assertEquals(existingPerson.getUserId(), newPerson.getUserId());
		Assert.assertEquals(
			existingPerson.getUserName(), newPerson.getUserName());
		Assert.assertEquals(existingPerson.getPsId(), newPerson.getPsId());
		Assert.assertEquals(existingPerson.getPsName(), newPerson.getPsName());
		Assert.assertEquals(
			existingPerson.getPsPosition(), newPerson.getPsPosition());
		Assert.assertEquals(existingPerson.getDptId(), newPerson.getDptId());
		Assert.assertEquals(
			existingPerson.getPsPhone(), newPerson.getPsPhone());
		Assert.assertEquals(
			existingPerson.getPsEmail(), newPerson.getPsEmail());
		Assert.assertEquals(
			existingPerson.getPsStatus(), newPerson.getPsStatus());
		Assert.assertEquals(
			existingPerson.getPsDelete(), newPerson.getPsDelete());
		Assert.assertEquals(
			Time.getShortTimestamp(existingPerson.getPscreate()),
			Time.getShortTimestamp(newPerson.getPscreate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingPerson.getPsmodifiedDate()),
			Time.getShortTimestamp(newPerson.getPsmodifiedDate()));
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
	public void testCountBypsName() throws Exception {
		_persistence.countBypsName("");

		_persistence.countBypsName("null");

		_persistence.countBypsName((String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Person newPerson = addPerson();

		Person existingPerson = _persistence.findByPrimaryKey(
			newPerson.getPrimaryKey());

		Assert.assertEquals(existingPerson, newPerson);
	}

	@Test(expected = NoSuchPersonException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<Person> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"Contact_Person", "uuid", true, "groupId", true, "companyId", true,
			"userId", true, "userName", true, "psId", true, "psName", true,
			"psPosition", true, "dptId", true, "psPhone", true, "psEmail", true,
			"psStatus", true, "psDelete", true, "pscreate", true,
			"psmodifiedDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Person newPerson = addPerson();

		Person existingPerson = _persistence.fetchByPrimaryKey(
			newPerson.getPrimaryKey());

		Assert.assertEquals(existingPerson, newPerson);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Person missingPerson = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingPerson);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		Person newPerson1 = addPerson();
		Person newPerson2 = addPerson();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPerson1.getPrimaryKey());
		primaryKeys.add(newPerson2.getPrimaryKey());

		Map<Serializable, Person> persons = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(2, persons.size());
		Assert.assertEquals(
			newPerson1, persons.get(newPerson1.getPrimaryKey()));
		Assert.assertEquals(
			newPerson2, persons.get(newPerson2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Person> persons = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(persons.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		Person newPerson = addPerson();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPerson.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Person> persons = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, persons.size());
		Assert.assertEquals(newPerson, persons.get(newPerson.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Person> persons = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(persons.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		Person newPerson = addPerson();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPerson.getPrimaryKey());

		Map<Serializable, Person> persons = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, persons.size());
		Assert.assertEquals(newPerson, persons.get(newPerson.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			PersonLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Person>() {

				@Override
				public void performAction(Person person) {
					Assert.assertNotNull(person);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		Person newPerson = addPerson();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Person.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("psId", newPerson.getPsId()));

		List<Person> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Person existingPerson = result.get(0);

		Assert.assertEquals(existingPerson, newPerson);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Person.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("psId", RandomTestUtil.nextLong()));

		List<Person> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		Person newPerson = addPerson();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Person.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("psId"));

		Object newPsId = newPerson.getPsId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in("psId", new Object[] {newPsId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPsId = result.get(0);

		Assert.assertEquals(existingPsId, newPsId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Person.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("psId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"psId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		Person newPerson = addPerson();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(newPerson.getPrimaryKey()));
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

		Person newPerson = addPerson();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Person.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("psId", newPerson.getPsId()));

		List<Person> result = _persistence.findWithDynamicQuery(dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(Person person) {
		Assert.assertEquals(
			person.getUuid(),
			ReflectionTestUtil.invoke(
				person, "getColumnOriginalValue", new Class<?>[] {String.class},
				"uuid_"));
		Assert.assertEquals(
			Long.valueOf(person.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				person, "getColumnOriginalValue", new Class<?>[] {String.class},
				"groupId"));
	}

	protected Person addPerson() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Person person = _persistence.create(pk);

		person.setUuid(RandomTestUtil.randomString());

		person.setGroupId(RandomTestUtil.nextLong());

		person.setCompanyId(RandomTestUtil.nextLong());

		person.setUserId(RandomTestUtil.nextLong());

		person.setUserName(RandomTestUtil.randomString());

		person.setPsName(RandomTestUtil.randomString());

		person.setPsPosition(RandomTestUtil.randomString());

		person.setDptId(RandomTestUtil.nextLong());

		person.setPsPhone(RandomTestUtil.randomString());

		person.setPsEmail(RandomTestUtil.randomString());

		person.setPsStatus(RandomTestUtil.nextLong());

		person.setPsDelete(RandomTestUtil.nextLong());

		person.setPscreate(RandomTestUtil.nextDate());

		person.setPsmodifiedDate(RandomTestUtil.nextDate());

		_persons.add(_persistence.update(person));

		return person;
	}

	private List<Person> _persons = new ArrayList<Person>();
	private PersonPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}