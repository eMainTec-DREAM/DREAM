package dream.work.pm.list.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.work.pm.list.dao.MaPmEqClnDetailDAO;
import dream.work.pm.list.dto.MaPmEqClnDetailDTO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * 사용자재 상세 dao
 * @author  kim21017
 * @version $Id: MaPmEqClnDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPmEqClnDetailDAOTarget"
 * @spring.txbn id="maPmEqClnDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPmEqClnDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPmEqClnDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPmEqClnDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmId
     * @param pmEquipId
     * @param compNo
     * @return
     */
    public MaPmEqClnDetailDTO findDetail(String pmId, String pmEquipId, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT												");
        query.append("       x.pmequip_id 					pmEquipId,		");
        query.append("       x.equip_id 					equipId,		");
        query.append("       (SELECT item_no								");
        query.append("          FROM TAEQUIPMENT							");
        query.append("         WHERE comp_no = x.comp_no					");
        query.append("           AND equip_id = x.equip_id) equipNo,		");
        query.append("       (SELECT (SELECT full_desc 						");
        query.append("                 FROM TAEQLOC 						");
        query.append("                 WHERE eqloc_id = a.eqloc_id			");
        query.append("                   AND comp_no = a.comp_no)			");
        query.append("          FROM TAEQUIPMENT a							");
        query.append("         WHERE a.comp_no = x.comp_no					");
        query.append("           AND a.equip_id = x.equip_id) eqLocDesc,	");
        query.append("       (SELECT description							");
        query.append("          FROM TAEQUIPMENT							");
        query.append("         WHERE comp_no = x.comp_no					");
        query.append("           AND equip_id = x.equip_id) equipDesc,		");
        query.append("       x.description 					workPart		");
        query.append("FROM   TAPMEQUIP x	    							");
        query.append("WHERE	 x.pmequip_id 		= '"+pmEquipId+"'			");
        query.append("  AND  x.comp_no			= '"+compNo+"'				");
    
        MaPmEqClnDetailDTO maPmEqClnDetailDTO = 
        		(MaPmEqClnDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPmEqClnDetailDTO()));
        
        return maPmEqClnDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPmEqClnDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmEqClnDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     */
    public int updateDetail(MaPmEqClnDetailDTO maPmEqClnDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAPMEQUIP SET				");
    	query.append("	equip_id				= ?,	");
    	query.append("	description				= ?		");
    	query.append("WHERE pmequip_id		    = ?	    ");
    	query.append("  AND comp_no			    = ?		");
    	
    	Object[] objects = new Object[] {
    			maPmEqClnDetailDTO.getEquipId(),
    			maPmEqClnDetailDTO.getWorkPart(),
    			maPmEqClnDetailDTO.getPmEquipId(),
    			maPmMstrCommonDTO.getCompNo()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPmEqClnDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmEqClnDetailDTO
     * @param maPmMstrCommonDTO
     * @return
     */
    public int insertDetail(MaPmEqClnDetailDTO maPmEqClnDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAPMEQUIP							");
    	query.append("	(comp_no,	pmequip_id,		pm_id,			");
    	query.append("	 equip_id,	description,	init_wrk_date,	");
    	query.append("	 last_sch_date								");
    	query.append("	)VALUES										");
    	query.append("	(?,				?,			?,				");
    	query.append("	 ?,				?,			?,				");
    	query.append("	 ?											");
    	query.append("	)											");
    	
    	Object[] objects = new Object[] {
    	        maPmMstrCommonDTO.getCompNo(),
    	        maPmEqClnDetailDTO.getPmEquipId(),
    	        maPmMstrCommonDTO.getPmId(),
    	        maPmEqClnDetailDTO.getEquipId(),
    	        maPmEqClnDetailDTO.getWorkPart(),
    	        maPmEqClnDetailDTO.getInitWrkDate(),
    	        maPmEqClnDetailDTO.getInitWrkDate()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}