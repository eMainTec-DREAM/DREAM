package dream.consult.comp.cdsys.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.consult.comp.cdsys.dao.CdSysCodeParentLovDAO;
import dream.consult.comp.cdsys.dto.CdSysCodeParentLovDTO;
import dream.consult.comp.cdsys.form.CdSysCodeParentLovForm;
import dream.consult.comp.cdsys.service.CdSysCodeParentLovService;

/**
 * 시스템코드 부모 LOV - List Service implements
 * @author kim21017
 * @version $Id: CdSysCodeParentLovServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="cdSysCodeParentLovServiceTarget"
 * @spring.txbn id="cdSysCodeParentLovService"
 * @spring.property name="cdSysCodeParentLovDAO" ref="cdSysCodeParentLovDAO"
 */
public class CdSysCodeParentLovServiceImpl implements CdSysCodeParentLovService
{
	private CdSysCodeParentLovDAO cdSysCodeParentLovDAO = null;

	public List findList(CdSysCodeParentLovForm cdSysCodeParentLovForm, User user) throws Exception
    {      
		CdSysCodeParentLovDTO cdSysCodeParentLovDTO = cdSysCodeParentLovForm.getCdSysCodeParentLovDTO();
		
		String code         = cdSysCodeParentLovForm.getCode();    //Search Value
        String keyCode      = cdSysCodeParentLovForm.getKeyCode();  //Search Column
        String codeType     = cdSysCodeParentLovForm.getCodeType(); //Table
        String jsonParam    = cdSysCodeParentLovForm.getParam();  //Condition
        String jaonCol      = cdSysCodeParentLovForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);

		
        return cdSysCodeParentLovDAO.findList(cdSysCodeParentLovDTO,user, columnMap ,conditionMap);
    }

	public String findTotalCount(CdSysCodeParentLovForm cdSysCodeParentLovForm, User user) throws Exception
    {      
		CdSysCodeParentLovDTO cdSysCodeParentLovDTO = cdSysCodeParentLovForm.getCdSysCodeParentLovDTO();
		
		String code         = cdSysCodeParentLovForm.getCode();    //Search Value
        String keyCode      = cdSysCodeParentLovForm.getKeyCode();  //Search Column
        String codeType     = cdSysCodeParentLovForm.getCodeType(); //Table
        String jsonParam    = cdSysCodeParentLovForm.getParam();  //Condition
        String jaonCol      = cdSysCodeParentLovForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
        return cdSysCodeParentLovDAO.findTotalCount(cdSysCodeParentLovDTO,user, columnMap ,conditionMap);
    }

	public CdSysCodeParentLovDAO getCdSysCodeParentLovDAO() {
		return cdSysCodeParentLovDAO;
	}

	public void setCdSysCodeParentLovDAO(CdSysCodeParentLovDAO cdSysCodeParentLovDAO) {
		this.cdSysCodeParentLovDAO = cdSysCodeParentLovDAO;
	}
	
}

