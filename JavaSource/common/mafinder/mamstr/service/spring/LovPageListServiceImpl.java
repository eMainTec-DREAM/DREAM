package common.mafinder.mamstr.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import common.mafinder.mamstr.dao.LovPageListDAO;
import common.mafinder.mamstr.dto.LovPageListDTO;
import common.mafinder.mamstr.form.LovPageListForm;
import common.mafinder.mamstr.service.LovPageListService;

/**
 * ÆäÀÌÁöÆË¾÷ ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovPageListServiceTarget"
 * @spring.txbn id="lovPageListService"
 * @spring.property name="lovPageListDAO" ref="lovPageListDAO"
 */
public class LovPageListServiceImpl implements LovPageListService
{
    /** ÆäÀÌÁö ÆË¾÷ DAO */
    private LovPageListDAO lovPageListDAO = null;
    public LovPageListDAO getLovPageListDAO() 
    {
		return lovPageListDAO;
	}

	public void setLovPageListDAO(LovPageListDAO lovPageListDAO) 
	{
		this.lovPageListDAO = lovPageListDAO;
	}

	public List findPageList(LovPageListDTO lovPageListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovPageListDAO.findPageList(lovPageListDTO,loginUser);
        
        return resultList;
    }

    @Override
    public List findPageAcList(LovPageListDTO lovPageListDTO, User user,
            LovPageListForm lovPageListForm)
    {
        String code         = lovPageListForm.getCode();    //Search Value
        String keyCode      = lovPageListForm.getKeyCode();  //Search Column
        String codeType     = lovPageListForm.getCodeType(); //Table
        String jsonParam    = lovPageListForm.getParam();  //Condition
        String jaonCol      = lovPageListForm.getResultCol();  //Result Column


        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type); // <maPtMstrList.partNo:part_no>

        List resultList = null;
        resultList = lovPageListDAO.findDeptAcList(lovPageListDTO, user, columnMap ,conditionMap);
        
        return resultList;
    }

	@Override
	public String findTotalCount(LovPageListForm lovPageListForm, User loginUser) {
		
		LovPageListDTO lovPageListDTO = lovPageListForm.getLovPageListDTO();
        String code         = lovPageListForm.getCode();    //Search Value
        String keyCode      = lovPageListForm.getKeyCode();  //Search Column
        String codeType     = lovPageListForm.getCodeType(); //Table
        String jsonParam    = lovPageListForm.getParam();  //Condition
        String jaonCol      = lovPageListForm.getResultCol();  //Result Column

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type); 

        return lovPageListDAO.findTotalCount(lovPageListDTO, loginUser, columnMap, conditionMap);
        
	}
}