package dream.consult.consult.user.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.consult.consult.user.dao.McUserPwDAO;
import dream.consult.consult.user.dto.McUserCommonDTO;
import dream.consult.consult.user.dto.McUserPwDTO;


/**
 * 비밀번호설정 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="mcUserPwDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class McUserPwDAOOraImpl extends BaseJdbcDaoSupportOra implements McUserPwDAO
{
    /**
     * detail find
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param mcUserCommonDTO
     * @param loginUser
     * @return
     */
    public McUserPwDTO findDetail(McUserCommonDTO mcUserCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT												");
        query.append("       x.ehuser_id			userId,			        ");
        query.append("       x.ehuser_no			userNo,				    ");
        query.append("       x.euser_name		userName,					");
        query.append("       x.password			                            ");
        query.append("FROM   TAEHUSER x										");
        query.append("WHERE  1=1                                            ");
        query.getAndQuery("x.ehuser_id", mcUserCommonDTO.getUserId());
    
        McUserPwDTO mcUserPwDTO = 
        		(McUserPwDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new McUserPwDTO()));
        
        return mcUserPwDTO;
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param mcUserPwDTO
     * @param loginUser
     * @return
     */
    public int updateDetail(McUserPwDTO mcUserPwDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAEHUSER  SET	     ");
    	query.append("	     password	  = ?    ");
    	query.append("WHERE  ehuser_id 	  = ?	 ");

    	Object[] objects = new Object[] {
    			CommonUtil.aesEncodeString(mcUserPwDTO.getPassword()),
    			mcUserPwDTO.getUserId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}