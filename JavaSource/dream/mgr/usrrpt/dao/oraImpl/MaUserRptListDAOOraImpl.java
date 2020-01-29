package dream.mgr.usrrpt.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.usrrpt.dao.MaUserRptListDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;


/**
 * 메뉴 - 목록 dao
 * @author  kim21017
 * @version $Id: MaUserRptListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maUserRptListDAOTarget"
 * @spring.txbn id="maUserRptListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaUserRptListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaUserRptListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaUserRptListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maUserRptCommonDTO
     * @return List
     */
    public List findMenuList(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        String lang = loginUser.getLangId();

        query.append("SELECT                                                        ");
        query.append("       '' seqNo,                                          	");
        query.append("       '' isDelCheck,                                       	");
        query.append("       SFACODE_TO_DESC(x.usrdata_type,'USRDATA_TYPE','SYS','','"+lang+"') usrdataType,    	");
        query.append("       title,                                                	");
        query.append("       description,                                        	");
        query.append("       cre_date creDate,                                    	");
        query.append("       (SELECT description                                	");
        query.append("        FROM   TADEPT a                                    	");
        query.append("        WHERE  a.dept_id = x.dept_id) creDept,            	");
        query.append("       (SELECT user_name                                    	");
        query.append("        FROM   TAUSER                                        	");
        query.append("        WHERE  user_id = x.cre_id) creBy,                    	");
        query.append("       usrrptlist_id usrrptlistId                             ");
        query.append("FROM   TAUSRRPTLIST x                                         ");
        query.append("WHERE  1=1                                                	");
        query.append(this.getWhere(maUserRptCommonDTO, loginUser));
        query.append("ORDER by usrrptlist_id DESC             						");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaUserRptListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maUserRptCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser)
    {        
        QueryBuffer query = new QueryBuffer();
        query.getLikeQuery("x.title", maUserRptCommonDTO.getTitle());
        
        // CommonDTO의 wkorId가 있는경우는 상세에서 수정 되어서 List의 한 Row만을 재조회
        if (!"".equals(maUserRptCommonDTO.getUsrrptlistId()))
        {
            query.getAndQuery("x.usrrptlist_id", maUserRptCommonDTO.getUsrrptlistId());
            return query.toString();
        }
        
        if (!"".equals(maUserRptCommonDTO.getCreBy()))
        {
            query.getAndQuery("x.cre_id", maUserRptCommonDTO.getCreBy());
            return query.toString();
        }
        
        if(!"".equals(maUserRptCommonDTO.getCreByDesc())&&maUserRptCommonDTO.getCreByDesc()!=null){
        	query.append("AND cre_id IN	( SELECT user_id						");
        	query.append("				  FROM TAUSER x, TAEMP y				");
        	query.append("				  WHERE x.emp_id = y.emp_id				");
        	query.getLikeQuery("emp_name", maUserRptCommonDTO.getCreByDesc());
        	query.append("				)										");
        }
        
        query.getSysCdQuery("x.usrdata_type", maUserRptCommonDTO.getUsrdataType(), maUserRptCommonDTO.getUsrdataTypeDesc(), "USRDATA_TYPE", loginUser.getCompNo(),loginUser.getLangId());
        
        
        query.getAndDateQuery("x.cre_date", maUserRptCommonDTO.getCreDateFrom(), maUserRptCommonDTO.getCreDateTo());
		
        return query.toString();

    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaUserRptListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteRpt(String id)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAUSRRPTLIST				    ");
    	query.append("WHERE usrrptlist_id = '"+id+"'			");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }

	@Override
	public int deleteTable(String id) {
		
		QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAUSRRPTTAB				    ");
    	query.append("WHERE usrrptlist_id = '"+id+"'			");
    	
    	return this.getJdbcTemplate().update(query.toString());
	}

	@Override
	public int deleteJoin(String id) {
		QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAUSRRPTJOIN				    ");
    	query.append("WHERE usrrptlist_id = '"+id+"'			");
    	
    	return this.getJdbcTemplate().update(query.toString());
	}

	@Override
	public int deleteCol(String id) {
		QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAUSRRPTCOL				    ");
    	query.append("WHERE usrrptlist_id = '"+id+"'			");
    	
    	return this.getJdbcTemplate().update(query.toString());
	}

	@Override
	public int deleteParam(String id) {
		QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAUSRRPTWHR				    ");
    	query.append("WHERE usrrptlist_id = '"+id+"'			");
    	
    	return this.getJdbcTemplate().update(query.toString());
	}

	@Override
	public int deleteOrd(String id) {
		QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAUSRRPTORD				    ");
    	query.append("WHERE usrrptlist_id = '"+id+"'			");
    	
    	return this.getJdbcTemplate().update(query.toString());
	}

	@Override
	public String findTotalCount(MaUserRptCommonDTO maUserRptCommonDTO, User user) throws Exception
	{
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                        ");
        query.append("       COUNT(1)                           					");
        query.append("FROM   TAUSRRPTLIST x                                         ");
        query.append("WHERE  1=1                                                	");
        query.append(this.getWhere(maUserRptCommonDTO, user));
        query.append("ORDER by usrrptlist_id DESC             						");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}