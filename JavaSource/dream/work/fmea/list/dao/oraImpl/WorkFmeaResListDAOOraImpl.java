package dream.work.fmea.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.fmea.list.dao.WorkFmeaResListDAO;
import dream.work.fmea.list.dto.WorkFmeaResCommonDTO;

/**
 * 고장영향성평가 - List DAO implements
 * @author kim21017
 * @version $Id: WorkFmeaResListDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workFmeaResListDAOTarget"
 * @spring.txbn id="workFmeaResListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkFmeaResListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkFmeaResListDAO
{
	public List findList(WorkFmeaResCommonDTO workFmeaResCommonDTO, User user) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT																			");
        query.append("		''														AS seqNo			");
        query.append("		,''														AS isDelCheck		");
        query.append("		,x.wofmea_id											AS woFmeaId			");
        query.append("		,x.wkor_id												AS wkorId			");
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
        query.append("		,x.req_dept												AS reqDeptId		");
        query.append("		,(SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no			");
        query.append("			AND a.dept_id = x.req_dept)							AS reqDeptDesc		");
        query.append("		,x.req_by												AS reqById			");
        query.append("		,(SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no				");
        query.append("			AND a.emp_id = x.req_by)							AS reqByDesc		");
        query.append("		,x.situation											AS situation		");
        query.append("		,x.repair												AS repair			");
        query.append("		,x.wofmea_status										AS woFmeaStatusId	");
        query.append("		,SFACODE_TO_DESC(x.wofmea_status,'WOFMEA_STATUS','SYS',''					");
        query.append("							,'"+user.getLangId()+"') 			AS woFmeaStatusDesc	");
        query.append("		,x.fmea_priority										AS fmeaPriorityId	");
        query.append("		,SFACODE_TO_DESC(x.fmea_priority,'FMEA_PRIORITY','SYS',''					");
        query.append("							,'"+user.getLangId()+"') 			AS fmeaPriorityDesc	");
        query.append("		,x.fmea_wotype											AS fmeaWoTypeId		");
        query.append("		,SFACODE_TO_DESC(x.fmea_wotype,'FMEA_WOTYPE','SYS',''						");
        query.append("							,'"+user.getLangId()+"') 			AS fmeaWoTypeDesc	");
        query.append("		,x.iscalib												AS isCalibId		");
        query.append("		,SFACODE_TO_DESC(x.iscalib,'IS_USE','SYS',''								");
        query.append("							,'"+user.getLangId()+"') 			AS isCalibDesc		");
        query.append("		,x.isqual												AS isQualId			");
        query.append("		,SFACODE_TO_DESC(x.isqual,'IS_USE','SYS',''									");
        query.append("							,'"+user.getLangId()+"') 			AS isQualDesc		");
        query.append("		,x.review_comments										AS reviewComments	");
        query.append("		,x.review_date											AS reviewDate		");
        query.append("		,x.review_dept											AS reviewDeptId		");
        query.append("		,(SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no			");
        query.append("			AND a.dept_id = x.review_dept)						AS reviewDeptDesc	");
        query.append("		,x.review_by											AS reviewById		");
        query.append("		,(SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no				");
        query.append("			AND a.emp_id = x.review_by)							AS reviewByDesc		");
        query.append("FROM TAWOFMEA x																	");
    	query.append("WHERE  1=1																		");
    	query.append("    AND wofmea_status != 'WRT'													");
    	query.append(this.getWhere(workFmeaResCommonDTO, user));
        query.getOrderByQuery("x.wofmea_no", workFmeaResCommonDTO.getOrderBy(), workFmeaResCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workFmeaResCommonDTO.getIsLoadMaxCount(), workFmeaResCommonDTO.getFirstRow()));
    } 

	private String getWhere(WorkFmeaResCommonDTO workFmeaResCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        if(!"".equals(workFmeaResCommonDTO.getWoFmeaId())){
        	query.getAndNumKeyQuery("x.wofmea_id", workFmeaResCommonDTO.getWoFmeaId());
        	return query.toString();
        }
        //의뢰일자
        query.getAndDateQuery("x.req_date", workFmeaResCommonDTO.getFilterReqStartDate(), workFmeaResCommonDTO.getFilterReqEndDate());
        //상태
        query.getSysCdQuery("x.wofmea_status", workFmeaResCommonDTO.getFilterWoFmeaStatusId(), workFmeaResCommonDTO.getFilterWoFmeaStatusDesc(), "WOFMEA_STATUS", user.getCompNo(),user.getLangId());
        //의뢰부서
        query.getDeptLevelQuery("x.req_dept", workFmeaResCommonDTO.getFilterReqDeptId(), workFmeaResCommonDTO.getFilterReqDeptDesc(), user.getCompNo());
        //의뢰자
        query.getCodeLikeQuery("x.req_by", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.req_by AND a.comp_no='"+user.getCompNo()+"')", 
        		workFmeaResCommonDTO.getFilterReqById(), workFmeaResCommonDTO.getFilterReqByDesc());
        //접수번호
        query.getStringEqualQuery("x.wofmea_no", workFmeaResCommonDTO.getFilterWoFmeaNo());
        //설비
        query.getCodeLikeQuery("x.equip_id", "(SELECT a.description FROM TAEQUIPMENT a WHERE a.equip_id = x.equip_id AND a.comp_no='"+user.getCompNo()+"')", 
        		workFmeaResCommonDTO.getFilterEquipId(), workFmeaResCommonDTO.getFilterEquipDesc());
        //검토일자
        query.getAndDateQuery("x.review_date", workFmeaResCommonDTO.getFilterReviewStartDate(), workFmeaResCommonDTO.getFilterReviewEndDate());
        //검토자
        query.getCodeLikeQuery("x.review_by", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.review_by AND a.comp_no='"+user.getCompNo()+"')", 
        		workFmeaResCommonDTO.getFilterReviewById(), workFmeaResCommonDTO.getFilterReviewByDesc());
        //영향성평가
    	query.getSysCdQuery("x.fmea_priority", workFmeaResCommonDTO.getFilterFmeaPriorityId(), workFmeaResCommonDTO.getFilterFmeaPriorityDesc(), "FMEA_PRIORITY", user.getCompNo(),user.getLangId());
    	//작업구분
    	query.getSysCdQuery("x.fmea_wotype", workFmeaResCommonDTO.getFilterFmeaWoTypeId(), workFmeaResCommonDTO.getFilterFmeaWoTypeDesc(), "FMEA_WOTYPE", user.getCompNo(),user.getLangId());
    	//Calibration 여부
    	query.getSysCdQuery("x.iscalib", workFmeaResCommonDTO.getFilterIsCalibId(), workFmeaResCommonDTO.getFilterIsCalibDesc(), "IS_USE", user.getCompNo(),user.getLangId());
    	//Qualification 여부
    	query.getSysCdQuery("x.isqual", workFmeaResCommonDTO.getFilterIsQualId(), workFmeaResCommonDTO.getFilterIsQualDesc(), "IS_USE", user.getCompNo(),user.getLangId());
        
        return query.toString();
    }

    public int deleteList(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAWOFMEA					");
        query.append("WHERE  comp_no 		= ?				");
        query.append("  AND  wofmea_id		= ?				");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
        	};
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(WorkFmeaResCommonDTO workFmeaResCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT						");
        query.append("		COUNT(1)				");
        query.append("FROM TAWOFMEA x				");
    	query.append("WHERE  1=1					");
    	query.append(this.getWhere(workFmeaResCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}