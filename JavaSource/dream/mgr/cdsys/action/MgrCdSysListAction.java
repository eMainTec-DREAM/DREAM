package dream.mgr.cdsys.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;
import dream.mgr.cdsys.form.MgrCdSysListForm;
import dream.mgr.cdsys.service.MgrCdSysListService;

/**
 * 시스템코드 - 목록 action
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/mgrCdSysList" name="mgrCdSysListForm"
 *                input="/dream/mgr/cdsys/mgrCdSysList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrCdSysList" path="/dream/mgr/cdsys/mgrCdSysList.jsp"
 *                        redirect="false"
 */
public class MgrCdSysListAction extends AuthAction
{
    /** 조회 */
    public static final int LISTTYPE_LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrCdSysListForm mgrCdSysListForm = (MgrCdSysListForm) form;
        
        switch (mgrCdSysListForm.getStrutsAction())
        {
            case MgrCdSysListAction.LISTTYPE_LIST_FIND:
                findListTypeList(request, mgrCdSysListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrCdSysListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrCdSysListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrCdSysListAction.BASE_GRID_EXPORT:
            	findListTypeList(request, mgrCdSysListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrCdSysListForm mgrCdSysListForm) throws IOException
    {
        super.setHeader(request, response, mgrCdSysListForm.getListId(), mgrCdSysListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param mgrCdSysListForm
     * @throws IOException 
     * @throws Exception
     */
    private void findListTypeList(HttpServletRequest request, MgrCdSysListForm mgrCdSysListForm, HttpServletResponse response, boolean excelExport) throws IOException 
    {
    	MgrCdSysListService mgrCdSysListService = (MgrCdSysListService) getBean("mgrCdSysListService");        

    	MgrCdSysCommonDTO mgrCdSysCommonDTO = mgrCdSysListForm.getMgrCdSysCommonDTO();
    	mgrCdSysCommonDTO.setUserLang(getUser(request).getLangId());

    	//Paging
    	mgrCdSysCommonDTO.setIsLoadMaxCount("Y".equals(mgrCdSysListForm.getIsLoadMaxCount())?true:false);
    	mgrCdSysCommonDTO.setFirstRow(mgrCdSysListForm.getFirstRow());
    	mgrCdSysCommonDTO.setOrderBy(mgrCdSysListForm.getOrderBy());
    	mgrCdSysCommonDTO.setDirection(mgrCdSysListForm.getDirection());
        
    	//리스트를 조회한다.
        List resultList = mgrCdSysListService.findListTypeList(mgrCdSysCommonDTO);
        //Paging
        String totalCount = "";
        if(Integer.parseInt(mgrCdSysListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrCdSysListService.findTotalCount(mgrCdSysCommonDTO);

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, mgrCdSysListForm);//.makeGridExport(resultList, request, response,mgrCdSysListForm.getListId(),mgrCdSysListForm.getCurrentPageId(), mgrCdSysListForm.getFileName());
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
        
    }
}
