package dream.mgr.usrcd.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.mgr.usrcd.dao.CdUsrCodeParentLovDAO;
import dream.mgr.usrcd.dto.CdUsrCodeParentLovDTO;
import dream.mgr.usrcd.form.CdUsrCodeParentLovForm;
import dream.mgr.usrcd.service.CdUsrCodeParentLovService;

/**
 * 사용자코드 부모 LOV - List Service implements
 * @author kim21017
 * @version $Id: CdUsrCodeParentLovServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="cdUsrCodeParentLovServiceTarget"
 * @spring.txbn id="cdUsrCodeParentLovService"
 * @spring.property name="cdUsrCodeParentLovDAO" ref="cdUsrCodeParentLovDAO"
 */
public class CdUsrCodeParentLovServiceImpl implements CdUsrCodeParentLovService
{
	private CdUsrCodeParentLovDAO cdUsrCodeParentLovDAO = null;

	public List findList(CdUsrCodeParentLovForm cdUsrCodeParentLovForm, User user) throws Exception
    {      
		CdUsrCodeParentLovDTO cdUsrCodeParentLovDTO = cdUsrCodeParentLovForm.getCdUsrCodeParentLovDTO();
		
		String code         = cdUsrCodeParentLovForm.getCode();    //Search Value
        String keyCode      = cdUsrCodeParentLovForm.getKeyCode();  //Search Column
        String codeType     = cdUsrCodeParentLovForm.getCodeType(); //Table
        String jsonParam    = cdUsrCodeParentLovForm.getParam();  //Condition
        String jaonCol      = cdUsrCodeParentLovForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);

		
        return cdUsrCodeParentLovDAO.findList(cdUsrCodeParentLovDTO,user, columnMap ,conditionMap);
    }

	public String findTotalCount(CdUsrCodeParentLovForm cdUsrCodeParentLovForm, User user) throws Exception
    {      
		CdUsrCodeParentLovDTO cdUsrCodeParentLovDTO = cdUsrCodeParentLovForm.getCdUsrCodeParentLovDTO();
		
		String code         = cdUsrCodeParentLovForm.getCode();    //Search Value
        String keyCode      = cdUsrCodeParentLovForm.getKeyCode();  //Search Column
        String codeType     = cdUsrCodeParentLovForm.getCodeType(); //Table
        String jsonParam    = cdUsrCodeParentLovForm.getParam();  //Condition
        String jaonCol      = cdUsrCodeParentLovForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
        return cdUsrCodeParentLovDAO.findTotalCount(cdUsrCodeParentLovDTO,user, columnMap ,conditionMap);
    }

	public CdUsrCodeParentLovDAO getCdUsrCodeParentLovDAO() {
		return cdUsrCodeParentLovDAO;
	}

	public void setCdUsrCodeParentLovDAO(CdUsrCodeParentLovDAO cdUsrCodeParentLovDAO) {
		this.cdUsrCodeParentLovDAO = cdUsrCodeParentLovDAO;
	}
	
}

