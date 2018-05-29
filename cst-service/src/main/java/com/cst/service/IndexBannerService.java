package com.cst.service;

import java.util.*;

import com.cst.service.model.*;
/**
 * @author lind
 *
 */
public abstract interface IndexBannerService {
	
	public abstract List<IndexBanner> getIndexBannerListPage(IndexBanner indexBanner);
	
	public abstract IndexBanner getIndexBannerById(int indexBannerId);
	
	public abstract int saveIndexBanner(IndexBanner indexBanner,String doWhat);

	public abstract void deleteIndexBanner(int indexBannerId);
	
}