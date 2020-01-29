package dream.pers.priv.info.dao.oraImpl;

import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.pers.priv.info.dao.MaChangePwDAO;
import dream.pers.priv.info.dto.MaChangePwDTO;

/**
 * �ý����ڵ�-�з� dao
 * @author  kim21017
 * @version $Id: MaChangePwDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maChangePwDAOTarget"
 * @spring.txbn id="maChangePwDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaChangePwDAOOraImpl extends BaseJdbcDaoSupportOra implements MaChangePwDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaChangePwDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maChangePwDTO
     * @return
     */
    public MaChangePwDTO findDetail(MaChangePwDTO maChangePwDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT														");
        query.append("       x.user_id			userId,								");
        query.append("       x.user_no			userNo,								");
        query.append("       x.user_name		userName,							");
        query.append("       x.password			oldPwTemp							");
        query.append("FROM   TAUSER x												");
        query.append("WHERE  x.user_id 			= '"+maChangePwDTO.getUserId()+"'	");
        query.append("  AND  x.comp_no 			= '"+maChangePwDTO.getCompNo()+"'	");
    
        MaChangePwDTO maChangePwDTO1 = 
        		(MaChangePwDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaChangePwDTO()));
        
        return maChangePwDTO1;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaChangePwDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maChangePwDTO
     * @return
     */
    public int updateDetail(MaChangePwDTO maChangePwDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAUSER  SET	");
    	query.append("	password		= ?,");
    	query.append("	change_pwd_date  = TO_CHAR(SYSDATE,'YYYYMMDD')");
    	query.append("WHERE user_id 	= ?	");
    	query.append("  AND comp_no		= ?	");
    	
    	Object[] objects = new Object[] {
    			CommonUtil.aesEncodeString(maChangePwDTO.getNewPw()),
    			maChangePwDTO.getUserId(),
    			maChangePwDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    public int insertPwChanHist(MaChangePwDTO maChangePwDTO)
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
    			maChangePwDTO.getCompNo()
    			,maChangePwDTO.getUserId()
    			,DateUtil.getDateTime()
    			,CommonUtil.aesEncodeString(maChangePwDTO.getNewPw())
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    public String checkPwHist(MaChangePwDTO maChangePwDTO, String pwChangeHistCnt)
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
    			maChangePwDTO.getCompNo()
    			,maChangePwDTO.getUserId()
    			,pwChangeHistCnt
    			,CommonUtil.aesEncodeString(maChangePwDTO.getNewPw())
    	};
    	
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
    }
}