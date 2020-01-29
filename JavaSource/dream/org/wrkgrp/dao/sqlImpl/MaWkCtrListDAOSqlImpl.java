package dream.org.wrkgrp.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.org.wrkgrp.dao.MaWkCtrListDAO;
import dream.org.wrkgrp.dto.MaWkCtrCommonDTO;

/**
 * 작업그룹 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maWkCtrListDAOTarget"
 * @spring.txbn id="maWkCtrListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWkCtrListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWkCtrListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWkCtrCommonDTO
     * @return List
     */
    public List findWkCtrList(MaWkCtrCommonDTO maWkCtrCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT x.wkctr_id                             ID,         ");
        query.append("       ''                                     SEQNO,      ");
        query.append("       ''                                     ISDELCHECK, ");
        query.append("       x.wkctr_id                             WKCTRID,    ");
        query.append("       x.wkctr_no                             WKCTRNO,    ");
        query.append("       x.description                          WKCTRDESC,  ");
        query.append("       x.p_wkctr_id                           PAWKCTRID,  ");
        query.append("       (SELECT description								");
        query.append("          FROM TAWKCTR									");
        query.append("			WHERE comp_no = x.comp_no						");
        query.append("			AND wkctr_id = x.p_wkctr_id) 		PAWKCTRDESC,");
        query.append("       dbo.SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId()+"') ISUSE ,    ");
        query.append("       x.ord_no                               ORDNO,      ");
        query.append("       x.remark                               REMARK,     ");
        query.append("       MIN(y.lvl) OVER()    AS MINLVL,     ");
        query.append("       y.lvl LEVEL                                              ");
        query.append("FROM   TAWKCTR x, (SELECT * FROM dbo.SFAWKCTR_ALL('"+maWkCtrCommonDTO.getCompNo()+"','0')) y                                              ");
        query.append("WHERE x.wkctr_id = y.wkctr_id	");
    	query.append("AND  x.comp_no = '"+maWkCtrCommonDTO.getCompNo()+"'		");
        query.append(this.getWhere(maWkCtrCommonDTO, user));
        query.append("ORDER BY ISNULL(x.ord_no, '99999999')");

        
        return getJdbcTemplate().queryForList(query.toString());
    } 
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWkCtrCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaWkCtrCommonDTO maWkCtrCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getLikeQuery("x.wkctr_no", maWkCtrCommonDTO.getFilterWkCtrNo());
        query.getLikeQuery("x.full_desc", maWkCtrCommonDTO.getFilterWkCtrDesc());
        
        // 사용여부 
        query.getSysCdQuery("x.is_use", maWkCtrCommonDTO.getFilterIsUse(), maWkCtrCommonDTO.getFilterIsUse(), "IS_USE", maWkCtrCommonDTO.getCompNo(), user.getLangId());
        
        if(!"".equals(maWkCtrCommonDTO.getFilterPaWkCtrId()))
        {
            query.getAndQuery("x.p_wkctr_id", maWkCtrCommonDTO.getFilterPaWkCtrId());
        }
        else if(!"".equals(maWkCtrCommonDTO.getFilterPaWkCtrDesc()))
        {
            query.append("AND x.p_wkctr_id IN (SELECT a.wkctr_id            ");
            query.append("                    FROM   TAWKCTR a              ");
            query.append("                    WHERE  1=1                    ");
            query.getAndQuery("a.comp_no", maWkCtrCommonDTO.getCompNo());
            query.getLikeQuery("a.full_desc", maWkCtrCommonDTO.getFilterPaWkCtrDesc());
            query.append("                    )                             ");
        }
        
        return query.toString();
    }

    /**
     * 삭제
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param wkCtrId
     * @return
     */
    public int deleteWkCtr(String compNo, String wkCtrId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE TAWKCTR          ");
        query.append("WHERE  comp_no  = ?     ");
        query.append("  AND  wkctr_id  = ?    ");
        
        Object[] objects = new Object[] {   
                compNo,
                wkCtrId
                };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}