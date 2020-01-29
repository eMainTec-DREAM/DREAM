package dream.req.work.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.req.work.dao.ReqWorkDetailDAO;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkDetailDTO;

/**
 * 작업요청 - 상세 dao
 *
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="reqWorkDetailDAOTarget"
 * @spring.txbn id="reqWorkDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ReqWorkDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements ReqWorkDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     *
     * @param reqWorkCommonDTO
     * @param loginUser
     * @return
     */
    public ReqWorkDetailDTO findDetail(ReqWorkCommonDTO reqWorkCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        String compNo = user.getCompNo();
        String lang = user.getLangId();

        query.append("SELECT									");
        query.append("      x.woreq_id	woReqId					");
        query.append("      ,x.woreq_no	woReqNo					");
        query.append("      ,x.woreq_status woReqStatusId		");
        query.append("		,dbo.SFACODE_TO_DESC(x.woreq_status,'WOREQ_STATUS','SYS','','"+lang+"')	woReqStatusDesc	");
        query.append("      ,x.req_date reqDate					");
        query.append("      ,x.req_emp_id reqEmpId				");
        query.append("      ,x.req_dept_id reqDeptId			");
        query.append("		,(SELECT a.emp_name					");
        query.append("			FROM TAEMP a					");
        query.append("			WHERE a.comp_no = x.comp_no		");
        query.append("			AND a.emp_id = x.req_emp_id		");
        query.append("			)+'/'+						");
        query.append("			(SELECT a.description			");
        query.append("			FROM TADEPT a					");
        query.append("			WHERE a.comp_no = x.comp_no		");
        query.append("			AND a.dept_id = x.req_dept_id	");
        query.append("			)				reqEmpDesc		");
        query.append("      ,x.req_phone reqPhone				");
        query.append("      ,x.req_email reqEmail				");
        query.append("      ,x.equip_id reqEquipId				");
        query.append("		,(SELECT a.item_no  				");
        query.append("			FROM TAEQUIPMENT a				");
        query.append("			WHERE a.comp_no = x.comp_no		");
        query.append("			AND	a.equip_id = x.equip_id)	");
        query.append("							reqEquipNo     	");
        query.append("		,(SELECT a.description				");
        query.append("			FROM TAEQUIPMENT a				");
        query.append("			WHERE a.comp_no = x.comp_no		");
        query.append("			AND	a.equip_id = x.equip_id)	");
        query.append("							reqEquipDesc	");
        query.append("      ,x.eqloc_id reqEqLocId				");
        query.append("		,(SELECT a.full_desc				");
        query.append("			FROM TAEQLOC a					");
        query.append("			WHERE a.comp_no = x.comp_no		");
        query.append("			AND	a.eqloc_id = x.eqloc_id)	");
        query.append("							reqEqLocDesc	");
        query.append("      ,x.description reqDesc				");
        query.append("      ,x.request reqRequest				");
        query.append("      ,x.rec_dept_id  as recDeptId        ");
        query.append("      ,(SELECT a.description              ");
        query.append("            FROM TADEPT a                 ");
        query.append("            WHERE a.comp_no = x.comp_no   ");
        query.append("            AND a.dept_id = x.rec_dept_id ");
        query.append("            )             as recDeptDesc  ");
        query.append("      ,x.rec_wkctr_id as recWkCtrId       ");
        query.append("      ,(SELECT a.description              ");
        query.append("        FROM TAWKCTR a                    ");
        query.append("        WHERE a.comp_no = x.comp_no        ");
        query.append("            AND a.wkctr_id = x.rec_wkctr_id");
        query.append("            )             as recWkCtrDesc ");
        query.append("      ,x.rec_emp_id  as recEmpId          ");
        query.append("      ,(SELECT a.emp_name                 ");
        query.append("            FROM TAEMP a                  ");
        query.append("            WHERE a.comp_no = x.comp_no   ");
        query.append("            AND a.emp_id = x.rec_emp_id   ");
        query.append("            ) as recEmpName               ");
        query.append("      ,x.req_priority reqPriorityId		");
        query.append("		,dbo.SFACODE_TO_DESC(x.req_priority,'REQ_PRIORITY','SYS','','"+lang+"')	reqPriorityDesc	");
        query.append("      ,x.eq_class eqClassId		");
        query.append("		,dbo.SFACODE_TO_DESC(x.eq_class,'EQ_CLASS','SYS','','"+lang+"')	eqClassDesc	");
        query.append("      ,x.req_com_date reqComDate			");
        query.append("      ,x.review review					");
        query.append("      ,x.mo_cd                                                            moCd           		");
        query.append("      ,(SELECT (SELECT b.key_name                                                        		");
        query.append("                        FROM talang b                                                    		");
        query.append("                        WHERE  b.lang = '"+lang+"'                                        	");
        query.append("                        AND aa.key_type = b.key_type                                    		");
        query.append("                        AND aa.key_no = b.key_no) description                            		");
        query.append("           		FROM TAFAILURE aa                                                           ");
        query.append("          		WHERE aa.comp_no = x.comp_no                                                ");
        query.append("            		  AND aa.failure_id = x.mo_cd)                      	moCdDesc            ");
        query.append("      ,x.mo_desc                                                        	moDesc    			");
        query.append("      ,x.invt_categ                                                     	invtCateg			");
        query.append("      ,dbo.SFACODE_TO_DESC(x.invt_categ,'INVT_CATEG','SYS','','"+lang+"') invtCategDesc 		");
        query.append("      ,x.invt_type                                                      	invtType			");
        query.append("      ,dbo.SFACODE_TO_DESC(x.invt_type,'INVT_TYPE','SYS','','"+lang+"')   invtTypeDesc		");
        query.append("      ,x.plant                                                            plantId			 	");
        query.append("      ,(SELECT description                                         							");
        query.append("         FROM TAPLANT                                             							");
        query.append("         WHERE comp_no = x.comp_no                                 							");
        query.append("           AND plant = x.plant)                    						plantDesc   		");
        query.append("		,x.eqdn_start_date													eqDnDate			");
        query.append("		,x.eqdn_start_time													eqDnTime			");
        query.append("      , SUBSTRING(x.req_time, 9, 4)                                    AS reqTime             ");
        query.append("      ,x.req_plant                                                        reqPlantId			");
        query.append("      ,(SELECT description                                         							");
        query.append("        FROM TAPLANT                                             								");
        query.append("        WHERE comp_no = x.comp_no                                 							");
        query.append("          AND plant = x.req_plant)                    					reqPlantDesc   		");
        query.append("		, x.eqasmb_id 														AS REQEQASMBID		");
        query.append("		, (SELECT a.full_desc 																	");
        query.append("			 FROM TAEQASMB a																	");
        query.append("			WHERE a.comp_no   = x.comp_no														");
        query.append("			  AND a.eqasmb_id = x.eqasmb_id) 								AS REQEQASMBDESC	");
        query.append("FROM TAWOREQ x																				");
        query.append("WHERE 1=1																						");
        query.append("    and 	x.comp_no = ?																		");
        query.append("    and 	x.woreq_id = ?																		");
        
        Object[] objects = new Object[] {
        		compNo
        		,reqWorkCommonDTO.getWoReqId()
    	};
        
        ReqWorkDetailDTO resultDTO =
        		(ReqWorkDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new ReqWorkDetailDTO()));

        return resultDTO;
    }

    /**
     * detail update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     *
     * @param reqWorkDetailDTO
     * @return
     */
    public int updateDetail(ReqWorkDetailDTO reqWorkDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAWOREQ SET					");
    	query.append("	     req_phone			= ?			");
    	query.append("	     ,req_email			= ?			");
    	query.append("	     ,eqloc_id			= ?			");
    	query.append("	     ,equip_id			= ?			");
    	query.append("	     ,description		= ?			");
    	query.append("	     ,request			= ?			");
    	query.append("	     ,rec_dept_id		= ?			");
    	query.append("	     ,rec_wkctr_id		= ?			");
    	query.append("	     ,rec_emp_id		= ?			");
    	query.append("	     ,req_priority		= ?			");
    	query.append("	     ,req_com_date		= ?			");
    	query.append("	     ,eq_class			= ?			");
    	query.append("	     ,mo_cd				= ?			");
    	query.append("	     ,mo_desc			= ?			");
    	query.append("	     ,invt_categ		= ?			");
    	query.append("	     ,invt_type			= ?			");
    	query.append("	     ,plant				= ?			");
    	query.append("	     ,eqdn_start_date	= ?			");
    	query.append("	     ,eqdn_start_time	= ?			");
    	query.append("	     ,req_plant			= ?			");
    	query.append("	     ,eqasmb_id			= ?			");
    	query.append("WHERE  woreq_id			= ?			");
    	query.append("  AND  comp_no			= ?			");

    	Object[] objects = new Object[] {
    			reqWorkDetailDTO.getReqPhone()
    			,reqWorkDetailDTO.getReqEmail()
    			,reqWorkDetailDTO.getReqEqLocId()
    			,reqWorkDetailDTO.getReqEquipId()
    			,reqWorkDetailDTO.getReqDesc()
    			,reqWorkDetailDTO.getReqRequest()
    			,reqWorkDetailDTO.getRecDeptId()
    			,reqWorkDetailDTO.getRecWkCtrId()
    			,reqWorkDetailDTO.getRecEmpId()
    			,reqWorkDetailDTO.getReqPriorityId()
    			,reqWorkDetailDTO.getReqComDate()
    			,reqWorkDetailDTO.getEqClassId()
    			,reqWorkDetailDTO.getMoCd()
    			,reqWorkDetailDTO.getMoDesc()
    			,reqWorkDetailDTO.getInvtCateg()
    			,reqWorkDetailDTO.getInvtType()
    			,reqWorkDetailDTO.getPlantId()
    			,reqWorkDetailDTO.getEqDnDate()
    			,reqWorkDetailDTO.getEqDnTime()
    			,reqWorkDetailDTO.getReqPlantId()
    			,reqWorkDetailDTO.getReqEqAsmbId()
    			,reqWorkDetailDTO.getWoReqId()
    			,user.getCompNo()
    	};

    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }

    public int insertDetail(ReqWorkDetailDTO reqWorkDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;

        query.append("INSERT INTO TAWOREQ (											");
        query.append("   comp_no        	, woreq_id      	, woreq_no			");
        query.append("   , description   	, eqloc_id      	, equip_id			");
        query.append("   , woreq_status  	, req_date      	, req_dept_id		");
        query.append("   , req_emp_id    	, req_phone     	, req_email			");
        query.append("   , request       	, rec_dept_id   	, rec_wkctr_id   	");
        query.append("   , rec_emp_id   	, req_priority		, req_com_date    	");
        query.append("   , eq_class      	, plant				, mo_cd          	");
        query.append("   , mo_desc			, woreq_type		, invt_categ		");
        query.append("   , invt_type     	, req_time			, eqdn_start_date	");
        query.append("   , eqdn_start_time	, woreq_channel		, req_plant			");
        query.append("   , eqasmb_id												");
        query.append(")	VALUES (                                     				");
        query.append("     ?				, ?					, ?					");
        query.append("   , ?				, ?					, ?					");
        query.append("   , ?				, ?					, ?					");
        query.append("   , ?				, ?					, ?					");
        query.append("   , ?				, ?					, ?					");
        query.append("   , ?				, ?					, ?					");
        query.append("   , ?				, ?					, ?					");
        query.append("   , ?				, ?					, ?					");
        query.append("   , ?				, ?					, ?					");
        query.append("   , ?				, ?					, ?					");
        query.append("   , ?														");
        query.append(")																");

        Object[] objects = new Object[] {
                 user.getCompNo()
               , reqWorkDetailDTO.getWoReqId()
               , reqWorkDetailDTO.getWoReqNo()
               , reqWorkDetailDTO.getReqDesc()
               , reqWorkDetailDTO.getReqEqLocId()
               , reqWorkDetailDTO.getReqEquipId()
               , reqWorkDetailDTO.getWoReqStatusId()
               , reqWorkDetailDTO.getReqDate()
               , reqWorkDetailDTO.getReqDeptId()
               , reqWorkDetailDTO.getReqEmpId()
               , reqWorkDetailDTO.getReqPhone()
               , reqWorkDetailDTO.getReqEmail()
               , reqWorkDetailDTO.getReqRequest()
               , reqWorkDetailDTO.getRecDeptId()
    		   , reqWorkDetailDTO.getRecWkCtrId()
    		   , reqWorkDetailDTO.getRecEmpId()
    		   , reqWorkDetailDTO.getReqPriorityId()
    		   , reqWorkDetailDTO.getReqComDate()
    		   , reqWorkDetailDTO.getEqClassId()
    		   , reqWorkDetailDTO.getPlantId()
    		   , reqWorkDetailDTO.getMoCd()
    		   , reqWorkDetailDTO.getMoDesc()
    		   , reqWorkDetailDTO.getWoReqType()
    		   , reqWorkDetailDTO.getInvtCateg()
    		   , reqWorkDetailDTO.getInvtType()
    		   , DateUtil.getDateTime()
    		   , reqWorkDetailDTO.getEqDnDate()
    		   , reqWorkDetailDTO.getEqDnTime()
    		   , reqWorkDetailDTO.getReqChannelId()
    		   , reqWorkDetailDTO.getReqPlantId()
    		   , reqWorkDetailDTO.getReqEqAsmbId()
        };
        rtnValue = getJdbcTemplate().update(query.toString(), getObject(objects));

        return rtnValue;
    }

    @Override
    public int updateStatus(ReqWorkDetailDTO reqWorkDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE TAWOREQ SET                    ");
        query.append("       woreq_status       = 'REQ'     ");
        query.append("       ,req_date          = ?         ");
        query.append("       ,req_time          = ?         ");
        query.append("WHERE  woreq_id           = ?         ");
        query.append("  AND  comp_no            = ?         ");

        Object[] objects = new Object[] {
        		DateUtil.getDate()
        		,DateUtil.getDateTime()
                ,reqWorkDetailDTO.getWoReqId()
                ,user.getCompNo()
        };

        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAWOREQ SET                    ");
        query.append("       woreq_status   = ?             ");
        query.append("       ,req_date          = ?         ");
        query.append("       ,req_time          = ?         ");
        query.append("WHERE  woreq_id       = ?             ");
        query.append("  AND  comp_no            = ?         ");
        
        Object[] objects = new Object[] {
        		appReqDetailDTO.getParentStatus()
        		,DateUtil.getDate()
                ,DateUtil.getDateTime()
        		,appReqDetailDTO.getObjectId()
        		,user.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
}