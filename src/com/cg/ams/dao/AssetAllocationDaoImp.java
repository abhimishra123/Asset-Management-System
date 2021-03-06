package com.cg.ams.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.ams.bean.Asset;
import com.cg.ams.bean.AssetAllocation;
import com.cg.ams.bean.AssetForm;
import com.cg.ams.exception.AssetAlreadyExistException;
import com.cg.ams.exception.InvalidIdException;
import com.cg.ams.exception.ReadOperationFailed;
import com.cg.ams.exception.UpdateFailedException;


public class AssetAllocationDaoImp implements AssetAllocationDao {
	private static HashMap<String, AssetAllocation> allocations = new HashMap<String, AssetAllocation>();
	static final Logger logger = Logger.getLogger(UserMasterDaoImp.class);
	static {
		PropertyConfigurator.configure("C:\\Abhi\\Asset Management System\\log4j\\log4j.properties");
	}
	@Override
	public AssetAllocation readAssetAllocation(String allocationId) throws ReadOperationFailed{
		AssetAllocation a = allocations.get(allocationId);
		if(a != null) {
			logger.info("Read Asset Allocation Successful");
			return a;
			}
			else {
				logger.info("Read Asset Allocation Failed");
				throw new  ReadOperationFailed();
				
			}
	}
	
	public boolean requestApproveDao(String requestId) throws ReadOperationFailed, AssetAlreadyExistException, InvalidIdException{
		AssetFormDaoImp f = new AssetFormDaoImp();
		AssetDaoImp asset = new AssetDaoImp(); 
		
		AssetForm form = f.read(requestId); 
		String empno = form.getEmpId();
		LocalDate release_date = form.getReleaseDate();
		LocalDate allocation_date = LocalDate.now();
		String assetName = form.getAssetName();
		System.out.println(assetName);
		String assetId = asset.read(assetName);
		String allocationId=assetId+empno;
		AssetAllocation a=new AssetAllocation( allocationId, assetId, empno,  allocation_date, release_date);
			System.out.println(a);	 
		boolean result = createAssetAllocation(a);
		f.changeStatusDao(requestId);
		if(result) {
			f.changeStatusDao(requestId);
			logger.info("Request Approved");
			return true;
		}
		else {
			throw new  AssetAlreadyExistException();
			
		}
	}
	@Override
	public boolean createAssetAllocation(AssetAllocation a)  throws  AssetAlreadyExistException{
		AssetAllocation result=allocations.putIfAbsent(a.getAllocationId(), a);
	    if(result==null) {
	    	logger.info("User Master Created Successfully");
	    	return true;
	    }
	    logger.info("User Master not created Successfully");
	    throw new  AssetAlreadyExistException();
		
		
	}
	@Override
	public boolean updateAssetAllocation(String allocationId, AssetAllocation a) throws UpdateFailedException{
		AssetAllocation result = allocations.put(allocationId, a);
		if (result != null) {
			logger.info("Updation SuccessFul");
			return true;
		}
		logger.info("Updation Failed");
		throw new UpdateFailedException();
		
	}
	@Override
	public boolean deleteAssetAllocation(String allocationId)throws InvalidIdException{
		AssetAllocation a = allocations.remove(allocationId);
		if (a != null) {
			
			return true;
		}
		throw new InvalidIdException();
		
	}
	public static void mockData() {
		allocations.put("xyz1",new AssetAllocation("xyz1","ABC2","123", LocalDate.parse("2020-02-13"), LocalDate.parse("2020-02-27")));
		allocations.put("xyz2",new AssetAllocation("xyz2","ABC4","124", LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-24")));
		allocations.put("xyz3",new AssetAllocation("xyz3","ABC5","125", LocalDate.parse("2020-01-15"), LocalDate.parse("2020-02-01")));
		allocations.put("xyz4",new AssetAllocation("xyz4","ABC3","126", LocalDate.parse("2020-01-13"), LocalDate.parse("2020-02-21")));
		allocations.put("xyz5",new AssetAllocation("xyz6","ABC1","127", LocalDate.parse("2020-01-20"), LocalDate.parse("2020-02-27")));
	}
	public void print() {
		System.out.println(allocations);
	}
}
