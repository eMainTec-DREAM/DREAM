package dream.work.fmea.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.fmea.list.dao.WorkFmeaDetailDAO;
import dream.work.fmea.list.dto.WorkFmeaCommonDTO;
import dream.work.fmea.list.dto.WorkFmeaDetailDTO;

/**
 * 고장영향성평가 - Detail DAO implements
 * @author kim21017
 * @version $Id: WorkFmeaDetailDAOSqlImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workFmeaDetailDAOTarget"
 * @spring.txbn id="workFmeaDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkFmeaDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkFmeaDetailDAO
{
	
    public WorkFmeaDetailDTO findDetail(WorkFmeaCommonDTO workFmeaCommonDTO, User user) 
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT																			");
        query.append("		x.wofmea_id												AS woFmeaId			");
        query.append("		,x.wkor_id												AS wkorId			");
        query.append("        ,(SELECT a.description FROM TAWORKORDER a WHERE a.comp_no = x.comp_no       ");
        query.append("            AND a.wkor_id = x.wkor_id)                           AS wkorDesc       ");
        query.append("		,x.wofmea_no											AS woFmeaNo			");
        query.append("		,x.equip_id												AS equipId			");
        query.append("		,(SELECT a.item_no FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no			");
        query.append("			AND a.equip_id = x.equip_id)						AS itemNo			");
        query.append("		,(SELECT a.description FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no		");
        query.append("			AND a.equip_id = x.equip_id)						AS equipDesc		");
        query.append("		,x.eqloc_id												AS eqLocId			");
        query.append("		,(SELECT a.full_desc FROM TAEQLOC a WHERE a.comp_no = x.comp_no				");
        query.append("			AND a.eqloc_id = x.eqloc_id)						AS eqLocDesc		");
        query.append("		,x.req_date												AS reqDate			");
        query.append("		,x.req_by												AS reqById			");
        query.append("		,(SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no				");
        query.append("			AND a.emp_id = x.req_by)							AS reqByDesc		");
        query.append("		,x.req_dept												AS reqDeptId		");
        query.append("		,(SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no			");
        query.append("			AND a.dept_id = x.req_dept)							AS reqDeptDesc		");
        query.append("		,x.situation											AS situation		");
        query.append("		,x.repair												AS repair			");
        query.append("		,x.wofmea_status										AS woFmeaStatusId	");
        query.append("		,dbo.SFACODE_TO_DESC(x.wofmea_status,'WOFMEA_STATUS','SYS',''				");
        query.append("							,?						) 			AS woFmeaStatusDesc	");
        query.append("		,x.fmea_priority										AS fmeaPriorityId	");
        query.append("		,dbo.SFACODE_TO_DESC(x.fmea_priority,'FMEA_PRIORITY','SYS',''				");
        query.append("							,?						) 			AS fmeaPriorityDesc	");
        query.append("		,x.fmea_wotype											AS fmeaWoTypeId		");
        query.append("		,dbo.SFACODE_TO_DESC(x.fmea_wotype,'FMEA_WOTYPE','SYS',''					");
        query.append("							,?						) 			AS fmeaWoTypeDesc	");
        query.append("		,x.iscalib												AS isCalibId		");
        query.append("		,dbo.SFACODE_TO_DESC(x.iscalib,'IS_USE','SYS',''							");
        query.append("							,?						) 			AS isCalibDesc		");
        query.append("		,x.isqual												AS isQualId			");
        query.append("		,dbo.SFACODE_TO_DESC(x.isqual,'IS_USE','SYS',''								");
        query.append("							,?						) 			AS isQualDesc		");
        query.append("		,x.review_date											AS reviewDate		");
        query.append("		,x.review_by											AS reviewById		");
        query.append("		,(SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no				");
        query.append("			AND a.emp_id = x.review_by)							AS reviewByDesc		");
        query.append("		,x.review_by											AS reviewById		");
        query.append("		,(SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no				");
        query.append("			AND a.emp_id = x.review_by)							AS reviewByDesc		");
        query.append("		,x.review_comments										AS reviewComments	");
        query.append("FROM TAWOFMEA x																	");
    	query.append("WHERE  1=1																		");
    	query.append("AND  wofmea_id 		= ?															");
    	query.append("AND  comp_no    		= ?															");
        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,user.getLangId()
        		,user.getLangId()
        		,user.getLangId()
        		,user.getLangId()
        		,workFmeaCommonDTO.getWoFmeaId()
    			,user.getCompNo()
    	};
    
        WorkFmeaDetailDTO workFmeaDetailDTO = 
        		(WorkFmeaDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new WorkFmeaDetailDTO()));
        
        return workFmeaDetailDTO;
        
    }

    public int insertDetail(WorkFmeaDetailDTO workFmeaDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TAWOFMEA(			");
    	query.append("	comp_no						");
    	query.append("	,wofmea_id					");
    	query.append("	,wkor_id					");
    	query.append("	,wofmea_no					");
    	query.append("	,wofmea_status				");
    	query.append("	,equip_id					");
    	query.append("	,eqloc_id					");
    	query.append("	,req_date					");
    	query.append("	,req_dept					");
    	query.append("	,req_by						");
    	query.append("	,situation					");
    	query.append("	,repair						");
    	query.append("	,fmea_priority				");
    	query.append("	,fmea_wotype				");
    	query.append("	,iscalib					");
    	query.append("	,isqual						");
    	query.append("	,review_date				");
    	query.append("	,review_dept				");
    	query.append("	,review_by					");
    	query.append("	,review_comments			");
    	query.append(")VALUES(						");
    	query.append("	?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append(")								");
    	
    	Object[] objects = new Object[] {
    			 user.getCompNo()
    			,workFmeaDetailDTO.getWoFmeaId()
    			,workFmeaDetailDTO.getWkorId()
    			,workFmeaDetailDTO.getWoFmeaNo()
    			,workFmeaDetailDTO.getWoFmeaStatusId()
    			,workFmeaDetailDTO.getEquipId()
    			,workFmeaDetailDTO.getEqLocId()
    			,workFmeaDetailDTO.getReqDate()
    			,workFmeaDetailDTO.getReqDeptId()
    			,workFmeaDetailDTO.getReqById()
    			,workFmeaDetailDTO.getSituation()
    			,workFmeaDetailDTO.getRepair()
    			,workFmeaDetailDTO.getFmeaPriorityId()
    			,workFmeaDetailDTO.getFmeaWoTypeId()
    			,workFmeaDetailDTO.getIsCalibId()
    			,workFmeaDetailDTO.getIsQualId()
    			,workFmeaDetailDTO.getReviewDate()
    			,workFmeaDetailDTO.getReviewDeptId()
    			,workFmeaDetailDTO.getReviewById()
    			,workFmeaDetailDTO.getReviewComments()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int updateDetail(WorkFmeaDetailDTO workFmeaDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAWOFMEA SET						");
    	query.append("	equip_id				= ?				");
    	query.append("	,eqloc_id				= ?				");
    	query.append("	,req_date				= ?				");
    	query.append("	,req_dept				= ?				");
    	query.append("	,req_by					= ?				");
    	query.append("	,situation				= ?				");
    	query.append("	,repair					= ?				");
    	query.append("	,fmea_priority			= ?				");
    	query.append("	,fmea_wotype			= ?				");
    	query.append("	,iscalib				= ?				");
    	query.append("	,isqual					= ?				");
    	query.append("	,review_date			= ?				");
    	query.append("	,review_dept			= ?				");
    	query.append("	,review_by				= ?				");
    	query.append("	,review_comments		= ?				");
    	query.append("WHERE  comp_no			= ?				");
    	query.append("  AND  wofmea_id			= ?				");
    	
    	Object[] objects = new Object[] {
    			workFmeaDetailDTO.getEquipId()
    			,workFmeaDetailDTO.getEqLocId()
    			,workFmeaDetailDTO.getReqDate()
    			,workFmeaDetailDTO.getReqDeptId()
    			,workFmeaDetailDTO.getReqById()
    			,workFmeaDetailDTO.getSituation()
    			,workFmeaDetailDTO.getRepair()
    			,workFmeaDetailDTO.getFmeaPriorityId()
    			,workFmeaDetailDTO.getFmeaWoTypeId()
    			,workFmeaDetailDTO.getIsCalibId()
    			,workFmeaDetailDTO.getIsQualId()
    			,workFmeaDetailDTO.getReviewDate()
    			,workFmeaDetailDTO.getReviewDeptId()
    			,workFmeaDetailDTO.getReviewById()
    			,workFmeaDetailDTO.getReviewComments()
    			,user.getCompNo()
    			,workFmeaDetailDTO.getWoFmeaId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    public int confirmDetail(WorkFmeaDetailDTO workFmeaDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAWOFMEA SET						");
    	query.append("	wofmea_status			= ?				");
    	query.append("WHERE  comp_no			= ?				");
    	query.append("  AND  wofmea_id			= ?				");
    	
    	Object[] objects = new Object[] {
    			"CCA"
    			,user.getCompNo()
    			,workFmeaDetailDTO.getWoFmeaId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    @Override
    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAWOFMEA SET                    ");
        query.append("       wofmea_status   = ?             ");
        query.append("WHERE  wofmea_id       = ?             ");
        query.append("AND  comp_no       = ?             ");
        
        Object[] objects = new Object[] {
                appReqDetailDTO.getParentStatus(),
                appReqDetailDTO.getObjectId(),
                user.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public String getStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT wofmea_status      ");
        query.append("FROM TAWOFMEA      ");
        query.append("WHERE comp_no = ?     ");
        query.append("and wofmea_id = ?       ");
        
        Object[] objects = new Object[] {
                user.getCompNo(),
                appReqDetailDTO.getObjectId()
        };
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }
}