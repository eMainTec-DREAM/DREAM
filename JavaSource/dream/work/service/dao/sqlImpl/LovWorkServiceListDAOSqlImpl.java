package dream.work.service.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.service.dao.LovWorkServiceListDAO;
import dream.work.service.dto.LovWorkServiceListDTO;

/**
 * 서비스 LOV - List DAO implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="lovWorkServiceListDAOTarget"
 * @spring.txbn id="lovWorkServiceListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovWorkServiceListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovWorkServiceListDAO
{
	public List findList(LovWorkServiceListDTO lovWorkServiceListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT                                            													");
        query.append("	a.service_id     											  					  AS serviceId      ");
        query.append("	,a.service_no 												  					  AS serviceNo      ");
        query.append("	,a.description 												  					  AS serviceName    ");
        query.append("	,dbo.SFACODE_TO_DESC(a.service_uom,'SERVICE_UOM', 'SYS', '',  '"+user.getLangId()+"') AS serviceUom ");
        query.append("	,a.reg_date      											  					  AS regDate        ");
        query.append("	,(SELECT  description FROM tadept x        															");
        query.append("	  WHERE x.comp_no = a.comp_no AND x.dept_id =a.dept_id) 		  				  AS deptDesc       ");
        query.append("	,(SELECT  emp_name FROM taemp y        																");
        query.append("	  WHERE y.comp_no = a.comp_no AND y.emp_id =a.emp_id) 			  				  AS empDesc        ");
        query.append("	,dbo.SFACODE_TO_DESC(a.is_use,'IS_USE','SYS','','"+user.getLangId()+"') 		  AS isUse          ");
        query.append("	,a.remark 													  					  AS remark         ");
        query.append("FROM taservice a        																				");
        query.append("WHERE  1 = 1                                      ");
        query.append(this.getWhere(lovWorkServiceListDTO, user, columnMap, conditionMap));
        query.getOrderByQuery("a.service_no", lovWorkServiceListDTO.getOrderBy(), lovWorkServiceListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovWorkServiceListDTO.getIsLoadMaxCount(), lovWorkServiceListDTO.getFirstRow()));
    } 

	private String getWhere(LovWorkServiceListDTO lovWorkServiceListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.getAndQuery("a.comp_no", user.getCompNo());

        // 등록일자
        query.getAndDateQuery("a.reg_date", lovWorkServiceListDTO.getFilterFromRegDate(), lovWorkServiceListDTO.getFilterToRegDate());
        // 작성자 
        query.getCodeLikeQuery("a.emp_id", "(SELECT  emp_name FROM taemp y WHERE y.comp_no = a.comp_no AND y.emp_id =a.emp_id)", 
        		lovWorkServiceListDTO.getFilterEmpId(), lovWorkServiceListDTO.getFilterEmpDesc());
        // 서비스명 
        query.getLikeQuery("a.description", lovWorkServiceListDTO.getFilterDescription());
        // 서비스 #
        query.getLikeQuery("a.service_no", lovWorkServiceListDTO.getFilterServiceNo());

    	return query.toString();
    }

    public String findTotalCount(LovWorkServiceListDTO lovWorkServiceListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                        ");
        query.append("      COUNT(1)                ");
        query.append("FROM taservice a	            ");
        query.append("WHERE  1=1                    ");
        query.append(this.getWhere(lovWorkServiceListDTO, user, columnMap, conditionMap));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}