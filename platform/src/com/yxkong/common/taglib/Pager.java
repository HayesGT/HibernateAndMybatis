package com.yxkong.common.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


public class Pager extends TagSupport {
	private static final long serialVersionUID = 1748808483489292847L;
	//当前页
	private Integer pageNo;
	//共多少页
	private Integer totalPage;
	//每页多少条
	private Integer pageSize = 10;
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
				int start = 1;
				int end = totalPage;
				for(int i=4;i>=1;i--){
					if((pageNo-i)>=1){
						start = pageNo-i;
						break;
					}
				}
				for(int i=4;i>=1;i--){
					if((pageNo+i)<=totalPage){
						end = pageNo+i;
						break;
					}
				}
				//如果小于9则右侧补齐
				if(end-start+1<=9){
					Integer padLen = 9-(end-start+1);
					for(int i=padLen;i>=1;i--){
						if((end+i)<=totalPage){
							end = end+i;
							break;
						}
					}
				}
				
				//如果还小于9左侧补齐
				if(end-start+1<=9){
					Integer padLen = 9-(end-start+1);
					for(int i=padLen;i>=1;i--){
						if((start-i)>=1){
							start = start-i;
							break;
						}
					}
				}
				
				if(pageNo>1){
//					if(start>1){
						out.print("<li><a href='javascript:go(1)'>首页</a></li>");
//					}
					out.print("<li><a href='javascript:go("+(pageNo-1)+")'>上一页</a></li>");
				}else{
					out.print("<li class='pre-disabled'><span>首页</span></li>");
					out.print("<li class='pre-disabled'><span>上一页</span></li>");
				}
				
				for(int i=start;i<=end;i++){
					if(i==pageNo){
						out.print("<li class='active'><a href='javascript:go("+i+")'>" + i + "</a></li>");
					}else{
						out.print("<li><a href='javascript:go("+i+")'>" + i + "</a></li>");
					}
				}
				if(pageNo<totalPage){
					out.print("<li><a href='javascript:go("+(pageNo+1)+")'>下一页</a></li>");
//					if(end<totalPage){
						out.print("<li><a href='javascript:go("+totalPage+")'>尾页</a></li>");
//					}
				}else{
					out.print("<li class='next-disabled'><span>下一页</span></li>");
					out.print("<li class='next-disabled'><span>尾页</span></li>");
				}
				//out.print("<li><a href='javascript:void(0)'>共" + totalPage + "页" + this.totalCount + "条</a></li>");
				out.print("</ul></div>");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return super.doStartTag();

	}
	
	public static Integer getStartIndex(Integer pageNum, Integer pageSize){
		Integer res = 0;
		if(pageNum>0){
			res = (pageNum-1)*pageSize;
		}
		return res;
	}
}
