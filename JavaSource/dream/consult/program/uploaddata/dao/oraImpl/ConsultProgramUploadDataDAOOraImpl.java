package dream.consult.program.uploaddata.dao.oraImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.consult.program.uploaddata.dao.ConsultProgramUploadDataDAO;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataColDTO;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataDTO;

/**
 * 업로드데이타 - 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="consultProgramUploadDataDAOTarget"
 * @spring.txbn id="consultProgramUploadDataDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultProgramUploadDataDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultProgramUploadDataDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramUploadDataDTO
     * @return List
     */
    public List findList(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                        ");
        query.append("   ''                     AS ISDELCHECK       ");
        query.append("  ,''                     AS SEQNO            ");
        query.append("  , x.exceltab_id         AS EXCELTABID       ");
        query.append("  , x.exceltab_id         AS EXCELTYPEID      ");
        query.append("  , x.exceltab_no         AS EXCELTABNO       ");
        query.append("  , x.description         AS EXCELTABNAME     ");
        query.append("  , x.table_name          AS TABLENAME        ");
        query.append("  , x.upload_pgm_name     AS UPLOADPGMNAME    ");
        query.append("  , x.is_use              AS ISUSE            ");
        query.append("  , x.is_use_config       AS ISUSECONFIG      ");
        query.append("  , x.sheet_name          AS SHEETNAME        ");
        query.append("  , x.excellist_status    AS EXCELLISTSTATUSID");
        query.append("  , SFACODE_TO_DESC(x.excellist_status,'EXCELLIST_STATUS','SYS','','"+user.getLangId()+"') AS EXCELLISTSTATUS        ");
        query.append("  , x.remark              AS REMARK           ");
        query.append("FROM TAEXCELTAB x                             ");
        query.append("WHERE 1=1                                     ");
        query.append(this.getWhere(consultProgramUploadDataDTO, user));
        query.getOrderByQuery("x.exceltab_no", consultProgramUploadDataDTO.getOrderBy(), consultProgramUploadDataDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(consultProgramUploadDataDTO.getIsLoadMaxCount(), consultProgramUploadDataDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int delete(String id)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("DELETE FROM TAEXCELCOL	   ");
    	query.append("WHERE exceltab_id = '"+id+"' ");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query.setClear();
    	query.append("DELETE FROM TAEXCELTAB	   ");
    	query.append("WHERE exceltab_id = '"+id+"' ");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    
    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramUploadDataDTO
     * @return
     * @throws Exception
     */
    private String getWhere(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        if (!"".equals(consultProgramUploadDataDTO.getExcelTabId()))
        {
            query.getAndQuery("x.exceltab_id", consultProgramUploadDataDTO.getExcelTabId());
            return query.toString();
        }
        
        // Excel 유형 No
        query.getLikeQuery("x.exceltab_no", consultProgramUploadDataDTO.getFilterExcelTabNo());
        // Excel 유형명
        query.getLikeQuery("x.description", consultProgramUploadDataDTO.getFilterExcelTabName());
        // 테이블명
        query.getLikeQuery("x.table_name", consultProgramUploadDataDTO.getFilterTableName());
        // 프로그램명
        query.getLikeQuery("x.upload_pgm_name", consultProgramUploadDataDTO.getFilterUploadPgmName());
        // 사용여부
        query.getAndQuery("x.is_use", consultProgramUploadDataDTO.getFilterIsUse());
        // 관리자설정여부
        query.getAndQuery("x.is_use_config", consultProgramUploadDataDTO.getFilterIsUseConfig());
        
        return query.toString();
    }
    
    public String findTotalCount(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT            ");
        query.append("      COUNT(1)    ");
        query.append("FROM TAEXCELTAB x ");
        query.append("WHERE  1=1        ");
        query.append(this.getWhere(consultProgramUploadDataDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramUploadDataDTO
     * @return
     */
    public int insertDetail(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        int resultValue = 0;
                
        // 테이블명이 TY로 시작하지 않으면 TY를 붙여준다.
        String tableName = consultProgramUploadDataDTO.getTableName().toUpperCase();
        if(!"".equals(tableName) && !tableName.startsWith("TY"))
        {
            consultProgramUploadDataDTO.setTableName("TY"+tableName);   
        }
        else
        {
            consultProgramUploadDataDTO.setTableName(tableName);   
        }
        
        query.append("INSERT INTO TAEXCELTAB (                 ");
        query.append("      exceltab_id    , exceltab_no       ");
        query.append("    , description    , table_name        ");
        query.append("    , upload_pgm_name                    ");
        query.append("    , excellist_status                   ");
        query.append("    , is_use         , is_use_config     ");
        query.append("    , upd_date       , upd_by            ");
        query.append("    , remark         , sheet_name        ");
        query.append("    )    VALUES    (                     ");
        query.append("      ?              , ?                 ");
        query.append("    , ?              , ?                 ");
        query.append("    , ?                                  ");
        query.append("    , ?                                  ");
        query.append("    , ?              , ?                 ");
        query.append("    ,TO_CHAR(SYSDATE,'yyyymmdd')    ,?   ");
        query.append("    , ?              , ?                 ");
        query.append("    )                                    ");
        
        Object[] objects = new Object[] {
                  consultProgramUploadDataDTO.getExcelTabId()
                , consultProgramUploadDataDTO.getExcelTabNo()
                , consultProgramUploadDataDTO.getExcelTabName()
                , consultProgramUploadDataDTO.getTableName()
                , consultProgramUploadDataDTO.getUploadPgmName()
                , consultProgramUploadDataDTO.getExcelListStatusId()
                , consultProgramUploadDataDTO.getIsUse()
                , consultProgramUploadDataDTO.getIsUseConfig()
                , user.getEmpId() 
                , consultProgramUploadDataDTO.getRemark()
                , consultProgramUploadDataDTO.getSheetName()
        };
        
        resultValue = getJdbcTemplate().update(query.toString(), objects);
        
        return resultValue;
    }
    /**
     * detail update
     * @author youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramUploadDataDTO
     * @return
     */
    public int updateDetail(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        // 테이블명이 TY로 시작하지 않으면 TY를 붙여준다.
        if(!consultProgramUploadDataDTO.getTableName().substring(0, 2).equals("TY"))
        {
            consultProgramUploadDataDTO.setTableName("TY"+consultProgramUploadDataDTO.getTableName().toUpperCase());   
        }
        else
        {
            consultProgramUploadDataDTO.setTableName(consultProgramUploadDataDTO.getTableName().toUpperCase());   
        }
        
        query.append("UPDATE TAEXCELTAB SET         ");
        query.append("  exceltab_no          = ?,   ");
        query.append("  description          = ?,   ");
        query.append("  table_name           = ?,   ");
        query.append("  sheet_name           = ?,   ");
        query.append("  is_use               = ?,   ");
        query.append("  is_use_config        = ?,   ");
        query.append("  upload_pgm_name      = ?,   ");
        query.append("  remark               = ?,   ");
        query.append("  upd_date             = TO_CHAR(SYSDATE,'yyyymmdd'),   ");
        query.append("  upd_by               = ?    ");
        query.append("WHERE exceltab_id      = ?    ");
        
        Object[] objects = new Object[] {
                  consultProgramUploadDataDTO.getExcelTabNo()
                , consultProgramUploadDataDTO.getExcelTabName()
                , consultProgramUploadDataDTO.getTableName()
                , consultProgramUploadDataDTO.getSheetName()
                , consultProgramUploadDataDTO.getIsUse()
                , consultProgramUploadDataDTO.getIsUseConfig()
                , consultProgramUploadDataDTO.getUploadPgmName()
                , consultProgramUploadDataDTO.getRemark()
                , user.getEmpId()
                , consultProgramUploadDataDTO.getExcelTabId()
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }

    // 테이블 존재여부 확인
    public String checkTableExist(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT COUNT(1)           ");
        query.append("FROM user_tables x        ");
        query.append("WHERE UPPER(x.table_name) = ?    ");
        
        Object[] objects = new Object[] {
                consultProgramUploadDataDTO.getTableName()
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    
    // 테이블 생성
    @Override
    public int completeDetail(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        
        // 컬럼갯수
        int colCnt = 0;
        // insert할 컬럼 query내용
        String columns = ""; 
        // key 컬럼
        String keyCol = "";
        
        String lang = user.getLangId();
        
        List mapList = new ArrayList();
        Map map = new HashMap<String, String>();
        
        map.put("table_col_name", "SKEY_ID");
        map.put("description", "keyId");
        map.put("table_col_type", "NUMBER");
        map.put("table_col_size", "18");
        map.put("ord_no", "9995");
        map.put("is_key", "Y");
        map.put("is_system_display", "N");
        
        mapList.add(map);
        map = new HashMap<String, String>();
        
        map.put("table_col_name", "COMP_NO");
        map.put("description", "compNo");
        map.put("table_col_type", "STRING");
        map.put("table_col_size", "6");
        map.put("ord_no", "9996");
        map.put("is_key", "Y");
        map.put("is_system_display", "N");
        
        mapList.add(map);
        map = new HashMap<String, String>();
        
        map.put("table_col_name", "USER_NO");
        map.put("description", "userNo");
        map.put("table_col_type", "STRING");
        map.put("table_col_size", "30");
        map.put("ord_no", "9997");
        map.put("is_key", "Y");
        map.put("is_system_display", "N");
        
        mapList.add(map);
        map = new HashMap<String, String>();
        
        map.put("table_col_name", "IS_SUCCESS");
        map.put("description", "isSuccess");
        map.put("table_col_type", "STRING");
        map.put("table_col_size", "20");
        map.put("ord_no", "9998");
        map.put("is_key", "N");
        map.put("is_system_display", "Y");
        
        mapList.add(map);
        map = new HashMap<String, String>();
        
        map.put("table_col_name", "MESSAGE");
        map.put("description", "message");
        map.put("table_col_type", "STRING");
        map.put("table_col_size", "1024");
        map.put("ord_no", "9999");
        map.put("is_key", "N");
        map.put("is_system_display", "Y");
        
        mapList.add(map);
        
        for (int i = 0; i<mapList.size(); i++)
        {
            map = (Map) mapList.get(i);
            String ord_no, table_col_name , description, table_col_type, table_col_size, is_key, is_system_display;
            
            ord_no = (String) map.get("ord_no");
            table_col_name = (String) map.get("table_col_name");
            description = (String) map.get("description");
            table_col_type = (String) map.get("table_col_type");
            table_col_size = (String) map.get("table_col_size");
            is_key = (String) map.get("is_key");
            is_system_display = (String) map.get("is_system_display");
            
            query.setClear();
            query.append("INSERT INTO TAEXCELCOL (              ");
            query.append("  exceltab_id    , excelcolumn_id     ");
            query.append(", ord_no         , excel_col_name     ");
            query.append(", is_key         , table_col_name     ");
            query.append(", description    , table_col_type     ");
            query.append(", table_col_size , REMARK             ");
            query.append(", upd_date       , upd_by             ");
            query.append(", is_editable    , is_system          ");
            query.append(", is_system_display                    ");
            query.append(") VALUES (                            ");
            query.append("  ?              , sqaexcelcolumn_id.nextval                  ");
            query.append(", ?              , ''                 ");
            query.append(", ?              , ?                  ");
            query.append(", (SELECT key_name FROM TALANG WHERE key_type='LABEL' AND key_no= ? AND lang='"+lang+"')  , ?    ");
            query.append(", ?              , ''                 ");
            query.append(", TO_CHAR(SYSDATE,'yyyymmdd')   , ?   ");
            query.append(", 'N'            , 'Y'                ");
            query.append(", ?                                   ");
            query.append(")                                     ");
            
            Object[] objects = new Object[] {
                    consultProgramUploadDataDTO.getExcelTabId()
                    , ord_no
                    , is_key
                    , table_col_name
                    , description
                    , table_col_type
                    , table_col_size
                    , user.getEmpId() 
                    , is_system_display
            };
            
            getJdbcTemplate().update(query.toString(), objects);
        }
        
        // 컬럼갯수 알아오기
        query.setClear();
        query.append("SELECT COUNT(1)           ");
        query.append("FROM TAEXCELCOL x         ");
        query.append("WHERE x.exceltab_id = ?   ");

        Object[] cntObjects = new Object[] {
                consultProgramUploadDataDTO.getExcelTabId()
        };
        
        colCnt = Integer.parseInt(QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),cntObjects)));

        // 등록된 컬럼이 0명이 아니면
        if(colCnt>=1)
        {
            // 등록된 컬럼수만큼 INSERT한다.
            for(int i=1; i<=colCnt; i++)
            {
                // i번째 레코드를 읽어와서 DTO에 저장한다.
                query.setClear();
                query.append("SELECT                                ");
                query.append("    excelColId , excelTabId           ");
                query.append("  , ordNo      , excelColName         ");
                query.append("  , isKeyId    , tableColName         ");
                query.append("  , excelColComments , tableColTypeId ");
                query.append("  , tableColType        , tableColSize      ");
                query.append("FROM (                                                ");
                    query.append("SELECT                                            ");
                    query.append("  RANK() OVER(ORDER BY x.ord_no) AS rno           ");
                    query.append("  , x.excelcolumn_id      AS excelColId           ");
                    query.append("  , x.exceltab_id         AS excelTabId           ");
                    query.append("  , x.ord_no              AS ordNo                ");
                    query.append("  , x.excel_col_name      AS excelColName         ");
                    query.append("  , x.is_key              AS isKeyId              ");
                    query.append("  , x.table_col_name      AS tableColName         ");
                    query.append("  , x.description         AS excelColComments     ");
                    query.append("  , x.table_col_type      AS tableColTypeId       ");
                    query.append("  , SFACODE_TO_DESC(x.table_col_type,'DATA_TYPE','SYS','','"+user.getLangId()+"') AS tableColType ");
                    query.append("  , x.table_col_size      AS tableColSize         ");
                    query.append("FROM TAEXCELCOL x                                 ");
                    query.append("WHERE 1=1                                         ");
                    query.getAndNumKeyQuery("x.exceltab_id", consultProgramUploadDataDTO.getExcelTabId());
                    query.append("ORDER BY x.ord_no                                   ");
                query.append(")                                                     ");
                query.append("WHERE rno='"+i+"'                                     ");

                // DTO에 저장
                ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO = 
                        (ConsultProgramUploadDataColDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new ConsultProgramUploadDataColDTO()));

                // 한 줄의 레코드 생성
                String comma = (i==1)?" ":", ";
                columns += comma+consultProgramUploadDataColDTO.getTableColName() + " " 
                                +consultProgramUploadDataColDTO.getTableColType() + "(" 
                                +consultProgramUploadDataColDTO.getTableColSize() + ") ";
                                
                if("Y".equals(consultProgramUploadDataColDTO.getIsKeyId()))
                {   // Key컬럼인 경우
                    columns += "NOT NULL ";
                    if("".equals(keyCol))
                        keyCol  += consultProgramUploadDataColDTO.getTableColName();
                    else
                        keyCol  += ", "+consultProgramUploadDataColDTO.getTableColName();
                }
            }
            
            // 테이블 생성 (테이블명은 TY로 시작)
            query.setClear();
            query.append("CREATE TABLE "+consultProgramUploadDataDTO.getTableName()+" (      ");
            query.append("      "+columns+"     ");
            // Key 컬럼이 존재하면
            if(!"".equals(keyCol))
            {
                query.append(" , CONSTRAINT PK_"+consultProgramUploadDataDTO.getTableName()+" PRIMARY KEY(   "+keyCol+"  )  ");
            }
            query.append("                )    ");

            rtnValue = getJdbcTemplate().update(query.toString());
        }
        return rtnValue;
    }
    
    // 테이블 코멘트 추카
    @Override
    public int setComments(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        
        String tableName = "";
        String tableComment = "";
        String columnName = "";
        String columnComment = "";
        
        tableName = consultProgramUploadDataDTO.getTableName();
        tableComment = consultProgramUploadDataDTO.getExcelTabNo();
        
        // 테이블 코멘트
        query.append("COMMENT ON TABLE "+tableName+" IS '"+tableComment+"'       ");

        rtnValue += getJdbcTemplate().update(query.toString());
        
        // 컬럼갯수
        int colCnt = 0;
        
        // 컬럼갯수 알아오기
        query.setClear();
        query.append("SELECT COUNT(1)           ");
        query.append("FROM TAEXCELCOL x         ");
        query.append("WHERE x.exceltab_id = ?   ");

        Object[] cntObjects = new Object[] {
                consultProgramUploadDataDTO.getExcelTabId()
        };
        
        colCnt = Integer.parseInt(QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),cntObjects)));

        // 등록된 컬럼이 0명이 아니면
        if(colCnt>=1)
        {
            // 등록된 컬럼수만큼 INSERT한다.
            for(int i=1; i<=colCnt; i++)
            {
                // i번째 레코드를 읽어와서 DTO에 저장한다.
                query.setClear();
                query.append("SELECT                                ");
                query.append("    excelColId , excelTabId           ");
                query.append("  , ordNo      , excelColName         ");
                query.append("  , isKeyId    , tableColName         ");
                query.append("  , excelColComments , tableColTypeId ");
                query.append("  , tableColSize                      ");
                query.append("FROM (                                                ");
                    query.append("SELECT                                            ");
                    query.append("  ROWNUM AS rno                                   ");
                    query.append("  , x.excelcolumn_id      AS excelColId           ");
                    query.append("  , x.exceltab_id         AS excelTabId           ");
                    query.append("  , x.ord_no              AS ordNo                ");
                    query.append("  , x.excel_col_name      AS excelColName         ");
                    query.append("  , x.is_key              AS isKeyId              ");
                    query.append("  , x.table_col_name      AS tableColName         ");
                    query.append("  , x.description         AS excelColComments     ");
                    query.append("  , x.table_col_type      AS tableColTypeId       ");
                    query.append("  , x.table_col_size      AS tableColSize         ");
                    query.append("FROM TAEXCELCOL x                                 ");
                    query.append("WHERE 1=1                                         ");
                    query.getAndNumKeyQuery("x.exceltab_id", consultProgramUploadDataDTO.getExcelTabId());
                    query.append("ORDER BY x.ord_no                                   ");
                query.append(")                                                     ");
                query.append("WHERE rno='"+i+"'                                     ");

                // DTO에 저장
                ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO = 
                        (ConsultProgramUploadDataColDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new ConsultProgramUploadDataColDTO()));

                columnName = consultProgramUploadDataColDTO.getTableColName();
                columnComment = consultProgramUploadDataColDTO.getExcelColComments();
                
                // 컬럼 코멘트
                query.setClear();
                query.append("COMMENT ON COLUMN "+tableName+"."+columnName+" IS '"+columnComment+"'        ");
                
                rtnValue += getJdbcTemplate().update(query.toString());
            }
            
        }
        return rtnValue;
    }
    
    
    @Override
    public int updateStatus(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("UPDATE TAEXCELTAB SET         ");
        query.append("  excellist_status     = ?,   ");
        query.append("  upd_date             = TO_CHAR(SYSDATE,'yyyymmdd'),   ");
        query.append("  upd_by               = ?    "); // consult 쪽에서는 upd_by 값이 안들어감
        query.append("WHERE exceltab_id      = ?    ");
        
        Object[] objects = new Object[] {
                  "C"
                , user.getEmpId()
                , consultProgramUploadDataDTO.getExcelTabId()
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
    
    // 테이블 삭제
    @Override
    public int dropTable(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        
        query.append("DELETE FROM TAEXCELCOL        ");
        query.append("WHERE exceltab_id = ?        ");
        query.append("AND is_system = ?        ");
        
        Object[] objects = new Object[] {
                consultProgramUploadDataDTO.getExcelTabId()
                ,"Y"
        };
        
        getJdbcTemplate().update(query.toString(), objects);
        
        query.setClear();
        
        query.append("DROP TABLE "+consultProgramUploadDataDTO.getTableName()+" ");
        
        rtnValue = getJdbcTemplate().update(query.toString());
        
        return rtnValue;
    }
    
    @Override
    public int updateStatusAsW(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAEXCELTAB SET         ");
        query.append("  excellist_status     = ?,   ");
        query.append("  upd_date             = TO_CHAR(SYSDATE,'yyyymmdd'),   ");
        query.append("  upd_by               = ?    "); // consult 쪽에서는 upd_by 값이 안들어감
        query.append("WHERE exceltab_id      = ?    ");
        
        Object[] objects = new Object[] {
                "W"
                , user.getEmpId()
                , consultProgramUploadDataDTO.getExcelTabId()
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
}