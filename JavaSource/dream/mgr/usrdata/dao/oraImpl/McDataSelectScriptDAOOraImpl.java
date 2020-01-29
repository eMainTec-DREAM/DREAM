package dream.mgr.usrdata.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.mgr.usrdata.dao.McDataSelectListDAO;
import dream.mgr.usrdata.dao.McDataSelectScriptDAO;
import dream.mgr.usrdata.dto.McDataSelectCommonDTO;
import dream.mgr.usrdata.dto.McDataSelectDetailDTO;


/**
 * 메뉴 - 목록 dao
 * @author  kim21017
 * @version $Id: McDataSelectListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="mcDataSelectScriptDAOTarget"
 * @spring.txbn id="mcDataSelectScriptDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class McDataSelectScriptDAOOraImpl extends BaseJdbcDaoSupportOra implements McDataSelectScriptDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: McDataSelectListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param mcDataSelectCommonDTO
     * @return List
     */
    public int updateScript(McDataSelectCommonDTO mcDataSelectCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAUSRDATA SET							");
    	query.append("	script    		= ?							");
    	query.append("WHERE usrdata_id 	= ?							");
    	query.append("  AND comp_no		= ?							");
    	
    	Object[] objects = new Object[] {
    			mcDataSelectCommonDTO.getScript(),
    			mcDataSelectCommonDTO.getUsrrptId(),
    			mcDataSelectCommonDTO.getCompNo()
    	};
    	
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }


	public McDataSelectCommonDTO findScript(McDataSelectCommonDTO mcDataSelectCommonDTO) {
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       SFACODE_TO_DESC(x.usrdata_type,'USRDATA_TYPE','SYS','','ko')    usrdataTypeDesc,	");
        query.append("       usrdata_type usrdataType,							");
        query.append("       title,												");
        query.append("       description,										");
        query.append("       cre_date creDate,									");
        query.append("       dept_id creDept,									");
        query.append("       (SELECT description								");
        query.append("        FROM   TADEPT a									");
        query.append("        WHERE  a.dept_id = x.dept_id) creDeptDesc,	    ");
        query.append("       cre_id creBy,										");
        query.append("       (SELECT user_name									");
        query.append("        FROM   TAUSER										");
        query.append("        WHERE  user_id = x.cre_id) creByDesc,				");
        query.append("       script,	    									");
        query.append("       comp_no compNo,  									");
        query.append("       usrdata_id usrrptId									");
        query.append("FROM   TAUSRDATA x											");
        query.append("WHERE  x.usrdata_id = '"+mcDataSelectCommonDTO.getUsrrptId()+"'                 		");

    
        McDataSelectCommonDTO DetailDTO = 
        		(McDataSelectCommonDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new McDataSelectCommonDTO()));
        
        return DetailDTO;
	}
    
   
}