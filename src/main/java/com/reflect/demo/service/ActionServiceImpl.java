package com.reflect.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reflect.demo.dao.ActionDao;
import com.reflect.demo.entity.Action;
import com.reflect.demo.instance.OverviewAction;
import com.reflect.demo.instance.PaginationInfo;

@Service
public class ActionServiceImpl implements ActionService {

	@Autowired
	private ActionDao actionDao;
	
	private PaginationInfo paginationInfo = new PaginationInfo();
	@Override
	@Transactional
	public Action createAction(Action action) {
		return actionDao.save(action);
	}

	@Override
	@Transactional
	public Action getActionById(Long id) {
		Optional<Action> actionOpt = actionDao.findById(id);
		if (actionOpt.isPresent()) {
            return actionOpt.get();
        } else {
            // handle the case where no activity with the specified ID exists
            return null;
        }
	}

	@Override
	@Transactional
	public Action updateAction(Action action) {
		Optional<Action> actionOpt = actionDao.findById(action.getId());
		if (!actionOpt.isPresent()) {
			return null;
        }
		Action newAction = new Action();
        BeanUtils.copyProperties(action, newAction);
		return actionDao.update(newAction);
	}

	@Override
	@Transactional
	public List<Action> getAllActions(int pageNumber,int pageSize) {
		
		List<Action> actions = actionDao.findAll();
		setupPagination(actions.size(),pageNumber,pageSize);
		List<Action> paginatedActions = actions.stream()
	            .skip((long) (pageNumber - 1) * pageSize)
	            .limit(pageSize)
	            .collect(Collectors.toList());
		return paginatedActions;
	}
	
	@Override
	@Transactional   //deprecated function
	public List<Action> getAllActionsForList(int pageNumber,int pageSize){
		List<Object[]> queryResult = actionDao.findAllForList();
		List<Action> actionList = new ArrayList<Action>();
		for(Object[] datas: queryResult) {
			Action action = new Action();
			action.setId((Long)datas[0]);
			action.setObjective((String)datas[1]);
			action.setSatisfaction((int)datas[2]);
			action.setOutcome((String)datas[3]);
			action.setThoughts((String)datas[4]);
			action.setUpdatedAt(null);
		}
		return actionList;
	}
	
	@Override
	@Transactional
	public void deleteActionById(Long id) {
		Optional<Action> actionOpt = actionDao.findById(id);
		if (actionOpt.isPresent()) {
			actionDao.delete(actionOpt.get());
        }
	}
	
	private void setupPagination(int listSize,int pageNumber,int pageSize){
		paginationInfo.setCurrentPage(pageNumber);
		paginationInfo.setPageSize(pageSize);
		paginationInfo.setTotalPages((int) Math.ceil((double) listSize / pageSize));
		paginationInfo.setTotalItems(listSize);
	}
	
	
	@Override
	public PaginationInfo getPaginationInfo() {
		 return this.paginationInfo;
	}

}
