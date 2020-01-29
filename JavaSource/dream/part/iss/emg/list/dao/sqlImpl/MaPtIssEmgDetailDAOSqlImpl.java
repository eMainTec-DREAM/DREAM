package dream.part.iss.emg.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.part.iss.emg.list.dao.MaPtIssEmgDetailDAO;
import dream.part.iss.emg.list.dto.MaPtIssEmgCommonDTO;
import dream.part.iss.emg.list.dto.MaPtIssEmgDetailDTO;

/**
 * 긴급출고 - 상세 dao
 * 
 * @author ssong
 * @version $Id: MaPtIssEmgDetailDAO.java,v 1.0 2015/12/02 08:25:47 ssong Exp $
 * @since 1.0
 * @spring.bean id="maPtIssEmgDetailDAOTarget"
 * @spring.txbn id="maPtIssEmgDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtIssEmgDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPtIssEmgDetailDAO
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssEmgCommonDTO
     * @return
     */
    public MaPtIssEmgDetailDTO findDetail(MaPtIssEmgCommonDTO maPtIssEmgCommonDTO,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT																				");
        query.append("        '' seqNo     																	");
        query.append("        ,'' isDelCheck     															");
        query.append("        ,ptemgisslist_id ptemgisslistId     											");
        query.append("        ,(SELECT a.description     													");
        query.append("          FROM   TADEPT a     														");
        query.append("          WHERE  a.dept_id = x.issue_dept AND a.comp_no = x.comp_no) issueDeptDesc    							");
        query.append("        ,ptiss_type ptissType                              							");
        query.append("        ,issue_dept issueDept                              							");
        query.append("        ,issue_by issueBy                                  							");
        query.append("        ,(SELECT emp_name     														");
        query.append("          FROM   TAEMP a     															");
        query.append("          WHERE  a.emp_id = issue_by AND a.comp_no = x.comp_no) issueByName     									");
//        query.getDate("issue_date", "issueDate,");
        query.append("        ,issue_date issueDate     													");
        query.append("        ,y.part_no partNo     														");
        query.append("        ,y.part_id partId     														");
        query.append("        ,x.wcode_id wcodeId															");
        query.append("        ,(SELECT wname     															");
        query.append("          FROM   TAWAREHOUSE a     													");
        query.append("          WHERE  a.wcode_id = x.wcode_id AND a.comp_no = x.comp_no) wname     									");
        
        query.append("        ,x.to_wcode_id toWcodeId	    												");
        query.append("        ,(SELECT wname     															");
        query.append("          FROM   TAWAREHOUSE a     													");
        query.append("          WHERE  a.wcode_id = x.to_wcode_id AND a.comp_no = x.comp_no) toWname     									");
        
        query.append("        ,y.full_desc partDesc     													");
        query.append("        ,x.part_grade partGrade     													");
        query.append("        ,dbo.SFACODE_TO_DESC(x.part_grade,'PART_GRADE','SYS','','"+user.getLangId()+"') partGradeDesc     			");
        query.append("        ,x.issue_qty issueQty     													");
        query.append("        ,x.rec_by recBy     													        ");
        query.append("        ,(SELECT emp_name     														");
        query.append("          FROM   TAEMP a     															");
        query.append("          WHERE  a.emp_id = rec_by AND a.comp_no = x.comp_no) recByName     									");
        query.append("        ,ptemgiss_status ptemgissStatus     											");
        query.append("        ,dbo.SFACODE_TO_DESC(x.ptemgiss_status,'PTEMGISS_STATUS','SYS','','"+user.getLangId()+"') ptemgissStatusDesc		");
        query.append("        ,ptemg_task_status ptemgTaskStatus     										");
        query.append("        ,dbo.SFACODE_TO_DESC(x.ptemg_task_status,'PTEMG_TASK_STATUS','SYS','','"+user.getLangId()+"') ptemgTaskStatusDesc  ");
        query.append("        ,(SELECT a.wo_no     															");
        query.append("          FROM   TAWORKORDER a     													");
        query.append("          WHERE  a.wkor_id = x.wkor_id AND a.comp_no = x.comp_no) woNo     										");
        query.append("        ,(SELECT a.description     ");
        query.append("          FROM   TAWORKORDER a     ");
        query.append("          WHERE a.wkor_id = x.wkor_id AND a.comp_no = x.comp_no) woDesc     ");
        query.append("        ,x.remark     ");
        query.append("        ,x.req_qty reqQty     ");
        query.append("        ,x.equip_id equipId              ");
        query.append("        ,(SELECT a.description     ");
        query.append("          FROM   TAEQUIPMENT a     ");
        query.append("          WHERE a.equip_id = x.equip_id AND a.comp_no = x.comp_no) equipName     ");
        query.append("        ,(SELECT a.full_desc     ");
        query.append("          FROM   TAEQLOC a     ");
        query.append("          WHERE a.eqloc_id = (select aa.eqloc_id from taequipment aa where aa.equip_id=x.equip_id and aa.comp_no=a.comp_no) AND a.comp_no = x.comp_no) eqLocDesc     ");
        query.append("        ,x.eqasmb_id eqAsmbId              ");
        query.append("        ,(SELECT a.full_desc     ");
        query.append("          FROM   TAEQASMB a     ");
        query.append("          WHERE a.eqasmb_id = x.eqasmb_id AND a.comp_no = x.comp_no) eqAsmbDesc     ");
		query.append("		 ,x.ctctr_id						 ctCtrId			");
		query.append("		 ,(SELECT a.description									");
		query.append("			FROM TACTCTR a										");
		query.append("			WHERE a.comp_no = x.comp_no							");
		query.append("			AND a.ctctr_id = x.ctctr_id ) 		ctCtrDesc		");
		query.append("		,(SELECT a.ptemgissreq_status from TAPTEMGISSREQ a WHERE a.comp_no = x.comp_no AND a.ptemgissreq_id = x.ptemgissreq_id) ptEmgIssReqStatus		");
		query.append("		 ,x.erp_iss_no					erpIssNo				");
		query.append("		 ,x.erp_yyyy					erpYyyy					");
		query.append("		 ,x.budat						erpBudat				");
		query.append("       ,(select stock_qty from taptstock where comp_no = x.comp_no and wcode_id = x.wcode_id and part_id = x.part_id and part_grade = x.part_grade) stockQty  ");
        query.append("FROM TAPTEMGISSLIST x, TAPARTS y								");
        query.append("WHERE x.part_id = y.part_id									");
        query.append(" AND x.comp_no = y.comp_no									");
        query.append("  AND x.comp_no = ?											");
        query.append("  AND ptemgisslist_id = ?										");
        query.append("ORDER BY ptemgisslist_id DESC									");
    
        Object[] objects  = new Object[] {
                user.getCompNo()
        		,maPtIssEmgCommonDTO.getPtemgisslistId()
        };
        
        MaPtIssEmgDetailDTO maPtIssEmgDetailDTO = 
        		(MaPtIssEmgDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MaPtIssEmgDetailDTO()));
        
        return maPtIssEmgDetailDTO;
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssEmgDetailDTO
     * @return
     */
    public int insertPtIssEmgList(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects = null;
 
        query.append("INSERT INTO TAPTEMGISSLIST                                ");
        query.append("  (comp_no,           ptemgisslist_id,                    ");
        query.append("   ptemgiss_status,   ptiss_type,                         ");
        query.append("   ptemg_task_status, wopart_id,                          ");
        query.append("   wkor_id,           wcode_id,                           ");
        query.append("   issue_date,        issue_dept,                         ");
        query.append("   issue_by,          rec_by,                             ");
        query.append("   part_id,           part_grade,                         ");
        query.append("   issue_qty,         remark,                             ");
        query.append("   equip_id,          eqasmb_id,                          ");
        query.append("   req_qty,           ptemgissreq_id,                     ");
        query.append("   to_wcode_id,       ctctr_id,                           ");
        query.append("   plant,             req_date,                           ");
        query.append("   req_dept,          req_by                              ");
        query.append("  )   VALUES                                              ");
        query.append("  (?,                 ?,                                  ");
        query.append("   ?,                 ?,                                  ");
        query.append("   ?,                 ?,                                  ");
        query.append("   ?,                 ?,                                  ");
        query.append("   ?,                 ?,                                  ");
        query.append("   ?,                 ?,                                  ");
        query.append("   ?,                 ?,                                  ");
        query.append("   ?,                 ?,                                  ");
        query.append("   ?,                 ?,                                  ");
        query.append("   ?,                 ?,                                  ");
        query.append("   ?,                 ?,                                  ");
        query.append("   ?,                 ?,                                  ");
        query.append("   ?,                 ?                                   ");
        query.append("  )                                                       ");

        
        objects = new Object[] {
                loginUser.getCompNo(),
                maPtIssEmgDetailDTO.getPtemgisslistId(),
                maPtIssEmgDetailDTO.getPtemgissStatus(),
                maPtIssEmgDetailDTO.getPtissType(),
                maPtIssEmgDetailDTO.getPtemgTaskStatus(),
                maPtIssEmgDetailDTO.getWopartId(),
                maPtIssEmgDetailDTO.getWkorId(),
                maPtIssEmgDetailDTO.getWcodeId(),
                "".equals(maPtIssEmgDetailDTO.getIssueDate())?DateUtil.getDate():maPtIssEmgDetailDTO.getIssueDate(),
                maPtIssEmgDetailDTO.getIssueDept(),
                maPtIssEmgDetailDTO.getIssueBy(),
                maPtIssEmgDetailDTO.getRecBy(),
                maPtIssEmgDetailDTO.getPartId(),
                maPtIssEmgDetailDTO.getPartGrade(),
                maPtIssEmgDetailDTO.getIssueQty(),
                maPtIssEmgDetailDTO.getRemark(),
                maPtIssEmgDetailDTO.getEquipId(),
                maPtIssEmgDetailDTO.getEqAsmbId(),
                maPtIssEmgDetailDTO.getIssueQty(),
                maPtIssEmgDetailDTO.getPtemgissreqId(),
                maPtIssEmgDetailDTO.getToWcodeId(),
                maPtIssEmgDetailDTO.getCtCtrId(),
                loginUser.getPlant(),
                "".equals(maPtIssEmgDetailDTO.getIssueDate())?DateUtil.getDate():maPtIssEmgDetailDTO.getIssueDate(),
                maPtIssEmgDetailDTO.getIssueDept(),
                maPtIssEmgDetailDTO.getIssueBy()
        };
            
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }

    public int updatePtIssEmgList(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE TAPTEMGISSLIST SET                        		");
        query.append("  issue_date                	= ?,       				");
        query.append("  part_id              		= ?,                   	");
        query.append("  wcode_id                	= ?,                   	");
        query.append("  part_grade                  = ?,                   	");
        query.append("  issue_qty                	= ?,                   	");
        query.append("  rec_by                  	= ?,                   	");
        query.append("  remark                      = ?,                   	");
        query.append("  to_wcode_id                 = ?,                   	");
        query.append("  equip_id                    = ?,                    ");
        query.append("  eqasmb_id                   = ?,                    ");
        query.append("  req_qty                     = ?,                    ");
        query.append("  ctctr_id                    = ?                     ");
        query.append("WHERE ptemgisslist_id         = ?          			");
        query.append("AND comp_no                   = ?                     ");
        
        Object[] objects = new Object[] {
        		"".equals(maPtIssEmgDetailDTO.getIssueDate())?DateUtil.getDate():maPtIssEmgDetailDTO.getIssueDate(),
        		maPtIssEmgDetailDTO.getPartId(),
        		maPtIssEmgDetailDTO.getWcodeId(),
        		maPtIssEmgDetailDTO.getPartGrade(),
        		maPtIssEmgDetailDTO.getIssueQty(),
        		maPtIssEmgDetailDTO.getRecBy(),
        		maPtIssEmgDetailDTO.getRemark(),
        		maPtIssEmgDetailDTO.getToWcodeId(),
        		maPtIssEmgDetailDTO.getEquipId(),
                maPtIssEmgDetailDTO.getEqAsmbId(),
                maPtIssEmgDetailDTO.getReqQty(),
                maPtIssEmgDetailDTO.getCtCtrId(),
        		maPtIssEmgDetailDTO.getPtemgisslistId(),
        		maPtIssEmgDetailDTO.getCompNo()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
        
    }
    public int insertPtIssHist(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, String histId, String issMode, String issType, String wcodeId, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append(" INSERT INTO TAPTISSHIST(                                 ");
        query.append("     comp_no ,ptisshist_id ,ptiss_mode ,ptiss_type        ");
        query.append("     ,isslist_id ,dept_id ,wcode_id ,iss_date             ");
        query.append("     ,part_id ,part_grade ,iss_qty ,unit_price            ");
        query.append("     ,tot_price                                           ");
        query.append(" )                                                        ");
        query.append(" SELECT                                                   ");
        query.append("     a.comp_no                                            ");
        query.append("     ,?                                                   ");
        query.append("     ,?                                                   ");
        query.append("     ,?                                                   ");
        query.append("     ,a.ptemgisslist_id                                   ");
        query.append("     ,a.issue_dept                                        ");
        query.append("     ,?                                                   ");
        query.append("     ,a.issue_date                                        ");
        query.append("     ,a.part_id                                           ");
        query.append("     ,a.part_grade                                        ");
        query.append("     ,a.issue_qty                                         ");
        query.append("     ,b.last_price                                        ");
        query.append("     ,CONVERT(NUMERIC(18,2), b.last_price * a.issue_qty) tot_price      ");
        query.append(" FROM TAPTEMGISSLIST a INNER JOIN TAPARTS b               ");
        query.append(" ON a.comp_no = b.comp_no                                 ");
        query.append(" AND a.part_id = b.part_id                                ");
        query.append(" WHERE 1=1                                                ");
        query.append("   AND a.comp_no         = ?                              ");
        query.append("   AND a.ptemgisslist_id = ?                              ");
        
        Object[] objects = new Object[] {
        		histId
        		,issMode
        		,issType
        		,wcodeId
        		,user.getCompNo()
        		,maPtIssEmgDetailDTO.getPtemgisslistId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    public int execPtIss(String histId, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("exec dbo.SP_PT_OUTSTOCK '"+user.getCompNo()+"', '"+histId+"' ; ");
       
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }
    public int insertPtRecHist(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, String histId, String issMode, String issType, String wcodeId, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TAPTRECHIST (                                 						");
        query.append("       comp_no,        ptrechist_id,                      						");
        query.append("       ptrec_mode,     ptrec_type,            reclist_id, 						");
        query.append("       dept_id,        wcode_id,              rec_date,   						");
        query.append("       part_id,        part_grade,            rec_qty,    						");
        query.append("       unit_price,     tot_price                          						");
        query.append(")                                                         						");
        query.append("SELECT comp_no,       ?,                     										");
        query.append("       ?,  			?,						?,                 					");
        query.append("       ?,       		?,             			?,									");
        query.append("       x.part_id,      ?,           			?,         							");
        query.append("       x.last_price,   CONVERT(NUMERIC(18,2), x.last_price * ?)  					");
        query.append("FROM   TAPARTS x                                          						");
        query.append("WHERE  x.comp_no = ?                                      						");
        query.append("  AND  x.part_id = ?                                      						");
        
        Object[] objects = new Object[] {
        		histId
        		,issMode
        		,issType
        		,""
        		,maPtIssEmgDetailDTO.getIssueDept()
        		,wcodeId
        		,"".equals(maPtIssEmgDetailDTO.getIssueDate())?DateUtil.getDate():maPtIssEmgDetailDTO.getIssueDate()
        		,maPtIssEmgDetailDTO.getPartGrade()
        		,maPtIssEmgDetailDTO.getIssueQty()
        		,maPtIssEmgDetailDTO.getIssueQty()
        		,user.getCompNo()
        		,maPtIssEmgDetailDTO.getPartId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    public int execPtRec(String histId, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("exec dbo.SP_PT_INSTOCK '"+user.getCompNo()+"', '"+histId+"' ;  ");
       
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }
	public int issueComp(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User user) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
        query.append("UPDATE TAPTEMGISSLIST SET                        	   ");
        query.append("  ptemgiss_status            		= ?                ");
        query.append("  ,issue_dept            			= ?                ");
        query.append("  ,issue_by            			= ?                ");
        query.append("WHERE ptemgisslist_id         = ?                    ");
        query.append("AND comp_no                   = ?                    ");


        
        Object[] objects = new Object[] {
        		"C"
        		,user.getDeptId()
        		,user.getEmpId()
        		,maPtIssEmgDetailDTO.getPtemgisslistId()
        		,user.getCompNo()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
	}
	public int issueCancel(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User user) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE TAPTEMGISSLIST SET                        	   ");
        query.append("  ptemgiss_status            	= ?                    ");
        query.append("WHERE ptemgisslist_id         = ?                    ");
        query.append("AND comp_no                   = ?                    ");


        
        Object[] objects = new Object[] {
        		"X",
        		maPtIssEmgDetailDTO.getPtemgisslistId(),
        		user.getCompNo()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
	}

    @Override
    public int insertPtIssEmgReq(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
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
        query.append("  ,eqasmb_id                  ");
        query.append("  ,plant                      ");
        query.append("  ,remark                     ");
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
        query.append(")                             ");
        
        Object[] objects = new Object[] {
                loginUser.getCompNo(),
                maPtIssEmgDetailDTO.getPtemgissreqId(),
                maPtIssEmgDetailDTO.getPtemgissreqId(),
                "WT",
                "".equals(maPtIssEmgDetailDTO.getIssueDate())?DateUtil.getDate():maPtIssEmgDetailDTO.getIssueDate(),
                maPtIssEmgDetailDTO.getIssueBy(),
                maPtIssEmgDetailDTO.getIssueDept(),
                maPtIssEmgDetailDTO.getWcodeId(),
                maPtIssEmgDetailDTO.getToWcodeId(),
                maPtIssEmgDetailDTO.getCtCtrId(),
                maPtIssEmgDetailDTO.getRecBy(),
                maPtIssEmgDetailDTO.getEquipId(),
                maPtIssEmgDetailDTO.getEqAsmbId(),
                loginUser.getPlant(),
                maPtIssEmgDetailDTO.getRemark()
        };
            
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }

    @Override
    public int completePtIssEmgReqStatus(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAPTEMGISSREQ SET ptemgissreq_status = 'CI'      ");
        query.append("  					, upd_date            = ?         ");
        query.append("  					, upd_by              = ?         ");
        query.append("WHERE ptemgissreq_id = (SELECT ptemgissreq_id           ");
        query.append("                        FROM TAPTEMGISSLIST             ");
        query.append("                        WHERE ptemgisslist_id = ?       ");
        query.append("                        AND comp_no = ?                 ");
        query.append("                       )                                ");
        query.append("AND comp_no = ?                                         ");
        query.append("AND (SELECT count(1)                                    ");
        query.append("     FROM TAPTEMGISSLIST                                ");
        query.append("     WHERE ptemgissreq_id = (SELECT ptemgissreq_id      ");
        query.append("                             FROM TAPTEMGISSLIST        ");
        query.append("                             WHERE ptemgisslist_id = ?  ");
        query.append("                             AND comp_no = ?            ");
        query.append("                            )                           ");
        query.append("     AND comp_no = ?                                    ");
        query.append("     AND ptemgiss_status = 'C'                          ");
        query.append("    )                                                   ");
        query.append("    =                                                   ");
        query.append("    (SELECT count(1)                                    ");
        query.append("     FROM TAPTEMGISSLIST                                ");
        query.append("     WHERE ptemgissreq_id = (SELECT ptemgissreq_id      ");
        query.append("                             FROM TAPTEMGISSLIST        ");
        query.append("                             WHERE ptemgisslist_id = ?  ");
        query.append("                             AND comp_no = ?            ");
        query.append("                            )                           ");
        query.append("     AND comp_no = ?                                    ");
        query.append("    )                                                   ");
        
        Object[] objects = new Object[] {
                DateUtil.getDateTime()
                ,loginUser.getUserId()
                ,maPtIssEmgDetailDTO.getPtemgisslistId()
                ,loginUser.getCompNo()
                ,loginUser.getCompNo()
                ,maPtIssEmgDetailDTO.getPtemgisslistId()
                ,loginUser.getCompNo()
                ,loginUser.getCompNo()
                ,maPtIssEmgDetailDTO.getPtemgisslistId()
                ,loginUser.getCompNo()
                ,loginUser.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    @Override
    public int cancelPtIssEmgReqStatus(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAPTEMGISSREQ SET ptemgissreq_status = 'X'       ");
        query.append("  					, upd_date            = ?         ");
        query.append("  					, upd_by              = ?         ");
        query.append("WHERE ptemgissreq_id = (SELECT ptemgissreq_id           ");
        query.append("                        FROM TAPTEMGISSLIST             ");
        query.append("                        WHERE ptemgisslist_id = ?       ");
        query.append("                        AND comp_no = ?                 ");
        query.append("                       )                                ");
        query.append("AND comp_no = ?                                         ");
        
        Object[] objects = new Object[] {
                DateUtil.getDateTime()
                ,loginUser.getUserId()
                ,maPtIssEmgDetailDTO.getPtemgisslistId()
                ,loginUser.getCompNo()
                ,loginUser.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }

	@Override
	public String findStockQty(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT ISNULL(stock_qty,0)");
        query.append("FROM TAPTSTOCK            ");
        query.append("WHERE comp_no  = ?        ");
        query.append("AND part_id    = ?        ");
        query.append("AND part_grade = ?        ");
        query.append("AND wcode_id   = ?        ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
                ,maPtIssEmgDetailDTO.getPartId()
                ,maPtIssEmgDetailDTO.getPartGrade()
                ,maPtIssEmgDetailDTO.getWcodeId()
        };
        
        return QuerySqlBuffer.listToString(this.getJdbcTemplate().queryForList(query.toString(), objects));
    }

}