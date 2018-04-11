/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wwtbam;

import java.lang.reflect.Method;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zhuan
 */
public class WWTBAMTest {
    
    public WWTBAMTest() {
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
 
    
    @Test
    public void testGetChoiceIndex()
    {
        try {
            Method method=WWTBAM.class.getDeclaredMethod("getChoiceIndex",String.class);
            method.setAccessible(true);
            assertEquals(method.invoke(null,"A"),0);
            assertEquals(method.invoke(null,"B"),1);
            assertEquals(method.invoke(null,"C"),2);
            assertEquals(method.invoke(null,"D"),3);
        } catch (Exception e) {
            fail("Exception happened:"+e);
        }
    }
    
    @Test
    public void testGetStringChoiceFromIndex() {
        try {
            Method method=WWTBAM.class.getDeclaredMethod("getStringChoiceFromIndex",int.class);
            method.setAccessible(true);
            assertEquals(method.invoke(null,0),"A");
            assertEquals(method.invoke(null,1),"B");
            assertEquals(method.invoke(null,2),"C");
            assertEquals(method.invoke(null,3),"D");
        } catch (Exception e) {
            fail("Exception happened:"+e);
        }
    }
    
    @Test
    public void testGetAnswerOtherThanCorrect() {
        try {
            Method method=WWTBAM.class.getDeclaredMethod("getAnswerOtherThanCorrect",int.class,String.class);
            method.setAccessible(true);
            assertEquals(method.invoke(null,0,"A"),1);
            assertEquals(method.invoke(null,1,"A"),2);
            assertEquals(method.invoke(null,2,"A"),3);
            assertEquals(method.invoke(null,0,"B"),0);
            assertEquals(method.invoke(null,1,"B"),2);
            assertEquals(method.invoke(null,2,"B"),3);
            assertEquals(method.invoke(null,0,"C"),0);
            assertEquals(method.invoke(null,1,"C"),1);
            assertEquals(method.invoke(null,2,"C"),3);
            assertEquals(method.invoke(null,0,"D"),0);
            assertEquals(method.invoke(null,1,"D"),1);
            assertEquals(method.invoke(null,2,"D"),2);
            
        } catch (Exception e) {
            fail("Exception happened:"+e);
        }
    }
}
