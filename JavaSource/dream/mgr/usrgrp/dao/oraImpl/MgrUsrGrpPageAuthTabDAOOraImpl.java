package dream.mgr.usrgrp.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.usrgrp.dao.MgrUsrGrpPageAuthTabDAO;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthTabDTO;

/**
 * 화면권한설정상세탭탭권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="mgrUsrGrpPageAuthTabDAOTarget"
 * @spring.txbn id="mgrUsrGrpPageAuthTabDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrUsrGrpPageAuthTabDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrUsrGrpPageAuthTabDAO
{
    @Override
    public List findList(MgrUsrGrpPageAuthTabDTO mgrUsrGrpPageAuthTabDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                                                    ");
        query.append("    ''                                                                                                                    AS ISDELCHECK   ");
        query.append("    ,''                                                                                                                   AS SEQNO        ");
        query.append("    ,a.pgpage_id                                                                                                          AS PGPAGEID     ");
        query.append("    ,b.usrgrp_id                                                                                                          AS USRGRPID     ");
        query.append("    ,c.ugpgpgau_id                                                                                                        AS UGPGPGAUID   ");
        query.append("    ,(SELECT file_name FROM TAPAGE WHERE page_id = a.c_page_id)                                                           AS FILENAME     ");
        query.append("    ,(SELECT key_name FROM TALANG WHERE key_type = a.key_type AND key_no = a.key_no AND lang = '"+user.getLangId()+"')    AS TABNAME      ");
        query.append("    ,CASE WHEN c.ugpgpgau_id IS NULL THEN 'N' ELSE 'Y' END                                                                AS ISAUTH       ");
        query.append("    ,CASE WHEN (SELECT COUNT(1) FROM TAUGPGAU WHERE page_id = a.c_page_id AND usrgrp_id = b.usrgrp_id) > 0 THEN 'Y' ELSE 'N' END  AS PAGEAUTH ");
        query.append("FROM TAPGPAGE a                                                                                                                           ");
        query.append("INNER JOIN TAUSRGRP b ON 1 = 1                                                                                                            ");
        query.append(this.getWhere(mgrUsrGrpPageAuthTabDTO, user));
        query.append("LEFT OUTER JOIN TAUGPGPGAU c ON a.pgpage_id = c.pgpage_id AND b.comp_no = c.comp_no AND b.usrgrp_id = c.usrgrp_id                         ");
        query.getOrderByQuery("a.ord_no", mgrUsrGrpPageAuthTabDTO.getOrderBy(), mgrUsrGrpPageAuthTabDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(mgrUsrGrpPageAuthTabDTO.getIsLoadMaxCount(), mgrUsrGrpPageAuthTabDTO.getFirstRow()));
    }
    
    @Override
    public int insertDetail(MgrUsrGrpPageAuthTabDTO mgrUsrGrpPageAuthTabDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("INSERT INTO TAUGPGPGAU(                       ");
        query.append("   comp_no        ,ugpgpgau_id   ,pgpage_id   ");
        query.append("   ,usrgrp_id                                 ");
        query.append(")VALUES(                                      ");
        query.append("   ?              ,?              ,?          ");
        query.append("   ,?                                         ");
        query.append(")                                             ");
        
        Object[] objects = new Object[] {
            user.getCompNo()
            ,mgrUsrGrpPageAuthTabDTO.getUgpgpgauId()
            ,mgrUsrGrpPageAuthTabDTO.getPgpageId()
            ,mgrUsrGrpPageAuthTabDTO.getUsrgrpId()
        };
        return getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public int deleteList(String pgpageId, String usrgrpId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAUGPGPGAU        ");
        query.append("WHERE  comp_no        = ?     ");
        query.append("  AND  pgpage_id      = ?     ");
        query.append("  AND  usrgrp_id      = ?     ");
        
        Object[] objects = new Object[] {   
            user.getCompNo()
            ,pgpageId
            ,usrgrpId
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public String findTotalCount(MgrUsrGrpPageAuthTabDTO mgrUsrGrpPageAuthTabDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                            ");
        query.append("    COUNT(1)                                                                                                      ");
        query.append("FROM TAPGPAGE a                                                                                                   ");
        query.append("INNER JOIN TAUSRGRP b ON 1 = 1                                                                                    ");
        query.append(this.getWhere(mgrUsrGrpPageAuthTabDTO, user));
        query.append("LEFT OUTER JOIN TAUGPGPGAU c ON a.pgpage_id = c.pgpage_id AND b.comp_no = c.comp_no AND b.usrgrp_id = c.usrgrp_id ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
    private String getWhere(MgrUsrGrpPageAuthTabDTO mgrUsrGrpPageAuthTabDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.append("AND EXISTS(SELECT page_id FROM TAPAGE WHERE page_id = a.c_page_id AND is_chkauth = 'Y')");
        query.getStringEqualQuery("b.comp_no", user.getCompNo());
        query.getStringEqualQuery("a.page_id", mgrUsrGrpPageAuthTabDTO.getPageId());
        query.getStringEqualQuery("b.usrgrp_id", mgrUsrGrpPageAuthTabDTO.getUsrgrpId());
        if(!"".equals(mgrUsrGrpPageAuthTabDTO.getPgpageId())){
            query.getAndQuery("a.pgpage_id", mgrUsrGrpPageAuthTabDTO.getPgpageId());
            return query.toString();
        }
        
        return query.toString();
    }
}