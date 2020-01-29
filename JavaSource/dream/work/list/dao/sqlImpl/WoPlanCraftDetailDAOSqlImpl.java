package dream.work.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.work.list.dao.WoPlanCraftDetailDAO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanCraftDetailDTO;

/**
 * 작업계획목록 - 작업자 상세 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="woPlanCraftDetailDAOTarget"
 * @spring.txbn id="woPlanCraftDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WoPlanCraftDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements WoPlanCraftDetailDAO
{
    /**
     * detail find
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param wkOrId
     * @param woCraftId
     * @param compNo
     * @return
     */
    public WoPlanCraftDetailDTO findDetail(String wkOrId, String woCraftId, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT											");
        query.append("       x.wkor_id 				wkOrId,				");
        query.append("       x.woplancraft_id 		woCraftId,			");
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
        query.append("FROM   TAWOPLANCRAFT x							");
        query.append("WHERE	 x.woplancraft_id 		= '"+woCraftId+"'	");
        query.append("  AND  x.comp_no			= '"+compNo+"'			");
    
        WoPlanCraftDetailDTO woPlanCraftDetailDTO = 
        		(WoPlanCraftDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new WoPlanCraftDetailDTO()));
        
        return woPlanCraftDetailDTO;
    }
    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCraftDetailDTO
     * @param woPlanCommonDTO
     * @return
     */
    public int updateDetail(WoPlanCraftDetailDTO woPlanCraftDetailDTO, WoPlanCommonDTO woPlanCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAWOPLANCRAFT SET			");
    	query.append("	emp_id					= ?,	");
    	query.append("	start_date				= ?,	");
    	query.append("	start_time				= ?,	");
    	query.append("	end_date				= ?,	");
    	query.append("	end_time				= ?,	");
    	query.append("	work_time				= ?,	");
    	query.append("	remark					= ?		");
    	query.append("WHERE woplancraft_id		= ?		");
    	query.append("  AND comp_no			    = ?		");
    	
    	Object[] objects = new Object[] {
    			woPlanCraftDetailDTO.getEmpId(),
    			woPlanCraftDetailDTO.getStartDate(),
    			woPlanCraftDetailDTO.getStartTime(),
    			woPlanCraftDetailDTO.getEndDate(),
    			woPlanCraftDetailDTO.getEndTime(),
    			woPlanCraftDetailDTO.getWorkTime(),
    			woPlanCraftDetailDTO.getRemark(),
    			woPlanCraftDetailDTO.getWoCraftId(),
    			woPlanCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCraftDetailDTO
     * @param woPlanCommonDTO
     * @return
     */
    public int insertDetail(WoPlanCraftDetailDTO woPlanCraftDetailDTO, WoPlanCommonDTO woPlanCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAWOPLANCRAFT				");
    	query.append("	(comp_no,		woplancraft_id,		");
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
    			woPlanCommonDTO.getCompNo(),
    			woPlanCraftDetailDTO.getWoCraftId(),
    			woPlanCommonDTO.getWkOrId(),
    			woPlanCraftDetailDTO.getEmpId(),
    			woPlanCraftDetailDTO.getStartDate(),
    			woPlanCraftDetailDTO.getStartTime(),
    			woPlanCraftDetailDTO.getEndDate(),
    			woPlanCraftDetailDTO.getEndTime(),
    			woPlanCraftDetailDTO.getWorkTime(),
    			woPlanCraftDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * 재고확인
     */
    public String validEmp(WoPlanCraftDetailDTO woPlanCraftDetailDTO, WoPlanCommonDTO woPlanCommonDTO, User loginUser){
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.append("SELECT count(*) 			");
    	query.append("FROM TAWOPLANCRAFT x		");
    	query.append("WHERE 1=1					");
    	query.getAndQuery("x.wkor_id", woPlanCommonDTO.getWkOrId());
    	query.getAndQuery("x.emp_id", woPlanCraftDetailDTO.getEmpId());
    	return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
}