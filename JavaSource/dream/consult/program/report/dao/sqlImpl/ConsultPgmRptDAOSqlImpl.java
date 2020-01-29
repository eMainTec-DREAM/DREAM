package dream.consult.program.report.dao.sqlImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.program.report.dao.ConsultPgmRptDAO;
import dream.consult.program.report.dto.ConsultPgmRptDTO;

/**
 * Report List - 목록 dao 
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="consultPgmRptDAOTarget"
 * @spring.txbn id="consultPgmRptDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultPgmRptDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultPgmRptDAO
{
	public List findList(ConsultPgmRptDTO consultPgmRptDTO, User user) throws Exception {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append(getColums(consultPgmRptDTO, user));
        query.append(getTables(consultPgmRptDTO, user));
        query.append(this.getWhere(consultPgmRptDTO, user));
        query.append(getOrderBy(consultPgmRptDTO, user));
        
		return getJdbcTemplate().queryForList(query.toString(consultPgmRptDTO.getIsLoadMaxCount(), consultPgmRptDTO.getFirstRow()));
	}

	public String findTotalCount(ConsultPgmRptDTO consultPgmRptDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT								");
        query.append("    COUNT(1)							");
        query.append(getTables(consultPgmRptDTO, user));
        query.append(this.getWhere(consultPgmRptDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
	}
	
	@Override
	public int[] insertDetail(final List<ConsultPgmRptDTO> list, final User user) throws Exception {
		QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("INSERT INTO TARPTLIST (                       						");
        query.append("   	 rptlist_id        		, rptlist_no			, description	");
        query.append("     , is_use					, remark								");
        query.append(")VALUES(                                      						");
        query.append("  	 ?              		, ?              		, ?         	");
        query.append("     , ?              		, ?              		         		");
        query.append(")                                             						");
        
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
            	ConsultPgmRptDTO consultPgmRptDTO = list.get(i);
                
                Object[] objects = new Object[] {
                		consultPgmRptDTO.getRptListId()
                 	   , consultPgmRptDTO.getReportNo()
                 	   , consultPgmRptDTO.getReportName()
                 	   , consultPgmRptDTO.getIsUse()
                   	   , consultPgmRptDTO.getRemark()
                };
                
                for(int j=0;j<objects.length;j++){
                	 ps.setObject(j+1, getObject(objects[j]));
                }
            }
            
            @Override
            public int getBatchSize()
            {
                return list.size();
            }
        });
	}
	
	public int[] updateDetail(final List<ConsultPgmRptDTO> list, final User user) throws Exception {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("UPDATE TARPTLIST SET					");
    	query.append("	     rptlist_no			= ?			");
    	query.append("	   , description		= ?			");
    	query.append("	   , is_use				= ?			");
    	query.append("	   , remark				= ?			");
    	query.append(" WHERE rptlist_id			= ?			");
    	
    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ConsultPgmRptDTO consultPgmRptDTO = list.get(i);
				
				Object[] objects = new Object[] {
						consultPgmRptDTO.getReportNo()
						   , consultPgmRptDTO.getReportName()
						   , consultPgmRptDTO.getIsUse()
	                       , consultPgmRptDTO.getRemark()
	                       , consultPgmRptDTO.getRptListId()                                                                          
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
	
	public int[] deleteList(final List<ConsultPgmRptDTO> list, final User user) throws Exception {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("DELETE TARPTLIST					");
    	query.append(" WHERE rptlist_id		= ?			");
    	
    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ConsultPgmRptDTO consultPgmRptDTO = list.get(i);
				
				Object[] objects = new Object[] {
						consultPgmRptDTO.getRptListId()                                                                     
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
	public String getColums(ConsultPgmRptDTO consultPgmRptDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT 		");
		query.append("		''                    																			AS ISDELCHECK   	");
		query.append("    , ''                    																			AS SEQNO    		");
		query.append("    , x.rptlist_id  																					AS rptListId		");
		query.append("    , x.rptlist_no  																					AS reportNo			");
		query.append("    , x.description 																					AS reportName		");
		query.append("    , x.description 																					AS reportDesc		");
		query.append("    , (SELECT dbo.SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', '', '"+user.getLangId()+"') ) 			AS isUse			");
		query.append("    , x.remark 																						AS remark 			");
				
		return query.toString();
	}

	@Override
	public String getTables(ConsultPgmRptDTO consultPgmRptDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("FROM TARPTLIST x		");

		return query.toString();
	}

	@Override
	public String getOrderBy(ConsultPgmRptDTO consultPgmRptDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.getOrderByQuery("x.rptlist_id","x.rptlist_no", consultPgmRptDTO.getOrderBy(), consultPgmRptDTO.getDirection());		
		
		return query.toString();
	}

	@Override
	public String getWhere(ConsultPgmRptDTO consultPgmRptDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("WHERE 1 = 1	");
		
		if(!"".equals(consultPgmRptDTO.getRptListId())){
            query.getAndQuery("x.rptlist_id", consultPgmRptDTO.getRptListId());
            
            return query.toString();
        }
		
        // 출력물 #
        query.getLikeQuery("x.rptlist_no", consultPgmRptDTO.getFilterReportNo());
        
        // 출력물 명
        query.getLikeQuery("x.description", consultPgmRptDTO.getFilterReportName());
        
        // Report 종류
        if(!"".equals(consultPgmRptDTO.getFilterReportFileTypeDesc()) || !"".equals(consultPgmRptDTO.getFilterReportFileType()))
        {
        	query.append("AND x.rptlist_id IN (SELECT a.rptlist_id 		");
        	query.append("                       FROM TARPTFILE a 		");
        	query.append("                      WHERE 1=1  				");
        	query.getSysCdQuery("a.rptfile_type", consultPgmRptDTO.getFilterReportFileType(), consultPgmRptDTO.getFilterReportFileTypeDesc(), "RPTFILE_TYPE", "", user.getLangId());
        	query.append("                     )						");
        }
        
        return query.toString();
	}

	@Override
	public String checkDetail(ConsultPgmRptDTO consultPgmRptDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT count(*) 				");
    	query.append("FROM TARPTLIST				");
    	query.append("WHERE 1=1						");
    	query.getLikeQuery("rptlist_no", consultPgmRptDTO.getReportNo());
    	query.append("AND rptlist_id != "+consultPgmRptDTO.getRptListId()+" ");

    	return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
	}
}