package dream.pers.priv.info.dao.oraImpl;

import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.pers.priv.info.dao.MaMyInfoDAO;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;

/**
 * ³»Á¤º¸ DAO
 * @author  kim21017
 * @version $Id: MaMyInfoDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maMyInfoDAOTarget"
 * @spring.txbn id="maMyInfoDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaMyInfoDAOOraImpl extends BaseJdbcDaoSupportOra implements MaMyInfoDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaMyInfoDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMyInfoCommonDTO
     * @return MaMyInfoCommonDTO
     */
	public MaMyInfoCommonDTO findDetail(MaMyInfoCommonDTO maMyInfoCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        String loginId = maMyInfoCommonDTO.getUserId();
        String compNo  = maMyInfoCommonDTO.getCompNo();
        String lang	   = maMyInfoCommonDTO.getUserLang();
        
        query.append("SELECT						                                                        			        ");
        query.append("		'"+loginId+"'			                                                        userId,		        ");
        query.append("		y.emp_no || '/' || y.emp_name                                                   userNo,		        ");
        query.append("		x.user_no				                                                        loginNo,	        ");
        query.append("		x.user_name				                                                        userName,	        ");
        query.append("		(SELECT group_name		                                                        			        ");
        query.append("		   FROM TAUSRGRP		                                                        			        ");
        query.append("		  WHERE usrgrp_id = x.usrgrp_id		                                                                ");
        query.append("		    AND comp_no = '"+compNo+"'		                                                                ");
        query.append("		)						                                                        userGroup,	        ");
        query.append("		x.reg_date				                                                        regDate,	        ");
        query.append("		x.login_date			                                                        loginDate,	        ");
        query.append("		x.m_phone				                                                        phone,		        ");
        query.append("		y.e_mail				                                                        email,		        ");
        query.append("      x.alarm_view_yn                                                                 alarmViewYn,        ");
        query.append("		x.init_menu_id			                                                        menuId,		        ");
        query.append("		(SELECT (SELECT key_name			                                                                ");
        query.append("					FROM TALANG				                                                                ");
        query.append("					WHERE key_type='MENU'	                                                                ");
        query.append("					  AND key_no =  tm.key_no                                                               ");
        query.append("					  AND lang='"+lang+"')                                                                  ");
        query.append("		   FROM TAMENU tm					                                                                ");
        query.append("		  WHERE menu_id = x.init_menu_id	                                                                ");
        query.append("		)						                                                        menuDesc,	        ");
        query.append("		x.secur_grade			                                                        securGradeId,	    ");
        query.append("     (select 										                                                        ");
        query.append("       nvl((select bb.key_name							                                                ");
        query.append("            from talang bb								                                                ");
        query.append("            where  bb.lang = '"+lang+"'					                                                ");
        query.append("            and aa.key_type = bb.key_type					                                                ");
        query.append("            and aa.key_no = bb.key_no), aa.description)	                                                ");
        query.append("       description										                                                ");
        query.append("      FROM TACDSYSD aa							                                                        ");
        query.append("        where aa.list_type = 'SECUR_GRADE'                                                                ");
        query.append("                  and aa.cdsysd_no = x.secur_grade                                                        ");
        query.append("      )                                                                            as securGradeDesc,     ");
        query.append("		x.scrn_font_size			                                                    scrnFontSizeId,	    ");
        query.append("     (select 										                                                        ");
        query.append("       nvl((select bb.key_name							                                                ");
        query.append("            from talang bb								                                                ");
        query.append("            where  bb.lang = '"+lang+"'					                                                ");
        query.append("            and aa.key_type = bb.key_type					                                                ");
        query.append("            and aa.key_no = bb.key_no), aa.description)	                                                ");
        query.append("       description										                                                ");
        query.append("      FROM TACDSYSD aa							                                                        ");
        query.append("        where aa.list_type = 'SCRN_FONT_SIZE'                                                             ");
        query.append("                  and aa.cdsysd_no = x.scrn_font_size                                                     ");
        query.append("      )                                                                            as scrnFontSizeDesc,   ");
        query.append("		x.shift_type			                                                        shiftTypeId,	    ");
        query.append("     (select 										                                                        ");
        query.append("       nvl((select bb.key_name							                                                ");
        query.append("            from talang bb								                                                ");
        query.append("            where  bb.lang = '"+lang+"'					                                                ");
        query.append("            and aa.key_type = bb.key_type					                                                ");
        query.append("            and aa.key_no = bb.key_no), aa.description)	                                                ");
        query.append("       description										                                                ");
        query.append("      FROM TACDSYSD aa							                                                        ");
        query.append("        where aa.list_type = 'SHIFT_TYPE'                                                                 ");
        query.append("                  and aa.cdsysd_no = x.shift_type                                                         ");
        query.append("      )                                                                            as shiftTypeDesc,      ");
        query.append("       y.wkctr_id	                             					                    wkCtrId,		    ");
        query.append("		 (SELECT description														                        ");
        query.append("		  FROM TAWKCTR																                        ");
        query.append("		  WHERE comp_no = y.comp_no													                        ");
        query.append("		  AND wkctr_id = y.wkctr_id) 			 					                    wkCtrDesc,		    ");
        query.append("       x.filter_wkctr_id	                             			                    filterWkCtrId,		");
        query.append("		 (SELECT description														                        ");
        query.append("		  FROM TAWKCTR																                        ");
        query.append("		  WHERE comp_no = x.comp_no													                        ");
        query.append("		  AND wkctr_id = x.filter_wkctr_id) 			 			                    filterWkCtrDesc,	");
        query.append("		y.plant			                                                                plant,	 			");
        query.append("       SFAPLANTTODESC(y.comp_no,y.plant)                                              plantDesc,		    ");
        query.append("		x.filter_plant			                                                        filterPlant,	 	");
        query.append("       SFAPLANTTODESC(x.comp_no,x.filter_plant)                                       filterPlantDesc,	");
        query.append("       x.eqctg_type  	                                                                eqCtgTypeId,        ");
        query.append("       SFACODE_TO_DESC(x.eqctg_type, 'EQCTG_TYPE', 'SYS', x.comp_no,'"+lang+"')       eqCtgTypeDesc,      ");
        query.append("       x.filter_dept_id                                                               filterDeptId,	    ");
        query.append("       SFAIDTODESC(x.filter_dept_id, '', 'DEPT', x.comp_no)                           filterDeptDesc,     ");
        query.append("		x.eqloc_id			                                                            eqLocId,			");
        query.append("     (select aa.full_desc from TAEQLOC aa      	                                                        ");
        query.append("      where aa.eqloc_id = x.eqloc_id      		                                                        ");
        query.append("      and aa.comp_no = x.comp_no 				                                                            ");
        query.append("      )                                                                            as eqLocDesc			");
        query.append("      ,x.menu_display                                                                 menuDisplay         ");
        query.append("      ,SFACODE_TO_DESC(x.menu_display, 'MENU_DISPLAY', 'SYS', x.comp_no,'"+lang+"')   menuDisplayDesc     ");
        query.append("      ,x.filter_emp_id                                                                filterEmpId         ");
        query.append("      ,(SELECT a.emp_name                                                                                 ");
        query.append("        FROM TAEMP a                                                                                      ");
        query.append("        WHERE a.comp_no = x.comp_no                                                                       ");
        query.append("        AND a.emp_id = x.filter_emp_id)                                               filterEmpDesc       ");
        query.append("      ,y.is_mail_rec                                                                  isMailRec           ");
        query.append("      ,x.filter_usage_dept_id                                                         filterUsageDeptId	");
        query.append("      ,SFAIDTODESC(x.filter_usage_dept_id, '', 'DEPT', x.comp_no)                     filterUsageDeptDesc	");
        query.append("		,x.bee_init_menu_id			                                                    beeInitMenuId		");
        query.append("		,(SELECT (SELECT key_name			                                                                ");
        query.append("					FROM TALANG				                                                                ");
        query.append("					WHERE key_type='MENU'	                                                                ");
        query.append("					  AND key_no =  tm.key_no                                                               ");
        query.append("					  AND lang='"+lang+"')                                                                  ");
        query.append("		   FROM TAMENU tm					                                                                ");
        query.append("		  WHERE menu_id = x.bee_init_menu_id	                                                            ");
        query.append("		)						                                                        beeInitMenuDesc	    ");
        query.append("FROM   TAUSER x									                                                        ");
        query.append("     ,taemp y                                                                                             ");
        query.append("WHERE  x.user_id = '"+loginId+"'					                                                        ");
        query.append("  AND  x.comp_no = '"+compNo+"'					                                                        ");
        query.append("    and x.comp_no = y.comp_no                                                                             ");
        query.append("    and x.emp_id = y.emp_id                                                                               ");
        
        MaMyInfoCommonDTO maMyInfoCommonDTO1 = 
        		(MaMyInfoCommonDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaMyInfoCommonDTO()));
        
        return maMyInfoCommonDTO1;
    }
	
	/**
     * detail update
     * @author kim21017
     * @version $Id: MaMyInfoDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBtnMngDetailDTO
     * @return
     */
    public int updateDetail(MaMyInfoCommonDTO maMyInfoCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAUSER SET		    	");
    	query.append("	 m_phone		    = ?		");
    	query.append("  ,menu_display    	= ?    	");
    	query.append("	,init_menu_id    	= ?		");
    	query.append("	,secur_grade     	= ?		");
    	query.append("	,shift_type      	= ?		");
    	query.append("  ,alarm_view_yn   	= ?    	");
    	query.append("	,scrn_font_size  	= ?		");
    	query.append("	,eqctg_type		 	= ?    	");
    	query.append("	,filter_dept_id	 	= ?    	");
    	query.append("	,eqloc_id        	= ?		");
    	query.append("	,filter_emp_id	 	= ?		");
    	query.append("	,filter_wkctr_id 	= ?		");
    	query.append("	,filter_usage_dept_id = ?	");
    	query.append("  ,filter_plant       = ?		");
    	query.append("	,bee_init_menu_id   = ?		");
    	query.append("WHERE user_id     = ?			");
    	query.append("  AND comp_no     = ?			");
    	
    	Object[] objects = new Object[] {
    			maMyInfoCommonDTO.getPhone(),
    			maMyInfoCommonDTO.getMenuDisplay(),
    			maMyInfoCommonDTO.getMenuId(),
    			maMyInfoCommonDTO.getSecurGradeId(),
    			maMyInfoCommonDTO.getShiftTypeId(),
    			maMyInfoCommonDTO.getAlarmViewYn(),
    			maMyInfoCommonDTO.getScrnFontSizeId(),
    			maMyInfoCommonDTO.getEqCtgTypeId(),
    			maMyInfoCommonDTO.getFilterDeptId(),
    			maMyInfoCommonDTO.getEqLocId(),
    			maMyInfoCommonDTO.getFilterEmpId(),
    			maMyInfoCommonDTO.getFilterWkCtrId(),
    			maMyInfoCommonDTO.getFilterUsageDeptId(),
    			maMyInfoCommonDTO.getFilterPlant(),
    			maMyInfoCommonDTO.getBeeInitMenuId(),
    			maMyInfoCommonDTO.getUserId(),
    			maMyInfoCommonDTO.getCompNo()
    	};
    	
    	getJdbcTemplate().update(query.toString(), objects);
    	
    	query = new QueryBuffer();
    	query.append("update taemp set                                 ");
    	//query.append("        plant = ?,                               ");
    	//query.append("        wkctr_id = ?,                            ");
    	query.append("        e_mail = ?,                              ");
    	query.append("        is_mail_rec = ?                          ");
    	query.append("where comp_no =  ?                               ");
    	query.append("    and emp_id = ( select emp_id                 ");
    	query.append("                           from tauser           ");
    	query.append("                           where comp_no = ?     ");
    	query.append("                           and user_id = ?       ");
    	query.append("                         )                       ");

    	
    	objects = new Object[] {
    			//maMyInfoCommonDTO.getPlant(),
    			//maMyInfoCommonDTO.getWkCtrId(),
    			maMyInfoCommonDTO.getEmail(),
    			maMyInfoCommonDTO.getIsMailRec(),
    			maMyInfoCommonDTO.getCompNo(),
    			maMyInfoCommonDTO.getCompNo(),
    			maMyInfoCommonDTO.getUserId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}