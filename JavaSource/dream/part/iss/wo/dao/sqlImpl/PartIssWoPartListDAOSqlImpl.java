package dream.part.iss.wo.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.part.iss.wo.dao.PartIssWoPartListDAO;
import dream.part.iss.wo.dto.PartIssWoPartListDTO;

/**
 * 자재출고WO - 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="partIssWoPartListDAOTarget"
 * @spring.txbn id="partIssWoPartListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartIssWoPartListDAOSqlImpl extends BaseJdbcDaoSupportSql implements PartIssWoPartListDAO
{
    public List findWoPartList(PartIssWoPartListDTO partIssWoPartListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                                            ");
        query.append("    ''                                                                            AS isDelCheck   ");
        query.append("    ,''                                                                           AS seqNo        ");
        query.append("    ,b.wo_no                                                                      AS woNo         ");
        query.append("    ,b.description                                                                AS woDesc       ");
        query.append("    ,b.wkor_date                                                                  AS woDate       ");
        query.append("    ,d.item_no                                                                    AS itemNo       ");
        query.append("    ,d.description                                                                AS equipDesc    ");
        query.append("    ,(SELECT full_desc FROM TAEQLOC                                                               ");
        query.append("                       WHERE comp_no = d.comp_no                                                  ");
        query.append("                       AND eqloc_id = d.eqloc_id)                                 AS eqLocDesc    ");
        query.append("    ,dbo.SFACODE_TO_DESC(b.wo_status,'WO_STATUS','SYS','','"+user.getLangId()+"') AS woStatusDesc ");
        query.append("    ,(SELECT description FROM TADEPT                                                              ");
        query.append("                         WHERE comp_no = b.comp_no                                                ");
        query.append("                         AND dept_id = b.dept_id)                                 AS deptDesc     ");
        query.append("    ,(SELECT description FROM TAWKCTR                                                             ");
        query.append("                         WHERE comp_no = b.comp_no                                                ");
        query.append("                         AND wkctr_id = b.wkctr_id)                               AS wkCtrDesc    ");
        query.append("    ,(SELECT emp_name FROM TAEMP                                                                  ");
        query.append("                      WHERE comp_no = b.comp_no                                                   ");
        query.append("                      AND emp_id = b.emp_id)                                      AS empDesc      ");
        query.append("    ,a.use_qty                                                                    AS useQty       ");
        query.append("    ,a.remark                                                                     AS remark       ");
        query.append("    ,a.wopart_id                                                                  AS woPartId     ");
        query.append("FROM TAWOPARTS a INNER JOIN TAWORKORDER b                                                         ");
        query.append("  ON a.comp_no = b.comp_no                                                                        ");
        query.append("  AND a.wkor_id = b.wkor_id                                                                       ");
        query.append("LEFT OUTER JOIN TAWOEQUIP c                                                                       ");
        query.append("  ON b.comp_no = c.comp_no                                                                        ");
        query.append("  AND b.wkor_id = c.wkor_id                                                                       ");
        query.append("INNER JOIN TAEQUIPMENT d                                                                          ");
        query.append("  ON c.comp_no = d.comp_no                                                                        ");
        query.append("  AND c.equip_id = d.equip_id                                                                     ");
        query.append("WHERE 1 = 1                                                                                       ");
        query.append(this.getWhere(partIssWoPartListDTO, user));
        query.getOrderByQuery("a.wopart_id","a.wopart_id", partIssWoPartListDTO.getOrderBy(), partIssWoPartListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(partIssWoPartListDTO.getIsLoadMaxCount(), partIssWoPartListDTO.getFirstRow()));
    }
    
    private String getWhere(PartIssWoPartListDTO partIssWoPartListDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getStringEqualQuery("a.comp_no", user.getCompNo());
        
        query.getAndQuery("a.ptisslist_id", partIssWoPartListDTO.getPtIssListId());
        
        return query.toString();
    }

    @Override
    public String findWoPartTotalCount(PartIssWoPartListDTO partIssWoPartListDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                                        ");
        query.append("    count(1)                                                                                  ");
        query.append("FROM TAWOPARTS a INNER JOIN TAWORKORDER b                                                     ");
        query.append("  ON a.comp_no = b.comp_no                                                                    ");
        query.append("  AND a.wkor_id = b.wkor_id                                                                   ");
        query.append("LEFT OUTER JOIN TAWOEQUIP c                                                                   ");
        query.append("  ON b.comp_no = c.comp_no                                                                    ");
        query.append("  AND b.wkor_id = c.wkor_id                                                                   ");
        query.append("INNER JOIN TAEQUIPMENT d                                                                      ");
        query.append("  ON c.comp_no = d.comp_no                                                                    ");
        query.append("  AND c.equip_id = d.equip_id                                                                 ");
        query.append("WHERE 1 = 1                                                                                   ");
        query.append(this.getWhere(partIssWoPartListDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
    
    public int deleteWoPart(String woPartId, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAWOPARTS SET                                         ");
        query.append("   ptisslist_id = NULL                                       ");
        query.append("WHERE wopart_id = ?                                          ");
        query.append("  AND comp_no   = ?                                          ");
        
        Object[] objects = new Object[] {
                woPartId
                ,user.getCompNo()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }

    @Override
    public int linkWoPartDetail(String woPartId, PartIssWoPartListDTO partIssWoPartListDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAWOPARTS SET                                         ");
        query.append("   ptisslist_id = ?                                          ");
        query.append("WHERE wopart_id = ?                                          ");
        query.append("  AND comp_no   = ?                                          ");
        
        Object[] objects = new Object[] {
                partIssWoPartListDTO.getPtIssListId()
                ,woPartId
                ,user.getCompNo()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    @Override
    public int addWoPartDetail(String wkOrId, PartIssWoPartListDTO partIssWoPartListDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TAWOPARTS (                           ");
        query.append("    comp_no       ,wopart_id      ,wkor_id        ");
        query.append("    ,wcode_id     ,part_id        ,part_grade     ");
        query.append("    ,use_qty      ,unit_price     ,tot_price      ");
        query.append("    ,ptisslist_id                                 ");
        query.append(") SELECT                                          ");
        query.append("    comp_no       ,?              ,?              ");
        query.append("    ,wcode_id     ,part_id        ,part_grade     ");
        query.append("    ,issue_qty    ,unit_price     ,tot_price      ");
        query.append("    ,ptisslist_id                                 ");
        query.append("FROM TAPTISSLIST                                  ");
        query.append("WHERE comp_no    = ?                              ");
        query.append("AND ptisslist_id = ?                              ");
        
        Object[] objects = new Object[] {
                this.getNextSequence("SQAWOPART_ID")
                ,wkOrId
                ,user.getCompNo()
                ,partIssWoPartListDTO.getPtIssListId()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}