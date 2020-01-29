package dream.work.rpt.mapmrep.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.rpt.mapmrep.dto.MaPmRepListDTO;
import dream.work.rpt.mapmrep.form.MaPmRepListForm;
import dream.work.rpt.mapmrep.service.MaPmRepListService;

/**
 * 예방수리내역 Action
 * @author  kim21017
 * @version $Id: MaPmRepListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPmRepList" name="maPmRepListForm"
 *                input="/dream/work/rpt/pmrep/maPmRepList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPmRepList" path="/dream/work/rpt/pmrep/maPmRepList.jsp"
 *                        redirect="false"
 */
public class MaPmRepListAction extends AuthAction
{
    /** 조회 */
    public static final int PM_LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPmRepListForm maPmRepListForm = (MaPmRepListForm) form;
        
        switch (maPmRepListForm.getStrutsAction())
        {
            case MaPmRepListAction.PM_LIST_FIND:
                findPmRepList(request, maPmRepListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmRepListAction.BASE_SET_HEADER:
                setHeader(request, response, maPmRepListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmRepListAction.BASE_GRID_EXPORT:
            	findPmRepList(request, maPmRepListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPmRepList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPmRepListForm maPmRepListForm) throws IOException
    {
        super.setHeader(request, response, maPmRepListForm.getListId(),maPmRepListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaPmRepListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPmRepListForm
     * @param response
     * @throws Exception
     */
    private void findPmRepList(HttpServletRequest request, MaPmRepListForm maPmRepListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaPmRepListService maPmRepListService = (MaPmRepListService) getBean("maPmRepListService");        

    	MaPmRepListDTO maPmRepListDTO = maPmRepListForm.getMaPmRepListDTO();
    	maPmRepListDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
    	maPmRepListDTO.setIsLoadMaxCount("Y".equals(maPmRepListForm.getIsLoadMaxCount())?true:false);
    	maPmRepListDTO.setFirstRow(maPmRepListForm.getFirstRow());
    	maPmRepListDTO.setOrderBy(maPmRepListForm.getOrderBy());
    	maPmRepListDTO.setDirection(maPmRepListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maPmRepListService.findPmRepList(maPmRepListDTO,getUser(request));

        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(maPmRepListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPmRepListService.findTotalCount(maPmRepListDTO,getUser(request));
                
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maPmRepListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}
}
