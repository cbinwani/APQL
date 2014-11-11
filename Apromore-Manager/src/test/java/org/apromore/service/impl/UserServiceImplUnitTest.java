package org.apromore.service.impl;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.powermock.api.easymock.PowerMock.createMock;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apromore.dao.SearchHistoryRepository;
import org.apromore.dao.UserRepository;
import org.apromore.dao.model.SearchHistory;
import org.apromore.dao.model.User;
import org.apromore.exception.UserNotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit test the UserService Implementation.
 *
 * @author <a href="mailto:cam.james@gmail.com">Cameron James</a>
 */
public class UserServiceImplUnitTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private UserServiceImpl usrServiceImpl;
    private UserRepository usrRepo;
    private SearchHistoryRepository searchHistoryRepo;

    @Before
    public final void setUp() throws Exception {
        usrRepo = createMock(UserRepository.class);
        searchHistoryRepo = createMock(SearchHistoryRepository.class);
        usrServiceImpl = new UserServiceImpl(usrRepo, searchHistoryRepo);
    }

    @Test
    public void getAllUsers() {
        List<User> users = new ArrayList<>();

        expect(usrRepo.findAll()).andReturn(users);
        replay(usrRepo);

        List<User> serviceUsers = usrServiceImpl.findAllUsers();
        verify(usrRepo);
        assertThat(serviceUsers, equalTo(users));
    }

    @Test
    public void getUser() throws Exception {
        String username = "jaybob";
        User usr = new User();

        expect(usrRepo.findByUsername(username)).andReturn(usr);
        replay(usrRepo);

        User serviceUsr = usrServiceImpl.findUserByLogin(username);
        verify(usrRepo);
        assertThat(serviceUsr, equalTo(usr));
    }

    @Test
    public void getUserNotFound() throws Exception {
        String username = "jaybob";

        expect(usrRepo.findByUsername(username)).andReturn(null);
        replay(usrRepo);

        exception.expect(UserNotFoundException.class);
        usrServiceImpl.findUserByLogin(username);

        verify(usrRepo);
    }


    @Test
    public void writeUser() {
        Integer id = 1;
        String username = "username";
        User usr = createUser();

        expect(usrRepo.findOne(id)).andReturn(usr);
        expect(usrRepo.save((User) anyObject())).andReturn(usr);
        replay(usrRepo);

        usrServiceImpl.writeUser(usr);
        verify(usrRepo);

        assertThat(username, equalTo(usr.getUsername()));
    }

    @Test
    public void testUpdateUserSearchHistory() {
        String username = "username";
        User usr = createUser();
        List<SearchHistory> histories = new ArrayList<>();
        histories.add(new SearchHistory());

        expect(usrRepo.findByUsername(username)).andReturn(usr);
        expect(searchHistoryRepo.save((Set<SearchHistory>) anyObject())).andReturn(histories);
        expect(usrRepo.save((User) anyObject())).andReturn(usr);
        replay(usrRepo);

        usrServiceImpl.updateUserSearchHistory(usr, histories);
        verify(usrRepo);

        assertThat(username, equalTo(usr.getUsername()));
    }

    private User createUser() {
        User user = new User();

        user.setId(1);
        user.setUsername("username");
        user.setFirstName("firstname");
        user.setLastName("lastname");
        user.setDateCreated(new Date());
        user.setLastActivityDate(new Date());

        return user;
    }
}
