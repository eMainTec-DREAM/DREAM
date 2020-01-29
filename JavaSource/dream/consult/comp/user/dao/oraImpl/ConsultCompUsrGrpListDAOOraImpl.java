package dream.consult.comp.user.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.comp.user.dao.ConsultCompUsrGrpListDAO;
import dream.consult.comp.user.dto.ConsultCompUsrGrpCommonDTO;


/**
 * ���ѱ׷� - ��� dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="consultCompUsrGrpListDAOTarget"
 * @spring.txbn id="consultCompUsrGrpListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompUsrGrpListDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultCompUsrGrpListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompUsrGrpCommonDTO
     * @return List
     */
    public List findUsrGrpList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, User user)
    { 
        QueryBuffer query = new QueryBuffer(); 

        query.append("SELECT ''                                       seqNo,              ");
        query.append("       ''                                       isDelCheck,         ");
        query.append("       x.comp_no                                compNo,             ");
        query.append("       y.description                            compName,           ");
        query.append("       x.usrgrp_id                              usrGrpId,           "); 
        query.append("       x.usrgrp_no                              usrGrpNo,           "); 
        query.append("       x.group_name                             usrGrpName,         "); 
        query.append("       x.remark                                    		          "); 
        query.append("FROM   TAUSRGRP x  INNER JOIN TACOMP y ON x.comp_no = y.comp_no     ");
        query.append("WHERE  1=1                                         			      "); 
        query.append(this.getWhere(consultCompUsrGrpCommonDTO, user));
        query.getOrderByQuery("x.usrgrp_id DESC", consultCompUsrGrpCommonDTO.getOrderBy(), consultCompUsrGrpCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(consultCompUsrGrpCommonDTO.getIsLoadMaxCount(), consultCompUsrGrpCommonDTO.getFirstRow()));
    } 
    
    /**
     * Filter ����
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompUsrGrpCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        if (!"".equals(consultCompUsrGrpCommonDTO.getUsrGrpId()))
        {
            query.getAndQuery("x.usrgrp_id", consultCompUsrGrpCommonDTO.getUsrGrpId());
            return query.toString();
        }

        query.getCodeLikeQuery("x.comp_no", "(SELECT a.description FROM TACOMP a WHERE a.comp_no = x.comp_no)", consultCompUsrGrpCommonDTO.getFilterCompNo(), consultCompUsrGrpCommonDTO.getFilterCompDesc());
        
        query.getLikeQuery("x.group_name", consultCompUsrGrpCommonDTO.getFilterGroupName());
        return query.toString();
    }

    /**
     * ����
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompUsrGrpGridDTO
     * @return
     */
    public int deleteUsrGrp(String compNo, String usrGrpId)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TAUSRGRP                            ");
        query.append("WHERE  comp_no    = ?                      ");
        query.append("  AND  usrgrp_id  = ?                      ");

        Object[] objects = new Object[] {   
                compNo,
                usrGrpId
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);                  
    }
    
    /**
     * �޴� ���� ���� 
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultCompUsrGrpGridDTO
     * @return
     */
    public int deleteUsrGrpMenu(String compNo, String usrGrpId)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TAUSRGRPMENU                         ");
        query.append("WHERE  comp_no    = ?                      ");
        query.append("  AND  usrgrp_id  = ?                      ");
        
        Object[] objects = new Object[] {   
                compNo,
                usrGrpId
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);                  
    }

	@Override
	public String findTotalCount(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, User user)
	{
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT                         ");
        query.append("       COUNT(1)                ");
        query.append("FROM   TAUSRGRP x              ");
        query.append("WHERE  1=1                     "); 
        query.append(this.getWhere(consultCompUsrGrpCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
		return QueryBuffer.listToString(resultList);
	}
}