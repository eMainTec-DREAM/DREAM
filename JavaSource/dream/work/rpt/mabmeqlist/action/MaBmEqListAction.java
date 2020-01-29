package dream.work.rpt.mabmeqlist.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.mabmeqlist.dto.MaBmEqListDTO;
import dream.work.rpt.mabmeqlist.form.MaBmEqListForm;
import dream.work.rpt.mabmeqlist.service.MaBmEqListService;

/**
 * 설비별고장분석 Action
 * @author  kim21017
 * @version $Id: MaBmEqListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maBmEqList" name="maBmEqListForm"
 *                input="/dream/work/rpt/bmeqlist/maBmEqList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maBmEqList" path="/dream/work/rpt/bmeqlist/maBmEqList.jsp"
 *                        redirect="false"
 */
public class MaBmEqListAction extends AuthAction
{
    /** 조회 */
    public static final int BM_LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaBmEqListForm maBmEqListForm = (MaBmEqListForm) form;
        
        switch (maBmEqListForm.getStrutsAction())
        {
            case MaBmEqListAction.BM_LIST_FIND:
                findBmEqList(request, maBmEqListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaBmEqListAction.BASE_SET_HEADER:
                setHeader(request, response, maBmEqListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaBmEqListAction.BASE_GRID_EXPORT:
            	findBmEqList(request, maBmEqListForm, response);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maBmEqList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaBmEqListForm maBmEqListForm) throws IOException
    {
        super.setHeader(request, response, maBmEqListForm.getListId(),maBmEqListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaBmEqListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maBmEqListForm
     * @param response
     * @throws Exception
     */
    private void findBmEqList(HttpServletRequest request, MaBmEqListForm maBmEqListForm, HttpServletResponse response) throws IOException
    {
    	MaBmEqListService maBmEqListService = (MaBmEqListService) getBean("maBmEqListService");        

    	MaBmEqListDTO maBmEqListDTO = maBmEqListForm.getMaBmEqListDTO();
    	maBmEqListDTO.setCompNo(getUser(request).getCompNo());
    	maBmEqListDTO.setUserLang(getUser(request).getLangId());
        //리스트를 조회한다.
        List resultList = maBmEqListService.findBmEqList(maBmEqListDTO, getUser(request));

        super.makeJsonResult(resultList, request, response, maBmEqListForm.getListId());
	}
}
