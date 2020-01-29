package dream.mgr.user.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
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
public class MaUserPwDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaUserPwDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("SELECT												");
        query.append("       x.user_id			USERID,						");
        query.append("       x.user_no			USERNO,						");
        query.append("       x.user_name		USERNAME,					");
        query.append("       x.password			PASSWORD                            ");
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
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAUSER  SET	     ");
    	query.append("	     password	  = ?    ");
    	query.append("WHERE  user_id 	  = ?	 ");
    	query.append("  AND  comp_no	  = ?	 ");
    	
    	Object[] objects = new Object[] {
    			CommonUtil.aesEncodeString(maUserPwDTO.getPassword()),
    			maUserPwDTO.getUserId(),
    			maUserPwDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
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
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("INSERT INTO TAUSRPWCHANGEHIST (		   		");
    	query.append("    comp_no    	   , usrpwchangehist_id		");
    	query.append("	, user_id  		   , change_time	   		");
    	query.append("	, password  		   				   		");
    	query.append(")VALUES(							            ");
    	query.append("	  ?			       , NEXT VALUE FOR sqausrpwchangehist_id	");
    	query.append("	, ?			       , ?		                ");
    	query.append("	, ?		                					");
    	query.append(")												");
    	
    	Object[] objects = new Object[] {
    			maUserPwDTO.getCompNo()
    			,maUserPwDTO.getUserId()
    			,DateUtil.getDateTime()
    			,CommonUtil.aesEncodeString(maUserPwDTO.getPassword())
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public String checkPwHist(MaUserPwDTO maUserPwDTO, String pwChangeHistCnt)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
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
    	query.append("    ) a								");
    	query.append("WHERE a.rno <= ?						");
    	query.append("AND a.PASSWORD = ?					");

    	Object[] objects = new Object[] {
    			maUserPwDTO.getCompNo()
    			,maUserPwDTO.getUserId()
    			,pwChangeHistCnt
    			,CommonUtil.aesEncodeString(maUserPwDTO.getPassword())
    	};
    	
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
    }
}