package dream.req.work.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.req.work.dao.MaWoReqDetailDAO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqDetailDTO;

/**
 * 작업요청 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="maWoReqDetailDAOTarget"
 * @spring.txbn id="maWoReqDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoReqDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWoReqDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoReqCommonDTO
     * @param loginUser
     * @return
     */
    public MaWoReqDetailDTO findDetail(MaWoReqCommonDTO maWoReqCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        String compNo = user.getCompNo();
        String lang = user.getLangId();

        query.append("SELECT									");
        query.append("      x.woreq_id	woReqId					");
        query.append("      ,x.woreq_no	woReqNo					");
        query.append("      ,x.woreq_status woReqStatusId		");
        query.append("		,SFACODE_TO_DESC(x.woreq_status,'WOREQ_STATUS','SYS','','"+lang+"')	woReqStatusDesc	");
        query.append("      ,x.req_date reqDate					");
        query.append("      ,SUBSTR(x.req_time, 9, 4) AS reqTime");
        query.append("      ,x.req_emp_id reqEmpId				");
        query.append("      ,x.req_dept_id reqDeptId			");
        query.append("		,(SELECT a.emp_name					");
        query.append("			FROM TAEMP a					");
        query.append("			WHERE a.comp_no = x.comp_no		");
        query.append("			AND a.emp_id = x.req_emp_id		");
        query.append("			)||'/'||						");
        query.append("			(SELECT a.description			");
        query.append("			FROM TADEPT a					");
        query.append("			WHERE a.comp_no = x.comp_no		");
        query.append("			AND a.dept_id = x.req_dept_id	");
        query.append("			)				reqEmpDesc		");
        query.append("      ,(SELECT a.description              ");
        query.append("            FROM TADEPT a                 ");
        query.append("            WHERE a.comp_no = x.comp_no   ");
        query.append("            AND a.dept_id = x.req_dept_id ");
        query.append("            )             as REqDEPTDESC  ");
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
        query.append("		,SFACODE_TO_DESC(x.req_priority,'REQ_PRIORITY','SYS','','"+lang+"')	reqPriorityDesc	");
        query.append("      ,x.eq_class eqClassId		");
        query.append("		,SFACODE_TO_DESC(x.eq_class,'EQ_CLASS','SYS','','"+lang+"')			eqClassDesc		");
        query.append("      ,x.req_com_date reqComDate			");
        query.append("      ,x.review review					");
        query.append("      ,x.invt_categ                                                     	invtCateg		");
        query.append("      ,SFACODE_TO_DESC(x.invt_categ,'INVT_CATEG','SYS','','"+lang+"')    	invtCategDesc 	");
        query.append("      ,x.invt_type                                                      	invtType		");
        query.append("      ,SFACODE_TO_DESC(x.invt_type,'INVT_TYPE','SYS','','"+lang+"')    	invtTypeDesc	");
        query.append("      ,x.mo_cd                                                            moCd           	");
        query.append("      ,(SELECT (SELECT b.key_name                                                        	");
        query.append("                        FROM talang b                                                    	");
        query.append("                        WHERE  b.lang = '"+lang+"'                                        ");
        query.append("                        AND aa.key_type = b.key_type                                    	");
        query.append("                        AND aa.key_no = b.key_no) description                            	");
        query.append("           		FROM TAFAILURE aa                                                       ");
        query.append("          		WHERE aa.comp_no = x.comp_no                                            ");
        query.append("            		  AND aa.failure_id = x.mo_cd)                      	moCdDesc        ");
        query.append("      ,x.mo_desc                                                        	moDesc    		");
        query.append("      ,x.plant                                                            plantId			");
        query.append("      ,(SELECT description                                         						");
        query.append("         FROM TAPLANT                                             						");
        query.append("         WHERE comp_no = x.comp_no                                 						");
        query.append("           AND plant = x.plant)                    						plantDesc   	");
        query.append("		,x.eqdn_start_date													eqDnDate		");
        query.append("		,x.eqdn_start_time													eqDnTime		");
        query.append("		,x.woreq_channel																	REQCHANNELID	");
        query.append("      ,SFACODE_TO_DESC(x.woreq_channel,'WOREQ_CHANNEL','SYS','"+ compNo +"','"+lang+"')	REQCHANNELDESC	");
        query.append("		, x.eqasmb_id 														AS REQEQASMBID		");
        query.append("		, (SELECT a.full_desc 																	");
        query.append("			 FROM TAEQASMB a																	");
        query.append("			WHERE a.comp_no   = x.comp_no														");
        query.append("			  AND a.eqasmb_id = x.eqasmb_id) 								AS REQEQASMBDESC	");
        query.append("FROM TAWOREQ x																			");
        query.append("WHERE 1=1																					");
        query.append("    and 	x.comp_no = ?																	");
        query.append("    and 	x.woreq_id = ?																	");
        
        Object[] objects = new Object[] {
        		compNo
        		,maWoReqCommonDTO.getWoReqId()
    	};
        
        MaWoReqDetailDTO resultDTO = 
        		(MaWoReqDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MaWoReqDetailDTO()));
        
        return resultDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoReqDetailDTO
     * @return
     */
    public int updateDetail(MaWoReqDetailDTO maWoReqDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAWOREQ SET					");
    	query.append("	     req_phone			= ?			");
    	query.append("	     ,req_email			= ?			");
    	query.append("	     ,eqloc_id			= ?			");
    	query.append("	     ,equip_id			= ?			");
    	query.append("	     ,description		= ?			");
    	query.append("	     ,request			= ?			");
    	query.append("	     ,req_dept_id		= ?			");
    	query.append("	     ,req_emp_id		= ?			");
    	query.append("	     ,rec_dept_id		= ?			");
    	query.append("	     ,rec_wkctr_id		= ?			");
    	query.append("	     ,rec_emp_id		= ?			");
    	query.append("	     ,review			= ?			");
    	query.append("	     ,invt_categ		= ?			");
    	query.append("	     ,invt_type			= ?			");
    	query.append("	     ,mo_cd				= ?			");
    	query.append("	     ,mo_desc			= ?			");
    	query.append("	     ,req_priority		= ?			");
    	query.append("	     ,plant				= ?			");
    	query.append("	     ,eqdn_start_date	= ?			");
    	query.append("	     ,eqdn_start_time	= ?			");
    	query.append("	     ,woreq_status  	= ?			");
    	query.append("	     ,woreq_channel  	= ?			");
    	query.append("	     ,req_date		    = ?			");
    	query.append("	     ,eqasmb_id		    = ?			");
    	query.append("WHERE  woreq_id			= ?			");
    	query.append("  AND  comp_no			= ?			");
    	
    	Object[] objects = new Object[] {
    			maWoReqDetailDTO.getReqPhone()
    			,maWoReqDetailDTO.getReqEmail()
    			,maWoReqDetailDTO.getReqEqLocId()
    			,maWoReqDetailDTO.getReqEquipId()
    			,maWoReqDetailDTO.getReqDesc()
    			,maWoReqDetailDTO.getReqRequest()
    			,maWoReqDetailDTO.getReqDeptId()
    			,maWoReqDetailDTO.getReqEmpId()
    			,maWoReqDetailDTO.getRecDeptId()
    			,maWoReqDetailDTO.getRecWkCtrId()
    			,maWoReqDetailDTO.getRecEmpId()
    			,maWoReqDetailDTO.getReview()
    			,maWoReqDetailDTO.getInvtCateg()
    			,maWoReqDetailDTO.getInvtType()
    			,maWoReqDetailDTO.getMoCd()
    			,maWoReqDetailDTO.getMoDesc()
    			,maWoReqDetailDTO.getReqPriorityId()
    			,maWoReqDetailDTO.getPlantId()
    			,maWoReqDetailDTO.getEqDnDate()
    			,maWoReqDetailDTO.getEqDnTime()
    			,maWoReqDetailDTO.getWoReqStatusId()
    			,maWoReqDetailDTO.getReqChannelId()
    			,maWoReqDetailDTO.getReqDate()
    			,maWoReqDetailDTO.getReqEqAsmbId()
    			,maWoReqDetailDTO.getWoReqId()
    			,user.getCompNo()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }

    public int insertDetail(MaWoReqDetailDTO maWoReqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        
        query.append("INSERT INTO TAWOREQ (													");
        query.append("   	  	  comp_no       	, woreq_id       	, woreq_no			");
        query.append("   		, description   	, eqloc_id       	, equip_id			");
        query.append("   		, woreq_status		, req_date       	, req_dept_id		");
        query.append("   		, req_emp_id    	, req_phone      	, req_email			");
        query.append("   		, request       	, rec_dept_id    	, rec_wkctr_id   	");
        query.append("   		, rec_emp_id    	, review         	, plant          	");
        query.append("   		, invt_categ		, invt_type			, mo_cd         	");
        query.append("   		, mo_desc			, req_priority  	, eqdn_start_date	");
        query.append("   		, eqdn_start_time	, woreq_channel		, eqasmb_id			");
        query.append(") VALUES (                                     						");
        query.append("   		  ?					, ?					, ?					");
        query.append("   		, ?					, ?					, ?					");
        query.append("   		, ?					, ?					, ?					");
        query.append("   		, ?					, ?					, ?					");
        query.append("   		, ?					, ?					, ?					");
        query.append("   		, ?					, ?					, ?					");
        query.append("   		, ?					, ?					, ?					");
        query.append("   		, ?					, ?					, ?					");
        query.append("   		, ?					, ?					, ?					");
        query.append(")																		");
        
        Object[] objects = new Object[] {
        		 user.getCompNo()
               , maWoReqDetailDTO.getWoReqId()
               , maWoReqDetailDTO.getWoReqNo()
               , maWoReqDetailDTO.getReqDesc()
               , maWoReqDetailDTO.getReqEqLocId()
               , maWoReqDetailDTO.getReqEquipId()
               , maWoReqDetailDTO.getWoReqStatusId()
               , maWoReqDetailDTO.getReqDate()
               , maWoReqDetailDTO.getReqDeptId()
               , maWoReqDetailDTO.getReqEmpId()
               , maWoReqDetailDTO.getReqPhone()
               , maWoReqDetailDTO.getReqEmail()
               , maWoReqDetailDTO.getReqRequest()
               , maWoReqDetailDTO.getRecDeptId()
       		   , maWoReqDetailDTO.getRecWkCtrId()
       		   , maWoReqDetailDTO.getRecEmpId()
       		   , maWoReqDetailDTO.getReview()
       		   , maWoReqDetailDTO.getPlantId()
       		   , maWoReqDetailDTO.getInvtCateg()
       		   , maWoReqDetailDTO.getInvtType()
       		   , maWoReqDetailDTO.getMoCd()
       		   , maWoReqDetailDTO.getMoDesc()
       		   , maWoReqDetailDTO.getReqPriorityId()
       		   , maWoReqDetailDTO.getEqDnDate()
       		   , maWoReqDetailDTO.getEqDnTime()
       		   , maWoReqDetailDTO.getReqChannelId()
       		   , maWoReqDetailDTO.getReqEqAsmbId()
        };
        rtnValue = getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }

    @Override
    public int updateStatus(MaWoReqDetailDTO maWoReqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("UPDATE TAWOREQ SET                    ");
        query.append("       woreq_status          = 'REC'  ");
        query.append("       ,REC_DATE          = ?         ");
        query.append("       ,REC_TIME          = ?         ");
        query.append("       ,REC_EMP_ID		= ?         ");
        query.append("       ,REC_DEPT_ID		= ?         ");
        query.append("WHERE  woreq_id           = ?         ");
        query.append("  AND  comp_no            = ?         ");

        Object[] objects = new Object[] {
        		DateUtil.getDate()
        		,DateUtil.getDateTime()
        		,maWoReqDetailDTO.getRecEmpId()
        		,maWoReqDetailDTO.getRecDeptId()
                ,maWoReqDetailDTO.getWoReqId()
                ,user.getCompNo()
        }; 

        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    @Override
    public int updateIncStatus(MaWoReqDetailDTO maWoReqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("UPDATE TAWOREQ SET                    ");
        query.append("       woreq_status      = 'INC'      ");
        query.append("       ,REC_DATE          = ?         ");
        query.append("       ,REC_TIME          = ?         ");
        query.append("       ,REC_EMP_ID		= ?         ");
        query.append("       ,REC_DEPT_ID		= ?         ");
        query.append("WHERE  woreq_id           = ?         ");
        query.append("  AND  comp_no            = ?         ");

        Object[] objects = new Object[] {
        		DateUtil.getDate()
        		,DateUtil.getDateTime()
        		,maWoReqDetailDTO.getRecEmpId()
        		,maWoReqDetailDTO.getRecDeptId()
                ,maWoReqDetailDTO.getWoReqId()
                ,user.getCompNo()
        };

        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    @Override
    public int updateReqStatus(MaWoReqDetailDTO maWoReqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("UPDATE TAWOREQ SET                    ");
        query.append("       woreq_status       = 'REQ'     ");
        query.append("     , rec_date           = null      ");
        query.append("     , rec_time           = null      ");
        query.append("     , rec_emp_id         = null      ");
        query.append("     , rec_dept_id        = null      ");
        query.append("WHERE  woreq_id           = ?         ");
        query.append("  AND  comp_no            = ?         ");

        Object[] objects = new Object[] {
                 maWoReqDetailDTO.getWoReqId()
               , user.getCompNo()
        };

        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    public String getRecBy(MaWoReqDetailDTO maWoReqDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

        query.append("SELECT rec_emp_id recEmpId						");
        query.append("FROM TAWOREQ x									");
        query.append("WHERE  x.woreq_id           = ?         			");
        query.append("  AND  x.comp_no            = ?         			");

        Object[] objects = new Object[] {
                maWoReqDetailDTO.getWoReqId()
                ,user.getCompNo()
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
    }
    
    @Override
    public String checkValidRecDept(MaWoReqDetailDTO maWoReqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT CASE WHEN COUNT(1) > 0 THEN 'Y'  ");
        query.append("            ELSE 'N' END                ");
        query.append("FROM TADEPT x                           ");
        query.append("WHERE 1 = 1                             ");
        query.append("AND x.comp_no = ?                       ");
        query.append("AND x.dept_id = ?                       ");
        query.append("START WITH x.dept_id = ?                ");
        query.append("CONNECT BY PRIOR x.p_dept_id = x.dept_id");
        
        Object[] objects = new Object[] {
                user.getCompNo()
                ,user.getDeptId()
                ,maWoReqDetailDTO.getRecDeptId()
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
    }
    
    public List findWoRecReport(MaWoReqDetailDTO maWoReqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        String compNo = user.getCompNo();
        String lang = user.getLangId();

        query.append("SELECT                                    ");
        query.append("      x.woreq_no  WOREQNO                 ");
        query.append("      ,x.req_date REQDATE                 ");
        query.append("      ,(SELECT a.description              ");
        query.append("      FROM TADEPT a                       ");
        query.append("      WHERE a.comp_no = x.comp_no         ");
        query.append("      AND a.dept_id = x.req_dept_id       ");
        query.append("      )               REQDEPT             ");
        query.append("      ,(SELECT a.emp_name                 ");
        query.append("      FROM TAEMP a                        ");
        query.append("      WHERE a.comp_no = x.comp_no         ");
        query.append("      AND a.emp_id = x.req_emp_id         ");
        query.append("      )               REQBY               ");
        query.append("      ,x.req_phone        REQPHONE        ");
        query.append("      ,x.req_email        REQEMAIL        ");
        query.append("      ,x.req_com_date RECDATE                 ");
        query.append("      ,SFACODE_TO_DESC(x.req_priority,'REQ_PRIORITY','SYS','','"+lang+"') REQPRIORITY ");
        query.append("      ,(SELECT a.description              ");
        query.append("      FROM TADEPT a                       ");
        query.append("      WHERE a.comp_no = x.comp_no         ");
        query.append("      AND a.dept_id = x.rec_dept_id       ");
        query.append("      )               RECDEPT             ");
        query.append("      ,(SELECT a.emp_name                 ");
        query.append("      FROM TAEMP a                        ");
        query.append("      WHERE a.comp_no = x.comp_no         ");
        query.append("      AND a.emp_id = x.rec_emp_id         ");
        query.append("      )               RECBY               ");
        query.append("      ,(SELECT a.full_desc                ");
        query.append("          FROM TAEQLOC a                  ");
        query.append("          WHERE a.comp_no = x.comp_no     ");
        query.append("          AND a.eqloc_id = x.eqloc_id)    ");
        query.append("                          EQLOC           ");
        query.append("      ,(SELECT a.description              ");
        query.append("          FROM TAEQUIPMENT a              ");
        query.append("          WHERE a.comp_no = x.comp_no     ");
        query.append("          AND a.equip_id = x.equip_id)    ");
        query.append("                          EQUIP           ");
        query.append("      ,x.description DESCRIPTION          ");
        query.append("      ,x.request REQUEST                  ");
        query.append("      ,x.review RESPONSE                  ");
        query.append("      ,x.plant            plantId         ");
        query.append("      ,(SELECT description                ");
        query.append("        FROM TAPLANT                      ");
        query.append("        WHERE comp_no = x.comp_no         ");
        query.append("         AND plant = x.plant) plantDesc   ");
        query.append("FROM TAWOREQ x                            ");
        query.append("WHERE 1=1                                 ");
        query.append("    and   x.comp_no = ?                   ");
        query.append("    and   x.woreq_id = ?                  ");
        
        Object[] objects = new Object[] {
                compNo
                ,maWoReqDetailDTO.getWoReqId()
        };
        
        return getJdbcTemplate().queryForList(query.toString(),objects);
    }
    
    @Override
    public String chkWoExistCnt(String woReqId, User user, String method)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" SELECT                                    ");
        query.append("        COUNT(1)             AS count      ");
        query.append("   FROM TAWOREQRES x                       ");
        query.append("   INNER JOIN TAWORKORDER y                ");
        query.append("          ON x.comp_no     = y.comp_no     ");
        query.append("         AND x.wkor_id     = y.wkor_id     ");
        query.append("  WHERE  x.comp_no         = ?             ");
        query.append("    AND  x.woreq_id        = ?             ");
        query.append("    AND  x.woreqres_method = ?             ");
        
        Object[] objects = new Object[] {
                 user.getCompNo()
               , woReqId
               , method
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
    }
    
    @Override
    public String chkInvtExistCnt(String woReqId, User user, String method)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" SELECT                                    ");
        query.append("        COUNT(1)             AS count      ");
        query.append("   FROM TAWOREQRES x                       ");
        query.append("   INNER JOIN TAINVTLIST y                 ");
        query.append("          ON x.comp_no     = y.comp_no     ");
        query.append("         AND x.invtlist_id = y.invtlist_id ");
        query.append("  WHERE  x.comp_no         = ?             ");
        query.append("    AND  x.woreq_id        = ?             ");
        query.append("    AND  x.woreqres_method = ?             ");
        
        Object[] objects = new Object[] {
                 user.getCompNo()
               , woReqId
               , method
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
    }
    
    @Override
    public String chkWoStCnt(String woReqId, User user, String method)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" SELECT                                    ");
        query.append("        COUNT(1)             AS count      ");
        query.append("   FROM TAWOREQRES x                       ");
        query.append("  INNER JOIN TAWORKORDER y                 ");
        query.append("          ON x.comp_no     = y.comp_no     ");
        query.append("         AND x.wkor_id     = y.wkor_id     ");
        query.append("  WHERE  x.comp_no         = ?             ");
        query.append("    AND  x.woreq_id        = ?             ");
        query.append("    AND  x.woreqres_method = ?             ");
        query.append("    AND  y.wo_status      != 'C'           ");
        
        Object[] objects = new Object[] {
                 user.getCompNo()
               , woReqId
               , method
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
    }
    
    @Override
    public String chkInvtStCnt(String woReqId, User user, String method)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" SELECT                                    ");
        query.append("        COUNT(1)             AS count      ");
        query.append("   FROM TAWOREQRES x                       ");
        query.append("  INNER JOIN TAINVTLIST y                  ");
        query.append("          ON x.comp_no     = y.comp_no     ");
        query.append("         AND x.invtlist_id = y.invtlist_id ");
        query.append("  WHERE  x.comp_no         = ?             ");
        query.append("    AND  x.woreq_id        = ?             ");
        query.append("    AND  x.woreqres_method = ?             ");
        query.append("    AND  y.invtlist_status != 'C'          ");
        
        Object[] objects = new Object[] {
                 user.getCompNo()
               , woReqId
               , method
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
    }
    @Override
    public int changeHdrStatus(String woReqId, String changeStatus, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("UPDATE TAWOREQ SET                ");
        query.append("  woreq_status            = ?     ");
        query.append("WHERE woreq_id            = ?     ");
        query.append("  AND comp_no             = ?     ");
        
        Object[] objects = new Object[] {
                 changeStatus
               , woReqId
               , user.getCompNo()
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
}