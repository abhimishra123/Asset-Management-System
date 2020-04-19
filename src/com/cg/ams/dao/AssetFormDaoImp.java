package com.cg.ams.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.ams.bean.AssetForm;
import com.cg.ams.exception.AssetAlreadyExistException;
import com.cg.ams.exception.InvalidIdException;
import com.cg.ams.exception.ReadOperationFailed;
import com.cg.ams.exception.UpdateFailedException;

public class AssetFormDaoImp implements AssetFormDao {
	private static HashMap<String, AssetForm>forms = new HashMap<String, AssetForm>();
	static final Logger logger = Logger.getLogger(UserMasterDaoImp.class);
	static {
		PropertyConfigurator.configure("C:\\Users\\Abhishek\\Desktop\\CoreJava\\Ab\\log4j\\log4j.properties");
	}
	@Override
	public ArrayList<AssetForm> readAssetforms()throws ReadOperationFailed {
		List<AssetForm> result = forms.values().stream().collect(Collectors.toList()); 
		logger.info("Asset Read Successfully");
		return new ArrayList<AssetForm>(result);
	}
	@Override
	public boolean createAssetForm(AssetForm a) throws AssetAlreadyExistException{
		AssetForm result=forms.putIfAbsent(a.getRequestId(), a);
	    if(result == null) {
	    	return true;
	    }
	    else {
	    	throw new AssetAlreadyExistException();
	    }
			
		}
	@Override
	public boolean update(AssetForm form)throws UpdateFailedException{
		AssetForm result=forms.putIfAbsent(form.getRequestId(), form);
		if(result != null) {
			logger.info("Update OPeration SuccessFul");
	    	return true;
	    }
	    else {
	    	logger.info("Update OPeration UnSuccessFul");
	    	throw new UpdateFailedException();
	    	
	    }
	    
	}
	@Override
	public String checkStatusDao(String assetRequestId) throws InvalidIdException{
		AssetForm a = forms.get(assetRequestId);
		System.out.println(forms);
		if(a != null) {
			logger.info("Checking Status Successful");
			return a.getStatus();
		}
		else {
			logger.info("Checking Status Successful");
			throw new InvalidIdException();
		}
		
	}
	@Override
	public boolean changeStatusDao(String assetRequestId)throws InvalidIdException{
		AssetForm a = forms.get(assetRequestId);
		 if(a != null) {
			 a.setStatus("Allocated");
			 return true;
			}
			else {
				throw new InvalidIdException();
				
			}
	}

	@Override
	public boolean requestDeclineDao(String assetRequestId) throws InvalidIdException{
		AssetForm a = forms.get(assetRequestId);
		 if(a != null) {
			 a.setStatus("Declined");
			 return true;
			}
			else {
				throw new InvalidIdException();
			}
	}
	@Override
	public AssetForm read(String requestId) throws InvalidIdException{
		AssetForm a = forms.get(requestId);
		if(a != null) {
			return a;
			}
			else {
				throw new  InvalidIdException();
			}
	}
	public static void mockData() {
		forms.put("1",new AssetForm("456","Rakesh","Mouse","456Rakesh",LocalDate.parse("2017-10-22"),"Processing"));
		forms.put("2",new AssetForm("457","Rakesh","CPU","457Rakesh",LocalDate.parse("2017-10-22"),"Processing"));
		forms.put("3",new AssetForm("458","Rakesh","Monitor","458Rakesh",LocalDate.parse("2017-10-22"),"Processing"));
		forms.put("4",new AssetForm("459","Rakesh","Mouse","459Rakesh",LocalDate.parse("2017-10-22"),"Processing"));
		
	}
	
}
