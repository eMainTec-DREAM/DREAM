package intf.dream.ant.asset.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import intf.dream.ant.common.AntCommonValues;
import intf.dream.ant.asset.dao.AntAssetListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="antAssetListDAOTarget"
 * @spring.txbn id="antAssetListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AntAssetListDAOOraImpl extends BaseJdbcDaoSupportOra implements AntAssetListDAO
{
	public List findAssetList(Map map) throws Exception
    {
    	QueryBuffer query = new QueryBuffer(); 
    	
    	String compNo = String.valueOf(map.get("compNo"));
    	String startDate = String.valueOf(map.get("startDate"));
    	String endDate = String.valueOf(map.get("endDate"));
    	String plant = String.valueOf(map.get("plant"));

    	String deptId = String.valueOf(map.get("deptId"));
    	String empId = String.valueOf(map.get("empId"));
    	String wkctrId = String.valueOf(map.get("wkctrId"));
    	String eqctgType = String.valueOf(map.get("eqctgType"));
    	String eqlocId = String.valueOf(map.get("eqlocId"));

    	query.append("SELECT						");
    	query.append("		a.COMP_NO				");
    	query.append("		,a.EQUIP_ID				");
    	query.append("		,a.ITEM_NO				");
    	query.append("		,a.DESCRIPTION			");
    	query.append("		,a.EQLOC_ID				");
    	query.append("		,a.EQUIPCTG_ID			");
    	query.append("		,a.EQ_STATUS			");
    	query.append("		,a.DEPT_ID				");
    	query.append("		,a.MAIN_MNG_ID			");
    	query.append("		,a.SUB_MNG_ID			");
    	query.append("		,a.SETUP_DATE			");
    	query.append("		,a.BUY_AMT				");
    	query.append("		,a.PLF_TYPE				");
    	query.append("		,a.MAKER				");
    	query.append("		,a.MODEL_NO				");
    	query.append("		,a.CAPACITY				");
    	query.append("		,a.UTIL_CAPA			");
    	query.append("		,a.REMARK				");
    	query.append("		,a.EXCEL_NO				");
    	query.append("		,a.AS_VENDOR			");
    	query.append("		,a.AS_NAME				");
    	query.append("		,a.AS_PHONE				");
    	query.append("		,a.PLANT				");
    	query.append("		,a.EQCTG_TYPE			");
    	query.append("FROM TAEQUIPMENT a			");
    	query.append("WHERE 1=1						");
    	query.getStringEqualQuery("a.comp_no", compNo);
        query.append("  AND a.plant = '"+plant+"'			");
        query.getAndQuery("a.rec_emp_id", empId);
        query.getWkCtrLevelQuery("a.wkctr_id", wkctrId, "", compNo);
        query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	
    	return getJdbcTemplate().queryForList(query.toString());
    } 
	public List findAssetFileList(Map map) throws Exception
    {
    	QueryBuffer query = new QueryBuffer(); 
    	
    	String compNo = String.valueOf(map.get("compNo"));
    	String startDate = String.valueOf(map.get("startDate"));
    	String endDate = String.valueOf(map.get("endDate"));
    	String plant = String.valueOf(map.get("plant"));

    	String deptId = String.valueOf(map.get("deptId"));
    	String empId = String.valueOf(map.get("empId"));
    	String wkctrId = String.valueOf(map.get("wkctrId"));
    	String eqctgType = String.valueOf(map.get("eqctgType"));
    	String eqlocId = String.valueOf(map.get("eqlocId"));

    	query.append("SELECT x.comp_no AS comp_no					");
    	query.append("		,x.docdata_id AS file_id				");
    	query.append("		,y.object_type AS object_type			");
    	query.append("		,(SELECT a.object_id FROM TAOBJDOC a WHERE a.comp_no = x.comp_no AND a.doc_id = x.doc_id)   AS object_id	");
    	query.append("		,y.dept_id AS dept_id					");
    	query.append("		,y.reg_id AS reg_id						");
    	query.append("		,y.reg_date AS reg_date					");
    	query.append("		,x.nf_file_path AS file_path			");
    	query.append("		,x.file_name AS file_name				");
    	query.append("		,x.file_ext AS extension				");
    	query.append("		,'' AS remark							");
    	query.append("FROM TADOCDATA x INNER JOIN TADOCUMENT y		");
    	query.append("	ON x.comp_no = y.comp_no					");
    	query.append("	AND x.doc_id = y.doc_id						");
    	query.append("WHERE 1=1										");
    	query.getStringEqualQuery("x.comp_no", compNo);
    	query.append("AND x.file_ext IN ((SELECT TRIM(REGEXP_SUBSTR(value$, '[^,]+', 1, LEVEL))											");
    	query.append("						FROM (SELECT value$ FROM TACONFIG WHERE comp_no = '"+compNo+"' AND NAME='IMG_FILE_TYPE')	");
    	query.append("						CONNECT BY  INSTR(value$, ',', 1, LEVEL - 1) > 0))											");
    	query.append("AND x.doc_id IN (SELECT doc_id 				");
    	query.append("					FROM TAOBJDOC				");
    	query.append("					WHERE 1=1					");
    	query.getStringEqualQuery("comp_no", compNo);
    	query.getStringEqualQuery("object_type", "EQMSTR");
    	query.append("					AND object_id IN ( 							");
    	query.append("									SELECT						");
    	query.append("											a.EQUIP_ID			");
    	query.append("									FROM TAEQUIPMENT a			");
    	query.append("									WHERE 1=1					");
    	query.getStringEqualQuery("a.comp_no", compNo);
        query.append("  AND a.plant 	=  '"+plant+"'								");
        
        query.getWkCtrLevelQuery("a.wkctr_id", wkctrId, "", compNo);
        query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
        
    	return getJdbcTemplate().queryForList(query.toString());
    } 

//    public int insertAssetList(Map map, String woReqId)
//    {
//        QueryBuffer query = new QueryBuffer();
//        Object[] objects;
//
//        query.append("INSERT INTO TAEQUIPMENT (                                                     ");
//        query.append("     COMP_NO, EQUIP_ID, ITEM_NO, DESCRIPTION, EQLOC_ID                    ");
//        query.append("     , EQ_STATUS, DEPT_ID, MAIN_MNG_ID          							");
//        query.append("     , SUB_MNG_ID, SETUP_DATE, BUY_AMT, PLF_TYPE, MAKER		            ");
//        query.append("     , MODEL_NO, CAPACITY, UTIL_CAPA, REMARK, EXCEL_NO		            ");
//        query.append("     , AS_VENDOR, AS_NAME, AS_PHONE, PLANT, EQCTG_TYPE                    ");
//        query.append("      )                                                                   ");
//        query.append("VALUES (                                                                  ");
//        query.append("      ?,?,?,?,?                                                           ");
//        query.append("     ,?,?,?,?,?                                                           ");
//        query.append("     ,?,?,?,?,?                                                           ");
//        query.append("     ,?,?,?,?,?                                                           ");
//        query.append("     ,?,?,?                                                               ");
//        query.append("         )                                                                ");
//        
//        objects = new Object[] {
//        		convertString(map.get("compNo"))
//        		,convertString(map.get("equipId"))
//        		,convertString(map.get("itemNo"))
//        		,convertString(map.get("description"))
//        		,convertString(map.get("eqLocId"))
//        		,convertString(map.get("eqStatus"))
//        		,convertString(map.get("deptId"))
//        		,convertString(map.get("mainMngId"))
//        		,convertString(map.get("subMngId"))
//        		,convertString(map.get("setUpDate"))
//        		,convertString(map.get("buyAmt"))
//        		,convertString(map.get("plfType"))
//        		,convertString(map.get("marker"))
//        		,convertString(map.get("modelNo"))
//        		,convertString(map.get("capaCity"))
//        		,convertString(map.get("utilCapa"))
//        		,convertString(map.get("remark"))
//        		,convertString(map.get("excelNo"))
//        		,convertString(map.get("asVendor"))
//        		,convertString(map.get("asName"))
//        		,convertString(map.get("asPhone"))
//        		,convertString(map.get("plant"))
//        		,convertString(map.get("eqCtgType"))
//        };
//        return getJdbcTemplate().update(query.toString(), objects);
//    }
    public int changeFileSeq(Map map, String equipId)
    {
        QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("UPDATE TADOCUMENT SET                  ");
        query.append("  doc_no            = ?,               ");
        query.append("  remark            = ?                ");
        query.append("WHERE doc_no        = ?                ");
        query.append("    and object_type = ?                ");
        query.append("    and comp_no     = ?                ");

        
        objects = new Object[] {
        		equipId,
        		convertString(map.get("remark")),
                convertString(map.get("equipId")),
                "EQMSTR",
                convertString(map.get("compNo"))
        };
        getJdbcTemplate().update(query.toString(), objects);
        
        query = new QueryBuffer();
        query.append("UPDATE TAOBJDOC SET                   ");
        query.append("  object_id           = ?             ");
        query.append("WHERE object_id       = ?             ");
        query.append("    and object_type   = ?             ");
        query.append("    and comp_no       = ?             ");

        
        objects = new Object[] {
        		equipId,
                convertString(map.get("equipId")),
                "EQMSTR",
                convertString(map.get("compNo"))
        };
        
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
    

}