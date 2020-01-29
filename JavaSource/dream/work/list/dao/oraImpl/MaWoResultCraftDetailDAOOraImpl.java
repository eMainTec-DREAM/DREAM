package dream.work.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.list.dao.MaWoResultCraftDetailDAO;
import dream.work.list.dto.MaWoResultCraftDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업결과-작업자 상세 dao
 * @author  kim21017
 * @version $Id: MaWoResultCraftDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoResultCraftDetailDAOTarget"
 * @spring.txbn id="maWoResultCraftDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoResultCraftDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWoResultCraftDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoResultCraftDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woCraftId
     * @param compNo
     * @return
     */
    public MaWoResultCraftDetailDTO findDetail(String wkOrId, String woCraftId, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();

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
        query.append("WHERE	 x.wocraft_id 		= '"+woCraftId+"'		");
        query.append("  AND  x.comp_no			= '"+compNo+"'			");
    
        MaWoResultCraftDetailDTO maWoResultCraftDetailDTO = 
        		(MaWoResultCraftDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaWoResultCraftDetailDTO()));
        
        return maWoResultCraftDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultCraftDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultCraftDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int updateDetail(MaWoResultCraftDetailDTO maWoResultCraftDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

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
    			maWoResultCraftDetailDTO.getEmpId(),
    			maWoResultCraftDetailDTO.getStartDate(),
    			maWoResultCraftDetailDTO.getStartTime(),
    			maWoResultCraftDetailDTO.getEndDate(),
    			maWoResultCraftDetailDTO.getEndTime(),
    			maWoResultCraftDetailDTO.getWorkTime(),
    			maWoResultCraftDetailDTO.getRemark(),
    			maWoResultCraftDetailDTO.getWoCraftId(),
    			maWoResultMstrCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaWoResultCraftDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultCraftDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int insertDetail(MaWoResultCraftDetailDTO maWoResultCraftDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

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
    			maWoResultMstrCommonDTO.getCompNo(),
    			maWoResultCraftDetailDTO.getWoCraftId(),
    			maWoResultMstrCommonDTO.getWkOrId(),
    			maWoResultCraftDetailDTO.getEmpId(),
    			maWoResultCraftDetailDTO.getStartDate(),
    			maWoResultCraftDetailDTO.getStartTime(),
    			maWoResultCraftDetailDTO.getEndDate(),
    			maWoResultCraftDetailDTO.getEndTime(),
    			maWoResultCraftDetailDTO.getWorkTime(),
    			maWoResultCraftDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * 재고확인
     */
    public String validEmp(MaWoResultCraftDetailDTO maWoResultCraftDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser){
    	QueryBuffer query = new QueryBuffer();
    	query.append("SELECT count(*) 			");
    	query.append("FROM TAWOCRAFT			");
    	query.append("WHERE 1=1					");
    	query.getAndQuery("wkor_id", maWoResultMstrCommonDTO.getWkOrId());
    	query.getAndQuery("emp_id", maWoResultCraftDetailDTO.getEmpId());
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
	@Override
	public List validTime(MaWoResultCraftDetailDTO maWoResultCraftDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user){
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT 									");
		query.append("    	 x.start_date AS startDate			");
		query.append("     , x.start_time AS startTime			");
		query.append("     , x.end_date   AS endDate			");
		query.append("     , x.end_time   AS endTime			");
		query.append("  FROM TAWOCRAFT x						");
		query.append(" WHERE 1 = 1								");
		query.getAndQuery("x.comp_no", user.getCompNo());
		query.getAndQuery("x.wkor_id", maWoResultMstrCommonDTO.getWkOrId());
		query.getAndQuery("x.emp_id", maWoResultCraftDetailDTO.getEmpId());
		
		if(maWoResultCraftDetailDTO.getWoCraftId() != null || !"".equals(maWoResultCraftDetailDTO.getWoCraftId()))
			query.append("   AND x.wocraft_id NOT IN ('"+ maWoResultCraftDetailDTO.getWoCraftId() +"')		");
		
		return getJdbcTemplate().queryForList(query.toString());
	}
}