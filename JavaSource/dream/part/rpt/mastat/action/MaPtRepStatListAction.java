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
import dream.part.rpt.mastat.dto.MaPtRepStatCommonDTO;
import dream.part.rpt.mastat.form.MaPtRepStatListForm;
import dream.part.rpt.mastat.service.MaPtRepStatListService;

/**
 * 자재수리내역 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPtRepStatList" name="maPtRepStatListForm"
 *                input="/dream/part/rpt/mastat/maPtRepStatList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtRepStatList" path="/dream/part/rpt/mastat/maPtRepStatList.jsp"
 *                        redirect="false"
 */
public class MaPtRepStatListAction extends AuthAction
{
    /** 조회 */
    public static final int PTREP_STAT_LIST_FIND     = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtRepStatListForm maPtRepStatListForm = (MaPtRepStatListForm) form;
        
        switch (maPtRepStatListForm.getStrutsAction())
        {
            case MaPtRepStatListAction.PTREP_STAT_LIST_FIND:
            	findList(request, maPtRepStatListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtRepStatListAction.BASE_SET_HEADER:
                setHeader(request, response, maPtRepStatListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtRepStatListAction.BASE_GRID_EXPORT:
            	findList(request, maPtRepStatListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPtRepStatList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPtRepStatListForm maPtRepStatListForm) throws IOException
    {
        super.setHeader(request, response, maPtRepStatListForm.getListId(), maPtRepStatListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtRepStatListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MaPtRepStatListForm maPtRepStatListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaPtRepStatListService maPtRepStatListService = (MaPtRepStatListService) getBean("maPtRepStatListService");        

    	MaPtRepStatCommonDTO maPtRepStatCommonDTO = maPtRepStatListForm.getMaPtRepStatCommonDTO();
    	maPtRepStatCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
    	maPtRepStatCommonDTO.setIsLoadMaxCount("Y".equals(maPtRepStatListForm.getIsLoadMaxCount())?true:false);
    	maPtRepStatCommonDTO.setFirstRow(maPtRepStatListForm.getFirstRow());
    	maPtRepStatCommonDTO.setOrderBy(maPtRepStatListForm.getOrderBy());
    	maPtRepStatCommonDTO.setDirection(maPtRepStatListForm.getDirection());

        //리스트를 조회한다.
        List resultList = maPtRepStatListService.findList(maPtRepStatCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(maPtRepStatListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPtRepStatListService.findTotalCount(maPtRepStatCommonDTO,getUser(request));
                
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maPtRepStatListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
}
