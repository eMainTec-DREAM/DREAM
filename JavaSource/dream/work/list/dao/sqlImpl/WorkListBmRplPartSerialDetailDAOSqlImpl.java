package dream.work.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
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
public class WorkListBmRplPartSerialDetailDAOSqlImpl extends BaseJdbcDaoSupportOra implements WorkListBmRplPartSerialDetailDAO
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
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT											");
        query.append("       x.wkor_id 				wkOrId,				");
        query.append("       x.wocraft_id 			woCraftId,			");
        query.append("       x.emp_id 				empId,				");
        query.append("       (SELECT emp_no								");
        query.append("          FROM TAEMP								");
        query.append("         WHERE comp_no = x.comp_no				");
        query.append("           AND emp_id = x.emp_id) empNo,			");
        query.append("       (SELECT emp_name							");
        query.append("          FROM TAEMP								");
        query.append("         WHERE comp_no = x.comp_no				");
        query.append("           AND emp_id = x.emp_id) empDesc,		");
        query.append("		x.start_date			startDate,			");
        query.append("		x.start_time			startTime,			");
        query.append("		x.end_date				endDate,			");
        query.append("		x.end_time				endTime,			");
        query.append("       x.work_time 			workTime,			");
        query.append("       x.remark 				remark				");
        query.append("FROM   TAWOCRAFT x								");
//        query.append("WHERE	 x.wocraft_id 		= '"+woCraftId+"'		");
//        query.append("  AND  x.comp_no			= '"+compNo+"'			");
    
        WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO = 
        		(WorkListBmRplPartSerialDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new WorkListBmRplPartSerialDetailDTO()));
        
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
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAWOCRAFT SET				");
    	query.append("	emp_id					= ?,	");
    	query.append("	start_date				= ?,	");
    	query.append("	start_time				= ?,	");
    	query.append("	end_date				= ?,	");
    	query.append("	end_time				= ?,	");
    	query.append("	work_time				= ?,	");
    	query.append("	remark					= ?		");
    	query.append("WHERE wocraft_id		= ?			");
    	query.append("  AND comp_no			= ?			");
    	
    	Object[] objects = new Object[] {
//    			workListBmRplPartSerialDetailDTO.getEmpId(),
//    			workListBmRplPartSerialDetailDTO.getStartDate(),
//    			workListBmRplPartSerialDetailDTO.getStartTime(),
//    			workListBmRplPartSerialDetailDTO.getEndDate(),
//    			workListBmRplPartSerialDetailDTO.getEndTime(),
//    			workListBmRplPartSerialDetailDTO.getWorkTime(),
//    			workListBmRplPartSerialDetailDTO.getRemark(),
//    			workListBmRplPartSerialDetailDTO.getWoCraftId(),
//    			maWoResultMstrCommonDTO.getCompNo()
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
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAWOCRAFT					");
    	query.append("	(comp_no,		wocraft_id,			");
    	query.append("	 wkor_id,		emp_id,				");
    	query.append("	 start_date,	start_time,			");
    	query.append("	 end_date,		end_time,			");
    	query.append("	 work_time,		remark				");
    	query.append("	)	VALUES							");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?					");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
//    			maWoResultMstrCommonDTO.getCompNo(),
//    			workListBmRplPartSerialDetailDTO.getWoCraftId(),
//    			maWoResultMstrCommonDTO.getWkOrId(),
//    			workListBmRplPartSerialDetailDTO.getEmpId(),
//    			workListBmRplPartSerialDetailDTO.getStartDate(),
//    			workListBmRplPartSerialDetailDTO.getStartTime(),
//    			workListBmRplPartSerialDetailDTO.getEndDate(),
//    			workListBmRplPartSerialDetailDTO.getEndTime(),
//    			workListBmRplPartSerialDetailDTO.getWorkTime(),
//    			workListBmRplPartSerialDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * 재고확인
     */
    public String validSerial(WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser){
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.append("SELECT count(*) 			");
    	query.append("FROM TAWOCRAFT			");
    	query.append("WHERE 1=1					");
    	query.getAndQuery("wkor_id", maWoResultMstrCommonDTO.getWkOrId());
//    	query.getAndQuery("emp_id", workListBmRplPartSerialDetailDTO.getEmpId());
    	return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
	@Override
	public void deleteGarbageSerial(WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO,
			MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();

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