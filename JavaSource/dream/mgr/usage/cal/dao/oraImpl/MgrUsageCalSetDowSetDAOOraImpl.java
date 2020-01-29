package dream.mgr.usage.cal.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.mgr.usage.cal.dao.MgrUsageCalSetDowSetDAO;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDowSetDTO;

/**
 * 요일별 설정 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="mgrUsageCalSetDowSetDAOTarget"
 * @spring.txbn id="mgrUsageCalSetDowSetDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrUsageCalSetDowSetDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrUsageCalSetDowSetDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrUsageCalSetDowSetDTO
     * @param mgrUsageCalSetDowSetDTO
     * @param loginUser
     * @return List
     */
    public List findDowList(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(getColums(mgrUsageCalSetDowSetDTO, loginUser));
        query.append(getTables(mgrUsageCalSetDowSetDTO, loginUser));
        query.append(this.getWhere(mgrUsageCalSetDowSetDTO,loginUser));
        query.append(getOrderBy(mgrUsageCalSetDowSetDTO, loginUser));
        
        return getJdbcTemplate().queryForList(query.toString(mgrUsageCalSetDowSetDTO.getIsLoadMaxCount(), mgrUsageCalSetDowSetDTO.getFirstRow()));
    }
    
	@Override
	public String getColums(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User loginUser) {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT																						");
        query.append("       '' 																seqNo				");
        query.append("       ,'' isDelCheck																			");
        query.append("       ,x.eqloc_id 														eqLocId				");
        query.append("       ,x.comp_no             											compNo            	");
        query.append("       ,x.eqlocdowrun_id 													eqLocDowRunId		");
        query.append("       ,x.dow 															dow					");
        query.append("		 ,SFACODE_TO_DESC(x.dow,'DOW','SYS','','"+loginUser.getLangId()+"') dowDesc				");
        query.append("       ,x.dtime															dayRunTime			");
        query.append("       ,x.ntime															nightRunTime		");
        query.append("       ,x.etime															extraRunTime		");
        query.append("       ,x.is_use 															isUse				");
        query.append("       ,x.ucnt 															UCNT				");
        query.append("       ,x.lnwrklist_id													lnWrkListId			");
        query.append("       ,x.ord_no															ordNo				");
	    query.append("       ,x.remark															remark				");
        
		return query.toString();
	}

	@Override
	public String getTables(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
		query.append("FROM   TAEQLOCDOWRUN x																		");
		
		return query.toString();
	}

	@Override
	public String getOrderBy(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
		query.getOrderByQuery("x.ord_no", mgrUsageCalSetDowSetDTO.getOrderBy(), mgrUsageCalSetDowSetDTO.getDirection());		
		
		return query.toString();
	}

	public String getWhere(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("WHERE 1 = 1	");
    	
    	query.getAndQuery("x.lnwrklist_id", mgrUsageCalSetDowSetDTO.getLnWrkListId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(mgrUsageCalSetDowSetDTO.getEqLocDowRunId()))
        {
            query.getAndQuery("x.eqlocdowrun_id", mgrUsageCalSetDowSetDTO.getEqLocDowRunId());
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
    public int[] deleteDowList(final List<MgrUsageCalSetDowSetDTO> list , final User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("DELETE FROM TAEQLOCDOWRUN			");
    	query.append("WHERE  eqlocdowrun_id 	= ?		");
    	query.append("  AND  comp_no		 	= ?		");

    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO = list.get(i);
				
		    	Object[] objects = new Object[] {
		    			mgrUsageCalSetDowSetDTO.getEqLocDowRunId()
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
    
    /**
     * 요일 중복 검사
     */
    public String validDay(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user){
    	QueryBuffer query = new QueryBuffer();
    	query.append("SELECT count(*) 				");
    	query.append("FROM TAEQLOCDOWRUN			");
    	query.append("WHERE 1=1						");
    	query.getStringEqualQuery("comp_no", user.getCompNo());
    	query.getStringEqualQuery("dow", mgrUsageCalSetDowSetDTO.getDow());
    	query.getAndNumKeyQuery("lnwrklist_id", mgrUsageCalSetDowSetDTO.getLnWrkListId());

    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    public int deleteWrkTime(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("DELETE FROM TALNWRKTIME x							");
    	query.append("WHERE 1=1											");
    	query.append("  AND x.wrk_date >= TO_CHAR(SYSDATE,'YYYYMMDD')	");
    	query.getAndNumKeyQuery("x.lnwrklist_id", mgrUsageCalSetDowSetDTO.getLnWrkListId());
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
        
        return rtnValue;
    }
    public int execRunTime(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query = new QueryBuffer();
        
        query.append("begin                                                 ");
        query.append("SP_MAKE_TALNWRKTIME('"+user.getCompNo()+"','"+mgrUsageCalSetDowSetDTO.getLnWrkListId()+"'); ");
        query.append("end;                                                  ");
        
        this.getJdbcTemplate().execute(query.toString());
        
        return rtnValue;
        
    }

	@Override
	public String findTotalCount(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT								");
        query.append("    COUNT(1)							");
        query.append(getTables(mgrUsageCalSetDowSetDTO, user));
        query.append(this.getWhere(mgrUsageCalSetDowSetDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);

	}

	@Override
	public int[] updateDetail(final List<MgrUsageCalSetDowSetDTO> list, final User user) throws Exception {
		QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAEQLOCDOWRUN SET		");
    	query.append("	dtime				= ?	    ");
    	query.append("	,ntime				= ?	    ");
    	query.append("	,etime				= ?	    ");
    	query.append("	,is_use				= ?	    ");
    	query.append("	,ord_no				= ?		");
    	query.append("	,ucnt				= ?		");
    	query.append("	,remark				= ?		");
    	query.append("WHERE eqlocdowrun_id	= ?		");
    	query.append("  AND comp_no			= ?		");
    	
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
            	MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO = list.get(i);

            	Object[] objects = new Object[] {
            			mgrUsageCalSetDowSetDTO.getDayRunTime()
            			,mgrUsageCalSetDowSetDTO.getNightRunTime()
            			,mgrUsageCalSetDowSetDTO.getExtraRunTime()
            			,mgrUsageCalSetDowSetDTO.getIsUse()
            			,mgrUsageCalSetDowSetDTO.getOrdNo()
            			,mgrUsageCalSetDowSetDTO.getUcnt()
            			,mgrUsageCalSetDowSetDTO.getRemark()
            			,mgrUsageCalSetDowSetDTO.getEqLocDowRunId()
            			,user.getCompNo()
            	};
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
            @Override
            public int getBatchSize()
            {
                return list.size();
            }
        });
	}

	@Override
	public int[] insertDetail(final List<MgrUsageCalSetDowSetDTO> list, final User user) throws Exception {
		QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAEQLOCDOWRUN				");
    	query.append("	(comp_no,		eqlocdowrun_id,		");
    	query.append("	 lnwrklist_id,	dow,				");
    	query.append("	 dtime,			ntime,				");
    	query.append("	 etime,			is_use,				");
    	query.append("	 ord_no			, ucnt  			");
    	query.append("	,remark								");
    	query.append("	)	VALUES							");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,             ?					");
    	query.append("	 ,?             					");
    	query.append("	)									");
    	
    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
		{
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException
			{
				MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO = list.get(i);
				
				Object[] objects = new Object[] {
		    			user.getCompNo()
		    			,mgrUsageCalSetDowSetDTO.getEqLocDowRunId()
		    			,mgrUsageCalSetDowSetDTO.getLnWrkListId()
		    			,mgrUsageCalSetDowSetDTO.getDow()
		    			,mgrUsageCalSetDowSetDTO.getDayRunTime()
		    			,mgrUsageCalSetDowSetDTO.getNightRunTime()
		    			,mgrUsageCalSetDowSetDTO.getExtraRunTime()
		    			,mgrUsageCalSetDowSetDTO.getIsUse()
		    			,mgrUsageCalSetDowSetDTO.getOrdNo()
		    			,mgrUsageCalSetDowSetDTO.getUcnt()
		    			,mgrUsageCalSetDowSetDTO.getRemark()
		    	};
				
				for(int j=0;j<objects.length;j++){
					ps.setObject(j+1, objects[j]);
				}
			}
			
			@Override
			public int getBatchSize()
			{
				return list.size();
			}
		});
	}
}