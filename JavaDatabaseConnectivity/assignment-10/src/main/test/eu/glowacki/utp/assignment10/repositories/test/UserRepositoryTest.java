package eu.glowacki.utp.assignment10.repositories.test;

import eu.glowacki.utp.assignment10.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;

import eu.glowacki.utp.assignment10.UnimplementedException;
import eu.glowacki.utp.assignment10.dtos.UserDTO;
import eu.glowacki.utp.assignment10.repositories.IUserRepository;

import java.util.List;

public final class UserRepositoryTest extends RepositoryTestBase<UserDTO, IUserRepository> {

	private static final String NAME = "User";
	private static final String PASSWORD = "Password";
	private static final UserDTO USER = new UserDTO(1, NAME, PASSWORD);
	private static final String NAME2 = "User2";
	private static final String PASSWORD2 = "Password2";
	private static final UserDTO USER_UPDATED = new UserDTO(1, NAME2, PASSWORD2);
	private static final UserDTO USER2 = new UserDTO(2, NAME2, PASSWORD2);

	@Test
	public void add() {
		Assert.assertFalse(_repository.exists(USER));
		int countBefore = _repository.getCount();
		_repository.add(USER);
		int countAfter = _repository.getCount();
		Assert.assertEquals(countBefore + 1, countAfter);
		Assert.assertTrue(_repository.exists(USER));
	}

	@Test
	public void update() {
		Assert.assertFalse(_repository.exists(USER));
		Assert.assertFalse(_repository.exists(USER_UPDATED));
		int countBefore = _repository.getCount();
		_repository.add(USER);
		int countAfter = _repository.getCount();
		Assert.assertEquals(countBefore + 1, countAfter);
		Assert.assertTrue(_repository.exists(USER));
		_repository.update(USER_UPDATED);
		countAfter = _repository.getCount();
		Assert.assertEquals(countBefore + 1, countAfter);
		Assert.assertTrue(_repository.exists(USER_UPDATED));
		Assert.assertFalse(_repository.exists(USER));
	}

	@Test
	public void addOrUpdate() {
		Assert.assertFalse(_repository.exists(USER));
		Assert.assertFalse(_repository.exists(USER_UPDATED));
		int countBefore = _repository.getCount();
		_repository.addOrUpdate(USER);
		int countAfter = _repository.getCount();
		Assert.assertEquals(countBefore + 1, countAfter);
		Assert.assertTrue(_repository.exists(USER));
		_repository.addOrUpdate(USER_UPDATED);
		countAfter = _repository.getCount();
		Assert.assertEquals(countBefore + 1, countAfter);
		Assert.assertTrue(_repository.exists(USER_UPDATED));
		Assert.assertFalse(_repository.exists(USER));
	}

	@Test
	public void delete() {
		Assert.assertFalse(_repository.exists(USER));
		int countBefore = _repository.getCount();
		_repository.add(USER);
		int countAfter = _repository.getCount();
		Assert.assertEquals(countBefore + 1, countAfter);
		Assert.assertTrue(_repository.exists(USER));
		_repository.delete(USER);
		countAfter = _repository.getCount();
		Assert.assertEquals(countAfter, countBefore);
		Assert.assertFalse(_repository.exists(USER));
	}

	@Test
	public void findById() {
		Assert.assertFalse(_repository.exists(USER));
		int countBefore = _repository.getCount();
		_repository.add(USER);
		int countAfter = _repository.getCount();
		Assert.assertEquals(countBefore + 1, countAfter);
		Assert.assertTrue(_repository.exists(USER));
		UserDTO user = _repository.findById(USER.getId());
		Assert.assertEquals(user, USER);
	}

	@Test
	public void findByName() {
		Assert.assertFalse(_repository.exists(USER));
		Assert.assertFalse(_repository.exists(USER2));
		int countBefore = _repository.getCount();
		_repository.add(USER);
		_repository.add(USER2);
		int countAfter = _repository.getCount();
		Assert.assertEquals(countBefore + 2, countAfter);
		Assert.assertTrue(_repository.exists(USER));
		Assert.assertTrue(_repository.exists(USER2));
		List<UserDTO> users = _repository.findByName("se");
		Assert.assertEquals(2, users.size());
		users = _repository.findByName("r2");
		Assert.assertEquals(1, users.size());
		users = _repository.findByName("Admin");
		Assert.assertEquals(0, users.size());
	}

	@Override
	protected IUserRepository Create() {
		//throw new UnimplementedException();
		return new UserRepository();
	}
}