package dream.work.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.list.dao.MaWoResultStPointDetailDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultStPointDetailDTO;

/**
 * 작업결과-작업필수검사항목 상세 dao
 * @author  kim21017
 * @version $Id: MaWoResultStPointDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoResultStPointDetailDAOTarget"
 * @spring.txbn id="maWoResultStPointDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoResultStPointDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWoResultStPointDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoResultStPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woPointId
     * @param compNo
     * @return
     */
    public MaWoResultStPointDetailDTO findDetail(String wkOrId, String woStPointId, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	String compNo = user.getCompNo();

        query.append("SELECT																									");
        query.append("		x.wostpoint_id woStPointId,																			");
        query.append("		y.step_num stepNum,																					");
        query.append("		(SELECT description																					");
        query.append("		   FROM TAEQASMB																					");
        query.append("		  WHERE comp_no = y.comp_no																			");
        query.append("			AND eqasmb_id = y.eqasmb_id) eqAsmbDesc,														");
        query.append("		y.check_point CHECKPOINT,																			");
        query.append("		y.check_method checkMethod,																			");
        query.append("		y.fit_basis fitBasis,																				");
        query.append("		SFACODE_TO_DESC(y.check_type,'CHECK_TYPE','SYS','','"+user.getLangId()+"') checkTypeDesc,									");
        query.append("		y.check_min||' / '||y.check_basis_val||' / '||y.check_max||' . ('||y.uom||')' checkValUom,			");
        query.append("		y.remark remark,																					");
        query.append("		x.st_point_rslt_status stPointRsltStatus,															");
        query.append("		SFACODE_TO_DESC(trim(x.st_point_rslt_status),'ST_POINT_RSLT_STATUS','SYS','','"+user.getLangId()+"') stPointRsltStatusDesc,	");
        query.append("		x.result_value resultValue,																			");
        query.append("		x.remark ResultRemark																				");
        query.append("FROM  TAWOSTPOINT x, TASTWRKPOINT y																		");
        query.append("WHERE x.comp_no = y.comp_no																				");
        query.append("  AND x.stwrk_point_id = y.stwrk_point_id																	");
        query.append("  AND x.comp_no = '"+compNo+"'																			");
        query.append("  AND x.wostpoint_id = "+woStPointId+"																	");
    
        MaWoResultStPointDetailDTO maWoResultStPointDetailDTO = 
        		(MaWoResultStPointDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaWoResultStPointDetailDTO()));
        
        return maWoResultStPointDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultStPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultStPointDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int updateDetail(MaWoResultStPointDetailDTO maWoResultStPointDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	Object[] objects;
    	
		query.append("UPDATE TAWOSTPOINT SET			");
    	query.append("	st_point_rslt_status	= ?,	");
    	query.append("	result_value			= ?,	");
    	query.append("	remark					= ?		");
    	query.append("WHERE wostpoint_id		= ?		");
    	query.append("  AND comp_no				= ?		");
    	
    	objects = new Object[] {
    			maWoResultStPointDetailDTO.getStPointRsltStatus(),
    			maWoResultStPointDetailDTO.getResultValue(),
    			maWoResultStPointDetailDTO.getResultRemark(),
    			maWoResultStPointDetailDTO.getWoStPointId(),
    			maWoResultMstrCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}