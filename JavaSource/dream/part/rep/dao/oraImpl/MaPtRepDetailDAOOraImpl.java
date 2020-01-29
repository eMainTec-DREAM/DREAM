package dream.part.rep.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.part.rep.dao.MaPtRepDetailDAO;
import dream.part.rep.dto.MaPtRepCommonDTO;
import dream.part.rep.dto.MaPtRepDetailDTO;

/**
 * 부품수리 - 상세 dao
 * 
 * @author ssong
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="maPtRepDetailDAOTarget"
 * @spring.txbn id="maPtRepDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtRepDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtRepDetailDAO
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepCommonDTO
     * @param loginUser
     * @return
     */
    public MaPtRepDetailDTO findDetail(MaPtRepCommonDTO maPtRepCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT x.comp_no                              compNo,             ");
        query.append("       x.ptrepairlist_id                      ptRepairListId,     ");
        query.append("       x.ptrepairlist_no                      ptRepairListNo,     ");
        query.append("       x.ptrepairlist_status                  ptRepairListStatus, ");
        query.append("       SFACODE_TO_DESC(x.ptrepairlist_status, 'REPAIR_STATUS', 'SYS', x.comp_no,'"+loginUser.getLangId()+"') ptRepairListStatusDesc, "); 
        query.append("       x.wkor_id                              wkorId,             ");
        query.append("       x.wopart_id                            woPartId,   		");
        query.append("       x.dept_id                              deptId,     		");
        query.append("       SFAIDTODESC(x.dept_id, '', 'DEPT', x.comp_no) deptDesc,  	"); 
        query.append("       x.wcode_id                             wcodeId,    		"); 
        query.append("      (SELECT wname FROM TAWAREHOUSE                      		");
        query.append("       WHERE  comp_no  = x.comp_no                        		");
        query.append("         AND  wcode_id = x.wcode_id)          wname,      		"); //창고명         
        query.append("       x.vendor_id                            vendorId,   		"); 
        query.append("      (SELECT description FROM TAVENDOR                   		");                                  
        query.append("       WHERE  comp_no   = x.comp_no                       		");
        query.append("         AND  vendor_id = x.vendor_id)        vendorDesc, 		"); 
        query.append("       x.repair_date                          repairDate, 		"); 
        query.append("       x.request_date                         requestDate, 		"); 
        query.append("       x.reg_date                             regDate, 			"); 
        query.append("       x.part_id                              partId,     		"); 
        query.append("       y.part_no                              partNo,     		");
        query.append("       y.description||', '||y.pt_size         partNameSize,		");
        query.append("       NVL(x.repair_qty, 0)                   repairQty,  		"); 
        query.append("       NVL(x.unit_price, 0)                   unitPrice,  		"); 
        query.append("       NVL(x.tot_price, 0)                    totPrice,   		"); 
        query.append("       x.inspector                            inspector,  		");  
        query.append("       SFAIDTODESC(x.inspector, '', 'EMP', x.comp_no) inspectorName,	");  
        query.append("       x.request_By                            requestBy,  		");  
        query.append("       SFAIDTODESC(x.request_By, '', 'EMP', x.comp_no) requestName, 	");  
        query.append("       x.remark                               remark,      		");
        query.append("       y.is_serial_part                       isSerial,      		");
        query.append("       x.equip_id                             equipId,      		"); 
        query.append("       (SELECT item_no FROM taequipment 							");
        query.append("       WHERE equip_id = x.equip_id								");
        query.append("            AND comp_no = x.comp_no) 			itemNo,				");
        query.append("       (SELECT description FROM taequipment 						");
        query.append("       WHERE equip_id = x.equip_id 								");
        query.append("            AND comp_no = x.comp_no) 			equipName,  		");
        query.append("       x.serial_no                            serialNo      		");
        query.append("       ,x.plant                        		plantId				");
        query.append("       ,(SELECT description                            			");
        query.append("          FROM TAPLANT                                 			");
        query.append("          WHERE comp_no = x.comp_no                    			");
        query.append("            AND plant = x.plant)       		plantDesc  			");
        query.append("       ,x.eqasmb_id                    		eqasmbId			");
        query.append("       ,(SELECT description                            			");
        query.append("          FROM TAEQASMB                                 			");
        query.append("          WHERE comp_no = x.comp_no                    			");
        query.append("            AND eqasmb_id = x.eqasmb_id)      eqasmbDesc  		");
        query.append("       ,x.part_grade                          partGrade           ");  
        query.append("       ,SFACODE_TO_DESC(x.part_grade, 'PART_GRADE', 'SYS', x.comp_no,'"+loginUser.getLangId()+"') partGradeDesc   ");  
        query.append("FROM   TAPTREPAIRLIST x ,TAPARTS y                        		");
        query.append("WHERE  x.comp_no      = y.comp_no(+)	                    		");
        query.append("  AND  x.part_id      = y.part_id(+)                      		");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.ptrepairlist_id", maPtRepCommonDTO.getPtRepairListId());
    
        return (MaPtRepDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPtRepDetailDTO()));
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepDetailDTO
     * @return
     */
    public int insertDetail(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAPTREPAIRLIST (                              ");
    	query.append("  comp_no,   ptrepairlist_id, ptrepairlist_no, ptrepairlist_status,");
    	query.append("  wkor_id,   wopart_id,       dept_id,         wcode_id,  ");
    	query.append("  vendor_id, repair_date,     part_id,         repair_qty,");
    	query.append("  unit_price,tot_price, 	    inspector,       remark,    ");
    	query.append("  reg_date, request_date,request_by,           serial_no, ");
    	query.append("  equip_id,  part_grade,		plant,			 eqasmb_id	");
    	query.append(")	VALUES (                                                ");
    	query.append("  ?,          ?,             ?,            ?,             ");
    	query.append("  ?,          ?,             ?,            ?,             ");
    	query.append("  ?,          ?,             ?,            ?,             ");
    	query.append("  ?,          ?,             ?,            ?,             ");
    	query.append("  ?,          ?,             ?,            ?,             ");
    	query.append("  ?,          ?,			   ?,			 ?				");
    	query.append(")                                                         ");
    	
    	Object[] objects = new Object[] {
    			maPtRepDetailDTO.getCompNo(),
    			maPtRepDetailDTO.getPtRepairListId(),
    			maPtRepDetailDTO.getPtRepairListNo(),
    			maPtRepDetailDTO.getPtRepairListStatus(),
    			maPtRepDetailDTO.getWkorId(),
    			maPtRepDetailDTO.getWoPartId(),
    			maPtRepDetailDTO.getDeptId(),
    			maPtRepDetailDTO.getWcodeId(),
    			maPtRepDetailDTO.getVendorId(),
    			maPtRepDetailDTO.getRepairDate(),
    			maPtRepDetailDTO.getPartId(),
    			maPtRepDetailDTO.getRepairQty(),
    			maPtRepDetailDTO.getUnitPrice(),
    			maPtRepDetailDTO.getTotPrice(),
    			maPtRepDetailDTO.getInspector(),
    			maPtRepDetailDTO.getRemark(),
    			maPtRepDetailDTO.getRegDate(),
    			maPtRepDetailDTO.getRequestDate(),
    			maPtRepDetailDTO.getRequestBy(),
    			maPtRepDetailDTO.getSerialNo(),
    			maPtRepDetailDTO.getEquipId(),
    			maPtRepDetailDTO.getPartGrade(),
    			maPtRepDetailDTO.getPlantId(),
    			maPtRepDetailDTO.getEqAsmbId()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail 
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepDetailDTO
     * @return
     */
    public int updateDetail(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAPTREPAIRLIST SET		      ");
        query.append("       dept_id             = ?      ");
        query.append("       ,vendor_id          = ?      ");    
        query.append("       ,repair_date        = ?      ");    
        query.append("       ,part_id            = ?      ");
        query.append("       ,wcode_id           = ?      ");
        query.append("       ,repair_qty         = ?      ");     
        query.append("       ,unit_price         = ?      ");  
        query.append("       ,tot_price          = ?      ");
        query.append("       ,inspector          = ?      ");
        query.append("       ,request_date       = ?      ");
        query.append("       ,request_by         = ?      ");
        query.append("       ,remark             = ?      ");
        query.append("       ,serial_no          = ?      ");
        query.append("       ,part_grade         = ?      ");
        query.append("       ,equip_id           = ?      ");
        query.append("       ,plant           	 = ?      ");
        query.append("       ,eqasmb_id          = ?      ");
    	query.append("WHERE  comp_no            = ?	      ");
    	query.append("  AND  ptrepairlist_id    = ?       ");
    	
    	Object[] objects = new Object[] {
                maPtRepDetailDTO.getDeptId(),
                maPtRepDetailDTO.getVendorId(),
                maPtRepDetailDTO.getRepairDate(),
                maPtRepDetailDTO.getPartId(),
                maPtRepDetailDTO.getWcodeId(),
                maPtRepDetailDTO.getRepairQty(),
                maPtRepDetailDTO.getUnitPrice(),
                maPtRepDetailDTO.getTotPrice(),
                maPtRepDetailDTO.getInspector(),
                maPtRepDetailDTO.getRequestDate(),
                maPtRepDetailDTO.getRequestBy(),
                maPtRepDetailDTO.getRemark(),
                maPtRepDetailDTO.getSerialNo(),
                maPtRepDetailDTO.getPartGrade(),
                maPtRepDetailDTO.getEquipId(),
                maPtRepDetailDTO.getPlantId(),
                maPtRepDetailDTO.getEqAsmbId(),
                maPtRepDetailDTO.getCompNo(),
                maPtRepDetailDTO.getPtRepairListId()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * update 
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepDetailDTO
     * @return
     */
    public int updatePtRepairListStatus(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAPTREPAIRLIST SET		        ");
        query.append("       ptrepairlist_status  = ?       ");
        //의뢰의 경우 의뢰자가 없으면 로그인 사원정보 입력 
        if("S".equals(maPtRepDetailDTO.getPtRepairListStatus())){
        	if("".equals(maPtRepDetailDTO.getRequestBy())){
        		query.append("  ,request_by  = '"+loginUser.getEmpId()+"'       ");
        	}else{
        		query.append("  ,request_by  = '"+maPtRepDetailDTO.getRequestBy()+"'       ");
        	}
        }
        query.append("WHERE  comp_no              = ?	    ");
        query.append("  AND  ptrepairlist_id      = ?       ");
        
        Object[] objects = new Object[] {
                maPtRepDetailDTO.getPtRepairListStatus(),
                maPtRepDetailDTO.getCompNo(),
                maPtRepDetailDTO.getPtRepairListId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * find status 
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepDetailDTO
     * @return
     */
    public String findPtRepairListStatus(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT ptrepairlist_status        ");    
        query.append("FROM   TAPTREPAIRLIST             ");
        query.append("WHERE  1=1                        ");
        query.getAndQuery("comp_no", maPtRepDetailDTO.getCompNo());
        query.getAndQuery("ptrepairlist_id", maPtRepDetailDTO.getPtRepairListId());
                
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
       
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtRepDetailDTO
     * @return
     */
    public String validPtRepairListNo(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser)
    {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*)                                           ");
        query.append("FROM   TAPTREPAIRLIST x                                   ");
        query.append("WHERE  1=1                                                ");
        query.getAndQuery("x.comp_no", maPtRepDetailDTO.getCompNo());
        query.getAndQuery("x.ptrepairlist_no", maPtRepDetailDTO.getPtRepairListNo());
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
  /**
   * 입고 History insert
   * @author ssong
   * @version $Id:$
   * @since   1.0
   * 
   * @param maPtRecDetailDTO
   * @return
   */
  public int insertRecHistory(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser)
  {
      QueryBuffer query = new QueryBuffer();

      query.append("INSERT INTO TAPTRECHIST (                                          ");
      query.append("       comp_no, ptrechist_id                                       ");
      query.append("       ,ptrec_mode                                                 ");
      query.append("       ,ptrec_type                                                 ");
      query.append("       ,reclist_id, dept_id, wcode_id, vendor_id                   ");
      query.append("       , rec_date, part_id, part_grade,rec_qty                     ");
      query.append("       , unit_price, tot_price )                                  ");
      
      query.append("SELECT x.comp_no,                                           ");
      query.append("       '"+maPtRepDetailDTO.getPtRecHistId()+"' ptRecHistId, ");
      query.append("       '"+maPtRepDetailDTO.getPtRecMode()+"'   ptrecMode,   ");
      query.append("       'REPAIR'                                ptrecType,   ");
      query.append("       ptrepairlist_id,  dept_id,    x.wcode_id,    vendor_id,  ");
      query.append("       repair_date,      x.part_id,  x.part_grade,  repair_qty, ");
      query.append("       unit_price,       tot_price                          ");
      query.append("FROM   TAPTREPAIRLIST x                                     ");
      query.append("WHERE  1=1                                                  ");
      query.getAndQuery("x.comp_no", maPtRepDetailDTO.getCompNo());
      query.getAndQuery("x.ptrepairlist_id", maPtRepDetailDTO.getPtRepairListId());
      
      return this.getJdbcTemplate().update(query.toString());
  }
  
  /**
   * 입고완료 전에 SP_PT_INSTOCK 프로시져 호출 
   * @author  ssong
   * @version $Id:$
   * @since   1.0
   * 
   * @param maPtRepDetailDTO
   * @param loginUser
   * @return
   * @throws Exception
   */
  public int executeSpPtInstock(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser) throws Exception
  {
      QueryBuffer query = new QueryBuffer();

      query.append("begin                                                     ");
      query.append("SP_PT_INSTOCK('"+maPtRepDetailDTO.getCompNo()+"', '"+maPtRepDetailDTO.getPtRecHistId()+"' ); ");
      query.append("end;                                                      ");
    
      this.getJdbcTemplate().execute(query.toString());
      
      return 0;
  }
  /**
   * 입고취소  SP_PT_OUTSTOCK 프로시져 호출 
   * @author  kim21017
   * @version $Id:$
   * @since   1.0
   * 
   * @param maPtRepDetailDTO
   * @param loginUser
   * @return
   * @throws Exception
   */
  public int executeSpPtOutstock(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser) throws Exception
  {
      QueryBuffer query = new QueryBuffer();

      query.append("begin                                                     ");
      query.append("SP_PT_OUTSTOCK('"+loginUser.getCompNo()+"', '"+maPtRepDetailDTO.getPtRecHistId()+"' ); ");
      query.append("end;                                                      ");
    
      this.getJdbcTemplate().execute(query.toString());
      
      return 0;
  }
  
  /**
   * update 
   * @author ssong
   * @version $Id:$
   * @since   1.0
   * 
   * @param maPtRepDetailDTO
   * @return
   */
  public int updateEquipment(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser, String status)
  {
      QueryBuffer query = new QueryBuffer();
      
      query.append("UPDATE TAEQUIPMENT SET		        ");
      query.append("       eq_status  = ?       ");
      query.append("WHERE  comp_no    = ?       ");
      query.append("  AND  equip_id   = ?       ");
      
      Object[] objects = new Object[] {
              status
              ,maPtRepDetailDTO.getCompNo()
              ,maPtRepDetailDTO.getEquipId()
      };
      
      return this.getJdbcTemplate().update(query.toString(), objects);
  }
  
  
  public int insertEqHistory(MaPtRepDetailDTO maPtRepDetailDTO,User user, String status)
  {
  	QueryBuffer query = new QueryBuffer();

    query.append("INSERT INTO TAEQHISTORY(          							  ");
    query.append("       comp_no         ,eqhistory_id                     		  ");
    query.append("       ,item_no        ,serial_no              ,wkor_id         ");
    query.append("       ,pminslist_id   ,ptrepairlist_id        ,description     ");
    query.append("       ,eq_status      ,wo_type                ,wkor_date       ");
    query.append("       ,tot_amt        ,start_date             ,start_time      ");
    query.append("       ,end_date       ,end_time               ,work_time       ");
    query.append("       ,dept_id        ,wkctr_id               ,emp_id          ");
    query.append("       ,perform		 ,plant                                   ");
    query.append(")VALUES(          											  ");
    query.append("        ?,             SQAEQHISTORY_ID.NEXTVAL   				  ");
    query.append("       ,(SELECT item_no                 ");
    query.append("         FROM TAEQUIPMENT               ");
    query.append("         WHERE equip_id= ?)             ");//item_no
    query.append("        ,?                              ");//serialNo
    query.append("        ,''                             ");//wkor_id
    query.append("        ,''                             ");//pminslist_id
    query.append("       ,?             ");//repair_id
    query.append("       ,''               ");//desc
    query.append("       , ?          ");//설비상태
    query.append("       ,''               ");//wo_type
    query.append("       ,''               ");//작업일자
    query.append("       ,?               ");//총작업금액
    query.append("       ,''              ");//from작업일자
    query.append("       ,''              ");//from 작업시간
    query.append("       ,''               ");//to작업일자
    query.append("       ,''              ");//to작업시간
    query.append("       ,''               ");//=작업시간
    query.append("       ,?               ");//부서
    query.append("       ,''               ");//워크센터
    query.append("       ,?               ");//emp
    query.append("       ,''             ");
    query.append("       ,?             ");//plant
    query.append(")                                       ");
  	
  	Object[] objects = new Object[] {
  	       maPtRepDetailDTO.getCompNo()//1
  	       ,maPtRepDetailDTO.getEquipId()//itemNo2
  	       ,maPtRepDetailDTO.getSerialNo()//3
  	       ////eqstatus4
  	       //pminslist_id
  	       ,maPtRepDetailDTO.getPtRepairListId()//5
  	       ,status//설비상태9
  	       //작업일자
  	       ,maPtRepDetailDTO.getTotPrice()//11
  	       //from작업일자
  	       //작업시간
  	       //to작업일자
  	       //to작업시간
  	       //작업시간
  	       ,user.getDeptId()//12
  	       //워크센터Id   
  	       ,user.getEmpId()//13
  	       ////14
  	       ,user.getPlant()
  	};  	
  	return this.getJdbcTemplate().update(query.toString(), objects);

  }
}