package com.cg.ams.service;

import java.util.ArrayList;

import com.cg.ams.bean.Asset;
import com.cg.ams.bean.AssetForm;
import com.cg.ams.dao.AssetAllocationDaoImp;
import com.cg.ams.dao.AssetDaoImp;
import com.cg.ams.dao.AssetFormDao;
import com.cg.ams.dao.AssetFormDaoImp;
import com.cg.ams.exception.AssetAlreadyExistException;
import com.cg.ams.exception.InvalidIdException;
import com.cg.ams.exception.ReadOperationFailed;

public class AdminServiceImpl implements AdminService {
	AssetDaoImp asset=new AssetDaoImp();
	AssetFormDaoImp a= new AssetFormDaoImp();
		public ArrayList assetAvailability() throws ReadOperationFailed{
			ArrayList list = asset.readAssets();
			return list;
		}
		public boolean addAsset(Asset a) throws AssetAlreadyExistException{
			asset.mockData();
			boolean result = asset.createAsset(a);
			return result;
		}
		
		public ArrayList checkRequests() throws ReadOperationFailed{
			ArrayList list = a.readAssetforms();
			return list;
		}
		
		public boolean requestApprove(String requestId) throws ReadOperationFailed, AssetAlreadyExistException, InvalidIdException{
			AssetAllocationDaoImp a = new AssetAllocationDaoImp();
			boolean result = a.requestApproveDao(requestId);
			return result;
		}
		
		public boolean requestDecline(String requestId) throws InvalidIdException{
			AssetFormDaoImp a = new AssetFormDaoImp();
			boolean result = a.requestDeclineDao(requestId);
			return result;
		}

}
