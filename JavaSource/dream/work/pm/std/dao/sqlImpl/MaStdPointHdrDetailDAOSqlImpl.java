package dream.work.pm.std.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.work.pm.std.dao.MaStdPointHdrDetailDAO;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPointHdrDetailDTO;

/**
 * 표준항목 - 상세 dao
 * 
 * @author kim21017
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maStdPointHdrDetailDAOTarget"
 * @spring.txbn id="maStdPointHdrDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaStdPointHdrDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaStdPointHdrDetailDAO
{
	/**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0 
     * 
     * @param maStdPointCommonDTO
     * @param loginUser
     * @return
     */
    public MaStdPointHdrDetailDTO findDetail(MaStdPointCommonDTO maStdPointCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT																			");
        query.append("		x.stwrk_id									stWrkId							");
        query.append("		,x.stwrk_no									stWrkNo							");
        query.append("		,x.description								stWrkDesc						");
        query.append("      ,x.eqctg_id 								eqCtgId							");
        query.append("		,(SELECT a.full_desc														");
        query.append("		    FROM TAEQCTG	a														");
        query.append("		   WHERE a.comp_no = x.comp_no												");
        query.append("		     AND a.eqctg_id = x.eqctg_id)			eqCtgDesc						");
        query.append("      ,x.upd_by									updBy							");
        query.append("		,(SELECT a.emp_name															");
        query.append("		    FROM TAEMP a															");
        query.append("		   WHERE a.comp_no = x.comp_no												");
        query.append("		     AND a.emp_id = x.upd_by)				updName							");
        query.append("      ,x.reg_by regBy																");
        query.append("		,(SELECT a.emp_name															");
        query.append("		   FROM TAEMP a																");
        query.append("		  WHERE a.comp_no = x.comp_no												");
        query.append("			AND a.emp_id = x.reg_by)	regName										");
        query.append("      ,x.reg_date									regDate							");
        query.append("      ,x.revisionhist_id 							revisionhistId					");
        query.append("      ,x.revision_status 							revisionStatusId				");
        query.append("      ,(SELECT dbo.SFACODE_TO_DESC(x.revision_status, 'REVISION_STATUS', 'SYS', '"+loginUser.getCompNo()+"','"+loginUser.getLangId()+"') )	revisionStatusDesc	");
        query.append("      ,x.is_last_version 							isLastVersion					");
        query.append("		,x.remark									remark							");
        query.append("		,x.dept_id									deptId							");
        query.append("      ,(SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = x.dept_id) 	deptDesc	");
        query.append("		,x.is_active 								isActive						");
        query.append("      ,(SELECT dbo.SFACODE_TO_DESC(x.is_active, 'IS_USE', 'SYS', x.comp_no,'"+loginUser.getLangId()+"') ) isActiveDesc		");
        query.append("		,x.stwrk_status								stwrkStatus						");
        query.append("      ,(SELECT dbo.SFACODE_TO_DESC(x.stwrk_status, 'STWRK_STATUS', 'SYS', x.comp_no,'"+loginUser.getLangId()+"') ) stwrkStatusDesc		");
        query.append("		,x.upd_date 								lastUpdTime						");
        query.append("FROM   TASTWRKLST x																");
        query.append("WHERE  1=1																		");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.stwrk_id", maStdPointCommonDTO.getStWrkId());
    
        MaStdPointHdrDetailDTO maStdPointHdrDetailDTO = 
        		(MaStdPointHdrDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaStdPointHdrDetailDTO()));
        
        return maStdPointHdrDetailDTO;
    }
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointHdrDetailDTO
     * @return
     */
    public int insertDetail(MaStdPointHdrDetailDTO maStdPointHdrDetailDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TASTWRKLST                               ");
    	query.append("    (comp_no            ,stwrk_id                    ");
    	query.append("     ,stwrk_no          ,description                 ");
    	query.append("     ,eqctg_id          ,upd_by                	   ");
    	query.append("     ,reg_date          ,reg_by                      ");
    	query.append("     ,dept_id           ,is_active                   ");
    	query.append("     ,remark            ,stwrk_status				   ");
    	query.append("     ,upd_date          ,stwrk_type	         	   ");
    	query.append("     ,plant							         	   ");
    	query.append("    )    VALUES                                      ");
    	query.append("    (?                ,?                             ");
    	query.append("     ,?                ,?                            ");
    	query.append("     ,?                ,?                            ");
    	query.append("     ,?        		 ,?  						   ");
    	query.append("     ,?                ,?                            ");
    	query.append("     ,?                ,?        					   ");
    	query.append("     ,?                ,?        					   ");
    	query.append("     ,?                	       					   ");
    	query.append("    )                                                ");
    	
    	Object[] objects = new Object[] {
    			loginUser.getCompNo()
    			,maStdPointHdrDetailDTO.getStWrkId()
    	        ,maStdPointHdrDetailDTO.getStWrkNo()
    	        ,maStdPointHdrDetailDTO.getStWrkDesc()
    	        ,maStdPointHdrDetailDTO.getEqCtgId()
    	        ,loginUser.getEmpId()
    	        ,maStdPointHdrDetailDTO.getRegDate()
    	        ,maStdPointHdrDetailDTO.getRegBy()
    	        ,maStdPointHdrDetailDTO.getDeptId()
    	        ,maStdPointHdrDetailDTO.getIsActive()
    	        ,maStdPointHdrDetailDTO.getRemark()
    	        ,maStdPointHdrDetailDTO.getStwrkStatus()
    	        ,maStdPointHdrDetailDTO.getLastUpdTime()
    	        ,"S"
    	        ,loginUser.getPlant()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointHdrDetailDTO
     * @return
     */
    public int updateDetail(MaStdPointHdrDetailDTO maStdPointHdrDetailDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	Object[] objects;

    	query.append("UPDATE TASTWRKLST  SET   ");
    	query.append("    description     = ?  ");
    	query.append("    ,eqctg_id       = ?  ");
    	query.append("    ,upd_by         = ?  ");
    	query.append("    ,dept_id        = ?  ");
    	query.append("    ,is_active      = ?  ");
    	query.append("    ,reg_date	      = ?  ");
    	query.append("    ,remark         = ?  ");
    	query.append("    ,upd_date       = ?  ");
    	query.append("    ,reg_by	      = ?  ");
    	query.append("WHERE stwrk_id      = ?  ");
    	query.append("  AND comp_no       = ?  ");
    	
    	objects = new Object[] {
    			maStdPointHdrDetailDTO.getStWrkDesc()
    			,maStdPointHdrDetailDTO.getEqCtgId()
    			,loginUser.getEmpId()
    			,maStdPointHdrDetailDTO.getDeptId()
    			,maStdPointHdrDetailDTO.getIsActive()
    			,maStdPointHdrDetailDTO.getRegDate()
    	        ,maStdPointHdrDetailDTO.getRemark()
    	        ,maStdPointHdrDetailDTO.getLastUpdTime()
    	        ,maStdPointHdrDetailDTO.getRegBy()
    	        ,maStdPointHdrDetailDTO.getStWrkId()
    	        ,loginUser.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointHdrDetailDTO
     * @return
     */
    public int confirmDetail(MaStdPointHdrDetailDTO maStdPointHdrDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	Object[] objects;

    	query.append("UPDATE TASTWRKLST		");
    	query.append("SET stwrk_status = ?	");
    	query.append("WHERE comp_no = ?		");
    	query.append("AND stwrk_id  = ?		");
    	
    	objects = new Object[] {
    			"C",
    	        maStdPointHdrDetailDTO.getCompNo(),
    	        maStdPointHdrDetailDTO.getStWrkId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
	public int insertRevisionHist(MaStdPointHdrDetailDTO maStdPointHdrDetailDTO, User user, String histId) {
		QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("INSERT INTO TAREVISIONHIST               				");
    	query.append("(                                        				");
    	query.append("  revisionhist_id,			comp_no,            	");
    	query.append("  object_id,  				object_no,       		");
    	query.append("	revision_object_type,      	doc_no,                 ");
    	query.append("  revision_status,         	revision_step_status,	");
    	query.append("  revision_type,           	rev_no,        			");
    	query.append("  wrk_date,           		wrk_id,         		");
    	query.append("  rev_desc       				   						");
    	query.append(")VALUES                                  				");
    	query.append("(                                        				");
    	query.append("    ?,               ?,                  				");
    	query.append("    ?,               ?,                  				");
    	query.append("    ?,               ?,                    			");
    	query.append("    ?,               ?,                  				");
    	query.append("    ?,               ?,                  				");
    	query.append("    ?,               ?,                  				");
    	query.append("    ?                                   				");
    	query.append(")                                        				");

    	
    	Object[] objects = new Object[] {
    			histId
    			,user.getCompNo()
    	        ,maStdPointHdrDetailDTO.getStWrkId()
    	        ,maStdPointHdrDetailDTO.getStWrkNo()
    	        ,"STWRK"
    	        ,""
    	        ,"C"
    	        ,"CMP"
    	        ,"C"
    	        ,"1.00"
    	        ,DateUtil.getDateTime("yyyyMMdd")
    	        ,user.getEmpId()
    	        ,maStdPointHdrDetailDTO.getStWrkDesc()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
	}
    

	@Override
	public int updateStdPointMstrLastVersion(MaStdPointHdrDetailDTO maStdPointHdrDetailDTO, User user, String histId) {
		QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TASTWRKLST SET                					");
    	query.append("		revisionhist_id = ?                             ");
    	query.append("		,revision_status = ?                            ");
    	query.append("		,is_last_version = ?                            ");
    	query.append("WHERE comp_no = ?                       				");
    	query.append("AND   stwrk_id = ?                       				");

    	
    	Object[] objects = new Object[] {
    			histId
    			,"C"
    	        ,"Y"
    	        ,user.getCompNo()
    	        ,maStdPointHdrDetailDTO.getStWrkId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
	}
}