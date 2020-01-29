package dream.mgr.intf.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fins.org.json.JSONObject;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.mgr.intf.dto.MgrIntfCommonDTO;
import dream.mgr.intf.form.MgrIntfParamsForm;
import dream.mgr.intf.service.MgrIntfParamsService;

/**
 * 인터페이스 파라미터 팝업
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/mgrIntfParams" name="mgrIntfParamsForm"
 *                input="/dream/mgr/intf/mgrIntfParams.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrIntfParams" path="/dream/mgr/intf/mgrIntfParams.jsp"
 *                        redirect="false"
 */
public class MgrIntfParamsAction extends AuthAction
{
    /** find */
    public static final int LIST_FIND   = 1001;
    /** exec */
    public static final int LIST_EXEC   = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrIntfParamsForm mgrIntfParamsForm = (MgrIntfParamsForm) form;
        switch (mgrIntfParamsForm.getStrutsAction())
        {
            case MgrIntfParamsAction.BASE_SET_HEADER:
                setHeader(request, response, mgrIntfParamsForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrIntfParamsAction.LIST_FIND:
                this.findList(request, response, mgrIntfParamsForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrIntfParamsAction.LIST_EXEC:
            	execList(request, response, mgrIntfParamsForm);
                returnActionForward = mapping.findForward("jsonPage");
            	break;
            case MgrIntfParamsAction.BASE_GRID_EXPORT:
                this.findList(request, response, mgrIntfParamsForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrIntfParamsForm mgrIntfParamsForm) throws IOException
    {
        super.setHeader(request, response, mgrIntfParamsForm.getListId(), mgrIntfParamsForm.getCurrentPageId()); 
    }
    
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrIntfParamsForm mgrIntfParamsForm, boolean excelExport) throws Exception
    {
        MgrIntfParamsService mgrIntfParamsService = (MgrIntfParamsService) getBean("mgrIntfParamsService");
        MgrIntfCommonDTO mgrIntfCommonDTO = mgrIntfParamsForm.getMgrIntfCommonDTO();
        
        User user = getUser(request);
        
        List resultList = mgrIntfParamsService.findList(mgrIntfCommonDTO, user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, mgrIntfParamsForm);
        else CommonUtil.makeJsonResult(resultList, request, response, mgrIntfParamsForm.getListId());
    }
    
    private void execList(HttpServletRequest request, HttpServletResponse response, MgrIntfParamsForm mgrIntfParamsForm) throws Exception
    {
        MgrIntfParamsService mgrIntfParamsService = (MgrIntfParamsService) getBean("mgrIntfParamsService");
        MgrIntfCommonDTO mgrIntfCommonDTO = mgrIntfParamsForm.getMgrIntfCommonDTO();
        
        List<Map> gridList = CommonUtil.setGridMap(request);
        
        JSONObject jsonObj = mgrIntfParamsService.execList(gridList, mgrIntfCommonDTO, getUser(request));
        
        response.getWriter().print(jsonObj.toString());
    }
}