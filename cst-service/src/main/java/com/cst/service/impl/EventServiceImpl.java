package com.cst.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

import com.cst.service.model.*;
import com.cst.service.dao.*;
import com.cst.service.*;

/**
 * @author lind
 *
 */

@Service("EventService")
public class EventServiceImpl implements EventService{
	@Autowired
	private EventMapper eventMapper;
	
	@Override
	public List<Event> getEventListPage(Event event){
		return eventMapper.getEventListPage(event);
	}
	
	@Override
	public Event getEventById(int id){
		return eventMapper.getEventById(id);
	}

	@Override
	public int saveEvent(Event event,String doWhat){
		if("add".equals(doWhat)){
			return eventMapper.insertEvent(event);
		}else if("edit".equals(doWhat)){
			eventMapper.updateEvent(event);
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteEvent(int id){
		eventMapper.deleteEvent(id);
	}
	
}