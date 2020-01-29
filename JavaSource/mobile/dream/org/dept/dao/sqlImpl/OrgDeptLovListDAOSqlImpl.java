package mobile.dream.org.dept.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import mobile.dream.org.dept.dao.OrgDeptLovListDAO;
import mobile.dream.org.dept.dto.OrgDeptLovListDTO;

/**
 * 부서검색 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="orgDeptLovListDAOTarget"
 * @spring.txbn id="orgDeptLovListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class OrgDeptLovListDAOSqlImpl extends BaseJdbcDaoSupportSql implements OrgDeptLovListDAO
{
    /**
     * 자재 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param orgDeptLovListDTO
     * @param loginUser
     * @return
     */
    public List findDeptList(OrgDeptLovListDTO orgDeptLovListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT												");
        query.append("       x.dept_id              AS ID,					");
        query.append("       x.dept_id              AS DEPTID,				");
        query.append("       x.p_dept_id            AS PDEPTID,				");
        query.append("       x.dept_no              AS DEPTNO,				");
        query.append("		 ''                     AS SEQNO,               ");
        query.append("       x.description          AS DEPTDESC,			");
        query.append("       MIN(y.LVL) OVER( ORDER BY ISNULL(x.ord_no, '99999999')) AS MINLVL,		");
        query.append("       y.LVL 					AS LEVEL,				");
        query.append("       x.dept_categ 			AS DEPTCATEG,			");
        query.append("       x.is_lowest_lvl 		AS ISLOWESTLVL			");
        query.append("FROM   TADEPT	x										");
        query.append("		 ,(SELECT * FROM dbo.SFADEPT_ALL('"+loginUser.getCompNo()+"','0')) y	");
        query.append("WHERE 1=1												");
        query.append("AND 	x.dept_id = y.dept_id							");
        query.append("AND 	x.is_use = 'Y'  								");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.dept_categ", orgDeptLovListDTO.getCodeType());
        query.getAndQuery("x.dept_no", orgDeptLovListDTO.getDeptNo());
        query.getLikeQuery("x.description", orgDeptLovListDTO.getDeptDesc());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}