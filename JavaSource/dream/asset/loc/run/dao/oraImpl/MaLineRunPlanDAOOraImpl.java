package dream.asset.loc.run.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.asset.loc.run.dao.MaLineRunPlanDAO;
import dream.asset.loc.run.dto.MaLineRunPlanDTO;

/**
 * 라인가동계획 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maLineRunPlanDAOTarget"
 * @spring.txbn id="maLineRunPlanDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaLineRunPlanDAOOraImpl extends BaseJdbcDaoSupportOra implements MaLineRunPlanDAO
{
    /**
     * grid find
     * @author kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param maLineRunPlanDTO
     * @return List
     */
    public List findList(MaLineRunPlanDTO maLineRunPlanDTO, User user)
    { 
        QueryBuffer query = new QueryBuffer();

        query.append(getColums(maLineRunPlanDTO, user));
        query.append(getTables(maLineRunPlanDTO, user));
        query.append(this.getWhere(maLineRunPlanDTO, user));
        query.append(getOrderBy(maLineRunPlanDTO, user));
        
        return getJdbcTemplate().queryForList(query.toString(maLineRunPlanDTO.getIsLoadMaxCount(), maLineRunPlanDTO.getFirstRow()));
    } 
    

	@Override
	public String getColums(MaLineRunPlanDTO maLineRunPlanDTO, User user) {
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
	public String getTables(MaLineRunPlanDTO maLineRunPlanDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
        query.append("FROM   TALNWRKTIME x                                                  ");

		return query.toString();
	}

	@Override
	public String getOrderBy(MaLineRunPlanDTO maLineRunPlanDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
        query.getOrderByQuery("x.plant, x.wrk_date, x.eqloc_id", maLineRunPlanDTO.getOrderBy(), maLineRunPlanDTO.getDirection());

		return query.toString();
	}
	
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param maLineRunPlanDTO
     * @return
     * @throws Exception
     */
    public String getWhere(MaLineRunPlanDTO maLineRunPlanDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();

        query.append(" WHERE 1=1 		");
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.append("AND EXISTS (SELECT 1 FROM TALNWRKLIST a 					");
        query.append("             WHERE a.comp_no = x.comp_no 					");
        query.append("               AND a.lnwrklist_id = x.lnwrklist_id 		");
        query.getAndQuery("a.lnwrk_calendar_type", "R");
        query.append("            )												");
        
        if (!"".equals(maLineRunPlanDTO.getLnWrkTimeId()))
        {
            query.getAndQuery("x.lnwrktime_id", maLineRunPlanDTO.getLnWrkTimeId());
            return query.toString();
        }
        //일자
        query.getAndDateQuery("x.wrk_date", maLineRunPlanDTO.getFilterStartWrkDate(), maLineRunPlanDTO.getFilterEndWrkDate());
        // 공장 
        query.getPlantCdQuery("x.plant", maLineRunPlanDTO.getFilterPlantId(), maLineRunPlanDTO.getFilterPlantDesc(), maLineRunPlanDTO.getCompNo());
        
        //라인
        query.getEqLocLevelQuery("x.eqloc_id", maLineRunPlanDTO.getFilterLineId(), maLineRunPlanDTO.getFilterLineDesc(), maLineRunPlanDTO.getCompNo());
        
        // 가동달력
        query.getCodeLikeQuery("x.lnwrklist_id", "(SELECT a.description FROM TALNWRKLIST a WHERE a.lnwrklist_id = x.lnwrklist_id AND a.comp_no='"+maLineRunPlanDTO.getCompNo()+"')", 
                maLineRunPlanDTO.getFilterLnWrkListId(), maLineRunPlanDTO.getFilterLnWrkListDesc());
        
        return query.toString();
    }


    /**
     * detail insert
     * @author kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param maLineRunPlanDTO
     * @return
     */
    public int[] insertDetail(final List<MaLineRunPlanDTO> list, final User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TALNWRKTIME(									");
    	query.append("   comp_no,      lnwrktime_id,    plant,   wrk_date,		");
    	query.append("   eqloc_id,     dtime,           ntime,   etime, remark, ");
    	query.append("   lnwrklist_id, ucnt                                     ");
    	query.append(")VALUES(							                        ");
    	query.append("	 ?,			   ?,		   ?,		    ?,              ");
    	query.append("	 ?,			   ?,		   ?,		    ?,      ?,      ");
    	query.append("	 ?,            ?                                        ");
    	query.append(")													        ");
    	
    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
            	MaLineRunPlanDTO maLineRunPlanDTO = list.get(i);
                
            	Object[] objects = new Object[] {
            			maLineRunPlanDTO.getCompNo()
            			,maLineRunPlanDTO.getLnWrkTimeId()
            			,maLineRunPlanDTO.getPlantId()
            			,CommonUtil.getRowDateToNum(maLineRunPlanDTO.getWrkDate())
            			,maLineRunPlanDTO.getLineId()
            			,CommonUtil.getRowDateToNum(maLineRunPlanDTO.getDayRunTime())
            			,CommonUtil.getRowDateToNum(maLineRunPlanDTO.getNightRunTime())
            			,CommonUtil.getRowDateToNum(maLineRunPlanDTO.getEvenRunTime())
            			,maLineRunPlanDTO.getRemark()
            			,maLineRunPlanDTO.getLnWrkListId()
            			,CommonUtil.getRowDateToNum(maLineRunPlanDTO.getUcnt())
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
     * @author kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param maLineRunPlanDTO
     * @return
     */
    public int[] updateDetail(final List<MaLineRunPlanDTO> list, final User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TALNWRKTIME SET	            ");
        query.append("        dtime          = ?            ");
        query.append("       ,ntime          = ?            ");
        query.append("       ,etime          = ?            ");
        query.append("       ,remark         = ?            ");
        query.append("       ,ucnt           = ?            ");
        query.append("       ,wrk_date       = ?            ");
        query.append("       ,lnwrklist_id   = ?            ");
    	query.append("WHERE  comp_no         = ?            ");
    	query.append("  AND  lnwrktime_id    = ?            ");
    	
    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				MaLineRunPlanDTO maLineRunPlanDTO = list.get(i);
		
				Object[] objects = new Object[] {
		                 CommonUtil.getRowDateToNum(maLineRunPlanDTO.getDayRunTime())
		                ,CommonUtil.getRowDateToNum(maLineRunPlanDTO.getNightRunTime())
		                ,CommonUtil.getRowDateToNum(maLineRunPlanDTO.getEvenRunTime())
		                ,maLineRunPlanDTO.getRemark()
		                ,CommonUtil.getRowDateToNum(maLineRunPlanDTO.getUcnt())
		                ,CommonUtil.getRowDateToNum(maLineRunPlanDTO.getWrkDate())
		                ,maLineRunPlanDTO.getLnWrkListId()
		                ,user.getCompNo()
		                ,maLineRunPlanDTO.getLnWrkTimeId()
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
     * @param maLineRunPlanDTO
     * @return
     */
    public String validLineRunPlan(MaLineRunPlanDTO maLineRunPlanDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*)                                           	");
        query.append("FROM   TALNWRKTIME x                                        	");
        query.append("WHERE  x.comp_no   = '" + maLineRunPlanDTO.getCompNo()   + "'	");
        query.append("  AND  x.wrk_date= '" + CommonUtil.getRowDateToNum(maLineRunPlanDTO.getWrkDate())+ "'		");
//        query.append("  AND  x.plant= '" + maLineRunPlanDTO.getPlantId()+ "'		");
//        query.append("  AND  x.eqloc_id= '" + maLineRunPlanDTO.getLineId()+ "'		");
        
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
    public int[] deleteList(final List<MaLineRunPlanDTO> list, final User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TALNWRKTIME            ");
        query.append("WHERE  comp_no      = ?       ");
        query.append("  AND  lnwrktime_id = ?		");
        
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				MaLineRunPlanDTO maLineRunPlanDTO = list.get(i);
				
				Object[] objects = new Object[] {   
		                user.getCompNo()
		                , maLineRunPlanDTO.getLnWrkTimeId()
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
	public String findTotalCount(MaLineRunPlanDTO maLineRunPlanDTO, User user)
	{
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT                             ");
        query.append("       COUNT(1)                    ");
        query.append(getTables(maLineRunPlanDTO, user));
        query.append(this.getWhere(maLineRunPlanDTO, user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
		return QueryBuffer.listToString(resultList);
	}
}