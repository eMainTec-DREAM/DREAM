package dream.org.emp.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.org.emp.dao.LovEmpListDAO;
import dream.org.emp.dto.LovEmpListDTO;

/**
 * 사원검색 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovEmpListDAOTarget"
 * @spring.txbn id="lovEmpListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovEmpListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovEmpListDAO
{
    /**
     * 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEmpListDTO
     * @param loginUser
     * @return
     */
    public List findEmpList(LovEmpListDTO lovEmpListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                            ");
        query.append("       emp_id                AS empId             ");
        query.append("       ,emp_no               AS empNo             ");
        query.append("       ,emp_name             AS empName           ");
        query.append("       ,dept_id              AS deptId            ");
        query.append("       ,(SELECT description                       ");
        query.append("          FROM TADEPT                             ");
        query.append("         WHERE comp_no = x.comp_no                ");
        query.append("           AND dept_id = x.dept_id) AS deptDesc	");
        query.append("       ,plant                 plant              ");
        query.append("FROM   TAEMP x							        ");
        query.append("WHERE 1=1									        ");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getLikeQuery("emp_no", lovEmpListDTO.getEmpNo());
        query.getLikeQuery("emp_name", lovEmpListDTO.getEmpName());
      //부서
        query.getDeptLevelQuery("x.dept_id", lovEmpListDTO.getDeptId(), lovEmpListDTO.getDeptDesc(), loginUser.getCompNo());

      //query.append("ORDER by emp_id                                   ");
        query.getOrderByQuery("x.emp_id","x.emp_id", lovEmpListDTO.getOrderBy(), lovEmpListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovEmpListDTO.getIsLoadMaxCount(), lovEmpListDTO.getFirstRow()));
    }

	@Override
	public List findEmpAcList(LovEmpListDTO lovEmpListDTO, User user, Map<String, String> conditionMap) {
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                            ");
        query.append("		 comp_no                 comp_no             ");
        query.append("		 ,(select y.description from tacomp y where y.comp_no = x.comp_no) compDesc             ");
        query.append("       ,emp_id                emp_id             	");
        query.append("       ,emp_no               emp_no             	");
        query.append("       ,emp_name             emp_name           	");
        query.append("       ,dept_id              dept_id            	");
        query.append("       ,grade                grade                ");
        query.append("       ,(SELECT description                       ");
        query.append("          FROM TADEPT                             ");
        query.append("         WHERE comp_no = x.comp_no                ");
        query.append("           AND dept_id = x.dept_id) AS deptDesc	");
        query.append("       ,m_phone               m_phone             ");
        query.append("       ,e_mail                e_mail              ");
        query.append("       ,plant                 plant              ");
        query.append("       ,(SELECT y.description FROM TAPLANT y WHERE y.plant = x.plant) plantDesc     ");
        query.append("       ,is_join               is_join             ");
        query.append("FROM   TAEMP x							        ");
        query.append("WHERE 1=1									        ");
        query.getAndQuery("x.comp_no", conditionMap);
        query.getAndQuery("x.plant", conditionMap);
        query.getAndQuery("x.dept_id", conditionMap);
        query.getAndQuery("x.is_join", conditionMap);

        if(conditionMap.containsKey("comp_no")) {
            query.getAndQuery("comp_no", conditionMap);
        }
        else {
            query.getAndQuery("comp_no", user.getCompNo());
        }
        
        query.getCodeLikeQuery("x.is_join","dbo.SFACODE_TO_DESC(x.is_join,'IS_USE','SYS','','"+user.getLangId()+"')", lovEmpListDTO.getIsJoinId(), lovEmpListDTO.getIsJoinDesc());;
        
        query.getLikeQuery("emp_no", lovEmpListDTO.getEmpNo());
        query.getLikeQuery("emp_name", lovEmpListDTO.getEmpName());
        //부서
        query.getDeptLevelQuery("x.dept_id", lovEmpListDTO.getDeptId(), lovEmpListDTO.getDeptDesc(), user.getCompNo());
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant )", 
                lovEmpListDTO.getPlantId(), lovEmpListDTO.getPlantDesc());
        
        query.getOrderByQuery("emp_id","emp_id", lovEmpListDTO.getOrderBy(), lovEmpListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovEmpListDTO.getIsLoadMaxCount(), lovEmpListDTO.getFirstRow()));
        
	}
	
	public String findTotalCount(LovEmpListDTO lovEmpListDTO, User user, Map<String, String> columnMap,
            Map<String, String> conditionMap)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                            ");
        query.append("       count(1)                                   ");
        query.append("FROM   TAEMP x							        ");
        query.append("WHERE 1=1									        ");
        query.getAndQuery("x.comp_no", conditionMap);
        query.getAndQuery("x.plant", conditionMap);
        query.getAndQuery("x.dept_id", conditionMap);
        query.getAndQuery("x.is_join", conditionMap);

        if(conditionMap.containsKey("comp_no")) {
            query.getAndQuery("comp_no", conditionMap);
        }
        else {
            query.getAndQuery("comp_no", user.getCompNo());
        }
       
        query.getCodeLikeQuery("x.is_join","dbo.SFACODE_TO_DESC(x.is_join,'IS_USE','SYS','','"+user.getLangId()+"')", lovEmpListDTO.getIsJoinId(), lovEmpListDTO.getIsJoinDesc());;
        
        query.getLikeQuery("emp_no", lovEmpListDTO.getEmpNo());
        query.getLikeQuery("emp_name", lovEmpListDTO.getEmpName());
        //부서
        query.getDeptLevelQuery("x.dept_id", lovEmpListDTO.getDeptId(), lovEmpListDTO.getDeptDesc(), user.getCompNo());
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant )", 
                lovEmpListDTO.getPlantId(), lovEmpListDTO.getPlantDesc());
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}