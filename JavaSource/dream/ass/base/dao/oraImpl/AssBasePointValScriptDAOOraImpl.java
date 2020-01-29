package dream.ass.base.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.ass.base.dao.AssBasePointValScriptDAO;
import dream.ass.base.dto.AssBasePointValDetailDTO;


/**
 * 메뉴 - 목록 dao
 * @author  youngjoo38
 * @version $Id: McDataSelectListDAO.java,v 1.0 2017/11/06 16:00:12 youngjoo38 Exp $
 * @since   1.0
 * @spring.bean id="assBasePointValScriptDAOTarget"
 * @spring.txbn id="assBasePointValScriptDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssBasePointValScriptDAOOraImpl extends BaseJdbcDaoSupportOra implements AssBasePointValScriptDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: McDataSelectListDAO.java,v 1.0 2017/11/06 16:00:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param assBasePointValDetailDTO
     * @return List
     */
    public int updateScript(AssBasePointValDetailDTO assBasePointValDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAASSBASEPVAL SET						");
    	query.append("	sql_script    	= ?							");
    	query.append("WHERE assbasepval_id 	= ?						");
    	query.append("  AND comp_no		= ?							");
    	
    	Object[] objects = new Object[] {
    			assBasePointValDetailDTO.getScript(),
    			assBasePointValDetailDTO.getAssBasePointValId(),
    			loginUser.getCompNo()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }


	public AssBasePointValDetailDTO findScript(AssBasePointValDetailDTO assBasePointValDetailDTO) {
		QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT                                                                  ");
		query.append("        x.assbasepval_id                 AS assBasePointValId           ");
		query.append("        ,x.assbasepoint_id               AS assBasePointId              ");
		query.append("        ,x.assbaselist_id                AS assBaseListId               ");
		query.append("        ,x.ass_eval                      AS assEval                     ");
		query.append("        ,x.eval_value                    AS evalValue                   ");
		query.append("        ,x.ord_no                        AS ordNo                       ");
		query.append("        ,x.is_use                        AS isUseId                     ");
		query.append("        ,x.REMARK                        AS REMARK                      ");
		query.append("        ,x. sql_script                   AS script                      ");
		query.append("FROM TAASSBASEPVAL x                                                    ");
		query.append("WHERE 1=1                                                               ");
		
		query.getAndQuery("x.assbaselist_id", assBasePointValDetailDTO.getAssBaseListId());
		query.getAndQuery("x.assbasepoint_id", assBasePointValDetailDTO.getAssBasePointId());
		query.getAndQuery("x.assbasepval_id", assBasePointValDetailDTO.getAssBasePointValId());
		
        AssBasePointValDetailDTO DetailDTO = 
        		(AssBasePointValDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new AssBasePointValDetailDTO()));
        
        return DetailDTO;
	}
    
   
}