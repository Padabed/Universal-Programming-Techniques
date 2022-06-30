package eu.glowacki.utp.assignment10.repositories.test;

import eu.glowacki.utp.assignment10.repositories.GroupRepository;
import org.junit.Assert;
import org.junit.Test;

import eu.glowacki.utp.assignment10.UnimplementedException;
import eu.glowacki.utp.assignment10.dtos.GroupDTO;
import eu.glowacki.utp.assignment10.repositories.IGroupRepository;

import java.util.List;

public class GroupRepositoryTest extends RepositoryTestBase<GroupDTO, IGroupRepository> {

	private static final String NAME = "Group";
	private static final String DESCRIPTION = "Description";
	private static final GroupDTO GROUP = new GroupDTO(1, NAME, DESCRIPTION);
	private static final String NAME2 = "Group2";
	private static final String DESCRIPTION2 = "Description2";
	private static final GroupDTO GROUP_UPDATED = new GroupDTO(1, NAME2, DESCRIPTION2);
	private static final GroupDTO GROUP2 = new GroupDTO(2, NAME2, DESCRIPTION2);

	@Test
	public void add() {
		Assert.assertFalse(_repository.exists(GROUP));
		int countBefore = _repository.getCount();
		_repository.add(GROUP);
		int countAfter = _repository.getCount();
		Assert.assertEquals(countBefore + 1, countAfter);
		Assert.assertTrue(_repository.exists(GROUP));
	}

	@Test
	public void update() {
		Assert.assertFalse(_repository.exists(GROUP));
		Assert.assertFalse(_repository.exists(GROUP_UPDATED));
		int countBefore = _repository.getCount();
		_repository.add(GROUP);
		int countAfter = _repository.getCount();
		Assert.assertEquals(countBefore + 1, countAfter);
		Assert.assertTrue(_repository.exists(GROUP));
		_repository.update(GROUP_UPDATED);
		countAfter = _repository.getCount();
		Assert.assertEquals(countBefore + 1, countAfter);
		Assert.assertTrue(_repository.exists(GROUP_UPDATED));
		Assert.assertFalse(_repository.exists(GROUP));
	}

	@Test
	public void addOrUpdate() {
		Assert.assertFalse(_repository.exists(GROUP));
		Assert.assertFalse(_repository.exists(GROUP_UPDATED));
		int countBefore = _repository.getCount();
		_repository.addOrUpdate(GROUP);
		int countAfter = _repository.getCount();
		Assert.assertEquals(countBefore + 1, countAfter);
		Assert.assertTrue(_repository.exists(GROUP));
		_repository.addOrUpdate(GROUP_UPDATED);
		countAfter = _repository.getCount();
		Assert.assertEquals(countBefore + 1, countAfter);
		Assert.assertTrue(_repository.exists(GROUP_UPDATED));
		Assert.assertFalse(_repository.exists(GROUP));
	}

	@Test
	public void delete() {
		Assert.assertFalse(_repository.exists(GROUP));
		int countBefore = _repository.getCount();
		_repository.add(GROUP);
		int countAfter = _repository.getCount();
		Assert.assertEquals(countBefore + 1, countAfter);
		Assert.assertTrue(_repository.exists(GROUP));
		_repository.delete(GROUP);
		countAfter = _repository.getCount();
		Assert.assertEquals(countAfter, countBefore);
		Assert.assertFalse(_repository.exists(GROUP));
	}

	@Test
	public void findById() {
		Assert.assertFalse(_repository.exists(GROUP));
		int countBefore = _repository.getCount();
		_repository.add(GROUP);
		int countAfter = _repository.getCount();
		Assert.assertEquals(countBefore + 1, countAfter);
		Assert.assertTrue(_repository.exists(GROUP));
		GroupDTO group = _repository.findById(GROUP.getId());
		Assert.assertEquals(group, GROUP);
	}

	@Test
	public void findByName() {
		Assert.assertFalse(_repository.exists(GROUP));
		Assert.assertFalse(_repository.exists(GROUP2));
		int countBefore = _repository.getCount();
		_repository.add(GROUP);
		_repository.add(GROUP2);
		int countAfter = _repository.getCount();
		Assert.assertEquals(countBefore + 2, countAfter);
		Assert.assertTrue(_repository.exists(GROUP));
		Assert.assertTrue(_repository.exists(GROUP2));
		List<GroupDTO> groups = _repository.findByName("Gr");
		Assert.assertEquals(2, groups.size());
		groups = _repository.findByName("Group2");
		Assert.assertEquals(1, groups.size());
		groups = _repository.findByName("QWERTY");
		Assert.assertEquals(0, groups.size());
	}

	@Override
	protected IGroupRepository Create() {
		//throw new UnimplementedException();
		return new GroupRepository();
	}
}