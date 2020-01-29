package dream.mgr.user.dao.oraImpl;

import java.util.List;
import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.user.dao.MgrUserPlantauthListDAO;
import dream.mgr.user.dto.MaUserCommonDTO;

/**
 * 사용자 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="mgrUserPlantauthListDAOTarget"
 * @spring.txbn id="mgrUserPlantauthListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrUserPlantauthListDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrUserPlantauthListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUserCommonDTO
     * @param user
     * @return List
     */
    public List findUserList(MaUserCommonDTO maUserCommonDTO, User loginUser)
    { 
        QueryBuffer query = new QueryBuffer(); 
       
        query.append("SELECT                                                    ");
        query.append("       ''                                     seqNo,      ");
        query.append("       ''                                     isDelCheck, ");
        query.append("       x.comp_no compNo                                   ");
        query.append("       ,usrplantauth_id usrplantauthId                    ");
        query.append("       ,user_id userId                                    ");
        query.append("       ,x.plant                                           ");
        query.append("       ,y.description plantDesc                           ");
        query.append("       ,x.is_auth isAuthId                                ");
        query.append("		 ,SFACODE_TO_DESC(x.is_auth,'IS_USE','SYS',x.comp_no, '"+loginUser.getLangId()+"') isAuth	");
        query.append("       ,REMARK                                            ");
        query.append("FROM   TAPLANT y left outer JOIN TAUSRPLANTAUTH x ON x.plant = y.plant AND x.comp_no = y.comp_no AND y.is_use = 'Y'       ");
        query.append("WHERE  1 = 1                                              ");
        query.append(this.getWhere(maUserCommonDTO,loginUser));
        query.getOrderByQuery("x.plant", maUserCommonDTO.getOrderBy(), maUserCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maUserCommonDTO.getIsLoadMaxCount(), maUserCommonDTO.getFirstRow()));
    } 
    public String findTotalCount(MaUserCommonDTO maUserCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("      count(1)                                            ");
        query.append("FROM   TAPLANT y left outer JOIN TAUSRPLANTAUTH x ON x.plant = y.plant AND x.comp_no = y.comp_no AND y.is_use = 'Y'       ");
        query.append("WHERE  1 = 1                                              ");
        query.append(this.getWhere(maUserCommonDTO,loginUser));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
    /**
     * Filter 조건
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUserCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaUserCommonDTO maUserCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        query.getAndQuery("x.comp_no", maUserCommonDTO.getFilterCompNo()); 
        if (!"".equals(maUserCommonDTO.getUsrPlantauthId()))
        {
            query.getAndQuery("x.usrplantauth_id", maUserCommonDTO.getUsrPlantauthId());
            return query.toString();
        }
        
        query.getAndQuery("x.user_id", maUserCommonDTO.getUserId());
        
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
    public int deleteUser(MaUserCommonDTO maUserCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TAUSRPLANTAUTH                                 ");
        query.append("WHERE  comp_no            = ?                         ");
        query.append("  AND  usrplantauth_id    = ?                         ");

        Object[] objects = new Object[] {   
                loginUser.getCompNo(),
                maUserCommonDTO.getUsrPlantauthId()
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

}