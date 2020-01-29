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
import dream.work.rpt.energyuseprecon.month.dto.EnergyUsePreConMonthDetailDTO;
import dream.work.rpt.energyuseprecon.month.form.EnergyUsePreConMonthListForm;
import dream.work.rpt.energyuseprecon.month.service.EnergyUsePreConMonthDetailService;

/**
 * EnergyUsePreConMonth Page - Detail Action
 * 
 * @author ghlee
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/energyUsePreConMonthDetailList" name="energyUsePreConMonthListForm"
 *                input="/dream/work/rpt/energyuseprecon/month/energyUsePreConMonthDetailList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/energyUsePreConMonthDetailChart" name="energyUsePreConMonthListForm"
 *                input="/dream/work/rpt/energyuseprecon/month/energyUsePreConMonthDetailChart.jsp" scope="request"
 *                validate="false"
 */
public class EnergyUsePreConMonthDetailAction extends AuthAction
{
    /** 상세조회 */
    public static final int DETAIL_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        EnergyUsePreConMonthListForm energyUsePreConMonthListForm = (EnergyUsePreConMonthListForm) form;
        
        switch (energyUsePreConMonthListForm.getStrutsAction())
        {
            case EnergyUsePreConMonthDetailAction.BASE_SET_HEADER:
                setHeader(request, response, energyUsePreConMonthListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case EnergyUsePreConMonthDetailAction.DETAIL_FIND:
                findDetail(request, response, energyUsePreConMonthListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case EnergyUsePreConMonthDetailAction.BASE_GRID_EXPORT:
                findDetail(request, response, energyUsePreConMonthListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, EnergyUsePreConMonthListForm energyUsePreConMonthListForm) throws IOException
    {
        super.setHeader(request, response, energyUsePreConMonthListForm.getListId(), energyUsePreConMonthListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND DETAIL
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param energyUsePreConMonthListForm
     */
    
    private void findDetail(HttpServletRequest request, HttpServletResponse response, EnergyUsePreConMonthListForm energyUsePreConMonthListForm, boolean excelExport) throws Exception
    {
        EnergyUsePreConMonthDetailService energyUsePreConMonthDetailService = (EnergyUsePreConMonthDetailService) getBean("energyUsePreConMonthDetailService");
        EnergyUsePreConMonthDetailDTO energyUsePreConMonthDetailDTO = energyUsePreConMonthListForm.getEnergyUsePreConMonthDetailDTO();
      
        //Paging
//        energyUsePreConMonthDetailDTO.setIsLoadMaxCount("Y".equals(energyUsePreConMonthListForm.getIsLoadMaxCount())?true:false);
//        energyUsePreConMonthDetailDTO.setFirstRow(energyUsePreConMonthListForm.getFirstRow());
//        energyUsePreConMonthDetailDTO.setOrderBy(energyUsePreConMonthListForm.getOrderBy());
//        energyUsePreConMonthDetailDTO.setDirection(energyUsePreConMonthListForm.getDirection());
        
        User user = getUser(request);
        List resultList = energyUsePreConMonthDetailService.findDetail(energyUsePreConMonthDetailDTO, user);
        
        //Paging
        String totalCount = "";
        
//        if(Integer.parseInt(energyUsePreConMonthListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = energyUsePreConMonthDetailService.findTotalCount(energyUsePreConMonthDetailDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,energyUsePreConMonthListForm.getListId(),energyUsePreConMonthListForm.getCurrentPageId(), energyUsePreConMonthListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, energyUsePreConMonthListForm.getListId());
    }
}