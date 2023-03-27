package com.reflect.demo.service;

import java.util.List;

import com.reflect.demo.entity.Action;
import com.reflect.demo.instance.OverviewAction;
import com.reflect.demo.instance.PaginationInfo;


public interface ActionService {
	Action createAction(Action action);

    Action getActionById(Long id);
    
    Action updateAction(Action action);

    List<Action> getAllActions(int pageNumber,int pageSize);
    
    void deleteActionById(Long id);
    
    public PaginationInfo getPaginationInfo();
    
    List<Action> getAllActionsForList(int pageNumber,int intpageSize);
}
