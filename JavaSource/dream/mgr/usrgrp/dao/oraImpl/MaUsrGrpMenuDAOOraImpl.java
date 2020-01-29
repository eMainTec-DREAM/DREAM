package dream.mgr.usrgrp.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
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
public class MaUsrGrpMenuDAOOraImpl extends BaseJdbcDaoSupportOra implements MaUsrGrpMenuDAO
{
    
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
        query.append("ORDER by ord_no                                           ");
         
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
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAUSRGRPMENU(					                ");
    	query.append("   comp_no,   usrgrp_id,     usrgrpmenu_id,               ");
    	query.append("   menu_id                                                ");
    	query.append(")VALUES(							                        ");
    	query.append("	 ?,		    ?,		       SQAUSRGRPMENU_ID.NEXTVAL,    ");
    	query.append("	 ?                                                      ");
    	query.append(")													        ");
    	
    	Object[] objects = new Object[] {
    			maUsrGrpMenuDTO.getCompNo(),
    			maUsrGrpMenuDTO.getUsrGrpId(),
//    			maUsrGrpMenuDTO.getUsrGrpMenuId(),
    			maUsrGrpMenuDTO.getMenuId()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
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
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TAUSRGRPMENU                            ");
        query.append("WHERE  comp_no        = ?                      ");
        query.append("  AND  usrgrp_id      = ?                      ");

        Object[] objects = new Object[] {   
                compNo,
                usrGrpId
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);                  
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
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TAUSRGRPMENU                            ");
        query.append("WHERE  comp_no        = ?                      ");
        query.append("  AND  usrgrpmenu_id  = ?                      ");
        
        Object[] objects = new Object[] {   
                maUsrGrpMenuDTO.getCompNo(),
                maUsrGrpMenuDTO.getUsrGrpMenuId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);                  
    }

    public List findMenuListForTree(String pMenuId, String usrGrpId, String compNo, User user)
    {
        QueryBuffer query = new QueryBuffer();   

        query.append("SELECT                                         ");
        query.append("      menu_id id,                              ");
        query.append("      description text,                        ");
        query.append("      NVL( (SELECT '1' FROM TAUSRGRPMENU y     ");
        query.append("            WHERE  y.menu_id = x.menu_id       ");
        query.append("              AND  y.comp_no ='"+compNo+"'     ");
        query.append("              AND  y.usrgrp_id ='"+usrGrpId+"'), '') checked ,   ");
        query.append("      '1' OPEN                                 ");
        query.append("FROM  TAMENU x                                 ");
        query.append("WHERE 1 = 1                                    ");
        query.append("AND DECODE (x.menu_id, '56' , '1', '0') IN (SELECT DECODE(a.menu_id, '56','1', '0')                         ");
        query.append("                FROM TAUSRGRPMENU a                   ");
        query.append("                WHERE a.comp_no ='"+user.getCompNo()+"'         ");
        query.append("                  AND a.usrgrp_id ='"+user.getUsrgrpId()+"')    ");

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