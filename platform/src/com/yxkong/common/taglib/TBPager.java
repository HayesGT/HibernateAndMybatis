package com.yxkong.common.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


public class TBPager extends TagSupport {
	private static final long serialVersionUID = -8341120465173736955L;
	//当前页
	private Integer pageNo;
	//共多少页
	private Integer totalPage;
	//每页多少条
	private Integer pageSize =10;
	//总数
	private Integer totalCount = 0;
	//表单id
	private String formId;

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}
	
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public int doStartTag() throws JspException {
		
		JspWriter out = pageContext.getOut();

		if (totalCount%pageSize==0) {
			totalPage = totalCount/pageSize;
		} else {
			totalPage = (totalCount/pageSize)+1;
		}
		if (pageNo < 1) {
			pageNo = 1;
		}
		try {
			if (totalPage>1) {
				StringBuffer pageSb=new StringBuffer();
				pageSb.append("var _pageNo=document.createElement(\"input\");")
				      .append("_pageNo.name=\"pageNo\";")
				      .append("_pageNo.id=\"pageNo\";")
				      .append("_pageNo.type=\"hidden\";")
				      .append("_pageNo.value=pageNum;")
				      .append("var _pageSize=document.createElement(\"input\");")
				      .append("_pageSize.name=\"pageSize\";")
				      .append("_pageSize.id=\"pageSize\";")
				      .append("_pageSize.type=\"hidden\";")
				      .append("_pageSize.value=\"").append(pageSize).append("\";")
				      .append("_form.appendChild(_pageNo);")
				      .append("_form.appendChild(_pageSize);");
				out.print("<script type='text/javascript'>" + 
						  		"function go(pageNum){" + 
						  			"var _form = document.getElementById('" + formId + "');"+
						  			pageSb.toString()+
						  			"_form.submit();"+
		  						"}" + 
						  "</script>");
				
				out.print("<div class='pagination'><ul>");
				initPagination(out, totalPage, pageNo);
				out.print("</ul></div>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return super.doStartTag();

	}
	private void initPagination(JspWriter out,int totalPage,int pageNo)throws Exception{
		//中间显示几条
		int pageShow = 5;  
        int start = 0;   
        int end = 0;  
        if (pageNo < pageShow) {  
            start = 1;  
            end = pageShow;  
            if(pageNo>3&&totalPage>=pageShow+2){
            	end = pageShow+1;  
            }
        } else {  
            start = pageNo - 2;  
            end = pageNo + 2;  
            //右侧补到5条
            if(totalPage-pageNo<=pageShow-3){
            	start=totalPage-pageShow+1;
            	if(start==2){
            		start=1;
            	}
        	}
        }  
        if (end > totalPage) {  
            end = totalPage;  
        }         
        if (pageNo > 1) {  
        	out.print("<li><a href='javascript:go("+(pageNo-1)+")'>上一页</a></li>");
        }else{
        	out.print("<li class='pre-disabled'><span>上一页</span></li>");
        }  
        if (start > 1) {  
        	out.print("<li><a href='javascript:go(1)'>1</a></li>");  
        	out.print("<li class='dot'>...</li>");  
        }  
        
        for (int i = start; i <= end; i++) {  
            if (i == pageNo) {  
                out.print("<li class='active'><a href='javascript:go("+i+")'>" + i + "</a></li>");  
            } else {  
                out.print("<li><a href='javascript:go("+i+")'>" + i + "</a></li>");  
            }  
        }             
        if (end < totalPage) {  
            if (end != totalPage - 1) {  
                out.print("<li class='dot'>...</li>");  
            }  
            out.print("<li><a href='javascript:go("+totalPage+")'>" + totalPage + "</a></li>");  
        }  
        if (pageNo < totalPage) {  
            out.print("<li><a href='javascript:go("+(pageNo+1)+")'>下一页</a></li>");  
        }else{
        	 out.print("<li class='next-disabled'><span>下一页</span></li>");  
        }  
	}
}
