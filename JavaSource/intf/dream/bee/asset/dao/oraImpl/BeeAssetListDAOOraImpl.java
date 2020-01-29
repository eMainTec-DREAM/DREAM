package intf.dream.bee.asset.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QueryBuffer;
import intf.dream.bee.asset.dao.BeeAssetListDAO;
import intf.dream.bee.asset.dto.BeeAssetCommonDTO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="beeAssetListDAOTarget"
 * @spring.txbn id="beeAssetListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeeAssetListDAOOraImpl extends BaseJdbcDaoSupportOra implements BeeAssetListDAO
{
	public List findAssetList(BeeAssetCommonDTO beeAssetCommonDTO, Map map) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 						");
    	query.append("       a.comp_no				");
    	query.append("      ,a.equip_id				");
        query.append("      ,a.item_no				");
        query.append("      ,a.description			");
        query.append("      ,a.eqloc_id				");
        query.append("      ,(SELECT x.full_desc                         ");
        query.append("          FROM TAEQLOC x                           ");
        query.append("         WHERE x.comp_no = a.comp_no               ");
        query.append("           AND x.eqloc_id = a.eqloc_id) EQLOC_DESC ");
        query.append("      ,a.eqctg_id									 ");
        query.append("      ,(SELECT x.full_desc                         ");
        query.append("          FROM TAEQCTG x                           ");
        query.append("         WHERE x.comp_no = a.comp_no               ");
        query.append("           AND x.eqctg_id = a.eqctg_id) EQCTG_DESC ");
        query.append("      ,a.eq_status			");
        query.append("      ,a.dept_id				");
        query.append(" 		,(SELECT x.description FROM TADEPT x WHERE x.comp_no = a.comp_no AND x.dept_id = a.dept_id) AS dept_desc		");
        query.append("      ,a.main_mng_id			");
        query.append("      ,(SELECT z.emp_name FROM taemp z WHERE  z.comp_no = a.comp_no AND z.emp_id = a.main_mng_id) AS main_mng_desc	");
        query.append("      ,a.sub_mng_id			");
        query.append("      ,a.setup_date			");
        query.append("      ,a.buy_amt				");
        query.append("      ,a.plf_type				");
        query.append("      ,a.maker				");
        query.append("      ,a.model_no				");
        query.append("      ,a.capacity				");
        query.append("      ,a.util_capa			");
        query.append("      ,a.is_law_eq			");
        query.append("      ,a.guaranty_date		");
        query.append("      ,a.ord_no				");
        query.append("      ,a.remark				");
        query.append("      ,a.excel_no				");
        query.append("      ,a.as_vendor			");
        query.append("      ,a.as_name				");
        query.append("      ,a.as_phone				");
        query.append("      ,a.old_eq_no			");
        query.append("      ,a.serial_no			");
        query.append("      ,a.eq_grade				");
        query.append("      ,a.plant				");
        query.append("      ,a.storage				");
        query.append(" 		,(SELECT x.description FROM TAPLANT x WHERE x.comp_no = a.comp_no AND x.plant = a.plant) AS plant_desc		");
        query.append("      ,a.eqctg_type			");
        query.append("      ,'' eqstrloc_no			");
        
        if(!"".equals(CommonUtil.convertString(String.valueOf(map.get("equipId")))))
        {
        	query.append(this.getDetailColumn(beeAssetCommonDTO, map));
        }
        
        
        query.append("FROM TAEQUIPMENT a										      ");
        query.append("WHERE 1=1					");
        
        query.append(this.getAssetWhere(beeAssetCommonDTO, map));

        query.getOrderByQuery("a.ord_no, a.item_no", "", "");
        
        return getJdbcTemplate().queryForList(query.toString(beeAssetCommonDTO.getIsLoadMaxCount(), beeAssetCommonDTO.getFirstRow()));
        
    }
	
	private String getDetailColumn(BeeAssetCommonDTO beeAssetCommonDTO, Map map)
    {        
     	
        QueryBuffer query = new QueryBuffer();
        
        query.append("      ,(SELECT count(*)                            ");
        query.append("        FROM TAEQUIPMENT x                         ");
        query.append("        WHERE x.comp_no = a.comp_no                ");
        query.append("        AND x.p_equip_id = a.equip_id              ");
        query.append("        AND x.eqctg_type='TL') AS eqTool_Cnt       ");
        query.append("      ,a.tag_no				");
        query.append("      ,a.usage_dept			");
        query.append(" 		,(SELECT x.description FROM TADEPT x WHERE x.comp_no = a.comp_no AND x.dept_id = a.usage_dept) AS usage_dept_desc		");
        query.append(" 		,(SELECT count(*)											");
        query.append("			FROM TAIMAGE x INNER JOIN TAIMGDATA y					");
        query.append("			ON x.comp_no = y.comp_no								");
        query.append("			AND x.image_id = y.image_id								");
        query.append("			WHERE 1=1												");
        query.append("			AND x.comp_no = a.comp_no								");
        query.append("			AND x.object_id = a.equip_id							");
        query.append("			AND x.object_type = 'EQMSTR'							");
        query.append("		)	AS image_cnt											");
        query.append(" 		,(SELECT count(*) FROM TAEQASMB x WHERE x.comp_no = a.comp_no AND x.equip_id = a.equip_id) AS eqasmb_cnt		");
        query.append(" 		,(SELECT count(*) FROM TAEQSPEC x WHERE x.comp_no = a.comp_no AND x.equip_id = a.equip_id) AS eqspec_cnt		");
        query.append(" 		,(SELECT count(*) FROM TAEQPART x WHERE x.comp_no = a.comp_no AND x.equip_id = a.equip_id) AS eqpart_cnt		");
        query.append("      ,(SELECT count(*)                            				");
    	query.append("			FROM TADOCDATA x INNER JOIN TADOCUMENT y				");
    	query.append("			ON x.comp_no = y.comp_no								");
    	query.append("			AND x.doc_id = y.doc_id									");
    	query.append("			WHERE 1=1												");
    	query.append("			AND x.comp_no = a.comp_no								");
    	query.append("			AND y.object_type='EQMSTR'								");
    	query.append("			AND x.doc_id IN (										");
    	query.append("					SELECT z.doc_id									");
    	query.append("					FROM TAOBJDOC z									");
    	query.append("					WHERE 1=1										");
    	query.append("					AND z.comp_no=a.comp_no							");
    	query.append("					AND z.object_type = 'EQMSTR'					");
    	query.append("					AND z.object_id = a.equip_id					");
    	query.append("					)												");
    	query.append("			) AS file_cnt											");
    	query.append("            ,(SELECT count(*)                                     ");
    	query.append("            FROM TADOCDATA x INNER JOIN TADOCUMENT y              ");
    	query.append("            ON x.comp_no = y.comp_no                              ");
    	query.append("            AND x.doc_id = y.doc_id                               ");
    	query.append("            WHERE 1=1                                             ");
    	query.append("            AND x.comp_no = a.comp_no                             ");
    	query.append("            AND y.object_type='EQMSTR'                            ");
    	query.append("            AND x.doc_id IN (                                     ");
    	query.append("                    SELECT z.doc_id                               ");
    	query.append("                    FROM TAOBJDOC z                               ");
    	query.append("                    WHERE 1=1                                     ");
    	query.append("                    AND z.comp_no=a.comp_no                       ");
    	query.append("                    AND z.object_type = 'EQMSTR'                  ");
    	query.append("                    AND z.object_id = a.equip_id                  ");
    	query.append("                    )                                             ");
    	query.append("            ) AS document_cnt                                     ");
    	query.append("      ,a.weight													");
        query.append("            ,(SELECT count(*)                                   ");
        query.append("            FROM TAWORKORDER x INNER JOIN tawoequip y           ");
        query.append("            ON x.comp_no = y.comp_no                            ");
        query.append("            AND x.wkor_id = y.wkor_id                           ");
        query.append("            WHERE 1=1                                           ");
        query.append("            AND y.comp_no = a.comp_no                           ");
        query.append("            AND y.equip_id = a.equip_id                         ");
        query.append("            AND x.wo_status='C'                                 ");
        query.append("            ) AS wohist_cnt                                     ");
        
        return query.toString();
    }
	
	
	public List findAssetCount(BeeAssetCommonDTO beeAssetCommonDTO, Map map) throws Exception
    {
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 						");
    	query.append("       COUNT(1)	count		");
        query.append("FROM TAEQUIPMENT a			");
        query.append("WHERE 1=1						");

        query.append(this.getAssetWhere(beeAssetCommonDTO, map));

        return this.getJdbcTemplate().queryForList(query.toString());
        
    } 
	
	
	private String getAssetWhere(BeeAssetCommonDTO beeAssetCommonDTO, Map map)
    {        
		String compNo = CommonUtil.convertString(String.valueOf(map.get("compNo")));
		String equipDesc = CommonUtil.convertString(String.valueOf(map.get("equipDesc")));
		String equipId = CommonUtil.convertString(String.valueOf(map.get("equipId")));
		String deptId = CommonUtil.convertString(String.valueOf(map.get("deptId")));
		String eqCtgType = CommonUtil.convertString(String.valueOf(map.get("eqCtgType")));
		if("".equals(eqCtgType)) eqCtgType = CommonUtil.convertString(String.valueOf(map.get("eqctgType")));
		String eqLocId = CommonUtil.convertString(String.valueOf(map.get("eqLocId")));
		if("".equals(eqLocId)) eqLocId = CommonUtil.convertString(String.valueOf(map.get("eqlocId")));
		String plant = CommonUtil.convertString(String.valueOf(map.get("plant")));
     	String usageDeptId = CommonUtil.convertString(String.valueOf(map.get("usageDeptId")));
     	String eqCtgId = CommonUtil.convertString(String.valueOf(map.get("eqCtgId")));
     	
        QueryBuffer query = new QueryBuffer();
        
        query.append("AND a.is_last_version='Y'	");
        query.getStringEqualQuery("a.is_deleted", "N");
        query.getStringEqualQuery("a.comp_no", compNo);
    	query.getDeptLevelQuery("a.usage_dept", usageDeptId, "", compNo);
    	query.append("AND a.plant='"+plant+"'			");
        query.getAndQuery("a.equip_id", equipId);
        query.getStringEqualQuery("a.eqctg_type", eqCtgType);
        query.getEqLocLevelQuery("a.eqloc_id", eqLocId, "", compNo);
        query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
        if(!"".equals(equipDesc)){
            query.append("AND (a.item_no like '%"+equipDesc+"%' OR a.description like '%"+equipDesc+"%' )					");
        }
        if(!"".equals(eqCtgId)){
        	query.getEqCtgLevelQuery("a.eqctg_id", eqCtgId, "", compNo);
        }
        
        return query.toString();
    }
	
	public List findAssetPartList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String equipId = String.valueOf(map.get("equipId"));
    	
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT															");
        query.append("		a.comp_no comp_no											");
        query.append("		,a.equip_id equip_id										");
        query.append("		,'['||b.PART_NO||'] '||b.DESCRIPTION||'  '||CASE a.CONSIST_QTY WHEN null then 0 ELSE a.CONSIST_QTY END ||'ea' description ");
        query.append("FROM TAEQPART a, TAPARTS b										");
        query.append("WHERE a.comp_no = b.comp_no										");
        query.append("  AND a.part_id = b.part_id										");
        query.getAndQuery("a.comp_no",compNo);
        query.getAndQuery("a.equip_id",equipId);
        query.getStringEqualQuery("b.is_deleted", "N");
        query.append("ORDER BY a.PART_ID												");
        
        return getJdbcTemplate().queryForList(query.toString());
    } 
	public List findAssetSpecList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String equipId = String.valueOf(map.get("equipId"));
    	
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT																");
        query.append("		a.comp_no comp_no												");
        query.append("		,a.equip_id equip_id											");
        query.append("		,a.CATEG||'['||a.PROMPT||'] '||a.RESPONSE||a.UOM description	");
        query.append("FROM TAEQSPEC a														");
        query.append("WHERE 1=1																");
        query.getAndQuery("a.comp_no",compNo);
        query.getAndQuery("a.equip_id",equipId);
        query.append("ORDER BY a.ORD_NO, a.CATEG											");
        
        return getJdbcTemplate().queryForList(query.toString());
    } 
	public List findAssetToolList(Map map) throws Exception
	{
		String compNo   = String.valueOf(map.get("compNo"));
		String pEquipId  = String.valueOf(map.get("pEquipId"));
		String equipId  = String.valueOf(map.get("equipId"));
    	String lang 	= String.valueOf(map.get("lang"));
     	String usageDeptId = String.valueOf(map.get("usageDeptId"));
		
		QueryBuffer query = new QueryBuffer();
		

        query.append("SELECT                                                    ");
        query.append("         x.comp_no AS COMP_NO                             ");
        query.append("         ,x.equip_id AS EQUIP_ID                          ");
        query.append("         ,x.item_no AS ITEM_NO                            ");
        query.append("         ,x.description AS EQUIP_DESC                     ");
        query.append("         ,(SELECT                                         ");
        query.append("                 CASE WHEN a.LAST_CPLT_DATE IS NULL THEN a.INIT_WRK_DATE 		");
        query.append("                 ELSE a.LAST_CPLT_DATE END AS LAST_CPLT_DATE					");
        query.append("             FROM TAPMEQUIP a INNER JOIN TAPMLST b        ");
        query.append("             ON a.comp_no = b.comp_no                     ");
        query.append("             AND a.pm_id = b.pm_id                        ");
        query.append("           WHERE a.comp_no = x.comp_no                    ");
        query.append("             AND a.equip_id = x.equip_id                  ");
        query.append("             AND b.wo_type = 'PMC'                        ");
        query.append("             AND ROWNUM = 1 ) AS LAST_CPLT_DATE           ");
        query.append("         ,(SELECT (SELECT aa.emp_name                     ");
        query.append("             FROM TAEMP aa                                ");
        query.append("           WHERE aa.comp_no = a.comp_no                   ");
        query.append("             AND aa.emp_id = a.last_cplt_by  )            ");
        query.append("             FROM TAPMEQUIP a INNER JOIN TAPMLST b        ");
        query.append("             ON a.comp_no = b.comp_no                     ");
        query.append("             AND a.pm_id = b.pm_id                        ");
        query.append("           WHERE a.comp_no = x.comp_no                    ");
        query.append("             AND a.equip_id = x.equip_id                  ");
        query.append("             AND b.wo_type = 'PMC'                        ");
        query.append("             AND ROWNUM = 1 ) AS LAST_CPLT_BY             ");
        query.append("         ,(SELECT (SELECT                                 ");
        query.append("                  (SELECT y.key_name FROM TALANG y        ");
        query.append("                  WHERE y.key_type = aa.key_type          ");
        query.append("                  AND   y.key_no = aa.key_no              ");
        query.append("                  AND   y.lang = '"+lang+"'               ");
        query.append("                  )                                       ");
        query.append("              FROM TACDSYSD aa                            ");
        query.append("              WHERE aa.list_type='CALIB_RSLT_STATUS'      ");
        query.append("              AND aa.cdsysd_no = a.pm_result_status)      ");
        query.append("             FROM TAPMEQUIP a INNER JOIN TAPMLST b        ");
        query.append("             ON a.comp_no = b.comp_no                     ");
        query.append("             AND a.pm_id = b.pm_id                        ");
        query.append("           WHERE a.comp_no = x.comp_no                    ");
        query.append("             AND a.equip_id = x.equip_id                  ");
        query.append("             AND b.wo_type = 'PMC'                        ");
        query.append("             AND ROWNUM = 1 ) AS PM_RESULT_STATUS         ");
        query.append("         ,(SELECT                                         ");
        query.append("                 CASE WHEN a.NEXT_PLAN_DATE IS NULL THEN		");
        query.append("                 (to_char(case (select aa.period_type from tapmlst aa Where aa.comp_no = a.comp_no and aa.pm_id = a.pm_id)	");
        query.append("                 		WHEN 'D' THEN			");
        query.append("                 			(select to_date(a.init_wrk_date)+aa.cycle + (CASE WHEN aa.wo_type ='PMC' THEN -1 ELSE 0 END)  from tapmlst aa Where aa.comp_no = a.comp_no and aa.pm_id = a.pm_id)	");
        query.append("                 		WHEN 'W' THEN			");
        query.append("                 			(select to_date(a.init_wrk_date)+(aa.cycle*7) + (CASE WHEN aa.wo_type ='PMC' THEN -1 ELSE 0 END) from tapmlst aa Where aa.comp_no = a.comp_no and aa.pm_id = a.pm_id)	");
        query.append("                 		WHEN 'M' THEN			");
        query.append("                 			(select add_months(to_date(a.init_wrk_date),aa.cycle) + (CASE WHEN aa.wo_type ='PMC' THEN -1 ELSE 0 END) from tapmlst aa Where aa.comp_no = a.comp_no and aa.pm_id = a.pm_id)	");
        query.append("                 		WHEN 'Y' THEN			");
        query.append("                 			(select add_months(to_date(a.init_wrk_date),(aa.cycle*12)) + (CASE WHEN aa.wo_type ='PMC' THEN -1 ELSE 0 END) from tapmlst aa Where aa.comp_no = a.comp_no and aa.pm_id = a.pm_id)	");
        query.append("                 		END, 'YYYYMMDD')			");
        query.append("                 )     ELSE a.NEXT_PLAN_DATE END AS NEXT_PLAN_DATE	");
        query.append("             FROM TAPMEQUIP a INNER JOIN TAPMLST b        ");
        query.append("             ON a.comp_no = b.comp_no                     ");
        query.append("             AND a.pm_id = b.pm_id                        ");
        query.append("           WHERE a.comp_no = x.comp_no                    ");
        query.append("             AND a.equip_id = x.equip_id                  ");
        query.append("             AND b.wo_type = 'PMC'                        ");
        query.append("             AND ROWNUM = 1 ) AS NEXT_PLAN_DATE           ");
        query.append("         ,et.all_range AS ALL_RANGE                       ");
        query.append("         ,et.use_range AS USE_RANGE                       ");
        query.append("         ,et.tolerance AS TOLERANCE                       ");
        query.append("         ,et.accuracy AS ACCURACY                         ");
        query.append("         ,et.min_unit_value AS MIN_UNIT_VALUE             ");
        query.append("         ,et.measure_unit AS MEASURE_UNIT                 ");
        query.append("FROM TAEQUIPMENT x INNER JOIN TAEQTOOL et                 ");
        query.append("ON x.comp_no = et.comp_no                                 ");
        query.append("AND x.equip_id = et.equip_id                              ");
        query.append("WHERE 1=1                                                 ");
        query.getAndQuery("x.comp_no",compNo);
        query.getStringEqualQuery("x.is_deleted", "N");
    	query.getDeptLevelQuery("et.usage_dept", usageDeptId, "", compNo);
        query.getAndQuery("x.p_equip_id",pEquipId);
        query.getAndQuery("x.equip_id",equipId);
        query.append("ORDER BY x.item_no                                        ");
		
		return getJdbcTemplate().queryForList(query.toString());
	} 
	public List findDocumentList(Map map) throws Exception
    {
		String compNo 		= String.valueOf(map.get("compNo"));
    	String lang 		= String.valueOf(map.get("lang"));
    	String objectId 	= String.valueOf(map.get("objectId"));
    	String objectType 	= String.valueOf(map.get("objectType"));
    	String regId 		= String.valueOf(map.get("regId"));
    	String docDataId 	= String.valueOf(map.get("docDataId"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT a.docdata_id							AS ID				");
    	query.append("		,a.comp_no								AS COMP_NO			");
    	query.append("		,a.doc_id								AS file_id			");
    	query.append("		,b.object_type							AS OBJECT_TYPE		");
    	query.append("		,'"+objectId+"'							AS OBJECT_ID		");
    	query.append("		,b.dept_id								AS DEPT_ID			");
    	query.append("		,b.reg_id								AS REG_ID			");
    	query.append("		,b.reg_date								AS REG_DATE			");
    	query.append("		,a.nf_file_path							AS FILE_PATH		");
    	query.append("		,a.file_name							AS FILE_NAME		");
    	query.append("		,a.file_ext								AS EXTENSION		");
    	query.append("		,b.remark								AS REMARK			");
    	query.append("		,'dream/android/'||a.docdata_id 		AS URL				");
    	query.append("		,b.description 							AS DESCRIPTION		");
    	query.append("		,(SELECT x.emp_name FROM TAEMP x WHERE x.comp_no = b.comp_no AND x.emp_id = b.reg_id) AS REGDESC		");
    	query.append("		,(SELECT x.description FROM TADEPT x WHERE x.comp_no = b.comp_no AND x.dept_id = b.dept_id) AS DEPTDESC	");
    	query.append("		,a.file_size 							AS FILESIZE			");
    	query.append("		,b.doc_no	 							AS DOC_NO			");
    	query.append("		,b.docctg_id 							AS DOC_CTG_ID		");
    	query.append("		,(SELECT x.description FROM TADOCCTG x WHERE x.comp_no = b.comp_no AND x.docctg_id = b.docctg_id) AS DOC_CTG_DESC	");
    	query.append("		,b.doc_categ 							AS DOC_CATEG_ID		");
		query.append("      ,(SELECT SFACODE_TO_DESC(b.doc_categ,'DOC_CATEG','USR','"+compNo+"','"+lang+"') FROM DUAL) AS DOC_CATEG_DESC		");
    	query.append("		,b.secur_grade 							AS SECUR_GRADE_ID	");
		query.append("      ,(SELECT SFACODE_TO_DESC(b.secur_grade,'SECUR_GRADE','SYS','"+compNo+"','"+lang+"') FROM DUAL) AS SECUR_GRADE_DESC	");
    	query.append("FROM TADOCDATA a INNER JOIN TADOCUMENT b							");
    	query.append("ON a.comp_no = b.comp_no											");
    	query.append("AND a.doc_id = b.doc_id											");
    	query.append("WHERE 1=1															");
    	query.getStringEqualQuery("object_type", objectType);
    	query.getAndQuery("a.docdata_id", docDataId);
    	query.append("AND a.doc_id IN (												");
    	query.append("			SELECT doc_id										");
    	query.append("			FROM TAOBJDOC										");
    	query.append("			WHERE 1=1											");
    	query.getStringEqualQuery("comp_no", compNo);
    	query.getStringEqualQuery("object_type", objectType);
    	query.getAndQuery("object_id", objectId);
    	query.append("			)													");
    	query.append("ORDER BY file_name											");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
	public List findDocumentCtgList(Map map) throws Exception
	{
		String compNo 		= String.valueOf(map.get("compNo"));
		String lang 		= String.valueOf(map.get("lang"));
		String objectId 	= String.valueOf(map.get("objectId"));
		String objectType 	= String.valueOf(map.get("objectType"));
		String regId 		= String.valueOf(map.get("regId"));
		String docDataId 	= String.valueOf(map.get("docDataId"));
		
		QueryBuffer query = new QueryBuffer();
		
		if("".equals(docDataId) || "null".equals(docDataId)){
			query.append("select comp_no      						                    ");
			query.append("        ,docdata_id      					                    ");
			query.append("        ,doc_id      						                    ");
			query.append("        ,file_name      					                    ");
			query.append("        ,file_ext      					                    ");
			query.append("        ,file_size      					                    ");
			query.append("        ,nf_file_path      				                    ");
			query.append("        ,doc_no      						                    ");
			query.append("        ,description      				                    ");
			query.append("        ,dept_id      					                    ");
			query.append("        ,dept_desc      					                    ");
			query.append("        ,reg_id      						                    ");
			query.append("        ,reg_desc      					                    ");
			query.append("        ,object_type      				                    ");
			query.append("        ,object_type_desc      			                    ");
			query.append("        ,url      						                    ");
			query.append("        ,ID      							                    ");
			query.append("        ,PID      						                    ");
			query.append("        ,isFolder      					                    ");
			query.append("        ,ordNo      						                    ");
			query.append("        ,level lvl      					                    ");
			query.append("FROM       								                    ");
			query.append("(      									                    ");
			query.append("SELECT x.comp_no AS comp_no                      				");
			query.append("        ,null AS docdata_id                  					");
			query.append("        ,null AS doc_id                          				");
			query.append("        ,x.description AS file_name                  			");
			query.append("        ,'' AS file_ext                      					");
			query.append("        ,null AS file_size                  					");
			query.append("        ,'' AS nf_file_path              						");
			query.append("        ,'' AS doc_no                          				");
			query.append("        ,x.description AS description              			");
			query.append("        ,null AS dept_id                      				");
			query.append("        ,'' AS dept_desc          							");
			query.append("        ,null AS reg_id                          				");
			query.append("        ,'' AS reg_desc                  						");
			query.append("        ,'' AS object_type              						");
			query.append("        ,'' AS object_type_desc                          		");
			query.append("        ,'' as url      										");
			query.append("        ,to_char(x.docctg_id) AS ID      						");
			query.append("        ,x.p_docctg_id as PID             					");
			query.append("        ,'Y' as isFolder      								");
			query.append("        ,'0' as ordNo      									");
			query.append("from tadocctg x       										");
			query.append("WHERE  1=1      												");
			query.append("		  AND x.comp_no = '"+compNo+"'							");
			query.append("		  AND x.is_use = 'Y'									");
			query.append("UNION ALL      												");
		}
		
		query.append("SELECT x.comp_no AS comp_no                      				");
		query.append("            ,x.docdata_id AS docdata_id                  		");
		query.append("            ,x.doc_id AS doc_id                          		");
		query.append("            ,x.file_name AS file_name                  		");
		query.append("            ,x.file_ext AS file_ext                      		");
		query.append("            ,x.file_size AS file_size                  		");
		query.append("            ,x.nf_file_path AS nf_file_path              		");
		query.append("            ,y.doc_no AS doc_no                          		");
		query.append("            ,y.description AS description              		");
		query.append("            ,y.dept_id AS dept_id                      		");
		query.append("            ,(SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = y.dept_id) AS dept_desc          ");
		query.append("            ,y.reg_id AS reg_id                          		");
		query.append("            ,(SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_id = y.reg_id) AS reg_desc                  ");
		query.append("            ,y.object_type AS object_type              		");
		query.append("            ,(SELECT SFACODE_TO_DESC(y.object_type,'OBJECT_TYPE','SYS','','"+lang+"') FROM DUAL) AS object_type_desc                          ");
		query.append("            ,'dream/android/'||x.docdata_id as url      		");
		query.append("           ,'-2'  as ID      									");
		query.append("           ,case when y.docctg_id is null then 0 else y.docctg_id end as PID      ");
		query.append("           ,'N' as isFolder      								");
		query.append("          ,'1' as ordNo      									");
		query.append("        FROM TADOCDATA x INNER JOIN TADOCUMENT y          	");
		query.append("        ON x.comp_no = y.comp_no                          	");
		query.append("        AND x.doc_id = y.doc_id                          		");
		query.append("        WHERE 1=1                                          	");
		query.append("		  AND x.comp_no = '"+compNo+"'							");
        query.getAndNumKeyQuery("x.docdata_id", docDataId);
    	query.append("AND x.doc_id IN (												");
    	query.append("			SELECT doc_id										");
    	query.append("			FROM TAOBJDOC										");
    	query.append("			WHERE 1=1											");
    	query.getStringEqualQuery("comp_no", compNo);
    	query.getStringEqualQuery("object_type", objectType);
    	query.getAndQuery("object_id", objectId);
    	query.append("			)													");
        
		
        if("".equals(docDataId) || "null".equals(docDataId)){
        	query.append("        )      												");
			query.append("        START WITH PID=0      								");
			query.append("        CONNECT BY PRIOR ID = PID      						");
			query.append("        ORDER SIBLINGS BY ordNo ASC      						");
			query.append("              												");
		}
		
		return getJdbcTemplate().queryForList(query.toString());
	}
	
	public List findWoHistList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String lang = String.valueOf(map.get("lang"));
		String equipId = String.valueOf(map.get("equipId"));
		//Detail Á¶È¸ ½Ã
		String wkorId = String.valueOf(map.get("wkorId"));
    	
        QueryBuffer query = new QueryBuffer();
        

        query.append("WITH eqInfo AS (				");
        query.append("    SELECT a.equip_id,			");
        query.append("           a.wkor_id,				");
        query.append("           b.item_no,				");
        query.append("           a.comp_no,				");
        query.append("           b.sub_mng_id,			");
        query.append("           b.eqctg_id,			");
        query.append("           b.eqloc_id,			");
        query.append("           (SELECT x.full_desc FROM TAEQLOC x WHERE x.comp_no = b.comp_no AND x.eqloc_id = b.eqloc_id) eqloc_desc,	");
        query.append("           b.old_eq_no,			");
        query.append("           b.description ,		");
        query.append("           b.eqctg_type			");
        query.append("FROM  TAWOEQUIP a, TAEQUIPMENT b	");
        query.append("     WHERE a.comp_no = b.comp_no	");
        query.append("     AND a.equip_id = b.equip_id	");
    	query.getAndQuery("b.equip_id", equipId);
        query.getStringEqualQuery("b.is_deleted", "N");
        query.append("		)							");
        query.append("SELECT a.comp_no																					AS COMPNO		");
    	query.append("		,a.wkor_id																					AS WKORID		");
    	query.append("		,a.description																				AS WODESC		");
    	query.append("		,a.wo_no																					AS WONO			");
    	query.append("		,a.wo_type																					AS WOTYPE		");
        query.append("		,(SELECT SFACODE_TO_DESC(a.wo_type,'WO_TYPE','SYS','','"+lang+"') FROM DUAL)									AS WOTYPEDESC	");
    	query.append("		,a.pm_type																					AS PMTYPE		");
        query.append("		,(SELECT SFACODE_TO_DESC(a.pm_type,'PM_TYPE','SYS','','"+lang+"') FROM DUAL)									AS PMTYPEDESC	");
    	query.append("		,a.wkor_date																				AS WKORDATE		");
    	query.append("		,a.wo_status																				AS WO_STATUS	");
        query.append("		,(SELECT SFACODE_TO_DESC(a.wo_status,'WO_STATUS','SYS','','"+lang+"') FROM DUAL)								AS WOSTATUSDESC	");
    	query.append("		,(SELECT x.equip_id FROM eqInfo x WHERE x.wkor_id = a.wkor_id AND rownum=1) 				AS EQUIPID		");
    	query.append("		,(SELECT x.item_no FROM eqInfo x WHERE x.wkor_id = a.wkor_id AND rownum=1) 				AS EQUIPNO		");
    	query.append("		,(SELECT x.description FROM eqInfo x WHERE x.wkor_id = a.wkor_id AND rownum=1) 			AS EQUIPDESC	");
    	query.append("		,a.start_date 																				AS STARTDATE	");
        query.append("		,a.start_time 																				AS STARTTIME	");
        query.append("		,a.end_date 																				AS ENDDATE		");
        query.append(" 		,a.end_time 																				AS ENDTIME		");
        query.append(" 		,a.dept_id 																					AS DEPTID		");
        query.append(" 		,(SELECT x.description FROM TADEPT x WHERE x.comp_no = a.comp_no AND x.dept_id = a.dept_id) AS DEPTDESC		");
        query.append(" 		,a.emp_id 																					AS EMPID		");
        query.append(" 		,(SELECT x.emp_name FROM TAEMP x WHERE x.comp_no = a.comp_no AND x.emp_id = a.emp_id) 		AS EMPDESC		");
        query.append(" 		,a.perform 																					AS PERFORM		");
    	query.append("		,(SELECT count(1) FROM TAWOCRAFT x WHERE x.comp_no = a.comp_no AND x.wkor_id = a.wkor_id) 	AS WOCRAFTCNT	");
    	query.append("		,(SELECT count(1) FROM TAWOPARTS x WHERE x.comp_no = a.comp_no AND x.wkor_id = a.wkor_id) 	AS WOPARTSCNT	");
    	query.append("		,(SELECT count(*) FROM tadocdata WHERE doc_id IN (SELECT x.doc_id FROM TAOBJDOC x WHERE x.comp_no = a.comp_no 	");
    	query.append("											AND x.object_id = a.wkor_id AND object_type='WORESULT')						");
    	query.append("											AND file_ext IN ((SELECT TRIM(REGEXP_SUBSTR(value$, '[^,]+', 1, LEVEL))		");
    	query.append("						FROM (SELECT value$ FROM TACONFIG WHERE comp_no = '"+compNo+"' AND NAME='IMG_FILE_TYPE')		");
    	query.append("						CONNECT BY  INSTR(value$, ',', 1, LEVEL - 1) > 0))												");
    	query.append("												)	AS WOPHOTOCNT														");
    	query.append("		,(SELECT x.eqloc_id FROM eqInfo x WHERE x.wkor_id = a.wkor_id AND rownum=1) 				AS EQLOCID		");
    	query.append("		,(SELECT x.eqloc_desc FROM eqInfo x WHERE x.wkor_id = a.wkor_id AND rownum=1) 			AS EQLOCDESC	");
    	query.append("		,a.eqasmb_id 																				AS EQASMBID	");
    	query.append("		,(SELECT x.full_desc FROM TAEQASMB x WHERE x.comp_no = a.comp_no AND x.eqasmb_id = a.eqasmb_id AND rownum=1) 	AS EQASMBDESC	");

        query.append("		,(SELECT TO_CHAR(y.full_desc||'|')  																		");
    	query.append("			FROM TAWOPARTS x INNER JOIN TAPARTS y																	");
    	query.append(" 			ON x.comp_no = y.comp_no																				");
    	query.append(" 			AND x.part_id = y.part_id                                                								");
    	query.append("			WHERE  x.wkor_id = a.wkor_id                                       										");
    	query.append("			AND   x.comp_no = a.comp_no                                         									");
        query.append("			AND   rownum = 1 	) 									AS WOPARTSDESC									");
        
    	query.append("FROM TAWORKORDER a																								");
    	query.append("WHERE 1=1																											");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getStringEqualQuery("a.wo_status", "C");
    	query.getStringEqualQuery("a.is_deleted", "N");
    	query.getAndQuery("a.wkor_id", wkorId);
    	query.append("AND a.wkor_id IN (SELECT wkor_id FROM eqInfo)									");
    	query.append("ORDER BY a.wkor_date desc,cre_time desc										");
    	
    	
        return getJdbcTemplate().queryForList(query.toString());
    } 
    
	public String findTotalCount(BeeAssetCommonDTO beeAssetCommonDTO, Map map) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT count(*) count		");
    	query.append("FROM TAEQUIPMENT a		");
        query.append("WHERE 1=1					");
        query.append(this.getAssetWhere(beeAssetCommonDTO, map));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }


	@Override
	public int insertAsset(Map map) throws Exception {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("INSERT INTO TAEQUIPMENT (                                                 ");
        query.append("     COMP_NO, EQUIP_ID, ITEM_NO, DESCRIPTION, EQLOC_ID                    ");
        query.append("     , EQCTG_ID, EQ_STATUS, DEPT_ID, MAIN_MNG_ID, SUB_MNG_ID ");
        query.append("     , SETUP_DATE, BUY_AMT, PLF_TYPE, MAKER, MODEL_NO                     ");
        query.append("     , CAPACITY, UTIL_CAPA, IS_LAW_EQ, GUARANTY_DATE, ORD_NO              ");
        query.append("     , REMARK, EXCEL_NO, AS_VENDOR, AS_NAME, AS_PHONE                     ");
        query.append("     , OLD_EQ_NO, SERIAL_NO, EQ_GRADE, PLANT, EQCTG_TYPE                  ");
        query.append("     , BUY_DATE, P_EQUIP_ID, QUANTITY, REVISIONHIST_ID, REVISION_STATUS   ");
        query.append("     , IS_LAST_VERSION, SUPPLIER, COUNTRY_MAKER, RUN_DATE, DISUSED_DATE   ");
        query.append("     , PMI_ACTION_TYPE, PROD_SHAPE, EQ_SPEC, EQ_SIZE, WEIGHT              ");
        query.append("     , EQSTRLOC_NO, IS_DELETED, DELETE_BY, DELETE_TIME, USAGE_DEPT        ");
        query.append("     , USAGE_EMP, CTCTR_ID, UPD_BY, UPD_DATE, TAG_NO                      ");
        query.append("     , LNWRKLIST_ID, CRE_DATE, CRE_BY, CURRENCY, MESEQUIP_ID              ");
        query.append("     , STORAGE, WKCTR_ID                                                  ");
        query.append("      )                                                                   ");
        query.append("VALUES (                                                                  ");
        query.append("      ?,?,?,?,?                                                           ");
        query.append("     ,?,?,?,?,?                                                           ");
        query.append("     ,?,?,?,?,?                                                           ");
        query.append("     ,?,?,?,?,?                                                           ");
        query.append("     ,?,?,?,?,?                                                           ");
        query.append("     ,?,?,?,?,?                                                           ");
        query.append("     ,?,?,?,?,?                                                           ");
        query.append("     ,?,?,?,?,?                                                           ");
        query.append("     ,?,?,?,?,?                                                           ");
        query.append("     ,?,?,?,?,?                                                           ");
        query.append("     ,?,?,?,?,?                                                           ");
        query.append("     ,?,?,?,?,?                                                           ");
        query.append("     ,?,?                                                                 ");
        query.append("         )                                                                ");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("equipId"))
        		,convertString(map.get("itemNo"))
        		,convertString(map.get("description"))
        		,convertString(map.get("eqLocId"))
        		,convertString(map.get("eqCtgId"))
        		,"R"
        		,convertString(map.get("deptId"))
        		,convertString(map.get("mainMngId"))
        		,""
        		,convertString(map.get("setupDate"))
        		,""
        		,""
        		,convertString(map.get("maker"))
        		,convertString(map.get("modelNo"))
        		,convertString(map.get("capacity"))
        		,""
        		,""
        		,convertString(map.get("guarantyDate"))
        		,""
        		,""
        		,""
        		,convertString(map.get("asVendor"))
        		,convertString(map.get("asName"))
        		,convertString(map.get("asPhone"))
        		,""
        		,""
        		,""
        		,convertString(map.get("plant"))
        		,"MC"
        		,""
        		,""
        		,""
        		,""
        		,""
        		,"Y"
        		,""
        		,""
        		,""
        		,""
        		,""
        		,""
        		,""
        		,""
        		,convertString(map.get("weight"))
        		,""
        		,"N"
        		,""
        		,""
        		,convertString(map.get("usageDept"))
        		,""
        		,""
        		,""
        		,""
        		,convertString(map.get("tagNo"))
        		,""
        		,DateUtil.getDateTime()
        		,convertString(map.get("userId"))
        		,""
        		,""
        		,convertString(map.get("storage"))
        		,""
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }


	@Override
	public int updateAsset(Map map) throws Exception {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("UPDATE TAEQUIPMENT SET                                      ");
        query.append("       description           = ?                            ");
        query.append("       ,plant                = ?                            ");
        query.append("       ,eqloc_id             = ?                            ");
        query.append("       ,eqctg_id             = ?                            ");
        query.append("       ,maker                = ?                            ");
        query.append("       ,setup_date           = ?                            ");
        query.append("       ,model_no             = ?                            ");
        query.append("       ,dept_id              = ?                            ");
        query.append("       ,main_mng_id          = ?                            ");
        query.append("       ,as_vendor            = ?                            ");
        query.append("       ,as_name              = ?                            ");
        query.append("       ,as_phone             = ?                            ");
        query.append("       ,guaranty_date        = ?                            ");
        query.append("       ,storage              = ?                            ");
        query.append("       ,capacity             = ?                            ");
        query.append("       ,usage_dept           = ?                            ");
        query.append("       ,weight               = ?                            ");
        query.append("       ,tag_no               = ?                            ");
        query.append("       ,upd_date             = ?                            ");
        query.append("       ,upd_by               = ?                            ");
        query.append("WHERE comp_no     = ?                                       ");
        query.append("  AND equip_id    = ?                                       ");
        
        objects = new Object[] {
        		convertString(map.get("description"))
        		,convertString(map.get("plant"))
        		,convertString(map.get("eqLocId"))
        		,convertString(map.get("eqCtgId"))
        		,convertString(map.get("maker"))
        		,convertString(map.get("setupDate"))
        		,convertString(map.get("modelNo"))
        		,convertString(map.get("deptId"))
        		,convertString(map.get("mainMngId"))
        		,convertString(map.get("asVendor"))
        		,convertString(map.get("asName"))
        		,convertString(map.get("asPhone"))
        		,convertString(map.get("guarantyDate"))
        		,convertString(map.get("storage"))
        		,convertString(map.get("capacity"))
        		,convertString(map.get("usageDept"))
        		,convertString(map.get("weight"))
        		,convertString(map.get("tagNo"))
        		,DateUtil.getDateTime()
        		,convertString(map.get("userId"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("equipId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }




	@Override
	public List eqAsmbList(Map map) throws Exception {
		
		QueryBuffer query = new QueryBuffer();
        
        String compNo = String.valueOf(map.get("compNo"));
        String equipId = String.valueOf(map.get("equipId"));
        String eqAsmbId = String.valueOf(map.get("eqAsmbId"));
        
        query.append("SELECT '[ '||x.eqasmb_no||' ] '||x.description as displayDesc	");
        query.append("       ,x.eqasmb_id as eqAsmbId                   ");
        query.append("       ,x.eqasmb_no as eqAsmbNo                   ");
        query.append("       ,x.description as description              ");
        query.append("       ,x.full_desc as fullDesc                   ");
        query.append("       ,x.p_eqasmb_id as pEqAsmbId                ");
        query.append("       ,(SELECT a.description FROM TAEQASMB a WHERE a.comp_no = x.comp_no AND a.eqasmb_id = x.p_eqasmb_id) AS pEqAsmbDesc  ");
        query.append("       ,x.maker as maker                          ");
        query.append("       ,x.model_no as modelNo                     ");
        query.append("       ,x.spec as spec                            ");
        query.append("       ,x.tag_no as tagNo                         ");
        query.append("       ,LEVEL AS LVL                              ");
        query.append("FROM TAEQASMB x                                   ");
        query.append("WHERE 1=1                                         ");
        query.getAndQuery("x.comp_no",compNo);
        query.getAndQuery("x.equip_id",equipId);
        query.getAndQuery("x.eqasmb_id",eqAsmbId);
        query.append("START WITH p_eqasmb_id = '0'						");
        query.append("CONNECT BY PRIOR eqasmb_id = p_eqasmb_id			");
        query.append("ORDER SIBLINGS BY ord_no							");
        
        return getJdbcTemplate().queryForList(query.toString());
	}




	@Override
	public int insertEqAsmb(Map map) throws Exception {
		QueryBuffer query = new QueryBuffer();

        query.append("INSERT INTO TAEQASMB (                                                    ");
        query.append("     COMP_NO, EQASMB_ID, EQUIP_ID, EQ_CTG_ASMB_ID, EQCTG_ID               ");
        query.append("     , DESCRIPTION, IS_EQTYPE_ASMB, ORD_NO, IS_USE, EQ_CTG_ASMB_NO        ");
        query.append("     , P_EQ_CTG_ASMB_ID, EQASMB_NO, P_EQASMB_ID, REMARK, FULL_DESC        ");
        query.append("     , MAKER, MODEL_NO, BUY_AMT, SPEC, SETUP_DATE                         ");
        query.append("     , TAG_NO                                                             ");
        query.append("      )                                                                   ");
        query.append("VALUES (                                                                  ");
        query.append("      ?,?,?,?,?                                                           ");
        query.append("     ,?,?,?,?,?                                                           ");
        query.append("     ,?,?,?,?,?                                                           ");
        query.append("     ,?,?,?,?,?                                                           ");
        query.append("     ,?                                                                   ");
        query.append("         )                                                                ");
        
        Object[] objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("eqAsmbId"))
        		,convertString(map.get("equipId"))
        		,""
        		,""
        		
        		,convertString(map.get("description"))
        		,"N"
        		,convertString(map.get("eqAsmbId"))
        		,"Y"
        		,""
        		
        		,""
        		,convertString(map.get("eqAsmbId"))
        		,convertString(map.get("pEqAsmbId"))
        		,""
        		,""

        		,convertString(map.get("maker"))
        		,convertString(map.get("modelNo"))
        		,""
        		,convertString(map.get("spec"))
        		,""
        		,convertString(map.get("tagNo"))
        		
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }




	@Override
	public int updateEqAsmb(Map map) throws Exception {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("UPDATE TAEQASMB SET                                     ");
        query.append("       description       = ?                            ");
        query.append("       ,p_eqasmb_id      = ?                            ");
        query.append("       ,maker            = ?                            ");
        query.append("       ,model_no         = ?                            ");
        query.append("       ,spec             = ?                            ");
        query.append("       ,tag_no           = ?                            ");
        query.append("WHERE comp_no      = ?                                  ");
        query.append("  AND eqasmb_id    = ?                                  ");
        
        objects = new Object[] {
        		convertString(map.get("description"))
        		,convertString(map.get("pEqAsmbId"))
        		,convertString(map.get("maker"))
        		,convertString(map.get("modelNo"))
        		,convertString(map.get("spec"))
        		,convertString(map.get("tagNo"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("eqAsmbId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }




	@Override
	public void makeEqAsmbFullDesc(Map map) throws Exception {
		
        QueryBuffer query = new QueryBuffer();
        
        String compNo = convertString(map.get("compNo"));
        String eqAsmbId = convertString(map.get("eqAsmbId"));
        
        query.append("begin                                          ");
        query.append("SP_EQ_UPD_EQASMB('"+compNo+"','"+eqAsmbId+"'); ");
        query.append("end;                                           ");
        
        this.getJdbcTemplate().execute(query.toString());
    
		
	}




	@Override
	public List eqSpecList(Map map) throws Exception {
		
		QueryBuffer query = new QueryBuffer();
        
        String compNo = String.valueOf(map.get("compNo"));
        String equipId = String.valueOf(map.get("equipId"));
        String eqSpecId = String.valueOf(map.get("eqSpecId"));
        
        query.append("SELECT x.eqspec_id as eqSpecId                    ");
        query.append("       ,x.eqasmb_id as eqAsmbId                   ");
        query.append("       ,(SELECT a.description FROM TAEQASMB a WHERE a.comp_no = x.comp_no AND a.eqasmb_id = x.eqasmb_id) AS eqAsmbDesc  ");
        query.append("       ,x.categ as categ                          ");
        query.append("       ,x.prompt as prompt                        ");
        query.append("       ,x.response as response                    ");
        query.append("       ,x.uom as uom                              ");
        query.append("       ,ord_no AS ordNo                           ");
        query.append("FROM TAEQSPEC x                                   ");
        query.append("WHERE 1=1                                         ");
        query.getAndQuery("x.comp_no",compNo);
        query.getAndQuery("x.equip_id",equipId);
        query.getAndQuery("x.eqspec_id",eqSpecId);
        query.append("ORDER BY ord_no									");
        
        return getJdbcTemplate().queryForList(query.toString());
	}




	@Override
	public int insertEqSpec(Map map) throws Exception {
		QueryBuffer query = new QueryBuffer();

        query.append("INSERT INTO TAEQSPEC (                                     ");
        query.append("     COMP_NO, EQSPEC_ID, EQUIP_ID, CATEG, PROMPT           ");
        query.append("     , RESPONSE, UOM, ORD_NO, EQASMB_ID, EQCTGSPEC_ID      ");
        query.append("     , IS_EQTYPE_SPEC                                      ");
        query.append("      )                                                    ");
        query.append("VALUES (                                                   ");
        query.append("      ?,?,?,?,?                                            ");
        query.append("     ,?,?,?,?,?                                            ");
        query.append("     ,?                                                    ");
        query.append("         )                                                 ");
        
        Object[] objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("eqSpecId"))
        		,convertString(map.get("equipId"))
        		,convertString(map.get("categ"))
        		,convertString(map.get("prompt"))
        		,convertString(map.get("response"))
        		,convertString(map.get("uom"))
        		,convertString(map.get("ordNo"))
        		,convertString(map.get("eqAsmbId"))
        		,""
        		,""
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }

	@Override
	public int updateEqSpec(Map map) throws Exception {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("UPDATE TAEQSPEC SET                                     ");
        query.append("       eqasmb_id         = ?                            ");
        query.append("       ,categ            = ?                            ");
        query.append("       ,prompt           = ?                            ");
        query.append("       ,response         = ?                            ");
        query.append("       ,uom              = ?                            ");
        query.append("       ,ord_no           = ?                            ");
        query.append("WHERE comp_no      = ?                                  ");
        query.append("  AND eqspec_id    = ?                                  ");
        
        objects = new Object[] {
        		convertString(map.get("eqAsmbId"))
        		,convertString(map.get("categ"))
        		,convertString(map.get("prompt"))
        		,convertString(map.get("response"))
        		,convertString(map.get("uom"))
        		,convertString(map.get("ordNo"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("eqSpecId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }

	@Override
	public List eqPartList(Map map) throws Exception {
		
		QueryBuffer query = new QueryBuffer();
        
        String compNo = String.valueOf(map.get("compNo"));
        String equipId = String.valueOf(map.get("equipId"));
        String eqPartId = String.valueOf(map.get("eqPartId"));
        String lang = String.valueOf(map.get("lang"));
        lang = "null".equals(lang)?"ko":lang;
        
        query.append("SELECT x.eqpart_id as eqPartId                    ");
        query.append("       ,x.eqasmb_id as eqAsmbId                   ");
        query.append("       ,(SELECT a.description FROM TAEQASMB a WHERE a.comp_no = x.comp_no AND a.eqasmb_id = x.eqasmb_id) AS eqAsmbDesc  ");
        query.append("       ,x.part_id as partId                       ");
        query.append("       ,(SELECT a.description FROM TAPARTS a WHERE a.comp_no = x.comp_no AND a.part_id = x.part_id) AS partDesc  ");
        query.append("       ,x.consist_qty as consistQty               ");
        query.append("       ,x.is_use as isUse                         ");
        query.append("FROM TAEQPART x                                   ");
        query.append("WHERE 1=1                                         ");
        query.getAndQuery("x.comp_no",compNo);
        query.getAndQuery("x.equip_id",equipId);
        query.getAndQuery("x.eqpart_id",eqPartId);
        query.append("ORDER BY ord_no									");
        
        return getJdbcTemplate().queryForList(query.toString());
	}
	
	@Override
	public int insertEqPart(Map map) throws Exception {
		QueryBuffer query = new QueryBuffer();

        query.append("INSERT INTO TAEQPART (                                                        ");
        query.append("     COMP_NO, EQPART_ID, EQASMB_ID, EQUIP_ID, PART_ID                         ");
        query.append("     , CONSIST_QTY, USE_QTY, ISSUE_TIME, ISSUE_FIRST_DATE, ISSUE_LAST_DATE    ");
        query.append("     , EQ_CTG_PART_ID, EQ_CTG_ASMB_ID, IS_EQTYPE_PART, IS_USE, ORD_NO         ");
        query.append("      )                                                                       ");
        query.append("VALUES (                                                                      ");
        query.append("      ?,?,?,?,?                                                               ");
        query.append("     ,?,?,?,?,?                                                               ");
        query.append("     ,?,?,?,?,?                                                               ");
        query.append("         )                                                                    ");
        
        Object[] objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("eqPartId"))
        		,convertString(map.get("eqAsmbId"))
        		,convertString(map.get("equipId"))
        		,convertString(map.get("partId"))
        		
        		,convertString(map.get("consistQty"))
        		,""
        		,""
        		,""
        		,""

        		,""
        		,""
        		,""
        		,convertString(map.get("isUse"))
        		,convertString(map.get("eqPartId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }


	@Override
	public int updateEqPart(Map map) throws Exception {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("UPDATE TAEQPART SET                                   ");
        query.append("       eqasmb_id       = ?                            ");
        query.append("       ,part_id        = ?                            ");
        query.append("       ,consist_qty    = ?                            ");
        query.append("       ,is_use         = ?                            ");
        query.append("WHERE comp_no      = ?                                ");
        query.append("  AND eqpart_id    = ?                                ");
        
        objects = new Object[] {
        		convertString(map.get("eqAsmbId"))
        		,convertString(map.get("partId"))
        		,convertString(map.get("consistQty"))
        		,convertString(map.get("isUse"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("eqPartId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    } 
}