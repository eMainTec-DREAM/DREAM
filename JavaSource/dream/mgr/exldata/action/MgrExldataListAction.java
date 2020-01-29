package dream.mgr.exldata.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fins.org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.mgr.exldata.dto.MgrExldataCommonDTO;
import dream.mgr.exldata.form.MgrExldataListForm;
import dream.mgr.exldata.service.MgrExldataListService;

/**
 * Excel Data Upload - List Action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/mgrExldataList" name="mgrExldataListForm"
 *                input="/dream/mgr/exldata/mgrExldataList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrExldataList" path="/dream/mgr/exldata/mgrExldataList.jsp"
 *                        redirect="false"
 */

public class MgrExldataListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 				= 8001;
    /** 삭제 */
    public static final int LIST_DELETE 			= 7002;
    /** set header */
    public static final int SET_DUMMY_HEADER		= 1003;
    /** get excel template */
    public static final int TEMPLATE_EXPORT			= 1004;
    /** upload data */
    public static final int UPLOAD_DATA				= 1005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrExldataListForm mgrExldataListForm = (MgrExldataListForm) form;
        
        super.updateAudit(mgrExldataListForm.getMgrExldataCommonDTO().getAuditKey()==""?mgrExldataListForm.getMgrExldataCommonDTO().getAuditKey():mgrExldataListForm.getMgrExldataCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (mgrExldataListForm.getStrutsAction())
        {
            case MgrExldataListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrExldataListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrExldataListAction.LIST_FIND:
                findList(request, response, mgrExldataListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MgrExldataListAction.LIST_DELETE:
            	deleteList(request, mgrExldataListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MgrExldataListAction.BASE_GRID_EXPORT:
            	findList(request, response, mgrExldataListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case MgrExldataListAction.TEMPLATE_EXPORT:
                findExcelTemplateData(request, response, mgrExldataListForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case MgrExldataListAction.SET_DUMMY_HEADER:
                setDummyHeader(request, response, mgrExldataListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrExldataListAction.UPLOAD_DATA:
            	uploadData(request, response, mgrExldataListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("mgrExldataList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrExldataListForm mgrExldataListForm) throws IOException
    {
        super.setHeader(request, response, mgrExldataListForm.getListId(), mgrExldataListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param mgrExldataListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrExldataListForm mgrExldataListForm, boolean excelExport) throws Exception
    {
    	MgrExldataListService mgrExldataListService = (MgrExldataListService) getBean("mgrExldataListService");
    	MgrExldataCommonDTO mgrExldataCommonDTO = mgrExldataListForm.getMgrExldataCommonDTO();

    	//Paging
    	mgrExldataCommonDTO.setIsLoadMaxCount("Y".equals(mgrExldataListForm.getIsLoadMaxCount())?true:false);
    	mgrExldataCommonDTO.setFirstRow(mgrExldataListForm.getFirstRow());
    	mgrExldataCommonDTO.setOrderBy(mgrExldataListForm.getOrderBy());
    	mgrExldataCommonDTO.setDirection(mgrExldataListForm.getDirection());
    	
        List resultList = mgrExldataListService.findExldataList(mgrExldataCommonDTO, getUser(request));
        //Paging
        String totalCount = "";
        if(Integer.parseInt(mgrExldataListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrExldataListService.findTotalCount(mgrExldataCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,mgrExldataListForm.getListId(),mgrExldataListForm.getCurrentPageId(), mgrExldataListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param mgrExldataListForm
     */
    private void deleteList(HttpServletRequest request, MgrExldataListForm mgrExldataListForm) throws Exception
    {
    	MgrExldataListService mgrExldataListService = (MgrExldataListService) getBean("mgrExldataListService");
    	String table = mgrExldataListForm.getMgrExldataCommonDTO().getFilterTableName();
        String[] deleteRows = mgrExldataListForm.getDeleteRows();
        
        mgrExldataListService.deleteExldataList(table, deleteRows, getUser(request));
        setAjaxStatus(request);
    }
    
    public int setDummyHeader(HttpServletRequest request, HttpServletResponse response, MgrExldataListForm mgrExldataListForm) throws Exception
    {
        MgrExldataListService mgrExldataListService = (MgrExldataListService) getBean("mgrExldataListService");
        MgrExldataCommonDTO mgrExldataCommonDTO = mgrExldataListForm.getMgrExldataCommonDTO();
        List resultList = null;
        Map resultMap = null;
        
        resultList = mgrExldataListService.getDummyHeader(mgrExldataCommonDTO, getUser(request));
        
        String height = "";
        if(resultList != null && resultList.size() > 1)
        {
            height = String.valueOf(((Map)(resultList.get(resultList.size()-1))).get("HEIGHT"));
        }

        int heightInt = 0;
        if(height != "" && height != "null") heightInt = Math.round(Integer.parseInt(height)) * 30 + 30; //한줄에 30px. + footer 20px
        
        resultMap = CommonUtil.makeHeaderJson(resultList);

       // Gson gson = new Gson();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create(); //유니코드 처리
        
        JSONObject jsonObj = new JSONObject();
        
        String jsonString = gson.toJson(resultMap).toString(); 
        
        jsonObj.put("header", jsonString);
        jsonObj.put("height", heightInt+"");
        
        response.getWriter().print(jsonObj.toString());
        
        return resultList.size();
    }
    
    /**
     * FIND TEMPLATE
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param mgrExldataListForm
     */
    private void findExcelTemplateData(HttpServletRequest request, HttpServletResponse response, MgrExldataListForm mgrExldataListForm) throws Exception
    {
        MgrExldataListService mgrExldataListService = (MgrExldataListService) getBean("mgrExldataListService");
        MgrExldataCommonDTO mgrExldataCommonDTO = mgrExldataListForm.getMgrExldataCommonDTO();
        
        List sheetList = mgrExldataListService.findExcelTemplateData(mgrExldataCommonDTO, getUser(request));
        
        CommonUtil.makeMultiGridExport(sheetList, request, response, mgrExldataListForm);
    }
    
    private void uploadData(HttpServletRequest request, HttpServletResponse response, MgrExldataListForm mgrExldataListForm) throws Exception
    {
    	MgrExldataListService mgrExldataListService = (MgrExldataListService) getBean("mgrExldataListService");
    	MgrExldataCommonDTO mgrExldataCommonDTO = mgrExldataListForm.getMgrExldataCommonDTO();
    	
    	mgrExldataListService.uploadData(mgrExldataCommonDTO, getUser(request));
    	setAjaxStatus(request);
    }
    
}
