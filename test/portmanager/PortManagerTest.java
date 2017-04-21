/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portmanager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dhana
 */
public class PortManagerTest {
    
    public PortManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of start method, of class PortManager.
     */
    @Test
    public void testStart() {
        System.out.println("start");
        Stage primaryStage = null;
        PortManager instance = new PortManager();
        instance.start(primaryStage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class PortManager.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        PortManager.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of PortScan method, of class PortManager.
     */
    @Test
    public void testPortScan() {
        System.out.println("PortScan");
        PortManager.PortScan();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of portIsOpen method, of class PortManager.
     */
    @Test
    public void testPortIsOpen() {
        System.out.println("portIsOpen");
        ExecutorService es = null;
        String ip = "";
        int port = 0;
        int timeout = 0;
        Future<PortManager.ScanResult> expResult = null;
        Future<PortManager.ScanResult> result = PortManager.portIsOpen(es, ip, port, timeout);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
