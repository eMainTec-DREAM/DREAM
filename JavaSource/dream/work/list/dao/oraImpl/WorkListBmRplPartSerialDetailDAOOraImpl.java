package dream.work.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.list.dao.WorkListBmRplPartSerialDetailDAO;
import dream.work.list.dto.WorkListBmRplPartSerialDetailDTO;
import dream.work.list.dto.WorkListBmRplPartSerialListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업결과-부품Serial 상세 dao
 * @author  kim21017
 * @version $Id: WorkListBmRplPartSerialDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workListBmRplPartSerialDetailDAOTarget"
 * @spring.txbn id="workListBmRplPartSerialDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkListBmRplPartSerialDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkListBmRplPartSerialDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: WorkListBmRplPartSerialDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woCraftId
     * @param compNo
     * @return
     */
    public WorkListBmRplPartSerialDetailDTO findDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,WorkListBmRplPartSerialListDTO workListBmRplPartSerialListDTO, String compNo) {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT											");
        query.append("       woparts_serial_id wopartsSerialId			");
        query.append("       ,wopart_id wopartId						");
        query.append("       ,part_id partId							");
        query.append("       ,in_serial_no inSerialNo					");
        query.append("       ,in_equip_id inEquipId						");
        query.append("       ,out_serial_no outSerialNo					");
        query.append("       ,out_equip_id outEquipId					");
        query.append("       ,remark									");
        query.append("FROM   TAWOPARTS_SERIAL							");
        query.append("WHERE	 woparts_serial_id 	= ?						");
        query.append("  AND  comp_no				= ?					");
    
        Object[] objects = new Object[] {
        		workListBmRplPartSerialListDTO.getWopartSerialId()
        		,compNo
        };
        
        WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO = 
        		(WorkListBmRplPartSerialDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new WorkListBmRplPartSerialDetailDTO()));
        
        return workListBmRplPartSerialDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkListBmRplPartSerialDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListBmRplPartSerialDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int updateDetail(WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAWOPARTS_SERIAL SET		");
    	query.append("	part_id					= ?,	");
    	query.append("	in_serial_no			= ?,	");
    	query.append("	out_serial_no			= ?,	");
    	query.append("	in_equip_id				= ?,	");
    	query.append("	out_equip_id			= ?,	");
    	query.append("	remark					= ?		");
    	query.append("WHERE woparts_serial_id	= ?		");
    	query.append("  AND comp_no				= ?		");
    	
    	Object[] objects = new Object[] {
    			workListBmRplPartSerialDetailDTO.getPartId(),
    			workListBmRplPartSerialDetailDTO.getInSerialNo(),
    			workListBmRplPartSerialDetailDTO.getOutSerialNo(),
    			workListBmRplPartSerialDetailDTO.getInEquipId(),
    			workListBmRplPartSerialDetailDTO.getOutEquipId(),
    			workListBmRplPartSerialDetailDTO.getRemark(),
    			workListBmRplPartSerialDetailDTO.getWopartsSerialId(),
    			maWoResultMstrCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: WorkListBmRplPartSerialDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListBmRplPartSerialDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int insertDetail(WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAWOPARTS_SERIAL			");
    	query.append("	(comp_no,		woparts_serial_id,	");
    	query.append("	 wopart_id,		part_id,			");
    	query.append("	 in_serial_no,	out_serial_no,		");
    	query.append("	 in_equip_id,	out_equip_id,		");
    	query.append("	 remark								");
    	query.append("	)	VALUES							");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?									");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    			maWoResultMstrCommonDTO.getCompNo(),
    			workListBmRplPartSerialDetailDTO.getWopartsSerialId(),
    			workListBmRplPartSerialDetailDTO.getWopartId(),
    			workListBmRplPartSerialDetailDTO.getPartId(),
    			workListBmRplPartSerialDetailDTO.getInSerialNo(),
    			workListBmRplPartSerialDetailDTO.getOutSerialNo(),
    			workListBmRplPartSerialDetailDTO.getInEquipId(),
    			workListBmRplPartSerialDetailDTO.getOutEquipId(),
    			workListBmRplPartSerialDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * 재고확인
     */
    public String validSerial(WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser){
    	QueryBuffer query = new QueryBuffer();
    	query.append("SELECT count(*) 			");
    	query.append("FROM TAWOCRAFT			");
    	query.append("WHERE 1=1					");
    	query.getAndQuery("wkor_id", maWoResultMstrCommonDTO.getWkOrId());
//    	query.getAndQuery("emp_id", workListBmRplPartSerialDetailDTO.getEmpId());
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
	@Override
	public void deleteGarbageSerial(WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO,MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		
		QueryBuffer query = new QueryBuffer();

		query.append("DELETE														");
		query.append("FROM  TAWOPARTS_SERIAL x										");
		query.append("WHERE x.woparts_serial_id NOT IN (SELECT y.woparts_serial_id	");
		query.append("                                 FROM   TAWOPARTS_SERIAL y	");
		query.append("                                 WHERE  y.wopart_id = ?		");
		query.append("                                   AND  y.part_id   = ?		");
		query.append("                                )								");
		query.append("  AND x.wopart_id = ?        									");

    	
    	Object[] objects = new Object[] {
    			workListBmRplPartSerialDetailDTO.getWopartId(),
    			workListBmRplPartSerialDetailDTO.getPartId(),
    			workListBmRplPartSerialDetailDTO.getWopartId()
    	};
    	getJdbcTemplate().update(query.toString(), objects);
	}
}