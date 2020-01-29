package dream.consult.consult.user.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.consult.consult.user.dao.McUserListDAO;
import dream.consult.consult.user.dto.McUserCommonDTO;
import dream.consult.consult.user.dto.McUserListDTO;


/**
 * 사용자 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="mcUserListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class McUserListDAOSqlImpl extends BaseJdbcDaoSupportSql implements McUserListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param mcUserCommonDTO
     * @param user
     * @return List
     */
    public List findUserList(McUserCommonDTO mcUserCommonDTO, User loginUser)
    { 
        QuerySqlBuffer query = new QuerySqlBuffer(); 
       
        query.append("SELECT ''                                     seqNo,          ");
        query.append("       ''                                     isDelCheck,     ");
        query.append("       x.ehuser_id                              userId,       ");
        query.append("       x.ehuser_no                              userNo,       ");
        query.append("       x.euser_name                            userName,      ");
        query.append("       dbo.SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', '','"+loginUser.getLangId()+"') isUse         ");
        query.append("FROM   TAEHUSER x                                             ");
        query.append("WHERE  1 = 1                                                  ");
        query.append(this.getWhere(mcUserCommonDTO,loginUser));
        query.append("ORDER by ehuser_no                          			        ");
         
        return getJdbcTemplate().queryForList(query.toString());
    } 
    
    public int updateUsers(Map map)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE TAEHUSER SET             ");
        query.append("       password        = ?      ");
        query.append("WHERE  1 = 1                    ");
        query.append("  AND  ehuser_id       = ?     ");

    	
        Object[] objects = new Object[] {
        		CommonUtil.aesEncodeString(String.valueOf(map.get("userNo"))),
                String.valueOf(map.get("userId"))
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * Filter 조건
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param mcUserCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(McUserCommonDTO mcUserCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        if (!"".equals(mcUserCommonDTO.getUserId()))
        {
            query.getAndQuery("x.ehuser_id", mcUserCommonDTO.getUserId());
            return query.toString();
        }

        // 계정명
        query.getLikeQuery("x.euser_name",  mcUserCommonDTO.getFilterUserName());
        // 로그인계정
        query.getLikeQuery("x.ehuser_no",  mcUserCommonDTO.getFilterUserNo());

        return query.toString();
    }
    
    /**
     * 사용자 삭제
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param userId
     * @return
     */
    public int deleteUser(McUserListDTO mcUserListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE TAEHUSER                            ");
        query.append("WHERE  1 = 1                               ");
        query.append("  AND  ehuser_id  = ?                      ");

        Object[] objects = new Object[] {   
                mcUserListDTO.getUserId()
                };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}