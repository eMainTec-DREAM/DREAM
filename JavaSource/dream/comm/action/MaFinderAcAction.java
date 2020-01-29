package dream.comm.action;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;

import com.fins.org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import dream.comm.form.MaFinderAcForm;
import dream.comm.service.MaFinderAcService;

/**
 * AutoComplete Action
 * @author  jung7126
 * @version $Id: ValidationAction.java,v 1.3 2014/02/13 05:46:07 pochul2423 Exp $
 * @since   1.0
 * 
 * @struts:action path="/maFinderAc" name="maFinderAcForm"
 *                input="/common/ajax/ajaxXmlVal.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="ajaxXmlVal" path="/common/ajax/ajaxXmlVal.jsp"
 *                        redirect="false"
 * @struts.action-forward name="lovAcPopup" path="/dream/comm/lovAcDirPopup.jsp"
 *                        redirect="false"
 */
public class MaFinderAcAction extends SuperAuthAction
{
    /** common AutoComplete */
    public static final int AC_DESC  			    = 1004;
    /** LOV */
    public static final int AC_LOV                  = 1005;
    /** Common Validation */
    public static final int VA_CHECK                = 1006;
    /** AutoComplete Custom */
    public static final int AC_CUSTOM               = 1007;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaFinderAcForm maFinderAcForm = (MaFinderAcForm)form;
        
        switch (maFinderAcForm.getStrutsAction())
        {
            case MaFinderAcAction.AC_DESC :
                findAcDesc(request, maFinderAcForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaFinderAcAction.AC_LOV :
                findAcLov(request, maFinderAcForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaFinderAcAction.VA_CHECK :
                findValCnt(request, maFinderAcForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaFinderAcAction.AC_CUSTOM :
                findAcCustom(request, maFinderAcForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                returnActionForward = mapping.findForward("lovAcPopup");
                break;
        }
        
        return returnActionForward;
    }
    
    private void findAcCustom(HttpServletRequest request, MaFinderAcForm maFinderAcForm, HttpServletResponse response) throws IOException 
    {
        MaFinderAcService maFinderAcService = (MaFinderAcService)getBean("maFinderAcService", request);
        
        String sValue         = maFinderAcForm.getCode();    //Search Value
        String acType     = maFinderAcForm.getCodeType(); //Table
        String jsonParam    = maFinderAcForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> codeTypeMap = gson.fromJson(jsonParam, type);
        
        Map resultList = maFinderAcService.findAcCustom(sValue, acType, codeTypeMap, getUser(request));
        
        super.makeJsonResultPop((List)resultList.get("list"), request, response, maFinderAcForm.getListId());
        
    }

    private void findValCnt(HttpServletRequest request,  MaFinderAcForm maFinderAcForm, HttpServletResponse response) throws IOException
    {
        MaFinderAcService maFinderAcService = (MaFinderAcService)getBean("maFinderAcService", request);
        
        String code         = maFinderAcForm.getCode();    //Search Value
        String keyCode      = maFinderAcForm.getKeyCode();  //Search Column
        String codeType     = maFinderAcForm.getCodeType(); //Table
        String jsonParam    = maFinderAcForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
        int resultCnt = maFinderAcService.findValCnt(keyCode, code, codeType, conditionMap);

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("count", resultCnt);

        response.getWriter().print(jsonObj.toString());
    }

    private void findAcLov(HttpServletRequest request, MaFinderAcForm maFinderAcForm, HttpServletResponse response) throws IOException
    {
        MaFinderAcService maFinderAcService = (MaFinderAcService)getBean("maFinderAcService", request);
        
        String code         = maFinderAcForm.getCode();    //Search Value
        String keyCode      = maFinderAcForm.getKeyCode();  //Search Column
        String codeType     = maFinderAcForm.getCodeType(); //Table
        String jsonParam    = maFinderAcForm.getParam();  //Condition
        String jaonCol      = maFinderAcForm.getResultCol();  //Result Column
        String jaonLabel    = maFinderAcForm.getLabel();
        String lang         = getUser(request).getLangId();
        
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type); // <maPtMstrList.partNo:part_no>
        Map<String,String> labelMap = gson.fromJson(jaonLabel, type);
        
        Map resultList = maFinderAcService.findAutoCompleteDesc(keyCode, code, columnMap, codeType, conditionMap, labelMap, lang, false, getUser(request));

        super.makeJsonResultWithHead((List)resultList.get("list"), request, response, maFinderAcForm.getListId(), labelMap);
    }

    /**
     * description 값으로 id를 검색한다.
     * @author  javaworker
     * @version $Id: ValidationAction.java,v 1.3 2014/02/13 05:46:07 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param validationForm
     * @throws IOException 
     */
    private void findAcDesc(HttpServletRequest request, MaFinderAcForm maFinderAcForm, HttpServletResponse response) throws IOException
    {
        MaFinderAcService maFinderAcService = (MaFinderAcService)getBean("maFinderAcService", request);
        
        String code         = maFinderAcForm.getCode();    //Search Value
        String keyCode      = maFinderAcForm.getKeyCode();  //Search Column
        String codeType     = maFinderAcForm.getCodeType(); //Table
        String jsonParam    = maFinderAcForm.getParam();  //Condition
        String jaonCol      = maFinderAcForm.getResultCol();  //Result Column
        String jaonLabel    = maFinderAcForm.getLabel();
        String lang         = getUser(request).getLangId();


        //Table Name Switch
        
        //column Sequence How????
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type); // <maPtMstrList.partNo:part_no>
        Map<String,String> labelMap = gson.fromJson(jaonLabel, type);
        
        Map resultList = maFinderAcService.findAutoCompleteDesc(keyCode, code, columnMap, codeType, conditionMap, labelMap, lang, true, getUser(request));
        
        super.makeJsonResultPop((List)resultList.get("list"), request, response, maFinderAcForm.getListId());
        
//        makeJson(resultList, request, response);
    }
}
