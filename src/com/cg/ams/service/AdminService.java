package com.cg.ams.service;

import java.util.ArrayList;

import com.cg.ams.bean.Asset;
import com.cg.ams.exception.AssetAlreadyExistException;
import com.cg.ams.exception.InvalidIdException;
import com.cg.ams.exception.ReadOperationFailed;

public interface AdminService {
	public ArrayList assetAvailability() throws ReadOperationFailed;
	public boolean addAsset(Asset a) throws AssetAlreadyExistException;
	public ArrayList checkRequests() throws ReadOperationFailed;
	public boolean requestApprove(String requestId) throws ReadOperationFailed, AssetAlreadyExistException, InvalidIdException;
	public boolean requestDecline(String requestId) throws InvalidIdException;
}
