package dream.consult.consult.user.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.consult.consult.user.dao.McUserDetailDAO;
import dream.consult.consult.user.dto.McUserCommonDTO;
import dream.consult.consult.user.dto.McUserDetailDTO;

/**
 * 사용자 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="mcUserDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class McUserDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements McUserDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0 
     * 
     * @param userId
     * @return
     */
    public McUserDetailDTO findDetail(McUserCommonDTO mcUserCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                        ");
        query.append("       x.ehuser_id                                userId,     ");
        query.append("       x.ehuser_no                                userNo,     ");
        query.append("       x.euser_name                            userName,      ");
        query.append("       x.is_use                                    isUse,     ");
        query.append("       SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', '','"+loginUser.getLangId()+"') isUseDesc         ");
        query.append("FROM   TAEHUSER x                                             ");
        query.append("WHERE  1=1                                                    ");
        query.getAndQuery("x.ehuser_id", mcUserCommonDTO.getUserId());
    
        McUserDetailDTO mcUserDetailDTO = 
        		(McUserDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new McUserDetailDTO()));
        
        return mcUserDetailDTO;
    }
    
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param mcUserDetailDTO
     * @return
     */
    public int insertDetail(McUserDetailDTO mcUserDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAEHUSER(					                    ");
    	query.append("   ehuser_id,   ehuser_no,       euser_name,              ");
    	query.append("	 password,    is_use                                    ");
    	query.append(")VALUES(							                        ");
    	query.append("	 ?,			  ?,		       ?,	                    ");
    	query.append("	 ?,			  ?		                                    ");
    	query.append(")													        ");

    	Object[] objects = new Object[] {
    			mcUserDetailDTO.getUserId(),
    			mcUserDetailDTO.getUserNo(),
    			mcUserDetailDTO.getUserName(),
    			// 초기 password 세팅 
    			CommonUtil.aesEncodeString(mcUserDetailDTO.getUserNo()),
    			mcUserDetailDTO.getIsUse()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param mcUserDetailDTO
     * @return
     */
    public int updateDetail(McUserDetailDTO mcUserDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAEHUSER SET	        ");
    	query.append("	     ehuser_no      = ?,	");
    	query.append("	     euser_name	    = ?,	");
    	query.append("	     is_use         = ? 	");
    	query.append("WHERE  1 = 1	                ");
    	query.append("  AND  ehuser_id      = ?     ");
    	
    	Object[] objects = new Object[] {
    			mcUserDetailDTO.getUserNo(),
    			mcUserDetailDTO.getUserName(),
    			mcUserDetailDTO.getIsUse(),
    			mcUserDetailDTO.getUserId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * valid userId
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param mcUserDetailDTO
     * @return
     */
    public String validUserNo(McUserDetailDTO mcUserDetailDTO, User loginUser)
    {
        
    	QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*)                           ");
        query.append("FROM   TAEHUSER x                         ");
        query.append("WHERE  1=1                                ");
        query.getAndQuery("x.ehuser_no", mcUserDetailDTO.getUserNo());
     
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    
    }
}