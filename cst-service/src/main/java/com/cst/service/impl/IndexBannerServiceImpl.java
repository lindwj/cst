package com.cst.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

import com.cst.service.*;
import com.cst.service.dao.*;
import com.cst.service.model.*;

/**
 * @author lind
 *
 */

@Service("IndexBannerService")
public class IndexBannerServiceImpl implements IndexBannerService{
	@Autowired
	private IndexBannerMapper indexBannerMapper;
	
	@Override
	public List<IndexBanner> getIndexBannerListPage(IndexBanner indexBanner){
		return indexBannerMapper.getIndexBannerListPage(indexBanner);
	}
	
	@Override
	public IndexBanner getIndexBannerById(int indexBannerId){
		return indexBannerMapper.getIndexBannerById(indexBannerId);
	}

	@Override
	public int saveIndexBanner(IndexBanner indexBanner,String doWhat){
		if("add".equals(doWhat)){
			return indexBannerMapper.insertIndexBanner(indexBanner.getIndexBannerList());
		}else if("edit".equals(doWhat)){
			
			for(IndexBanner temp:indexBanner.getIndexBannerList()){
				indexBannerMapper.updateIndexBanner(temp);
				}
			
		}		
		return 0;
  	
	}
	
	@Override
	public void deleteIndexBanner(int indexBannerId){
		indexBannerMapper.deleteIndexBanner(indexBannerId);
	}
	
}