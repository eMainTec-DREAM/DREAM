package dream.consult.program.setting.upload.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.exception.SqlIgnoreException;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.consult.program.setting.upload.dao.ConsultPgmSettingUpdDAO;
import dream.consult.program.setting.upload.dto.ConsultPgmSettingUpdDTO;

/**
 * Excel Data Upload - List DAO implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="consultPgmSettingUpdDAOTarget"
 * @spring.txbn id="consultPgmSettingUpdDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultPgmSettingUpdDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultPgmSettingUpdDAO
{
	public List findExldataList(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
        List excelColList = consultPgmSettingUpdDTO.getExcelColList();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        if(!"".equals(consultPgmSettingUpdDTO.getTableName())) {
            query.append("SELECT        ");
            query.append("    ''        AS seqNo            ");
            query.append("    ,''       AS isDelCheck       ");
            for(int i=0; i<excelColList.size(); i++) {
                Map excelColMap = (Map) excelColList.get(i);
                query.append("    ,"+excelColMap.get("TABLECOLNAME"));
            }
            query.append("FROM "+consultPgmSettingUpdDTO.getTableName());
            query.append("WHERE  1=1                                                                        ");
            query.append(this.getWhere(consultPgmSettingUpdDTO, user));
            query.getOrderByQuery("comp_no","1", consultPgmSettingUpdDTO.getOrderBy(), consultPgmSettingUpdDTO.getDirection());
        }

        return getJdbcTemplate().queryForList(query.toString(consultPgmSettingUpdDTO.getIsLoadMaxCount(), consultPgmSettingUpdDTO.getFirstRow()));
    } 

	private String getWhere(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user)
    {        
		QuerySqlBuffer query = new QuerySqlBuffer();
        
		if(!"".equals(consultPgmSettingUpdDTO.getSkeyId())){
            query.getAndQuery("SKEY_ID", consultPgmSettingUpdDTO.getSkeyId());
            return query.toString();
        }
		query.getAndQuery("comp_no", user.getCompNo());
        query.getAndQuery("user_no", user.getUserNo());
        
        query.append("AND EXISTS(SELECT 1 FROM TAEXCELTABLIST WHERE 1=1        ");
        query.getAndQuery("exceltab_id", consultPgmSettingUpdDTO.getExcelTabId());
        query.append("                                                                            )     ");
        
        String fromDate = CommonUtil.dateToData(consultPgmSettingUpdDTO.getFilterStartDate());
        String toDate   = CommonUtil.dateToData(consultPgmSettingUpdDTO.getFilterEndDate());
        
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
            query.append("AND (SELECT upload_date FROM TAEXCELTABLIST WHERE 1=1         ");
            query.getStringEqualQuery("comp_no", user.getCompNo());
            query.getAndQuery("exceltab_id", consultPgmSettingUpdDTO.getExcelTabId());
            query.append("                                                                            ) >= '"+fromDate+"'  ");
        }
        
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
            query.append("AND (SELECT upload_date FROM TAEXCELTABLIST WHERE 1=1         ");
            query.getStringEqualQuery("comp_no", user.getCompNo());
            query.getAndQuery("exceltab_id", consultPgmSettingUpdDTO.getExcelTabId());
            query.append("                                                                            ) <= '"+toDate+"'   ");
        }        
        //성공여부
        query.getAndQuery("is_success", consultPgmSettingUpdDTO.getFilterIsSuccess());

    	return query.toString();
    }

    public int deleteExldataList(String table, String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	if(!"".equals(table)) {
            query.append("DELETE FROM "+table);
            query.append("WHERE  skey_id = ?        ");
            
            Object[] objects = new Object[] {   
                    id
                    };
            
            return this.getJdbcTemplate().update(query.toString(), objects);
        }
        else return 0;
    }
    public String findTotalCount(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                 ");
    	query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM "+consultPgmSettingUpdDTO.getTableName());
        query.append("WHERE  1=1                                                                        ");
        query.append(this.getWhere(consultPgmSettingUpdDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
    
    public List findExcelColList(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT        ");
        query.append("    table_col_name  tableColName      ");
        query.append("    ,excel_col_name excelColName      ");
        query.append("    ,description         label       ");
        query.append("    ,is_key                isKey       ");
        query.append("    ,is_editable         isEditable       ");
        query.append("    ,is_system         isSystem       ");
        query.append("    ,is_system_display         isSystemDisplay       ");
        query.append("FROM TAEXCELCOL       ");
        query.append("WHERE exceltab_id = ?   ");
        query.append("   and is_use = 'Y'      ");
        query.append("ORDER BY ord_no       ");
        
        Object[] objects = new Object[] {   
                consultPgmSettingUpdDTO.getExcelTabId()
        };
        
        return getJdbcTemplate().queryForList(query.toString(),objects);
    } 
    
    public List getDummyHeader(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT        ");
        query.append("    'isDelCheck'            ID,               ");
        query.append("    '60'                        width,        ");
        query.append("    'center'                  align,          ");
        query.append("    'ch'                        SORT,         ");
        query.append("    'ch'                        TYPE,         ");
        query.append("    'false'                    hidden,        ");
        query.append("    key_name              VALUE,      ");
        query.append("    '10'                       height         ");
        query.append("FROM TALANG       ");
        query.append("WHERE key_no='ISDELCHECK'                     ");
        query.append("AND key_type='LABEL'                              ");
        query.append("AND lang='"+user.getLangId()+"'     ");
        query.append("UNION ALL     ");
        query.append("SELECT        ");
        query.append("    'seqNo'                    ID,               ");
        query.append("    '60'                        width,        ");
        query.append("    'center'                  align,          ");
        query.append("    'int'                        SORT,         ");
        query.append("    'cntr'                        TYPE,         ");
        query.append("    'false'                    hidden,        ");
        query.append("    key_name              VALUE,      ");
        query.append("    '10'                       height         ");
        query.append("FROM TALANG       ");
        query.append("WHERE key_no='seqNo'                     ");
        query.append("AND key_type='LABEL'                              ");
        query.append("AND lang='"+user.getLangId()+"'     ");
        query.append("UNION ALL     ");
        query.append("SELECT * FROM     ");
        query.append("(     ");
        query.append("    SELECT TOP(100) PERCENT               ");
        query.append("        table_col_name   ID,                                                                 ");
        query.append("        CASE WHEN UPPER(table_col_name)='MESSAGE' AND is_system='Y' THEN '200' ELSE '100' END  width,      ");
        query.append("        CASE WHEN UPPER(table_col_name)='IS_SUCCESS' AND is_system='Y' THEN 'center' ELSE 'left' END align, ");
        query.append("        'str'                   SORT,                                                             ");
        query.append("        'ro'                    TYPE,                                                             ");
        query.append("        CASE WHEN is_system='Y' AND is_system_display='N' THEN 'true' ELSE 'false' END hidden,                      ");
        query.append("        description          VALUE,                                                           ");
        query.append("        '10'                     height                                                            ");
        query.append("    FROM TAEXCELCOL               ");
        query.append("    WHERE exceltab_id = ?          ");
        query.append("       and is_use = 'Y'      ");
        query.append("    ORDER BY ISNULL(ord_no,0)       ");
        query.append(") a     ");

        Object[] objects = new Object[] {   
                consultPgmSettingUpdDTO.getFilterExcelTabId()
        };
        
        return getJdbcTemplate().queryForList(query.toString(), getObject(objects));
    }

    @Override
    public List findExcelHeader(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT        ");
        query.append("    excel_col_name    ID      ");
        query.append("    ,excel_col_name    VALUE      ");
        query.append("    ,'100'                  WIDTH       ");
        query.append("    ,'false'                  HIDDEN       ");
        query.append("    ,remark                  COLCOMMENT       ");
        query.append("FROM TAEXCELCOL       ");
        query.append("WHERE exceltab_id = ?   ");
        query.append("AND is_system = ?   ");
        query.append("   and is_use = 'Y'      ");
        query.append("ORDER BY ISNULL(ord_no,0)       ");
        
        Object[] objects = new Object[] {   
                consultPgmSettingUpdDTO.getFilterExcelTabId()
                ,"N"
        };
        
        return getJdbcTemplate().queryForList(query.toString(), objects);
    }

	@Override
	public ConsultPgmSettingUpdDTO findExcelTab(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT                                                  ");
        query.append("    x.exceltab_id               AS excelTabId           ");
        query.append("    ,x.exceltab_no              AS excelTabNo           ");
        query.append("    ,x.description              AS excelTabDesc         ");
        query.append("    ,x.table_name               AS tableName            ");
        query.append("    ,x.upload_pgm_name          AS uploadPgmName        ");
        query.append("    ,x.excellist_status         AS excelListStatus      ");
        query.append("    ,x.is_use                   AS isUse                ");
        query.append("    ,x.remark                   AS remark               ");
        query.append("    ,x.sheet_name               AS sheetName            ");
        query.append("    ,x.is_use_config            AS isUseConfig          ");
        query.append("    ,x.excel_upload_type        AS excelUploadType      ");
        query.append("FROM TAEXCELTAB x                                       ");
        query.append("WHERE x.exceltab_id = "+consultPgmSettingUpdDTO.getExcelTabId());
		
		return (ConsultPgmSettingUpdDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new ConsultPgmSettingUpdDTO()));
	}
	
	@Override
	public String findExcelSheetName(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception {
	    
	    QuerySqlBuffer query = new QuerySqlBuffer();
	    
	    query.append("SELECT x.sheet_name      		");
	    query.append("FROM TAEXCELTAB x				");
	    query.append("WHERE 1=1						");
	    query.getAndNumKeyQuery("x.exceltab_id", consultPgmSettingUpdDTO.getFilterExcelTabId());
	    
	    List resultList=  getJdbcTemplate().queryForList(query.toString());
	    return QuerySqlBuffer.listToString(resultList);
	}

	@Override
	public void uploadData(String pgmName, User user) throws SqlIgnoreException {
        try{
            if("".equals(pgmName)) throw new SqlIgnoreException("No Procedure : Upload Program");
            QuerySqlBuffer query = new QuerySqlBuffer();
            
            query.append("EXEC 											");
            query.append("dbo." + pgmName);
            query.append("                  '"+user.getCompNo()+"'		");
            query.append("                 ,'"+user.getUserNo()+"'		");
            query.append("                 ,0         					");
            
            this.getJdbcTemplate().execute(query.toString());
        }catch(Exception e){
            throw new SqlIgnoreException(e.getMessage());
        }
    }
	
	public List findExldataDetail(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) 
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        List excelColList = consultPgmSettingUpdDTO.getExcelColList();
        
        if(!"".equals(consultPgmSettingUpdDTO.getTableName())) {
            query.append("SELECT        ");
            for(int i=0; i<excelColList.size(); i++) {
                Map excelColMap = (Map) excelColList.get(i);
                String comma = (i==0)?"    ":"    ,";
                query.append(comma+excelColMap.get("TABLECOLNAME"));
            }
            query.append("FROM "+consultPgmSettingUpdDTO.getTableName());
            query.append("WHERE skey_id = ?                                  ");
        }
        
        Object[] objects = new Object[] {
                consultPgmSettingUpdDTO.getSkeyId()
        };
        
        return getJdbcTemplate().queryForList(query.toString(), objects);
        
    }
    

    public int insertExldataDetail(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        return rtnValue;
    }
    
    
    
    public int updateExldataDetail(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        int rtnValue  = 0;
        int ind = 0;
        
        Map paramMap = consultPgmSettingUpdDTO.getParamMap();

        query.append("UPDATE "+consultPgmSettingUpdDTO.getTableName()+" SET");
        for(Object key:paramMap.keySet()) {
            String comma = (ind==0)?"    ":"    ,";
            query.append(comma+(String) key+" = '"+paramMap.get(key)+"' ");
            ind++;
        }
        query.append("WHERE  skey_id        = ?             ");
        
        Object[] objects = new Object[] {
                consultPgmSettingUpdDTO.getSkeyId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    @Override
    public int updateUpdDate(String excelTabId, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        int rtnValue  = 0;
        
        query.append("UPDATE TAEXCELTABLIST SET");
        query.append("  upd_date     = ?,   ");
        query.append("  upd_by        = ?   ");
        query.append("WHERE  comp_no        = ?             ");
        query.append("AND  exceltab_id        = ?             ");
        
        Object[] objects = new Object[] {
                DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss")
                ,user.getEmpId()
                ,user.getCompNo()
                ,excelTabId
                
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    public void uploadExldataDetail(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("exec ");
        query.append(consultPgmSettingUpdDTO.getUploadPgmName());
        query.append("                  '"+user.getCompNo()+"'          ");
        query.append("                 ,'"+user.getUserNo()+"'         ");
        query.append("                 ,"+consultPgmSettingUpdDTO.getSkeyId()+"         ");
        
        this.getJdbcTemplate().execute(query.toString());
    }

    @Override
    public List findExcelExScript(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                            ");
        query.append("    x.sheet_name  AS sheetName    ");
        query.append("    ,x.script     AS script       ");
        query.append("FROM TAEXCELEXSCRIPT x            ");
        query.append("WHERE x.exceltab_id = ?           ");
        query.append("AND x.is_use = 'Y'                ");
        query.append("ORDER BY x.ord_no                 ");
        
        Object[] objects = new Object[] {   
                consultPgmSettingUpdDTO.getFilterExcelTabId()
        };
        
        return getJdbcTemplate().queryForList(query.toString(),getObject(objects));
    }

    @Override
    public List exeScriptToList(String script) throws SqlIgnoreException
    {
        try{
            return getJdbcTemplate().queryForList(script);
        }catch(Exception e){
            throw new SqlIgnoreException(e.getMessage());
        }
    }
}