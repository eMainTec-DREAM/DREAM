package dream.consult.program.uploaddata.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.consult.program.uploaddata.dao.ConsultProgramUploadDataScriptDAO;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataDTO;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataScriptDTO;

/**
 * ø¢ºø ¬¸¡∂µ•¿Ã≈∏ - DAO implements
 * @author ghlee
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="consultProgramUploadDataScriptDAOTarget"
 * @spring.txbn id="consultProgramUploadDataScriptDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultProgramUploadDataScriptDAOOraImpl  extends BaseJdbcDaoSupportOra implements ConsultProgramUploadDataScriptDAO
{
    public List findList(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, ConsultProgramUploadDataScriptDTO consultProgramUploadDataScriptDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                            ");
        query.append("   ''                     AS seqNo                ");
        query.append("  ,''                     AS isDelCheck           ");
        query.append("  ,x.excelexscript_id     AS excelExScriptId      ");
        query.append("  ,x.exceltab_id          AS excelTabId           ");
        query.append("  ,x.ord_no               AS ordNo                ");
        query.append("  ,x.description          AS description          ");
        query.append("  ,x.sheet_name           AS sheetName            ");
        query.append("  ,x.is_use               AS isUse                ");
        query.append("  ,x.script               AS script               ");
        query.append("  ,x.remark               AS remark               ");
        query.append("FROM TAEXCELEXSCRIPT x                            ");
        query.append("WHERE 1=1                                         ");
        query.append(this.getWhere(consultProgramUploadDataDTO, consultProgramUploadDataScriptDTO, user));
        query.getOrderByQuery("x.ord_no", consultProgramUploadDataDTO.getOrderBy(), consultProgramUploadDataDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(consultProgramUploadDataDTO.getIsLoadMaxCount(), consultProgramUploadDataDTO.getFirstRow()));

    }
    
    private String getWhere(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, ConsultProgramUploadDataScriptDTO consultProgramUploadDataScriptDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        if (!"".equals(consultProgramUploadDataScriptDTO.getExcelExScriptId()))
        {
            query.getAndQuery("x.excelexscript_id", consultProgramUploadDataScriptDTO.getExcelExScriptId());
        }
        
        query.getAndNumKeyQuery("x.exceltab_id", consultProgramUploadDataDTO.getExcelTabId());
        
        return query.toString();
    }
    
    public int deleteList(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE FROM TAEXCELEXSCRIPT      ");
        query.append("WHERE excelexscript_id = ?    ");
        
        Object[] objects = new Object[] {   
                 id
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    public String findTotalCount(ConsultProgramUploadDataDTO consultProgramUploadDataDTO,
            ConsultProgramUploadDataScriptDTO consultProgramUploadDataScriptDTO, User user) throws Exception 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                      ");
        query.append("       COUNT(1)             ");
        query.append("FROM   TAEXCELEXSCRIPT x    ");
        query.append("WHERE  1=1                  ");
        query.append(this.getWhere(consultProgramUploadDataDTO, consultProgramUploadDataScriptDTO, user));
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    public int insertDetail(ConsultProgramUploadDataScriptDTO consultProgramUploadDataScriptDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO TAEXCELEXSCRIPT (    ");
        query.append("  exceltab_id    , excelexscript_id");
        query.append(", ord_no         , description   ");
        query.append(", sheet_name     , is_use        ");
        query.append(", script         , remark        ");
        query.append(", upd_date       , upd_by        ");
        query.append(") VALUES (                       ");
        query.append("  ?              , ?             ");
        query.append(", ?              , ?             ");
        query.append(", ?              , ?             ");
        query.append(", ?              , ?             ");
        query.append(", ?              , ?             ");
        query.append(")                                ");

        Object[] objects = new Object[] {
                consultProgramUploadDataScriptDTO.getExcelTabId()
                ,consultProgramUploadDataScriptDTO.getExcelExScriptId()
                ,consultProgramUploadDataScriptDTO.getOrdNo()
                ,consultProgramUploadDataScriptDTO.getDescription()
                ,consultProgramUploadDataScriptDTO.getSheetName()
                ,consultProgramUploadDataScriptDTO.getIsUse()
                ,consultProgramUploadDataScriptDTO.getScript()
                ,consultProgramUploadDataScriptDTO.getRemark()
                ,DateUtil.getTimeStamp(9)
                ,user.getEmpId()
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
    
    public int updateDetail(ConsultProgramUploadDataScriptDTO consultProgramUploadDataScriptDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAEXCELEXSCRIPT SET       ");
        query.append("  ord_no                  = ?    ");
        query.append(", description             = ?    ");
        query.append(", sheet_name              = ?    ");
        query.append(", is_use                  = ?    ");
        query.append(", script                  = ?    ");
        query.append(", remark                  = ?    ");
        query.append(", upd_date                = ?    ");
        query.append(", upd_by                  = ?    ");
        query.append("WHERE  exceltab_id        = ?    ");
        query.append("  AND  excelexscript_id   = ?    ");
        
        Object[] objects = new Object[] {
                consultProgramUploadDataScriptDTO.getOrdNo()
                ,consultProgramUploadDataScriptDTO.getDescription()
                ,consultProgramUploadDataScriptDTO.getSheetName()
                ,consultProgramUploadDataScriptDTO.getIsUse()
                ,consultProgramUploadDataScriptDTO.getScript()
                ,consultProgramUploadDataScriptDTO.getRemark()
                ,DateUtil.getTimeStamp(9)
                ,user.getEmpId()
                ,consultProgramUploadDataScriptDTO.getExcelTabId()
                ,consultProgramUploadDataScriptDTO.getExcelExScriptId()
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
}
