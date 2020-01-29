package dream.part.rec.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.part.rec.dao.LovPoItemAcListDAO;
import dream.part.rec.dto.LovPoItemAcListDTO;

/**
 * 발주 선택 Lov SqlImpl
 * @author  nhkim8548
 * @version $Id: LovPoItemAcListDAOSqlImpl.java,v 1.0 2018/09/13 15:00:00 nhkim8548 Exp $
 * @since   1.0
 *
 * @spring.bean id="lovPoItemAcListDAOTarget"
 * @spring.txbn id="lovPoItemAcListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovPoItemAcListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovPoItemAcListDAO
{
    public List findList(LovPoItemAcListDTO lovPoItemAcListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append(" SELECT                                                           ");
        query.append("        y.polist_id                              AS POLISTID      ");
        query.append("      , y.polist_no                              AS POLISTNO      ");
        query.append("      , x.ptpoitem_id                            AS POITEMID      ");
        query.append("      , z.part_id                                AS PARTID        ");
        query.append("      , z.part_no                                AS PARTNO        ");
        query.append("      , z.description +' / '+ z.pt_size          AS PARTNAMESIZE  ");
        query.append("      , x.po_qty                                 AS POQTY         ");
        query.append("      , x.ptpritem_id                            AS PRITEMID      ");
        query.append("      , ISNULL(x.gr_qty, 0)                      AS GRQTY         ");
        query.append("      , y.plant                                  AS PLANT         ");
        query.append("      , ( SELECT c.description                                    ");
        query.append("            FROM TAPLANT c                                        ");
        query.append("           WHERE c.comp_no = y.comp_no                            ");
        query.append("             AND c.plant = y.plant )             AS PLANTDESC     ");
        query.append("      , y.vendor_id                              AS VENDORID      ");
        query.append("      , y.part_grade                             AS PARTGRADE     ");
        query.append("      , z.description                            AS PARTDESC      ");
        query.append("      , z.pt_size                                AS PARTSIZE      ");
        query.append("      , z.uom                                    AS UOM           ");
        query.append("      , y.wcode_id                               AS WCODEID       ");
        query.append("   FROM TAPTPOITEM x                                              ");
        query.append("  INNER JOIN TAPTPOLIST y                                         ");
        query.append("          ON x.comp_no = y.comp_no                                ");
        query.append("         AND x.polist_id = y.polist_id                            ");
        query.append("  INNER JOIN TAPARTS z                                            ");
        query.append("          ON x.comp_no = z.comp_no                                ");
        query.append("         AND x.part_id = z.part_id                                ");
        query.append("  WHERE x.po_qty > ISNULL(x.gr_qty,0)                             ");
        query.append(this.getWhere(lovPoItemAcListDTO, user));
        query.getOrderByQuery("x.ptpoitem_id","x.ptpoitem_id", lovPoItemAcListDTO.getOrderBy(), lovPoItemAcListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    private String getWhere(LovPoItemAcListDTO lovPoItemAcListDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        // 회사
        query.getAndQuery("x.comp_no", user.getCompNo());
        // 발주번호
        query.getLikeQuery("y.polist_no", lovPoItemAcListDTO.getOrderNo());
        // 부품번호
        query.getLikeQuery("z.part_no", lovPoItemAcListDTO.getPartNo());
        // 부품명
        query.getLikeQuery("z.description", lovPoItemAcListDTO.getPartNameSize());
        // 공장
        query.getPlantCdQuery("y.plant", lovPoItemAcListDTO.getPlantId(), lovPoItemAcListDTO.getPlantDesc(), user.getCompNo());
        
        return query.toString();
    }
}