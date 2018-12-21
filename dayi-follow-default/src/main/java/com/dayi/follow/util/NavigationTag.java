package com.dayi.follow.util;

import org.apache.taglibs.standard.tag.common.core.UrlSupport;
import com.dayi.mybatis.support.Page;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;


/**
 * 显示格式 首页 上一页 1 2 3 4 5 下一页 尾页
 */
public class NavigationTag extends TagSupport {
    static final long serialVersionUID = 2372405317744358833L;

    /**
     * request 中用于保存PageV3<E> 对象的变量名,默认为“page”
     */
    private String bean = "page";

    /**
     * 分页跳转的url地址,此属性必须
     */
    private String url = null;

    /**
     * 显示页码数量 0为简单分页
     */
    private int number = 5;

    /**
     * 分页类型
     */
    private int type = 1;
    /**
     * 是否优化分页,仅对主键为数值型的有效
     */
    private boolean optimize;

    @Override
    public int doStartTag() throws JspException {
        JspWriter writer = pageContext.getOut();
        Page<?> onePage = (Page<?>) pageContext.getRequest().getAttribute(bean);
        if (onePage == null)
            return SKIP_BODY;
        url = resolveUrl(url, pageContext);
        Object firstModel = null;
        Object lastModel = null;

        if (onePage.getResults() != null && onePage.getResults().size() > 0) {
            firstModel = onePage.getResults().get(0);
            lastModel = onePage.getResults().get(onePage.getResults().size() - 1);
        }
        try {
            if (onePage.getTotalRecord() > 0) {
                if (number > 0) {
                    //完成分页条
                    writer.print("<span class=\"recordTotal\">共<i data-id=\"pageRecordTotal\">" + onePage.getTotalRecord() + "</i>条数据</span>");
                    if (onePage.getTotalPage() > 1) {
                        writer.print("<div class=\"pages\">");
                        if (onePage.getPageNo() - 1 >= 1) {//是否还有上一页.
                            String preUrl = append(url, type, "pageNo", onePage.getPageNo() - 1);
                            if (optimize && firstModel != null) {
                                //preUrl = append(preUrl, type, PageUtil.getIdName(firstModel), PageUtil.getIdValue(firstModel));
                                preUrl = append(preUrl, type, "pre", "true");
                            }
                            writer.print("<a href=\"" + append(url, type, "pageNo", 1) + "\" class=\"pgbtn first\" title=\"首页\" data-id=\"1\">首页</a>");
                            writer.print("<a href=\"" + preUrl + "\" class=\"pgbtn prev\" title=\"上一页\"  data-id=\"" + (onePage.getPageNo() - 1) + "\">上一页</a>");

                        }

                        //显示当前页码的前2页码和后两页码
                        //若1 则 1 2 3 4 5, 若2 则 1 2 3 4 5, 若3 则1 2 3 4 5,
                        //若4 则 2 3 4 5 6 ,若10  则 8 9 10 11 12
                        long currIndex = onePage.getPageNo();
                        long startIndex = (currIndex - 2 > 0 && onePage.getTotalPage() > number) ? currIndex - 2 : 1;
                    /*if (currIndex + 3 > onePage.getTotalPages() && onePage.getTotalPages() > (number - 1)) {
                        startIndex = onePage.getTotalPages() - (number - 1);
                    }*/
                        for (int i = 1; i <= number && startIndex <= onePage.getTotalPage(); startIndex++, i++) {
                            if (startIndex == currIndex) {
                                writer.print("<span class=\"pg current\">" + startIndex + "</span>");
                                continue;
                            }
                            String pageUrl = append(url, type, "pageNo", startIndex);
                            //long offset = 0;
                            //long id = 0;
                            if (optimize && firstModel != null && lastModel != null) {
                                if (startIndex < currIndex) {
                                    //pre
                                    pageUrl = append(pageUrl, type, "pre", "true");
                                    //offset = (startIndex - currIndex + 1) * onePage.getPageSize();
                                    //id = NumberUtils.toLong(PageUtil.getIdValue(firstModel)) + offset;
                                } else {
                                    //next
                                    //offset = (startIndex - currIndex - 1) * onePage.getPageSize();
                                    //id = NumberUtils.toLong(PageUtil.getIdValue(lastModel)) + offset;
                                }
                                //pageUrl = append(pageUrl, type, PageUtil.getIdName(lastModel), "" + id);
                            }
                            writer.print("<a href=\"" + pageUrl + "\" title=\"" + startIndex + "\" class=\"pg\" data-id=\"" + startIndex + "\">" + startIndex + "</a>");

                        }
                        if (onePage.getPageNo() + 1 <= onePage.getTotalPage()) {
                            String nextUrl = append(url, type, "pageNo", onePage.getPageNo() + 1);
                            writer.print("<a href=\"" + nextUrl + "\" class=\"pgbtn next\" title=\"下一页\" data-id=\"" + (onePage.getPageNo() + 1) + "\">下一页</a>");
                            writer.print("<a href=\"" + append(url, type, "pageNo", onePage.getTotalPage()) + "\" class=\"pgbtn last\" title=\"尾页\" data-id=\"" + onePage.getTotalPage() + "\">尾页</a>");
                        }
/*
                    //跳转功能
                    writer.print("<div class=\"pagesGoto\">");
                    writer.print("共" + onePage.getTotalPages() + "页 ");
                    writer.print("转第<input type=\"text\" name=\"pageNo\" value=\"" + onePage.getPageNo() + "\">页 ");
                    writer.print("<button type=\"button\" data-url=\"" + url + "\" data-type=\""+type+"\">确认</button>");
                    writer.print("</div>");
*/

                        writer.print("</div>");
                    }
                } else {
                    //简单分页条
                    writer.print("<div class=\"pages\">");
                    if (onePage.getPageNo() - 1 >= 1) {
                        String preUrl = append(url, type, "pageNo", onePage.getPageNo() - 1);
                        if (optimize && firstModel != null) {
                            preUrl = append(preUrl, type, "pre", "true");
                        }
                        writer.print("<a href=\"" + append(url, type, "pageNo", 1) + "\" class=\"pgbtn first\" title=\"首页\">首页</a>");
                        writer.print("<a href=\"" + preUrl + "\" class=\"pgbtn prev\" title=\"上一页\">上一页</a>");
                    } else {
                        writer.print("<span class=\"pgbtn first\">首页</span>");
                        writer.print("<span class=\"pgbtn prev\">上一页</span>");
                    }
                    if (onePage.getPageNo() + 1 <= onePage.getTotalPage()) {
                        String nextUrl = append(url, type, "pageNo", onePage.getPageNo() + 1);
                        writer.print("<a href=\"" + nextUrl + "\" class=\"pgbtn next\" title=\"下一页\">下一页</a>");
                        writer.print("<a href=\"" + append(url, type, "pageNo", onePage.getTotalPage()) + "\" class=\"pgbtn last\" title=\"尾页\">尾页</a>");
                    } else {
                        writer.print("<span class=\"pgbtn next\">下一页</span>");
                        writer.print("<span class=\"pgbtn last\">尾页</span>");
                    }
                    writer.print("</div>");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    /**
     * 为url 添加上下文环境.如果是登陆用户则还要添加uid参数
     *
     * @param url
     * @param pageContext
     * @return
     * @throws JspException
     */
    private String resolveUrl(String url, javax.servlet.jsp.PageContext pageContext) throws JspException {
        url = UrlSupport.resolveUrl(url, null, pageContext);

        url = url.replaceAll("&pageNo=\\d*", "").replaceAll("\\?pageNo=\\d*", "").replaceAll("&pre=true", "").replaceAll("&id=\\d*", "").replaceAll("\\?id=\\d*", "").replaceAll("\\?$", "");

        return url;
    }


    private String append(String url, int type, String key, long value) {
        return append(url, type, key, String.valueOf(value));
    }

    /**
     * 为url 参加参数对儿
     *
     * @param url
     * @param key
     * @param value
     * @return
     */
    private String append(String url, int type, String key, String value) {
        if (url == null || url.trim().length() == 0) {
            return "";
        }

        if (url.indexOf(".html") != -1) {
            if (type == 2) {
                url = url.replaceAll(".html", "-" + value + ".html");
            } else if (type == 1) {
                url = url.replaceAll(".html", "_00" + value + ".html");
            } else if (type == 3) {
                if (url.indexOf("?") == -1) {
                    url = url + "?" + key + "=" + value;
                } else {
                    if (url.endsWith("?")) {
                        url = url + key + "=" + value;
                    } else {
                        url = url + "&amp;" + key + "=" + value;
                    }
                }
            }

        } else {
            if (url.indexOf("?") == -1) {
                url = url + "?" + key + "=" + value;
            } else {
                if (url.endsWith("?")) {
                    url = url + key + "=" + value;
                } else {
                    url = url + "&amp;" + key + "=" + value;
                }
            }
        }
        return url;
    }


    /**
     * @return the bean
     */
    public String getBean() {
        return bean;
    }

    /**
     * @param bean the bean to set
     */
    public void setBean(String bean) {
        this.bean = bean;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setOptimize(boolean optimize) {
        this.optimize = optimize;
    }

}
