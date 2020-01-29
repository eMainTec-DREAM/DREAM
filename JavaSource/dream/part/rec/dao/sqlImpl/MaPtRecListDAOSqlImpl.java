package dream.part.rec.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.part.rec.dao.MaPtRecListDAO;
import dream.part.rec.dto.MaPtRecCommonDTO;

/**
 * 구매입고 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtRecListDAOTarget"
 * @spring.txbn id="maPtRecListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtRecListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPtRecListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecCommonDTO
     * @return List
     */
    public List findList(MaPtRecCommonDTO maPtRecCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = maPtRecCommonDTO.getCompNo();
        
        query.append("SELECT ''                                                 seqNo,          ");
        query.append("       ''                                                 isDelCheck,     ");
        query.append("       x.comp_no                                          compNo,         ");
        query.append("       x.prreclist_id                                     prRecListId,    ");
        query.append("       x.prreclist_no                                     prRecListNo,    ");
        query.append("       dbo.SFAIDTODESC(x.dept_id, '', 'DEPT', x.comp_no)  deptDesc,       "); 
        query.getDate("x.rec_date", "recDate,");
        query.append("       dbo.SFAIDTODESC(x.vendor_id, '', 'VENDOR', x.comp_no) vendorDesc,  "); 
        query.append("       x.prreclist_status                                 prRecListStatus,");
        query.append("       dbo.SFACODE_TO_DESC(x.prreclist_status, 'PRRECLIST_STATUS', 'SYS', x.comp_no,'"+user.getLangId()+"') recStatusDesc, ");
        query.append("       x.part_id                                          partId,         "); 
        query.append("       y.part_no                                          partNo,         ");
        query.append("       CASE WHEN x.part_id IS NULL                                        ");
        query.append("            THEN x.pt_desc+', '+ISNULL(x.pt_size,'')                      ");
        query.append("            ELSE y.description +', '+ ISNULL(y.pt_size,'')                ");
        query.append("            END                                           partNameSize,   ");
        query.append("       ISNULL(x.rec_qty, 0)                               recQty,         "); 
        query.append("       ISNULL(x.unit_price, 0)                            unitPrice,      "); 
        query.append("       ISNULL(x.tot_price, 0)                             totPrice,       ");
        query.append("       dbo.SFAIDTODESC(x.inspector, '', 'EMP', x.comp_no) inspectorName,  ");  
        query.append("       dbo.sfacode_to_desc(x.is_make_part_no,'IS_USE','SYS',x.comp_no, '"+user.getLangId()+"' ) isMakePartNo,     ");
        query.append("       x.is_make_part_no                                  isMakePartNoId  ");
        query.append("      ,x.plant                                            plantId         ");
        query.append("      ,(SELECT a.description                                              ");
        query.append("         FROM TAPLANT a                                                   ");
        query.append("         WHERE a.comp_no = x.comp_no                                      ");
        query.append("         AND a.plant = x.plant                                            ");
        query.append("         )                                                plantDesc       ");
        query.append("      ,x.dept_id                                          deptId          ");
        query.append("      ,x.wcode_id                                         wcodeId         ");
        query.append("      ,x.vendor_id                                        vendorId        ");
        query.append("      ,CASE WHEN x.part_id IS NULL THEN x.pt_desc                         ");
        query.append("            ELSE y.description                                            ");
        query.append("            END                                           PARTDESC        ");
        query.append("      ,CASE WHEN x.part_id IS NULL THEN x.pt_size                         ");
        query.append("            ELSE y.pt_size                                                ");
        query.append("            END                                           PARTSIZE        ");
        query.append("      ,y.model                                            MODEL           ");
        query.append("      ,x.uom                                              uom             ");
        query.append("      ,x.inspector                                        inspector       ");
        query.append("      ,x.part_grade                                       partGrade       ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.part_grade,'PART_GRADE','SYS','', '"+user.getLangId()+"' )   AS partGradeDesc    ");
        query.append("      ,x.remark                                           remark          ");
        query.append("      ,(SELECT aa.polist_no                                               ");
        query.append("          FROM taptpolist aa                                              ");
        query.append("         WHERE aa.comp_no   = x.comp_no                                   ");
        query.append("           AND aa.polist_id = x.polist_id)            poNo                ");
        query.append("      ,(SELECT bb.ptprlist_no                                             ");
        query.append("          FROM taptprlist bb                                              ");
        query.append("          LEFT OUTER JOIN taptpritem cc                                   ");
        query.append("                       ON  cc.comp_no    = bb.comp_no                     ");
        query.append("                      AND cc.ptprlist_id = bb.ptprlist_id                 ");
        query.append("         WHERE bb.comp_no     = x.comp_no                                 ");
        query.append("           AND cc.ptpritem_id = x.ptpritem_id)        reqNo               ");
        query.append("    , y.model                                         ptmodel		        ");
        query.append("FROM   TAPTPRRECLIST x LEFT OUTER JOIN TAPARTS y                          ");
        query.append("ON x.comp_no = y.comp_no                                                  ");
        query.append("AND x.part_id = y.part_id                                                 ");
        query.append("WHERE 1=1                                                                 ");

        query.append(this.getWhere(maPtRecCommonDTO,user));

        query.getOrderByQuery("x.prreclist_id desc","x.prreclist_id DESC", maPtRecCommonDTO.getOrderBy(), maPtRecCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPtRecCommonDTO.getIsLoadMaxCount(), maPtRecCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteList(String compNo, String prRecListId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        query = new QuerySqlBuffer();
        
        query.append("DELETE FROM TAPTPRRECLIST                   ");
        query.append("WHERE  comp_no       = '"+compNo+"'         ");
        query.append("  AND  prreclist_id  = '"+prRecListId+"'    ");
        
        rtnValue = this.getJdbcTemplate().update(query.toString());
        
        return rtnValue;
    }
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPtRecCommonDTO maPtRecCommonDTO,User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.getAndQuery("x.comp_no", user.getCompNo());
        
        if (!"".equals(maPtRecCommonDTO.getPrRecListId()))
        {
            query.getAndQuery("x.prreclist_id", maPtRecCommonDTO.getPrRecListId());
            return query.toString();
        }
        
        query.getAndNumKeyQuery("x.ptpoitem_id", maPtRecCommonDTO.getPtPoItemId());

        //상태
        query.getSysCdQuery("x.prreclist_status", maPtRecCommonDTO.getPrRecStatus(), maPtRecCommonDTO.getPrRecStatusDesc(), "PRRECLIST_STATUS", maPtRecCommonDTO.getCompNo(),user.getLangId());
          
        // 부서
          query.getDeptLevelQuery("x.dept_id", maPtRecCommonDTO.getFilterDeptId(), maPtRecCommonDTO.getFilterDeptDesc(), maPtRecCommonDTO.getCompNo());
        
        //입고일자
        String startDate = maPtRecCommonDTO.getFilterRecStartDate();
        String endDate = maPtRecCommonDTO.getFilterRecEndDate();
        query.getAndDateQuery("x.rec_date", startDate, endDate);

        
        // 검수자
        if(!"".equals(maPtRecCommonDTO.getFilterInspector()))
        {
            query.getAndQuery("x.inspector", maPtRecCommonDTO.getFilterInspector());
        }
        else if(!"".equals(maPtRecCommonDTO.getFilterInspectorName()))
        {
            query.append(" AND  x.inspector IN (SELECT emp_id FROM TAEMP        ");
            query.append("                      WHERE  comp_no = x.comp_no      ");
            query.getLikeQuery("emp_name", maPtRecCommonDTO.getFilterInspectorName());
            query.append("                     )                                ");
        }
        
        // 품명/규격
        query.getLikeQuery("CASE WHEN x.part_id IS NULL THEN x.pt_desc+', '+ISNULL(x.pt_size,'') ELSE y.description+', '+ISNULL(y.pt_size,'') END", maPtRecCommonDTO.getFilterPartNameSize());
        
        // 거래처
        if(!"".equals(maPtRecCommonDTO.getFilterDeptId()))
        {
            query.getAndQuery("x.vendor_id", maPtRecCommonDTO.getFilterVendorId());
        }
        else if(!"".equals(maPtRecCommonDTO.getFilterVendorDesc()))
        {
            query.append(" AND  x.vendor_id IN (SELECT vendor_id FROM TAVENDOR  ");
            query.append("                    WHERE  comp_no = x.comp_no        ");
            query.getLikeQuery("description", maPtRecCommonDTO.getFilterVendorDesc());
            query.append("                     )                                ");
        }
        //상태
        query.getAndQuery("x.prreclist_status", maPtRecCommonDTO.getPrRecStatus());
        
        // 구매신청번호
        query.getLikeQuery("(SELECT a.ptprlist_no FROM TAPTPRLIST a  WHERE a.ptplist_id = (SELECT b.ptprlist_id  FROM TAPTPRITEM b  WHERE b.ptpritem_id = x.ptpritem_id) )", maPtRecCommonDTO.getFilterReqNo());
  
        // 발주번호
        query.getLikeQuery("(SELECT a.polist_no FROM TAPTPOLIST a WHERE a.comp_no = x.comp_no AND a.polist_id = x.polist_id)", maPtRecCommonDTO.getFilterPoNo());
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no=x.comp_no AND a.plant=x.plant )", 
                maPtRecCommonDTO.getFilterPlantId(), maPtRecCommonDTO.getFilterPlantDesc());
        
        return query.toString();
    }

    @Override
    public String findTotalCount(MaPtRecCommonDTO maPtRecCommonDTO, User user)
    {    
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = user.getCompNo();
        
        query.append("SELECT                                ");
        query.append("       COUNT(1)                       ");
        query.append("FROM   TAPTPRRECLIST x LEFT OUTER JOIN TAPARTS y  ");
        query.append("ON x.comp_no = y.comp_no              ");
        query.append("AND x.part_id = y.part_id             ");
        query.append("WHERE 1=1                             ");
        query.append(this.getWhere(maPtRecCommonDTO,user));
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
    }
    
    public int insertQrCode(String id, String fileName, User loginUser) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TAREPORTPARAM (   ");
        query.append("        comp_no               ");
        query.append("        ,user_id              ");
        query.append("        ,file_name            ");
        query.append("        ,skey_id              ");
        query.append(")                             ");
        query.append("VALUES (                      ");
        query.append("        ?                     ");
        query.append("        ,?                    ");
        query.append("        ,?                    ");
        query.append("        ,?                    ");
        query.append(")                             ");
        
        Object[] objects = new Object[]{
                loginUser.getCompNo(),
                loginUser.getUserId(),
                fileName,
                id,
        };
        
        int result = this.getJdbcTemplate().update(query.toString(),getObject(objects));

        return result;
    }

    public int deleteQrCode(String fileName, User loginUser) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE TAREPORTPARAM    ");
        query.append("WHERE  comp_no   = ?    ");
        query.append("AND    user_id   = ?    ");
        query.append("AND    file_name = ?    ");
        
        Object[] objects = new Object[]{
                loginUser.getCompNo()
                ,loginUser.getUserId()
                ,fileName
        };
        
        int result = this.getJdbcTemplate().update(query.toString(),getObject(objects));
        
        return result;
    }

    @Override
    public String getData(MaPtRecCommonDTO maPtRecCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                            ");
        query.append("    CASE WHEN (SELECT COUNT(x.exceltab_id)        ");
        query.append("                FROM TAEXCELTAB x                 ");
        query.append("                WHERE exceltab_no = ?) = 0        ");
        query.append("        THEN '0'                                  ");
        query.append("        ELSE (SELECT                              ");
        query.append("                CONVERT(VARCHAR,x.exceltab_id) + ',' + x.description + ',' + x.table_name             ");
        query.append("            FROM TAEXCELTAB x                     ");
        query.append("            WHERE x.exceltab_no = ?)              ");
        query.append("        END                                       ");
        
        Object[] objects = new Object[] {
                 maPtRecCommonDTO.getExceltabNo()
                ,maPtRecCommonDTO.getExceltabNo()
        };
    
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }
}