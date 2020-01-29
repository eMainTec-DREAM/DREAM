package dream.mgr.usrgrp.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.categ.list.dto.MaEqCatalogDetailDTO;
import dream.mgr.usrgrp.dao.MgrUsrGrpPageAuthDAO;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthDTO;

/**
 * 화면권한설정
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="mgrUsrGrpPageAuthDAOTarget"
 * @spring.txbn id="mgrUsrGrpPageAuthDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrUsrGrpPageAuthDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrUsrGrpPageAuthDAO
{
    public String getColums(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                        ");
        query.append("    ''                                                                            AS ISDELCHECK               ");
        query.append("    ,''                                                                           AS SEQNO                    ");
        query.append("    ,a.page_id                                                                    AS PAGEID                   ");
        query.append("    ,b.usrgrp_id                                                                  AS USRGRPID                 ");
        query.append("    ,c.ugpgau_id                                                                  AS ugpgauId                 ");
        query.append("    ,b.usrgrp_no                                                                  AS USRGRPNO                 ");
        query.append("    ,b.group_name                                                                 AS GROUPNAME                ");
        query.append("    ,a.file_name                                                                  AS FILENAME                 ");
        query.append("    ,a.description                                                                AS PAGEDESC                 ");
        query.append("    ,a.service_type                                                               AS SERVICETYPE              ");
        query.append("    ,SFACODE_TO_DESC(a.service_type,'SERVICE_TYPE','SYS','','"+user.getLang()+"') AS SERVICETYPEDESC          ");
        query.append("    ,CASE WHEN c.ugpgau_id IS NULL THEN 'N' ELSE 'Y' END                          AS ISAUTH                   ");
        query.append("    ,a.remark                                                                     AS REMARK                   ");
        
        return query.toString();
    }
    
    /**
     * @author  HN4741
     * @version $Id:$
     * @since   1.0
     * 
     * TAPAGE a, TAUSRGRP b, TAUPGAU c
     * @param mgrUsrGrpPageAuthDTO
     * @param user
     * @return
     */
    public String getTables(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("FROM TAPAGE a                                                                                                 ");
        query.append("INNER JOIN TAUSRGRP b ON 1 = 1                                                                                ");
        query.append("LEFT OUTER JOIN TAUGPGAU c ON a.page_id = c.page_id AND b.comp_no = c.comp_no AND b.usrgrp_id = c.usrgrp_id   ");
        
        return query.toString();
    }
    
    public String getOrderBy(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.getOrderByQuery("b.usrgrp_no, a.file_name", mgrUsrGrpPageAuthDTO.getOrderBy(), mgrUsrGrpPageAuthDTO.getDirection());
       
        return query.toString();
    }
    
    @Override
    public List findList(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(getColums(mgrUsrGrpPageAuthDTO,user));
        query.append(getTables(mgrUsrGrpPageAuthDTO,user));
        query.append(this.getWhere(mgrUsrGrpPageAuthDTO, user));
        query.append(getOrderBy(mgrUsrGrpPageAuthDTO,user));
        
        return getJdbcTemplate().queryForList(query.toString(mgrUsrGrpPageAuthDTO.getIsLoadMaxCount(), mgrUsrGrpPageAuthDTO.getFirstRow()));
    }
    
    @Override
    public int[] insertDetail(final List<MgrUsrGrpPageAuthDTO> list , final User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO TAUGPGAU(                         ");
        query.append("   comp_no        ,ugpgau_id      ,page_id    ");
        query.append("   ,usrgrp_id                                 ");
        query.append(")VALUES(                                      ");
        query.append("   ?              ,?              ,?          ");
        query.append("   ,?                                         ");
        query.append(")                                             ");
        
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {
            
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
                MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO = list.get(i);

                Object[] objects = new Object[] {
                        user.getCompNo()
                        ,mgrUsrGrpPageAuthDTO.getUgpgauId()
                        ,mgrUsrGrpPageAuthDTO.getPageId()
                        ,mgrUsrGrpPageAuthDTO.getUsrgrpId()
                };
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
            @Override
            public int getBatchSize()
            {
                return list.size();
            }
        });

    }

    @Override
    public int deleteList(String pageId, String usrgrpId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAUGPGAU          ");
        query.append("WHERE  comp_no        = ?     ");
        query.append("  AND  page_id        = ?     ");
        query.append("  AND  usrgrp_id      = ?     ");
        
        Object[] objects = new Object[] {   
            user.getCompNo()
            ,pageId
            ,usrgrpId
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public String findTotalCount(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                        ");
        query.append("    COUNT(1)                                                                                                  ");
        query.append(getTables(mgrUsrGrpPageAuthDTO,user));
        query.append(this.getWhere(mgrUsrGrpPageAuthDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
    public String getWhere(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.append("WHERE 1 = 1");
        query.getStringEqualQuery("a.is_chkauth", "Y");
        query.getStringEqualQuery("a.is_use", "Y");
        query.getStringEqualQuery("b.comp_no", user.getCompNo());
        if(!"".equals(mgrUsrGrpPageAuthDTO.getPageId()) && !"".equals(mgrUsrGrpPageAuthDTO.getUsrgrpId())){
            query.getAndQuery("a.page_id", mgrUsrGrpPageAuthDTO.getPageId());
            query.getAndQuery("b.usrgrp_id", mgrUsrGrpPageAuthDTO.getUsrgrpId());
            return query.toString();
        }
        
        //권한그룹
        query.getCodeLikeQuery("b.usrgrp_id", "b.group_name", mgrUsrGrpPageAuthDTO.getFilterUsrgrpId(), mgrUsrGrpPageAuthDTO.getFilterGroupName());
        //서비스구분
        query.getSysCdQuery("a.service_type", mgrUsrGrpPageAuthDTO.getFilterServiceType(), mgrUsrGrpPageAuthDTO.getFilterServiceTypeDesc(), "SERVICE_TYPE", user.getCompNo(), user.getLang());
        //화면ID
        query.getLikeQuery("a.file_name", mgrUsrGrpPageAuthDTO.getFilterFileName());
        //권한여부
        String isAuth = mgrUsrGrpPageAuthDTO.getFilterIsAuth();
        if("Y".equals(isAuth.toUpperCase())) {
            query.append("AND c.ugpgau_id IS NOT NULL");
        }
        else if("N".equals(isAuth.toUpperCase())) {
            query.append("AND c.ugpgau_id IS NULL");
        }
        
        return query.toString();
    }
}