package dream.mgr.user.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.user.dao.LovUserListDAO;
import dream.mgr.user.dto.LovUserListDTO;

/**
 * 사용자 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovUserListDAOTarget"
 * @spring.txbn id="lovUserListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovUserListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovUserListDAO
{
    /**
     * 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovUserListDTO
     * @param loginUser
     * @return
     */
    public List findUserList(LovUserListDTO lovUserListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       user_id                userId,                     ");
        query.append("       user_no                userNo,                     ");
        query.append("       user_name              userName,                   ");
        query.append("       e_mail                 email                       ");
        query.append("FROM   TAUSER         						            ");
        query.append("WHERE  1=1                                                ");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getAndQuery("user_no", lovUserListDTO.getUserNo());
        query.getLikeQuery("user_name", lovUserListDTO.getUserName());
        query.append("ORDER by user_id                                          ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }

	@Override
	public List findUserAcList(LovUserListDTO lovUserListDTO, User user, Map<String, String> conditionMap) {
		
		QueryBuffer query = new QueryBuffer();

		query.append("SELECT                                                    				");
		query.append("       y.emp_name 		emp_name										"); //Second will be For AC Object
		query.append("       ,x.user_id 		user_id											");
		query.append("       ,x.comp_no 		comp_no											");
		query.append("       ,x.user_no 		user_no											");
		query.append("       ,x.user_name 		user_name										");
		query.append("       ,x.e_mail 		    e_mail										    ");
		query.append("       ,y.emp_id 		    emp_id										    ");
		query.append("FROM   TAUSER x, TAEMP y													");
		query.append("WHERE  1 = 1                                              				");
		query.append("  AND  x.emp_id=y.emp_id													");
		query.append("  AND  x.is_use = 'Y'														");
        query.getAndQuery("x.comp_no", conditionMap);
        if(conditionMap.containsKey("dept_id") && !"".equals(conditionMap.get("dept_id"))) {
            query.append("AND y.dept_id  IN (SELECT dept_id                                         ");
            query.append("                 FROM tadept                                              ");
            query.append("                 WHERE 1=1                                                ");
            query.getAndQuery("comp_no", conditionMap);
            query.append("                 START WITH dept_id ='"+conditionMap.get("dept_id")+"'  ");
            query.append("                 CONNECT BY PRIOR dept_id = p_dept_id)                    ");

        }
        query.getAndQuery("x.user_no", lovUserListDTO.getUserNo());
        query.getLikeQuery("x.user_name", lovUserListDTO.getUserName());
		query.append("ORDER BY  emp_name														");

        return getJdbcTemplate().queryForList(query.toString());
	}
}