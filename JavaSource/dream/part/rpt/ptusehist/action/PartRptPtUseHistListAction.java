package dream.part.rpt.ptusehist.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.rpt.ptusehist.dto.PartRptPtUseHistCommonDTO;
import dream.part.rpt.ptusehist.form.PartRptPtUseHistListForm;
import dream.part.rpt.ptusehist.service.PartRptPtUseHistListService;

/**
 * 부품 사용 분석 - List Action
 * 
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/partRptPtUseHistList" name="partRptPtUseHistListForm"
 *                input="/dream/part/rpt/ptusehist/partRptPtUseHistList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="partRptPtUseHistList" path="/dream/part/rpt/ptusehist/partRptPtUseHistList.jsp"
 *                        redirect="false"
 */

public class PartRptPtUseHistListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        PartRptPtUseHistListForm partRptPtUseHistListForm = (PartRptPtUseHistListForm) form;
        
        switch (partRptPtUseHistListForm.getStrutsAction())
        {
            case PartRptPtUseHistListAction.BASE_SET_HEADER:
                setHeader(request, response, partRptPtUseHistListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PartRptPtUseHistListAction.LIST_FIND:
                findList(request, response, partRptPtUseHistListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case PartRptPtUseHistListAction.BASE_GRID_EXPORT:
            	findList(request, response, partRptPtUseHistListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
                //returnActionForward = mapping.findForward("partRptPtUseHistList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, PartRptPtUseHistListForm partRptPtUseHistListForm) throws IOException
    {
        super.setHeader(request, response, partRptPtUseHistListForm.getListId(), partRptPtUseHistListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND 부품 사용 LIST
     * @author  sy.yang
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param partRptPtUseHistListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, PartRptPtUseHistListForm partRptPtUseHistListForm, boolean excelExport) throws Exception
    {
    	PartRptPtUseHistListService partRptPtUseHistListService = (PartRptPtUseHistListService) getBean("partRptPtUseHistListService");
    	PartRptPtUseHistCommonDTO partRptPtUseHistCommonDTO = partRptPtUseHistListForm.getPartRptPtUseHistCommonDTO();

    	//Paging
    	partRptPtUseHistCommonDTO.setIsLoadMaxCount("Y".equals(partRptPtUseHistListForm.getIsLoadMaxCount())?true:false);
    	partRptPtUseHistCommonDTO.setFirstRow(partRptPtUseHistListForm.getFirstRow());
    	partRptPtUseHistCommonDTO.setOrderBy(partRptPtUseHistListForm.getOrderBy());
    	partRptPtUseHistCommonDTO.setDirection(partRptPtUseHistListForm.getDirection());
    	
        List resultList = partRptPtUseHistListService.findPtUseList(partRptPtUseHistCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(partRptPtUseHistListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = partRptPtUseHistListService.findTotalCount(partRptPtUseHistCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,partRptPtUseHistListForm.getListId(),partRptPtUseHistListForm.getCurrentPageId(), partRptPtUseHistListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    
}
