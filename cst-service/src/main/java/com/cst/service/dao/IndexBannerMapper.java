package com.cst.service.dao;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface IndexBannerMapper {
	
	public abstract List<IndexBanner> getIndexBannerListPage(IndexBanner indexBanner);
	
	public abstract IndexBanner getIndexBannerById(int indexBannerId);

	public abstract int insertIndexBanner(List<IndexBanner> indexBannerList);

	public abstract void updateIndexBanner(IndexBanner indexBanner);

	public abstract void deleteIndexBanner(int indexBannerId);
	
}
