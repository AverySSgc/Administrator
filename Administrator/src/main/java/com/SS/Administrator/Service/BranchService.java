package com.SS.Administrator.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.SS.Administrator.DAO.BranchDAO;
import com.SS.Administrator.Entity.Branch;

@Component
@Transactional
public class BranchService {
	@Autowired
	private BranchDAO bDao;
	
	public List<Branch> readAllBranchs(){
		return bDao.findAll();
	}
	@Transactional
	public void addBranch(Branch branch) throws IllegalArgumentException{
		if(branch.getBranchId()==null) {
			Branch returned =bDao.save(branch);
			branch = returned;
		}else
			throw new IllegalArgumentException();
	}
	@Transactional
	public void updateBranch(Branch branch)throws NoSuchElementException{
		if(!bDao.existsById(branch.getBranchId()))
			throw new NoSuchElementException();
		Branch returned= bDao.save(branch);
		branch= returned;
	}
	@Transactional
	public Branch deleteBranch(int branchId)throws NoSuchElementException{
		Branch deleteBranch = bDao.findById(branchId).get();
		bDao.deleteById(branchId);
		return deleteBranch;
	}
	
	public Branch findBranchById(int branchId) {
		return bDao.findById(branchId).get();
	}
}
