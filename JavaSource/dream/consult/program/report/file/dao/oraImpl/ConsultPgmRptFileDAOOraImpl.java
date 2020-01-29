package dream.consult.program.report.file.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.consult.program.report.file.dao.ConsultPgmRptFileDAO;
import dream.consult.program.report.file.dto.ConsultPgmRptFileDTO;

/**
 * Report List - ¸ñ·Ï dao 
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="consultPgmRptFileDAOTarget"
 * @spring.txbn id="consultPgmRptFileDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultPgmRptFileDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultPgmRptFileDAO
{
    public List findList(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user) throws Exception {
    	QueryBuffer query = new QueryBuffer();
		
		query.append(getColums(consultPgmRptFileDTO, user));
        query.append(getTables(consultPgmRptFileDTO, user));
        query.append(this.getWhere(consultPgmRptFileDTO, user));
        query.append(getOrderBy(consultPgmRptFileDTO, user));
        
		return getJdbcTemplate().queryForList(query.toString(consultPgmRptFileDTO.getIsLoadMaxCount(), consultPgmRptFileDTO.getFirstRow()));
	}

	public String findTotalCount(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT								");
        query.append("    COUNT(1)							");
        query.append(getTables(consultPgmRptFileDTO, user));
        query.append(this.getWhere(consultPgmRptFileDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
	}
	
	@Override
	public int[] updateDetail(final List<ConsultPgmRptFileDTO> list, final User user) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        
		query.append("UPDATE TARPTFILE SET					");
    	query.append("	     file_name			= ?			");
    	query.append("	   , rptfile_type		= ?			");
    	query.append("	   , svr_addr			= ?			");
    	query.append("	   , design_file		= ?			");
    	query.append("	   , query_file			= ?			");
    	query.append("	   , reg_date			= ?			");
    	query.append("	   , is_use				= ?			");
    	query.append("	   , writer				= ?			");
    	query.append("	   , remark				= ?			");
    	query.append(" WHERE rptfile_id			= ?			");
    	
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
            	ConsultPgmRptFileDTO consultPgmRptFileDTO = list.get(i);
                
                Object[] objects = new Object[] {
                		  consultPgmRptFileDTO.getFileDesc()
                		, consultPgmRptFileDTO.getReportFileTypeId()
                		, consultPgmRptFileDTO.getServerUrl()
                  		, consultPgmRptFileDTO.getReportFileName()
                  		, consultPgmRptFileDTO.getQueryFileName()
                		, CommonUtil.getRowDateToNum(consultPgmRptFileDTO.getRepRegDate())
                		, consultPgmRptFileDTO.getIsUse()
                		, consultPgmRptFileDTO.getWriteBy()
                		, consultPgmRptFileDTO.getRemark()
                		, consultPgmRptFileDTO.getRptFileId()
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
	public int[] insertDetail(final List<ConsultPgmRptFileDTO> list, final User user) throws Exception
	{
		QueryBuffer query = new QueryBuffer();
		
		query.append("INSERT INTO TARPTFILE(                       							");
		query.append("		rptlist_id		, rptfile_id      			, file_name			");
		query.append("    , rptfile_type	, svr_addr 					, design_file		");
		query.append("    , query_file		, reg_date        			, writer			");
		query.append("    , is_use			, remark                   						");
		query.append(")VALUES(                                      						");
		query.append("  	 ?              , ?              			, ?          		");
		query.append("     , ?              , ?              			, ?          		");
		query.append("     , ?              , ?              			, ?          		");
		query.append("     , ?				, ?                       						");
		query.append(")                                             						");
		
		return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
		{
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException
			{
				ConsultPgmRptFileDTO consultPgmRptFileDTO = list.get(i);
				
				Object[] objects = new Object[] {
						consultPgmRptFileDTO.getRptListId()
						, consultPgmRptFileDTO.getRptFileId()
						, consultPgmRptFileDTO.getFileDesc()
						, consultPgmRptFileDTO.getReportFileTypeId()
						, consultPgmRptFileDTO.getServerUrl()
	              		, consultPgmRptFileDTO.getReportFileName()
	              		, consultPgmRptFileDTO.getQueryFileName()
						, CommonUtil.getRowDateToNum(consultPgmRptFileDTO.getRepRegDate())
						, consultPgmRptFileDTO.getWriteBy()
						, consultPgmRptFileDTO.getIsUse()
						, consultPgmRptFileDTO.getRemark()
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

	public int[] deleteList(final List<ConsultPgmRptFileDTO> list, final User user) throws Exception {
		QueryBuffer query = new QueryBuffer();
		
		query.append("DELETE TARPTFILE				");
    	query.append(" WHERE rptfile_id		= ?		");
    	
    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ConsultPgmRptFileDTO consultPgmRptFileDTO = list.get(i);
				
				Object[] objects = new Object[] {
                         consultPgmRptFileDTO.getRptFileId()                                                                     
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
	public String getColums(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT																																");
		query.append("		''                 																						AS ISDELCHECK 			");
		query.append("	  , ''               																						AS SEQNO 				");
		query.append("    , x.rptlist_id  																							AS rptListId			");
		query.append("    , x.rptfile_id 																							AS rptFileId			");
		query.append("    , x.file_name 																							AS fileDesc				");
		query.append("    , x.rptfile_type 																							AS reportFileTypeId		");
		query.append("    , (SELECT SFACODE_TO_DESC(x.rptfile_type, 'RPTFILE_TYPE', 'SYS', '', '"+user.getLangId()+"') FROM dual) 	AS reportFileType		");
		query.append("    , x.svr_addr 																								AS serverUrl			");
		query.append("    , x.design_file 																							AS reportFileName		");
		query.append("    , x.query_file 																							AS queryFileName		");
		query.append("    , x.reg_date 																								AS repRegDate			");
		query.append("    , (SELECT SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', '', '"+user.getLangId()+"') FROM dual) 				AS isUse				");
		query.append("    , x.writer  																								AS writeBy				");
		query.append("    , x.remark 																								AS remark				");

		return query.toString();
	}

	@Override
	public String getTables(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
		query.append("  FROM TARPTFILE x		");
		
		return query.toString();
	}

	@Override
	public String getOrderBy(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
		query.getOrderByQuery("x.rptfile_id", consultPgmRptFileDTO.getOrderBy(), consultPgmRptFileDTO.getDirection());		
		
		return query.toString();
	}

	@Override
	public String getWhere(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
		query.append("WHERE 1 = 1	");
		
		query.getAndQuery("x.rptlist_id", consultPgmRptFileDTO.getRptListId());
		
		if(!"".equals(consultPgmRptFileDTO.getRptFileId())){
            query.getAndQuery("x.rptfile_id", consultPgmRptFileDTO.getRptFileId());
            
            return query.toString();
        }
		
		return query.toString();
	}
	
	public String checkDetail(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user)
	{
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT count(*) 				");
    	query.append("FROM TARPTFILE				");
    	query.append("WHERE 1=1						");
    	query.getAndQuery("file_name", consultPgmRptFileDTO.getFileDesc());
    	query.append("AND rptfile_id != "+consultPgmRptFileDTO.getRptFileId() +" ");

    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
	}
	
}