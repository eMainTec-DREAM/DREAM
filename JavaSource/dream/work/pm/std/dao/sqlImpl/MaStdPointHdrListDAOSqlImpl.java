package dream.work.pm.std.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.pm.std.dao.MaStdPointHdrListDAO;
import dream.work.pm.std.dto.MaStdPointCommonDTO;

/**
 * 표준항목 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maStdPointHdrListDAOTarget"
 * @spring.txbn id="maStdPointHdrListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaStdPointHdrListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaStdPointHdrListDAO
{
	/**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointCommonDTO
     * @param loginUser
     * @return List
     */
    public List findStdPointHdrList(MaStdPointCommonDTO maStdPointCommonDTO, User loginUser)
    { 
        QuerySqlBuffer query = new QuerySqlBuffer(); 
       
        query.append("SELECT																	");
        query.append("		''	 						seqNo									");
        query.append("		,'' 						isDelCheck								");
        query.append("		,x.stwrk_id 				stWrkId									");
        query.append("		,x.stwrk_no 				stWrkNo									");
        query.append("		,x.description 				stWrkDesc								");
        query.append("      ,(SELECT a.full_desc                                            	");
        query.append("          FROM TAEQCTG a                                             		");
        query.append("         WHERE a.comp_no = x.comp_no                                  	");
        query.append("           AND a.eqctg_id = x.eqctg_id)		eqCtgDesc					");
        query.append("      ,(SELECT dbo.SFACODE_TO_DESC(x.revision_status,'REVISION_STATUS','SYS','','"+loginUser.getLangId()+"') )    revisionStatus    ");
        query.append("      ,x.is_last_version          isLastVersion    						");
        query.append("		,x.remark 					remark									");
        query.append("      ,(SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = x.dept_id) 	REGDEPT		");
        query.append("      ,(SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_id = x.reg_by) 			REGBY		");
        query.append("      ,x.reg_date 				REGDATE									");
        query.append("      ,(SELECT dbo.SFACODE_TO_DESC(x.is_active, 'IS_USE', 'SYS', x.comp_no,'"+loginUser.getLangId()+"') ) isActiveDesc		");
        query.append("FROM   TASTWRKLST x														");
        query.append("WHERE  1=1																"); 
        query.append(this.getWhere(maStdPointCommonDTO,loginUser));
        query.getOrderByQuery("x.stwrk_id", "x.stwrk_no", maStdPointCommonDTO.getOrderBy(), maStdPointCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maStdPointCommonDTO.getIsLoadMaxCount(), maStdPointCommonDTO.getFirstRow()));
    } 
    
    /**
     * Filter 조건
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaStdPointCommonDTO maStdPointCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.getAndQuery("x.comp_no", user.getCompNo());  
        query.getAndQuery("x.stwrk_type", "S");
        
        if (!"".equals(maStdPointCommonDTO.getStWrkId()))
        {
            query.getAndQuery("x.stwrk_id", maStdPointCommonDTO.getStWrkId());
            return query.toString();
        }
        
        query.getAndQuery("x.is_deleted", "N");
        
        query.getLikeQuery("x.description", maStdPointCommonDTO.getFilterStWrkDesc());
    	
    	query.getLikeQuery("x.stwrk_no", maStdPointCommonDTO.getFilterStWrkNo()); 
    	
    	query.getAndQuery("x.eqctg_id", maStdPointCommonDTO.getFilterEqCtgId());
    	
    	query.getAndQuery("x.revision_status", maStdPointCommonDTO.getRevisionStatus());
        query.getAndQuery("x.is_last_version", maStdPointCommonDTO.getIsLastVersion());
        
        if(!"".equals(maStdPointCommonDTO.getRevNo())){
        	query.append("AND x.revisionhist_id IN (SELECT 	a.revisionhist_id  								");
        	query.append("							FROM 	TAREVISIONHIST a								");
        	query.append("							WHERE 	a.comp_no = x.comp_no							");
        	query.append("					  		AND 	a.revision_object_type = 'PMSTD'				");
        	query.append("					  		AND 	a.rev_no = '"+maStdPointCommonDTO.getRevNo()+"'	");
            query.append("						 	)														");
        }
         
        return query.toString();
    }
    
    /**
     * 표준항목 삭제
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param id
     * @param loginUser
     * @return
     */
    public int deleteList(String id, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        query.append("UPDATE TASTWRKLST SET                           ");
        query.append("			  is_deleted = ? 					  ");
        query.append("WHERE  comp_no         = ?                      ");
        query.append("  AND  stwrk_id        = ?                      ");

        Object[] objects = new Object[] {   
        		"Y",
        		loginUser.getCompNo(),
                id
                };
        rtnValue = this.getJdbcTemplate().update(query.toString(),objects);
        
        query = new QuerySqlBuffer();
        query.append("UPDATE TASTWRKPOINT set                         ");
        query.append("			  is_deleted = ? 					  ");
        query.append("WHERE  comp_no         = ?                      ");
        query.append("  AND  stwrk_id        = ?                      ");
        
        Object[] objects1 = new Object[] {   
        		"Y",
        		loginUser.getCompNo(),
                id
                };
        rtnValue = this.getJdbcTemplate().update(query.toString(),objects1);
        
        return rtnValue;
    }

    /**
	 * copy detail insert
	 * @param maEqMstrDetailDTO
	 * @return
	 */
	public int insertCopyDetail(String newId, String oldId,String revisionHistId,String revisionStatus, User user, String isCopy)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TASTWRKLST										");
    	query.append("(comp_no, 		stwrk_id, 	stwrk_no, 	description, 		");
    	query.append(" stwrk_status,	eqctg_id, 	dept_id, 	reg_date, 			");
    	query.append(" upd_by, 			reg_by, 	remark, 	revisionhist_id, 	");
    	query.append(" revision_status, is_last_version								");
    	query.append(")																");
    	query.append("SELECT														");
    	query.append("	 	comp_no,		?,										");
    	if("Y".equals(isCopy))
    	{
    		// 복사
    		query.append(""+newId+",		"); 
    		query.append("	 '['+(SELECT a.key_name FROM TALANG a WHERE a.key_type='LABEL' AND a.key_no='copied' AND a.lang = '"+user.getLangId()+"')+']'+description,	");
    	}
    	else
    	{
    		// Revision
    		query.append("	stwrk_no, 		");	
    		query.append("	description,  	");
    	}
    	query.append("	stwrk_status, eqctg_id,	dept_id, CONVERT(CHAR(8), getdate(), 112),	");
    	query.append(" 	?,  	?, 		remark, 	?,										");
    	query.append("  ?,		? 															");
    	query.append("FROM 	TASTWRKLST														");
    	query.append("WHERE 1=1																");
    	query.append("AND 	comp_no = ?														");
    	query.append("AND 	stwrk_id= ?														");
    	
    	Object[] objects = new Object[] {
    			newId
    			,user.getEmpId()
    			,user.getEmpId()
    			,revisionHistId
    			,revisionStatus
    			,"N"
    			,user.getCompNo()
    			,oldId
    	};
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
	public int insertCopyPoint(String newId, String oldId, User user, String isCopy)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TASTWRKPOINT												");
    	query.append("(comp_no, 		stwrk_id,		stwrk_point_id,		stwrk_point_no, ");
    	query.append(" step_num,		eqasmb_id, 		std_check_point_id,	check_point, 	");
    	query.append(" check_method, 	fit_basis, 		check_type, 		check_min, 		");
    	query.append(" check_basis_val,	check_max,		uom,				schedule_type,	");
    	query.append(" cycle, 			period_type,	usage,				is_force,		");
    	query.append(" is_active, 		remark,			is_stopeq,			is_fins,		");
    	query.append(" is_funcins															");
    	query.append(")																		");
    	query.append("SELECT																");
    	query.append("	 comp_no,		?,	NEXT VALUE FOR SQASTWRK_POINT_ID,			");
    	if("Y".equals(isCopy))
    	{
    		// 복사
    		query.append("	SQASTWRK_POINT_ID.CURRVAL,		"); 
    	}
    	else
    	{
    		// Revision
    		query.append("	stwrk_point_no, 		");	
    	}
    	query.append(" step_num,		eqasmb_id, 		std_check_point_id,	check_point, 	");
    	query.append(" check_method, 	fit_basis, 		check_type, 		check_min, 		");
    	query.append(" check_basis_val,	check_max,		uom,				schedule_type,	");
    	query.append(" cycle, 			period_type,	usage,				is_force,		");
    	query.append(" is_active, 		remark,			is_stopeq,			is_fins,		");
    	query.append(" is_funcins															");
    	query.append("FROM 	TASTWRKPOINT													");
    	query.append("WHERE 1=1																");
    	query.append("AND 	comp_no = ?														");
    	query.append("AND 	stwrk_id= ?														");
    	
    	Object[] objects = new Object[] {
    			newId
    			,user.getCompNo()
    			,oldId
    	};
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
	public int insertCopyWoType(String newId, String oldId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TASTWRKWORK									");
    	query.append("(comp_no, 	stwrk_id,		stwrkwork_id,		 		");
    	query.append(" wo_type,		description, 	remark 						");
    	query.append(")															");
    	query.append("SELECT													");
    	query.append("	comp_no,	?,				NEXT VALUE FOR SQASTWRKWORK_ID,	");
    	query.append(" 	wo_type,	description, 	remark						");
    	query.append("FROM 	TASTWRKWORK											");
    	query.append("WHERE 1=1													");
    	query.append("AND 	comp_no = ?											");
    	query.append("AND 	stwrk_id= ?											");
    	
    	Object[] objects = new Object[] {
    			newId
    			,user.getCompNo()
    			,oldId
    	};
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
	public int insertCopyWork(String newId, String oldId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TASTWRKWORKPRC								");
    	query.append("(comp_no, 		stwrk_id,		stwrkworkprc_id,		");
    	query.append(" stwrkwork_id,	step_num, 		eqasmb_desc,			");
    	query.append(" description,		remark 									");
    	query.append(")															");
    	query.append("SELECT													");
    	query.append("	comp_no,		?,				NEXT VALUE FOR SQASTWRKWORKPRC_ID,	");
    	query.append(" 	stwrkwork_id,	step_num, 		eqasmb_desc,			");
    	query.append(" 	description,	remark									");
    	query.append("FROM 	TASTWRKWORKPRC										");
    	query.append("WHERE 1=1													");
    	query.append("AND 	comp_no = ?											");
    	query.append("AND 	stwrk_id= ?											");
    	
    	Object[] objects = new Object[] {
    			newId
    			,user.getCompNo()
    			,oldId
    	};
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
	public int insertCopyPart(String newId, String oldId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TASTWRKPART									");
    	query.append("(comp_no, 	stwrk_id,		stwrk_part_id,		 		");
    	query.append(" part_id,		part_desc, 		use_qty,					");
    	query.append(" remark 													");
    	query.append(")															");
    	query.append("SELECT													");
    	query.append("	comp_no,	?,				NEXT VALUE FOR SQASTWRKWORK_ID,	");
    	query.append(" 	part_id,	part_desc, 		use_qty,					");
    	query.append(" 	remark													");
    	query.append("FROM 	TASTWRKPART											");
    	query.append("WHERE 1=1													");
    	query.append("AND 	comp_no = ?											");
    	query.append("AND 	stwrk_id= ?											");
    	
    	Object[] objects = new Object[] {
    			newId
    			,user.getCompNo()
    			,oldId
    	};
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }

	public String findTotalCount(MaStdPointCommonDTO maStdPointCommonDTO, User loginUser) {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TASTWRKLST x										");
        query.append("WHERE  1=1												"); 
        query.append(this.getWhere(maStdPointCommonDTO,loginUser));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
}