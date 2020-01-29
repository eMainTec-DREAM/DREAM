package dream.work.pm.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.work.pm.list.dao.WorkPmPointUInsListDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * 사용량 항목 - List DAO implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workPmPointUInsListDAOTarget"
 * @spring.txbn id="workPmPointUInsListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmPointUInsListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkPmPointUInsListDAO
{
	public List findList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();
       
    	query.append("	SELECT   ''                          	seqNo				");
    	query.append("     		,''                          	isDelCheck          ");
    	query.append("     		,x.pm_point_id                  pmPointId    		");
    	query.append("     		,x.step_num                    	stepNum    			");
    	query.append("     		,x.STD_CHECK_POINT_ID           stdCheckPointId		");
    	query.append("		    , (SELECT check_point								");
    	query.append("			FROM TASTDCHECKPOINT								");
    	query.append("			WHERE comp_no = x.comp_no							");
    	query.append("			and STD_CHECK_POINT_ID = x.STD_CHECK_POINT_ID)		stdCheckPointDesc	");
    	query.append("     		,x.CHECK_POINT                  checkPoint			");
    	query.append("     		,x.UOM                          uom					");
    	query.append("     		,x.FIT_RATE                    	fitRate				");
    	query.append("     		,x.IS_RUN_VALUE                	isRunValue			");
    	query.append("     		,x.IS_ACTIVE                    isActive			");
    	query.append("     		,x.REMARK                       remark				");
    	query.append("	FROM 	TAPMPOINT x											");
        query.append("  WHERE 1=1        											");
        query.append(this.getWhere(maPmMstrCommonDTO, user));
        query.getOrderByQuery("x.pm_point_id", maPmMstrCommonDTO.getOrderBy(), maPmMstrCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(maPmMstrCommonDTO.getIsLoadMaxCount(), maPmMstrCommonDTO.getFirstRow()));
    }

	private String getWhere(MaPmMstrCommonDTO maPmMstrCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
    	query.getAndNumKeyQuery("x.pm_id", maPmMstrCommonDTO.getPmId());
		query.getStringEqualQuery("x.IS_DELETED", "N");
		
		// 회사
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        
        if (!"".equals(maPmMstrCommonDTO.getPmPointId()))
        {
            query.getAndQuery("x.pm_point_id", maPmMstrCommonDTO.getPmPointId());
            return query.toString();
        }
        
        return query.toString();
    } 

    public int deleteList(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAPMPOINT SET			");
    	query.append("      IS_DELETED 		= ?		");
    	query.append("    , IS_ACTIVE 		= ?		");
    	query.append("    , DELETE_BY 		= ?		");
    	query.append("    , DELETE_TIME 	= ?		");
    	query.append("WHERE COMP_NO 		= ?		");
    	query.append("  AND PM_POINT_ID 	= ?		");
        
        Object[] objects = new Object[] {   
        		"Y"
        		,"N"
        		,user.getUserId()
        		,DateUtil.getDate()
                ,user.getCompNo()
                ,id
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT									");
        query.append("       COUNT(1)							");
        query.append("FROM TAPMPOINT x							");
        query.append("WHERE 1=1									");
        query.append(this.getWhere(maPmMstrCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
    }
}