package dream.consult.comp.user.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.comp.user.dao.ConsultCompUsrGrpDetailDAO;
import dream.consult.comp.user.dto.ConsultCompUsrGrpCommonDTO;
import dream.consult.comp.user.dto.ConsultCompUsrGrpDetailDTO;



/**
 * 권한그룹 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="consultCompUsrGrpDetailDAOTarget"
 * @spring.txbn id="consultCompUsrGrpDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompUsrGrpDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultCompUsrGrpDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0 
     * 
     * @param userGroup
     * @return
     */
    public ConsultCompUsrGrpDetailDTO findDetail(String compNo, String usrGrpId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT								                    ");
        query.append("       x.comp_no		                COMPNO,	            ");
        query.append("       y.description                  compDesc,           ");
        query.append("       x.usrgrp_id                    USRGRPID,           ");
        query.append("       x.usrgrp_no                    USRGRPNO,           ");
        query.append("       x.group_name                   GROUPNAME,          ");
        query.append("       x.remark			            REMARK		        ");
        query.append("FROM   TAUSRGRP x INNER JOIN TACOMP y ON x.comp_no = y.comp_no               ");
        query.append("WHERE  x.comp_no   = '"+compNo+"'	                        ");
        query.append("  AND  x.usrgrp_id = '"+usrGrpId+"'	                    ");
    
        ConsultCompUsrGrpDetailDTO consultCompUsrGrpDetailDTO = 
        		(ConsultCompUsrGrpDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new ConsultCompUsrGrpDetailDTO()));
        
        return consultCompUsrGrpDetailDTO;
    }
    
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompUsrGrpDetailDTO
     * @return
     */
    public int insertDetail(ConsultCompUsrGrpDetailDTO consultCompUsrGrpDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAUSRGRP(					                    ");
    	query.append("   comp_no		,usrgrp_id	,usrgrp_no                  ");
    	query.append("   ,group_name	,remark		,is_admin_group             ");
    	query.append(")VALUES(							                        ");
    	query.append("	 ?				,?			,?                          ");
    	query.append("	 ,?				,?			,?							");
    	query.append(")													        ");
    	
    	Object[] objects = new Object[] {
    			consultCompUsrGrpDetailDTO.getCompNo(),
    			consultCompUsrGrpDetailDTO.getUsrGrpId(),
    			consultCompUsrGrpDetailDTO.getUsrGrpNo(),
    			consultCompUsrGrpDetailDTO.getGroupName(),
    			consultCompUsrGrpDetailDTO.getRemark(),
    			"N"
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompUsrGrpDetailDTO
     * @return
     */
    public int updateDetail(ConsultCompUsrGrpDetailDTO consultCompUsrGrpDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAUSRGRP SET       ");
    	query.append("	     group_name	  = ?,	");
    	query.append("	     remark       = ?,  ");
    	query.append("       comp_no      = ?,  ");
        query.append("       usrgrp_no    = ?   ");
    	query.append("WHERE  usrgrp_id    = ?	");
    	
    	Object[] objects = new Object[] {
    			consultCompUsrGrpDetailDTO.getGroupName(),
    			consultCompUsrGrpDetailDTO.getRemark(),
    			consultCompUsrGrpDetailDTO.getCompNo(),
    			consultCompUsrGrpDetailDTO.getUsrGrpNo(),
    			consultCompUsrGrpDetailDTO.getUsrGrpId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * valid userGroup
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param consultCompUsrGrpDetailDTO
     * @return
     */
    public String validUsrGrpNo(ConsultCompUsrGrpDetailDTO consultCompUsrGrpDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*)                          ");
        query.append("FROM   TAUSRGRP x                        ");
        query.append("WHERE  x.comp_no    = '" + consultCompUsrGrpDetailDTO.getCompNo() + "'");
        query.append("  AND  UPPER(x.usrgrp_no) = UPPER('" + consultCompUsrGrpDetailDTO.getUsrGrpNo() + "')");
     
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    
    }
    
    public List findMenuList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer(); 

        query.append("SELECT CASE WHEN y.menu_id= NULL THEN 'N' ELSE 'Y' END isCheck,");
        query.append("       x.menu_id                                 menuId,  "); 
        query.append("       x.description                                      "); 
        query.append("FROM   TAMENU x LEFT OUTER JOIN TAUSRGRPMENU y                              ");
        query.append("ON  x.menu_id = y.menu_id                                 "); 
        query.append("  AND  y.comp_no    = '" + consultCompUsrGrpCommonDTO.getCompNo()  + "'"); 
        query.append("  AND   y.usrgrp_id = '" + consultCompUsrGrpCommonDTO.getUsrGrpId()+ "'"); 
        query.append("ORDER BY ISNULL(ord_no, '99999999')                                 ");
         
        return getJdbcTemplate().queryForList(query.toString());
        
    }

    @Override
    public void deleteUserGrpMenuLsit(String compNo, String usrGrpId)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void insertUserGrpMenu(
            ConsultCompUsrGrpDetailDTO consultCompUsrGrpDetailDTO)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List findMenuListForTree(String string, String usrGrpId,
            String compNo, User user)
    {
        // TODO Auto-generated method stub
        return null;
    }
}