package dream.part.list.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.form.MaPtMstrIssStatListForm;
import dream.part.list.service.MaPtMstrIssStatListService;

/**
 * 부품마스터 출고이력 - 목록 action
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPtMstrIssStatList" name="maPtMstrIssStatListForm"
 *                input="/dream/part/list/maPtMstrIssStatList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtMstrIssStatList" path="/dream/part/list/maPtMstrIssStatList.jsp"
 *                        redirect="false"
 */
public class MaPtMstrIssStatListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtMstrIssStatListForm maPtMstrIssStatListForm = (MaPtMstrIssStatListForm) form;
        
        switch (maPtMstrIssStatListForm.getStrutsAction())
        {
            case MaPtMstrIssStatListAction.LIST_FIND:
            	findPtMstrIssStatList(request, maPtMstrIssStatListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtMstrIssStatListAction.BASE_SET_HEADER:
                setHeader(request, response, maPtMstrIssStatListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtMstrIssStatListAction.BASE_GRID_EXPORT:
            	findPtMstrIssStatList(request, maPtMstrIssStatListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPtMstrIssStatListForm maPtMstrIssStatListForm) throws IOException
    {
        super.setHeader(request, response, maPtMstrIssStatListForm.getListId(), maPtMstrIssStatListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtMstrIssStatListForm
     * @throws Exception
     */
    private void findPtMstrIssStatList(HttpServletRequest request, MaPtMstrIssStatListForm maPtMstrIssStatListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaPtMstrIssStatListService maPtMstrIssStatListService = (MaPtMstrIssStatListService) getBean("maPtMstrIssStatListService", request);        

    	MaPtMstrCommonDTO maPtMstrCommonDTO = maPtMstrIssStatListForm.getMaPtMstrCommonDTO();
    	
    	//Paging
    	maPtMstrCommonDTO.setIsLoadMaxCount("Y".equals(maPtMstrIssStatListForm.getIsLoadMaxCount())?true:false);
    	maPtMstrCommonDTO.setFirstRow(maPtMstrIssStatListForm.getFirstRow());
    	maPtMstrCommonDTO.setOrderBy(maPtMstrIssStatListForm.getOrderBy());
    	maPtMstrCommonDTO.setDirection(maPtMstrIssStatListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maPtMstrIssStatListService.findPtMstrIssStatList(maPtMstrCommonDTO,getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(maPtMstrIssStatListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPtMstrIssStatListService.findTotalCount(maPtMstrCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,maPtMstrIssStatListForm.getListId(),maPtMstrIssStatListForm.getCurrentPageId(), maPtMstrIssStatListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}
