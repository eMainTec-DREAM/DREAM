package dream.part.rec.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
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
public class MaPtRecListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtRecListDAO
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
        QueryBuffer query = new QueryBuffer();
        String compNo = maPtRecCommonDTO.getCompNo();
        
        query.append("SELECT ''                                             seqNo,          ");
        query.append("       ''                                             isDelCheck,     ");
        query.append("       x.comp_no                                      compNo,         ");
        query.append("       x.prreclist_id                                 prRecListId,    ");
        query.append("       x.prreclist_no                                 prRecListNo,    ");
        query.append("       SFAIDTODESC(x.dept_id, '', 'DEPT', x.comp_no)  deptDesc,       "); 
        query.getDate("x.rec_date", "recDate,");
        query.append("       SFAIDTODESC(x.vendor_id, '', 'VENDOR', x.comp_no) vendorDesc,  "); 
        query.append("       x.prreclist_status                             prRecListStatus,");
        query.append("       SFACODE_TO_DESC(x.prreclist_status, 'PRRECLIST_STATUS', 'SYS', x.comp_no,'"+user.getLangId()+"') recStatusDesc, "); 
        query.append("       x.part_id                                      partId,         "); 
        query.append("       y.part_no                                      partNo,         ");
        query.append("       CASE WHEN x.part_id IS NULL                                    ");
        query.append("            THEN x.pt_desc||', '||x.pt_size                           ");
        query.append("            ELSE y.description ||', '|| y.pt_size                     ");
        query.append("            END                                       partNameSize,   ");
        query.append("       NVL(x.rec_qty, 0)                              recQty,         "); 
        query.append("       NVL(x.unit_price, 0)                           unitPrice,      "); 
        query.append("       NVL(x.tot_price, 0)                            totPrice,       ");
        query.append("       SFAIDTODESC(x.inspector, '', 'EMP', x.comp_no) inspectorName,  ");  
        query.append("       SFACODE_TO_DESC(x.is_make_part_no,'IS_USE','SYS',x.comp_no, '"+user.getLangId()+"' ) isMakePartNo,     ");  
        query.append("       x.is_make_part_no                              isMakePartNoId  ");  
        query.append("      ,x.plant                                        plantId         ");
        query.append("      ,(SELECT a.description                                          ");
        query.append("         FROM TAPLANT a                                               ");
        query.append("         WHERE a.comp_no = x.comp_no                                  ");
        query.append("         AND a.plant = x.plant                                        ");
        query.append("         )                                            plantDesc       ");
        query.append("      ,x.dept_id                                      deptId          ");
        query.append("      ,x.wcode_id                                     wcodeId         ");
        query.append("      ,x.vendor_id                                    vendorId        ");
        query.append("      ,CASE WHEN x.part_id IS NULL THEN x.pt_desc                     ");
        query.append("            ELSE y.description                                        ");
        query.append("            END                                       PARTDESC        ");
        query.append("      ,CASE WHEN x.part_id IS NULL THEN x.pt_size                     ");
        query.append("            ELSE y.pt_size                                            ");
        query.append("            END                                       PARTSIZE        ");
        query.append("      ,y.model                                        MODEL           ");
        query.append("      ,x.uom                                          uom             ");
        query.append("      ,x.inspector                                    inspector       ");
        query.append("      ,x.part_grade                                   partGrade       ");
        query.append("      ,SFACODE_TO_DESC(x.part_grade,'PART_GRADE','SYS','', '"+user.getLangId()+"')    AS partGradeDesc    ");
        query.append("      ,x.remark                                       remark          ");
        query.append("      ,(SELECT aa.polist_no                                           ");
        query.append("          FROM taptpolist aa                                          ");
        query.append("         WHERE aa.comp_no   = x.comp_no                               ");
        query.append("           AND aa.polist_id = x.polist_id)            poNo            ");
        query.append("      ,(SELECT bb.ptprlist_no                                         ");
        query.append("          FROM taptprlist bb                                          ");
        query.append("          LEFT OUTER JOIN taptpritem cc                               ");
        query.append("                       ON  cc.comp_no    = bb.comp_no                 ");
        query.append("                      AND cc.ptprlist_id = bb.ptprlist_id             ");
        query.append("         WHERE bb.comp_no     = x.comp_no                             ");
        query.append("           AND cc.ptpritem_id = x.ptpritem_id)        reqNo           ");
        query.append("    , y.model                                         ptmodel		    ");
        query.append("FROM   TAPTPRRECLIST x LEFT OUTER JOIN TAPARTS y                      ");
        query.append("ON x.comp_no = y.comp_no                                              ");
        query.append("AND x.part_id = y.part_id                                             ");
        query.append("WHERE 1=1                                                             ");
        query.append(this.getWhere(maPtRecCommonDTO,user));
        query.getOrderByQuery("x.prreclist_id DESC", maPtRecCommonDTO.getOrderBy(), maPtRecCommonDTO.getDirection());
        
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
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        
        query = new QueryBuffer();
        
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
        QueryBuffer query = new QueryBuffer();
      
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
        query.getLikeQuery("CASE WHEN x.part_id IS NULL THEN x.pt_desc||', '||x.pt_size ELSE y.description||', '||y.pt_size END", maPtRecCommonDTO.getFilterPartNameSize());
        
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
      
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no=x.comp_no AND a.plant=x.plant )", 
                maPtRecCommonDTO.getFilterPlantId(), maPtRecCommonDTO.getFilterPlantDesc());
        
        // 구매신청번호
        if(!"".equals(maPtRecCommonDTO.getFilterReqNo())) {
            query.append(" AND EXISTS (SELECT 1                                     ");
            query.append("               FROM taptprlist a                          ");
            query.append("              INNER JOIN taptpritem b                     ");
            query.append("                      ON b.comp_no     = a.comp_no        ");
            query.append("                     AND b.ptprlist_id = a.ptprlist_id    ");
            query.append("              WHERE a.comp_no = x.comp_no                 ");
            query.append("                AND b.ptpritem_id = x.ptpritem_id         ");
            query.getLikeQuery("a.ptprlist_no", maPtRecCommonDTO.getFilterReqNo());
            query.append("            )                                             ");
        }
        
        // 발주번호
        if(!"".equals(maPtRecCommonDTO.getFilterPoNo())) {
            query.append(" AND EXISTS (SELECT 1                         ");
            query.append("               FROM taptpolist a              ");
            query.append("              WHERE a.comp_no   = x.comp_no   ");
            query.append("                AND a.polist_id = x.polist_id ");
            query.getLikeQuery("a.polist_no", maPtRecCommonDTO.getFilterPoNo());
            query.append("            )                                 ");
        }
     
        return query.toString();
    }

    @Override
    public String findTotalCount(MaPtRecCommonDTO maPtRecCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = user.getCompNo();
        
        query.append("SELECT                                ");
        query.append("       COUNT(1)                       ");
        query.append("FROM   TAPTPRRECLIST x LEFT OUTER JOIN TAPARTS y  ");
        query.append("ON x.comp_no = y.comp_no              ");
        query.append("AND x.part_id = y.part_id             ");               
        query.append("WHERE 1=1                             ");
        query.append(this.getWhere(maPtRecCommonDTO,user));

        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
    }
    
    public int insertQrCode(String id, String fileName, User loginUser) {
        QueryBuffer query = new QueryBuffer();
        
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
        
        int result = this.getJdbcTemplate().update(query.toString(),objects);

        return result;
    }

    public int deleteQrCode(String fileName, User loginUser) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TAREPORTPARAM    ");
        query.append("WHERE  comp_no   = ?    ");
        query.append("AND    user_id   = ?    ");
        query.append("AND    file_name = ?    ");
        
        Object[] objects = new Object[]{
                loginUser.getCompNo()
                ,loginUser.getUserId()
                ,fileName
        };
        
        int result = this.getJdbcTemplate().update(query.toString(),objects);
        
        return result;
    }

    @Override
    public String getData(MaPtRecCommonDTO maPtRecCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                        ");
        query.append("    CASE WHEN min(x.exceltab_id) IS NOT NULL  ");
        query.append("             THEN min(x.exceltab_id || ',' || x.description || ',' || x.table_name) ");
        query.append("             ELSE '0'                         ");
        query.append("             END                              ");
        query.append("FROM TAEXCELTAB x                             ");
        query.append("WHERE x.exceltab_no = ?                       ");
        
        Object[] objects = new Object[] {
                maPtRecCommonDTO.getExceltabNo()
        };
    
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }
}