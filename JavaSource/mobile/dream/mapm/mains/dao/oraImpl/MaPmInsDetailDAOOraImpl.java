package mobile.dream.mapm.mains.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import mobile.dream.mapm.mains.dao.MaPmInsDetailDAO;
import mobile.dream.mapm.mains.dto.MaPmInsDetailDTO;

/**
 * 상세 dao
 * 
 * @author jung7126
 * @version $Id: MaPmInsDetailDAO.java,v 1.0 2015/12/02 08:25:47 jung7126 Exp $
 * @since 1.0
 * @spring.bean id="maPmInsDetailDAOTarget"
 * @spring.txbn id="maPmInsDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPmInsDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPmInsDetailDAO
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaPmInsDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param eqCtgId
     * @param compNo
     * @return
     */
    public MaPmInsDetailDTO findDetail(String pmId, User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = user.getCompNo();
        
        query.append("SELECT                                                                                            ");
        query.append("        x.pm_no pmNo,                                                                             ");
        query.append("        x.description,                                                                            ");
        query.append("		  z.equip_id					 AS equipId,												");
        query.append("		  z.description					 AS equipDesc,												");
        query.append("        x.dept_id deptId,                                                                         ");
        query.append("        SFAIDTODESC(x.dept_id, '', 'DEPT' , '"+compNo+"') deptDesc,                               ");
        query.append("        x.wkctr_id wkCtrId,                                                                       ");
        query.append("		 (SELECT description																		");
        query.append("		  FROM TAWKCTR																				");
        query.append("		  WHERE comp_no = x.comp_no																	");
        query.append("		  AND wkctr_id = x.wkctr_id) 					 								wkCtrDesc,	");
        query.append("        x.pm_type pmType,                                                                         ");
        query.append("        SFACODE_TO_DESC(x.pm_type, x.wo_type||'_TYPE', 'SYS', '"+compNo+"','"+user.getLangId()+"') pmTypeDesc,             ");
        query.append("        x.schedule_type scheduleType,                                                             ");
        query.append("        SFACODE_TO_DESC(x.schedule_type, 'SCHEDULE_TYPE', 'SYS', '"+compNo+"','"+user.getLangId()+"') scheduleTypeDesc,    ");
        query.append("        x.cycle,                                                                                  ");
        query.append("        x.period_type periodType,                                                                 ");
        query.append("        SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE', 'SYS', '"+compNo+"','"+user.getLangId()+"') periodTypeDesc,          ");
        query.append("        x.USAGE,                                                                                  ");
        query.append("        x.init_wrk_date initWrkDate,                                                              ");
        query.append("        x.last_sch_date lastSchDate,                                                              ");
        query.append("        x.next_sch_date nextSchDate,                                                              ");
        query.append("        x.is_active isActive,                                                                     ");
        query.append("        x.wo_res_bef woResBef,                                                                    ");
        query.append("        x.REMARK,                                                                                 ");
        query.append("        x.pm_categ pmCateg,                                                       ");
        query.append("        SFACODE_TO_DESC(x.pm_categ, 'PM_CATEG', 'SYS', '"+compNo+"','"+user.getLangId()+"') pmCategDesc,   ");
        query.append("        x.wo_type woType,                                                         ");
        query.append("        SFACODE_TO_DESC(x.wo_type, 'WO_TYPE', 'SYS', '"+compNo+"','"+user.getLangId()+"') woTypeDesc,      ");
        query.append("        x.emp_id empId,                                                           ");
        query.append("        (SELECT emp_name                                                          ");
        query.append("         FROM   TAEMP a                                                           ");
        query.append("         WHERE  a.emp_id = x.emp_id) empName,                                     ");
        query.append("        x.pm_id pmId ,                                                                            ");
        query.append("        x.eqloc_id eqLocId,                                                                       ");
        query.append("		   (SELECT full_desc											                            ");
        query.append("		     FROM TAEQLOC												                            ");
        query.append("		     WHERE comp_no = x.comp_no									                            ");
        query.append("		      AND eqloc_id = x.eqloc_id)				eqLocDesc		                            ");
        query.append("FROM TAPMLST x INNER JOIN TAPMEQUIP y                                                     		");
        query.append("ON x.comp_no = y.comp_no AND x.pm_id = y.pm_id		                                            ");
        query.append("		INNER JOIN TAEQUIPMENT z                            		                                ");
        query.append("      ON y.comp_no = z.comp_no AND Y.equip_id = z.equip_id                						");
        query.append("WHERE  x.pm_id = '"+pmId+"'								                                        ");
        query.append("  AND  x.comp_no  = '"+compNo+"'								                                    ");
    
        MaPmInsDetailDTO maPmInsDetailDTO = 
        		(MaPmInsDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPmInsDetailDTO()));
        
        return maPmInsDetailDTO;
    }
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaPmInsDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmInsDetailDTO
     * @return
     */
    public int insertDetail(MaPmInsDetailDTO maPmInsDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAPMLST                      ");
    	query.append("(                                        ");
    	query.append("    pm_id,           comp_no,            ");
    	query.append("    pm_no,           description,        ");
    	query.append("    dept_id,                             ");
    	query.append("    pm_type,         schedule_type,      ");
    	query.append("    cycle,           period_type,        ");
    	query.append("    USAGE,           wo_res_bef,         ");
    	query.append("    init_wrk_date,   last_sch_date,      ");
    	query.append("    wo_type,         pm_categ,           ");
    	query.append("    emp_id,          eqloc_id,           ");
    	query.append("    is_active,       remark,             ");
    	query.append("    wkctr_id				               ");
    	query.append(")VALUES                                  ");
    	query.append("(                                        ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,                                   ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?					                   ");
    	query.append(")                                        ");

    	
    	Object[] objects = new Object[] {
    	        maPmInsDetailDTO.getPmId(),
    	        maPmInsDetailDTO.getCompNo(),
    	        maPmInsDetailDTO.getPmNo(),
    	        maPmInsDetailDTO.getDescription(),
    	        maPmInsDetailDTO.getDeptId(),
    	        maPmInsDetailDTO.getPmType(),
    	        maPmInsDetailDTO.getScheduleType(),
    	        maPmInsDetailDTO.getCycle(),
    	        maPmInsDetailDTO.getPeriodType(),
    	        maPmInsDetailDTO.getUsage(),
    	        maPmInsDetailDTO.getWoResBef(),
    	        maPmInsDetailDTO.getInitWrkDate(),
    	        maPmInsDetailDTO.getInitWrkDate(),
    	        maPmInsDetailDTO.getWoType(),
    	        maPmInsDetailDTO.getPmCateg(),
    	        maPmInsDetailDTO.getEmpId(),
    	        maPmInsDetailDTO.getEqLocId(),
    	        maPmInsDetailDTO.getIsActive(),
    	        maPmInsDetailDTO.getRemark(),
    	        maPmInsDetailDTO.getWkCtrId()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	if(!"".equals(maPmInsDetailDTO.getEquipId())&&maPmInsDetailDTO.getEquipId()!=null){
    		query = new QueryBuffer();
    		
    		query.append("INSERT INTO TAPMEQUIP(comp_no, pmequip_id,pm_id,equip_id)		");
    		query.append("VALUES(?,SQAPMEQUIP_ID.NEXTVAL,?,?)							");
    		Object[] objects1 = new Object[] {
        	        maPmInsDetailDTO.getCompNo(),
        	        maPmInsDetailDTO.getPmId(),
        	        maPmInsDetailDTO.getEquipId()
        	};
        	rtnValue = this.getJdbcTemplate().update(query.toString(), objects1);
    	}
    	return rtnValue;
    }
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaPmInsDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmInsDetailDTO
     * @return
     */
    public int updateDetail(MaPmInsDetailDTO maPmInsDetailDTO) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAPMLST SET               ");
    	query.append("       pm_no            = ?,     ");
    	query.append("       description      = ?,     ");
    	query.append("       dept_id          = ?,     ");
    	query.append("       pm_type          = ?,     ");
    	query.append("       schedule_type    = ?,     ");
    	query.append("       cycle            = ?,     ");
    	query.append("       period_type      = ?,     ");
    	query.append("       USAGE            = ?,     ");
    	query.append("       wo_res_bef       = ?,     ");
    	query.append("       init_wrk_date    = ?,     ");
    	query.append("       last_sch_date    = ?,     ");
    	query.append("       wo_type          = ?,     ");
    	query.append("       pm_categ         = ?,     ");
    	query.append("       emp_id           = ?,     ");
    	query.append("       is_active        = ?,     ");
    	query.append("       remark           = ?,     ");
    	query.append("       eqloc_id         = ?,     ");
    	query.append("       wkctr_id         = ?      ");
    	query.append("WHERE  pm_id            = ?      ");
    	query.append("  AND  comp_no          = ?      ");

        Object[] objects = new Object[] {
                maPmInsDetailDTO.getPmNo(),
                maPmInsDetailDTO.getDescription(),
                maPmInsDetailDTO.getDeptId(),
                maPmInsDetailDTO.getPmType(),
                maPmInsDetailDTO.getScheduleType(),
                maPmInsDetailDTO.getCycle(),
                maPmInsDetailDTO.getPeriodType(),
                maPmInsDetailDTO.getUsage(),
                maPmInsDetailDTO.getWoResBef(),
                maPmInsDetailDTO.getInitWrkDate(),
                maPmInsDetailDTO.getInitWrkDate(),
                maPmInsDetailDTO.getWoType(),
                maPmInsDetailDTO.getPmCateg(),
                maPmInsDetailDTO.getEmpId(),
                maPmInsDetailDTO.getIsActive(),
                maPmInsDetailDTO.getRemark(),
                maPmInsDetailDTO.getEqLocId(),
                maPmInsDetailDTO.getWkCtrId(),
                maPmInsDetailDTO.getPmId(),
                maPmInsDetailDTO.getCompNo()
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), objects);

        if(!"".equals(maPmInsDetailDTO.getEquipId())&&maPmInsDetailDTO.getEquipId()!=null){
    		query = new QueryBuffer();
    		
    		query.append("UPDATE TAPMEQUIP SET equip_id = ?			");
    		query.append("WHERE comp_no = ?							");
    		query.append("  AND pm_id   = ?							");
    		Object[] objects1 = new Object[] {
        	        maPmInsDetailDTO.getEquipId(),
        	        maPmInsDetailDTO.getCompNo(),
        	        maPmInsDetailDTO.getPmId(),
        	};
        	rtnValue = this.getJdbcTemplate().update(query.toString(), objects1);
    	}
    	return rtnValue;
    }
    public int updateWorkOrder(MaPmInsDetailDTO maPmInsDetailDTO) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAWORKORDER SET          ");
    	query.append("       dept_id         = ?,     ");
    	query.append("       wkctr_id        = ?,     ");
    	query.append("       emp_id          = ?      ");
    	query.append("WHERE  pm_id           = ?      ");
    	query.append("  AND  comp_no         = ?      ");
    	query.append("  AND  wo_status       = ?      ");
    	
    	Object[] objects = new Object[] {
    			maPmInsDetailDTO.getDeptId(),
    			maPmInsDetailDTO.getWkCtrId(),
    			maPmInsDetailDTO.getEmpId(),
    			maPmInsDetailDTO.getPmId(),
    			maPmInsDetailDTO.getCompNo(),
    			"P"
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	return rtnValue;
    }

    /**
     * 오늘날짜 이후 wo미발행 스케쥴 삭제
     * @author jung7126
     * @version $Id: MaPmInsDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmInsDetailDTO
     * @return
     */
    public int deletePmSched(MaPmInsDetailDTO maPmInsDetailDTO) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAPMSCHED x								");
    	query.append("WHERE  x.plan_date > TO_CHAR(SYSDATE,'yyyymmdd')		");
    	query.append("  AND  x.pm_id = ?									");
    	query.append("  AND  x.comp_no	= ?									");
    	query.append("  AND  x.wkor_id	IS NULL								");
    	
    	Object[] objects = new Object[] {
    			maPmInsDetailDTO.getPmId(),
    			maPmInsDetailDTO.getCompNo()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    public int createPmSchedule(MaPmInsDetailDTO maPmInsDetailDTO) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("begin                                                          ");
        query.append("SP_PM_MAKE_SCHEDULE_BYONE(                                     ");
        query.append("                  '"+maPmInsDetailDTO.getCompNo()+"'          ");
        query.append("                 ,'"+maPmInsDetailDTO.getPmId()+"'            ");
        query.append("                 ,'"+maPmInsDetailDTO.getEnterBy()+"'         ");
        query.append("                 ,to_char(add_months(sysdate,+12),'yyyy')|| '1231'  ");
        query.append("                 );                                            ");
        query.append("end;                                                           ");
        
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }
    public int createWorkOrder(MaPmInsDetailDTO maPmInsDetailDTO) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("begin                                                          ");
    	query.append("SP_PM_MAKE_WO_BYALL(                                          ");
    	query.append("                  '"+maPmInsDetailDTO.getCompNo()+"'          ");
    	query.append("                 ,'"+maPmInsDetailDTO.getEnterBy()+"'         ");
    	query.append("                 );                                            ");
    	query.append("end;                                                           ");
    	
    	this.getJdbcTemplate().execute(query.toString());
    	
    	return 0;
    }
    public String checkDetail(MaPmInsDetailDTO maPmInsDetailDTO, User user)
    {
        
    	QueryBuffer query = new QueryBuffer();
    	String pmId = maPmInsDetailDTO.getPmId();
        
        query.append("SELECT count(1) FROM TAPMEQUIP 			");
        query.append("WHERE comp_no = '"+user.getCompNo()+"'	");
        query.append("  AND pm_id = '"+pmId+"'					");
     
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    
    }
}