package dream.mgr.usage.cal.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.mgr.usage.cal.dao.MgrUsageCalSetDayDAO;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDayDTO;

/**
 * 사용달력일별횟수설정 - 목록 dao
 * @author  youngjoo38
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="mgrUsageCalSetDayDAOTarget"
 * @spring.txbn id="mgrUsageCalSetDayDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrUsageCalSetDayDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrUsageCalSetDayDAO
{
    /**
     * grid find
     * @author youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrUsageCalSetDayDTO
     * @return List
     */
    public List findList(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user)
    { 
        QueryBuffer query = new QueryBuffer();

        query.append(getColums(mgrUsageCalSetDayDTO, user));
        query.append(getTables(mgrUsageCalSetDayDTO, user));
        query.append(this.getWhere(mgrUsageCalSetDayDTO, user));
        query.append(getOrderBy(mgrUsageCalSetDayDTO, user));
        
        return getJdbcTemplate().queryForList(query.toString(mgrUsageCalSetDayDTO.getIsLoadMaxCount(), mgrUsageCalSetDayDTO.getFirstRow()));
    } 
    

	@Override
	public String getColums(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
        query.append("SELECT        														");
        query.append("    	 ''          								seqNo     			");
        query.append("     , ''         								isDelCheck        	");
        query.append("     , x.lnwrktime_id                           	lnWrkTimeId			");
        query.append("     , SFAPLANTTODESC(x.comp_no, x.plant) 		plantDesc			");
        query.append("	   , x.wrk_date									wrkDate				");
        query.append("     , (SELECT full_desc                                   			");
        query.append("          FROM TAEQLOC                                       			");
        query.append("         WHERE comp_no = x.comp_no									");
        query.append("           AND eqloc_id = x.eqloc_id)           	lineDesc			");
        query.append("     , x.dtime 									dayRunTime			");
        query.append("     , x.ntime 									nightRunTime		");
        query.append("     , x.etime 									extraRunTime		");
        query.append("     , x.remark 									remark				");
        query.append("     , x.lnwrklist_id               				lnWrkListId       	");
        query.append("     , (SELECT a.lnwrklist_no FROM TALNWRKLIST a WHERE a.lnwrklist_id = x.lnwrklist_id)           lnWrkListNo		");
        query.append("     , (SELECT a.description FROM TALNWRKLIST a WHERE a.lnwrklist_id = x.lnwrklist_id)			lnWrkListDesc	");
        query.append("     , x.ucnt                       				UCNT              ");
        query.append("     , sum(nvl(x.ucnt,0)) OVER (partition by x.lnwrklist_id ORDER BY x.lnwrklist_id, x.wrk_date)	tot_ucnt        ");
        query.append("     , sum(nvl(x.dtime,0) + nvl(x.ntime,0) + nvl(x.etime,0)) OVER (partition by x.lnwrklist_id ORDER BY x.lnwrklist_id, x.wrk_date)	tot_run_time	");
        query.append("     , x.plant									plantId				");
        query.append("     , x.eqloc_id									lineId				");
        query.append("     , x.etime									evenRunTime			");
        
		return query.toString();
	}

	@Override
	public String getTables(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
        query.append("FROM   TALNWRKTIME x                                                  ");

		return query.toString();
	}

	@Override
	public String getOrderBy(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
        query.getOrderByQuery("x.plant, x.wrk_date, x.eqloc_id", mgrUsageCalSetDayDTO.getOrderBy(), mgrUsageCalSetDayDTO.getDirection());

		return query.toString();
	}
	
    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrUsageCalSetDayDTO
     * @return
     * @throws Exception
     */
    public String getWhere(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();

        query.append(" WHERE 1=1 		");
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.append("AND EXISTS (SELECT 1 FROM TALNWRKLIST a 					");
        query.append("             WHERE a.comp_no = x.comp_no 					");
        query.append("               AND a.lnwrklist_id = x.lnwrklist_id 		");
        query.getAndQuery("a.lnwrk_calendar_type", "U");
        query.append("            )												");
        
        if (!"".equals(mgrUsageCalSetDayDTO.getLnWrkTimeId()))
        {
            query.getAndQuery("x.lnwrktime_id", mgrUsageCalSetDayDTO.getLnWrkTimeId());
            return query.toString();
        }
        //일자
        query.getAndDateQuery("x.wrk_date", mgrUsageCalSetDayDTO.getFilterStartWrkDate(), mgrUsageCalSetDayDTO.getFilterEndWrkDate());
        // 공장 
        query.getPlantCdQuery("x.plant", mgrUsageCalSetDayDTO.getFilterPlantId(), mgrUsageCalSetDayDTO.getFilterPlantDesc(), user.getCompNo());
        
        //라인
        query.getEqLocLevelQuery("x.eqloc_id", mgrUsageCalSetDayDTO.getFilterLineId(), mgrUsageCalSetDayDTO.getFilterLineDesc(), user.getCompNo());
        
        // 가동달력
        query.getCodeLikeQuery("x.lnwrklist_id", "(SELECT a.description FROM TALNWRKLIST a WHERE a.lnwrklist_id = x.lnwrklist_id AND a.comp_no='"+user.getCompNo()+"')", 
                mgrUsageCalSetDayDTO.getFilterLnWrkListId(), mgrUsageCalSetDayDTO.getFilterLnWrkListDesc());
        
        return query.toString();
    }


    /**
     * detail insert
     * @author youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrUsageCalSetDayDTO
     * @return
     */
    public int[] insertDetail(final List<MgrUsageCalSetDayDTO> list, final User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TALNWRKTIME(									");
    	query.append("   comp_no,      lnwrktime_id,    plant,   wrk_date,		");
    	query.append("   remark, 	   lnwrklist_id, 	ucnt                    ");
    	query.append(")VALUES(							                        ");
    	query.append("	 ?,			   ?,		   ?,		    ?,              ");
    	query.append("	 ?,			   ?,          ?                            ");
    	query.append(")													        ");
    	
    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
            	MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO = list.get(i);
                
            	Object[] objects = new Object[] {
            			user.getCompNo()
            			,mgrUsageCalSetDayDTO.getLnWrkTimeId()
            			,mgrUsageCalSetDayDTO.getPlantId()
            			,CommonUtil.getRowDateToNum(mgrUsageCalSetDayDTO.getWrkDate())
            			,mgrUsageCalSetDayDTO.getRemark()
            			,mgrUsageCalSetDayDTO.getLnWrkListId()
            			,mgrUsageCalSetDayDTO.getUcnt()
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
    
    /**
     * detail update
     * @author youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrUsageCalSetDayDTO
     * @return
     */
    public int[] updateDetail(final List<MgrUsageCalSetDayDTO> list, final User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TALNWRKTIME SET	            ");
        query.append("        remark         = ?            ");
        query.append("       ,ucnt           = ?            ");
        query.append("       ,wrk_date       = ?            ");
        query.append("       ,lnwrklist_id   = ?            ");
    	query.append("WHERE  comp_no         = ?            ");
    	query.append("  AND  lnwrktime_id    = ?            ");
    	
    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO = list.get(i);
		
				Object[] objects = new Object[] {
		                mgrUsageCalSetDayDTO.getRemark()
		                ,mgrUsageCalSetDayDTO.getUcnt()
		                ,CommonUtil.getRowDateToNum(mgrUsageCalSetDayDTO.getWrkDate())
		                ,mgrUsageCalSetDayDTO.getLnWrkListId()
		                ,user.getCompNo()
		                ,mgrUsageCalSetDayDTO.getLnWrkTimeId()
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
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param mgrUsageCalSetDayDTO
     * @return
     */
    public String validLineRunPlan(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*)                                           	");
        query.append("FROM   TALNWRKTIME x                                        	");
        query.append("WHERE  x.comp_no   	= '" + user.getCompNo()   + "'			");
        query.append("  AND  x.wrk_date		= '" + CommonUtil.getRowDateToNum(mgrUsageCalSetDayDTO.getWrkDate())+ "'		");
        query.append("  AND  x.lnwrklist_id = '" + mgrUsageCalSetDayDTO.getLnWrkListId()   + "'	");
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    
    }
    
    /**
     * 삭제
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param id
     * @return
     */
    public int[] deleteList(final List<MgrUsageCalSetDayDTO> list, final User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TALNWRKTIME            ");
        query.append("WHERE  comp_no      = ?       ");
        query.append("  AND  lnwrktime_id = ?		");
        
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO = list.get(i);
				
				Object[] objects = new Object[] {   
		                user.getCompNo()
		                , mgrUsageCalSetDayDTO.getLnWrkTimeId()
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
	public String findTotalCount(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user)
	{
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT                             ");
        query.append("       COUNT(1)                    ");
        query.append(getTables(mgrUsageCalSetDayDTO, user));
        query.append(this.getWhere(mgrUsageCalSetDayDTO, user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
		return QueryBuffer.listToString(resultList);
	}
}