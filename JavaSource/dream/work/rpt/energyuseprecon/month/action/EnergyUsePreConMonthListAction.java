package dream.work.rpt.energyuseprecon.month.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.rpt.energyuseprecon.month.dto.EnergyUsePreConMonthCommonDTO;
import dream.work.rpt.energyuseprecon.month.form.EnergyUsePreConMonthListForm;
import dream.work.rpt.energyuseprecon.month.service.EnergyUsePreConMonthListService;

/**
 * EnergyUsePreConMonth Page - List Action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/energyUsePreConMonthList" name="energyUsePreConMonthListForm"
 *                input="/dream/work/rpt/energyuseprecon/month/energyUsePreConMonthList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="energyUsePreConMonthList" path="/dream/work/rpt/energyuseprecon/month/energyUsePreConMonthList.jsp"
 *                        redirect="false"
 */
public class EnergyUsePreConMonthListAction extends AuthAction
{
    /** Á¶È¸ */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        EnergyUsePreConMonthListForm energyUsePreConMonthListForm = (EnergyUsePreConMonthListForm) form;
        
        switch (energyUsePreConMonthListForm.getStrutsAction())
        {
            case EnergyUsePreConMonthListAction.BASE_SET_HEADER:
                setHeader(request, response, energyUsePreConMonthListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case EnergyUsePreConMonthListAction.LIST_FIND:
                findList(request, response, energyUsePreConMonthListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case EnergyUsePreConMonthListAction.BASE_GRID_EXPORT:
                findList(request, response, energyUsePreConMonthListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("energyUsePreConMonthList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, EnergyUsePreConMonthListForm energyUsePreConMonthListForm) throws IOException
    {
        super.setHeader(request, response, energyUsePreConMonthListForm.getListId(), energyUsePreConMonthListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param energyUsePreConMonthListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, EnergyUsePreConMonthListForm energyUsePreConMonthListForm, boolean excelExport) throws Exception
    {
        EnergyUsePreConMonthListService energyUsePreConMonthListService = (EnergyUsePreConMonthListService) getBean("energyUsePreConMonthListService");
        EnergyUsePreConMonthCommonDTO energyUsePreConMonthCommonDTO = energyUsePreConMonthListForm.getEnergyUsePreConMonthCommonDTO();
      
        //Paging
        energyUsePreConMonthCommonDTO.setIsLoadMaxCount("Y".equals(energyUsePreConMonthListForm.getIsLoadMaxCount())?true:false);
        energyUsePreConMonthCommonDTO.setFirstRow(energyUsePreConMonthListForm.getFirstRow());
        energyUsePreConMonthCommonDTO.setOrderBy(energyUsePreConMonthListForm.getOrderBy());
        energyUsePreConMonthCommonDTO.setDirection(energyUsePreConMonthListForm.getDirection());
        
        User user = getUser(request);
        List resultList = energyUsePreConMonthListService.findList(energyUsePreConMonthCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(energyUsePreConMonthListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = energyUsePreConMonthListService.findTotalCount(energyUsePreConMonthCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,energyUsePreConMonthListForm.getListId(),energyUsePreConMonthListForm.getCurrentPageId(), energyUsePreConMonthListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}