package com.cg.ams.dao;

import com.cg.ams.bean.Asset;
import com.cg.ams.bean.AssetAllocation;
import com.cg.ams.exception.AssetAlreadyExistException;
import com.cg.ams.exception.InvalidIdException;
import com.cg.ams.exception.ReadOperationFailed;
import com.cg.ams.exception.UpdateFailedException;

public interface AssetAllocationDao {
	AssetAllocation readAssetAllocation(String allocationId) throws ReadOperationFailed;
    boolean createAssetAllocation(AssetAllocation a) throws AssetAlreadyExistException;
    boolean updateAssetAllocation(String allocationId, AssetAllocation a) throws UpdateFailedException;
    boolean deleteAssetAllocation(String allocationId) throws InvalidIdException;
}
