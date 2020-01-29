package dream.mgr.usrdata.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.usrdata.dao.McDataSelectListDAO;
import dream.mgr.usrdata.dto.McDataSelectCommonDTO;


/**
 * 메뉴 - 목록 dao
 * @author  kim21017
 * @version $Id: McDataSelectListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="mcDataSelectListDAOTarget"
 * @spring.txbn id="mcDataSelectListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class McDataSelectListDAOOraImpl extends BaseJdbcDaoSupportOra implements McDataSelectListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: McDataSelectListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param mcDataSelectCommonDTO
     * @return List
     */
    public List findMenuList(McDataSelectCommonDTO mcDataSelectCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        String lang = loginUser.getLangId();

        query.append("SELECT                                                    ");
        query.append("       '' seqNo,                                          ");
        query.append("       '' isDelCheck,                                   	");
        query.append("       SFACODE_TO_DESC(x.usrdata_type,'USRDATA_TYPE','SYS','','"+lang+"')    usrdataType,	");
        query.append("       title,												");
        query.append("       description,										");
        query.append("       cre_date creDate,									");
        query.append("       (SELECT description								");
        query.append("        FROM   TADEPT a									");
        query.append("        WHERE  a.dept_id = x.dept_id) creDept,			");
        query.append("       (SELECT emp_name									");
        query.append("        FROM   TAEMP										");
        query.append("        WHERE  emp_id = x.cre_id) creBy,					");
        query.append("       usrdata_id usrrptId									");
        query.append("FROM   TAUSRDATA x											");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(mcDataSelectCommonDTO, loginUser));
        query.append("ORDER by usrdata_id DESC             						");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: McDataSelectListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param mcDataSelectCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(McDataSelectCommonDTO mcDataSelectCommonDTO, User loginUser)
    {        
        QueryBuffer query = new QueryBuffer();
        query.getLikeQuery("x.title", mcDataSelectCommonDTO.getTitle());
        
        // CommonDTO의 wkorId가 있는경우는 상세에서 수정 되어서 List의 한 Row만을 재조회
        if (!"".equals(mcDataSelectCommonDTO.getUsrrptId()))
        {
            query.getAndQuery("x.usrdata_id", mcDataSelectCommonDTO.getUsrrptId());
            return query.toString();
        }
        
        if (!"".equals(mcDataSelectCommonDTO.getCreBy()))
        {
            query.getAndQuery("x.cre_id", mcDataSelectCommonDTO.getCreBy());
            return query.toString();
        }
        
        if(!"".equals(mcDataSelectCommonDTO.getCreByDesc())&&mcDataSelectCommonDTO.getCreByDesc()!=null){
        	query.append("AND cre_id IN	( SELECT user_id						");
        	query.append("				  FROM TAUSER x, TAEMP y				");
        	query.append("				  WHERE x.emp_id = y.emp_id				");
        	query.getLikeQuery("emp_name", mcDataSelectCommonDTO.getCreByDesc());
        	query.append("				)										");
        }
        
        query.getSysCdQuery("x.usrdata_type", mcDataSelectCommonDTO.getUsrdataType(), mcDataSelectCommonDTO.getUsrdataTypeDesc(), "USRDATA_TYPE", loginUser.getCompNo(),loginUser.getLangId());
        
        query.getAndDateQuery("x.cre_date", mcDataSelectCommonDTO.getCreDateFrom(), mcDataSelectCommonDTO.getCreDateTo());
		
        return query.toString();

    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: McDataSelectListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteMenu(String id)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAUSRDATA				    ");
    	query.append("WHERE usrdata_id = '"+id+"'			");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }

	@Override
	public String findTotalCount(McDataSelectCommonDTO mcDataSelectCommonDTO, User user) throws Exception
	{
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                           				");
        query.append("FROM   TAUSRDATA x										");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(mcDataSelectCommonDTO, user));
        query.append("ORDER by usrdata_id DESC             						");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}