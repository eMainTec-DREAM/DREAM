package dream.rcm.pmtask.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.rcm.pmtask.dao.RcmPmtaskAcListDAO;
import dream.rcm.pmtask.dto.RcmPmtaskAcListDTO;
import dream.rcm.pmtask.form.RcmPmtaskAcListForm;
import dream.rcm.pmtask.service.RcmPmtaskAcListService;

/**
 * LOV - List Service implements
 * @author kim21017
 * @version $Id: RcmPmtaskAcListServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="rcmPmtaskAcListServiceTarget"
 * @spring.txbn id="rcmPmtaskAcListService"
 * @spring.property name="rcmPmtaskAcListDAO" ref="rcmPmtaskAcListDAO"
 */
public class RcmPmtaskAcListServiceImpl implements RcmPmtaskAcListService
{
	private RcmPmtaskAcListDAO rcmPmtaskAcListDAO = null;

	public List findList(RcmPmtaskAcListForm rcmPmtaskAcListForm, User user) throws Exception
    {      
		RcmPmtaskAcListDTO rcmPmtaskAcListDTO = rcmPmtaskAcListForm.getRcmPmtaskAcListDTO();
		
		String code         = rcmPmtaskAcListForm.getCode();    //Search Value
        String keyCode      = rcmPmtaskAcListForm.getKeyCode();  //Search Column
        String codeType     = rcmPmtaskAcListForm.getCodeType(); //Table
        String jsonParam    = rcmPmtaskAcListForm.getParam();  //Condition
        String jaonCol      = rcmPmtaskAcListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);

		
        return rcmPmtaskAcListDAO.findList(rcmPmtaskAcListDTO,user, columnMap ,conditionMap);
    }

	public String findTotalCount(RcmPmtaskAcListForm rcmPmtaskAcListForm, User user) throws Exception
    {      
		RcmPmtaskAcListDTO rcmPmtaskAcListDTO = rcmPmtaskAcListForm.getRcmPmtaskAcListDTO();
		
		String code         = rcmPmtaskAcListForm.getCode();    //Search Value
        String keyCode      = rcmPmtaskAcListForm.getKeyCode();  //Search Column
        String codeType     = rcmPmtaskAcListForm.getCodeType(); //Table
        String jsonParam    = rcmPmtaskAcListForm.getParam();  //Condition
        String jaonCol      = rcmPmtaskAcListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
        return rcmPmtaskAcListDAO.findTotalCount(rcmPmtaskAcListDTO,user, columnMap ,conditionMap);
    }

    public RcmPmtaskAcListDAO getRcmPmtaskAcListDAO()
    {
        return rcmPmtaskAcListDAO;
    }

    public void setRcmPmtaskAcListDAO(RcmPmtaskAcListDAO rcmPmtaskAcListDAO)
    {
        this.rcmPmtaskAcListDAO = rcmPmtaskAcListDAO;
    }

}

