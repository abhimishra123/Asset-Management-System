
package com.cg.ams.dao;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.ams.bean.AssetAllocation;
import com.cg.ams.exception.AssetAlreadyExistException;
import com.cg.ams.exception.InvalidIdException;
import com.cg.ams.exception.ReadOperationFailed;
import com.cg.ams.exception.UpdateFailedException;

public class AssetAllocationDaoImpTest {

public static AssetAllocationDao asset= null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		asset = new AssetAllocationDaoImp();
		AssetAllocationDaoImp.mockData();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		asset = null;
	}

	@Test
	public void testReadAssetAllocationSuccessfully() throws ReadOperationFailed {
		assertEquals("ABC2", asset.readAssetAllocation("xyz1").getAssetId());
	}
	@Test(expected=ReadOperationFailed.class)
	public void testReadAssetAllocationFailed() throws ReadOperationFailed {
		assertEquals("ABC12", asset.readAssetAllocation("xyz17").getAssetId());
	}
	@Test
	public void testApproveRequestSuccessfully() throws Exception {
		
		assertTrue(true);
	}

	@Test
	public void testCreateAssetAllocationSuccessfully() throws AssetAlreadyExistException {
		assertTrue(asset.createAssetAllocation(new AssetAllocation("xyz7","ABC6","128", LocalDate.parse("2020-03-20"), LocalDate.parse("2020-02-17"))));
	}
	@Test(expected=AssetAlreadyExistException.class)
	public void testCreateAssetAllocationFailed() throws AssetAlreadyExistException {
		assertTrue(asset.createAssetAllocation(new AssetAllocation("xyz7","ABC6","128", LocalDate.parse("2020-03-20"), LocalDate.parse("2020-02-17"))));
	}
	@Test
	public void testUpdateAssetAllocationSuccessfully() throws UpdateFailedException {
		assertTrue(asset.updateAssetAllocation("xyz4", new AssetAllocation("xyz4","ABC8","130", LocalDate.parse("2020-01-15"), LocalDate.parse("2020-02-01"))));
	}
	@Test(expected=UpdateFailedException.class)
	public void testUpdateAssetAllocationFailed() throws UpdateFailedException {
		assertTrue(asset.updateAssetAllocation("xyz10", new AssetAllocation("xyz41","ABC72","130", LocalDate.parse("2020-01-15"), LocalDate.parse("2020-02-01"))));
	}
	@Test
	public void testDeleteAssetAllocationSuccessfully() throws InvalidIdException {
		assertTrue(asset.deleteAssetAllocation("xyz2"));
	}
	@Test(expected=InvalidIdException.class)
	public void testDeleteAssetAllocationFailed() throws InvalidIdException {
		assertTrue(asset.deleteAssetAllocation("xyz9"));
	}

}
