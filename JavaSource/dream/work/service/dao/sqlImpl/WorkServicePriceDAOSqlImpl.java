package dream.work.service.dao.sqlImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.google.gson.Gson;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.work.service.dao.WorkServicePriceDAO;
import dream.work.service.dto.WorkServicePriceDTO;

/**
 * 서비스 설정 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="workServicePriceDAOTarget"
 * @spring.txbn id="workServicePriceDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkServicePriceDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkServicePriceDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrContractDTO
     * @param workServicePriceDTO
     * @param loginUser
     * @return List
     */
    public List findList(WorkServicePriceDTO workServicePriceDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append(getColums(workServicePriceDTO, loginUser));
        query.append(getTables(workServicePriceDTO, loginUser));
        query.append(this.getWhere(workServicePriceDTO,loginUser));
        query.append(getOrderBy(workServicePriceDTO, loginUser));
        
        return getJdbcTemplate().queryForList(query.toString(workServicePriceDTO.getIsLoadMaxCount(), workServicePriceDTO.getFirstRow()));
    }
    
    

	@Override
	public String getColums(WorkServicePriceDTO workServicePriceDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT														");
		query.append("    '' 										seqNo			");
		query.append("    , '' 										isDelCheck		");
		query.append("    , x.serviceprice_id 						servicePriceId	");
		query.append("    , x.service_id 							serviceId		");
		query.append("	  , substring(x.start_month,0,5) + '-' + substring(x.start_month,5,2)		");
		query.append("      + ' ~ ' +												");
		query.append("        substring(x.end_month,0,5) + '-' + substring(x.end_month,5,2)     periodMonth		");
		query.append("    , x.unit_price 							unitPrice		");
		query.append("    , x.remark 								remark			");
		query.append("    , x.start_month 							fromDate		");
		query.append("    , x.end_month 							toDate			");
	    
		return query.toString();

	}
	@Override
	public String getTables(WorkServicePriceDTO workServicePriceDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("FROM TASERVICEPRICE x											");
		
		return query.toString();
	}
	@Override
	public String getOrderBy(WorkServicePriceDTO workServicePriceDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.getOrderByQuery("ISNULL(x.start_month,x.serviceprice_id)", workServicePriceDTO.getOrderBy(), workServicePriceDTO.getDirection());		
		
		return query.toString();
	}
    
    
    public String getWhere(WorkServicePriceDTO workServicePriceDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("WHERE 1 = 1	");
    	
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	query.getAndQuery("x.service_id", workServicePriceDTO.getServiceId());
    	
    	if (!"".equals(workServicePriceDTO.getServicePriceId()))
        {
            query.getAndQuery("x.serviceprice_id", workServicePriceDTO.getServicePriceId());
            return query.toString();
        }
    	
    	return query.toString();
    }
    
    /**
     * delete
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int[] deleteList(final List<WorkServicePriceDTO> list , final User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("DELETE FROM TASERVICEPRICE		");
    	query.append("WHERE  serviceprice_id 	= ?		");
    	query.append("  AND  comp_no		 	= ?		");

    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				WorkServicePriceDTO workServicePriceDTO = list.get(i);
				
		    	Object[] objects = new Object[] {
		    			workServicePriceDTO.getServicePriceId()
		    			,user.getCompNo()
		    	};
				
				for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
			}
    		
			@Override
			public int getBatchSize() {
				return list.size();
			}
    	});
    }
    
	@Override
	public String findTotalCount(WorkServicePriceDTO workServicePriceDTO, User user) {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
		query.append("SELECT								");
        query.append("    COUNT(1)							");
        query.append(getTables(workServicePriceDTO, user));
        query.append(this.getWhere(workServicePriceDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
	}
	@Override
	public int[] updateDetail(final List<WorkServicePriceDTO> list, final User user) throws Exception {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TASERVICEPRICE SET		");
    	query.append("	start_month			= ?	    ");
    	query.append("	,end_month			= ?	    ");
    	query.append("	,unit_price			= ?		");
    	query.append("	,remark				= ?		");
    	query.append("	,upd_time			= ?		");
    	query.append("	,upd_by				= ?		");
    	query.append("WHERE serviceprice_id	= ?		");
    	query.append("  AND comp_no			= ?		");
    	
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
            	WorkServicePriceDTO workServicePriceDTO = list.get(i);

            	Object[] objects = new Object[] {
            			CommonUtil.getRowDateToNum(workServicePriceDTO.getFromDate())
            			,CommonUtil.getRowDateToNum(workServicePriceDTO.getToDate())
            			,CommonUtil.getRowMoneyToNum(workServicePriceDTO.getUnitPrice())
            			,workServicePriceDTO.getRemark()
            			,workServicePriceDTO.getUpdTime()
            			,user.getEmpId()
            			,workServicePriceDTO.getServicePriceId()
            			,user.getCompNo()
            	};
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, getObject(objects[j]));
                }
                logger.debug(new Gson().toJson(objects));
            }
            
            @Override
            public int getBatchSize()
            {
                return list.size();
            }
        });
	}
	
	@Override
	public int[] insertDetail(final List<WorkServicePriceDTO> list, final User user) throws Exception {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("INSERT INTO TASERVICEPRICE			");
    	query.append("	(comp_no		,serviceprice_id	");
    	query.append("	,service_id		,start_month		");
    	query.append("	,end_month		,unit_price			");
    	query.append("	,remark								");
    	query.append("	,cre_time		,cre_by  			");
    	query.append("	,upd_time		,upd_by  			");
    	query.append("	)	VALUES							");
    	query.append("	( ?				,?					");
    	query.append("	 ,?				,?					");
    	query.append("	 ,?				,?					");
    	query.append("	 ,?									");
    	query.append("	 ,?             ,?					");
    	query.append("	 ,?             ,?					");
    	query.append("	)									");
    	
    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
		{
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException
			{
				WorkServicePriceDTO workServicePriceDTO = list.get(i);
				
				Object[] objects = new Object[] {
						user.getCompNo()
		    			,workServicePriceDTO.getServicePriceId()
		    			,workServicePriceDTO.getServiceId()
            			,CommonUtil.getRowDateToNum(workServicePriceDTO.getFromDate())
            			,CommonUtil.getRowDateToNum(workServicePriceDTO.getToDate())
            			,CommonUtil.getRowMoneyToNum(workServicePriceDTO.getUnitPrice())
		    			,workServicePriceDTO.getRemark()
            			,workServicePriceDTO.getCreTime()
            			,user.getEmpId()
            			,workServicePriceDTO.getUpdTime()
            			,user.getEmpId()
		    	};
				
				for(int j=0;j<objects.length;j++){
					ps.setObject(j+1, getObject(objects[j]));
				}
                logger.debug(new Gson().toJson(objects));
			}
			
			@Override
			public int getBatchSize()
			{
				return list.size();
			}
		});
	}
}