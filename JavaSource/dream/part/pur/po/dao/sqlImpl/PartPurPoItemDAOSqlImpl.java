package dream.part.pur.po.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.part.pur.po.dao.PartPurPoItemDAO;
import dream.part.pur.po.dto.PartPurPoItemDTO;

/**
 * 발주품목 - dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="partPurPoItemDAOTarget"
 * @spring.txbn id="partPurPoItemDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartPurPoItemDAOSqlImpl extends BaseJdbcDaoSupportSql implements PartPurPoItemDAO
{
    @Override
    public List find(PartPurPoItemDTO partPurPoItemDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("    a.ptpoitem_id     AS ptPoItemId                       ");
        query.append("    ,a.polist_id      AS poListId                         ");
        query.append("    ,a.ptpritem_id    AS ptPrItemId                       ");
        query.append("    ,a.part_id        AS partId                           ");
        query.append("    ,a.part_grade     AS partGrade                        ");
        query.append("    ,a.po_qty         AS poQty                            ");
        query.append("    ,a.unit_price     AS unitPrice                        ");
        query.append("    ,a.tot_price      AS totPrice                         ");
        query.append("    ,a.gr_qty         AS grQty                            ");
        query.append("    ,a.gr_on_qty      AS grOnQty                          ");
        query.append("    ,a.remark         AS remark                           ");
        query.append("    ,b.polist_status  AS poListStatus                     ");
        query.append("    ,b.plant          AS plant                            ");
        query.append("    ,b.dept_id        AS deptId                           ");
        query.append("    ,b.wcode_id       AS wcodeId                          ");
        query.append("    ,b.vendor_id      AS vendorId                         ");
        query.append("    ,b.po_date        AS poDate                           ");
        query.append("    ,c.description    AS partDesc                         ");
        query.append("    ,c.pt_size        AS ptSize                           ");
        query.append("    ,c.uom            AS uom                              ");
        query.append("FROM TAPTPOITEM a LEFT OUTER JOIN TAPTPOLIST b            ");
        query.append("ON a.comp_no = b.comp_no AND a.polist_id = b.polist_id    ");
        query.append("LEFT OUTER JOIN TAPARTS c                                 ");
        query.append("ON a.comp_no = c.comp_no AND a.part_id = c.part_id        ");
        query.append("WHERE 1 = 1                                               ");
        query.getStringEqualQuery("a.comp_no", user.getCompNo());
        query.getAndNumKeyQuery("a.ptpoitem_id", partPurPoItemDTO.getPtPoItemId());
        query.getAndNumKeyQuery("a.polist_id", partPurPoItemDTO.getPoListId());
        query.getAndNumKeyQuery("a.ptpritem_id", partPurPoItemDTO.getPtPrItemId());

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    @Override
    public int insert(PartPurPoItemDTO partPurPoItemDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TAPTPOITEM (                                  ");
        query.append("  comp_no,   ptpoitem_id,   polist_id,    ptpritem_id,    ");
        query.append("  part_id,   part_grade,    po_qty,       unit_price,     ");
        query.append("  tot_price, gr_qty,        gr_on_qty,    remark          ");
        query.append(") VALUES (                                                ");
        query.append("  ?,         ?,             ?,            ?,              ");
        query.append("  ?,         ?,             ?,            ?,              ");
        query.append("  ?,         ?,             ?,            ?               ");
        query.append(")                                                         ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
                ,partPurPoItemDTO.getPtPoItemId()
                ,partPurPoItemDTO.getPoListId()
                ,partPurPoItemDTO.getPtPrItemId()
                ,partPurPoItemDTO.getPartId()
                ,partPurPoItemDTO.getPartGrade()
                ,CommonUtil.getRowMoneyToNum(partPurPoItemDTO.getPoQty())
                ,CommonUtil.getRowMoneyToNum(partPurPoItemDTO.getUnitPrice())
                ,CommonUtil.getRowMoneyToNum(partPurPoItemDTO.getTotPrice())
                ,CommonUtil.getRowMoneyToNum(partPurPoItemDTO.getGrQty())
                ,CommonUtil.getRowMoneyToNum(partPurPoItemDTO.getGrOnQty())
                ,partPurPoItemDTO.getRemark()
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }

    @Override
    public int update(PartPurPoItemDTO partPurPoItemDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAPTPOITEM  SET              ");
        query.append("       part_id            = ?,      ");
        query.append("       part_grade         = ?,      ");
        query.append("       po_qty             = ?,      ");
        query.append("       unit_price         = ?,      ");
        query.append("       tot_price          = ?,      ");
        query.append("       gr_qty             = ?,      ");
        query.append("       gr_on_qty          = ?,      ");
        query.append("       remark             = ?       ");
        query.append("WHERE  comp_no            = ?       ");
        query.append("  AND  ptpoitem_id        = ?       ");
        
        Object[] objects = new Object[] {
                partPurPoItemDTO.getPartId()
                ,partPurPoItemDTO.getPartGrade()
                ,CommonUtil.getRowMoneyToNum(partPurPoItemDTO.getPoQty())
                ,CommonUtil.getRowMoneyToNum(partPurPoItemDTO.getUnitPrice())
                ,CommonUtil.getRowMoneyToNum(partPurPoItemDTO.getTotPrice())
                ,CommonUtil.getRowMoneyToNum(partPurPoItemDTO.getGrQty())
                ,CommonUtil.getRowMoneyToNum(partPurPoItemDTO.getGrOnQty())
                ,partPurPoItemDTO.getRemark()
                ,user.getCompNo()
                ,partPurPoItemDTO.getPtPoItemId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}