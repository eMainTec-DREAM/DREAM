package dream.org.wrkgrp.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.org.wrkgrp.dto.LovWkCtrListDTO;
import dream.org.wrkgrp.form.LovWkCtrListForm;
import dream.org.wrkgrp.service.LovWkCtrListService;

/**
 * 작업그룹 팝업
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovWkCtrList" name="lovWkCtrListForm"
 *                input="/dream/org/wrkgrp/lovWkCtrList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/wkCtrValLov" name="lovWkCtrListForm"
 *                input="/dream/org/wrkgrp/wkCtrValLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovWkCtrPopup" path="/dream/org/wrkgrp/lovWkCtrPopup.jsp"
 *                        redirect="false"
 */
public class LovWkCtrListAction extends SuperAuthAction
{
    public static final int LOV_WKCTR_DEFAULT 	= 1001;
    public static final int LOV_WKCTR_FIND      = 1002;
    public static final int LOV_WKCTR_AC_FIND   = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovWkCtrListForm lovWkCtrListForm = (LovWkCtrListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovWkCtrListForm.getStrutsAction())
        {
            case LovWkCtrListAction.LOV_WKCTR_DEFAULT :
            	returnActionForward = mapping.getInputForward();
                break;
            case LovWkCtrListAction.LOV_WKCTR_FIND :
                findWkCtrList(request, lovWkCtrListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovWkCtrListAction.LOV_WKCTR_AC_FIND:
            	findWkCtrAcList(request, response, lovWkCtrListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;   
            case LovWkCtrListAction.BASE_SET_HEADER:
                setHeader(request, response, lovWkCtrListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovWkCtrListForm lovWkCtrListForm) throws IOException
    {
        super.setHeader(request, response, lovWkCtrListForm.getListId(),lovWkCtrListForm.getCurrentPageId()); 
    }

    /**
     * TAWKCTR 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovWkCtrListForm
     */
    private void findWkCtrList(HttpServletRequest request,
            LovWkCtrListForm lovWkCtrListForm,HttpServletResponse response) throws IOException
    {
        LovWkCtrListService lovWkCtrListService = (LovWkCtrListService)getBean("lovWkCtrListService");
        
        LovWkCtrListDTO lovWkCtrListDTO = lovWkCtrListForm.getLovWkCtrListDTO();
        List resultList = lovWkCtrListService.findWkCtrList(lovWkCtrListDTO, getUser(request));
        
        super.makeTreeJsonResult(resultList, request, response, lovWkCtrListForm.getListId());
    	
    }
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovWkCtrListForm
     * @param excelExport
     */
    private void findWkCtrAcList(HttpServletRequest request, HttpServletResponse response, LovWkCtrListForm lovWkCtrListForm, boolean excelExport) throws Exception
    {
    	LovWkCtrListService lovWkCtrListService = (LovWkCtrListService)getBean("lovWkCtrListService");
    	LovWkCtrListDTO lovWkCtrListDTO = lovWkCtrListForm.getLovWkCtrListDTO();
        
    	//Paging
//    	lovWkCtrListDTO.setIsLoadMaxCount("Y".equals(lovWkCtrListForm.getIsLoadMaxCount())?true:false);
//    	lovWkCtrListDTO.setFirstRow(lovWkCtrListForm.getFirstRow());
//    	lovWkCtrListDTO.setOrderBy(lovWkCtrListForm.getOrderBy());
//    	lovWkCtrListDTO.setDirection(lovWkCtrListForm.getDirection());
    	
        List resultList = lovWkCtrListService.findWkCtrAcList(lovWkCtrListForm, getUser(request));
        
        //Paging
//        String totalCount = "";
//        if(Integer.parseInt(lovWkCtrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovWkCtrListService.findTotalCount(lovWkCtrListForm,getUser(request));

//        if(excelExport) super.makeJsonResult(resultList, request, response,totalCount);
//        else super.makeTreeJsonResult(resultList, request, response, lovWkCtrListForm.getListId());
        super.makeTreeJsonResult(resultList, request, response, lovWkCtrListForm.getListId());
    }

}