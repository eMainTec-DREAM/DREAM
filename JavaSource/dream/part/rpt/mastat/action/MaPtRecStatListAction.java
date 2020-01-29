package dream.part.rpt.mastat.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.part.rpt.mastat.dto.MaPtRecStatCommonDTO;
import dream.part.rpt.mastat.form.MaPtRecStatListForm;
import dream.part.rpt.mastat.service.MaPtRecStatListService;

/**
 * 자재입고내역 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPtRecStatList" name="maPtRecStatListForm"
 *                input="/dream/part/rpt/mastat/maPtRecStatList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtRecStatList" path="/dream/part/rpt/mastat/maPtRecStatList.jsp"
 *                        redirect="false"
 */
public class MaPtRecStatListAction extends AuthAction
{
    /** 조회 */
    public static final int PTREC_STAT_LIST_FIND     = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtRecStatListForm maPtRecStatListForm = (MaPtRecStatListForm) form;
        
        switch (maPtRecStatListForm.getStrutsAction())
        {
            case MaPtRecStatListAction.PTREC_STAT_LIST_FIND:
            	findList(request, maPtRecStatListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtRecStatListAction.BASE_SET_HEADER:
                setHeader(request, response, maPtRecStatListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtRecStatListAction.BASE_GRID_EXPORT:
            	findList(request, maPtRecStatListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPtRecStatList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPtRecStatListForm maPtRecStatListForm) throws IOException
    {
        super.setHeader(request, response, maPtRecStatListForm.getListId(), maPtRecStatListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtRecStatListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MaPtRecStatListForm maPtRecStatListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaPtRecStatListService maPtRecStatListService = (MaPtRecStatListService) getBean("maPtRecStatListService");        

    	MaPtRecStatCommonDTO maPtRecStatCommonDTO = maPtRecStatListForm.getMaPtRecStatCommonDTO();
    	maPtRecStatCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
    	maPtRecStatCommonDTO.setIsLoadMaxCount("Y".equals(maPtRecStatListForm.getIsLoadMaxCount())?true:false);
    	maPtRecStatCommonDTO.setFirstRow(maPtRecStatListForm.getFirstRow());
    	maPtRecStatCommonDTO.setOrderBy(maPtRecStatListForm.getOrderBy());
    	maPtRecStatCommonDTO.setDirection(maPtRecStatListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maPtRecStatListService.findList(maPtRecStatCommonDTO);
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(maPtRecStatListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPtRecStatListService.findTotalCount(maPtRecStatCommonDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maPtRecStatListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
}
