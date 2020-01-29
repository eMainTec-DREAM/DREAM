package dream.mgr.usrgrp.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mgr.usrgrp.dao.MgrUsrGrpPageAuthBtnDAO;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthBtnDTO;

/**
 * 화면권한설정상세탭버튼권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="mgrUsrGrpPageAuthBtnDAOTarget"
 * @spring.txbn id="mgrUsrGrpPageAuthBtnDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrUsrGrpPageAuthBtnDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrUsrGrpPageAuthBtnDAO
{
    @Override
    public List findList(MgrUsrGrpPageAuthBtnDTO mgrUsrGrpPageAuthBtnDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                                                                                    ");
        query.append("    ''                                                                                                                    AS ISDELCHECK   ");
        query.append("    ,''                                                                                                                   AS SEQNO        ");
        query.append("    ,a.pgbtn_id                                                                                                           AS PGBTNID      ");
        query.append("    ,b.usrgrp_id                                                                                                          AS USRGRPID     ");
        query.append("    ,c.ugpgbtnau_id                                                                                                       AS UGPGBTNAUID  ");
        query.append("    ,(SELECT button_no FROM TABUTTON WHERE button_id = a.button_id)                                                       AS BUTTONNO     ");
        query.append("    ,(SELECT key_name FROM TALANG WHERE key_type = a.key_type AND key_no = a.key_no AND lang = '"+user.getLangId()+"')    AS BUTTONNAME   ");
        query.append("    ,a.button_loc                                                                                                         AS BUTTONLOC    ");
        query.append("    ,CASE WHEN c.ugpgbtnau_id IS NULL THEN 'N' ELSE 'Y' END                                                               AS ISAUTH       ");
        query.append("FROM TAPGBTN a                                                                                                                            ");
        query.append("INNER JOIN TAUSRGRP b ON 1 = 1                                                                                                            ");
        query.append(this.getWhere(mgrUsrGrpPageAuthBtnDTO, user));
        query.append("LEFT OUTER JOIN TAUGPGBTNAU c ON a.pgbtn_id = c.pgbtn_id AND b.comp_no = c.comp_no AND b.usrgrp_id = c.usrgrp_id                          ");
        query.getOrderByQuery("a.pgbtn_id","a.button_loc, a.ord_no", mgrUsrGrpPageAuthBtnDTO.getOrderBy(), mgrUsrGrpPageAuthBtnDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(mgrUsrGrpPageAuthBtnDTO.getIsLoadMaxCount(), mgrUsrGrpPageAuthBtnDTO.getFirstRow()));
    }
    
    @Override
    public int insertDetail(MgrUsrGrpPageAuthBtnDTO mgrUsrGrpPageAuthBtnDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("INSERT INTO TAUGPGBTNAU(                      ");
        query.append("   comp_no        ,ugpgbtnau_id   ,pgbtn_id   ");
        query.append("   ,usrgrp_id                                 ");
        query.append(")VALUES(                                      ");
        query.append("   ?              ,?              ,?          ");
        query.append("   ,?                                         ");
        query.append(")                                             ");
        
        Object[] objects = new Object[] {
            user.getCompNo()
            ,mgrUsrGrpPageAuthBtnDTO.getUgpgbtnauId()
            ,mgrUsrGrpPageAuthBtnDTO.getPgbtnId()
            ,mgrUsrGrpPageAuthBtnDTO.getUsrgrpId()
        };
        return getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public int deleteList(String pgbtnId, String usrgrpId, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAUGPGBTNAU       ");
        query.append("WHERE  comp_no        = ?     ");
        query.append("  AND  pgbtn_id       = ?     ");
        query.append("  AND  usrgrp_id      = ?     ");
        
        Object[] objects = new Object[] {   
            user.getCompNo()
            ,pgbtnId
            ,usrgrpId
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public String findTotalCount(MgrUsrGrpPageAuthBtnDTO mgrUsrGrpPageAuthBtnDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                                                            ");
        query.append("    COUNT(1)                                                                                                      ");
        query.append("FROM TAPGBTN a                                                                                                    ");
        query.append("INNER JOIN TAUSRGRP b ON 1 = 1                                                                                    ");
        query.append(this.getWhere(mgrUsrGrpPageAuthBtnDTO, user));
        query.append("LEFT OUTER JOIN TAUGPGBTNAU c ON a.pgbtn_id = c.pgbtn_id AND b.comp_no = c.comp_no AND b.usrgrp_id = c.usrgrp_id  ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
    
    private String getWhere(MgrUsrGrpPageAuthBtnDTO mgrUsrGrpPageAuthBtnDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getStringEqualQuery("a.is_chkauth", "Y");
        query.getStringEqualQuery("b.comp_no", user.getCompNo());
        query.getStringEqualQuery("a.page_id", mgrUsrGrpPageAuthBtnDTO.getPageId());
        query.getStringEqualQuery("b.usrgrp_id", mgrUsrGrpPageAuthBtnDTO.getUsrgrpId());
        if(!"".equals(mgrUsrGrpPageAuthBtnDTO.getPgbtnId())){
            query.getAndQuery("a.pgbtn_id", mgrUsrGrpPageAuthBtnDTO.getPgbtnId());
            return query.toString();
        }
        
        return query.toString();
    }
}