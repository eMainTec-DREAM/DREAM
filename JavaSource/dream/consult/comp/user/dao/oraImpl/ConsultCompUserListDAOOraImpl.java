package dream.consult.comp.user.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.consult.comp.user.dao.ConsultCompUserListDAO;
import dream.consult.comp.user.dto.ConsultCompUserCommonDTO;

/**
 * CompUser Page - List DAO implements
 * @author youngjoo38
 * @version $Id: ConsultCompUserListDAOOraImpl.java,v 1.0 2017/08/10 09:12:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="consultCompUserListDAOTarget"
 * @spring.txbn id="consultCompUserListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompUserListDAOOraImpl  extends BaseJdbcDaoSupportOra implements ConsultCompUserListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: consultCompUserListDAO.java,v 1.0 2017/08/10 09:14:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param consultCompUserCommonDTO
     * @return List
     */
    public List findCompUserList(ConsultCompUserCommonDTO consultCompUserCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                              ");
        query.append("      ''                                                              AS seqNo      ");
        query.append("      ,''                                                             AS isDelCheck ");
        query.append("      ,x.user_id                                                      AS userId     ");
        query.append("      ,x.comp_no                                                      AS compNo     ");
        query.append("      ,(SELECT a.description                                                        ");
        query.append("        FROM TACOMP a                                                               ");
        query.append("        WHERE a.comp_no = x.comp_no)                                  AS compDesc   ");
        query.append("      ,x.usrgrp_id                                                    AS usrGrpId   ");
        query.append("      ,(SELECT a.GROUP_NAME                                                         ");
        query.append("        FROM TAUSRGRP a                                                             ");
        query.append("        WHERE a.comp_no = x.comp_no                                                 ");
        query.append("            AND a.usrgrp_id = x.usrgrp_id)                            AS usrGrpDesc ");
        query.append("      ,X.user_no                                                      AS userNo     ");
        query.append("      ,X.user_name                                                    AS userName   ");
        query.append("      ,y.dept_id                                                      AS deptId     ");
        query.append("      ,(SELECT a.description                                                        ");
        query.append("        FROM TADEPT a                                                               ");
        query.append("        WHERE a.comp_no = x.comp_no                                                 ");
        query.append("            AND a.dept_id = y.dept_id)                                AS deptDesc   ");
        query.append("      ,y.emp_id                                                       AS empId      ");
        query.append("      ,y.emp_name                                                     AS empName    ");
        query.append("      ,x.is_use                                                       AS isUseId    ");
        query.append("      ,SFACODE_TO_DESC( x.is_use, 'IS_USE','SYS',x.comp_no, '"+user.getLangId()+"')     AS isUseDesc  ");
        query.append("FROM TAUSER x INNER JOIN TAEMP y                                                    ");
        query.append("ON  x.comp_no = y.comp_no                                                           ");
        query.append("AND x.emp_id = y.emp_id                                                             ");
        query.append("WHERE  1=1                                                                          ");
        query.append(this.getWhere(consultCompUserCommonDTO, user));
        query.getOrderByQuery("x.user_name", consultCompUserCommonDTO.getOrderBy(), consultCompUserCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(consultCompUserCommonDTO.getIsLoadMaxCount(), consultCompUserCommonDTO.getFirstRow()));

    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompUserCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(ConsultCompUserCommonDTO consultCompUserCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        if(!"".equals(consultCompUserCommonDTO.getUserId())){
            query.getAndQuery("x.user_id", consultCompUserCommonDTO.getUserId());
            return query.toString();
        }
        
        //회사코드
        if(!"".equals(consultCompUserCommonDTO.getFilterCompNo()))
        {
            query.getAndQuery("x.comp_no", consultCompUserCommonDTO.getFilterCompNo());
        }
        else if(!"".equals(consultCompUserCommonDTO.getFilterCompDesc()))
        {
            query.append("AND x.comp_no IN (SELECT a.comp_no                ");
            query.append("                    FROM   TACOMP a                   ");
            query.append("                    WHERE  1=1                        ");
            query.getLikeQuery("a.description", consultCompUserCommonDTO.getFilterCompDesc());
            query.append("                    )                                 ");
        }
        
        
        // 권한 
        if(!"".equals(consultCompUserCommonDTO.getFilterUsrGrpId()))
        {
            query.getAndQuery("x.usrgrp_id", consultCompUserCommonDTO.getFilterUsrGrpId());
        }
        else if(!"".equals(consultCompUserCommonDTO.getFilterUsrGrpDesc()))
        {
            query.append("AND x.usrgrp_id IN (SELECT a.usrgrp_id                ");
            query.append("                    FROM   TAUSRGRP a                 ");
            query.append("                    WHERE  1=1                        ");
            query.getLikeQuery("a.group_name", consultCompUserCommonDTO.getFilterUsrGrpDesc());
            query.append("                    )                                 ");
        }
        
        //권한 그룹명(ID)
//        query.getEqLocLevelQuery("x.usrgrp_id", consultCompUserCommonDTO.getFilterUsrGrpId(), consultCompUserCommonDTO.getFilterUsrGrpDesc(), user.getCompNo());
        
        //계정ID
        query.getLikeQuery("x.user_no", consultCompUserCommonDTO.getFilterUserNo());
        //계정명
        query.getLikeQuery("x.user_name", consultCompUserCommonDTO.getFilterUserName());
        //부서명
        query.getDeptLevelQuery("y.dept_id", consultCompUserCommonDTO.getFilterDeptId(), consultCompUserCommonDTO.getFilterDeptDesc(), consultCompUserCommonDTO.getFilterCompNo());
        //사용여부
        query.getSysCdQuery("x.is_use", consultCompUserCommonDTO.getFilterIsUseId(), consultCompUserCommonDTO.getFilterIsUseDesc(), "IS_USE", consultCompUserCommonDTO.getFilterCompNo(), user.getLangId());

        return query.toString();
    }
    
    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: consultCompUserListDAO.java,v 1.0 2017/08/10 09:14:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param consultCompUserCommonDTO
     * @return
     * @throws Exception
     */
    public int deleteCompUserList(String id, User user, ConsultCompUserCommonDTO consultCompUserCommonDTO) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAUSER        ");
        query.append("WHERE 1 = 1               ");
        query.append("  AND user_id = ?         ");

        
        Object[] objects = new Object[] {   
                id
         };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    public String findTotalCount(
            ConsultCompUserCommonDTO consultCompUserCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM TAUSER x INNER JOIN TAEMP y                          ");
        query.append("ON  x.comp_no = y.comp_no                                 ");
        query.append("AND x.emp_id = y.emp_id                                   ");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(consultCompUserCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
    
    public int resetCompUserPw(String id, String userNo, String compNo) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("UPDATE TAUSER SET                     ");
        query.append("  password           = ?             ");
        query.append("WHERE  comp_no        = ?             ");
        query.append("  AND  user_id        = ?             ");
        
        Object[] objects = new Object[] {   
                CommonUtil.aesEncodeString(userNo)
                ,compNo
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
}
