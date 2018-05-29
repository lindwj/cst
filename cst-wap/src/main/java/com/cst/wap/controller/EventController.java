package com.cst.wap.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cst.service.model.Event;
import com.cst.service.model.EventForm;
import com.cst.service.EventService;




/**
 * @author lind
 */

@Controller
@RequestMapping("/event")
public class EventController {
	
	// 业务逻辑对象
	@Autowired
	private EventService eventService;
	
	// 查询结果
	private List<Event> eventList;
	
	private EventForm eventForm=new EventForm();
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	@RequestMapping("/eventListPage.do")
	@ResponseBody
	public String eventListPage() throws Exception{
		//eventForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		Event event=eventForm.getEvent();
		eventList = eventService.getEventListPage(event);
		eventForm.setEvent(event);
		request.setAttribute("page", eventForm.getPage());
		return "list";
	}
	
	/** 编辑前初始化对象*/
	@RequestMapping("/eventAddEditIni.do")
	@ResponseBody
	public Event eventAddEditIni(Event event) throws Exception{
//		Event event=eventForm.getEvent();
//		if ("edit".equals(doWhat)) {
//			event = this.eventService.getEventById(event.getId());
//			eventForm.setEvent(event);
//		}	
		
		event = this.eventService.getEventById(event.getId());
		return event;
	}
	
	/** 查看对象*/
	@RequestMapping("/eventDetail.do")
	@ResponseBody
	public String eventDetail() throws Exception {
//		this.setPare_moduleid(14);	
		Event event=eventForm.getEvent();
		event = this.eventService.getEventById(event.getId());
		eventForm.setEvent(event);
		return "detail";
	}

	@RequestMapping("/editCancel.do")
	@ResponseBody
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		Event event=eventForm.getEvent();
		event = this.eventService.getEventById(event.getId());
		eventForm.setEvent(event);
		return "detail";
	}
	/** 保存新增对象 */
	@RequestMapping("/eventAddEdit.do")
	@ResponseBody
	public String eventAddEdit() throws Exception {		
		//this.setPare_moduleid(14);
        //eventForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
        Event event=eventForm.getEvent();
		this.eventService.saveEvent(event, this.doWhat);
		eventForm.setEvent(event);
		return "detail";
	}
	/**删除对象*/
	@RequestMapping("/eventDelete.do")
	@ResponseBody
	public String eventDelete() throws Exception {	
		Event event=eventForm.getEvent();
		this.eventService.deleteEvent(event.getId());
		return "list";
	}	

	public EventForm getModel() {
		return eventForm;
	}
	
	public List<Event> getEventList() {
		return this.eventList;
	}

	public void setEventList(List<Event> eventList) {
		this.eventList = eventList;
	}
	
	public String getDoWhat() {
		return doWhat;
	}

	public void setDoWhat(String doWhat) {
		this.doWhat = doWhat;
	}
	
	public void setPare_moduleid(int pareModuleid) {
		pare_moduleid = pareModuleid;
	}
	
	
}
