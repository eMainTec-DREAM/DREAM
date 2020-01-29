package dream.consult.program.table.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.consult.program.table.dao.TableColValLovDAO;
import dream.consult.program.table.dto.TableColValLovDTO;
import dream.consult.program.table.form.TableColValLovForm;
import dream.consult.program.table.service.TableColValLovService;

/**
 * Å×ÀÌºí LOV - List Service implements
 * @author kim21017
 * @version $Id: TableColValLovServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="tableColValLovServiceTarget"
 * @spring.txbn id="tableColValLovService"
 * @spring.property name="tableColValLovDAO" ref="tableColValLovDAO"
 */
public class TableColValLovServiceImpl implements TableColValLovService
{
	private TableColValLovDAO tableColValLovDAO = null;

	public List findList(TableColValLovForm tableColValLovForm, User user) throws Exception
    {      
		TableColValLovDTO tableColValLovDTO = tableColValLovForm.getTableColValLovDTO();
		
		String code         = tableColValLovForm.getCode();    //Search Value
        String keyCode      = tableColValLovForm.getKeyCode();  //Search Column
        String codeType     = tableColValLovForm.getCodeType(); //TableCol
        String jsonParam    = tableColValLovForm.getParam();  //Condition
        String jaonCol      = tableColValLovForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);

		
        return tableColValLovDAO.findList(tableColValLovDTO,user, columnMap ,conditionMap);
    }

	public String findTotalCount(TableColValLovForm tableColValLovForm, User user) throws Exception
    {      
		TableColValLovDTO tableColValLovDTO = tableColValLovForm.getTableColValLovDTO();
		
		String code         = tableColValLovForm.getCode();    //Search Value
        String keyCode      = tableColValLovForm.getKeyCode();  //Search Column
        String codeType     = tableColValLovForm.getCodeType(); //TableCol
        String jsonParam    = tableColValLovForm.getParam();  //Condition
        String jaonCol      = tableColValLovForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
        return tableColValLovDAO.findTotalCount(tableColValLovDTO,user, columnMap ,conditionMap);
    }

	public TableColValLovDAO getTableColValLovDAO() {
		return tableColValLovDAO;
	}

	public void setTableColValLovDAO(TableColValLovDAO tableColValLovDAO) {
		this.tableColValLovDAO = tableColValLovDAO;
	}
	
}

