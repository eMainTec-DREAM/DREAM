package dream.consult.program.table.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.consult.program.table.dao.TableValLovDAO;
import dream.consult.program.table.dto.TableValLovDTO;
import dream.consult.program.table.form.TableValLovForm;
import dream.consult.program.table.service.TableValLovService;

/**
 * Å×ÀÌºí LOV - List Service implements
 * @author kim21017
 * @version $Id: TableValLovServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="tableValLovServiceTarget"
 * @spring.txbn id="tableValLovService"
 * @spring.property name="tableValLovDAO" ref="tableValLovDAO"
 */
public class TableValLovServiceImpl implements TableValLovService
{
	private TableValLovDAO tableValLovDAO = null;

	public List findList(TableValLovForm tableValLovForm, User user) throws Exception
    {      
		TableValLovDTO tableValLovDTO = tableValLovForm.getTableValLovDTO();
		
		String code         = tableValLovForm.getCode();    //Search Value
        String keyCode      = tableValLovForm.getKeyCode();  //Search Column
        String codeType     = tableValLovForm.getCodeType(); //Table
        String jsonParam    = tableValLovForm.getParam();  //Condition
        String jaonCol      = tableValLovForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);

		
        return tableValLovDAO.findList(tableValLovDTO,user, columnMap ,conditionMap);
    }

	public String findTotalCount(TableValLovForm tableValLovForm, User user) throws Exception
    {      
		TableValLovDTO tableValLovDTO = tableValLovForm.getTableValLovDTO();
		
		String code         = tableValLovForm.getCode();    //Search Value
        String keyCode      = tableValLovForm.getKeyCode();  //Search Column
        String codeType     = tableValLovForm.getCodeType(); //Table
        String jsonParam    = tableValLovForm.getParam();  //Condition
        String jaonCol      = tableValLovForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
        return tableValLovDAO.findTotalCount(tableValLovDTO,user, columnMap ,conditionMap);
    }

	public TableValLovDAO getTableValLovDAO() {
		return tableValLovDAO;
	}

	public void setTableValLovDAO(TableValLovDAO tableValLovDAO) {
		this.tableValLovDAO = tableValLovDAO;
	}
	
}

