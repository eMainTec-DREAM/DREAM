package dream.consult.program.uploaddata.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.consult.program.uploaddata.dao.ConsultProgramUploadDataColDAO;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataColDTO;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataDTO;

/**
 * 컬럼 - List DAO implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="consultProgramUploadDataColDAOTarget"
 * @spring.txbn id="consultProgramUploadDataColDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultProgramUploadDataColDAOOraImpl  extends BaseJdbcDaoSupportOra implements ConsultProgramUploadDataColDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param consultProgramUploadDataColDTO
     * @return List
     */
    public List findList(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                            ");
        query.append("   ''                     AS seqNo                ");
        query.append("  ,''                     AS isDelCheck           ");
        query.append("  , x.excelcolumn_id      AS excelColId           ");
        query.append("  , x.exceltab_id         AS excelTabId           ");
        query.append("  , x.ord_no              AS ordNo                ");
        query.append("  , x.excel_col_name      AS excelColName         ");
        query.append("  , x.is_key              AS isKeyId              ");
        query.append("  , SFACODE_TO_DESC(x.is_key,'IS_USE','SYS','','"+user.getLangId()+"') AS isKey     ");
        query.append("  , x.is_system              AS isSystemId              ");
        query.append("  , SFACODE_TO_DESC(x.is_system,'IS_USE','SYS','','"+user.getLangId()+"') AS isSystem     ");
        query.append("  , x.is_editable              AS isEditableId              ");
        query.append("  , SFACODE_TO_DESC(x.is_editable,'IS_USE','SYS','','"+user.getLangId()+"') AS isEditable     ");
        query.append("  , x.is_system_display              AS isSystemDisplayId              ");
        query.append("  , SFACODE_TO_DESC(x.is_system_display,'IS_USE','SYS','','"+user.getLangId()+"') AS isSystemDisplay     ");
        query.append("  , x.table_col_name      AS tableColName         ");
        query.append("  , x.description         AS excelColComments     ");
        query.append("  , x.table_col_type      AS tableColTypeId       ");
        query.append("  , SFACODE_TO_DESC(x.table_col_type,'DATA_TYPE','SYS','','"+user.getLangId()+"') AS tableColType       ");
        query.append("  , x.table_col_size      AS tableColSize         ");
        query.append("  , x.REMARK              AS REMARK               ");
        query.append("  , x.upd_date            AS updDate              ");
        query.append("  , x.upd_by              AS updBy                ");
        query.append("  , x.is_use              AS isUse                ");
        query.append("FROM TAEXCELCOL x                                 ");
        query.append("WHERE 1=1                                         ");
        query.append(this.getWhere(consultProgramUploadDataDTO, consultProgramUploadDataColDTO, user));
        query.getOrderByQuery("x.ord_no", consultProgramUploadDataDTO.getOrderBy(), consultProgramUploadDataDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(consultProgramUploadDataDTO.getIsLoadMaxCount(), consultProgramUploadDataDTO.getFirstRow()));

    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param consultProgramUploadDataColDTO
     * @return
     * @throws Exception
     */
    private String getWhere(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        if (!"".equals(consultProgramUploadDataColDTO.getExcelColId()))
        {
            query.getAndQuery("x.excelcolumn_id", consultProgramUploadDataColDTO.getExcelColId());
        }
        
        query.getAndQuery("x.is_system", "N");
        query.getAndNumKeyQuery("x.exceltab_id", consultProgramUploadDataDTO.getExcelTabId());
        
        return query.toString();
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param consultProgramUploadDataColDTO
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAEXCELCOL      ");
        query.append("WHERE excelcolumn_id = ?    ");
        query.getAndQuery("is_system", "N");

        Object[] objects = new Object[] {   
                 id
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    public String findTotalCount(ConsultProgramUploadDataDTO consultProgramUploadDataDTO,
            ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO, User user) throws Exception 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                      ");
        query.append("       COUNT(1)             ");
        query.append("FROM   TAEXCELCOL x         ");
        query.append("WHERE  1=1                  ");
        query.append(this.getWhere(consultProgramUploadDataDTO, consultProgramUploadDataColDTO, user));
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    public int insertDetail(ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;

        query.append("INSERT INTO TAEXCELCOL (         ");
        query.append("  exceltab_id    , excelcolumn_id");
        query.append(", ord_no         , excel_col_name");
        query.append(", is_key         , table_col_name");
        query.append(", description    , table_col_type");
        query.append(", table_col_size , REMARK        ");
        query.append(", upd_date       , upd_by        ");
        query.append(", is_editable    , is_system     ");
        query.append(", is_system_display, is_use      ");
        query.append(") VALUES (                       ");
        query.append("  ?              , ?             ");
        query.append(", ?              , ?             ");
        query.append(", ?              , ?             ");
        query.append(", ?              , ?             ");
        query.append(", ?              , ?             ");
        query.append(", TO_CHAR(SYSDATE,'yyyymmdd')              , ?             ");
        query.append(", ?              , ?             ");
        query.append(", ?              ,?              ");
        query.append(")                                ");

        Object[] objects = new Object[] {
                consultProgramUploadDataColDTO.getExcelTabId()
                ,consultProgramUploadDataColDTO.getExcelColId()
                ,consultProgramUploadDataColDTO.getOrdNo()
                ,consultProgramUploadDataColDTO.getExcelColName()
                ,"N"
                ,consultProgramUploadDataColDTO.getTableColName()
                ,consultProgramUploadDataColDTO.getExcelColComments()
                ,consultProgramUploadDataColDTO.getTableColTypeId()
                ,consultProgramUploadDataColDTO.getTableColSize()
                ,consultProgramUploadDataColDTO.getRemark()
                ,user.getEmpId()
                ,"Y"
                ,"N"
                ,"N"
                ,consultProgramUploadDataColDTO.getIsUse()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    public int updateDetail(ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        int rtnValue  = 0;

        query.append("UPDATE TAEXCELCOL SET            ");
        query.append("  ord_no                  = ?    ");
        query.append(", excel_col_name          = ?    ");
        query.append(", table_col_name          = ?    ");
        query.append(", description             = ?    ");
        query.append(", table_col_type          = ?    ");
        query.append(", table_col_size          = ?    ");
        query.append(", REMARK                  = ?    ");
        query.append(", upd_date                = ?    ");
        query.append(", upd_by                  = ?    ");
        query.append(", is_use                  = ?    ");
        query.append("WHERE  exceltab_id        = ?    ");
        query.append("  AND  excelcolumn_id     = ?    ");
        
        Object[] objects = new Object[] {
                consultProgramUploadDataColDTO.getOrdNo()
                ,consultProgramUploadDataColDTO.getExcelColName()
                ,consultProgramUploadDataColDTO.getTableColName()
                ,consultProgramUploadDataColDTO.getExcelColComments()
                ,consultProgramUploadDataColDTO.getTableColTypeId()
                ,consultProgramUploadDataColDTO.getTableColSize()
                ,consultProgramUploadDataColDTO.getRemark()
                ,DateUtil.getTimeStamp(9)
                ,user.getEmpId()
                ,consultProgramUploadDataColDTO.getIsUse()
                ,consultProgramUploadDataColDTO.getExcelTabId()
                ,consultProgramUploadDataColDTO.getExcelColId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
}
