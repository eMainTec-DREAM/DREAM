package mobile.dream.org.emp.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import mobile.dream.org.emp.dao.OrgEmpLovListDAO;
import mobile.dream.org.emp.dto.OrgEmpLovListDTO;

/**
 * 사원검색 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="orgEmpLovListDAOTarget"
 * @spring.txbn id="orgEmpLovListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class OrgEmpLovListDAOOraImpl extends BaseJdbcDaoSupportOra implements OrgEmpLovListDAO
{
    /**
     * 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param orgEmpLovListDTO
     * @param loginUser
     * @return
     */
    public List findEmpList(OrgEmpLovListDTO orgEmpLovListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       emp_id                AS empId,                    ");
        query.append("       emp_no                AS empNo,                    ");
        query.append("       emp_name              AS empName,                  ");
        query.append("       (SELECT description                                ");
        query.append("          FROM TADEPT                                     ");
        query.append("         WHERE comp_no = x.comp_no                        ");
        query.append("           AND dept_id = x.dept_id) AS deptDesc           ");
        query.append("FROM   TAEMP x							                ");
        query.append("WHERE 1=1									                ");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getLikeQuery("emp_no", orgEmpLovListDTO.getEmpNo());
        query.getLikeQuery("emp_name", orgEmpLovListDTO.getEmpName());
      //부서
        query.getDeptLevelQuery("x.dept_id", orgEmpLovListDTO.getDeptId(), orgEmpLovListDTO.getDeptDesc(), loginUser.getCompNo());

        query.append("ORDER by emp_id                                           ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}