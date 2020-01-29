package dream.asset.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.parser.ParseException;

import common.bean.User;
import common.struts.AuthAction;
import common.struts.SuperAuthAction;
import dream.asset.list.dto.LovEquipListDTO;
import dream.asset.list.form.LovEquipListForm;
import dream.asset.list.service.LovEquipListService;

/**
 * 설비 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovEquipList" name="lovEquipListForm"
 *                input="/dream/asset/list/lovEquipPopup.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/lovEquipAcList" name="lovEquipListForm"
 *                input="/dream/asset/list/lovEquipAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovEquipPopup" path="/dream/asset/list/lovEquipPopup.jsp"
 *                        redirect="false"
 */
public class LovEquipListAction extends SuperAuthAction
{
    public static final int LOV_EQUIP_DEFAULT 	= 1001;
    public static final int LOV_EQUIP_FIND     	= 1002;
    public static final int LOV_EQUIP_AC_FIND  	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovEquipListForm lovEquipListForm = (LovEquipListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovEquipListForm.getStrutsAction())
        {
            case LovEquipListAction.LOV_EQUIP_DEFAULT :
            	setJsonParam(request, lovEquipListForm,response);
                returnActionForward = mapping.findForward("lovEquipPopup");
                break;
            case LovEquipListAction.LOV_EQUIP_FIND :
                findEquipList(request, lovEquipListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovEquipListAction.LOV_EQUIP_AC_FIND :
                findEquipAcList(request, lovEquipListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovEquipListAction.BASE_SET_HEADER:
                setHeader(request, response, lovEquipListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void findEquipAcList(HttpServletRequest request, LovEquipListForm lovEquipListForm,
			HttpServletResponse response, boolean excelExport) throws Exception {
    	LovEquipListService lovEquipListService = (LovEquipListService)getBean("lovEquipListService");
        LovEquipListDTO lovEquipListDTO = lovEquipListForm.getLovEquipListDTO();
        
        //Paging
        lovEquipListDTO.setIsLoadMaxCount("Y".equals(lovEquipListForm.getIsLoadMaxCount())?true:false);
        lovEquipListDTO.setFirstRow(lovEquipListForm.getFirstRow());
        lovEquipListDTO.setOrderBy(lovEquipListForm.getOrderBy());
        lovEquipListDTO.setDirection(lovEquipListForm.getDirection());
        
        User user = getUser(request);
        List resultList = lovEquipListService.findEquipAcList(lovEquipListDTO, user, lovEquipListForm);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovEquipListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovEquipListService.findTotalCount(lovEquipListDTO,user,lovEquipListForm);
        
        if(excelExport) super.makeGridExport(resultList, request, response,lovEquipListForm.getListId(),lovEquipListForm.getCurrentPageId(), lovEquipListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
	}

	private void setJsonParam(HttpServletRequest request, LovEquipListForm lovEquipListForm, HttpServletResponse response) throws ParseException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	
    	LovEquipListDTO lovEquipListDTO =lovEquipListForm.getLovEquipListDTO();
    	LovEquipListService lovEquipListService = (LovEquipListService)getBean("lovEquipListService");
        
    	LovEquipListDTO resultDTO = lovEquipListService.setJsonParm(lovEquipListDTO);
    	
    	lovEquipListForm.setLovEquipListDTO(resultDTO);
	}

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovEquipListForm lovEquipListForm) throws IOException
    {
        super.setHeader(request, response, lovEquipListForm.getListId(),lovEquipListForm.getCurrentPageId()); 
    }

    /**
     * TAPARTS 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovEquipListForm
     */
    private void findEquipList(HttpServletRequest request,
            LovEquipListForm lovEquipListForm,HttpServletResponse response, boolean excelExport) throws Exception
    {
        LovEquipListService lovEquipListService = (LovEquipListService)getBean("lovEquipListService");
        LovEquipListDTO lovEquipListDTO = lovEquipListForm.getLovEquipListDTO();
        
        //Paging
        lovEquipListDTO.setIsLoadMaxCount("Y".equals(lovEquipListForm.getIsLoadMaxCount())?true:false);
        lovEquipListDTO.setFirstRow(lovEquipListForm.getFirstRow());
        lovEquipListDTO.setOrderBy(lovEquipListForm.getOrderBy());
        lovEquipListDTO.setDirection(lovEquipListForm.getDirection());
        
        User user = getUser(request);
        List resultList = lovEquipListService.findEquipList(lovEquipListDTO, getUser(request),lovEquipListForm);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovEquipListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovEquipListService.findTotalCount(lovEquipListDTO,user,lovEquipListForm);
        
        if(excelExport) super.makeGridExport(resultList, request, response,lovEquipListForm.getListId(),lovEquipListForm.getCurrentPageId(), lovEquipListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    	
    }

}