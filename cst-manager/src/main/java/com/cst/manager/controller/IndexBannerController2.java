package com.cst.manager.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cst.service.model.IndexBanner;
import com.cst.service.model.IndexBannerForm;
import com.cst.service.model.Manager;
import com.cst.service.model.Parameter;
import com.cst.service.model.Product;
import com.cst.service.model.ProductPic;
import com.cst.service.model.User;
import com.cst.service.util.Common;
import com.cst.service.IndexBannerService;
import com.cst.service.ParameterService;
import com.cst.service.ProductService;



/**
 * @author lind
 */

@Controller
@RequestMapping("/indexBanner")
public class IndexBannerController2{
	
	// 业务逻辑对象
	@Autowired
	private IndexBannerService indexBannerService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ParameterService parameterService;
	
	// 查询结果
	private List<IndexBanner> indexBannerList;
	
	private IndexBannerForm indexBannerForm=new IndexBannerForm();
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	/** 执行搜索 */
	@RequestMapping("/indexBannerListPage.do")
	public String indexBannerListPage(IndexBanner indexBanner,Model model) throws Exception{
		//indexBannerForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
//		IndexBanner indexBanner=null;
		
//		indexBannerForm.setIndexBanner(indexBanner);
//		request.setAttribute("page", indexBannerForm.getPage());
		
		Subject subject = SecurityUtils.getSubject();
		Manager mg = (Manager) subject.getPrincipal();

//		shuidian.setCreateByAdm(mg.getManagerId());
//		Shuidian shuidian=shuidianForm.getShuidian();
		indexBannerList = indexBannerService.getIndexBannerListPage(indexBanner);
//		shuidianForm.setShuidian(shuidian);
//		request.setAttribute("page", shuidianForm.getPage());
		
		model.addAttribute("indexBannerList", indexBannerList);
		model.addAttribute("page", indexBanner.getPage());
		model.addAttribute("errcode", indexBanner.getErrcode());
		
		if(indexBanner.getMenuId()!=null&&indexBanner.getMenuId()>0){
		subject.getSession().setAttribute("menuId", indexBanner.getMenuId());
		}
		
		
		return "/jsp/shuidian/shuidianuploadIndex";
	}
	
	/** 编辑前初始化对象*/
	@RequestMapping("/indexBannerAddEditIni.do")
	public String indexBannerAddEditIni(IndexBanner indexBanner,Model model) throws Exception{
		
		
		model.addAttribute("errcode", indexBanner.getErrcode());
		
//		List<IndexBanner> indexBannerList = this.indexBannerService.getIndexBannerListPage(indexBanner);
		
//		if(indexBannerList==null || indexBannerList.size()==0){
			indexBanner.setDowhat("add");
//		}else{
//			indexBanner.setDowhat("edit");
//		}
		
//		indexBanner.setIndexBannerList(indexBannerList);
		
		model.addAttribute("indexBanner", indexBanner);
		
		if(indexBanner.getMenuId()!=null&&indexBanner.getMenuId()>0){
			Subject subject = SecurityUtils.getSubject();
			subject.getSession().setAttribute("menuId", indexBanner.getMenuId());
			}
		return "/jsp/indexBanner/indexBanner";
	}
	
