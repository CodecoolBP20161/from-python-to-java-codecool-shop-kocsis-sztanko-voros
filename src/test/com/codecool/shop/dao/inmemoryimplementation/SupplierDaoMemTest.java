package com.codecool.shop.dao.inmemoryimplementation;

import com.codecool.shop.dao.SupplierDaoTest;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by dorasztanko on 2016.11.23..
 */
public class SupplierDaoMemTest extends SupplierDaoTest {

    @Before
    public void setUp() throws Exception {
        supplierDao = SupplierDaoMem.getInstance();
    }

    @Test
    public void getInstance() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


}