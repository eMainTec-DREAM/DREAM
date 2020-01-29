package dream.work.let.categ.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.form.WorkLetCategListForm;
import dream.work.let.categ.service.WorkLetCategListService;

/**
 * 안전작업유형 리스트 페이지 - List Action
 * 
 * @author euna0207
 * @version $Id: WorkLetCategListAction.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 * @struts:action path="/workLetCategList" name="workLetCategListForm"
 *                input="/dream/work/let/categ/workLetCategList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workLetCategList" path="/dream/work/let/categ/workLetCategList.jsp"
 *                        redirect="false"

 */

public class WorkLetCategListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        //* jsp form (form-bean에서 설정해둔 매핑시킨 form을 불러옴)
        WorkLetCategListForm workLetCategListForm = (WorkLetCategListForm) form;
        
        //super.updateAudit(workLetCategListForm.getWorkLetCategCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workLetCategListForm.getStrutsAction())
        {
            case WorkLetCategListAction.BASE_SET_HEADER:
                setHeader(request, response, workLetCategListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkLetCategListAction.LIST_FIND:
                findList(request, response, workLetCategListForm, false);
                //findForward메소드는 struts-config.xml에서 설정한 page로 포워드하게 되어있다
                //Global Forward Definition.
                //struts-config에 가보면, name=jsonPage에 대한 path가 설정되어있다. 
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkLetCategListAction.LIST_DELETE:
            	deleteList(request, workLetCategListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
                //BaseAction에 선언되어있음. 엑셀 
                //해당 선택 LIST값 DAO타고 불러온 후, gridExport에서 테마 지정 후, POI 라이브러리에서 엑셀 다운로드 처리
            case WorkLetCategListAction.BASE_GRID_EXPORT:
            	findList(request, response, workLetCategListForm,true);
            							//지정되어있는 servlet 존재
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
            	//getInputForward :  struts-config의 action태그에 지정해둔 <forward>에 지정된 내용을 가져옴 
                //returnActionForward = mapping.findForward("workLetCategList");
                break;
        }
        // action의 return값이 success 처리가 되면 forward태그의 action값에 맞는 JSP로 이동하게끔 설정해두었음 (struts-config에서)
        // <action>태그와 묶인 <forward>라서 findForward에서의 <forward>태그와 구별
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkLetCategListForm workLetCategListForm) throws IOException
    {
        super.setHeader(request, response, workLetCategListForm.getListId(), workLetCategListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  euna0207
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workLetCategListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkLetCategListForm workLetCategListForm, boolean excelExport) throws Exception
    {
    	//applicationContext-txbn.xml에 들어있는 Bean들 중 service bean가져온것
    	//getBean메서드는 Object타입이므로 타입 변환 시켜줌
    	WorkLetCategListService workLetCategListService = (WorkLetCategListService) getBean("workLetCategListService");
    	WorkLetCategCommonDTO workLetCategCommonDTO = workLetCategListForm.getWorkLetCategCommonDTO();

    	//BaseDTO
    	//스크롤 load max값
    	workLetCategCommonDTO.setIsLoadMaxCount("Y".equals(workLetCategListForm.getIsLoadMaxCount())?true:false);
    	//다음 스크롤처리시 어디부터 또 다음 load max값 가져와야하므로 첫 줄 알아야한다.
    	workLetCategCommonDTO.setFirstRow(workLetCategListForm.getFirstRow());
    	//순서
    	workLetCategCommonDTO.setOrderBy(workLetCategListForm.getOrderBy());
    	//ascending, descending
    	workLetCategCommonDTO.setDirection(workLetCategListForm.getDirection());
    	
    	User user = getUser(request);
        List resultList = workLetCategListService.findList(workLetCategCommonDTO, user);

        //Paging 
        String totalCount = "";
        //gird list totalCount 처리
        if(Integer.parseInt(
        		workLetCategListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workLetCategListService.findTotalCount(workLetCategCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workLetCategListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  euna0207
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param workLetCategListForm
     */
    private void deleteList(HttpServletRequest request, WorkLetCategListForm workLetCategListForm) throws Exception
    {
    	WorkLetCategListService workLetCategListService = (WorkLetCategListService) getBean("workLetCategListService");
        String[] deleteRows = workLetCategListForm.getDeleteRows();
        
    	User user = getUser(request);
        workLetCategListService.deleteList(deleteRows, user);
        // AJAX 처리 상태를 셋팅
        setAjaxStatus(request);
    }
    
}
