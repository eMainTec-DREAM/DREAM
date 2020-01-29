package dream.mgr.cccd.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mgr.cccd.dao.LovCtCtrListDAO;
import dream.mgr.cccd.dto.LovCtCtrListDTO;

/**
 * CP검색 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovCtCtrListDAOTarget"
 * @spring.txbn id="lovCtCtrListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovCtCtrListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovCtCtrListDAO
{
    /**
     * CP 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovCtCtrListDTO
     * @return
     */
    public List findCtCtrList(LovCtCtrListDTO lovCtCtrListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT												");
        query.append("		x.ctctr_id		AS ctctrId,						");
        query.append("		x.ctctr_no		AS ctctrNo,						");
        query.append("		x.description		AS ctctrDesc					");
        query.append("    ,(SELECT a.description FROM TADEPT a WHERE a.comp_no=x.comp_no AND a.dept_id=x.dept_id) deptDesc ");
        query.append("FROM TACTCTR x											");
        query.append("WHERE 1=1												");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.ctctr_no", lovCtCtrListDTO.getCtCtrNo());
        query.getLikeQuery("x.description", lovCtCtrListDTO.getCtCtrDesc());
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+loginUser.getCompNo()+"' AND a.plant = x.plant )", 
        		lovCtCtrListDTO.getPlantId(), lovCtCtrListDTO.getPlantDesc());
        
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    @Override
    public List findCtCtrACList(LovCtCtrListDTO lovCtCtrListDTO, User user, Map<String, String> conditionMap)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                															");
        query.append("      x.ctctr_id                                          														");
        query.append("      ,x.ctctr_no                                        															");
        query.append("      ,x.description                                     															");
        query.append("    ,(SELECT a.description FROM TADEPT a WHERE a.comp_no=x.comp_no AND a.dept_id=x.dept_id) 			deptDesc	");
        query.append("    , x.in_wcode_id        																			inWcodeId	");
        query.append("    ,(select a.wname from TAWAREHOUSE a where a.comp_no = x.comp_no and a.wcode_id = x.in_wcode_id)	inWcodeDesc	");
        query.append("FROM TACTCTR x                                     																");
        query.append("WHERE 1=1                                           																");
        query.getAndQuery("x.comp_no", conditionMap);
        query.getAndQuery("x.plant", conditionMap);
        query.getAndQuery("x.is_use", conditionMap);
        query.getAndQuery("x.in_wcode_id", conditionMap);
        query.getLikeQuery("x.ctctr_no", lovCtCtrListDTO.getCtCtrNo());
        query.getLikeQuery("x.description", lovCtCtrListDTO.getCtCtrDesc());
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", 
        		lovCtCtrListDTO.getPlantId(), lovCtCtrListDTO.getPlantDesc());
        //query.append("ORDER BY x.ctctr_no                              ");
        query.getOrderByQuery("x.ctctr_id","x.ctctr_no", lovCtCtrListDTO.getOrderBy(), lovCtCtrListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovCtCtrListDTO.getIsLoadMaxCount(), lovCtCtrListDTO.getFirstRow()));
    }

	@Override
	public String findTotalCount(LovCtCtrListDTO lovCtCtrListDTO, User user, Map<String, String> conditionMap)
	{
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                     	");
        query.append("      COUNT(1)								");
        query.append("FROM TACTCTR x                            	");
        query.append("WHERE 1=1                                    	");
        query.getAndQuery("x.comp_no", conditionMap);
        query.getAndQuery("x.plant", conditionMap);
        query.getAndQuery("x.is_use", conditionMap);
        query.getAndQuery("x.in_wcode_id", conditionMap);
        query.getLikeQuery("x.ctctr_no", lovCtCtrListDTO.getCtCtrNo());
        query.getLikeQuery("x.description", lovCtCtrListDTO.getCtCtrDesc());
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", lovCtCtrListDTO.getPlantId(), lovCtCtrListDTO.getPlantDesc());
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
		return QuerySqlBuffer.listToString(resultList);
	}
}