package dream.asset.rpt.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.asset.rpt.dao.AssetRptWorkHistDetailDAO;
import dream.asset.rpt.dto.AssetRptWorkHistCommonDTO;
import dream.asset.rpt.dto.AssetRptWorkHistDetailDTO;

/**
 * 설비이력(과거) - Detail DAO implements
 * @author js.lee
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="assetRptWorkHistDetailDAOTarget"
 * @spring.txbn id="assetRptWorkHistDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptWorkHistDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements AssetRptWorkHistDetailDAO
{
	
    public AssetRptWorkHistDetailDTO findRptWorkHistDetail(AssetRptWorkHistCommonDTO assetRptWorkHistCommonDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        String compNo = user.getCompNo();
        
        query.append("  SELECT    y.description   																						AS EQUIPNAME		");
        query.append("            , x.EQASMB_DESC   																					AS EQASMBDESC		");
        query.append("            , (SELECT FULL_DESC FROM TAEQLOC WHERE EQLOC_ID = y.EQLOC_ID)											AS EQLOCDESC		");
        query.append("            , (SELECT full_desc FROM taeqctg WHERE eqctg_id = y.eqctg_id)   										AS EQTYPE			");
        query.append("            , x.wkor_date           																				AS WKORDATE			");
        query.append("            , x.wo_type           																				AS WOTYPEID			");
        query.append("            , SFACODE_TO_DESC(x.WO_TYPE, 'WO_TYPE', 'SYS','', ?)  					  							AS WOTYPE			");
        query.append("            , x.description     																					AS DESCRIPTION		");
        query.append("            , (SELECT description FROM tadept WHERE dept_id = x.dept_id AND comp_no = x.comp_no)					AS DEPTDESC			");
        query.append("			  , NVL(x.EMP_NAME, (SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.emp_id AND COMP_NO = x.COMP_NO)) AS EMPNAME			");
        query.append("            , X.VENDOR_NAME    																					AS VENDORNAME		");
        query.append("            , X.TOT_AMT        																					AS TOTAMT			");
        query.append("            , x.perform        																					AS PERFORM			");
        query.append("            , x.CA_DESC																							AS CADESC			");
        query.append("            , x.RE_DESC																							AS REDESC			");
        query.append("            , x.start_date     																					AS STARTDATE		");
        query.append("            , x.start_time     																					AS STARTTIME		");
        query.append("            , X.end_date   																						AS ENDDATE			");
        query.append("            , X.end_time   																						AS ENDTIME			");
        query.append("            , x.work_time  																						AS WORKTIME			");
        query.append("            , y.equip_id  																						AS EQUIPID	 		");
        query.append("            , x.item_no     																						AS ITEMNO    		");
        query.append("            , x.eqhistory_id     																					AS EQHISTORYID  	");
        query.append("            , x.dept_id     																						AS DEPTID  			");
        query.append("            , x.emp_id     																						AS EMPID  			");
        query.append("            , x.wkor_id                                                                                        	AS WKORID			");
        query.append("            ,(SELECT param1 FROM tacdsysd WHERE list_Type= x.wo_type||'_TYPE' AND cdsysd_no=z.pm_type)    		AS WOPARAM			");
        query.append("            , x.eqhist_gen_type																		            AS EQHISTGENTYPE	");
        query.append("  FROM TAEQHISTORY x LEFT OUTER JOIN TAEQUIPMENT y                                                                                	");
        query.append("  ON y.ITEM_NO = x.ITEM_NO AND y.IS_LAST_VERSION = 'Y' AND y.COMP_NO = x.COMP_NO  													");
        query.append("  LEFT OUTER JOIN TAWORKORDER z																										");
        query.append("  ON x.COMP_NO = z.COMP_NO AND x.WKOR_ID = z.WKOR_ID   																				");
        query.append("  WHERE  1=1                                       																					");
        query.append("	AND x.COMP_NO = ?																													");
        query.append("	AND x.EQHISTORY_ID = ?																												");

        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,compNo
        		,assetRptWorkHistCommonDTO.getEqHistoryId()
    	};
    
        AssetRptWorkHistDetailDTO assetRptWorkHistDetailDTO = 
        		(AssetRptWorkHistDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new AssetRptWorkHistDetailDTO()));
        
        return assetRptWorkHistDetailDTO;
        
    }
    

    public int insertRptWorkHistDetail(AssetRptWorkHistDetailDTO assetRptWorkHistDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TAEQHISTORY (		");
    	query.append("     	comp_no					");
    	query.append("     ,eqhistory_id			");
    	query.append("     ,item_no       			");
    	query.append("     ,eqasmb_desc   			");
    	query.append("     ,wkor_date        		");
    	query.append("     ,wo_type        			");
    	query.append("     ,description    			");
    	query.append("     ,dept_id        			");
    	query.append("     ,emp_name        		");
    	query.append("     ,emp_id            		");
    	query.append("     ,vendor_name    			");
    	query.append("     ,tot_amt        			");
    	query.append("     ,perform        			");
    	query.append("     ,ca_desc        			");
    	query.append("     ,re_desc        			");
    	query.append("     ,start_date        		");
    	query.append("     ,start_time        		");
    	query.append("     ,end_date        		");
    	query.append("     ,end_time        		");
    	query.append("     ,work_time				");
    	query.append("     ,eqhist_gen_type			");
    	query.append("   ) VALUES(					");
    	query.append("     ?						");
    	query.append("    ,?						");
    	query.append("    ,?						");
    	query.append("    ,?						");
    	query.append("    ,?						");
    	query.append("    ,?						");
    	query.append("    ,?						");
    	query.append("    ,?						");
    	query.append("    ,?						");
    	query.append("    ,?						");
    	query.append("    ,?						");
    	query.append("    ,?						");
    	query.append("    ,?						");
    	query.append("    ,?						");
    	query.append("    ,?						");
    	query.append("    ,?						");
    	query.append("    ,?						");
    	query.append("    ,?						");
    	query.append("    ,?						");
    	query.append("    ,?						");
    	query.append("    ,?						");
    	query.append("    )							");

    	
    	Object[] objects = new Object[] {
    			 user.getCompNo()
    			,assetRptWorkHistDetailDTO.getEqHistoryId()
    			,assetRptWorkHistDetailDTO.getItemNo()
    			,assetRptWorkHistDetailDTO.getEqAsmbDesc()
    			,assetRptWorkHistDetailDTO.getWkorDate()
    			,assetRptWorkHistDetailDTO.getWoTypeId()
    			,assetRptWorkHistDetailDTO.getDescription()
    			,assetRptWorkHistDetailDTO.getDeptId()
    			,assetRptWorkHistDetailDTO.getEmpName()
    			,assetRptWorkHistDetailDTO.getEmpId()
    			,assetRptWorkHistDetailDTO.getVendorName()
    			,assetRptWorkHistDetailDTO.getTotAmt()
    			,assetRptWorkHistDetailDTO.getPerform()
    			,assetRptWorkHistDetailDTO.getCaDesc()
    			,assetRptWorkHistDetailDTO.getReDesc()
    			,assetRptWorkHistDetailDTO.getStartDate()
    			,assetRptWorkHistDetailDTO.getStartTime()
    			,assetRptWorkHistDetailDTO.getEndDate()
    			,assetRptWorkHistDetailDTO.getEndTime()
    			,assetRptWorkHistDetailDTO.getWorkTime()
    			,assetRptWorkHistDetailDTO.getEqHistGenType()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    
    
    public int updateRptWorkHistDetail(AssetRptWorkHistDetailDTO assetRptWorkHistDetailDTO, User user)
    {
    	
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAEQHISTORY SET		");
    	query.append("	  	 item_no 			= ?	");
    	query.append("	   , eqasmb_desc 		= ?	");
    	query.append("	   , wkor_date 			= ?	");
    	query.append("	   , wo_type 			= ?	");
    	query.append("	   , description 		= ?	");
    	query.append("	   , dept_id 			= ?	");
    	query.append("	   , emp_name 			= ?	");
    	query.append("	   , emp_id 			= ?	");
    	query.append("	   , vendor_name		= ?	");
    	query.append("	   , tot_amt 			= ?	");
    	query.append("	   , perform 			= ?	");
    	query.append("	   , ca_desc 			= ?	");
    	query.append("	   , re_desc 			= ?	");
    	query.append("	   , start_date 		= ?	");
    	query.append("	   , start_time 		= ?	");
    	query.append("	   , end_date 			= ?	");
    	query.append("	   , end_time 			= ?	");
    	query.append("	   , work_time 			= ?	");
    	query.append("	   , eqhist_gen_type	= ?	");
    	query.append("WHERE eqhistory_id 		= ?	");
    	query.append("	AND comp_no    			= ?	");
    	
    	Object[] objects = new Object[] {
    			 assetRptWorkHistDetailDTO.getItemNo()
      		   , assetRptWorkHistDetailDTO.getEqAsmbDesc()
      		   , assetRptWorkHistDetailDTO.getWkorDate()
      		   , assetRptWorkHistDetailDTO.getWoTypeId()
      		   , assetRptWorkHistDetailDTO.getDescription()
      		   , assetRptWorkHistDetailDTO.getDeptId()
      		   , assetRptWorkHistDetailDTO.getEmpName()
      		   , assetRptWorkHistDetailDTO.getEmpId()
      		   , assetRptWorkHistDetailDTO.getVendorName()
      		   , assetRptWorkHistDetailDTO.getTotAmt()
      		   , assetRptWorkHistDetailDTO.getPerform()
      		   , assetRptWorkHistDetailDTO.getCaDesc()
      		   , assetRptWorkHistDetailDTO.getReDesc()
      		   , assetRptWorkHistDetailDTO.getStartDate()
      		   , assetRptWorkHistDetailDTO.getStartTime()
      		   , assetRptWorkHistDetailDTO.getEndDate()
      		   , assetRptWorkHistDetailDTO.getEndTime()
      		   , assetRptWorkHistDetailDTO.getWorkTime()
      		   , assetRptWorkHistDetailDTO.getEqHistGenType()
      		   , assetRptWorkHistDetailDTO.getEqHistoryId()
      		   , user.getCompNo()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}