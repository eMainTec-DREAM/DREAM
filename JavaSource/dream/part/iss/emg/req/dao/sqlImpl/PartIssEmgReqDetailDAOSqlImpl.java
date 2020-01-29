package dream.part.iss.emg.req.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.part.iss.emg.req.dao.PartIssEmgReqDetailDAO;
import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;
import dream.part.iss.emg.req.dto.PartIssEmgReqDetailDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * 출고요청 - Detail DAO implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="partIssEmgReqDetailDAOTarget"
 * @spring.txbn id="partIssEmgReqDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartIssEmgReqDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements PartIssEmgReqDetailDAO
{
	
    public PartIssEmgReqDetailDTO findIssReqDetail(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO, User user) 
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("SELECT                                                                                                                                ");
        query.append("    a.ptemgissreq_id                                                                                      AS ptEmgIssReqId            ");
        query.append("    ,a.ptemgissreq_no                                                                                     AS ptEmgIssReqNo            ");
        query.append("    ,a.ptemgissreq_status                                                                                 AS ptEmgIssReqStatus        ");
        query.append("    ,dbo.SFACODE_TO_DESC(a.ptemgissreq_status,'PTEMGISSREQ_STATUS','SYS','',?)                            AS ptEmgIssReqStatusDesc    ");
        query.append("    ,a.req_date                                                                                             AS reqDate                ");
        query.append("    ,a.req_by                                                                                               AS reqBy                  ");
        query.append("    ,a.req_dept                                                                                             AS reqDept                ");
        query.append("    ,a.wcode_id                                                                                           AS wcodeId                  ");
        query.append("    ,(SELECT wname FROM TAWAREHOUSE aa                                                                                                ");
        query.append("        WHERE aa.comp_no=a.comp_no AND aa.wcode_id=a.wcode_id)                                              AS wcodeName              ");
        query.append("    ,a.to_wcode_id                                                                                          AS toWcodeId              ");
        query.append("    ,(SELECT wname FROM TAWAREHOUSE aa                                                                                                ");
        query.append("        WHERE aa.comp_no=a.comp_no AND aa.wcode_id=a.to_wcode_id)                                           AS toWcodeName            ");
        query.append("    ,a.ctctr_id                                                                                             AS ctctrId                ");
        query.append("    ,(SELECT description FROM TACTCTR aa                                                                                              ");
        query.append("        WHERE aa.comp_no=a.comp_no AND aa.ctctr_id=a.ctctr_id)                                              AS ctctrDesc              ");
        query.append("    ,a.rec_by                                                                                               AS recBy                  ");
        query.append("    ,(SELECT emp_name FROM TAEMP aa                                                                                                   ");
        query.append("        WHERE aa.comp_no=a.comp_no AND aa.emp_id=a.rec_by)                                                  AS recByDesc              ");
        query.append("    ,a.equip_id                                                                                             AS equipId                ");
        query.append("    ,(SELECT description FROM TAEQUIPMENT aa                                                                                          ");
        query.append("        WHERE aa.comp_no=a.comp_no AND aa.equip_id=a.equip_id)                                              AS equipDesc              ");
        query.append("    ,a.plant                                                                                                AS plantId                ");
        query.append("    ,(SELECT description FROM TAPLANT aa                                                                                              ");
        query.append("        WHERE aa.comp_no=a.comp_no AND aa.plant=a.plant)                                                    AS plantDesc              ");
        query.append("    ,a.remark                                                                                               AS remark                 ");
        query.append("FROM TAPTEMGISSREQ a                                                                                                                  ");
        query.append("WHERE  1=1                                                                                                                            ");
        query.append("AND    a.ptemgissreq_id = ?                                                                                                           ");
        query.append("AND    comp_no    = ?                                                                                                                 ");
        
        Object[] objects = new Object[] {
                user.getLangId()
                ,partIssEmgReqCommonDTO.getPtEmgIssReqId()
                ,user.getCompNo()
        };
    
        PartIssEmgReqDetailDTO partIssEmgReqDetailDTO = 
        		(PartIssEmgReqDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new PartIssEmgReqDetailDTO()));
        
        return partIssEmgReqDetailDTO;
        
    }
    

    public int insertIssReqDetail(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TAPTEMGISSREQ(    ");
        query.append("  comp_no                     ");
        query.append("  ,ptemgissreq_id             ");
        query.append("  ,ptemgissreq_no             ");
        query.append("  ,ptemgissreq_status         ");
        query.append("  ,req_date                   ");
        query.append("  ,req_by                     ");
        query.append("  ,req_dept                   ");
        query.append("  ,wcode_id                   ");
        query.append("  ,to_wcode_id                ");
        query.append("  ,ctctr_id                   ");
        query.append("  ,rec_by                     ");
        query.append("  ,equip_id                   ");
        query.append("  ,plant                      ");
        query.append("  ,remark                     ");
        query.append("  ,cre_date                   ");
        query.append("  ,cre_by                     ");
        query.append("  ,upd_date                   ");
        query.append("  ,upd_by                     ");
        query.append(")VALUES(                      ");
        query.append("  ?                           ");
        query.append("  ,?                          ");
        query.append("  ,?                          ");
        query.append("  ,?                          ");
        query.append("  ,?                          ");
        query.append("  ,?                          ");
        query.append("  ,?                          ");
        query.append("  ,?                          ");
        query.append("  ,?                          ");
        query.append("  ,?                          ");
        query.append("  ,?                          ");
        query.append("  ,?                          ");
        query.append("  ,?                          ");
        query.append("  ,?                          ");
        query.append("  ,?                          ");
        query.append("  ,?                          ");
        query.append("  ,?                          ");
        query.append("  ,?                          ");
        query.append(")                             ");
        
        Object[] objects = new Object[] {
                 user.getCompNo()
                 ,partIssEmgReqDetailDTO.getPtEmgIssReqId()
                 ,partIssEmgReqDetailDTO.getPtEmgIssReqNo()
                 ,partIssEmgReqDetailDTO.getPtEmgIssReqStatus()
                 ,partIssEmgReqDetailDTO.getReqDate()
                 ,partIssEmgReqDetailDTO.getReqBy()
                 ,partIssEmgReqDetailDTO.getReqDept()
                 ,partIssEmgReqDetailDTO.getWcodeId()
                 ,partIssEmgReqDetailDTO.getToWcodeId()
                 ,partIssEmgReqDetailDTO.getCtctrId()
                 ,partIssEmgReqDetailDTO.getRecBy()
                 ,partIssEmgReqDetailDTO.getEquipId()
                 ,partIssEmgReqDetailDTO.getPlantId()
                 ,partIssEmgReqDetailDTO.getRemark()
                 ,DateUtil.getDateTime()
                 ,user.getUserId()
                 ,DateUtil.getDateTime()
                 ,user.getUserId()
        };
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
    
    
    
    public int updateIssReqDetail(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAPTEMGISSREQ SET                  ");
        query.append("  req_date            = ?                 ");
        query.append("  ,req_by             = ?                 ");
        query.append("  ,req_dept           = ?                 ");
        query.append("  ,wcode_id           = ?                 ");
        query.append("  ,to_wcode_id        = ?                 ");
        query.append("  ,ctctr_id           = ?                 ");
        query.append("  ,rec_by             = ?                 ");
        query.append("  ,equip_id           = ?                 ");
        query.append("  ,plant              = ?                 ");
        query.append("  ,remark             = ?                 ");
        query.append("  ,upd_date           = ?                 ");
        query.append("  ,upd_by             = ?                 ");
        query.append("WHERE  comp_no        = ?                 ");
        query.append("  AND  ptemgissreq_id     = ?             ");
        
        Object[] objects = new Object[] {
                partIssEmgReqDetailDTO.getReqDate()
                ,partIssEmgReqDetailDTO.getReqBy()
                ,partIssEmgReqDetailDTO.getReqDept()
                ,partIssEmgReqDetailDTO.getWcodeId()
                ,partIssEmgReqDetailDTO.getToWcodeId()
                ,partIssEmgReqDetailDTO.getCtctrId()
                ,partIssEmgReqDetailDTO.getRecBy()
                ,partIssEmgReqDetailDTO.getEquipId()
                ,partIssEmgReqDetailDTO.getPlantId()
                ,partIssEmgReqDetailDTO.getRemark()
                ,DateUtil.getDateTime()
                ,user.getUserId()
                ,user.getCompNo()
                ,partIssEmgReqDetailDTO.getPtEmgIssReqId()
        };
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
    
    public int updateStatus(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE TAPTEMGISSREQ SET                      ");
        query.append("       ptemgissreq_status       	= ?         ");
        query.append("  	,upd_date           		= ?         ");
        query.append("  	,upd_by             		= ?         ");
        query.append("WHERE  ptemgissreq_id           	= ?         ");
        query.append("  AND  comp_no                  	= ?         ");

        Object[] objects = new Object[] {
                partIssEmgReqDetailDTO.getPtEmgIssReqStatus()
                ,DateUtil.getDateTime()
                ,user.getUserId()
                ,partIssEmgReqDetailDTO.getPtEmgIssReqId()
                ,user.getCompNo()
        };

        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public int updateIssListStatus(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAPTEMGISSLIST SET                   ");
    	query.append("       ptemgiss_status          = 'W'       ");
    	query.append("WHERE  ptemgissreq_id           = ?         ");
    	query.append("  AND  comp_no                  = ?         ");
    	
    	Object[] objects = new Object[] {
    			partIssEmgReqDetailDTO.getPtEmgIssReqId()
    			,user.getCompNo()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public int updateReqInfo(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE a SET										");
        query.append("			a.req_by = b.req_by						");
        query.append("			,a.rec_by = b.rec_by					");
        query.append("			,a.req_dept = b.req_dept				");
        query.append("			,a.req_date = b.req_date				");
        query.append("			,a.issue_date = b.req_date				");
        query.append("			,a.wcode_id = b.wcode_id				");
        query.append("			,a.to_wcode_id = b.to_wcode_id			");
        query.append("			,a.plant = b.plant			            ");
        query.append("			,a.ctctr_id = b.ctctr_id			    ");
        query.append("			,a.equip_id = b.equip_id			    ");
        query.append("			,a.eqasmb_id = b.eqasmb_id			    ");
        query.append("FROM TAPTEMGISSLIST a, TAPTEMGISSREQ b			");
        query.append("WHERE a.comp_no = b.comp_no						");
        query.append("AND a.ptemgissreq_id = b.ptemgissreq_id			");
        query.append("AND a.comp_no 			= ?						");
        query.append("AND a.ptemgissreq_id 		= ?						");

        Object[] objects = new Object[] {
        		user.getCompNo()
                ,partIssEmgReqDetailDTO.getPtEmgIssReqId()
        };

        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }


    @Override
    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAPTEMGISSREQ SET                    ");
        query.append("       ptemgissreq_status   = ?             ");
        query.append("WHERE  ptemgissreq_id       = ?             ");
        query.append("  AND  comp_no       = ?             ");
        
        Object[] objects = new Object[] {
                appReqDetailDTO.getParentStatus(),
                appReqDetailDTO.getObjectId(),
                user.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    @Override
    public String[] getPtemgisslistIds(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                   ");
        query.append("    ptemgisslist_id      ");
        query.append("FROM TAPTEMGISSLIST      ");
        query.append("WHERE 1=1                ");
        query.append("AND ptemgissreq_id = ?   ");
        query.append("AND comp_no        = ?   ");
        
        Object[] objects = new Object[] {
                partIssEmgReqDetailDTO.getPtEmgIssReqId()
                ,user.getCompNo()
        };
        
        return QuerySqlBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString(), getObject(objects)));
    }
}