package common.finder.valid.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.finder.valid.dao.ListOfValDAO;
import common.finder.valid.dto.ListOfValDTO;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;

/**
 * List Of Value DAOIml
 * @author  javaworker
 * @version $Id: ListOfValDAO.java,v 1.7 2015/01/09 00:16:44 pochul2423 Exp $
 * @since   1.0
 *
 * @spring.bean id="listOfValDAOTarget"
 * @spring.txbn id="listOfValDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */ 
public class ListOfValDAOSqlImpl extends BaseJdbcDaoSupportSql implements ListOfValDAO
{
    /**
     * code, description 을 검색 조건으로 
     * 시스템 코드 테이블검색 
     * @author  javaworker
     * @version $Id: ListOfValDAO.java,v 1.7 2015/01/09 00:16:44 pochul2423 Exp $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findSysDirList(ListOfValDTO listOfValDTO,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT												");
        query.append("       cdsysd_id id,									");
        query.append("       CDSYSD_NO code,								");
        query.append("       ISNULL((select aa.key_name						");
        query.append("            from talang aa							");
        query.append("            where  lang = '"+user.getLangId()+"'		");
        query.append("            and x.key_type = aa.key_type				");
        query.append("            and x.key_no = aa.key_no), x.description)	");
        query.append("       description									");
        query.append("FROM   TACDSYSD x										");
        query.append("WHERE  1=1											");
        query.append("  AND  cdsysm_id IN (SELECT cdsysm_id					");
        query.append("						FROM TACDSYSM					");
        query.append("						WHERE list_type='" + listOfValDTO.getCodeType() + "'	");
        query.append("					)									");
        query.getAndQuery("is_use", "Y");
        query.getLikeQuery("dbo.SFACODE_TO_DESC(cdsysd_no,list_type,'SYS','','"+user.getLangId()+"')", listOfValDTO.getDescription());
        query.getLikeQuery("cdsysd_no", listOfValDTO.getCode());
        if("PM".equals(listOfValDTO.getExtCode1())){
        	query.getAndQuery("param2", listOfValDTO.getExtCode1());
        }
        query.append("ORDER BY ISNULL(ord_no, '99999999')										");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * code, description 을 검색 조건으로 
     * 사용자 코드 테이블 검색
     * @author  javaworker
     * @version $Id: ListOfValDAO.java,v 1.7 2015/01/09 00:16:44 pochul2423 Exp $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findUsrDirList(ListOfValDTO listOfValDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT												");
        query.append("       cdusrd_id      id,								");
        query.append("       CDUSRD_NO           code,		        		");
        query.append("       description    description,					");
        query.append("       remark         remark	                        ");
        query.append("FROM   TACDUSRD										");
        query.append("WHERE  cdusrm_id = (SELECT cdusrm_id					");
        query.append("						FROM TACDUSRM					");
        query.append("						WHERE dir_type='" + listOfValDTO.getCodeType() + "'	");
        query.append("						  AND comp_no ='"+ listOfValDTO.getCompNo()+"'      ");
        query.append("					  )									");
        query.append("	AND  p_cdusrd_id = 0								");
        query.getLikeQuery("description", listOfValDTO.getDescription());
        query.getLikeQuery("CDUSRD_NO", listOfValDTO.getCode());
        query.append("ORDER BY ISNULL(ord_no, '9999')						");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * TAMENU Table에서 조회
     * @author  kim21017
     * @version $Id: ListOfValDAO.java,v 1.7 2015/01/09 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findMenuList(ListOfValDTO listOfValDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT											");
        query.append("       x.menu_id id,								");
        query.append("       (SELECT file_name							");
        query.append("          FROM TAPAGE								");
        query.append("         WHERE page_id=x.page_id) code,			");
        query.append("       x.description description					");
        query.append("FROM   TAMENU x ,TAUSRGRPMENU y     ");
        query.append("WHERE x.menu_id = y.menu_id   ");
        query.append("  AND  y.usrgrp_id =  '"+listOfValDTO.getUsrgrpId()+"'   ");
        query.getLikeQuery("x.menu_id", listOfValDTO.getCode());
        query.getLikeQuery("x.description", listOfValDTO.getDescription());
        query.append("ORDER BY x.description								");
        
        return getJdbcTemplate().queryForList(query.toString());
        
    }
    
    /**
     * TACDUSRD Table에서 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findCdUsrCdList(ListOfValDTO listOfValDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT											 ");
    	query.append("       cdusrd_id id,		      			         ");
    	query.append("       CDUSRD_NO as code,								         ");
    	query.append("       description								 ");
    	query.append("FROM   TACDUSRD									 ");
    	query.append("WHERE  comp_no   ='"+ listOfValDTO.getCompNo()+"'  ");
    	query.append("  AND  cdusrm_id ='"+ listOfValDTO.getExtCode1()+"'");
    	
    	query.getLikeQuery("description", listOfValDTO.getDescription());
    	query.getLikeQuery("CDUSRD_NO", listOfValDTO.getCode());
    	query.append("ORDER BY description								");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * TAUSRGRP Table에서 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findUsrGrpList(ListOfValDTO listOfValDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT											");
        query.append("       usrgrp_id      id,					        ");
        query.append("       usrgrp_no      code,					    ");
        query.append("       group_name     description					");
        query.append("FROM   TAUSRGRP									");
        query.append("WHERE  comp_no  ='"+ listOfValDTO.getCompNo()+"'");
        
        query.getLikeQuery("usrgrp_id", listOfValDTO.getCode());
        query.getLikeQuery("group_name", listOfValDTO.getDescription());
        query.append("ORDER BY group_name								");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * TAEMP Table에서 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findEmpNameList(ListOfValDTO listOfValDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                            ");
        query.append("       emp_id       id,                           ");
        query.append("       emp_no       code,                         ");
        query.append("       emp_name       description                 ");
        query.append("FROM   TAEMP                                      ");
        query.append("WHERE  comp_no  ='"+ listOfValDTO.getCompNo()+"'");
        query.getLikeQuery("emp_no", listOfValDTO.getCode());
        query.getLikeQuery("emp_name", listOfValDTO.getDescription());
        query.append("ORDER BY emp_name								    ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findEmpNoList(ListOfValDTO listOfValDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                            ");
        query.append("       emp_id       id,                           ");
        query.append("       emp_no       code,                         ");
        query.append("       emp_no         description                 ");
        query.append("FROM   TAEMP                                      ");
        query.append("WHERE  comp_no  ='"+ listOfValDTO.getCompNo()+"'");
        query.getLikeQuery("emp_id", listOfValDTO.getCode());
        query.getLikeQuery("emp_no", listOfValDTO.getDescription());
        query.append("ORDER BY emp_name                                ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * TADEPT Table에서 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findDeptList(ListOfValDTO listOfValDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT dept_id        id,								    ");
        query.append("       dept_no        code,					            ");
        query.append("       description					                    ");
        query.append("FROM   TADEPT									            ");
        query.append("WHERE  comp_no  ='"+ listOfValDTO.getCompNo()+"'          ");
        
        query.getLikeQuery("description", listOfValDTO.getDescription());
        query.append("ORDER BY description								        ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * TAEQLOC Table에서 조회 - 위치분류
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findEqLocList(ListOfValDTO listOfValDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT eqloc_id        id,								");
        query.append("       eqloc_no        code,								");
        query.append("       full_desc  description								     	");
        query.append("FROM   TAEQLOC											");
        query.append("WHERE  comp_no  ='"+ listOfValDTO.getCompNo()+"'			");
        query.getLikeQuery("description", listOfValDTO.getDescription());
        
        query.append("ORDER BY ISNULL(ord_no, '99999999')											");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findEqLocFullList(ListOfValDTO listOfValDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT eqloc_id        id,                                ");
        query.append("       eqloc_no        code,                              ");
        query.append("       full_desc  description                             ");
        query.append("FROM   TAEQLOC                                            ");
        query.append("WHERE  comp_no  ='"+ listOfValDTO.getCompNo()+"'          ");
        query.getLikeQuery("full_desc", listOfValDTO.getDescription());

        query.append("ORDER BY ISNULL(ord_no, '99999999')                                           ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    /**
     * TAEQCTG Table에서 조회 - 설비종류
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findEqCtgList(ListOfValDTO listOfValDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT eqctg_id        id,								");
        query.append("       eqctg_no        code,									");
        query.append("       full_desc       description				    ");
        query.append("FROM   TAEQCTG											");
        query.append("WHERE  comp_no  ='"+ listOfValDTO.getCompNo()+"'			");
        query.getLikeQuery("description", listOfValDTO.getDescription());
        query.append("ORDER BY ISNULL(ord_no, '99999999')											");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findEqCtgFullList(ListOfValDTO listOfValDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT eqctg_id        id,                                ");
        query.append("       eqctg_no        code,                                  ");
        query.append("       full_desc  description                             ");
        query.append("FROM   TAEQCTG                                            ");
        query.append("WHERE  comp_no  ='"+ listOfValDTO.getCompNo()+"'          ");
        query.getLikeQuery("full_desc", listOfValDTO.getDescription());

        query.append("ORDER BY ISNULL(ord_no, '99999999')                                           ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 창고
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findWcodeList(ListOfValDTO listOfValDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT wcode_id       id,									");
        query.append("       wcode          code,								");
        query.append("       wname	 		description							");
        query.append("FROM   TAWAREHOUSE										");
        query.append("WHERE  comp_no  ='"+ listOfValDTO.getCompNo()+"'			");
        query.getLikeQuery("wname", listOfValDTO.getDescription());
        query.getAndQuery("wh_categ", "PART");
        query.append("ORDER BY wname											");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * TAFIALURE Table에서 조회 - 설비종류
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findFailureList(ListOfValDTO listOfValDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT x.failure_id        id,							");
        query.append("       x.failure_no      code,							");
        query.append("       (select aa.key_name								");
        query.append("            from talang aa								");
        query.append("            where  aa.lang = '"+user.getLangId()+"'		");
        query.append("            and x.key_type = aa.key_type					");
        query.append("            and x.key_no = aa.key_no)	description			");
        query.append("FROM   TAFAILURE x										");
        query.append("WHERE  x.comp_no  ='"+ listOfValDTO.getCompNo()+"'		");
        query.getAndQuery("x.fail_type", listOfValDTO.getExtCode1());
        query.getLikeQuery("x.failure_no", listOfValDTO.getCode());
        if(!"".equals(listOfValDTO.getDescription())&&listOfValDTO.getDescription()!=null){
        	query.append("AND x.key_no IN	( SELECT key_no							");
        	query.append("					FROM TALANG								");
        	query.append("					WHERE key_type='FAILCODE'				");
        	query.getStringEqualQuery("lang", user.getLangId());
        	query.getLikeQuery("key_name", listOfValDTO.getDescription());
        	query.append("				)											");
        }
        
        query.append("ORDER BY x.failure_no										");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * YN 
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findYnList(ListOfValDTO listOfValDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT x.yn     id,						");
        query.append("       x.yn     code,						");
        query.append("       x.yn     description				");
        query.append("FROM   (SELECT 'Y' yn 					");
        query.append("			UNION ALL SELECT 'N' yn) x		");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * LEVEL(1,2,3,4)
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findLevelList(ListOfValDTO listOfValDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT x.lvl     id,							");
        query.append("       x.lvl     code,						");
        query.append("       x.lvl     description					");
        query.append("FROM   (SELECT '1' lvl 						");
        query.append("			union all SELECT '2' lvl  			");
        query.append("			union all SELECT '3' lvl  			");
        query.append("			union all SELECT '4' lvl  ) x		");
        
        return getJdbcTemplate().queryForList(query.toString());
    }

    /**
     * TAEQLOC Table에서 조회 - 위치분류
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findUserList(ListOfValDTO listOfValDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT user_id        id,                                 ");
        query.append("       user_no        code,                               ");
        query.append("       user_name      description                         ");
        query.append("FROM   TAUSER                                             ");
        query.append("WHERE  comp_no  ='"+ listOfValDTO.getCompNo()+"'          ");
        
        query.getLikeQuery("user_name", listOfValDTO.getDescription());
        query.append("ORDER BY user_name                                       ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findPmTypeList(ListOfValDTO listOfValDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT												");
        query.append("       CDSYSD_NO id,									");
        query.append("       CDSYSD_NO code,								");
        query.append("       ISNULL((select aa.key_name						");
        query.append("            from talang aa							");
        query.append("            where  lang = '"+user.getLangId()+"'		");
        query.append("            and x.key_type = aa.key_type				");
        query.append("            and x.key_no = aa.key_no), x.description)	");
        query.append("       description									");
        query.append("FROM   TACDSYSD x										");
        query.append("WHERE  1=1											");
        query.append("  AND  list_type='" + listOfValDTO.getCodeKind() + "'+'_TYPE'	");
        query.getLikeQuery("dbo.SFACODE_TO_DESC(cdsysd_no,list_type,'SYS','','"+user.getLangId()+"')", listOfValDTO.getDescription());
        query.getLikeQuery("cdsysd_no", listOfValDTO.getCode());
        query.getStringEqualQuery("x.is_use", "Y");
        query.append("ORDER BY ISNULL(ord_no, '99999999')										");
        
        return getJdbcTemplate().queryForList(query.toString());
    }

	public List findPlantList(ListOfValDTO listOfValDTO) {
	 	QuerySqlBuffer query = new QuerySqlBuffer();
        
	 	query.append("SELECT							");
        query.append("       plant id      				");
        query.append("       ,plant code  				");
        query.append("       ,description     			");
        query.append("FROM   TAPLANT     				");
        query.append("WHERE  1=1				    	");
        query.getLikeQuery("description", listOfValDTO.getDescription());
        query.getLikeQuery("plant", listOfValDTO.getCode());
        query.append("ORDER BY plant					");
        
        return getJdbcTemplate().queryForList(query.toString());
	}

	public List findEqToolList(ListOfValDTO listOfValDTO) {
	 	QueryBuffer query = new QueryBuffer();
        
	 	query.append("SELECT							");
	 	query.append("       x.equip_id		id			");
        query.append("       ,x.equip_id	code		");
        query.append("       ,y.description description ");
        query.append("FROM   TAEQTOOL x, TAEQUIPMENT y	");
        query.append("WHERE  x.equip_id = y.equip_id   	");
        query.append("AND	 x.is_standard_eq = 'Y'		");
        query.getLikeQuery("y.description", listOfValDTO.getDescription());
        
        return getJdbcTemplate().queryForList(query.toString());
	}
	
	public List findCrityList(ListOfValDTO listOfValDTO) {
	 	QueryBuffer query = new QueryBuffer();
        
	 	query.append("SELECT											");
	 	query.append("       critylist_id id							");
	 	query.append("       ,critylist_id code							");
        query.append("       ,description  								");
        query.append("FROM   TACRITYLIST 								");
        query.append("WHERE  comp_no = '"+ listOfValDTO.getCompNo()+"'	");
        query.append("AND	 is_use = 'Y'								");
        query.getLikeQuery("description", listOfValDTO.getDescription());
        
        return getJdbcTemplate().queryForList(query.toString());
	}
	
	public List findTaskMapList(ListOfValDTO listOfValDTO) {
	 	QueryBuffer query = new QueryBuffer();
        
	 	query.append("SELECT											");
	 	query.append("       pmtaskmaplist_id id						");
	 	query.append("       ,pmtaskmaplist_no code						");
        query.append("       ,description  								");
        query.append("FROM   TAPMTASKMAPLIST 							");
        query.append("WHERE  comp_no = '"+ listOfValDTO.getCompNo()+"'	");
        query.append("AND	 is_use = 'Y'								");
        query.getLikeQuery("description", listOfValDTO.getDescription());
        
        return getJdbcTemplate().queryForList(query.toString());
	}

	@Override
	public List findAcSysDirList(ListOfValDTO listOfValDTO, User user, Map<String, String> conditionMap) 
	{
		
		QueryBuffer query = new QueryBuffer();

        query.append("SELECT                 								    ");
        query.append("       ISNULL((select aa.key_name							");
        query.append("            from talang aa								");
        query.append("            where  lang = '"+user.getLangId()+"'			");
        query.append("            and x.key_type = aa.key_type					");
        query.append("            and x.key_no = aa.key_no), x.description)		");        
        query.append("       description									    ");
        query.append("       ,cdsysd_no									    	");
        query.append("       ,param1									    	");
        query.append("FROM   TACDSYSD x											");
        query.append("WHERE  1=1												");
        query.getAndQuery("is_use", conditionMap);
        query.append("  AND  cdsysm_id IN (SELECT cdsysm_id						");
        query.append("						FROM TACDSYSM						");
        query.append("						WHERE 1 = 1							");
        
        query.getAndQuery("list_type", conditionMap);
        query.getAndQuery("is_use", conditionMap);
        query.getAndQuery("param1", conditionMap);
        query.getAndQuery("param2", conditionMap);

        if("param22".equals(conditionMap.get("param22")))
		{
        	query.append("	AND ISNULL(x.param2,'') != 'PM'	");
		}
        if(conditionMap.containsKey("p_list_type")){
            query.append("AND list_type IN(SELECT param1                        ");
            query.append("                 FROM TACDSYSD                        ");
            query.append("                 WHERE cdsysm_id=(SELECT cdsysm_id    ");
            query.append("                                  FROM TACDSYSM       ");
            query.append("                                  WHERE 1=1           ");
            query.getAndQuery("list_type", conditionMap.get("p_list_type"));
            query.append("                                  )                   ");
            query.append("                 )                                    ");
        }
        if(conditionMap.containsKey("p_cdsysd_no")){
            query.append("AND list_type IN(SELECT param1                        ");
            query.append("                 FROM TACDSYSD                        ");
            query.append("                 WHERE 1=1                            ");
            query.getAndQuery("cdsysd_no", conditionMap.get("p_cdsysd_no"));
            query.append("                 )                                    ");
        }
        
        query.append("					)										");
        query.getLikeQuery("dbo.SFACODE_TO_DESC(cdsysd_no,list_type,'SYS','','"+user.getLangId()+"')", listOfValDTO.getDescription());
        query.getLikeQuery("cdsysd_no", listOfValDTO.getCode());
        query.append("ORDER BY  ord_no											");
        
        
        return getJdbcTemplate().queryForList(query.toString());
	}

	/**
     * code, description 을 검색 조건으로 
     * 회사 코드 테이블검색 
     * @author  javaworker
     * @version $Id: ListOfValDAO.java,v 1.7 2015/01/09 00:16:44 pochul2423 Exp $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findCompList(ListOfValDTO listOfValDTO,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();        

        query.append("SELECT 									");
        query.append("		COMP_NO			AS	code			");
        query.append("     	, DESCRIPTION	AS	description		");
        query.append("FROM TACOMP x								");
        query.append("WHERE 1=1									");
        query.getAndQuery("is_use", "Y");
        query.append("ORDER BY COMP_NO							");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}