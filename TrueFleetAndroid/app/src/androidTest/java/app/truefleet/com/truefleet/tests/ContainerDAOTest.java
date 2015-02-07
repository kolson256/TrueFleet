package app.truefleet.com.truefleet.tests;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import java.util.List;

import app.truefleet.com.truefleet.DAO.ContainerDAO;
import app.truefleet.com.truefleet.Models.Container;

/**
 * Created by Chris Lacy on 2/4/2015.
 */

public class ContainerDAOTest extends AndroidTestCase {
    public static final String LOG_TAG = ContainerDAOTest.class.getSimpleName();
    ContainerDAO containerDAO;
    Container c;
    private static final String TEST_FILE_PREFIX = "test_";

    @Override
    protected void setUp() throws Exception {

        RenamingDelegatingContext context
                = new RenamingDelegatingContext(getContext(), TEST_FILE_PREFIX);

        containerDAO = new ContainerDAO(context);
        containerDAO.open();

        c = new Container();
        c.setDescription("description");
        c.setVolume(5);
        c.setLength(4);
        c.setWidth(3);
        c.setHeight(2);
        c.setWeight(1);
        c.setNotes("notes");

        super.setUp();
    }

    public void testListAllOnEmptyTable() {
        List<Container> containers = containerDAO.getAllContainers();
        assertEquals(0, containers.size());
    }

    public void testGetOnEmptyTable() {
        assertNull(containerDAO.getContainer(1));

    }

    public void testInsert() {

        assertNotNull(containerDAO.insertContainer(c));
    }
    public void testValuesBack() {

        containerDAO.insertContainer(c);

        Container c2 = containerDAO.insertContainer(c);

        assertEquals(c.getDescription(), c2.getDescription());
        assertEquals(c.getHeight(), c2.getHeight());
        assertEquals(c.getLength(), c2.getLength());
        assertEquals(c.getNotes(), c2.getNotes());
        assertEquals(c.getVolume(), c2.getVolume());
        assertEquals(c.getWeight(), c2.getWeight());
        assertEquals(c.getWidth(), c2.getWidth());

        assertNotNull(c2.getId());

    }

    public void testList() {

        containerDAO.insertContainer(c);
        List<Container> containers = containerDAO.getAllContainers();
        assertEquals(containers.size(), 1);

        containerDAO.insertContainer(c);
        containers = containerDAO.getAllContainers();
        assertEquals(containers.size(), 2);

        Container toDelete = containers.get(0);
        containerDAO.deleteContainer(toDelete);
        containers = containerDAO.getAllContainers();
        assertEquals(1, containers.size());

        //invalid delete
        containerDAO.deleteContainer(toDelete);

        containers = containerDAO.getAllContainers();
        assertEquals(1, containers.size());

        containerDAO.deleteContainer(containers.get(0));
        containers = containerDAO.getAllContainers();
        assertEquals(0, containers.size());
    }

    @Override
    protected void tearDown() throws Exception {

        containerDAO.close();
        containerDAO = null;
        super.tearDown();
    }

}
