package dream.work.rpt.mapmpoint.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.rpt.mapmpoint.dto.MaPmPointListDTO;
import dream.work.rpt.mapmpoint.form.MaPmPointListForm;
import dream.work.rpt.mapmpoint.service.MaPmPointListService;

/**
 * 예방점검내역 Action
 * @author  kim21017
 * @version $Id: MaPmPointListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPmPointList" name="maPmPointListForm"
 *                input="/dream/work/rpt/pmpoint/maPmPointList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPmPointList" path="/dream/work/rpt/pmpoint/maPmPointList.jsp"
 *                        redirect="false"
 */
public class MaPmPointListAction extends AuthAction
{
    /** 조회 */
    public static final int PM_LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPmPointListForm maPmPointListForm = (MaPmPointListForm) form;
        
        switch (maPmPointListForm.getStrutsAction())
        {
            case MaPmPointListAction.PM_LIST_FIND:
                findPmPointList(request, maPmPointListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmPointListAction.BASE_SET_HEADER:
                setHeader(request, response, maPmPointListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmPointListAction.BASE_GRID_EXPORT:
            	findPmPointList(request, maPmPointListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPmPointList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPmPointListForm maPmPointListForm) throws IOException
    {
        super.setHeader(request, response, maPmPointListForm.getListId(),maPmPointListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaPmPointListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPmPointListForm
     * @param response
     * @throws Exception
     */
    private void findPmPointList(HttpServletRequest request, MaPmPointListForm maPmPointListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaPmPointListService maPmPointListService = (MaPmPointListService) getBean("maPmPointListService");        

    	MaPmPointListDTO maPmPointListDTO = maPmPointListForm.getMaPmPointListDTO();
    	maPmPointListDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
    	maPmPointListDTO.setIsLoadMaxCount("Y".equals(maPmPointListForm.getIsLoadMaxCount())?true:false);
    	maPmPointListDTO.setFirstRow(maPmPointListForm.getFirstRow());
    	maPmPointListDTO.setOrderBy(maPmPointListForm.getOrderBy());
    	maPmPointListDTO.setDirection(maPmPointListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maPmPointListService.findPmPointList(maPmPointListDTO,getUser(request));

        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(maPmPointListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPmPointListService.findTotalCount(maPmPointListDTO,getUser(request));
                
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maPmPointListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}
}
