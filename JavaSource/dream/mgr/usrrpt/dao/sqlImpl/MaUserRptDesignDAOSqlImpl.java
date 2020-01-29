package dream.mgr.usrrpt.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
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
public class MaUserRptDesignDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaUserRptDesignDAO
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
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAUSRDATA SET							");
    	query.append("	script    		= ?							");
    	query.append("WHERE usrdata_id 	= ?							");
    	query.append("  AND comp_no		= ?							");
    	
    	Object[] objects = new Object[] {
//    			mcDataSelectCommonDTO.getScript(),
//    			mcDataSelectCommonDTO.getUsrrptId(),
//    			mcDataSelectCommonDTO.getCompNo()
    	};
    	
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }


	public MaUserRptCommonDTO findScript(MaUserRptCommonDTO mcDataSelectCommonDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();
        
//        query.append("SELECT                                                    ");
//        query.append("       dbo.SFACODE_TO_DESC(x.usrdata_type,'USRDATA_TYPE','SYS','','ko')    usrdataTypeDesc,	");
//        query.append("       usrdata_type usrdataType,							");
//        query.append("       title,												");
//        query.append("       description,										");
//        query.append("       cre_date creDate,									");
//        query.append("       dept_id creDept,									");
//        query.append("       (SELECT description								");
//        query.append("        FROM   TADEPT a									");
//        query.append("        WHERE  a.dept_id = x.dept_id) creDeptDesc,	    ");
//        query.append("       cre_id creBy,										");
//        query.append("       (SELECT user_name									");
//        query.append("        FROM   TAUSER										");
//        query.append("        WHERE  user_id = x.cre_id) creByDesc,				");
//        query.append("       script,	    									");
//        query.append("       comp_no compNo,  									");
//        query.append("       usrdata_id usrrptId									");
//        query.append("FROM   TAUSRDATA x											");
//        query.append("WHERE  x.usrdata_id = '"+mcDataSelectCommonDTO.getUsrrptId()+"'                 		");
//
//    
        MaUserRptCommonDTO DetailDTO = 
        		(MaUserRptCommonDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaUserRptCommonDTO()));
        
        return DetailDTO;
	}
    
   
}