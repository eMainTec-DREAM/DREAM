package dream.work.pm.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.dto.MaWoResultPmCalDTO;
import dream.work.pm.list.dao.MaPmMstrDetailDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrDetailDTO;

/**
 * 상세 dao
 * 
 * @author jung7126
 * @version $Id: MaPmMstrDetailDAO.java,v 1.0 2015/12/02 08:25:47 jung7126 Exp $
 * @since 1.0
 * @spring.bean id="maPmMstrDetailDAOTarget"
 * @spring.txbn id="maPmMstrDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPmMstrDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPmMstrDetailDAO
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaPmMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param eqCtgId
     * @param compNo
     * @return
     */
    public MaPmMstrDetailDTO findDetail(MaPmMstrCommonDTO maPmMstrCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = user.getCompNo();
        
        query.append("SELECT                                                                ");
        query.append("        x.comp_no 									compNo          ");
        query.append("        ,x.pm_no 										pmNo            ");
        query.append("        ,x.pm_no 										oldPmNo         ");
        query.append("        ,x.description                                                ");
        query.append("        ,x.dept_id 									deptId          ");
        query.append("        ,SFAIDTODESC(x.dept_id, '', 'DEPT' , '"+compNo+"') deptDesc   ");
        query.append("        ,x.wkctr_id 									wkCtrId         ");
        query.append("		  ,(SELECT description											");
        query.append("		  	  FROM TAWKCTR												");
        query.append("		  	 WHERE comp_no = x.comp_no									");
        query.append("		  	   AND wkctr_id = x.wkctr_id) 				wkCtrDesc		");
        query.append("        ,x.pm_type 									pmType          ");
        query.append("        ,SFACODE_TO_DESC(x.pm_type, x.wo_type||'_TYPE', 'SYS', '"+compNo+"','"+user.getLangId()+"') pmTypeDesc             ");
        query.append("        ,x.schedule_type 								scheduleType    ");
        query.append("        ,SFACODE_TO_DESC(x.schedule_type, 'SCHEDULE_TYPE', 'SYS', '"+compNo+"','"+user.getLangId()+"') scheduleTypeDesc    ");
        query.append("        ,x.cycle                                                      ");
        query.append("        ,x.period_type 								periodType      ");
        query.append("        ,SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE', 'SYS', '"+compNo+"','"+user.getLangId()+"') periodTypeDesc          ");
        query.append("        ,x.USAGE                                                      ");
        query.append("        ,x.init_wrk_date 								initWrkDate     ");
        query.append("        ,x.is_active 									isActive        ");
        query.append("    	  ,SFACODE_TO_DESC( x.is_active, 'IS_USE','SYS',x.comp_no, '"+user.getLangId()+"')   AS isActiveDesc        ");
        query.append("        ,x.wo_res_bef 								woResBef        ");
        query.append("        ,x.REMARK                                                     ");
        query.append("        ,x.pm_categ 									pmCateg         ");
        query.append("        ,SFACODE_TO_DESC(x.pm_categ, 'PM_CATEG', 'SYS', '"+compNo+"','"+user.getLangId()+"') pmCategDesc   	");
        query.append("        ,x.wo_type woType                                             ");
        query.append("        ,SFACODE_TO_DESC(x.wo_type, 'WO_TYPE', 'SYS', '"+compNo+"','"+user.getLangId()+"') woTypeDesc			");
        query.append("        ,x.emp_id empId                                               ");
        query.append("        ,(SELECT emp_name                                             ");
        query.append("         FROM   TAEMP a                                               ");
        query.append("         WHERE  a.emp_id = x.emp_id) empName                          ");
        query.append("        ,pm_id 										pmId           	");
        query.append("        ,eqloc_id 									eqLocId			");
        query.append("		  ,(SELECT full_desc											");
        query.append("		      FROM TAEQLOC												");
        query.append("		     WHERE comp_no = x.comp_no									");
        query.append("		       AND eqloc_id = x.eqloc_id)				eqLocDesc		");
        query.append("        ,wrkcallist_id 								wrkcalListId	");
        query.append("		  ,(SELECT description											");
        query.append("		      FROM TAWRKCALLIST											");
        query.append("		     WHERE comp_no = x.comp_no									");
        query.append("		       AND wrkcallist_id = x.wrkcallist_id)		wrkcalListDesc	");
        query.append("		  ,x.init_wrk_date			             		lastSchDate		");
        query.append("        ,x.courselist_id                                      courselistId     ");
        query.append("        ,(SELECT description FROM TACOURSELIST WHERE courselist_id=x.courselist_id)     trainDesc        ");
        query.append("        ,x.revisionhist_id 							revisionhistId	");
        query.append("        ,x.revision_status 							revisionStatusId");
        query.append("        ,SFACODE_TO_DESC(x.revision_status, 'REVISION_STATUS', 'SYS', '"+compNo+"','"+user.getLangId()+"') revisionStatusDesc			");
        query.append("        ,x.is_last_version 							isLastVersion	");
        query.append("        ,x.work_number                            	workNumber   	");
        query.append("        ,x.product_id                                 prodGoodsId    	");
        query.append("        ,x.cycle                                      oldCycle    	");
        query.append("        ,x.period_type                                oldPeriodType    	");
        query.append("        ,x.schedule_type                              oldScheduleType    	");
        query.append("        ,(SELECT a.description FROM TAPRODUCT a WHERE a.product_id = x.product_id) prodGoods      ");
        query.append("	      ,x.work_time									predWoTimeMin	");
        query.append("		  ,x.upd_date									updDate			");
        query.append("		  ,x.cre_date									creDate			");
        query.append("		  ,( SELECT param2  											");
        query.append("		     FROM TACDSYSD  											");
        query.append("		     WHERE cdsysd_no=x.pm_type 									");
        query.append("		      AND list_type= x.wo_type||'_TYPE' )       AS pmParam		");
        query.append("        ,lnwrklist_id                                 lnWrkListId     ");
        query.append("        ,(SELECT description                                          ");
        query.append("            FROM TALNWRKLIST                                          ");
        query.append("           WHERE comp_no = x.comp_no                                  ");
        query.append("             AND lnwrklist_id = x.lnwrklist_id)       lnWrkListDesc   ");
        query.append("        ,x.plant                                      plantId         ");
        query.append("        , (SELECT DESCRIPTION											");
        query.append("        	FROM TAPLANT												");
        query.append("        	WHERE comp_no = x.comp_no									");
        query.append("        	AND plant = x.PLANT)                        plantDesc		");
        query.append("        ,x.tolerance                                  tolerance		");
        query.append("FROM    TAPMLST x                                                     ");
        query.append("WHERE  x.pm_id = ?								                    ");
        query.append("  AND  x.comp_no  = ?							                        ");
        
        Object[] objects = new Object[] {
        		maPmMstrCommonDTO.getPmId()
        		,compNo
        };
        
        
        MaPmMstrDetailDTO maPmMstrDetailDTO = 
        		(MaPmMstrDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MaPmMstrDetailDTO()));
        
        return maPmMstrDetailDTO;
    }
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaPmMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrDetailDTO
     * @return
     */
    public int insertDetail(MaPmMstrDetailDTO maPmMstrDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("INSERT INTO TAPMLST                      ");
    	query.append("(                                        ");
    	query.append("    pm_id,           comp_no,            ");
    	query.append("    pm_no,           description,        ");
    	query.append("    dept_id,         revision_status,    ");
    	query.append("    pm_type,         schedule_type,      ");
    	query.append("    cycle,           period_type,        ");
    	query.append("    USAGE,           wo_res_bef,         ");
    	query.append("    init_wrk_date,   is_Last_Version,    ");
    	query.append("    wo_type,         pm_categ,           ");
    	query.append("    emp_id,          eqloc_id,           ");
    	query.append("    is_active,       remark,             ");
    	query.append("    wkctr_id,		   wrkcallist_id,      ");
    	query.append("    work_number,     plant               ");
    	query.append("	, work_time		 , lnwrklist_id		   ");
    	query.append("	, upd_date	     , upd_by			   ");
    	query.append("	, cre_date	     , cre_by			   ");
        query.append("  , product_id     , tolerance           ");
    	query.append(")VALUES                                  ");
    	query.append("(                                        ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,			   ?,                  ");
    	query.append("    ?,               ?				   ");
    	query.append("	, ?				 , ?				   ");
    	query.append("	, ?				 , ?				   ");
    	query.append("	, ?				 , ?				   ");
    	query.append("	, ?				 , ?				   ");
    	query.append(")                                        ");

    	
    	Object[] objects = new Object[] {
    	        maPmMstrDetailDTO.getPmId(),
    	        loginUser.getCompNo(),
    	        maPmMstrDetailDTO.getPmNo(),
    	        maPmMstrDetailDTO.getDescription(),
    	        maPmMstrDetailDTO.getDeptId(),
    	        maPmMstrDetailDTO.getRevisionStatusId(),
    	        maPmMstrDetailDTO.getPmType(),
    	        maPmMstrDetailDTO.getScheduleType(),
    	        maPmMstrDetailDTO.getCycle(),
    	        maPmMstrDetailDTO.getPeriodType(),
    	        maPmMstrDetailDTO.getUsage(),
    	        maPmMstrDetailDTO.getWoResBef(),
    	        maPmMstrDetailDTO.getInitWrkDate().replaceAll("-", ""),
    	        maPmMstrDetailDTO.getIsLastVersion(),
    	        maPmMstrDetailDTO.getWoType(),
    	        maPmMstrDetailDTO.getPmCateg(),
    	        maPmMstrDetailDTO.getEmpId(),
    	        maPmMstrDetailDTO.getEqLocId(),
    	        maPmMstrDetailDTO.getIsActive(),
    	        maPmMstrDetailDTO.getRemark(),
    	        maPmMstrDetailDTO.getWkCtrId(),
    	        maPmMstrDetailDTO.getWrkcalListId(),
    	        maPmMstrDetailDTO.getWorkNumber(),
    	        maPmMstrDetailDTO.getPlantId(),
    	        maPmMstrDetailDTO.getPredWoTimeMin(),
    	        maPmMstrDetailDTO.getLnWrkListId(),
    	        maPmMstrDetailDTO.getUpdDate(),
    	        loginUser.getUserId(),
    	        maPmMstrDetailDTO.getCreDate(),
    	        loginUser.getUserId(),
                maPmMstrDetailDTO.getProdGoodsId(),
    	        maPmMstrDetailDTO.getTolerance()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaPmMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrDetailDTO
     * @return
     */
    public int updateDetail(MaPmMstrDetailDTO maPmMstrDetailDTO, User loginUser) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAPMLST SET               ");
    	query.append("       pm_no             = ?     ");
    	query.append("       ,description      = ?     ");
    	query.append("       ,dept_id          = ?     ");
    	query.append("       ,pm_type          = ?     ");
    	query.append("       ,schedule_type    = ?     ");
    	query.append("       ,cycle            = ?     ");
    	query.append("       ,period_type      = ?     ");
    	query.append("       ,USAGE            = ?     ");
    	query.append("       ,wo_res_bef       = ?     ");
    	query.append("       ,init_wrk_date    = ?     ");
    	query.append("       ,wo_type          = ?     ");
    	query.append("       ,pm_categ         = ?     ");
    	query.append("       ,emp_id           = ?     ");
    	query.append("       ,is_active        = ?     ");
    	query.append("       ,remark           = ?     ");
    	query.append("       ,eqloc_id         = ?     ");
    	query.append("       ,wkctr_id         = ?     ");
    	query.append("       ,wrkcallist_id    = ?     ");
    	query.append("       ,work_number      = ?     ");
    	query.append("       ,product_id       = ?     ");
    	query.append("       ,work_time        = ?     ");
    	query.append("       ,plant            = ?     ");
    	query.append("       ,lnwrklist_id	   = ?     ");
    	query.append("       ,upd_date         = ?     ");
    	query.append("       ,upd_by	       = ?     ");
    	query.append("       ,tolerance	       = ?     ");
    	query.append("WHERE  pm_id             = ?     ");
    	query.append("  AND  comp_no           = ?     ");

        Object[] objects = new Object[] {
                maPmMstrDetailDTO.getPmNo()
                ,maPmMstrDetailDTO.getDescription()
                ,maPmMstrDetailDTO.getDeptId()
                ,maPmMstrDetailDTO.getPmType()
                ,maPmMstrDetailDTO.getScheduleType()
                ,maPmMstrDetailDTO.getCycle()
                ,maPmMstrDetailDTO.getPeriodType()
                ,maPmMstrDetailDTO.getUsage()
                ,maPmMstrDetailDTO.getWoResBef()
                ,maPmMstrDetailDTO.getInitWrkDate().replaceAll("-", "")
                ,maPmMstrDetailDTO.getWoType()
                ,maPmMstrDetailDTO.getPmCateg()
                ,maPmMstrDetailDTO.getEmpId()
                ,maPmMstrDetailDTO.getIsActive()
                ,maPmMstrDetailDTO.getRemark()
                ,maPmMstrDetailDTO.getEqLocId()
                ,maPmMstrDetailDTO.getWkCtrId()
                ,maPmMstrDetailDTO.getWrkcalListId()
                ,maPmMstrDetailDTO.getWorkNumber()
                ,maPmMstrDetailDTO.getProdGoodsId()
                ,maPmMstrDetailDTO.getPredWoTimeMin()
                
                ,maPmMstrDetailDTO.getPlantId()
//                ,maPmMstrDetailDTO.getCompNo()
//                ,maPmMstrDetailDTO.getDeptId()
                
                ,maPmMstrDetailDTO.getLnWrkListId()
    	        ,maPmMstrDetailDTO.getUpdDate()
    	        ,loginUser.getUserId()
    	        ,maPmMstrDetailDTO.getTolerance()
                ,maPmMstrDetailDTO.getPmId()
                ,maPmMstrDetailDTO.getCompNo()
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), objects);

    	return rtnValue;
    }
    
    public int updateLastSchedDate(MaPmMstrDetailDTO maPmMstrDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAPMEQUIP SET				            ");
    	query.append("	   last_sch_date		= init_wrk_date	    ");
    	query.append("    ,next_sch_date        = init_wrk_date     ");
    	query.append("WHERE comp_no		        = ?	                ");
    	query.append("  AND pm_id			    = ?		            ");
    	
    	Object[] objects = new Object[] {
    			 user.getCompNo()
    			,maPmMstrDetailDTO.getPmId()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    
    
    public int updatePmRinsScheduleDetail(MaPmMstrDetailDTO maPmMstrDetailDTO, User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append(" UPDATE  TAPMINSLIST SET                                    ");
    	query.append("       DEPT_ID = ?                                           ");
    	query.append("     , WKCTR_ID = ?                                          ");
    	query.append("     , EMP_ID = ?                                            ");
    	query.append(" WHERE COMP_NO = ?                                           ");
    	query.append("      AND PM_ID = ?                                          ");
    	query.append("      AND PMSCHED_STATUS != 'C'                             ");
    	query.append("      AND WKOR_DATE >= ?                                    ");
    	
    	Object[] objects = new Object[] {
    			maPmMstrDetailDTO.getDeptId()
    			,maPmMstrDetailDTO.getWkCtrId()
    			,maPmMstrDetailDTO.getEmpId()
    			,user.getCompNo()
    			,maPmMstrDetailDTO.getPmId()
    			,DateUtil.getDate()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	    	
    	return rtnValue;
    }
    
    public int updatePmDinsScheduleDetail(MaPmMstrDetailDTO maPmMstrDetailDTO, User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append(" UPDATE  TAPMINSDLIST SET                                   ");
    	query.append("       DEPT_ID = ?                                         ");
    	query.append("     , WKCTR_ID = ?                                         ");
    	query.append("     , EMP_ID = ?                                            ");
    	query.append(" WHERE COMP_NO = ?                                          ");
    	query.append("      AND PM_ID = ?                                          ");
    	query.append("      AND PMSCHED_STATUS  != 'C'                             ");
    	query.append("      AND WKOR_DATE >= ?                                    ");
    	
    	Object[] objects = new Object[] {
    			maPmMstrDetailDTO.getDeptId()
    			,maPmMstrDetailDTO.getWkCtrId()
    			,maPmMstrDetailDTO.getEmpId()
    			,user.getCompNo()
    			,maPmMstrDetailDTO.getPmId()
    			,DateUtil.getDate()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	    	
    	return rtnValue;
    }
    
    public int updatePmPatrlScheduleDetail(MaPmMstrDetailDTO maPmMstrDetailDTO, User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append(" UPDATE  TAPMPTRLRSLTLIST  SET                              ");
    	query.append("       DEPT_ID = ?                                         ");
    	query.append("     , WKCTR_ID = ?                                         ");
    	query.append("     , EMP_ID = ?                                            ");
    	query.append(" WHERE COMP_NO = ?                                          ");
    	query.append("      AND PM_ID = ?                                          ");
    	query.append("      AND PMSCHED_STATUS != 'C'                             ");
    	query.append("      AND WKOR_DATE >= ?                                  ");
    	Object[] objects = new Object[] {
    			maPmMstrDetailDTO.getDeptId()
    			,maPmMstrDetailDTO.getWkCtrId()
    			,maPmMstrDetailDTO.getEmpId()
    			,user.getCompNo()
    			,maPmMstrDetailDTO.getPmId()
    			,DateUtil.getDate()
        };
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	    	
    	return rtnValue;
    }
    public int updatePmWoScheduleDetail(MaPmMstrDetailDTO maPmMstrDetailDTO, User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append(" UPDATE  TAWORKORDER  SET                                   ");
    	query.append("       DEPT_ID = ?                                         ");
    	query.append("     , WKCTR_ID = ?                                         ");
    	query.append("     , EMP_ID = ?                                            ");
    	query.append(" WHERE COMP_NO = ?                                          ");
    	query.append("      AND PM_ID = ?                                          ");
    	query.append("     AND WO_STATUS != 'C'                                   ");
    	query.append("     AND WKOR_DATE >= ?                                    ");
    	Object[] objects = new Object[] {
    			maPmMstrDetailDTO.getDeptId()
    			,maPmMstrDetailDTO.getWkCtrId()
    			,maPmMstrDetailDTO.getEmpId()
    			,maPmMstrDetailDTO.getCompNo()
    			,maPmMstrDetailDTO.getPmId()
    			,DateUtil.getDate()
        };
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	
    	query.setClear();
    	query.append("                                                            ");
    	query.append(" UPDATE  TAWOPLAN  SET                                      ");
    	query.append("       DEPT_ID = ?                                         ");
    	query.append("     , WKCTR_ID = ?                                         ");
    	query.append("     , EMP_ID = ?                                            ");
    	query.append(" WHERE COMP_NO = ?                                          ");
    	query.append("      AND PM_ID = ?                                          ");
    	query.append("       AND WO_STATUS != 'C'                                 ");
    	query.append("        AND WKOR_DATE >= ?        ");
    	objects = new Object[] {
    			maPmMstrDetailDTO.getDeptId()
    			,maPmMstrDetailDTO.getWkCtrId()
    			,maPmMstrDetailDTO.getEmpId()
    			,maPmMstrDetailDTO.getCompNo()
    			,maPmMstrDetailDTO.getPmId()
    			,DateUtil.getDate()
        };
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    
    

    public int createPmSchedule(String compNo, String Pmid, String enterBy) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("begin                                                          ");
        query.append("SP_PM_MAKE_SCHEDULE_BYONE(                                     ");
        query.append("                  '"+compNo+"'                                 ");
        query.append("                 ,'"+Pmid+"'                                   ");
        query.append("                 ,'"+enterBy+"'                                ");
        query.append("                 ,to_char(add_months(sysdate,+12),'yyyy')|| '1231'  ");
        query.append("                 );                                            ");
        query.append("end;                                                           ");
        
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }
    public int createWorkOrder(String compNo, String Pmid) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("begin                                                          ");
    	query.append("SP_PM_MAKE_TO_ONESCHED(                                        ");
    	query.append("                  '"+compNo+"'          ");
    	query.append("                 ,'"+Pmid+"'	         ");
    	query.append("                 );                                            ");
    	query.append("end;                                                           ");
    	
    	this.getJdbcTemplate().execute(query.toString());
    	
    	return 0;
    }
    public String checkDetail(MaPmMstrDetailDTO maPmMstrDetailDTO, User user)
    {
        
    	QueryBuffer query = new QueryBuffer();
    	String pmId = maPmMstrDetailDTO.getPmId();
        
        query.append("SELECT count(1) FROM TAPMEQUIP 			");
        query.append("WHERE comp_no = ?	                  ");
        query.append("  AND pm_id = ?					");
        
        Object[] objects = new Object[] {
        		user.getCompNo()
    			,pmId
        };
     
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    
    }
    
    /**
     * 오늘날짜 이후 wo, 스케쥴 삭제
     * @author jung7126
     * @version $Id: MaPmMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrDetailDTO
     * @return
     */
    public int deletePmScheduleAll(String compNo, String pmId) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("begin                                             ");
        query.append("SP_PM_MAKE_SCHEDALL_DELETE(                        ");
        query.append("                  '"+compNo+"'         			");
        query.append("                 ,'"+pmId+"'            		");
        query.append("                 );                               ");
        query.append("end;                                              ");
        
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }

    @Override
    public int insertRevisionHist(MaPmMstrDetailDTO maPmMstrDetailDTO, User user, String histId) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO TAREVISIONHIST                            ");
        query.append("(                                                     ");
        query.append("  revisionhist_id,            comp_no,                ");
        query.append("  object_id,                  object_no,              ");
        query.append("  revision_object_type,       doc_no,                 ");
        query.append("  revision_status,            revision_step_status,   ");
        query.append("  revision_type,              rev_no,                 ");
        query.append("  wrk_date,                   wrk_id,                 ");
        query.append("  rev_desc                                            ");
        query.append(")VALUES                                               ");
        query.append("(                                                     ");
        query.append("    ?,               ?,                               ");
        query.append("    ?,               ?,                               ");
        query.append("    ?,               ?,                               ");
        query.append("    ?,               ?,                               ");
        query.append("    ?,               ?,                               ");
        query.append("    ?,               ?,                               ");
        query.append("    ?                                                 ");
        query.append(")                                                     ");

        
        Object[] objects = new Object[] {
                histId
                ,user.getCompNo()
                ,maPmMstrDetailDTO.getPmId()
                ,maPmMstrDetailDTO.getPmNo()
                ,"PMSTD"
                ,""
                ,"C"
                ,"CMP"
                ,"C"
                ,"1.00"
                ,DateUtil.getDateTime("yyyyMMdd")
                ,user.getEmpId()
                ,maPmMstrDetailDTO.getDescription()
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
    
	@Override
	public int updatePmMstrLastVersion(MaPmMstrDetailDTO maPmMstrDetailDTO, User user, String histId) {
		QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAPMLST SET                					");
    	query.append("		revisionhist_id = ?                             ");
    	query.append("		,revision_status = ?                            ");
    	query.append("		,is_last_version = ?                            ");
    	query.append("WHERE comp_no = ?                       				");
    	query.append("AND   pm_id = ?                       				");

    	
    	Object[] objects = new Object[] {
    			histId
    			,"C"
    	        ,"Y"
    	        ,user.getCompNo()
    	        ,maPmMstrDetailDTO.getPmId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
	}

    @Override
    public int updatePmEquip(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, MaWoResultPmCalDTO maWoResultPmCalDTO, String pmId)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAPMEQUIP SET              ");
        query.append("       last_cplt_date     = ?     ");
        query.append("      ,next_plan_date     = ?     ");
        query.append("      ,last_cplt_by       = ?     ");
        query.append("      ,pm_result_status   = ?     ");
        query.append("WHERE 1=1                         ");
        query.append("  AND comp_no             = ?     ");
        query.append("  AND pm_id               = ?     ");
        
        Object[] objects = new Object[] {
                maWoResultMstrDetailDTO.getWkorDate().length() > 8 ? CommonUtil.convertDate(maWoResultMstrDetailDTO.getWkorDate()):maWoResultMstrDetailDTO.getWkorDate()
                ,maWoResultPmCalDTO.getNextCalibDate().length() > 8 ? CommonUtil.convertDate(maWoResultPmCalDTO.getNextCalibDate()):maWoResultPmCalDTO.getNextCalibDate()
                ,maWoResultMstrDetailDTO.getEnterBy()
                ,maWoResultPmCalDTO.getCalRsltStatId()
                ,maWoResultMstrDetailDTO.getCompNo()
                ,pmId
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }

	@Override
	public MaPmMstrDetailDTO findEquipDetail(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) {

        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT    x.pmequip_id            		pmequipId		");
        query.append("        , x.INIT_WRK_DATE        			initWrkDate		");
        query.append("        , x.PM_ID											");
        query.append("        , x.EQUIP_ID            			equipId			");
        query.append("        ,(SELECT DESCRIPTION                              ");
        query.append("          FROM TAEQUIPMENT                                ");
        query.append("          WHERE comp_no = x.comp_no                       ");
        query.append("          AND equip_id = x.equip_id )		equipDesc 		");
        query.append("FROM TAPMEQUIP x 											");
        query.append("WHERE x.comp_no = ?										");
        query.append("AND x.pm_id = ?											");
        query.append("AND EXISTS (SELECT pm_id									");
        query.append("            FROM TAPMLST									");
        query.append("            WHERE comp_no = ?								");
        query.append("            AND pm_id = ?									");
        query.append("            AND wo_type = ?)								");
        
        Object[] objects = new Object[] {
        		user.getCompNo()
        		,maPmMstrCommonDTO.getPmId()
        		,user.getCompNo()
        		,maPmMstrCommonDTO.getPmId()
        		,maPmMstrCommonDTO.getWoType()
        };
        
        
        MaPmMstrDetailDTO maPmMstrDetailDTO = 
        		(MaPmMstrDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MaPmMstrDetailDTO()));
        
        return maPmMstrDetailDTO;
    
	}

	@Override
	public String pmNoCheck(MaPmMstrDetailDTO maPmMstrDetailDTO, User user) {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT count(*)		");
    	query.append("FROM TAPMLST 			");
        query.append("WHERE comp_no = ?		");
        query.append("  AND pm_no = ?		");
        query.append("  AND is_deleted = ?	");
        query.append("  AND is_active = ?	");
        
        Object[] objects = new Object[] {
        		user.getCompNo()
    			,maPmMstrDetailDTO.getPmNo()
    			,"N"
    			,"Y"
        };
     
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    
    
	}

    
}