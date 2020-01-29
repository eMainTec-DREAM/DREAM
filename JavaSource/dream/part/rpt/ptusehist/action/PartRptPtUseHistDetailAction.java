package dream.part.rpt.ptusehist.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.part.rpt.ptusehist.dto.PartRptPtUseHistDetailDTO;
import dream.part.rpt.ptusehist.form.PartRptPtUseHistListForm;
import dream.part.rpt.ptusehist.service.PartRptPtUseHistDetailService;

/**
 * 부품 사용 분석 - Detail Action
 * 
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/partRptPtUseHistDetailList" name="partRptPtUseHistListForm"
 *                input="/dream/part/rpt/ptusehist/partRptPtUseHistDetailList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/partRptPtMstrEqPartList" name="partRptPtUseHistListForm"
 *                input="/dream/part/rpt/ptusehist/partRptPtMstrEqPartList.jsp" scope="request"
 *                validate="false"
 */
public class PartRptPtUseHistDetailAction extends AuthAction
{
    /** 상세조회 (부품별)*/
    public static final int PART_DETAIL_FIND       = 1001;
    /** 상세조회 (설비별)*/
    public static final int EQ_DETAIL_FIND       = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        PartRptPtUseHistListForm partRptPtUseHistListForm = (PartRptPtUseHistListForm) form;
        
        switch (partRptPtUseHistListForm.getStrutsAction())
        {
            case PartRptPtUseHistDetailAction.BASE_SET_HEADER:
                setHeader(request, response, partRptPtUseHistListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PartRptPtUseHistDetailAction.PART_DETAIL_FIND:
                findPartDetailList(request, response, partRptPtUseHistListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case PartRptPtUseHistDetailAction.BASE_GRID_EXPORT:
            	findPartDetailList(request, response, partRptPtUseHistListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case PartRptPtUseHistDetailAction.EQ_DETAIL_FIND:
            	findEqDetailList(request, response, partRptPtUseHistListForm,false);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;    
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, PartRptPtUseHistListForm partRptPtUseHistListForm) throws IOException
    {
        super.setHeader(request, response, partRptPtUseHistListForm.getListId(), partRptPtUseHistListForm.getCurrentPageId()); 
    }
   
    /**
     * 부품사용분석 부품별 FIND DETAIL List
     * @author  sy.yang
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param partRptPtUseHistListForm
     */
    
    private void findPartDetailList(HttpServletRequest request, HttpServletResponse response, PartRptPtUseHistListForm partRptPtUseHistListForm, boolean excelExport) throws Exception
    {
        PartRptPtUseHistDetailService partRptPtUseHistDetailService = (PartRptPtUseHistDetailService) getBean("partRptPtUseHistDetailService");
        PartRptPtUseHistDetailDTO partRptPtUseHistDetailDTO = partRptPtUseHistListForm.getPartRptPtUseHistDetailDTO();
      
        //Paging
        partRptPtUseHistDetailDTO.setIsLoadMaxCount("Y".equals(partRptPtUseHistListForm.getIsLoadMaxCount())?true:false);
        partRptPtUseHistDetailDTO.setFirstRow(partRptPtUseHistListForm.getFirstRow());
        partRptPtUseHistDetailDTO.setOrderBy(partRptPtUseHistListForm.getOrderBy());
        partRptPtUseHistDetailDTO.setDirection(partRptPtUseHistListForm.getDirection());
        
        List resultList = partRptPtUseHistDetailService.findPartDetailList(partRptPtUseHistDetailDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(partRptPtUseHistListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = partRptPtUseHistDetailService.findPartTotalCount(partRptPtUseHistDetailDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,partRptPtUseHistListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * 부품사용분석 설비별 FIND DETAIL List
     * @author  sy.yang
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param partRptPtUseHistListForm
     */    
    private void findEqDetailList(HttpServletRequest request, HttpServletResponse response, PartRptPtUseHistListForm partRptPtUseHistListForm, boolean excelExport) throws Exception
    {
    	PartRptPtUseHistDetailService partRptPtUseHistDetailService = (PartRptPtUseHistDetailService) getBean("partRptPtUseHistDetailService");
    	PartRptPtUseHistDetailDTO partRptPtUseHistDetailDTO = partRptPtUseHistListForm.getPartRptPtUseHistDetailDTO();
    	
    	//Paging
    	partRptPtUseHistDetailDTO.setIsLoadMaxCount("Y".equals(partRptPtUseHistListForm.getIsLoadMaxCount())?true:false);
    	partRptPtUseHistDetailDTO.setFirstRow(partRptPtUseHistListForm.getFirstRow());
    	partRptPtUseHistDetailDTO.setOrderBy(partRptPtUseHistListForm.getOrderBy());
    	partRptPtUseHistDetailDTO.setDirection(partRptPtUseHistListForm.getDirection());
    	
    	List resultList = partRptPtUseHistDetailService.findEqDetailList(partRptPtUseHistDetailDTO, getUser(request));
    	
    	//Paging
    	String totalCount = "";
    	if(Integer.parseInt(partRptPtUseHistListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = partRptPtUseHistDetailService.findEqTotalCount(partRptPtUseHistDetailDTO,getUser(request));
    	
    	if(excelExport) super.makeGridExport(resultList, request, response,partRptPtUseHistListForm.getListId(),partRptPtUseHistListForm.getCurrentPageId(), partRptPtUseHistListForm.getFileName());
    	else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
   
}