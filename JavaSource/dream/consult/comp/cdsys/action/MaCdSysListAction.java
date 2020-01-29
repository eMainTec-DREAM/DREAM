package dream.consult.comp.cdsys.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.struts.ConsultAuthAction;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;
import dream.consult.comp.cdsys.form.MaCdSysListForm;
import dream.consult.comp.cdsys.service.MaCdSysListService;

/**
 * 시스템코드 - 목록 action
 * @author  kim21017
 * @version $Id: MaCdSysListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maCdSysList" name="maCdSysListForm"
 *                input="/dream/consult/comp/cdsys/maCdSysList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maCdSysList" path="/dream/consult/comp/cdsys/maCdSysList.jsp"
 *                        redirect="false"
 */
public class MaCdSysListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int LISTTYPE_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LISTTYPE_LIST_DELETE 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaCdSysListForm maCdSysListForm = (MaCdSysListForm) form;
        
        switch (maCdSysListForm.getStrutsAction())
        {
            case MaCdSysListAction.LISTTYPE_LIST_FIND:
                findListTypeList(request, maCdSysListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaCdSysListAction.BASE_SET_HEADER:
                setHeader(request, response, maCdSysListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaCdSysListAction.LISTTYPE_LIST_DELETE:
                deleteListType(request, maCdSysListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaCdSysListAction.BASE_GRID_EXPORT:
            	findListTypeList(request, maCdSysListForm, response);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maCdSysList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaCdSysListForm maCdSysListForm) throws IOException
    {
        super.setHeader(request, response, maCdSysListForm.getListId(), maCdSysListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaCdSysListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maCdSysListForm
     * @throws IOException 
     * @throws Exception
     */
    private void findListTypeList(HttpServletRequest request, MaCdSysListForm maCdSysListForm, HttpServletResponse response) throws IOException 
    {
    	MaCdSysListService maCdSysListService = (MaCdSysListService) getBean("maCdSysListService");        

    	MaCdSysCommonDTO maCdSysCommonDTO = maCdSysListForm.getMaCdSysCommonDTO();
    	maCdSysCommonDTO.setUserLang(getUser(request).getLangId());

        //리스트를 조회한다.
        List resultList = maCdSysListService.findListTypeList(maCdSysCommonDTO);
 
    	super.makeJsonResult(resultList, request, response, maCdSysListForm.getListId());
    }
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaCdSysListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysListForm
     * @param request
     */
    private void deleteListType(HttpServletRequest request, MaCdSysListForm maCdSysListForm) throws Exception
    {
    	MaCdSysListService maCdSysListService = (MaCdSysListService) getBean("maCdSysListService");        

    	String[] deleteRows = maCdSysListForm.getDeleteRows();    // sheet 내역
        
    	maCdSysListService.deleteListType(deleteRows);
        
        setAjaxStatus(request);
    }
}
