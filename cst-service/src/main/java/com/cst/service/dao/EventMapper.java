package com.cst.service.dao;

import java.util.*;
import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface EventMapper {
	
	public abstract List<Event> getEventListPage(Event event);
	
	public abstract Event getEventById(int id);

	public abstract int insertEvent(Event event);

	public abstract void updateEvent(Event event);

	public abstract void deleteEvent(int id);
	
}