	/** 查看对象*/
	public String indexBannerDetail() throws Exception {
//		this.setPare_moduleid(14);	
		IndexBanner indexBanner=null;
		indexBanner = this.indexBannerService.getIndexBannerById(indexBanner.getIndexBannerId());
		indexBannerForm.setIndexBanner(indexBanner);
		return "detail";
	}
	public String editCancel() throws Exception {	
//		this.setPare_moduleid(14);		
		IndexBanner indexBanner=null;
		indexBanner = this.indexBannerService.getIndexBannerById(indexBanner.getIndexBannerId());
		indexBannerForm.setIndexBanner(indexBanner);
		return "detail";
	}
	/** 保存新增对象 */
	@RequestMapping("/indexBannerAddEdit.do")
	public ModelAndView indexBannerAddEdit(IndexBanner indexBanner, ModelAndView model) throws Exception {		
		//this.setPare_moduleid(14);
        //indexBannerForm.setStaffId(String.valueOf(session.get("STAFF_ID")));
		
		
		
		
		
		
		String validate = null;
		List <IndexBanner> pplistedit=new ArrayList<IndexBanner>();
		List <IndexBanner> pplistadd=new ArrayList<IndexBanner>();
		// add 新增 edit 修改
		if ("add".equals(indexBanner.getDowhat())) {

			// 后端校验 101 排序 状态 图片 等不能为空
			
			if(indexBanner.getIndexBannerList()==null){
				validate = "101";
			}
			
			
			List <IndexBanner> pplist=new ArrayList<IndexBanner>();
			for(IndexBanner temp:indexBanner.getIndexBannerList()){
				if (temp.getBannerUrl() == null || temp.getBannerUrl().length() == 0) {
					validate = "101";
				}
				if (temp.getSort() == null || temp.getSort()< 0) {
					validate = "101";
				}
				
				if("101".equals(validate)){
				pplist.add(temp);
				}
				
			}
			//去除空图片 空值 行
			indexBanner.getIndexBannerList().removeAll(pplist);
			
			if(indexBanner.getIndexBannerList()==null || indexBanner.getIndexBannerList().size()==0){
			
				model.addObject("errcode", "101");
				model.setViewName("redirect:/indexBanner/indexBannerAddEditIni.do");
				return model;
			}

			// 校验结束


		} else if ("edit".equals(indexBanner.getDowhat())) {
			if(indexBanner.getIndexBannerList()==null){
				validate = "101";
			}
			
			
			
			for(IndexBanner temp:indexBanner.getIndexBannerList()){
				
				if (temp.getSort() == null || temp.getSort()< 0) {
					validate = "101";
					continue;
				}
				
				//筛选出有getProductPicId 值，说明是需要修改的
				if (temp.getIndexBannerId() != null && temp.getIndexBannerId()> 0) {
					pplistedit.add(temp);
				}
				
				
				//筛选出没有getProductPicId 值，图片有值得，说明是需要新增的
				if ((temp.getIndexBannerId() == null || temp.getIndexBannerId()<= 0) && (temp.getBannerUrl() != null && temp.getBannerUrl().length()> 0)) {
					pplistadd.add(temp);
				}
				
				
			}
			

			if ((pplistadd.size()+pplistedit.size())==0) {
				model.addObject("errcode", "101");
				model.setViewName("redirect:/indexBanner/indexBannerAddEditIni.do");
				return model;
			}
		}

		try {
			if("add".equals(indexBanner.getDowhat())){
				this.indexBannerService.saveIndexBanner(indexBanner, indexBanner.getDowhat());
			}else{
				//插入 修改时 ，新增的内容
				if(pplistadd.size()>0){
					indexBanner.setIndexBannerList(pplistadd);
					this.indexBannerService.saveIndexBanner(indexBanner, "add");
				}
				
				//修改
				if(pplistedit.size()>0){
					indexBanner.setIndexBannerList(pplistedit);
					this.indexBannerService.saveIndexBanner(indexBanner,indexBanner.getDowhat());
				}
			}
			
		} catch (DuplicateKeyException be) {
			// 重复值
			model.addObject("errcode", "100");

			if ("edit".equals(indexBanner.getDowhat())) {
				model.setViewName("redirect:/indexBanner/indexBannerAddEditIni.do");
				return model;
			}

		}

		model.setViewName("redirect:/indexBanner/indexBannerAddEditIni.do");
		return model;
	}
	
	/**删除对象*/
	@RequestMapping("/indexBannerDelete.do")
	public String indexBannerDelete(IndexBanner indexBanner) throws Exception {	
		this.indexBannerService.deleteIndexBanner(indexBanner.getIndexBannerId());
		return "redirect:/indexBanner/indexBannerAddEditIni.do";
	}	

	public IndexBannerForm getModel() {
		return indexBannerForm;
	}
	
	public List<IndexBanner> getIndexBannerList() {
		return this.indexBannerList;
	}

	public void setIndexBannerList(List<IndexBanner> indexBannerList) {
		this.indexBannerList = indexBannerList;
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
