package dream.mgr.contract.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.mgr.contract.dao.LovMgrContractListDAO;
import dream.mgr.contract.dto.LovMgrContractListDTO;
import dream.mgr.contract.form.LovMgrContractListForm;
import dream.mgr.contract.service.LovMgrContractListService;

/**
 * 단가계약 LOV - List Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="lovMgrContractListServiceTarget"
 * @spring.txbn id="lovMgrContractListService"
 * @spring.property name="lovMgrContractListDAO" ref="lovMgrContractListDAO"
 */
public class LovMgrContractListServiceImpl implements LovMgrContractListService
{
	private LovMgrContractListDAO lovMgrContractListDAO = null;

	public List findList(LovMgrContractListForm lovMgrContractListForm, User user) throws Exception
    {      
		LovMgrContractListDTO lovMgrContractListDTO = lovMgrContractListForm.getLovMgrContractListDTO();
		
		String code         = lovMgrContractListForm.getCode();    //Search Value
        String keyCode      = lovMgrContractListForm.getKeyCode();  //Search Column
        String codeType     = lovMgrContractListForm.getCodeType(); //Table
        String jsonParam    = lovMgrContractListForm.getParam();  //Condition
        String jaonCol      = lovMgrContractListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);

		
        return lovMgrContractListDAO.findList(lovMgrContractListDTO,user, columnMap ,conditionMap);
    }

	public String findTotalCount(LovMgrContractListForm lovMgrContractListForm, User user) throws Exception
    {      
		LovMgrContractListDTO lovMgrContractListDTO = lovMgrContractListForm.getLovMgrContractListDTO();
		
		String code         = lovMgrContractListForm.getCode();    //Search Value
        String keyCode      = lovMgrContractListForm.getKeyCode();  //Search Column
        String codeType     = lovMgrContractListForm.getCodeType(); //Table
        String jsonParam    = lovMgrContractListForm.getParam();  //Condition
        String jaonCol      = lovMgrContractListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
        return lovMgrContractListDAO.findTotalCount(lovMgrContractListDTO,user, columnMap ,conditionMap);
    }

	public LovMgrContractListDAO getLovMgrContractListDAO() {
		return lovMgrContractListDAO;
	}

	public void setLovMgrContractListDAO(LovMgrContractListDAO lovMgrContractListDAO) {
		this.lovMgrContractListDAO = lovMgrContractListDAO;
	}
	
}

