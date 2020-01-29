package dream.part.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.list.dao.MaPtMstrWoPtHistListDAO;
import dream.part.list.dto.MaPtMstrCommonDTO;

/**
 * 부품마스터 사용이력 - 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtMstrWoPtHistListDAOTarget"
 * @spring.txbn id="maPtMstrWoPtHistListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtMstrWoPtHistListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtMstrWoPtHistListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrWoPtHistListDTO
     * @return List
     */
    public List findPtMstrWoPtHistList(MaPtMstrCommonDTO maPtMstrCommonDTO,User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                                                                                    ");
        query.append("    ''                                                                                                                                                AS isDelCheck       ");
        query.append("    ,''                                                                                                                                               AS seqNo            ");
        query.append("    ,CASE WHEN b.wkor_date IS NOT NULL THEN SUBSTR(b.wkor_date,1,4)||'-'||SUBSTR(b.wkor_date,5,2)||'-'||SUBSTR(b.wkor_date,7,2) ELSE b.wkor_date END  AS useDate          ");
        query.append("    ,(SELECT bb.item_no                                                                                                                                                   ");
        query.append("      FROM TAWOEQUIP aa INNER JOIN TAEQUIPMENT bb                                                                                                                         ");
        query.append("      ON aa.comp_no=bb.comp_no                                                                                                                                            ");
        query.append("      AND aa.equip_id=bb.equip_id                                                                                                                                         ");
        query.append("      WHERE aa.comp_no=b.comp_no                                                                                                                                          ");
        query.append("      AND aa.wkor_id=b.wkor_id)                                                                                                                       AS itemNo           ");
        query.append("    ,(SELECT bb.description                                                                                                                                               ");
        query.append("      FROM TAWOEQUIP aa INNER JOIN TAEQUIPMENT bb                                                                                                                         ");
        query.append("      ON aa.comp_no=bb.comp_no                                                                                                                                            ");
        query.append("      AND aa.equip_id=bb.equip_id                                                                                                                                         ");
        query.append("      WHERE aa.comp_no=b.comp_no                                                                                                                                          ");
        query.append("      AND aa.wkor_id=b.wkor_id)                                                                                                                       AS equipName        ");
        query.append("    ,b.wo_no                                                                                                                                          AS woNo             ");
        query.append("    ,b.description                                                                                                                                    AS woName           ");
        query.append("    ,SFACODE_TO_DESC(a.part_grade, 'PART_GRADE', 'SYS', '','"+user.getLangId()+"')                                                                    AS partGradeDesc    ");
        query.append("    ,a.use_qty                                                                                                                                        AS useQty           ");
        query.append("FROM TAWOPARTS a INNER JOIN TAWORKORDER b                                                                                                                                 ");
        query.append("ON a.comp_no = b.comp_no                                                                                                                                                  ");
        query.append("AND a.wkor_id = b.wkor_id                                                                                                                                                 ");
        query.append("WHERE 1 = 1                                                                                                                                                               ");
        query.append(this.getWhere(maPtMstrCommonDTO, user));
        query.getOrderByQuery("b.wkor_date desc", maPtMstrCommonDTO.getOrderBy(), maPtMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPtMstrCommonDTO.getIsLoadMaxCount(), maPtMstrCommonDTO.getFirstRow()));
    }
    
    private String getWhere(MaPtMstrCommonDTO maPtMstrCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getStringEqualQuery("a.comp_no", user.getCompNo());
        query.getStringEqualQuery("a.part_id", maPtMstrCommonDTO.getPartId());
        
        return query.toString();
    }
    
    @Override
    public String findTotalCount(MaPtMstrCommonDTO maPtMstrCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                        ");
        query.append("       COUNT(1)                               ");
        query.append("FROM TAWOPARTS a INNER JOIN TAWORKORDER b     ");
        query.append("ON a.comp_no = b.comp_no                      ");
        query.append("AND a.wkor_id = b.wkor_id                     ");
        query.append("WHERE 1 = 1                                   ");
        query.append(this.getWhere(maPtMstrCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}