package dream.part.rep.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.rep.dto.MaPtRepAppListDTO;
import dream.part.rep.dto.MaPtRepCommonDTO;
import dream.part.rep.form.MaPtRepAppListForm;
import dream.part.rep.service.MaPtRepAppListService;

/**
 * 수리기안 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPtRepAppList" name="maPtRepAppListForm"
 *                input="/dream/part/rep/maPtRepAppList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtRepAppList" path="/dream/part/rep/maPtRepAppList.jsp"
 *                        redirect="false"
 */
public class MaPtRepAppListAction extends AuthAction
{
    /** 조회 */
    public static final int PTREPAPP_LIST_FIND     = 1001;
    /** 삭제 */
    public static final int PTREPAPP_LIST_DELETE   = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtRepAppListForm maPtRepAppListForm = (MaPtRepAppListForm) form;
        
        switch (maPtRepAppListForm.getStrutsAction())
        {
            case MaPtRepAppListAction.BASE_SET_HEADER:
                setHeader(request, response, maPtRepAppListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtRepAppListAction.PTREPAPP_LIST_FIND:
            	findPtRepAppList(request, maPtRepAppListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtRepAppListAction.PTREPAPP_LIST_DELETE:
            	deletePtRep(request, maPtRepAppListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtRepAppListAction.BASE_GRID_EXPORT:
            	findPtRepAppList(request, maPtRepAppListForm,response);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPtRepAppList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPtRepAppListForm maPtRepAppListForm) throws IOException
    {
        super.setHeader(request, response, maPtRepAppListForm.getListId(), maPtRepAppListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtRepAppListForm
     * @throws Exception
     */
    private void findPtRepAppList(HttpServletRequest request, MaPtRepAppListForm maPtRepAppListForm, HttpServletResponse response) throws IOException
    {
    	MaPtRepAppListService maPtRepAppListService = (MaPtRepAppListService) getBean("maPtRepAppListService");        

    	MaPtRepCommonDTO maPtRepCommonDTO = maPtRepAppListForm.getMaPtRepCommonDTO();
    	MaPtRepAppListDTO maPtRepAppListDTO = maPtRepAppListForm.getMaPtRepAppListDTO();
    	
        //리스트를 조회한다.
        List resultList = maPtRepAppListService.findList(maPtRepCommonDTO,maPtRepAppListDTO, getUser(request));
        super.makeJsonResult(resultList, request, response, maPtRepAppListForm.getListId());
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepAppListForm
     * @param request
     */
    private void deletePtRep(HttpServletRequest request, MaPtRepAppListForm maPtRepAppListForm) throws Exception
    {
    	MaPtRepAppListService maPtRepAppListService = (MaPtRepAppListService) getBean("maPtRepAppListService");
    	String[] deleteRows = maPtRepAppListForm.getDeleteRows();    // sheet 내역
    	
        maPtRepAppListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
}
