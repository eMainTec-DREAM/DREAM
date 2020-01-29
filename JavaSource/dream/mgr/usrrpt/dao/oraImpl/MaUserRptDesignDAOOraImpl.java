package dream.mgr.usrrpt.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.mgr.usrrpt.dao.MaUserRptDesignDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;


/**
 * 메뉴 - 목록 dao
 * @author  kim21017
 * @version $Id: MaUserRptListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maUserRptDesignDAOTarget"
 * @spring.txbn id="maUserRptDesignDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaUserRptDesignDAOOraImpl extends BaseJdbcDaoSupportOra implements MaUserRptDesignDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaUserRptListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param mcDataSelectCommonDTO
     * @return List
     */
    public int updateScript(MaUserRptCommonDTO mcDataSelectCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAUSRDATA SET							");
    	query.append("	script    		= ?							");
    	query.append("WHERE usrdata_id 	= ?							");
    	query.append("  AND comp_no		= ?							");
    	
    	Object[] objects = new Object[] {
//    			mcDataSelectCommonDTO.getScript(),
//    			mcDataSelectCommonDTO.getUsrrptId(),
//    			mcDataSelectCommonDTO.getCompNo()
    	};
    	
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }


	public MaUserRptCommonDTO findScript(MaUserRptCommonDTO mcDataSelectCommonDTO) {
		QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT                                                    	");
		query.append("       SFACODE_TO_DESC(x.usrrpt_type,'USRRPT_TYPE','SYS','','ko')    usrrptTypeDesc,    	");
		query.append("       usrrpt_type usrrptType,                            	");
		query.append("       title,                                                	");
		query.append("       description,                                        	");
		query.append("       cre_date creDate,                                    	");
		query.append("       dept_id creDept,                                    	");
		query.append("       (SELECT description                                	");
		query.append("        FROM   TADEPT a                                    	");
		query.append("        WHERE  a.dept_id = x.dept_id) creDeptDesc,        	");
		query.append("       cre_id creBy,                                        	");
		query.append("       (SELECT user_name                                    	");
		query.append("        FROM   TAUSER                                        	");
		query.append("        WHERE  user_id = x.cre_id) creByDesc,                	");
		query.append("       comp_no compNo,                                      	");
		query.append("       usrrptlist_id usrrptlistId                            	");
		query.append("FROM   TAUSRRPTLIST x                                        	");
        query.append("WHERE  x.usrrptlist_id = '"+mcDataSelectCommonDTO.getUsrrptlistId()+"'                 		");

    
        MaUserRptCommonDTO DetailDTO = 
        		(MaUserRptCommonDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaUserRptCommonDTO()));
        
        return DetailDTO;
	}
    
   
}