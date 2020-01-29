package dream.consult.comp.user.dao.sqlImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.comp.user.dao.ConsultCompUsrGrpAuthListDAO;
import dream.consult.comp.user.dto.ConsultCompUsrGrpCommonDTO;


/**
 * 권한그룹 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="consultCompUsrGrpAuthListDAOTarget"
 * @spring.txbn id="consultCompUsrGrpAuthListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompUsrGrpAuthListDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultCompUsrGrpAuthListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompUsrGrpCommonDTO
     * @param admin 
     * @return List
     */
    public List findUsrGrpAuthList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, boolean admin)
    { 
        QuerySqlBuffer query = new QuerySqlBuffer(); 

        query.append("SELECT a.*, MIN(a.lvl) OVER() AS MINLVL FROM (        ");
        query.append("SELECT                                                ");
        query.append("       x.menu_id ID,                                    ");
        query.append("      x.ord_no SEQNO,                                      ");
        query.append("      'MENU' TYPEDESC,                                ");
        query.append("      (SELECT a.key_name                              ");
        query.append("       FROM   TALANG a                                ");
        query.append("       WHERE  a.key_no = x.key_no                     ");
        query.append("         AND  a.key_type ='MENU'                      ");
        query.append("         AND  a.lang = '"+consultCompUsrGrpCommonDTO.getUserLang()+"') MENUDESC,             ");
        query.append("      ISNULL( (SELECT '1' FROM TAUSRGRPMENU y            ");
        query.append("            WHERE  y.menu_id = x.menu_id              ");
        query.append("              AND  y.comp_no ='"+consultCompUsrGrpCommonDTO.getCompNo()+"'                  ");
        query.append("              AND  y.usrgrp_id ='"+consultCompUsrGrpCommonDTO.getUsrGrpId()+"'), '') AUTHCHECK , ");
        query.append("      'MENU' TYPE,                                    ");
        query.append("      x.menu_id KEYNO,                                ");
        query.append("      x.menu_id CODE,                                 ");
        query.append("      x.p_menu_id PCODE,                              ");
        query.append("      y.page_id PAGEID                                ");
//        query.append("      MIN(LEVEL) OVER() AS minLvl                     ");
        query.append("      ,z.lvl LVL                                      ");
        query.append("      ,x.page_id EXTCODE                              ");
        query.append("FROM  TAMENU x LEFT OUTER JOIN TAPAGE y                              ");
        query.append("ON    x.page_id = y.page_id , (SELECT * FROM dbo.SFAMENU_ALL('0'))z                                ");
        query.append("  WHERE x.menu_id=z.menu_id                                ");
        query.append("  AND x.is_use = 'Y'                                  ");

        if("ANDROID".equals(consultCompUsrGrpCommonDTO.getFilterServiceType())){
        	query.append("AND x.service_type IN ('ANDROID','ANT','BEE','CRICKET') ");
        }else{
        	query.getStringEqualQuery("x.service_type", consultCompUsrGrpCommonDTO.getFilterServiceType());
        }
        
        if(!admin) query.append(" AND x.is_system = 'N'                     ");
        query.append(") a                           ");
        query.append("ORDER BY a.LVL asc, SEQNO asc                          ");

         
        return getJdbcTemplate().queryForList(query.toString());
    } 
    
    public List findUsrGrpAuthPageList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO)
    { 
        QuerySqlBuffer query = new QuerySqlBuffer(); 

        query.append("SELECT                                           ");
        query.append("       ROW_NUMBER()                              ");
        query.append("       OVER(ORDER BY x.page_id    ) ID,          ");
        query.append("      '1' SEQNO,                                 ");
        query.append("      'PAGE' TYPEDESC,                           ");
        query.append("      x.description +' - '+(SELECT a.key_name                         ");
        query.append("       FROM   TALANG a                           ");
        query.append("       WHERE  a.key_no = y.key_no                ");
        query.append("         AND  a.key_type = y.key_type            ");
        query.append("         AND  a.lang = '"+consultCompUsrGrpCommonDTO.getUserLang()+"') MENUDESC,             ");
//        query.append("       x.description,                            ");
        query.append("       ISNULL((SELECT '1' FROM TAUGPGPGAU b         ");
        query.append("        WHERE b.pgpage_id = y.pgpage_id          ");
        query.append("            AND b.usrgrp_id = '"+consultCompUsrGrpCommonDTO.getUsrGrpId()+"'),'') AUTHCHECK, ");
        query.append("       'TAB' TYPE,                               ");
        query.append("       y.pgpage_id KEYNO,                        ");
        query.append("       x.page_id CODE,                           ");
        query.append("       y.page_id PCODE,                          ");
        query.append("       x.page_id PAGEID,                         ");
        query.append("       '1' AS MINLVL,                            ");
        query.append("       '1' AS LVL,                               ");
        query.append("       x.page_id EXTCODE                         ");
        query.append("FROM   TAPAGE x LEFT  JOIN TAPGPAGE y       ");
        query.append("ON  x.page_id = y.c_page_id                ");
        query.append("  AND  y.is_use = 'Y'                            ");
        query.append("  AND  x.is_chkauth = 'Y'                        ");
        
        return getJdbcTemplate().queryForList(query.toString());
    } 
    
    public List findUsrGrpAuthButtonList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO)
    { 
        QuerySqlBuffer query = new QuerySqlBuffer(); 

        query.append("SELECT                                            ");
        query.append("       a.page_id AS ID,                           ");
        query.append("       '1' AS SEQNO,                              ");
        query.append("       'BUTTON' TYPEDESC,                         ");
        query.append("      (SELECT a.key_name                              ");
        query.append("       FROM   TALANG a                                ");
        query.append("       WHERE  a.key_no = x.key_no                     ");
        query.append("         AND  a.key_type =x.key_type                  ");
        query.append("         AND  a.lang = '"+consultCompUsrGrpCommonDTO.getUserLang()+"') BUTTONDESC,             ");
        
//        query.append("       (SELECT b.description                      ");
//        query.append("        FROM TABUTTON b                           ");
//        query.append("        WHERE b.button_id = x.button_id           ");
//        query.append("        ) AS description,                         ");
        query.append("       ISNULL((SELECT '1' FROM TAUGPGBTNAU b         ");
        query.append("            WHERE   b.pgbtn_id = x.pgbtn_id       ");
        query.append("              AND   b.usrgrp_id = '"+consultCompUsrGrpCommonDTO.getUsrGrpId()+"'),'') AUTHCHECK,");
        query.append("       'BUTTON' TYPE,                             ");
        query.append("       x.pgbtn_id KEYNO,                          ");
        query.append("       '' CODE,                                   ");
        query.append("       CONVERT(varchar,x.page_id)  PCODE,         ");
        query.append("       a.page_id PAGEID,                          ");
        query.append("       '1' AS MINLVL,                             ");
        query.append("       '1' AS LVL                                 ");
        query.append("FROM   TAPAGE a ,TAPGBTN x                        ");
        query.append("WHERE  a.page_id = x.page_id                      ");
        query.append("  AND  x.is_use = 'Y'                             ");
        query.append("  AND  x.is_chkauth = 'Y'                         ");
//        query.append("  AND  1=2                         ");

        return getJdbcTemplate().queryForList(query.toString());
    } 
    
    /**
     * Filter 조건
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompUsrGrpCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("x.comp_no", consultCompUsrGrpCommonDTO.getFilterCompNo());
        query.getLikeQuery("x.group_name", consultCompUsrGrpCommonDTO.getFilterGroupName());
        return query.toString();
    }


    public int insertMenuAuth(String menu_id, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TAUSRGRPMENU                 ");
        query.append("(                                        ");
        query.append("    usrgrpmenu_id,   comp_no,            ");
        query.append("    usrgrp_id,       menu_id             ");
        query.append(")VALUES                                  ");
        query.append("(                                        ");
        query.append("    NEXT VALUE FOR SQAUSRGRPMENU_ID, ?,         ");
        query.append("    ?,               ?                   ");
        query.append(")                                        ");

        
        Object[] objects = new Object[] {
                consultCompUsrGrpCommonDTO.getCompNo(),
                consultCompUsrGrpCommonDTO.getUsrGrpId(),
                menu_id
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
        
    }

    public int deleteMenuAuth(String menu_id, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAUSRGRPMENU                                       ");
        query.append("WHERE  usrgrp_id   = '"+consultCompUsrGrpCommonDTO.getUsrGrpId()+"'     ");
        query.append("  AND  menu_id     = '"+menu_id+"'                             ");
        
        return this.getJdbcTemplate().update(query.toString());
    }

    public int insertTabAuth(String pgPageId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TAUGPGPGAU                   ");
        query.append("(                                        ");
        query.append("    ugpgpgau_id,                         ");
        query.append("    usrgrp_id,       pgpage_id           ");
        query.append(")VALUES                                  ");
        query.append("(                                        ");
        query.append("    NEXT VALUE FOR SQAUGPGPGAU_ID,              ");
        query.append("    ?,               ?                   ");
        query.append(")                                        ");

        
        Object[] objects = new Object[] {
                consultCompUsrGrpCommonDTO.getUsrGrpId(),
                pgPageId
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }

    public int deleteTabAuth(String pgPageId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAUGPGPGAU                                       ");
        query.append("WHERE  usrgrp_id   = '"+consultCompUsrGrpCommonDTO.getUsrGrpId()+"'   ");
        query.append("  AND  pgpage_id     = '"+pgPageId+"'                        ");
        
        return this.getJdbcTemplate().update(query.toString());
    }
    
    public int chkTabAuth(String keyNo, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(1)                                           ");
        query.append("FROM   TAUGPGPGAU									   		");
        query.append("WHERE  1=1												");
        query.getAndQuery("usrgrp_id", consultCompUsrGrpCommonDTO.getUsrGrpId());
        query.getAndQuery("pgpage_id", keyNo);
        
        return (int)getJdbcTemplate().queryForObject(query.toString(), Integer.class);
	}

    public int insertBtnAuth(String pgbtn_id, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TAUGPGBTNAU                  ");
        query.append("(                                        ");
        query.append("    ugpgbtnau_id,                        ");
        query.append("    usrgrp_id,       pgbtn_id            ");
        query.append(")VALUES                                  ");
        query.append("(                                        ");
        query.append("    NEXT VALUE FOR SQAUGPGBTN_ID,               ");
        query.append("    ?,               ?                   ");
        query.append(")                                        ");

        
        Object[] objects = new Object[] {
                consultCompUsrGrpCommonDTO.getUsrGrpId(),
                pgbtn_id
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }

    public int deleteBtnAuth(String pgbtn_id, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAUGPGBTNAU                                       ");
        query.append("WHERE  usrgrp_id   = '"+consultCompUsrGrpCommonDTO.getUsrGrpId()+"'    ");
        query.append("  AND  pgbtn_id    = '"+pgbtn_id+"'                           ");
        
        return this.getJdbcTemplate().update(query.toString());
    }
    
    public int chkBtnAuth(String keyNo, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(1)                                           ");
        query.append("FROM   TAUGPGBTNAU									   	");
        query.append("WHERE  1=1												");
        query.getAndQuery("usrgrp_id", consultCompUsrGrpCommonDTO.getUsrGrpId());
        query.getAndQuery("pgbtn_id", keyNo);
        
        return (int)getJdbcTemplate().queryForObject(query.toString(), Integer.class);
	}

    public List findNoMenuUsrGrpAuthPageList(  ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer(); 

        query.append("SELECT                                            ");
        query.append("       ROW_NUMBER()                               ");
        query.append("       OVER(ORDER BY x.page_id    ) ID,           ");
        query.append("      '1' SEQNO,                                  ");
        query.append("      'PAGE' TYPEDESC,                            ");
        query.append("       x.description,                             ");
        query.append("       ISNULL((SELECT '1' FROM TAUGPGAU b            ");
        query.append("        WHERE b.page_id = x.page_id               ");
        query.append("            AND b.usrgrp_id ='"+consultCompUsrGrpCommonDTO.getUsrGrpId()+"'),'') AUTHCHECK,");
        query.append("       'PAGE' TYPE,                               ");
        query.append("       x.page_id KEYNO,                           ");
        query.append("       x.page_id CODE,                            ");
        query.append("       '' PCODE,                                  ");
        query.append("       x.page_id PAGEID,                          ");
        query.append("       '1' AS MINLVL,                             ");
        query.append("       '1' AS LVL,                                ");
        query.append("       x.page_id EXTCODE                          ");
        query.append("FROM   TAPAGE x                                   ");
        query.append("WHERE NOT EXISTS (SELECT 1                        ");
        query.append("                  FROM   TAPGPAGE a               ");
        query.append("                  WHERE  a.c_page_id = x.page_id  ");
        query.append("                  UNION ALL                       ");
        query.append("                  SELECT 1                        ");
        query.append("                  FROM   TAMENU b                 ");
        query.append("                  WHERE  b.page_id = x.page_id    ");
        query.append("                 )                                ");
        query.append("  AND x.is_chkauth = 'Y'                          ");
        query.append("  AND x.is_use = 'Y'                              ");


        return getJdbcTemplate().queryForList(query.toString());
    }

    public int insertPageAuth(String pageId,ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TAUGPGAU                     ");
        query.append("(                                        ");
        query.append("    ugpgau_id,                           ");
        query.append("    usrgrp_id,       page_id             ");
        query.append(")VALUES                                  ");
        query.append("(                                        ");
        query.append("    NEXT VALUE FOR SQAUGPGAU_ID,                ");
        query.append("    ?,               ?                   ");
        query.append(")                                        ");

        
        Object[] objects = new Object[] {
                consultCompUsrGrpCommonDTO.getUsrGrpId(),
                pageId
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public int deletePageAuth(String pageId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAUGPGAU                                       ");
        query.append("WHERE  usrgrp_id   = '"+consultCompUsrGrpCommonDTO.getUsrGrpId()+"' ");
        query.append("  AND  page_id    = '"+pageId+"'                           ");
        
        return this.getJdbcTemplate().update(query.toString());
    }
    
	public int chkPageAuth(String pageId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(1)                                           ");
        query.append("FROM   TAUGPGAU									    	");
        query.append("WHERE  1=1												");
        query.getAndQuery("usrgrp_id", consultCompUsrGrpCommonDTO.getUsrGrpId());
        query.getAndQuery("page_id", pageId);
        
        return (int)getJdbcTemplate().queryForObject(query.toString(), Integer.class);
	}

	public int chkMenuAuth(String keyNo, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(1)                                           ");
        query.append("FROM   TAUSRGRPMENU									    ");
        query.append("WHERE  1=1												");
        query.getAndQuery("comp_no",  consultCompUsrGrpCommonDTO.getCompNo());
        query.getAndQuery("usrgrp_id", consultCompUsrGrpCommonDTO.getUsrGrpId());
        query.getAndQuery("menu_id", keyNo);
        
        return (int)getJdbcTemplate().queryForObject(query.toString(), Integer.class);
	}

	@Override
	public int deleteAllBtnAuth(String menuId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("DELETE z FROM TAUGPGBTNAU z											");
		query.append("WHERE z.pgbtn_id IN (SELECT a.pgbtn_id								");
		query.append("                     FROM   TAPGBTN a									");
		query.append("                     WHERE  a.page_id IN (SELECT DISTINCT(x.page_id) FROM dbo.SFAPGPAGEINMENU_ALL(?) AS x)	");
		query.append("                    )						                			");
		query.append("AND z.usrgrp_id = ?                        	                		");
		query.append("AND z.comp_no = ?                        	                			");

		Object[] objects = new Object[] {
				menuId
				,consultCompUsrGrpCommonDTO.getUsrGrpId()
				,consultCompUsrGrpCommonDTO.getCompNo()
		};
		
        return this.getJdbcTemplate().update(query.toString(),objects);
	}

	@Override
	public int deleteAllTabAuth(String menuId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("DELETE z FROM TAUGPGPGAU z											");
		query.append("WHERE pgpage_id IN (SELECT a.pgpage_id								");
		query.append("                    FROM   TAPGPAGE a									");
		query.append("                    WHERE  a.c_page_id  IN (SELECT DISTINCT(x.page_id) FROM dbo.SFAPGPAGEINMENU_ALL(?) AS x)	");
		query.append("                     )						                		");
		query.append("AND z.usrgrp_id = ?                        	                		");
		query.append("AND z.comp_no = ?                        	                			");

		Object[] objects = new Object[] {
				menuId
				,consultCompUsrGrpCommonDTO.getUsrGrpId()
				,consultCompUsrGrpCommonDTO.getCompNo()
		};
		
        return this.getJdbcTemplate().update(query.toString(),objects);
	}

	@Override
	public int deleteAllPageAuth(String menuId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("DELETE z FROM TAUGPGAU AS z                       ");
        query.append("WHERE z.page_id  = (SELECT x.page_id              ");
        query.append("                    FROM   TAMENU x               ");
        query.append("                    WHERE x.page_id IS NOT NULL   ");
        query.append("                    AND x.menu_id = ?             ");
        query.append("                    )                             ");
        query.append("AND z.usrgrp_id = ?                               ");
        query.append("AND z.comp_no = ?                                 ");


		Object[] objects = new Object[] {
		        menuId
				,consultCompUsrGrpCommonDTO.getUsrGrpId()
				,consultCompUsrGrpCommonDTO.getCompNo()
		};
		
        return this.getJdbcTemplate().update(query.toString(),objects);
	}

	@Override
	public int delteAllMenuAuth(String menuId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("DELETE z FROM TAUSRGRPMENU AS z                           ");
        query.append("WHERE z.menu_id  = ?                                      ");
        query.append("AND z.usrgrp_id = ?                                       ");
        query.append("AND z.comp_no = ?                                         ");

		Object[] objects = new Object[] {
				menuId
				,consultCompUsrGrpCommonDTO.getUsrGrpId()
				,consultCompUsrGrpCommonDTO.getCompNo()
		};
		
        return this.getJdbcTemplate().update(query.toString(),objects);
	}

	@Override
	public void grantAllBtnAuth(String menuId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("INSERT INTO TAUGPGBTNAU(ugpgbtnau_id, usrgrp_id, pgbtn_id, comp_no)	            ");
		query.append("SELECT NEXT VALUE FOR SQAUGPGBTN_ID, ?, a.pgbtn_id, ?					                ");
		query.append("FROM   TAPGBTN a							                    	                ");
		query.append("WHERE  a.page_id IN (SELECT DISTINCT(x.page_id) FROM dbo.SFAPGPAGEINMENU_ALL(?) AS x)				");


		Object[] objects = new Object[] {
				consultCompUsrGrpCommonDTO.getUsrGrpId()
				,consultCompUsrGrpCommonDTO.getCompNo()
				,menuId
		};
		
        this.getJdbcTemplate().update(query.toString(),objects);
	}

	@Override
	public void grantAllTabAuth(String menuId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("INSERT INTO TAUGPGPGAU(ugpgpgau_id, usrgrp_id, pgpage_id, comp_no)	                                    ");
		query.append("SELECT NEXT VALUE FOR SQAUGPGPGAU_ID, a.usrgrp_id, a.pgpage_id, a.comp_no  	                                    ");
		query.append("FROM (                                                    			                                    ");
		query.append("       SELECT ? as usrgrp_id, a.pgpage_id, ? as comp_no             			                            ");
		query.append("       FROM   TAPGPAGE a                            							                            ");
		query.append("       WHERE  a.c_page_id  IN (SELECT DISTINCT(x.page_id) FROM dbo.SFAPGPAGEINMENU_ALL(?) AS x)						    ");
		query.append("       group by a.pgpage_id                                                                               ");
		query.append("    ) a                                                                                                   ");



		Object[] objects = new Object[] {
				consultCompUsrGrpCommonDTO.getUsrGrpId()
				,consultCompUsrGrpCommonDTO.getCompNo()
				,menuId
				
		};
		
        this.getJdbcTemplate().update(query.toString(),objects);
	}

	@Override
	public void grantAllPageAuth(String menuId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("INSERT INTO TAUGPGAU(comp_no, ugpgau_id, page_id, usrgrp_id)	");
		query.append("SELECT 														");
		query.append("       ?, NEXT VALUE FOR SQAUGPGAU_ID, page_id, ?				");
		query.append("FROM TAMENU                                                   ");
        query.append("WHERE page_id IS NOT NULL                                     ");
        query.append("AND menu_id = ?                                               ");

		Object[] objects = new Object[] {
		        consultCompUsrGrpCommonDTO.getCompNo()
				,consultCompUsrGrpCommonDTO.getUsrGrpId()
				,menuId
		};
		
        this.getJdbcTemplate().update(query.toString(),objects);
	}

	@Override
	public void grantAllMenuAuth(String menuId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("INSERT INTO TAUSRGRPMENU(comp_no, usrgrpmenu_id, menu_id, usrgrp_id)	");
		query.append("VALUES(?, NEXT VALUE FOR SQAUSRGRPMENU_ID, ?, ?)                      ");

		Object[] objects = new Object[] {
				consultCompUsrGrpCommonDTO.getCompNo()
				,menuId
				,consultCompUsrGrpCommonDTO.getUsrGrpId()
		};
		
        this.getJdbcTemplate().update(query.toString(),objects);
	}

	@Override
	public List findUsrGrpPageList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) {
        QuerySqlBuffer query = new QuerySqlBuffer(); 

        query.append("WITH PAGEDATA AS(																");
        query.append("    SELECT                                         							");
        query.append("           y.page_id  pPageId                    								");
        query.append("           ,ISNULL(y.c_page_id, x.page_id) pageId    							");
        query.append("           ,pgpage_id pgPageId     							                ");
//        query.append("           ,x.description                        								");
        query.append("           ,ISNULL((SELECT a.key_name                                         ");
        query.append("                    FROM TALANG a                                             ");
        query.append("                    WHERE a.key_type = x.key_type                             ");
        query.append("                      AND a.key_no = x.key_no                                 ");
        query.append("                      AND a.lang = ?                                          ");
        query.append("                   ), description) description                                ");
        query.append("           ,x.file_name                            							");
        query.append("           ,y.ord_no ordNo                        							");
        query.append("           ,pgpage_id                            								");
        query.append("    FROM   TAPAGE x LEFT OUTER JOIN TAPGPAGE y                    			");
        query.append("      ON   x.page_id = y.c_page_id            								");
        query.append("      AND  y.is_use ='Y'                     									");
        query.append("    WHERE  x.is_chkauth = 'Y'                     							");
        query.append("      AND  x.is_use ='Y'                     									");
        query.append(")																				");
        query.append(", PGPAGEDATA AS(																");
        query.append("    SELECT    																");
        query.append("           z.pageId															");
        query.append("           ,z.pPageId															");
        query.append("           ,z.description														");
        query.append("           ,z.file_name														");
        query.append("           ,z.ordNo															");
        query.append("           ,z.pgpage_id														");
        query.append("           , 1 AS LVL															");
        query.append("           , convert(varchar(255), z.pageId) sort  							");
        query.append("    FROM   PAGEDATA AS z														");
        query.append("    WHERE  z.pageId = (SELECT a.page_id FROM TAMENU a WHERE a.menu_id = ?)	");
        query.append("    UNION ALL																	");
        query.append("    SELECT    																");
        query.append("           c.pageId															");
        query.append("           ,c.pPageId															");
        query.append("           ,c.description														");
        query.append("           ,c.file_name														");
        query.append("           ,c.ordNo															");
        query.append("           ,c.pgpage_id														");
        query.append("           ,d.LVL+ 1															");
        query.append("           , CONVERT(VARCHAR(255), CONVERT(NVARCHAR,d.sort) + ',' +  CONVERT(VARCHAR(255), c.pageId)) sort	");
        query.append("    FROM   PAGEDATA AS c INNER JOIN pgpageData AS d ON c.pPageId = d.pageId	");
        query.append("    WHERE  1 = 1																");
        query.append(")																				");
        query.append("SELECT 																		");
        query.append("        ROW_NUMBER() OVER(ORDER BY x.ordNo) id                                ");
        query.append("       ,'' seqNo                                    							");
        query.append("       ,x.pageId                                    							");
        query.append("       ,x.pPageId                                    							");
        query.append("       ,x.pgpage_id pgPageId                                    				");
        query.append("       ,x.description  pageDesc                      							");
        query.append("       ,x.file_name fileName                        							");
        query.append("       ,(SELECT a.key_name                        							");
        query.append("         FROM   TALANG a, TAMENU b                    						");
        query.append("         WHERE  a.key_no = b.key_no                							");
        query.append("           AND  a.key_type = b.key_type            							");
        query.append("           AND  a.lang =  ?                           						");
        query.append("           AND  b.menu_id = 133 ) menuDesc                                    ");
        query.append("       ,CASE ISNULL((SELECT TOP 1 '1' FROM TAUGPGAU b      					");
        query.append("                     WHERE  b.page_id = x.pageId     							");
        query.append("                       AND  b.usrgrp_id = ?          							");
        query.append("                  ),'')                            							");
        query.append("        WHEN '1'  THEN CASE ISNULL(x.pPageId,0)            					");
        query.append("                       WHEN 0 THEN '1'                						");
        query.append("                       ELSE ISNULL((SELECT '1' FROM TAUGPGPGAU b              ");
        query.append("                                    WHERE  b.pgpage_id = x.pgpage_id          ");
        query.append("                                      AND  b.usrgrp_id = ?),'')               ");
        query.append("                       END                            						");
        query.append("        ELSE ''                                    							");
        query.append("        END as AUTHCHECK    													");
        query.append("        ,sort CURPATH															");
        query.append("        ,CASE sort															");
        query.append("        WHEN REPLACE(sort,','+convert(nvarchar,x.pageId),'') THEN ''			");
        query.append("        ELSE REPLACE(sort,','+convert(nvarchar,x.pageId),'')					");
        query.append("        END PPATH,        													");
        query.append("        LVL,																	");
        query.append("        MIN(LVL) over () MINLVL     											");
        query.append("FROM PGPAGEDATA x						                   						");

        Object[] objects = new Object[] {
        		consultCompUsrGrpCommonDTO.getUserLang(),
                consultCompUsrGrpCommonDTO.getMenuId(),
                consultCompUsrGrpCommonDTO.getUserLang(),
                consultCompUsrGrpCommonDTO.getUsrGrpId(),
                consultCompUsrGrpCommonDTO.getUsrGrpId()
		};

        return getJdbcTemplate().queryForList(query.toString(), objects);
	}

	@Override
	public int deleteAllPageBtnAuth(String pageId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("DELETE z FROM TAUGPGBTNAU AS z												");
		query.append("WHERE z.pgbtn_id IN (SELECT a.pgbtn_id                        				");
		query.append("                     FROM   TAPGBTN a                            				");
		query.append("                     WHERE  a.page_id IN (SELECT DISTINCT(page_id) FROM dbo.SFAPGPAGE_CALL(?) AS x)	");
		query.append("                     )                                        				");
		query.append("AND z.usrgrp_id = ?                        	                				");
		query.append("AND z.comp_no = ?                        	                					");

		Object[] objects = new Object[] {
				pageId
				,consultCompUsrGrpCommonDTO.getUsrGrpId()
				,consultCompUsrGrpCommonDTO.getCompNo()
		};
		
        return this.getJdbcTemplate().update(query.toString(),objects);
	}

	@Override
	public int deleteAllPageTabAuth(String pgPageId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("DELETE z FROM TAUGPGPGAU AS z                                 ");
        query.append("WHERE pgpage_id = ?                                           ");
        query.append("AND z.usrgrp_id = ?                                           ");
        query.append("AND z.comp_no = ?                                             ");

        Object[] objects = new Object[] {
                pgPageId
                ,consultCompUsrGrpCommonDTO.getUsrGrpId()
                ,consultCompUsrGrpCommonDTO.getCompNo()
        };
		
        return this.getJdbcTemplate().update(query.toString(),objects);
	}

	@Override
	public int deleteAllPagePageAuth(String pageId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("DELETE z FROM TAUGPGAU AS z              ");
        query.append("WHERE z.page_id = ?                                             ");
        query.append("AND NOT EXISTS(SELECT ugpgpgau_id                               ");
        query.append("                 FROM TAUGPGPGAU                                ");
        query.append("                 WHERE usrgrp_id = ?                            ");
        query.append("                 AND comp_no = ?                                ");
        query.append("                 AND pgpage_id IN (SELECT pgpage_id             ");
        query.append("                                   FROM TAPGPAGE                ");
        query.append("                                   WHERE is_use = 'Y'           ");
        query.append("                                   AND c_page_id = ?)           ");
        query.append("                )                                               ");
        query.append("AND NOT EXISTS(SELECT usrgrpmenu_id                             ");
        query.append("               FROM TAUSRGRPMENU                                ");
        query.append("               WHERE usrgrp_id = ?                              ");
        query.append("               AND comp_no = ?                                  ");
        query.append("               AND menu_id IN (SELECT menu_id                   ");
        query.append("                               FROM TAMENU                      ");
        query.append("                               WHERE is_use = 'Y'               ");
        query.append("                               AND page_id = ?)                 ");
        query.append("              )                                                 ");
        query.append("AND z.usrgrp_id = ?                   ");
        query.append("AND z.comp_no = ?                     ");

        Object[] objects = new Object[] {
                pageId
                ,consultCompUsrGrpCommonDTO.getUsrGrpId()
                ,consultCompUsrGrpCommonDTO.getCompNo()
                ,pageId
                ,consultCompUsrGrpCommonDTO.getUsrGrpId()
                ,consultCompUsrGrpCommonDTO.getCompNo()
                ,pageId
                ,consultCompUsrGrpCommonDTO.getUsrGrpId()
                ,consultCompUsrGrpCommonDTO.getCompNo()
        };
		
        return this.getJdbcTemplate().update(query.toString(),objects);
	}

	@Override
	public void grantAllPageBtnAuth(String pageId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("INSERT INTO TAUGPGBTNAU(ugpgbtnau_id, usrgrp_id, pgbtn_id, comp_no)           ");
		query.append("SELECT NEXT VALUE FOR SQAUGPGBTN_ID, ?, a.pgbtn_id, ?					            ");
		query.append("FROM   TAPGBTN a                                                              ");
		query.append("WHERE  a.page_id IN (SELECT DISTINCT(x.page_id) FROM dbo.SFAPGPAGE_CALL(?) AS x)   ");

		Object[] objects = new Object[] {
				consultCompUsrGrpCommonDTO.getUsrGrpId()
				,consultCompUsrGrpCommonDTO.getCompNo()
				,pageId
		};
		
        this.getJdbcTemplate().update(query.toString(),objects);
	}

	@Override
	public void grantAllPageTabAuth(String pgPageId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("INSERT INTO TAUGPGPGAU(ugpgpgau_id, usrgrp_id, pgpage_id, comp_no)    ");
        query.append("VALUES(NEXT VALUE FOR SQAUGPGPGAU_ID, ?, ?, ?)                        ");
        
        Object[] objects = new Object[] {
                consultCompUsrGrpCommonDTO.getUsrGrpId()
                ,pgPageId
                ,consultCompUsrGrpCommonDTO.getCompNo()
        };
		
        this.getJdbcTemplate().update(query.toString(),objects);
	}

	@Override
	public void grantAllPagePageAuth(String pageId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("MERGE INTO TAUGPGAU A                       ");
        query.append("USING(  SELECT ? usrGrpId                   ");
        query.append("               ,? compNo                    ");
        query.append("               ,? pageId                    ");
        query.append("      )  b                                  ");
        query.append("ON(     A.comp_no = b.compNo                ");
        query.append("    AND A.page_id = b.pageId                ");
        query.append("    AND A.usrgrp_id = b.usrGrpId    )       ");
        query.append("WHEN MATCHED THEN                           ");
        query.append("    UPDATE SET     A.ugpgau_id = A.ugpgau_id                                                            ");
        query.append("WHEN NOT MATCHED THEN                                                                                   ");
        query.append("    INSERT (comp_no,          ugpgau_id,                  page_id,             usrgrp_id    )           ");
        query.append("    VALUES (b.compNo,         ?,                          b.pageId,            b.usrGrpId    );         ");
        
        Object[] objects = new Object[] {
                consultCompUsrGrpCommonDTO.getUsrGrpId()
                ,consultCompUsrGrpCommonDTO.getCompNo()
                ,pageId
                ,this.getNextSequence("sqaugpgau_id")
        };
		
        this.getJdbcTemplate().update(query.toString(),objects);
	}

	@Override
	public List getMpageList(String menuId) {
		QuerySqlBuffer query = new QuerySqlBuffer(); 
		
		query.append("SELECT                                     									");
		query.append("      x.page_id as \"PAGEID\"           										");
		query.append("FROM  dbo.SFAMENU_CALL(?,'') AS x                            					");
		query.append("WHERE x.page_id IS NOT NULL             										");

		Object[] objects = new Object[] {
				menuId
		};

        return getJdbcTemplate().queryForList(query.toString(), objects);
	}

	@Override
	public List findUsrGrpBtnList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer(); 

		query.append("SELECT															");
		query.append("       '' seqNo													");
		query.append("       ,(SELECT x.key_name										");
		query.append("         FROM   TALANG x											");
		query.append("         WHERE  x.key_type = a.key_type							");
		query.append("           AND  x.key_no = a.key_no								");
		query.append("           AND  x.lang = ? ) buttonDesc							");
		query.append("       ,b.button_no buttonNo										");
		query.append("       ,(SELECT y.description FROM TAPAGE y WHERE y.page_id = ? ) pageDesc	");
		query.append("       ,ISNULL((SELECT '1'										");
		query.append("             FROM   TAUGPGBTNAU k									");
		query.append("             WHERE  k.pgbtn_id = a.pgbtn_id						");
		query.append("               AND  k.usrgrp_id = ? ),'') AUTHCHECK				");
		query.append("       ,a.pgbtn_id pgbtnId 										");
		query.append("       ,a.page_id pageId 											");
		query.append("FROM TAPGBTN a, TABUTTON b										");
		query.append("WHERE a.button_id = b.button_id									");
		query.append("  AND a.page_id = ?												");
		query.append("  AND a.is_chkauth = 'Y'                         					");
		query.append("  AND a.is_use ='Y'                                               ");
        query.append("  AND b.is_use='Y'                                                ");
		
		Object[] objects = new Object[] {
				consultCompUsrGrpCommonDTO.getUserLang(),
				consultCompUsrGrpCommonDTO.getPageId(),
				consultCompUsrGrpCommonDTO.getUsrGrpId(),
				consultCompUsrGrpCommonDTO.getPageId()
		};

        return getJdbcTemplate().queryForList(query.toString(), objects);
	}

	@Override
	public int deletePageBtnAuth(String pgbtnId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("DELETE FROM TAUGPGBTNAU 				");
		query.append("WHERE pgbtn_id  	=  ?				");
		query.append("  AND usrgrp_id  	=  ?				");
		query.append("  AND comp_no  	=  ?				");

		Object[] objects = new Object[] {
				pgbtnId
				,consultCompUsrGrpCommonDTO.getUsrGrpId()
				,consultCompUsrGrpCommonDTO.getCompNo()
		};
		
        return this.getJdbcTemplate().update(query.toString(),objects);
	}

	@Override
	public int grantPageBtnAuth(String pgbtnId, ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("INSERT INTO TAUGPGBTNAU                 	");
		query.append("(                                        	");
		query.append("    ugpgbtnau_id,    comp_no,    			");
		query.append("    usrgrp_id,       pgbtn_id             ");
		query.append(")VALUES                                  	");
		query.append("(                                        	");
		query.append("    NEXT VALUE FOR SQAUGPGBTN_ID, ?,      ");
		query.append("    ?,               ?                   	");
		query.append(")                                        	");

        
        Object[] objects = new Object[] {
        		consultCompUsrGrpCommonDTO.getCompNo(),
                consultCompUsrGrpCommonDTO.getUsrGrpId(),
                pgbtnId
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
	}
	public String[] getAdminUsrGrp(String compNo){
		QuerySqlBuffer query = new QuerySqlBuffer();
		
    	query.append("SELECT usrgrp_no			");
    	query.append("FROM TAUSRGRP				");
    	query.append("WHERE 1=1					");
    	query.append("AND comp_no   	 = ?	");
    	query.append("AND is_admin_group = ?	");
    	
    	Object [] objects = new Object[]{
        		compNo
        		,"Y"
        };
    	
    	return QuerySqlBuffer.toStringSingleArray(this.getJdbcTemplate().queryForList(query.toString(),objects));
	}
}