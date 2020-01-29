package dream.mgr.usrgrp.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mgr.usrgrp.dao.MaUsrGrpMenuDAO;
import dream.mgr.usrgrp.dto.MaUsrGrpCommonDTO;
import dream.mgr.usrgrp.dto.MaUsrGrpMenuDTO;

/**
 * 권한그룹 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maUsrGrpMenuDAOTarget"
 * @spring.txbn id="maUsrGrpMenuDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaUsrGrpMenuDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaUsrGrpMenuDAO
{
    
    public List findMenuList(MaUsrGrpCommonDTO maUsrGrpCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer(); 

        query.append("SELECT CASE y.menu_id WHEN NULL THEN 'N' ELSE 'Y' END         ISCHECK, ");
        query.append("       x.menu_id                                 MENUID,  "); 
        query.append("       x.description                                      "); 
        query.append("FROM   TAMENU x RIGHT OUTER JOIN TAUSRGRPMENU y           ");
        query.append("	 ON  y.menu_id 	  = x.menu_id                          	"); 
        query.append("  AND  y.comp_no    = '" + maUsrGrpCommonDTO.getCompNo()  + "'"); 
        query.append("  AND  y.usrgrp_id = '" + maUsrGrpCommonDTO.getUsrGrpId()+ "'	"); 
        query.append("ORDER BY ISNULL(ord_no, '99999999')                           ");
         
        return getJdbcTemplate().queryForList(query.toString());
        
    }
    
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUsrGrpMenuDTO
     * @return
     */
    public int insertUserGrpMenu(MaUsrGrpMenuDTO maUsrGrpMenuDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAUSRGRPMENU(					                ");
    	query.append("   comp_no,   usrgrp_id,     usrgrpmenu_id,               ");
    	query.append("   menu_id                                                ");
    	query.append(")VALUES(							                        ");
    	query.append("	 ?,		    ?,		       NEXT VALUE FOR SQAUSRGRPMENU_ID,    ");
    	query.append("	 ?                                                      ");
    	query.append(")													        ");
    	
    	Object[] objects = new Object[] {
    			maUsrGrpMenuDTO.getCompNo(),
    			maUsrGrpMenuDTO.getUsrGrpId(),
//    			maUsrGrpMenuDTO.getUsrGrpMenuId(),
    			maUsrGrpMenuDTO.getMenuId()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
       
    /**
     * 삭제
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param usrGrpId
     * @return
     */
    public int deleteUserGrpMenuLsit(String compNo, String usrGrpId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE TAUSRGRPMENU                            ");
        query.append("WHERE  comp_no        = ?                      ");
        query.append("  AND  usrgrp_id      = ?                      ");

        Object[] objects = new Object[] {   
                compNo,
                usrGrpId
                };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));                  
    }
    
    /**
     * 삭제
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUsrGrpMenuDTO
     * @return
     */
    public int deleteUserGrpMenu(MaUsrGrpMenuDTO maUsrGrpMenuDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE TAUSRGRPMENU                            ");
        query.append("WHERE  comp_no        = ?                      ");
        query.append("  AND  usrgrpmenu_id  = ?                      ");
        
        Object[] objects = new Object[] {   
                maUsrGrpMenuDTO.getCompNo(),
                maUsrGrpMenuDTO.getUsrGrpMenuId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));                  
    }

    public List findMenuListForTree(String pMenuId, String usrGrpId, String compNo, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();   

        query.append("SELECT                                         ");
        query.append("      menu_id id,                              ");
        query.append("      description text,                        ");
        query.append("      ISNULL( (SELECT '1' FROM TAUSRGRPMENU y     ");
        query.append("            WHERE  y.menu_id = x.menu_id       ");
        query.append("              AND  y.comp_no ='"+compNo+"'     ");
        query.append("              AND  y.usrgrp_id ='"+usrGrpId+"'), '') checked ,   ");
        query.append("      '1' OPEN                                 ");
        query.append("FROM  TAMENU x                                 ");
        query.append("WHERE 1 = 1                                    ");
        query.append("AND (CASE x.menu_id WHEN '56' THEN '1' ELSE '0' END) IN (SELECT CASE a.menu_id WHEN '56' THEN '1' ELSE  '0' END	");
        query.append("                FROM TAUSRGRPMENU a                   		 	");
        query.append("                WHERE a.comp_no ='"+user.getCompNo()+"'         	");
        query.append("                  AND a.usrgrp_id ='"+user.getUsrgrpId()+"')    	");

        //      query.append("WHERE p_menu_id <> '56'                ");
        if("".equals(pMenuId)){
        query.append("AND p_menu_id = '0'               ");
        }else{
            query.append("AND p_menu_id ='"+pMenuId+"'  ");
        }
        query.append("ORDER BY x.ord_no                     ");
  
        return getJdbcTemplate().queryForList(query.toString());
        
    }

}