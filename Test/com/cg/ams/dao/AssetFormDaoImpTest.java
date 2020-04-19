package com.cg.ams.dao;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.ams.bean.Asset;
import com.cg.ams.bean.AssetForm;
import com.cg.ams.exception.AssetAlreadyExistException;
import com.cg.ams.exception.InvalidIdException;
import com.cg.ams.exception.ReadOperationFailed;
import com.cg.ams.exception.UpdateFailedException;

public class AssetFormDaoImpTest {

	public static AssetFormDao asset = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		asset = new AssetFormDaoImp();
		AssetFormDaoImp.mockData();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		asset = null;
	}

	@Test
	public void testReadAssetSuccessfully() throws InvalidIdException {
		assertEquals("Mouse", asset.read("456Rakesh").getAssetName());
	}
	@Test(expected=InvalidIdException.class)
	public void testReadAssetFailed() throws InvalidIdException,ReadOperationFailed {
		assertEquals("Mouse", asset.read("45678Ramu").getAssetName());
	}
	@Test
	public void testReadAssets() {
		assertTrue(true);
	}
	
	@Test
	public void testCreateAssetSuccessfully() throws AssetAlreadyExistException {
		assertTrue(asset.createAssetForm(new AssetForm("460","RaKesh","Speaker","460Rakesh",LocalDate.parse("2017-10-21"),"Processing")));
	}
	@Test(expected=AssetAlreadyExistException.class)
	public void testCreateAssetFailed() throws AssetAlreadyExistException {
		assertTrue(asset.createAssetForm(new AssetForm("459","Rakesh","Mouse","459Rakesh",LocalDate.parse("2017-10-22"),"Processing")));
	}
	@Test
	public void testUpdateAssetSuccessfully() throws UpdateFailedException {
		assertTrue(asset.update(new AssetForm("459","Rakesh","Mouse","459Rakesh",LocalDate.parse("2017-10-22"),"Processing")));
	}
	@Test(expected=UpdateFailedException.class)
	public void testUpdateAssetFailed() throws UpdateFailedException {
		assertTrue(asset.update(new AssetForm("461","Rakesh","Mouse","461Rakesh",LocalDate.parse("2017-10-23"),"Processing")));
	}
	
}
