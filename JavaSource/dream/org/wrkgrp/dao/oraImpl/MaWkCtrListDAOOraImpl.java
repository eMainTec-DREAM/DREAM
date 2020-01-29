package dream.org.wrkgrp.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
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
public class MaWkCtrListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWkCtrListDAO
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
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT x.wkctr_id                             id,         ");
        query.append("       ''                                     seqNo,      ");
        query.append("       ''                                     isDelCheck, ");
        query.append("       x.wkctr_id                             wkCtrId,    ");
        query.append("       x.wkctr_no                             wkCtrNo,    ");
        query.append("       x.description                          wkCtrDesc,  ");
        query.append("       x.p_wkctr_id                           paWkCtrId,  ");
        query.append("       (SELECT description								");
        query.append("          FROM TAWKCTR									");
        query.append("			WHERE comp_no = x.comp_no						");
        query.append("			AND wkctr_id = x.p_wkctr_id) 		paWkCtrDesc,");
        query.append("       SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId()+"') isUse ,    ");
        query.append("       x.ord_no                               ordNo,      ");
        query.append("       x.remark                               remark,     ");
        query.append("       MIN(LEVEL) OVER()    AS minLvl,     ");
        query.append("       LEVEL                                              ");
        query.append("FROM   TAWKCTR x                                          ");
    	query.append("WHERE  x.comp_no = '"+maWkCtrCommonDTO.getCompNo()+"'		");
        query.append(this.getWhere(maWkCtrCommonDTO, user));
        query.append("START WITH p_wkctr_id = '0'                               ");
        query.append("CONNECT BY PRIOR wkctr_id = p_wkctr_id                    ");
        query.append("ORDER BY x.ord_no");
        
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
        QueryBuffer query = new QueryBuffer();
        
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
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TAWKCTR          ");
        query.append("WHERE  comp_no  = ?     ");
        query.append("  AND  wkctr_id  = ?    ");
        
        Object[] objects = new Object[] {   
                compNo,
                wkCtrId
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
}