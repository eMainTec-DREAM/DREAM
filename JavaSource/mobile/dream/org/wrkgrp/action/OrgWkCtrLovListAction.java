package mobile.dream.org.wrkgrp.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import mobile.dream.org.wrkgrp.dto.OrgWkCtrLovListDTO;
import mobile.dream.org.wrkgrp.form.OrgWkCtrLovListForm;
import mobile.dream.org.wrkgrp.service.OrgWkCtrLovListService;


/**
 * 작업그룹 팝업
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/orgWkCtrLovList" name="orgWkCtrLovListForm"
 *                input="/mobile/dream/org/wrkgrp/orgWrkgrpLovList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="orgWkCtrLovPopup" path="/mobile/dream/org/wrkgrp/orgWrkgrpLovPopup.jsp"
 *                        redirect="false"
 */
public class OrgWkCtrLovListAction extends SuperAuthAction
{
    public static final int LOV_WKCTR_DEFAULT 	= 1001;
    public static final int LOV_WKCTR_FIND      = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        OrgWkCtrLovListForm orgWkCtrLovListForm = (OrgWkCtrLovListForm)form;
        ActionForward returnActionForward = null;
        
        switch (orgWkCtrLovListForm.getStrutsAction())
        {
            case OrgWkCtrLovListAction.LOV_WKCTR_DEFAULT :
                returnActionForward = mapping.findForward("orgWkCtrLovPopup");
                break;
            case OrgWkCtrLovListAction.LOV_WKCTR_FIND :
                findWkCtrList(request, orgWkCtrLovListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case OrgWkCtrLovListAction.BASE_SET_HEADER:
                setHeader(request, response, orgWkCtrLovListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, OrgWkCtrLovListForm orgWkCtrLovListForm) throws IOException
    {
        super.setHeader(request, response, orgWkCtrLovListForm.getListId(),orgWkCtrLovListForm.getCurrentPageId()); 
    }

    /**
     * TAWKCTR 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param orgWkCtrLovListForm
     */
    private void findWkCtrList(HttpServletRequest request,
            OrgWkCtrLovListForm orgWkCtrLovListForm,HttpServletResponse response) throws IOException
    {
        OrgWkCtrLovListService orgWkCtrLovListService = (OrgWkCtrLovListService)getBean("orgWkCtrLovListService");
        
        OrgWkCtrLovListDTO orgWkCtrLovListDTO = orgWkCtrLovListForm.getOrgWkCtrLovListDTO();
        List resultList = orgWkCtrLovListService.findWkCtrList(orgWkCtrLovListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, orgWkCtrLovListForm.getListId());
    	
    }

}