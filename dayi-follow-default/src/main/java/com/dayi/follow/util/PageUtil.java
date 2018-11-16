package com.fiidee.eagle.framework.base.page;


public class PageUtil {
    public static String getPageUrl(String pageURI, String pageQuery) {
        if(pageQuery != null) {
            pageQuery = pageQuery.replaceAll("&t=\\d*", "").replaceAll("\\?t=\\d*", "").replaceAll("&pageNo=\\d*", "").replaceAll("pageNo=\\d*", "").replaceAll("&pre=true", "").replaceAll("&ajax=1", "");
            return pageURI + "?" + pageQuery;
        } else {
            return pageURI + "?";
        }
    }
    public static String getPageUrl(String pageURI) {
        return pageURI.replaceAll("_00\\d*","");
    }


    /**
     * 从源分页转部分属性到目标分页
     * @param orginalPage
     * @param pageTarget
     * @return
     */
    public static Page convertTargetPage(Page orginalPage,Page pageTarget){
        pageTarget.setPageNo(orginalPage.getPageNo());
        pageTarget.setPageSize(orginalPage.getPageSize());
        pageTarget.setTotalCount(orginalPage.getTotalCount());
        pageTarget.setOrderBy(orginalPage.getOrderBy());
        pageTarget.setOrderByField(orginalPage.getOrderByField());
        pageTarget.setOrderByDirection(orginalPage.getOrderByDirection());
        pageTarget.setAutoCount(orginalPage.isAutoCount());
        pageTarget.setOrderByField(orginalPage.getOrderByField());
        return pageTarget;
    }
}
