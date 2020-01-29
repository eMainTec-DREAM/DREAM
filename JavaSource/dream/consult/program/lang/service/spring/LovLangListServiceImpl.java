package dream.consult.program.lang.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.consult.program.lang.dao.LovLangListDAO;
import dream.consult.program.lang.dto.LovLangListDTO;
import dream.consult.program.lang.form.LovLangListForm;
import dream.consult.program.lang.service.LovLangListService;

/**
 * ´Ù±¹¾îÆË¾÷ ServiceImpl
 * @author  kim21017
 * @version $Id: LovLangListServiceImpl.java,v 1.5 2015/01/09 00:16:42 kim21017 Exp $
 * @since   1.0
 *
 * @spring.bean id="lovLangListServiceTarget"
 * @spring.txbn id="lovLangListService"
 * @spring.property name="lovLangListDAO" ref="lovLangListDAO"
 */
public class LovLangListServiceImpl implements LovLangListService
{
    /** ´Ù±¹¾îÆË¾÷ DAO */
    private LovLangListDAO lovLangListDAO = null;

    public LovLangListDAO getLovLangListDAO() {
		return lovLangListDAO;
	}

	public void setLovLangListDAO(LovLangListDAO lovLangListDAO) {
		this.lovLangListDAO = lovLangListDAO;
	}

	public List findLangList(LovLangListDTO lovLangListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovLangListDAO.findLangList(lovLangListDTO,loginUser);
        
        return resultList;
    }
	public List findLangAcList(LovLangListForm lovLangListForm, User loginUser)
    {
		LovLangListDTO lovLangListDTO = lovLangListForm.getLovLangListDTO();

		String code         = lovLangListForm.getCode();    //Search Value
        String keyCode      = lovLangListForm.getKeyCode();  //Search Column
        String codeType     = lovLangListForm.getCodeType(); //Table
        String jsonParam    = lovLangListForm.getParam();  //Condition
        String jaonCol      = lovLangListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
        List resultList = null;
        resultList = lovLangListDAO.findLangAcList(lovLangListDTO,loginUser, columnMap ,conditionMap);
        
        return resultList;
    }

	@Override
	public String findTotalCount(LovLangListForm lovLangListForm, User loginUser) throws Exception {

		LovLangListDTO lovLangListDTO = lovLangListForm.getLovLangListDTO();
		String code         = lovLangListForm.getCode();    //Search Value
        String keyCode      = lovLangListForm.getKeyCode();  //Search Column
        String codeType     = lovLangListForm.getCodeType(); //Table
        String jsonParam    = lovLangListForm.getParam();  //Condition
        String jaonCol      = lovLangListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
        return lovLangListDAO.findTotalCount(lovLangListDTO,loginUser, columnMap ,conditionMap);
	}
}