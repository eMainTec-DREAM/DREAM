package dream.mgr.cal.dao.sqlImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mgr.cal.dao.MgrCalLineTimeDowSetDAO;
import dream.mgr.cal.dto.MgrCalLineTimeDowSetDTO;

/**
 * 요일별 설정 목록 dao
 * @author  euna0207
 * @version $Id: MgrCalLineTimeDowSetDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
 * @since   1.0
 * @spring.bean id="mgrCalLineTimeDowSetDAOTarget"
 * @spring.txbn id="mgrCalLineTimeDowSetDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrCalLineTimeDowSetDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrCalLineTimeDowSetDAO
{
    /**
     * grid find
     * @author  euna0207
     * @version $Id: MgrCalLineTimeDowSetDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
     * @since   1.0
     * 
     * @param mgrCalLineTimeSetDTO
     * @param mgrCalLineTimeDowSetDTO
     * @param loginUser
     * @return List
     */
    public List findDowList(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append(getColums(mgrCalLineTimeDowSetDTO, loginUser));
        query.append(getTables(mgrCalLineTimeDowSetDTO, loginUser));
        query.append(this.getWhere(mgrCalLineTimeDowSetDTO,loginUser));
        query.append(getOrderBy(mgrCalLineTimeDowSetDTO, loginUser));
        
        return getJdbcTemplate().queryForList(query.toString(mgrCalLineTimeDowSetDTO.getIsLoadMaxCount(), mgrCalLineTimeDowSetDTO.getFirstRow()));
    }
    
    

	@Override
	public String getColums(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("SELECT																						");
	    query.append("       '' 																	seqNo			");
	    query.append("       ,'' isDelCheck																			");
	    query.append("       ,x.eqloc_id 															eqLocId			");
	    query.append("       ,x.comp_no             												compNo          ");
	    query.append("       ,x.eqlocdowrun_id 														eqLocDowRunId	");
	    query.append("       ,x.dow 																dow				");
	    query.append("		 ,dbo.SFACODE_TO_DESC(x.dow,'DOW','SYS','','"+user.getLangId()+"') 		dowDesc			");
	    query.append("       ,x.dtime																dayRunTime		");
	    query.append("       ,x.ntime																nightRunTime	");
	    query.append("       ,x.etime																extraRunTime	");
	    query.append("       ,x.is_use 																isUse			");
	    query.append("       ,x.ucnt 																UCNT			");
	    query.append("       ,x.lnwrklist_id														lnWrkListId		");
	    query.append("       ,x.ord_no																ordNo			");
	    
		return query.toString();

	}
	@Override
	public String getTables(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("FROM   TAEQLOCDOWRUN x																		");
		
		return query.toString();
	}
	@Override
	public String getOrderBy(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.getOrderByQuery("x.eqlocdowrun_id", "x.ord_no", mgrCalLineTimeDowSetDTO.getOrderBy(), mgrCalLineTimeDowSetDTO.getDirection());		
		
		return query.toString();
	}
    
    
    public String getWhere(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("WHERE 1 = 1	");
    	
    	query.getAndQuery("x.lnwrklist_id", mgrCalLineTimeDowSetDTO.getLnWrkListId());
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(mgrCalLineTimeDowSetDTO.getEqLocDowRunId()))
        {
            query.getAndQuery("x.eqlocdowrun_id", mgrCalLineTimeDowSetDTO.getEqLocDowRunId());
            return query.toString();
        }
    	
    	return query.toString();
    }
    
    /**
     * delete
     * @author euna0207
     * @version $Id: MgrCalLineTimeDowSetDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int[] deleteDowList(final List<MgrCalLineTimeDowSetDTO> list , final User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("DELETE FROM TAEQLOCDOWRUN			");
    	query.append("WHERE  eqlocdowrun_id 	= ?		");
    	query.append("  AND  comp_no		 	= ?		");

    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO = list.get(i);
				
		    	Object[] objects = new Object[] {
		    			mgrCalLineTimeDowSetDTO.getEqLocDowRunId()
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
    public String validDay(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user){
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.append("SELECT count(*) 				");
    	query.append("FROM TAEQLOCDOWRUN			");
    	query.append("WHERE 1=1						");
    	query.getStringEqualQuery("comp_no", mgrCalLineTimeDowSetDTO.getCompNo());
    	query.getStringEqualQuery("dow", mgrCalLineTimeDowSetDTO.getDow());
    	query.getAndNumKeyQuery("lnwrklist_id", mgrCalLineTimeDowSetDTO.getLnWrkListId());

    	return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    public int deleteWrkTime(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("DELETE FROM TALNWRKTIME							");
    	query.append("WHERE 1=1											");
    	query.append("  AND wrk_date >= CONVERT(VARCHAR, GETDATE(), 112)	");
    	query.getAndNumKeyQuery("lnwrklist_id", mgrCalLineTimeDowSetDTO.getLnWrkListId());
    	query.getStringEqualQuery("comp_no", user.getCompNo());
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
        
        return rtnValue;
    }
    public int execRunTime(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query = new QuerySqlBuffer();
        
        query.append("EXEC dbo.SP_MAKE_TALNWRKTIME '"+mgrCalLineTimeDowSetDTO.getCompNo()+"','"+mgrCalLineTimeDowSetDTO.getLnWrkListId()+"'; ");
        
        this.getJdbcTemplate().execute(query.toString());
        
        return rtnValue;
        
    }
    
	@Override
	public String findTotalCount(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user) {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
		query.append("SELECT								");
        query.append("    COUNT(1)							");
        query.append(getTables(mgrCalLineTimeDowSetDTO, user));
        query.append(this.getWhere(mgrCalLineTimeDowSetDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
	}
	@Override
	public int[] updateDetail(final List<MgrCalLineTimeDowSetDTO> list, final User user) throws Exception {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.append("UPDATE TAEQLOCDOWRUN SET		");
    	query.append("	dtime				= ?	    ");
    	query.append("	,ntime				= ?	    ");
    	query.append("	,etime				= ?	    ");
    	query.append("	,is_use				= ?	    ");
    	query.append("	,ord_no				= ?		");
    	query.append("	,ucnt				= ?		");
    	query.append("WHERE eqlocdowrun_id	= ?		");
    	query.append("  AND comp_no			= ?		");
    	
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
            	MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO = list.get(i);

            	Object[] objects = new Object[] {
            			mgrCalLineTimeDowSetDTO.getDayRunTime()
            			,mgrCalLineTimeDowSetDTO.getNightRunTime()
            			,mgrCalLineTimeDowSetDTO.getExtraRunTime()
            			,mgrCalLineTimeDowSetDTO.getIsUse()
            			,mgrCalLineTimeDowSetDTO.getOrdNo()
            			,mgrCalLineTimeDowSetDTO.getUcnt()
            			,mgrCalLineTimeDowSetDTO.getEqLocDowRunId()
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
	public int[] insertDetail(final List<MgrCalLineTimeDowSetDTO> list, final User user) throws Exception {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("INSERT INTO TAEQLOCDOWRUN				");
    	query.append("	(comp_no,		eqlocdowrun_id,		");
    	query.append("	 lnwrklist_id,		dow,			");
    	query.append("	 dtime,			ntime,				");
    	query.append("	 etime,			is_use,				");
    	query.append("	 ord_no, ucnt    					");
    	query.append("	)	VALUES							");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,             ?					");
    	query.append("	)									");
    	
    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
		{
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException
			{
				MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO = list.get(i);
				
				Object[] objects = new Object[] {
		    			user.getCompNo()
		    			,mgrCalLineTimeDowSetDTO.getEqLocDowRunId()
		    			,mgrCalLineTimeDowSetDTO.getLnWrkListId()
		    			,mgrCalLineTimeDowSetDTO.getDow()
		    			,mgrCalLineTimeDowSetDTO.getDayRunTime()
		    			,mgrCalLineTimeDowSetDTO.getNightRunTime()
		    			,mgrCalLineTimeDowSetDTO.getExtraRunTime()
		    			,mgrCalLineTimeDowSetDTO.getIsUse()
		    			,mgrCalLineTimeDowSetDTO.getOrdNo()
		    			,mgrCalLineTimeDowSetDTO.getUcnt()
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