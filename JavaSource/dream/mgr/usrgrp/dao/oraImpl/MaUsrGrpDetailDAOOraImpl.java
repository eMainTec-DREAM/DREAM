package dream.mgr.usrgrp.dao.oraImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.usrgrp.dao.MaUsrGrpDetailDAO;
import dream.mgr.usrgrp.dto.MaUsrGrpCommonDTO;
import dream.mgr.usrgrp.dto.MaUsrGrpDetailDTO;

/**
 * 권한그룹 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maUsrGrpDetailDAOTarget"
 * @spring.txbn id="maUsrGrpDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaUsrGrpDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaUsrGrpDetailDAO
{
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUsrGrpDetailDTO
     * @return
     */
    public int insertDetail(MaUsrGrpDetailDTO maUsrGrpDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAUSRGRP(					                    ");
    	query.append("   comp_no		,usrgrp_id	,usrgrp_no                  ");
    	query.append("   ,group_name	,remark		,is_admin_group             ");
    	query.append(")VALUES(							                        ");
    	query.append("	 ?				,?			,?                          ");
    	query.append("	 ,?				,?			,?							");
    	query.append(")													        ");
    	
    	Object[] objects = new Object[] {
    			maUsrGrpDetailDTO.getCompNo(),
    			maUsrGrpDetailDTO.getUsrGrpId(),
    			maUsrGrpDetailDTO.getUsrGrpNo(),
    			maUsrGrpDetailDTO.getGroupName(),
    			maUsrGrpDetailDTO.getRemark(),
    			"N"
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUsrGrpDetailDTO
     * @return
     */
    public int updateDetail(MaUsrGrpDetailDTO maUsrGrpDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAUSRGRP SET       ");
    	query.append("	     group_name	  = ?,	");
    	query.append("	     remark       = ?   ");
    	query.append("WHERE  comp_no      = ?	");
    	query.append("  AND  usrgrp_id    = ?	");
    	
    	Object[] objects = new Object[] {
    			maUsrGrpDetailDTO.getGroupName(),
    			maUsrGrpDetailDTO.getRemark(),
    			maUsrGrpDetailDTO.getCompNo(),
    			maUsrGrpDetailDTO.getUsrGrpId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * valid userGroup
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maUsrGrpDetailDTO
     * @return
     */
    public String validUsrGrpNo(MaUsrGrpDetailDTO maUsrGrpDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*)                          ");
        query.append("FROM   TAUSRGRP x                        ");
        query.append("WHERE  x.comp_no    = '" + maUsrGrpDetailDTO.getCompNo() + "'");
        query.append("  AND  UPPER(x.usrgrp_no) = UPPER('" + maUsrGrpDetailDTO.getUsrGrpNo() + "')");
     
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    
    }
    
    public List findMenuList(MaUsrGrpCommonDTO maUsrGrpCommonDTO)
    {
        QueryBuffer query = new QueryBuffer(); 

        query.append("SELECT DECODE(y.menu_id, NULL, 'N', 'Y')         isCheck, ");
        query.append("       x.menu_id                                 menuId,  "); 
        query.append("       x.description                                      "); 
        query.append("FROM   TAMENU x, TAUSRGRPMENU y                           ");
        query.append("WHERE  x.menu_id = y.menu_id (+)                          "); 
        query.append("  AND  y.comp_no(+)    = '" + maUsrGrpCommonDTO.getCompNo()  + "'"); 
        query.append("  AND   y.usrgrp_id(+) = '" + maUsrGrpCommonDTO.getUsrGrpId()+ "'"); 
        query.getAndQuery("x.menu_no", maUsrGrpCommonDTO.getMenuNo());
        query.append("ORDER by ord_no                                 ");
         
        return getJdbcTemplate().queryForList(query.toString());
        
    }
}