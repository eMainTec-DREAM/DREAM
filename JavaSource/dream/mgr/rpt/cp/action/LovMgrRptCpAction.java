package dream.mgr.rpt.cp.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.mgr.rpt.cp.dto.LovMgrRptCpDTO;
import dream.mgr.rpt.cp.form.LovMgrRptCpForm;
import dream.mgr.rpt.cp.service.LovMgrRptCpService;

/**
 * 출력물 선택
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovMgrRptCpList" name="lovMgrRptCpForm"
 *                input="/dream/mgr/rpt/cp/lovMgrRptCpList.jsp" scope="request"
 *                validate="false"
 */
public class LovMgrRptCpAction extends AuthAction
{
    public static final int FIND    = 1001;
    public static final int PRINT   = 1002;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovMgrRptCpForm lovMgrRptCpForm = (LovMgrRptCpForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovMgrRptCpForm.getStrutsAction())
        {
            case LovMgrRptCpAction.FIND :
                findMgrRptCp(request, lovMgrRptCpForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovMgrRptCpAction.BASE_SET_HEADER:
                setHeader(request, response, lovMgrRptCpForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovMgrRptCpAction.PRINT:
                makeReport(lovMgrRptCpForm, request, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovMgrRptCpForm lovMgrRptCpForm) throws IOException
    {
        super.setHeader(request, response, lovMgrRptCpForm.getListId(),lovMgrRptCpForm.getCurrentPageId()); 
    }
    
    private void findMgrRptCp(HttpServletRequest request, LovMgrRptCpForm lovMgrRptCpForm,HttpServletResponse response, boolean excelExport) throws Exception
    {
        LovMgrRptCpService lovMgrRptCpService = (LovMgrRptCpService)getBean("lovMgrRptCpService", request);
        LovMgrRptCpDTO lovMgrRptCpDTO = lovMgrRptCpForm.getLovMgrRptCpDTO();
        
        //Paging
        lovMgrRptCpDTO.setIsLoadMaxCount("Y".equals(lovMgrRptCpForm.getIsLoadMaxCount())?true:false);
        lovMgrRptCpDTO.setFirstRow(lovMgrRptCpForm.getFirstRow());
        lovMgrRptCpDTO.setOrderBy(lovMgrRptCpForm.getOrderBy());
        lovMgrRptCpDTO.setDirection(lovMgrRptCpForm.getDirection());
        
        User user = getUser(request);
        List resultList = lovMgrRptCpService.findList(lovMgrRptCpDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovMgrRptCpForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovMgrRptCpService.findTotalCount(lovMgrRptCpDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,lovMgrRptCpForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    private void makeReport(LovMgrRptCpForm lovMgrRptCpForm, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        LovMgrRptCpService lovMgrRptCpService = (LovMgrRptCpService)getBean("lovMgrRptCpService", request);
        LovMgrRptCpDTO lovMgrRptCpDTO = lovMgrRptCpForm.getLovMgrRptCpDTO();
        
        response.getWriter().print(new Gson().toJson(lovMgrRptCpService.makeReport(lovMgrRptCpDTO, getUser(request))));
    }
}