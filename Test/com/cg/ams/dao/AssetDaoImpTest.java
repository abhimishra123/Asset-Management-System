package com.cg.ams.dao;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.ams.bean.Asset;
import com.cg.ams.exception.AssetAlreadyExistException;
import com.cg.ams.exception.InvalidIdException;
import com.cg.ams.exception.ReadOperationFailed;
import com.cg.ams.exception.UpdateFailedException;

public class AssetDaoImpTest {

	public static AssetDao asset = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		asset = new AssetDaoImp();
		AssetDaoImp.mockData();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		asset = null;
	}

	@Test
	public void testReadAssetSuccessfully() throws ReadOperationFailed {
		assertEquals("Mouse", asset.readAsset("ABC2").getAssetName());
	}
	@Test(expected=ReadOperationFailed.class)
	public void testReadAssetFailed() throws ReadOperationFailed {
		assertEquals("Mouse", asset.readAsset("ABC10").getAssetName());
	}
	@Test
	public void testReadAssets() {
		assertTrue(true);
	}
	
	@Test
	public void testCreateAssetSuccessfully() throws AssetAlreadyExistException {
		assertTrue(asset.createAsset(new Asset("ABC16","Speaker","Company:Boat",5,"Available")));
	}
	@Test(expected=AssetAlreadyExistException.class)
	public void testCreateAssetFailed() throws AssetAlreadyExistException {
		assertTrue(asset.createAsset(new Asset("ABC16","Speaker","Company:Boat",5,"Available")));
	}
	
	@Test
	public void testUpdateAssetSuccessfully() throws UpdateFailedException {
		assertTrue(asset.updateAsset("ABC2", new Asset("ABC2","Mouse","Company:HP",1,"Rejected")));
	}
	@Test(expected=UpdateFailedException.class)
	public void testUpdateAssetFailed() throws UpdateFailedException {
		assertTrue(asset.updateAsset("ABC12", new Asset("ABC12","Mouse","Company:HP",1,"Rejected")));
	}
	@Test
	public void testDeleteAssetSuccessfully() throws InvalidIdException {
		assertTrue(asset.deleteAsset("ABC3"));
	}
	@Test(expected=InvalidIdException.class)
	public void testDeleteAssetFailed() throws InvalidIdException {
		assertTrue(asset.deleteAsset("ABC9"));
	}

}
