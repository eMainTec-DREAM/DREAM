package dream.invt.list.dao.sqlImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.invt.list.dao.InvtWoRsltDAO;
import dream.invt.list.dto.InvtWoRsltDTO;

/**
 * @author  ghlee
 * @version $Id$
 * @since   1.0
 * @spring.bean id="invtWoRsltDAOTarget"
 * @spring.txbn id="invtWoRsltDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtWoRsltDAOSqlImpl extends BaseJdbcDaoSupportSql implements InvtWoRsltDAO
{
    @Override
    public List findList(InvtWoRsltDTO invtWoRsltDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                                                                    ");
        query.append("     ''                                                                                               AS SEQNO            ");
        query.append("    ,''                                                                                               AS ISDELCHECK       ");
        query.append("    ,a.invtwork_id                                                                                    AS INVTWORKID       ");
        query.append("    ,b.wkor_id                                                                                        AS WKORID           ");
        query.append("    ,(SELECT param1 FROM TACDSYSD WHERE list_type = b.wo_type+'_TYPE' AND cdsysd_no = b.pm_type)      AS PARAM            ");
        query.append("    ,b.wo_no                                                                                          AS WONO             ");
        query.append("    ,b.description                                                                                    AS WODESC           ");
        query.append("    ,b.wkor_date                                                                                      AS WODATE           ");
        query.append("    ,d.description                                                                                    AS EQUIPDESC        ");
        query.append("    ,d.item_no                                                                                        AS EQUIPNO          ");
        query.append("    ,(SELECT description FROM TADEPT WHERE comp_no = b.comp_no AND dept_id = b.dept_id)               AS DEPTDESC         ");
        query.append("    ,dbo.SFACODE_TO_DESC(b.wo_type,'WO_TYPE','SYS','','"+user.getLangId()+"')                         AS WOTYPEDESC       ");
        query.append("    ,dbo.SFACODE_TO_DESC(b.pm_type,b.wo_type+'_TYPE','SYS','','"+user.getLangId()+"')                 AS PMTYPEDESC       ");
        query.append("    ,dbo.SFACODE_TO_DESC(b.wo_status,'WO_STATUS','SYS','','"+user.getLangId()+"')                     AS WOSTATUSDESC     ");
        query.append("    ,b.perform                                                                                        AS REMARK           ");
        query.append("FROM TAINVTWORK a INNER JOIN TAWORKORDER b                                                                                ");
        query.append("ON a.comp_no = b.comp_no AND a.wkor_id = b.wkor_id                                                                        ");
        query.append("LEFT OUTER JOIN TAWOEQUIP c                                                                                               ");
        query.append("ON b.comp_no = c.comp_no AND b.wkor_id = c.wkor_id                                                                        ");
        query.append("LEFT OUTER JOIN TAEQUIPMENT d                                                                                             ");
        query.append("ON c.comp_no = d.comp_no AND c.equip_id = d.equip_id                                                                      ");
        query.append("WHERE 1 = 1                                                                                                               ");
        query.append(this.getWhere(invtWoRsltDTO, user));
        query.getOrderByQuery("a.invtwork_id", "b.wkor_date", invtWoRsltDTO.getOrderBy(), invtWoRsltDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(invtWoRsltDTO.getIsLoadMaxCount(), invtWoRsltDTO.getFirstRow()));
    }
    
    @Override
    public int deleteList(String deleteRow, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE FROM TAINVTWORK                           ");
        query.append("WHERE comp_no    = '" + user.getCompNo() + "'    ");
        query.append("AND invtwork_id  = '" + deleteRow + "'           ");
        
        return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(InvtWoRsltDTO invtWoRsltDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("a.comp_no", user.getCompNo());
        query.getStringEqualQuery("a.invtlist_id", invtWoRsltDTO.getInvtlistId());
        
        if(!"".equals(invtWoRsltDTO.getWkorId()))
        {
            query.getAndQuery("a.wkor_id", invtWoRsltDTO.getWkorId());
            return query.toString();
        }
        
        query.getAndQuery("a.invtwork_method", invtWoRsltDTO.getInvtworkMethod());
        
        return query.toString();
    }

    @Override
    public String findTotalCount(InvtWoRsltDTO invtWoRsltDTO, User user) throws Exception {

        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                ");
        query.append("    COUNT(1)                                          ");
        query.append("FROM TAINVTWORK a INNER JOIN TAWORKORDER b            ");
        query.append("ON a.comp_no = b.comp_no AND a.wkor_id = b.wkor_id    ");
        query.append("LEFT OUTER JOIN TAWOEQUIP c                           ");
        query.append("ON b.comp_no = c.comp_no AND b.wkor_id = c.wkor_id    ");
        query.append("LEFT OUTER JOIN TAEQUIPMENT d                         ");
        query.append("ON c.comp_no = d.comp_no AND c.equip_id = d.equip_id  ");
        query.append("WHERE 1 = 1                                           ");
        query.append(this.getWhere(invtWoRsltDTO, user));
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
	
	public int[] insertList(final List<InvtWoRsltDTO> list, final User user) throws Exception 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("INSERT INTO TAINVTWORK(   ");
        query.append("    comp_no               ");
        query.append("  , invtwork_id           ");
        query.append("  , invtlist_id           ");
        query.append("  , wkor_id               ");
        query.append("  , invtwork_method       ");
        query.append(")VALUES(                  ");
        query.append("    ?                     ");
        query.append("  , ?                     ");
        query.append("  , ?                     ");
        query.append("  , ?                     ");
        query.append("  , ?                     ");
        query.append(" )                        ");
        
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {

            @Override
            public int getBatchSize()
            {
                return list.size();
            }

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
                InvtWoRsltDTO invtWoRsltDTO = list.get(i);

                Object[] objects = new Object[] {
                        user.getCompNo()
                      , invtWoRsltDTO.getInvtworkId()
                      , invtWoRsltDTO.getInvtlistId()
                      , invtWoRsltDTO.getWkorId()
                      , invtWoRsltDTO.getInvtworkMethod()
                };
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, getObject(objects[j]));
                }
            }
            
        });
	}
}