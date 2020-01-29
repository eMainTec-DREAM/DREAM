package dream.mgr.user.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.mgr.user.dao.MaUserPwDAO;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MaUserPwDTO;

/**
 * 비밀번호설정 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maUserPwDAOTarget"
 * @spring.txbn id="maUserPwDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaUserPwDAOOraImpl extends BaseJdbcDaoSupportOra implements MaUserPwDAO
{
    /**
     * detail find
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUserCommonDTO
     * @param loginUser
     * @return
     */
    public MaUserPwDTO findDetail(MaUserCommonDTO maUserCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT												");
        query.append("       x.user_id			userId,						");
        query.append("       x.user_no			userNo,						");
        query.append("       x.user_name		userName,					");
        query.append("       x.password			                            ");
        query.append("FROM   TAUSER x										");
        query.append("WHERE  1=1                                            ");
        query.getAndQuery("x.comp_no", maUserCommonDTO.getCompNo());
        query.getAndQuery("x.user_id", maUserCommonDTO.getUserId());
    
        MaUserPwDTO maUserPwDTO = 
        		(MaUserPwDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaUserPwDTO()));
        
        return maUserPwDTO;
    }
    /**
     * detail update
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUserPwDTO
     * @param loginUser
     * @return
     */
    public int updateDetail(MaUserPwDTO maUserPwDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAUSER  SET	     ");
    	query.append("	     password	  = ?    ");
    	query.append("WHERE  user_id 	  = ?	 ");
    	query.append("  AND  comp_no	  = ?	 ");

    	Object[] objects = new Object[] {
    			CommonUtil.aesEncodeString(maUserPwDTO.getPassword()),
    			maUserPwDTO.getUserId(),
    			maUserPwDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * insert pw change history
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUserPwDTO
     * @param loginUser
     * @return
     */
    public int insertPwChanHist(MaUserPwDTO maUserPwDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("INSERT INTO TAUSRPWCHANGEHIST (		   		");
    	query.append("    comp_no    	   , usrpwchangehist_id		");
    	query.append("	, user_id  		   , change_time	   		");
    	query.append("	, password  		   				   		");
    	query.append(")VALUES(							            ");
    	query.append("	  ?			       , sqausrpwchangehist_id.NEXTVAL	");
    	query.append("	, ?			       , ?		                ");
    	query.append("	, ?		                					");
    	query.append(")												");
    	
    	Object[] objects = new Object[] {
    			maUserPwDTO.getCompNo()
    			,maUserPwDTO.getUserId()
    			,DateUtil.getDateTime()
    			,CommonUtil.aesEncodeString(maUserPwDTO.getPassword())
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    public String checkPwHist(MaUserPwDTO maUserPwDTO, String pwChangeHistCnt)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT 								");
    	query.append("    COUNT(1) 							");
    	query.append("FROM (								");
    	query.append("    SELECT 							");
    	query.append("          row_number() OVER(ORDER BY x.usrpwchangehist_id DESC) rno		");
    	query.append("          ,x.PASSWORD					");
    	query.append("    FROM TAUSRPWCHANGEHIST x			");
    	query.append("    WHERE 1=1							");
    	query.append("    AND x.comp_no = ?					");
    	query.append("    AND x.user_id = ?					");
    	query.append("    ORDER BY usrpwchangehist_id DESC	");
    	query.append("    ) a								");
    	query.append("WHERE a.rno <= ?						");
    	query.append("AND a.PASSWORD = ?					");

    	Object[] objects = new Object[] {
    			maUserPwDTO.getCompNo()
    			,maUserPwDTO.getUserId()
    			,pwChangeHistCnt
    			,CommonUtil.aesEncodeString(maUserPwDTO.getPassword())
    	};
    	
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
    }
}