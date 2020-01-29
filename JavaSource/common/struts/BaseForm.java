/*
 * Created on 2004. 3. 23.
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package common.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.Factory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;


public class BaseForm
        extends ValidatorForm
{
    /**
     * Servlet의 동작을 결정 하는 action
     */
    private int strutsAction = BaseAction.DEFAULT_ACTION;
    /** Interface 동작을 결정하는 Action */
    private String serviceName = "";
    /** IF Data */
    private String data         = "";
    /** decorator Name */
    private String isDecoratorName = "";
    
    /**report의 qrd파일의 저장경로*/
    private static String reportSqlPath = "";
    /**report의 result Txt파일의 저장경로*/
    private static String reportResultTxtPath = "";
    
    /** Div Layer Popup ID  */
    private String curDiaId   = "";
    /** dhtmlx grid paging */
    private String firstRow   = "";
    /** dhtmlx grid TotalCount Loaded */
    private String isTotalCount = "0";

    /** Delete Ids */
    private String[] deleteRows = null;
    /** Select Ids */
    private String[] selectRows = null;
    /** Select Ids */
    private String[] selectRows1 = null;
    /** Delete Ext Ids */
    private String[] deleteRowsExt = null;
    /** Delete Ext Ids */
    private String[] deleteRowsExt1 = null;

    private String allArray = "";
    /** Tree checked Ids */
    private String[] checkRows = null;
    
    /** list ID */
    private String listId   = "";
    /** currentPageId */
    private String currentPageId    = "";
    
    private String isLoadMaxCount = "";
    /** Excel File Name */
    private String fileName = "";
    /** order By for dynamic smart loading */
    private String orderBy  = "";
    
    private String direction    = "";
    /** Mobile Web List Paging 처리*/
    private String pageNum	= "";
    
    private String auditKey = "";
    
    private String errorLogId = "";
    /** Is Excel Export  */
    private int stAct = BaseAction.DEFAULT_ACTION;
    /** Excel Head List  */
    private String headList = "";
    /** Excel YN  */
    private String excelYn = "";
    /** Linked 에서 호출 여부  */
    private String isLinked = "";
    
    /** 초기 페이지 File Name */
    private String redirectPage = "";
    
    private String redirectParam = "";

	public BaseForm()
    {
        super();
    }


    /**
     * DTO Factory
     * 
     * @author pksup
     * @version $Id: BaseForm.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since 1.0
     */
    protected class DtoFactory
            implements Factory
    {
        private Class clazz = null;

        public DtoFactory(Class clazz)
        {
            this.clazz = clazz;
        }

        public Object create()
        {
            Object obj = null;
            try
            {
                obj = clazz.newInstance();
            }
            catch (Exception e)
            {}
            return obj;
        }
    }

    /**
     * @author pksup
     * @version $Id: BaseForm.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since 1.0
     * @param clazz
     * @return
     */
    protected Factory getDtoFactory(Class clazz)
    {
        return new DtoFactory(clazz);
    }

    /**
     * @param mapping
     * @param request
     * @see org.apache.struts.action.ActionForm#reset(
     *      org.apache.struts.action.ActionMapping,
     *      javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        super.reset(mapping, request);
        this.strutsAction = BaseAction.DEFAULT_ACTION;
    }

    /**
     * @see org.apache.struts.action.ActionForm#validate(
     *      org.apache.struts.action.ActionMapping,
     *      javax.servlet.http.HttpServletRequest)
     */
    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request)
    {
        return super.validate(mapping, request);
    }

    /**
     * @deprecated getStrutsAction을 사용해 주세요.
     * @return
     */
    public int getAction()
    {
        return this.strutsAction;
    }

    /**
     * @deprecated setStrutsAction을 사용해 주세요.
     * @param action
     */
    
    public void setAction(int action)
    {
        this.strutsAction = action;
    }
    
    
    public String getRedirectPage()
    {
        return redirectPage;
    }

    public void setRedirectPage(String redirectPage)
    {
        this.redirectPage = redirectPage;
    }

    public String getRedirectParam()
    {
        return redirectParam;
    }

    public void setRedirectParam(String redirectParam)
    {
        this.redirectParam = redirectParam;
    }

    public String getIsLinked()
    {
        return isLinked;
    }

    public void setIsLinked(String isLinked)
    {
        this.isLinked = isLinked;
    }

    public int getStAct()
    {
        return stAct;
    }

    public void setStAct(int stAct)
    {
        this.stAct = stAct;
    }

    public String getAuditKey()
    {
        return auditKey;
    }

    public void setAuditKey(String auditKey)
    {
        this.auditKey = auditKey;
    }

    public String getAllArray() {
		return allArray;
	}

	public void setAllArray(String allArray) {
		this.allArray = allArray;
	}

	public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getOrderBy()
    {
        return orderBy;
    }

    public void setOrderBy(String orderBy)
    {
        this.orderBy = orderBy;
    }

    public String getDirection()
    {
        return direction;
    }

    public void setDirection(String direction)
    {
        this.direction = direction;
    }

    public String getIsLoadMaxCount()
    {
        return isLoadMaxCount;
    }

    public void setIsLoadMaxCount(String isLoadMaxCount)
    {
        this.isLoadMaxCount = isLoadMaxCount;
    }

    public String getIsTotalCount()
    {
        return isTotalCount;
    }

    public void setIsTotalCount(String isTotalCount)
    {
        this.isTotalCount = isTotalCount;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public int getStrutsAction()
    {
        return strutsAction;
    }

    public void setStrutsAction(int saction)
    {
        this.strutsAction = saction;
    }

    public String getIsDecoratorName()
    {
        return isDecoratorName;
    }

    public void setIsDecoratorName(String isDecoratorName)
    {
        this.isDecoratorName = isDecoratorName;
    }

    public static String getReportSqlPath()
    {
        return reportSqlPath;
    }

    public static void setReportSqlPath(String reportSqlPath)
    {
        BaseForm.reportSqlPath = reportSqlPath;
    }

    public static String getReportResultTxtPath()
    {
        return reportResultTxtPath;
    }

    public static void setReportResultTxtPath(String reportResultTxtPath)
    {
        BaseForm.reportResultTxtPath = reportResultTxtPath;
    }

    public String getCurDiaId()
    {
        return curDiaId;
    }

    public void setCurDiaId(String curDiaId)
    {
        this.curDiaId = curDiaId;
    }

    public String[] getDeleteRows()
    {
        return deleteRows;
    }

    public void setDeleteRows(String[] deleteRows)
    {
        this.deleteRows = deleteRows;
    }

    public String[] getSelectRows() {
		return selectRows;
	}

	public void setSelectRows(String[] selectRows) {
		this.selectRows = selectRows;
	}
    public String[] getSelectRows1() {
		return selectRows1;
	}

	public void setSelectRows1(String[] selectRows1) {
		this.selectRows1 = selectRows1;
	}

	public String[] getCheckRows()
    {
        return checkRows;
    }

    public void setCheckRows(String[] checkRows)
    {
        this.checkRows = checkRows;
    }
    
    public String getListId()
    {
        return listId;
    }

    public void setListId(String listId)
    {
        this.listId = listId;
    }

    public String getCurrentPageId()
    {
        return currentPageId;
    }

    public void setCurrentPageId(String currentPageId)
    {
        this.currentPageId = currentPageId;
    }

    public String[] getDeleteRowsExt()
    {
        return deleteRowsExt;
    }

    public void setDeleteRowsExt(String[] deleteRowsExt)
    {
        this.deleteRowsExt = deleteRowsExt;
    }

    public String[] getDeleteRowsExt1()
    {
        return deleteRowsExt1;
    }

    public void setDeleteRowsExt1(String[] deleteRowsExt1)
    {
        this.deleteRowsExt1 = deleteRowsExt1;
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public String getFirstRow()
    {
        return firstRow;
    }

    public void setFirstRow(String firstRow)
    {
        this.firstRow = firstRow;
    }

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

    public String getErrorLogId()
    {
        return errorLogId;
    }

    public void setErrorLogId(String errorLogId)
    {
        this.errorLogId = errorLogId;
    }

    public String getHeadList()
    {
        return headList;
    }

    public void setHeadList(String headList)
    {
        this.headList = headList;
    }

    public String getExcelYn()
    {
        return excelYn;
    }

    public void setExcelYn(String excelYn)
    {
        this.excelYn = excelYn;
    }

    
}
