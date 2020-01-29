package dream.work.rpt.energyuseprecon.year.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.rpt.energyuseprecon.year.dto.EnergyUsePreConYearCommonDTO;
import dream.work.rpt.energyuseprecon.year.form.EnergyUsePreConYearListForm;
import dream.work.rpt.energyuseprecon.year.service.EnergyUsePreConYearListService;

/**
 * EnergyUsePreConYear Page - List Action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/energyUsePreConYearList" name="energyUsePreConYearListForm"
 *                input="/dream/work/rpt/energyuseprecon/year/energyUsePreConYearList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="energyUsePreConYearList" path="/dream/work/rpt/energyuseprecon/year/energyUsePreConYearList.jsp"
 *                        redirect="false"
 */
public class EnergyUsePreConYearListAction extends AuthAction
{
    /** Á¶È¸ */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        EnergyUsePreConYearListForm energyUsePreConYearListForm = (EnergyUsePreConYearListForm) form;
        
        switch (energyUsePreConYearListForm.getStrutsAction())
        {
            case EnergyUsePreConYearListAction.BASE_SET_HEADER:
                setHeader(request, response, energyUsePreConYearListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case EnergyUsePreConYearListAction.LIST_FIND:
                findList(request, response, energyUsePreConYearListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case EnergyUsePreConYearListAction.BASE_GRID_EXPORT:
                findList(request, response, energyUsePreConYearListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, EnergyUsePreConYearListForm energyUsePreConYearListForm) throws IOException
    {
        super.setHeader(request, response, energyUsePreConYearListForm.getListId(), energyUsePreConYearListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param energyUsePreConYearListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, EnergyUsePreConYearListForm energyUsePreConYearListForm, boolean excelExport) throws Exception
    {
        EnergyUsePreConYearListService energyUsePreConYearListService = (EnergyUsePreConYearListService) getBean("energyUsePreConYearListService");
        EnergyUsePreConYearCommonDTO energyUsePreConYearCommonDTO = energyUsePreConYearListForm.getEnergyUsePreConYearCommonDTO();
      
        //Paging
        energyUsePreConYearCommonDTO.setIsLoadMaxCount("Y".equals(energyUsePreConYearListForm.getIsLoadMaxCount())?true:false);
        energyUsePreConYearCommonDTO.setFirstRow(energyUsePreConYearListForm.getFirstRow());
        energyUsePreConYearCommonDTO.setOrderBy(energyUsePreConYearListForm.getOrderBy());
        energyUsePreConYearCommonDTO.setDirection(energyUsePreConYearListForm.getDirection());
        
        User user = getUser(request);
        List resultList = energyUsePreConYearListService.findList(energyUsePreConYearCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(energyUsePreConYearListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = energyUsePreConYearListService.findTotalCount(energyUsePreConYearCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,energyUsePreConYearListForm.getListId(),energyUsePreConYearListForm.getCurrentPageId(), energyUsePreConYearListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}