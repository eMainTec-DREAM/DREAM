package dream.rcm.system.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.rcm.system.dao.RcmSysEqDetailDAO;
import dream.rcm.system.dto.RcmSysEqDetailDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * 설비설정 상세 dao
 * @author  kim21017
 * @version $Id: RcmSysEqDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmSysEqDetailDAOTarget"
 * @spring.txbn id="rcmSysEqDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmSysEqDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements RcmSysEqDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmSysEqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmId
     * @param pmEquipId
     * @param compNo
     * @return
     */
    public RcmSysEqDetailDTO findDetail(String rcmListId, String rcmEqId, User user)
    {
    	QueryBuffer query = new QueryBuffer();

        query.append("SELECT												");
        query.append("       y.equip_id						equipId			");
        query.append("      ,y.item_no						itemNo			");
        query.append("      ,y.description					description		");
        query.append("      ,y.eqctg_id						eqCtgId			");
        query.append("		,(SELECT full_desc								");
        query.append("		    FROM TAEQCTG								");
        query.append("		   WHERE comp_no = y.comp_no					");
        query.append("		     AND eqctg_id = y.eqctg_id)	eqCtgDesc		");
        query.append("      ,y.eqloc_id						eqLocId			");
        query.append("		,(SELECT full_desc								");
        query.append("		    FROM TAEQLOC								");
        query.append("		   WHERE comp_no = y.comp_no					");
        query.append("		     AND eqloc_id = y.eqloc_id)	eqLocDesc		");
        query.append("      ,y.eq_status					eqStatusId		");
        query.append("		,SFACODE_TO_DESC(y.eq_status,'EQ_STATUS','SYS','','"+user.getLangId()+"') eqStatusDesc");
        query.append("      ,x.remark 						remark			");
        query.append("      ,x.rcmeq_id 					rcmEqId			");
        query.append("FROM   TARCMEQ x INNER JOIN TAEQUIPMENT y	    		");
        query.append("	ON   x.comp_no = y.comp_no							");
        query.append(" AND   x.equip_id = y.equip_id						");
        query.append("WHERE	 x.rcmeq_id 		= '"+rcmEqId+"'				");
        query.append("  AND  x.rcmlist_id		= '"+rcmListId+"'			");
        query.append("  AND  x.comp_no			= '"+user.getCompNo()+"'	");
    
        RcmSysEqDetailDTO rcmSysEqDetailDTO = 
        		(RcmSysEqDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new RcmSysEqDetailDTO()));
        
        return rcmSysEqDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmSysEqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEqDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     */
    public int updateDetail(RcmSysEqDetailDTO rcmSysEqDetailDTO, RcmSysCommonDTO rcmSysCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TARCMEQ SET				");
    	query.append("	equip_id				= ?,	");
    	query.append("	eqctg_id				= ?,	");
    	query.append("	eqloc_id				= ?,	");
    	query.append("	remark					= ?		");
    	query.append("WHERE rcmeq_id		    = ?	    ");
    	query.append("  AND comp_no			    = ?		");
    	
    	Object[] objects = new Object[] {
    			rcmSysEqDetailDTO.getEquipId()
    			,rcmSysEqDetailDTO.getEqCtgId()
    			,rcmSysEqDetailDTO.getEqLocId()
    			,rcmSysEqDetailDTO.getRemark()
    			,rcmSysEqDetailDTO.getRcmEqId()
    			,rcmSysCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmSysEqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEqDetailDTO
     * @param maPmMstrCommonDTO
     * @return
     */
    public int insertDetail(RcmSysEqDetailDTO rcmSysEqDetailDTO, RcmSysCommonDTO rcmSysCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TARCMEQ					");
    	query.append("	(comp_no,	rcmlist_id,	rcmeq_id,	");
    	query.append("	 equip_id,	eqctg_id,	eqloc_id,	");
    	query.append("	 remark								");
    	query.append("	)VALUES								");
    	query.append("	(?,			?,			?,			");
    	query.append("	 ?,			?,			?,			");
    	query.append("	 ?									");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    			rcmSysCommonDTO.getCompNo()
    			,rcmSysCommonDTO.getRcmListId()
    			,rcmSysEqDetailDTO.getRcmEqId()
    			,rcmSysEqDetailDTO.getEquipId()
    			,rcmSysEqDetailDTO.getEqCtgId()
    			,rcmSysEqDetailDTO.getEqLocId()
    			,rcmSysEqDetailDTO.getRemark()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}