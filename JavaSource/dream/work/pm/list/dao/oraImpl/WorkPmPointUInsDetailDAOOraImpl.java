package dream.work.pm.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.pm.list.dao.WorkPmPointUInsDetailDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmPointUInsDetailDTO;

/**
 * 사용량 항목 - Detail DAO implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workPmPointUInsDetailDAOTarget"
 * @spring.txbn id="workPmPointUInsDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmPointUInsDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkPmPointUInsDetailDAO
{
	@Override
    public WorkPmPointUInsDetailDTO findDetail(MaPmMstrCommonDTO maPmMstrMstrCommonDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
    	query.append("SELECT												");
    	query.append("          x.PM_POINT_ID			pmPointId			");
    	query.append("        , x.PM_ID					pmId				");
    	query.append("        , x.STEP_NUM				stepNum				");
    	query.append("        , x.STD_CHECK_POINT_ID	stdCheckPointId		");
    	query.append("		  , (SELECT check_point							");
    	query.append("			FROM TASTDCHECKPOINT						");
    	query.append("			WHERE comp_no = x.comp_no					");
    	query.append("			and STD_CHECK_POINT_ID = x.STD_CHECK_POINT_ID)		stdCheckPointDesc	");
    	query.append("        , x.CHECK_POINT			checkPoint			");
    	query.append("        , x.UOM					uom					");
    	query.append("        , x.FIT_RATE				fitRate				");
    	query.append("        , x.IS_RUN_VALUE			isRunValue			");
    	query.append("        , x.IS_ACTIVE				isActive			");
    	query.append("        , x.REMARK				remark				");
    	query.append("FROM TAPMPOINT x										");
    	query.append("WHERE comp_no = ?										");
    	query.append("  AND pm_point_id = ?									");
    	query.append("  AND is_deleted = ?									");

        Object[] objects = new Object[] {
                user.getCompNo()
                , maPmMstrMstrCommonDTO.getPmPointId()
                , "N"
    	};
    
        WorkPmPointUInsDetailDTO workPmPointUInsDetailDTO = 
        		(WorkPmPointUInsDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new WorkPmPointUInsDetailDTO()));
        
        return workPmPointUInsDetailDTO;
        
    }
    
	@Override
    public int insertDetail(WorkPmPointUInsDetailDTO workPmPointUInsDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("INSERT INTO TAPMPOINT(			");
    	query.append("	 COMP_NO						");
    	query.append(" , PM_POINT_ID					");
    	query.append(" , PM_ID							");
    	query.append(" , STEP_NUM						");
    	query.append(" , STD_CHECK_POINT_ID				");
    	query.append(" , CHECK_POINT					");
    	query.append(" , UOM							");
    	query.append(" , FIT_RATE						");
    	query.append(" , IS_RUN_VALUE					");
    	query.append(" , IS_ACTIVE						");
    	query.append(" , REMARK							");
    	query.append(" , IS_DELETED						");
    	query.append(" )								");
    	query.append("VALUES (							");
    	query.append("	 ?								");
    	query.append(" , ?								");
    	query.append(" , ?								");
    	query.append(" , ?								");
    	query.append(" , ?								");
    	query.append(" , ?								");
    	query.append(" , ?								");
    	query.append(" , ?								");
    	query.append(" , ?								");
    	query.append(" , ?								");
    	query.append(" , ?								");
    	query.append(" , ?								");
    	query.append(" )								");
    	
    	Object[] objects = new Object[] {
    			 user.getCompNo()
    			 ,workPmPointUInsDetailDTO.getPmPointId()
    			 ,workPmPointUInsDetailDTO.getPmId()
    			 ,workPmPointUInsDetailDTO.getStepNum()
    			 ,workPmPointUInsDetailDTO.getStdCheckPointId()
    			 ,workPmPointUInsDetailDTO.getCheckPoint()
    			 ,workPmPointUInsDetailDTO.getUom()
    			 ,workPmPointUInsDetailDTO.getFitRate()
    			 ,workPmPointUInsDetailDTO.getIsRunValue()
    			 ,workPmPointUInsDetailDTO.getIsActive()
    			 ,workPmPointUInsDetailDTO.getRemark()
    			 ,"N"
    	};
    	
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    
	@Override
    public int updateDetail(WorkPmPointUInsDetailDTO workPmPointUInsDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAPMPOINT SET					");
        query.append("      STEP_NUM               = ?		");
        query.append("    , STD_CHECK_POINT_ID     = ?		");
        query.append("    , CHECK_POINT            = ?		");
        query.append("    , UOM                    = ?		");
        query.append("    , FIT_RATE               = ?		");
        query.append("    , IS_RUN_VALUE           = ?		");
        query.append("    , IS_ACTIVE              = ?		");
        query.append("    , REMARK                 = ?		");
        query.append("WHERE COMP_NO = ?						");
        query.append("  AND PM_POINT_ID = ?					");

    	Object[] objects = new Object[] {
   			 	workPmPointUInsDetailDTO.getStepNum()
   			 	,workPmPointUInsDetailDTO.getStdCheckPointId()
   			 	,workPmPointUInsDetailDTO.getCheckPoint()
   			 	,workPmPointUInsDetailDTO.getUom()
   			 	,workPmPointUInsDetailDTO.getFitRate()
   			 	,workPmPointUInsDetailDTO.getIsRunValue()
   			 	,workPmPointUInsDetailDTO.getIsActive()
   			 	,workPmPointUInsDetailDTO.getRemark()
   			 	,user.getCompNo()
   			 	,workPmPointUInsDetailDTO.getPmPointId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }

	@Override
	public String setStepNum(WorkPmPointUInsDetailDTO workPmPointUInsDetailDTO, User user) throws Exception {
		QueryBuffer query = new QueryBuffer();
		query.append("SELECT NVL(MAX(step_num)+10,100)	");
		query.append("FROM TAPMPOINT					");
		query.append("WHERE 1=1							");
		query.getAndQuery("pm_id", workPmPointUInsDetailDTO.getPmId());
    	query.getAndQuery("comp_no", user.getCompNo());
    	query.getAndQuery("is_active", "Y");
    	query.getAndQuery("is_deleted", "N");
		
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
	}


}