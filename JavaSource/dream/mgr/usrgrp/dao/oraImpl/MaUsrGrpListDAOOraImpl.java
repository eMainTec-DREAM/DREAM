package dream.mgr.usrgrp.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.usrgrp.dao.MaUsrGrpListDAO;
import dream.mgr.usrgrp.dto.MaUsrGrpCommonDTO;

/**
 * 권한그룹 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maUsrGrpListDAOTarget"
 * @spring.txbn id="maUsrGrpListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaUsrGrpListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaUsrGrpListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUsrGrpCommonDTO
     * @return List
     */
    public List findUsrGrpList(MaUsrGrpCommonDTO maUsrGrpCommonDTO, User user)
    { 
        QueryBuffer query = new QueryBuffer(); 

        query.append("SELECT ''                                       seqNo,      	");
        query.append("       ''                                       isDelCheck, 	");
        query.append("       x.comp_no                                compNo,     	");
        query.append("       x.usrgrp_id                              usrGrpId,   	"); 
        query.append("       x.usrgrp_no                              usrGrpNo,   	"); 
        query.append("       x.group_name                             groupName,  	"); 
        query.append("       x.remark                                    			"); 
        query.append("FROM   TAUSRGRP x                                  			");
        query.append("WHERE  1=1                                         			"); 
        query.append(this.getWhere(maUsrGrpCommonDTO, user));
//        query.append("ORDER by usrgrp_no                                 ");
        query.getOrderByQuery("x.usrgrp_id DESC", maUsrGrpCommonDTO.getOrderBy(), maUsrGrpCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maUsrGrpCommonDTO.getIsLoadMaxCount(), maUsrGrpCommonDTO.getFirstRow()));
    } 
    
    /**
     * Filter 조건
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUsrGrpCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaUsrGrpCommonDTO maUsrGrpCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        if (!"".equals(maUsrGrpCommonDTO.getUsrGrpId()))
        {
            query.getAndQuery("x.usrgrp_id", maUsrGrpCommonDTO.getUsrGrpId());
            return query.toString();
        }

        query.getLikeQuery("x.group_name", maUsrGrpCommonDTO.getFilterGroupName());
        
        query.getAndQuery("x.usrgrp_no", maUsrGrpCommonDTO.getUsrGrpNo());
        
        return query.toString();
    }

    /**
     * 삭제
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUsrGrpGridDTO
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
     * 메뉴 권한 삭제 
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUsrGrpGridDTO
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
	public String findTotalCount(MaUsrGrpCommonDTO maUsrGrpCommonDTO, User user)
	{
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT                         ");
        query.append("       COUNT(1)                ");
        query.append("FROM   TAUSRGRP x              ");
        query.append("WHERE  1=1                     "); 
        query.append(this.getWhere(maUsrGrpCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
		return QueryBuffer.listToString(resultList);
	}
}