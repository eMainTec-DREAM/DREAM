package dream.mgr.manst.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.manst.dto.MaNstGrpCommonDTO;
import dream.mgr.manst.form.MaNstGrpListForm;
import dream.mgr.manst.service.MaNstGrpListService;

/**
 * 무정지대표라인 - 목록 
 * @author  kim21017
 * @version $Id: MaNstGrpListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maNstGrpList" name="maNstGrpListForm"
 *                input="/dream/mgr/manst/maNstGrpList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maNstGrpList" path="/dream/mgr/manst/maNstGrpList.jsp"
 *                        redirect="false"
 */
public class MaNstGrpListAction extends AuthAction
{
    /** 조회 */
    public static final int NST_GRP_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int NST_GRP_LIST_DELETE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaNstGrpListForm maNstGrpListForm = (MaNstGrpListForm) form;
        
        switch (maNstGrpListForm.getStrutsAction())
        {
            case MaNstGrpListAction.NST_GRP_LIST_FIND:
                findNstGrpList(request, maNstGrpListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaNstGrpListAction.BASE_SET_HEADER:
                setHeader(request, response, maNstGrpListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaNstGrpListAction.NST_GRP_LIST_DELETE:
            	deleteNstGrp(request, maNstGrpListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaNstGrpListAction.BASE_GRID_EXPORT:
            	findNstGrpList(request, maNstGrpListForm, response);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maNstGrpList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaNstGrpListForm maNstGrpListForm) throws IOException
    {
        super.setHeader(request, response, maNstGrpListForm.getListId(),maNstGrpListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaNstGrpListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maNstGrpListForm
     * @param response
     * @throws Exception
     */
    private void findNstGrpList(HttpServletRequest request, MaNstGrpListForm maNstGrpListForm, HttpServletResponse response) throws IOException
    {
    	MaNstGrpListService maNstGrpListService = (MaNstGrpListService) getBean("maNstGrpListService",request);        

    	MaNstGrpCommonDTO maNstGrpCommonDTO = maNstGrpListForm.getMaNstGrpCommonDTO();
    	maNstGrpCommonDTO.setCompNo(getUser(request).getCompNo());
        //리스트를 조회한다.
        List resultList = maNstGrpListService.findNstGrpList(maNstGrpCommonDTO);

        super.makeJsonResult(resultList, request, response, maNstGrpListForm.getListId());
	}
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaNstGrpListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maNstGrpListForm
     */
    private void deleteNstGrp(HttpServletRequest request, MaNstGrpListForm maNstGrpListForm) throws Exception
    {
    	MaNstGrpListService maNstGrpListService = (MaNstGrpListService) getBean("maNstGrpListService");        

    	String[] deleteRows = maNstGrpListForm.getDeleteRows();
    	MaNstGrpCommonDTO maNstGrpCommonDTO = maNstGrpListForm.getMaNstGrpCommonDTO();
    	maNstGrpCommonDTO.setCompNo(getUser(request).getCompNo());
    	maNstGrpListService.deleteNstGrp(deleteRows,maNstGrpCommonDTO);
        
        setAjaxStatus(request);
    }
}
