package dream.consult.comp.eqmgr.dao.oraImpl;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.comp.eqmgr.dao.MaEqMngDetailDAO;
import dream.consult.comp.eqmgr.dto.MaEqMngDetailDTO;

/**
 * 설비담당자변경 - 상세 dao
 * 
 * @author jung712
 * @version $Id: MaEqMngDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maEqMngDetailDAOTarget"
 * @spring.txbn id="maEqMngDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMngDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqMngDetailDAO
{
     /**
     * 설비 담당자(정) 변경
     * @author kim21017
     * @version $Id: MaEqMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMngDetailDTO
     * @return
     */
    public int updateMainManager(MaEqMngDetailDTO maEqMngDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAEQUIPMENT SET							");
    	query.append("	main_mng_id			= ?                      	");
    	query.append("WHERE dept_id 		= ?							");
    	query.append("  AND main_mng_id 	= ?							");
    	//query.getEqLocLevelQuery("eqloc_id", maEqMngDetailDTO.getEqLocId(), maEqMngDetailDTO.getEqLocDesc(), maEqMngDetailDTO.getCompNo());
    	query.getCodeLikeQuery("eqloc_id", "(SELECT x.full_desc FROM TAEQLOC x WHERE comp_no = x.comp_no AND eqloc_id = x.eqloc_id)", maEqMngDetailDTO.getEqLocId(), maEqMngDetailDTO.getEqLocDesc());
    	
    	Object[] objects = new Object[] {
    			maEqMngDetailDTO.getNewMainMngId(),
    			maEqMngDetailDTO.getDeptId(),
    			maEqMngDetailDTO.getMainMngId()
    	};
    	
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * 설비 담당자(부) 변경
     * @author kim21017
     * @version $Id: MaEqMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMngDetailDTO
     * @return
     */
    public int updateSubManager(MaEqMngDetailDTO maEqMngDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAEQUIPMENT SET							");
    	query.append("	sub_mng_id			= ?                      	");
    	query.append("WHERE dept_id 		= ?							");
    	query.append("  AND sub_mng_id 		= ?							");
    	query.getCodeLikeQuery("eqloc_id", "(SELECT x.full_desc FROM TAEQLOC x WHERE comp_no = x.comp_no AND eqloc_id = x.eqloc_id)", maEqMngDetailDTO.getEqLocId(), maEqMngDetailDTO.getEqLocDesc());
    	
    	Object[] objects = new Object[] {
    			maEqMngDetailDTO.getNewSubMngId(),
    			maEqMngDetailDTO.getDeptId(),
    			maEqMngDetailDTO.getSubMngId()
    	};
    	
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
}