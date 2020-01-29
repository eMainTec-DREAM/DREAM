package dream.work.list.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.work.list.dao.MaWoResultClnDetailDAO;
import dream.work.list.dto.MaWoResultClnDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업결과 설비 상세 dao
 * @author  kim21017
 * @version $Id: MaWoResultClnDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoResultClnDetailDAOTarget"
 * @spring.txbn id="maWoResultClnDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoResultClnDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWoResultClnDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoResultClnDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woEquipId
     * @param compNo
     * @return
     */
    public MaWoResultClnDetailDTO findDetail(String wkOrId, String woEquipId, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT												");
        query.append("       x.woequip_id 					woEquipId,		");
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
        query.append("       x.description 					workPart,		");
        query.append("       x.work_time 					workTime,		");
        query.append("       x.remark 						remark,			");
        query.append("       (SELECT wo_gen_type							");
        query.append("          FROM TAWORKORDER							");
        query.append("         WHERE comp_no = x.comp_no					");
        query.append("           AND wkor_id = x.wkor_id) woGenType			");
        query.append("FROM   TAWOEQUIP x	    							");
        query.append("WHERE	 x.woequip_id 		= '"+woEquipId+"'			");
        query.append("  AND  x.comp_no			= '"+compNo+"'				");
    
        MaWoResultClnDetailDTO maWoResultClnDetailDTO = 
        		(MaWoResultClnDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaWoResultClnDetailDTO()));
        
        return maWoResultClnDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultClnDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultClnDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int updateDetail(MaWoResultClnDetailDTO maWoResultClnDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAWOEQUIP SET				");
    	query.append("	equip_id				= ?,	");
    	query.append("	description				= ?,	");
    	query.append("	work_time				= ?,	");
    	query.append("	remark					= ?		");
    	query.append("WHERE woequip_id		    = ?	    ");
    	query.append("  AND comp_no			    = ?		");
    	
    	Object[] objects = new Object[] {
    			maWoResultClnDetailDTO.getEquipId(),
    			maWoResultClnDetailDTO.getWorkPart(),
    			maWoResultClnDetailDTO.getWorkTime(),
    			maWoResultClnDetailDTO.getRemark(),
    			maWoResultClnDetailDTO.getWoEquipId(),
    			maWoResultMstrCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaWoResultClnDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultClnDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int insertDetail(MaWoResultClnDetailDTO maWoResultClnDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAWOEQUIP							");
    	query.append("	(comp_no,		woequip_id,					");
    	query.append("	 wkor_id,		equip_id,	description,	");
    	query.append("	 work_time,		remark						");
    	query.append("	)VALUES										");
    	query.append("	(?,				?,							");
    	query.append("	 ?,				?,			?,				");
    	query.append("	 ?,				?							");
    	query.append("	)											");
    	
    	Object[] objects = new Object[] {
    			maWoResultMstrCommonDTO.getCompNo(),
    			maWoResultClnDetailDTO.getWoEquipId(),
    			maWoResultMstrCommonDTO.getWkOrId(),
    			maWoResultClnDetailDTO.getEquipId(),
    			maWoResultClnDetailDTO.getWorkPart(),
    			maWoResultClnDetailDTO.getWorkTime(),
    			maWoResultClnDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}