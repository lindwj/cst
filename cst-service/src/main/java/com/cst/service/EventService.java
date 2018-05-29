package com.cst.service;

import java.util.*;
import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface EventService {
	
	public abstract List<Event> getEventListPage(Event event);
	
	public abstract Event getEventById(int id);
	
	public abstract int saveEvent(Event event,String doWhat);

	public abstract void deleteEvent(int id);
	
}