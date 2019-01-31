package org.projetpoo;

import org.junit.Before;
import org.junit.Test;
import org.projetpoo.client.gui.MainWindow;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Unit test for simple the ProjetPOO app.
 */
public class AppTest {

    private static TCPServerTest tcpServerTest;

    @Before
    public void setUp() throws Exception {
        // Server setup
        if (tcpServerTest == null){
            tcpServerTest = new TCPServerTest(2000);
            (new Thread(tcpServerTest)).start();
        }

    }

    @Test
    public void userLogin() {

        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(false); // Hide the window for the tests
        MainWindow.localUser.login("testLogin");
        boolean result = false;
        try{
            result = tcpServerTest.connection.getUserStatusByNickName("testLogin");
        } catch (Exception e){
            fail();
        }
        assertTrue(result);
    }


    @Test
    public void userNameSaved() {
        boolean exists = true;
        try{
            tcpServerTest.connection.getUserStatusByNickName("testLogin");
        } catch (Exception e){
            e.printStackTrace();
            exists = false;
        }
        assertTrue(exists);
    }

}
