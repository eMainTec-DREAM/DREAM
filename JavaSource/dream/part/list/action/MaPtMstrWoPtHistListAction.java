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
import dream.part.list.form.MaPtMstrWoPtHistListForm;
import dream.part.list.service.MaPtMstrWoPtHistListService;

/**
 * 부품마스터 사용이력 - 목록 action
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPtMstrWoPtHistList" name="maPtMstrWoPtHistListForm"
 *                input="/dream/part/list/maPtMstrWoPtHistList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtMstrWoPtHistList" path="/dream/part/list/maPtMstrWoPtHistList.jsp"
 *                        redirect="false"
 */
public class MaPtMstrWoPtHistListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtMstrWoPtHistListForm maPtMstrWoPtHistListForm = (MaPtMstrWoPtHistListForm) form;
        
        switch (maPtMstrWoPtHistListForm.getStrutsAction())
        {
            case MaPtMstrWoPtHistListAction.LIST_FIND:
            	findPtMstrWoPtHistList(request, maPtMstrWoPtHistListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtMstrWoPtHistListAction.BASE_SET_HEADER:
                setHeader(request, response, maPtMstrWoPtHistListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtMstrWoPtHistListAction.BASE_GRID_EXPORT:
            	findPtMstrWoPtHistList(request, maPtMstrWoPtHistListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPtMstrWoPtHistListForm maPtMstrWoPtHistListForm) throws IOException
    {
        super.setHeader(request, response, maPtMstrWoPtHistListForm.getListId(), maPtMstrWoPtHistListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtMstrWoPtHistListForm
     * @throws Exception
     */
    private void findPtMstrWoPtHistList(HttpServletRequest request, MaPtMstrWoPtHistListForm maPtMstrWoPtHistListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaPtMstrWoPtHistListService maPtMstrWoPtHistListService = (MaPtMstrWoPtHistListService) getBean("maPtMstrWoPtHistListService", request);        

    	MaPtMstrCommonDTO maPtMstrCommonDTO = maPtMstrWoPtHistListForm.getMaPtMstrCommonDTO();
    	
    	//Paging
    	maPtMstrCommonDTO.setIsLoadMaxCount("Y".equals(maPtMstrWoPtHistListForm.getIsLoadMaxCount())?true:false);
    	maPtMstrCommonDTO.setFirstRow(maPtMstrWoPtHistListForm.getFirstRow());
    	maPtMstrCommonDTO.setOrderBy(maPtMstrWoPtHistListForm.getOrderBy());
    	maPtMstrCommonDTO.setDirection(maPtMstrWoPtHistListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maPtMstrWoPtHistListService.findPtMstrWoPtHistList(maPtMstrCommonDTO,getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(maPtMstrWoPtHistListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPtMstrWoPtHistListService.findTotalCount(maPtMstrCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,maPtMstrWoPtHistListForm.getListId(),maPtMstrWoPtHistListForm.getCurrentPageId(), maPtMstrWoPtHistListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}
