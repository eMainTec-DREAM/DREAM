package dream.part.pur.buy.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.part.pur.buy.dao.LovPartPurBuyPnListDAO;
import dream.part.pur.buy.dto.LovPartPurBuyPnListDTO;
import dream.part.pur.buy.form.LovPartPurBuyPnListForm;
import dream.part.pur.buy.service.LovPartPurBuyPnListService;

/**
 * 현장신청부품 선택 Lov ServiceImpl
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovPartPurBuyPnListServiceTarget"
 * @spring.txbn id="lovPartPurBuyPnListService"
 * @spring.property name="lovPartPurBuyPnListDAO" ref="lovPartPurBuyPnListDAO"
 */
public class LovPartPurBuyPnListServiceImpl implements LovPartPurBuyPnListService
{
    /** 현장신청부품 선택 Lov DAO */
    private LovPartPurBuyPnListDAO lovPartPurBuyPnListDAO = null;

    public LovPartPurBuyPnListDAO getLovPartPurBuyPnListDAO() {
		return lovPartPurBuyPnListDAO;
	}

	public void setLovPartPurBuyPnListDAO(LovPartPurBuyPnListDAO lovPartPurBuyPnListDAO) {
		this.lovPartPurBuyPnListDAO = lovPartPurBuyPnListDAO;
	}
	
	public List findAcList(LovPartPurBuyPnListForm lovPartPurBuyPnListForm, User user)
    {
		LovPartPurBuyPnListDTO lovPartPurBuyPnListDTO = lovPartPurBuyPnListForm.getLovPartPurBuyPnListDTO();
		
		String code         = lovPartPurBuyPnListForm.getCode();    //Search Value
        String keyCode      = lovPartPurBuyPnListForm.getKeyCode();  //Search Column
        String codeType     = lovPartPurBuyPnListForm.getCodeType(); //Table
        String jsonParam    = lovPartPurBuyPnListForm.getParam();  //Condition
        String jaonCol      = lovPartPurBuyPnListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);

        return lovPartPurBuyPnListDAO.findAcList(lovPartPurBuyPnListDTO, user, columnMap ,conditionMap);
    }
	
	public String findTotalCount(LovPartPurBuyPnListForm lovPartPurBuyPnListForm, User user)  throws Exception
    {
	    LovPartPurBuyPnListDTO lovPartPurBuyPnListDTO = lovPartPurBuyPnListForm.getLovPartPurBuyPnListDTO();
	    
		lovPartPurBuyPnListDTO.setPtPnListStatus("C");
	    
	    String code         = lovPartPurBuyPnListForm.getCode();    //Search Value
        String keyCode      = lovPartPurBuyPnListForm.getKeyCode();  //Search Column
        String codeType     = lovPartPurBuyPnListForm.getCodeType(); //Table
        String jsonParam    = lovPartPurBuyPnListForm.getParam();  //Condition
        String jaonCol      = lovPartPurBuyPnListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
	    
        return lovPartPurBuyPnListDAO.findTotalCount(lovPartPurBuyPnListDTO, user, columnMap ,conditionMap);
    }
}