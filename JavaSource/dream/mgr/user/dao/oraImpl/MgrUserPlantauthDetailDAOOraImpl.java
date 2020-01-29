package dream.mgr.user.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.mgr.user.dao.MgrUserPlantauthDetailDAO;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MgrUserPlantauthDetailDTO;

/**
 * 사용자 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="mgrUserPlantauthDetailDAOTarget"
 * @spring.txbn id="mgrUserPlantauthDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrUserPlantauthDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrUserPlantauthDetailDAO
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
    public MgrUserPlantauthDetailDTO findDetail(MaUserCommonDTO maUserCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       x.comp_no compNo                                   ");
        query.append("       ,usrplantauth_id usrplantauthId                    ");
        query.append("       ,user_id userId                                    ");
        query.append("       ,x.plant                                           ");
        query.append("       ,y.description plantDesc                           ");
        query.append("       ,is_auth isAuth                                    ");
        query.append("       ,REMARK                                            ");
        query.append("FROM   TAUSRPLANTAUTH x INNER JOIN TAPLANT y ON x.plant = y.plant AND x.comp_no = y.comp_no ");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.usrplantauth_id", maUserCommonDTO.getUsrPlantauthId() );
    
        MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO = 
        		(MgrUserPlantauthDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MgrUserPlantauthDetailDTO()));
        
        return mgrUserPlantauthDetailDTO;
    }
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrUserPlantauthDetailDTO
     * @return
     */
    public int insertDetail(MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAUSRPLANTAUTH (						");
    	query.append("    comp_no     , user_id       		, plant		");
    	query.append("  , is_auth     , usrplantauth_id   	, remark 	");
    	query.append(") VALUES (										");
    	query.append("    ?   		  , ?     				, ?			");
    	query.append("  , ?   		  , ?     				, ?			");
    	query.append(")     											");
        
        Object[] objects = new Object[] {
                loginUser.getCompNo()
                , mgrUserPlantauthDetailDTO.getUserId()
                , mgrUserPlantauthDetailDTO.getPlant()
                , mgrUserPlantauthDetailDTO.getIsAuth()
                , mgrUserPlantauthDetailDTO.getUsrplantauthId()
                , mgrUserPlantauthDetailDTO.getRemark()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    public int updateDetail(MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAUSRPLANTAUTH SET		");
    	query.append("    is_auth 	= ?				");
    	query.append("  , remark 	= ?				");
    	query.append("  , plant 	= ?				");
    	query.append("WHERE comp_no = ?				");
    	query.append("AND usrplantauth_id = ?		");
    	
    	Object[] objects = new Object[] {
    			mgrUserPlantauthDetailDTO.getIsAuth()
    			, mgrUserPlantauthDetailDTO.getRemark()
    			, mgrUserPlantauthDetailDTO.getPlant()
    			, loginUser.getCompNo()
    			, mgrUserPlantauthDetailDTO.getUsrplantauthId()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }

    public String validPlant(MaUserCommonDTO maUserCommonDTO, MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO, User user, String isRegbatch) throws Exception 
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT COUNT(*)			");
        query.append("FROM TAUSRPLANTAUTH x		");
        query.append("WHERE 1=1					");
        query.append("AND x.comp_no = ? 		");
        query.append("AND x.user_id = ?			");
        query.append("AND x.plant 	= ?			");
        if(!"Y".equals(isRegbatch))
        	query.append("AND x.usrplantauth_id != "+mgrUserPlantauthDetailDTO.getUsrplantauthId()+"");
        
        Object[] objects = new Object[] {
        		user.getCompNo()
        		,maUserCommonDTO.getUserId()
        		,mgrUserPlantauthDetailDTO.getPlant()
        };
        
		return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
	}
}