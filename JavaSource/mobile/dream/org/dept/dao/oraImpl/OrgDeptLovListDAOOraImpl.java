package mobile.dream.org.dept.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
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
public class OrgDeptLovListDAOOraImpl extends BaseJdbcDaoSupportOra implements OrgDeptLovListDAO
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
        QueryBuffer query = new QueryBuffer();
        String[] cols = {"description","dept_no"};
        
        query.append("SELECT													");
        query.append("       dept_id                AS id,						");
        query.append("       dept_id                AS deptId,					");
        query.append("       p_dept_id              AS pDeptId,					");
        query.append("       dept_no                AS deptNo,					");
        query.append("		 ''                     AS seqNo,                   ");
        query.append("       description            AS deptDesc,				");
//        query.append("       MIN(LEVEL) OVER( ORDER BY ord_no) AS minLvl,		");
        query.append("       LEVEL,												");
        query.append("       dept_categ deptCateg,								");
        query.append("       is_lowest_lvl isLowestLvl							");
        query.append("FROM   TADEPT												");
        query.append("WHERE 1=1													");
        query.append("    and is_use = 'Y'  									");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getAndQuery("dept_categ", orgDeptLovListDTO.getCodeType());
        query.getAndQuery("dept_no", orgDeptLovListDTO.getDeptNo());
        query.getLikeQuery("description", orgDeptLovListDTO.getDeptDesc());
        query.getLikeQuery(cols, orgDeptLovListDTO.getSearchText());
        
//        String deptStr = "0";
//        String deptCol = "p_dept_id";
//        if(orgDeptLovListDTO.getDeptNo()==null||"".equals(orgDeptLovListDTO.getDeptNo())){
//        	if(orgDeptLovListDTO.getDeptDesc()!=null&&!"".equals(orgDeptLovListDTO.getDeptDesc())){
//        		deptStr = orgDeptLovListDTO.getDeptDesc();
//            	deptCol = "description";
//        	}
//        }else{
//        	deptStr = orgDeptLovListDTO.getDeptNo();
//        	deptCol = "dept_no";
//        }
        
        query.append(" START WITH p_dept_id = 0									");
        query.append(" CONNECT BY PRIOR dept_id = p_dept_id						");
//        query.append(" order by dept_no                     					");
        query.append(" ORDER SIBLINGS by dept_no DESC 		");

        return getJdbcTemplate().queryForList(query.toString());
    }
}