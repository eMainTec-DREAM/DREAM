package dream.work.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.list.dao.WorkListGnlCaEqDetailDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.WorkListGnlCaEqDetailDTO;

/**
 * 작업상세  - 검교정 - 교정기 상세 dao
 * @author  kim21017
 * @version $Id: WorkListGnlCaEqDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workListGnlCaEqDetailDAOTarget"
 * @spring.txbn id="workListGnlCaEqDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkListGnlCaEqDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkListGnlCaEqDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: WorkListGnlCaEqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woCalibValueId
     * @param compNo
     * @return
     */
    public WorkListGnlCaEqDetailDTO findDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

        query.append("SELECT											");
        query.append("       y.description								");
        query.append("       ,y.item_no itemNo							");
        query.append("       ,x.serial_no serialNo						");
        query.append("       ,x.wo_no woNo								");
        query.append("       ,x.next_plan_date nextPlanDate				");
        query.append("       ,wocalibstdeq_id wocalibstdeqId			");
        query.append("       ,wkor_id wkOrId							");
        query.append("       ,x.equip_id equipId                        ");
        query.append("       ,sopdoc_no calibSopdocNo					");
        query.append("		 ,SFACODE_TO_DESC(sopdoc_no,'CALIB_SOPDOC_NO','USR','"+user.getCompNo()+"','"+user.getLangId()+"')	calibSopdocNoDesc	");
        query.append("FROM   TAWOCALIBSTDEQ x INNER JOIN TAEQUIPMENT y 	");
        query.append("  ON   x.equip_id = y.equip_id					");
        query.append("WHERE	 x.wocalibstdeq_id 		= ?					");
        query.append("  AND  x.comp_no				= ?					");
        
        Object[] objects = new Object[] {
        		maWoResultMstrCommonDTO.getWocalibstdeqId(),
        		user.getCompNo()
        };
    
        WorkListGnlCaEqDetailDTO workListGnlCaEqDetailDTO = 
        		(WorkListGnlCaEqDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new WorkListGnlCaEqDetailDTO()));
        
        return workListGnlCaEqDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkListGnlCaEqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListGnlCaEqDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int updateDetail(WorkListGnlCaEqDetailDTO workListGnlCaEqDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAWOCALIBSTDEQ SET			");
    	query.append("	equip_id				= ?,	");
    	query.append("	description				= ?,	");
    	query.append("	serial_no				= ?,	");
    	query.append("  wo_no                   = ?,    ");
    	query.append("	sopdoc_no				= ?		");
    	query.append("WHERE wocalibstdeq_id		= ?		");
    	query.append("  AND comp_no				= ?		");
    	
    	Object[] objects = new Object[] {
    			workListGnlCaEqDetailDTO.getEquipId(),
    			workListGnlCaEqDetailDTO.getDescription(),
    			workListGnlCaEqDetailDTO.getSerialNo(),
    			workListGnlCaEqDetailDTO.getWoNo(),
    			workListGnlCaEqDetailDTO.getCalibSopdocNo(),
    			workListGnlCaEqDetailDTO.getWocalibstdeqId(),
    			maWoResultMstrCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: WorkListGnlCaEqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListGnlCaEqDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int insertDetail(WorkListGnlCaEqDetailDTO workListGnlCaEqDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAWOCALIBSTDEQ					");
    	query.append("	(comp_no,				wocalibstdeq_id,	");
    	query.append("	 wkor_id,				calib_wkor_id,		");
    	query.append("	 wo_no,					equip_id,			");
    	query.append("	 description,			serial_no,			");
    	query.append("	 next_plan_date,		sopdoc_no			");
    	query.append("	)	VALUES									");
    	query.append("	(?,						?,					");
    	query.append("	 ?,						?,					");
    	query.append("	 ?,						?,					");
    	query.append("	 ?,						?,					");
    	query.append("	 ?,						?					");
    	query.append("	)											");
    	
    	Object[] objects = new Object[] {
    			maWoResultMstrCommonDTO.getCompNo(),
    			workListGnlCaEqDetailDTO.getWocalibstdeqId(),
    			maWoResultMstrCommonDTO.getWkOrId(),
    			workListGnlCaEqDetailDTO.getCalibWkorId(),
    			workListGnlCaEqDetailDTO.getWoNo(),
    			workListGnlCaEqDetailDTO.getEquipId(),
    			workListGnlCaEqDetailDTO.getDescription(),
    			workListGnlCaEqDetailDTO.getSerialNo(),
    			workListGnlCaEqDetailDTO.getNextPlanDate(),
    			workListGnlCaEqDetailDTO.getCalibSopdocNo()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    @Override
    public String checkDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,WorkListGnlCaEqDetailDTO workListGnlCaEqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                            ");
        query.append("       COUNT(x.wocalibstdeq_id)                ");
        query.append("FROM   TAWOCALIBSTDEQ x               ");
        query.append("  WHERE  1=1       ");
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.wkor_id", maWoResultMstrCommonDTO.getWkOrId());
        query.getAndQuery("x.equip_id", workListGnlCaEqDetailDTO.getEquipId());
        query.append("AND x.wocalibstdeq_id != "+workListGnlCaEqDetailDTO.getWocalibstdeqId());
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
}